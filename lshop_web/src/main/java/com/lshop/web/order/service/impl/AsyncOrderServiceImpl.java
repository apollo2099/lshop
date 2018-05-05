package com.lshop.web.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.util.GenericJDBCSupport;
import com.lshop.web.order.component.OrderCacheDao;
import com.lshop.web.order.service.AsyncOrderService;
import com.lshop.web.order.vo.OrderCahce;

/**
 * 同步服务采用jdbc去操作,自己管理事务
 * @author caicl
 *
 */
@Service("AsyncOrder")
public class AsyncOrderServiceImpl implements AsyncOrderService{
	private static final Log logger	= LogFactory.getLog(AsyncOrderServiceImpl.class);
	@Resource
	private OrderCacheDao orderCacheDao;
	/**
	 * 队伍中的订单缓存key
	 */
	private BlockingQueue<String> jobQueue = new LinkedBlockingQueue<String>();
	
	public AsyncOrderServiceImpl() {
		super();
		createWorkor(Runtime.getRuntime().availableProcessors());
	}
	@Override
	@PostConstruct
	public void scheduleSyncOrder() {
		//把缓存中订单入库
		Set<String> keys = orderCacheDao.rkeys(RedisKeyConstant.OrderKey("*"));
		if (ObjectUtils.isNotEmpty(keys)) {
			for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
				OrderCahce orderCahce = orderCacheDao.get(iterator.next());
				if (null != orderCahce) {
					
				}
			}
		}
	}
	/**
	 * 创建同步订单线程
	 * @param num
	 */
	private void createWorkor(int num) {
		for (int i = 0; i < num; i++) {
			OrderWorker worker = new OrderWorker();
			Thread wt = new Thread(worker);
			wt.setDaemon(true);
			wt.setName("OrderSync-"+i);
			wt.start();
		}
	}
	@Override
	public void asyncOrder(OrderCahce orderCahce) {
		try {
			jobQueue.put(RedisKeyConstant.OrderKey(orderCahce.getOrder().getOid()));
			synchronized (jobQueue) {
				jobQueue.notifyAll();
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}
	/**
	 * 同步订单到数据库
	 * @param orderCahce
	 * @return
	 */
	private boolean syncOrder(OrderCahce orderCahce) {
		Map<String, List<Object[]>> batchSql = new HashMap<String, List<Object[]>>();
		List<Object[]> paramList = new ArrayList<Object[]>();
		//订单主表
		LvOrder order = orderCahce.getOrder();
		String hql = "INSERT into lv_order(oid, pay_status, status, memid, user_email, paymethod, order_remark," 
				+"postprice, total_price, coupon_total_price, currency, isdelete, is_service_audit,"
				+"is_finance_audit,flag, code, create_time, store_id, is_gift) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[]{
				order.getOid(), order.getPayStatus(), order.getStatus(), order.getMemid(), order.getUserEmail(), order.getPaymethod(), order.getOrderRemark(),
				order.getPostprice(), order.getTotalPrice(), order.getCouponTotalPrice(), order.getCurrency(), order.getIsdelete(), order.getIsServiceAudit(),
				order.getIsFinanceAudit(), order.getFlag(), order.getCode(), order.getCreateTime(), order.getStoreId(), order.getIsGift()
		};
		paramList.add(param);
		batchSql.put(hql, paramList);
		//订单明细
		hql = "INSERT INTO lv_order_details(order_id, product_code, oprice, oremark, pnum, post_price, "
				+"currency, is_delete, is_package, code, create_time, store_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		paramList = new ArrayList<Object[]>();
		for (Iterator<LvOrderDetails> iterator = orderCahce.getDetails().iterator(); iterator.hasNext();) {
			LvOrderDetails details = iterator.next();
			param = new Object[]{
					details.getOrderId(), details.getProductCode(), details.getOprice(), details.getOremark(), details.getPnum(), details.getPostPrice(),
					details.getCurrency(), details.getIsDelete(), details.getIsPackage(), details.getCode(), details.getCreateTime(), details.getStoreId()
			};
			paramList.add(param);
		}
		batchSql.put(hql, paramList);
		//订单收货地址
		LvOrderAddress address = orderCahce.getAddress();
		hql = "INSERT INTO lv_order_address(order_id, rel_code, rel_name, post_code, mobile, tel,"
				+"contry_id, contry_name, province_id, province_name, city_id, city_name,"
				+"adress, best_delivery_time,store_id, code, create_time) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		paramList = new ArrayList<Object[]>();
		param = new Object[]{
				address.getOrderId(), address.getRelCode(), address.getRelName(), address.getPostCode(), address.getMobile(), address.getTel(),
				address.getContryId(), address.getContryName(), address.getProvinceId(), address.getProvinceName(), address.getCityId(), address.getCityName(),
				address.getAdress(), address.getBestDeliveryTime(), address.getStoreId(), address.getCode(), address.getCreateTime()
		};
		paramList.add(param);
		batchSql.put(hql, paramList);
		//订单套餐明细表
		if (ObjectUtils.isNotEmpty(orderCahce.getPackageDetail())) {
			paramList = new ArrayList<Object[]>();
			for (Iterator<LvOrderPackageDetails> iterator = orderCahce.getPackageDetail().iterator(); iterator.hasNext();) {
				LvOrderPackageDetails pack = iterator.next();
				hql = "INSERT into lv_order_package_details(order_details_code, pnum, product_code, pcode, currency, code,"
						+"oprice, create_time ) VALUES(?,?,?,?,?,?,?,?)";
				param = new Object[]{
						pack.getOrderDetailsCode(), pack.getPnum(), pack.getProductCode(), pack.getPcode(), pack.getCurrency(), pack.getCode(),
						pack.getOprice(), pack.getCreateTime()
				};
				paramList.add(param);
			}
			batchSql.put(hql, paramList);
		}
		//保存订单信息
		try {
			GenericJDBCSupport.executeBatch(batchSql);
			//缓存同步设置
			orderCacheDao.delete(RedisKeyConstant.OrderKey(order.getOid()));
		} catch (Exception e) {
			//同步失败
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}
	/**
	 * 同步订单工作线程
	 * @author caicl
	 *
	 */
	class OrderWorker implements Runnable{
		public OrderWorker() {
			super();
		}
		
		@Override
		public void run() {
			OrderCahce order = null;
			String key = null;
			try {
				while(true){
					key = jobQueue.poll();
					if (null == key) {
						synchronized (jobQueue) {
							jobQueue.wait();
						}
					} else {
						order = orderCacheDao.get(key);
						if (null != order) {
							boolean res = syncOrder(order);
							if (!res) {
								logger.error(Thread.currentThread().getName()+"同步订单失败:"+order.getOrder().getOid());
							}
							order = null;
						}
					}
				}
			} catch (InterruptedException e) {
				logger.info("订单同步线程中断!");
			}
		}
	}
}
