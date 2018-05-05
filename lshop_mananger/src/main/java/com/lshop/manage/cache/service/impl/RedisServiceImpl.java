package com.lshop.manage.cache.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.coupon.constant.CouponConstant;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.util.GenericJDBCSupport;
import com.lshop.common.util.RedisClient;
import com.lshop.manage.cache.service.RedisService;

@Service("RedisService")
public class RedisServiceImpl implements RedisService {
	private static final Log logger = LogFactory.getLog(RedisServiceImpl.class);

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
						"SELECT a.code,c.code FROM lv_activity a,lv_activity_link al,lv_coupon c WHERE a.code=al.activity_code AND al.given_product_code=c.coupon_type_code AND a.type_key=? AND a.status=? AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) AND c.coupon_status=? AND c.grant_type=?",
						new Object[] { ActivityConstant.TYPE_LINK, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE,
								CouponConstant.TYPE_ONLINE });
		// 订单赠券活动
		List<Object[]> giveList = GenericJDBCSupport
				.queryBySQLForSlave(
						"SELECT a.code,c.code FROM lv_activity a,lv_activity_limit_order alo,lv_coupon c WHERE a.code=alo.activity_code AND alo.given_product_code=c.coupon_type_code AND a.type_key=? AND a.status=? AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) AND c.coupon_status=? AND c.grant_type=?",
						new Object[] { ActivityConstant.TYPE_LIMIT_ORDER, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE,
								CouponConstant.TYPE_ONLINE });
        // 注册赠送活动
		List<Object[]> registerList = GenericJDBCSupport
				.queryBySQLForSlave(
						"SELECT a.code,c.code FROM lv_activity a,lv_activity_register ar,lv_coupon c WHERE a.code=ar.activity_code AND ar.given_product_code=c.coupon_type_code AND a.type_key=? AND a.status=? AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) AND c.coupon_status=? AND c.grant_type=?",
						new Object[] { ActivityConstant.TYPE_REGISTER, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE,
								CouponConstant.TYPE_ONLINE });
	   	
		// 分享就送活动 
		List<Object[]> shareList = GenericJDBCSupport
				.queryBySQLForSlave(
						"SELECT a.code,c.code FROM lv_activity a,lv_activity_share ash,lv_coupon c WHERE a.code=ash.activity_code AND ash.given_product_code=c.coupon_type_code AND a.type_key=? AND a.status=? AND ((a.start_time<=? AND a.end_time>=?) OR a.start_time>?) AND c.coupon_status=? AND c.grant_type=?",
						new Object[] { ActivityConstant.TYPE_SHARE, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate, CouponConstant.STATUS_DISGET_DISUSE,
								CouponConstant.TYPE_ONLINE });
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		list.addAll(getList);
		list.addAll(giveList);
		list.addAll(registerList);
		list.addAll(shareList);

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
}