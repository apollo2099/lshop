package com.lshop.manage.lvCoupon.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.RedisClient;
import com.lshop.manage.cache.service.RedisService;
import com.lshop.manage.lvCoupon.service.CouponRedisServer;
import com.lshop.manage.lvCoupon.service.CouponTaskService;
import com.lshop.manage.lvCoupon.service.LvCouponService;
import com.lshop.manage.lvCoupon.vo.CouponVo;

@Service("CouponTaskService")
public class CouponTaskServiceImpl implements CouponTaskService {
	private static final Log logger = LogFactory.getLog(CouponTaskServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private LvCouponService lvCouponService;
	@Resource
	private CouponRedisServer couponRedisServer;
	
	/**
	 * 
	 * Simple to Introduction  
	 * @ProjectName:  [lshop_mananger] 
	 * @Package:      [com.lshop.manage.lvCoupon.service.impl.CouponTaskServiceImpl.java]  
	 * @ClassName:    [CouponThread]   
	 * @Description:  [定义优惠码生产内部线程类]   
	 * @Author:       [liaoxiongjian]   
	 * @CreateDate:   [2015-1-10 下午2:25:47]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2015-1-10 下午2:25:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @Version:      [v2.0]
	 */
	class CouponThread extends Thread {
		private ThreadLocal<CouponVo> params = new ThreadLocal<CouponVo>();
		private CouponVo param;

		public CouponVo getParam() {
			return param;
		}
		public void setParam(CouponVo param) {
			this.param = param;
		}
		public CouponThread(CouponVo param) {
			super();
			this.param = param;
		}

		@Override
		public void run() {
			synchronized(this){
		    System.out.println("=============="+param.getCreateNum());
			AtomicInteger createNum = param.getCreateNum();// 创建优惠券数目
			String couponTypeCode=param.getCouponTypeCode();// 优惠券编码
			Short couponStatus=param.getCouponStatus();// 优惠码状态
			String operator=param.getOperator(); //操作人
			String activityCode = param.getActivityCode();// 活动编码
			Short couponType = param.getCouponType();// 优惠券类型（1线上，2线下）
			String prizeCode = param.getPrizeCode();// 奖品编码
			// 活动优惠券map（活动号，map（优惠券业务编码，优惠码对象））
			Map<String, Map<String, LvCoupon>> activityCouponMap = new HashMap<String, Map<String, LvCoupon>>();
			// 优惠码map（优惠码业务编码，优惠码对象）
			Map<String, LvCoupon> couponMap = new HashMap<String, LvCoupon>();
	

			// 生成对应数量优惠码信息
			for (int i = 0; i < createNum.get(); i++) {
				LvCoupon coupon = new LvCoupon();

				// 调用生成优惠券服务
				String couponCode = lvCouponService.createCouponCode();
				
				// 组装优惠券信息
				coupon.setCouponCode(couponCode);
				coupon.setCouponTypeCode(couponTypeCode);
				coupon.setCouponStatus(couponStatus);
				coupon.setGrantWay(activityCode);
				coupon.setGrantType(couponType);
				coupon.setOperator(operator);
				coupon.setCreateTime(new Date());
				coupon.setCode(CodeUtils.getCode());
				dao.save(coupon);
								

				// 缓存优惠券
				String code = coupon.getCode();// 优惠码编码
				if (ObjectUtils.isNotEmpty(prizeCode)) {
					if (activityCouponMap.containsKey(prizeCode)) {
						couponMap = activityCouponMap.get(prizeCode);
						couponMap.put(code, coupon);
					} else {
						couponMap.put(code, coupon);
						activityCouponMap.put(prizeCode, couponMap);
					}
				} else {
					if (activityCouponMap.containsKey(activityCode)) {
						couponMap = activityCouponMap.get(activityCode);
						couponMap.put(code, coupon);
					} else {
						couponMap.put(code, coupon);
						activityCouponMap.put(activityCode, couponMap);
					}
				}

			}



			try {
				// 更新优惠券缓存
				couponRedisServer.cacheActivityCoupon(activityCouponMap);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			System.out.println("==============end,"+param.getCreateNum());
		  }
			
		}
		
	}

	@Override
	public void createBatchCoupon(Integer createNum, String activityCode, String couponTypeCode,Short couponStatus,Short couponType,String operator) throws ServiceException {
		//线程包装
		AtomicInteger atoIntere=new AtomicInteger(createNum); 
		CouponVo innerParam = new CouponVo(activityCode,atoIntere,couponTypeCode,couponStatus,operator,couponType);
		CouponThread t = new CouponThread(innerParam);
		//创建优惠券线程
		Calendar cal = Calendar.getInstance();
		t.setName("thread-createCoupon-"+cal.getTimeInMillis());
		//设置守护线程
		t.setDaemon(true);
		t.start();
	}
	
	@Override
	public void createBatchCoupon(Integer createNum, String activityCode, String couponTypeCode,Short couponStatus,Short couponType,String operator,String prizeCode) throws ServiceException {
		//线程包装
		AtomicInteger atoCreateNum=new AtomicInteger(createNum); 
		CouponVo innerParam = new CouponVo(activityCode,atoCreateNum, couponTypeCode,couponStatus,operator,couponType, prizeCode);
		CouponThread t = new CouponThread(innerParam);
		//创建优惠券线程
		Calendar cal = Calendar.getInstance();
		t.setName("thread-createCoupon-"+cal.getTimeInMillis());
		//设置守护线程
		t.setDaemon(true);
		t.start();
	}
}
