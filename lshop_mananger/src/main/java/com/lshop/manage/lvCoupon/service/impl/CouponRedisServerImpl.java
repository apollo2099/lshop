package com.lshop.manage.lvCoupon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.coupon.constant.CouponConstant;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.util.GenericJDBCSupport;
import com.lshop.common.util.RedisClient;
import com.lshop.manage.lvCoupon.service.CouponRedisServer;
import com.lshop.manage.lvCoupon.service.LvCouponService;

@Service("CouponRedisServer")
public class CouponRedisServerImpl implements CouponRedisServer {
	private static final Log logger = LogFactory.getLog(CouponRedisServerImpl.class);
	@Resource
	private LvCouponService lvCouponService;
	

	public void cacheCouponCode(String couponKey,String couponValue) throws Exception{
		Jedis jedis = null;
		try {
			jedis = RedisClient.getRedis();
			// 更新优惠码缓存
			jedis.sadd(couponKey, couponValue);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			RedisClient.closeRedis(jedis);
		}
	}
	
	
	
	
	@Override
	public void cacheActivityCoupon(Map<String, Map<String, LvCoupon>> activityCouponMap) throws Exception {
		Jedis jedis = null;
		try {
			jedis = RedisClient.getRedis();

			for (Entry<String, Map<String, LvCoupon>> activityCoupon : activityCouponMap.entrySet()) {
				String activityCode = activityCoupon.getKey();

				String activityKey = ActivityConstant.getActivityCouponKey(activityCode);

				Map<String, LvCoupon> tempCouponMap = activityCoupon.getValue();
				for (Entry<String, LvCoupon> tempCoupon : tempCouponMap.entrySet()) {

					String code = tempCoupon.getKey();
					// 更新优惠券缓存
					jedis.sadd(activityKey, code);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			RedisClient.closeRedis(jedis);
		}

	}

	@Override
	public void initActivityCouponCache() throws Exception {
		Date nowDate = new Date();
		// 领券活动
		List<Object[]> getList = GenericJDBCSupport
				.queryBySQLForSlave(
						"SELECT a.code,c.code FROM lv_activity a,lv_activity_link al,lv_coupon c WHERE a.code=al.activity_code AND al.given_product_code=c.coupon_type_code AND a.code=c.grant_way AND a.type_key=? AND a.status=? AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) AND c.coupon_status=?",
						new Object[] { ActivityConstant.TYPE_LINK, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE});
		// 订单赠券活动
		List<Object[]> giveList = GenericJDBCSupport
				.queryBySQLForSlave(
						"SELECT a.code,c.code FROM lv_activity a,lv_activity_limit_order alo,lv_coupon c WHERE a.code=alo.activity_code AND alo.given_product_code=c.coupon_type_code AND a.code=c.grant_way AND a.type_key=? AND a.status=? AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) AND c.coupon_status=? AND c.grant_type=?",
						new Object[] { ActivityConstant.TYPE_LIMIT_ORDER, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE,
								CouponConstant.TYPE_ONLINE });
        // 注册赠送活动
		List<Object[]> registerList = GenericJDBCSupport
				.queryBySQLForSlave(
						"SELECT a.code,c.code FROM lv_activity a,lv_activity_register ar,lv_coupon c WHERE a.code=ar.activity_code AND ar.given_product_code=c.coupon_type_code AND a.code=c.grant_way AND a.type_key=? AND a.status=? AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) AND c.coupon_status=? AND c.grant_type=?",
						new Object[] { ActivityConstant.TYPE_REGISTER, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE,
								CouponConstant.TYPE_ONLINE });
	   	
		// 分享就送活动 
		List<Object[]> shareList = GenericJDBCSupport
				.queryBySQLForSlave(
						"SELECT a.code,c.code FROM lv_activity a,lv_activity_share ash,lv_coupon c WHERE a.code=ash.activity_code AND ash.given_product_code=c.coupon_type_code AND a.code=c.grant_way AND a.type_key=? AND a.status=? AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) AND c.coupon_status=? AND c.grant_type=?",
						new Object[] { ActivityConstant.TYPE_SHARE, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE,
								CouponConstant.TYPE_ONLINE });
		
		//购买指定商品就送活动
		List<Object[]> appointList = GenericJDBCSupport
				.queryBySQLForSlave(
						" SELECT a.code,c.code " +
						" FROM lv_activity a,lv_activity_appoint_product ar,lv_coupon c " +
						" WHERE a.code=ar.activity_code " +
						" AND ar.given_product_code=c.coupon_type_code" +
						" AND a.code=c.grant_way " +
						" AND a.type_key=? " +
						" AND a.status=? " +
						" AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) " +
						" AND c.coupon_status=? " +
						" AND c.grant_type=?",
						new Object[] { ActivityConstant.TYPE_APPOINT_PRODUCT, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE,
								CouponConstant.TYPE_ONLINE });
		
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		list.addAll(getList);
		list.addAll(giveList);
		list.addAll(registerList);
		list.addAll(shareList);
		list.addAll(appointList);

		Jedis jedis = null;
		try {
			jedis = RedisClient.getRedis();
			if (CollectionUtils.isNotEmpty(list)) {
				for (Object[] objs : list) {
					String activityCode = objs[0].toString();// 活动号
					String code = objs[1].toString();// 优惠券业务编码

					String activityKey = ActivityConstant.getActivityCouponKey(activityCode);

					
					// 更新优惠券缓存
					jedis.sadd(activityKey, code);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			RedisClient.closeRedis(jedis);
		}	
}
	
	// 初始化加载所有的优惠码数据信息
	public void initCouponCache(){
		logger.info("=================初始化优惠码缓存数据 开始===========================");
		BaseDto dto = new BaseDto();
		List<LvCoupon> couponList = lvCouponService.findAll(dto);
		if (ObjectUtils.isNotEmpty(couponList)) {
			logger.info("=================优惠码总条数:"+couponList.size()+"===========================");
			for (LvCoupon lvCoupon : couponList) {
				
				
				if (ObjectUtils.isNotEmpty(lvCoupon.getCouponCode())) {
					// 设置优惠券码数据到缓存中
					try {
						String couponKey=CouponConstant.getCouponKey(lvCoupon.getCouponCode());
						String couponValue=lvCoupon.getCouponCode();
						cacheCouponCode(couponKey,couponValue);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		}
		logger.info("=================初始化优惠码缓存数据 开始===========================");
	}
	
	
	
	
}
