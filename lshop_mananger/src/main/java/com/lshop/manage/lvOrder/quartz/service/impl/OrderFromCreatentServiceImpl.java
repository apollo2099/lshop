package com.lshop.manage.lvOrder.quartz.service.impl;


import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.DAOException;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvOrder.quartz.service.OrderFromCreatentService;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.ws.web.creatent.order.ThreeOrderSendDto;
import com.lshop.ws.web.creatent.order.ThreeOrderSendDtoResposne;
import com.lshop.ws.web.creatent.order.WSThreeOrderService;


/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_mananger] 
 * @Package:      [com.lshop.manage.lvOrder.quartz.service.impl.OrderFromCreatentServiceImpl.java]  
 * @ClassName:    [OrderFromCreatentServiceImpl]   
 * @Description:  [定时处理异常已发货未同步物流信息的订单，做物流信息同步的定时任务处理]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2015-2-3 上午10:54:31]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2015-2-3 上午10:54:31]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
@Service("OrderFromCreatentService")
public class OrderFromCreatentServiceImpl implements OrderFromCreatentService{
	private static final Log logger	= LogFactory.getLog(OrderFromCreatentServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private LvOrderService lvOrderService;
	@Resource
    private WSThreeOrderService wsThreeOrderService;
	
	
	
	/**
	 * 
	 * @Method: OrderToCreatentTask 
	 * @Description:  [支付成功發送訂單信息到启创代理商后台(线程入口程序)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-25 下午6:11:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-25 下午6:11:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @Version:      [v3.10.2]
	 * @throws
	 */
	@Override
	public void orderFromCreatentTask() throws ServiceException {
		//线程包装
		OrderFromCreatentThread t = new OrderFromCreatentThread();
		//创建优惠券线程
		Calendar cal = Calendar.getInstance();
		t.setName("thread-orderFromCreatentTask-"+cal.getTimeInMillis());
		//设置守护线程
		t.setDaemon(true);
		t.start();
	}
	
	/**
	 * 
	 * Simple to Introduction  
	 * @ProjectName:  [lshop_mananger] 
	 * @Package:      [com.lshop.manage.lvOrder.quartz.service.impl.OrderFromCreatentServiceImpl.java]  
	 * @ClassName:    [OrderFromCreatentThread]   
	 * @Description:  [定义内部线程类(支付成功發送訂單信息到启创代理商后台)]   
	 * @Author:       [liaoxiongjian]   
	 * @CreateDate:   [2014-12-25 下午6:24:00]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2014-12-25 下午6:24:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @Version:      [v3.10.2]
	 */
	class OrderFromCreatentThread extends Thread {
		public OrderFromCreatentThread() {
			super();
		}
		@Override
		public void run() {
			synchronized(this){
				synOrderLogisticsFromCreatent();
			}
		}
	}
	
	
	/**
	 * 
	 * @Method: synOrderLogisticsFromCretent 
	 * @Description:  [同步调用启创同步物流接口]
	 *                规则：系统自动同步已经发货没有物流信息的订单；  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-22 上午9:55:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-22 上午9:55:48]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	public void synOrderLogisticsFromCreatent(){
 		try {
			//查询已经审核未发货同步物流的订单信息
			List<LvOrder> orderList=lvOrderService.findUnfilledOrder();
			if(ObjectUtils.isNotEmpty(orderList)){
				for (LvOrder lvOrder : orderList) {
					ThreeOrderSendDtoResposne orderSendResponse= wsThreeOrderService.orderList(lvOrder.getOid(), "", "", "", "");
					if(orderSendResponse!=null){
						if(ObjectUtils.isNotEmpty(orderSendResponse.getResult().getStatus())&&orderSendResponse.getResult().getStatus()==1){
							List<ThreeOrderSendDto> treeOrderList=orderSendResponse.getOrderList();
							if(treeOrderList!=null){
								for (ThreeOrderSendDto threeOrderSend : treeOrderList) {
									if(ObjectUtils.isNotEmpty(threeOrderSend)){
										if (lvOrder != null&& lvOrder.getStatus() == Constants.ORDER_STATUS_0) {
											BaseDto dto = new BaseDto();
											LvOrder tempOrder = new LvOrder();
											tempOrder.setOid(threeOrderSend.getOrderCode());//订单号
											tempOrder.setExpressCompany(threeOrderSend.getDeliveryType());
											tempOrder.setExpressName(threeOrderSend.getDeliveryType());
											tempOrder.setExpressNum(threeOrderSend.getDeliveryNum());
											tempOrder.setShipTime(DateUtils.convertToDateTime(threeOrderSend.getSendDate()));
											tempOrder.setStatus(Short.valueOf(String.valueOf(threeOrderSend.getOrderStatus())));
											dto.put("lvOrder", tempOrder);
											dto.put("uName", "CREATENT");
											dto.put("operate","CREATENT_SEND_LOGISTICS");
											// 调用同步物流实现
											lvOrderService.doOrder(dto);
										}
									}
								}
							}
						}
					}
				}
			}			
		} catch (DAOException e) {
			logger.error(e.getMessage(),e);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
