package com.lshop.manage.lvCoupon.service;

import java.util.Map;

import com.lshop.common.pojo.logic.LvCoupon;

public interface CouponRedisServer {

	/**
	 * 
	 * @Method: cacheCouponCode 
	 * @Description:  [缓存优惠码数据（优惠码，优惠码）]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-12 上午11:53:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-12 上午11:53:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param couponCodeMap 
	 * @return void
	 */
	public void cacheCouponCode(String couponKey,String couponValue) throws Exception;
	/**
	 * 缓存活动优惠券 （活动号，set（优惠券业务编号））
	 * 
	 * @param activityCouponMap
	 * @throws Exception
	 */
	public void cacheActivityCoupon(Map<String, Map<String, LvCoupon>> activityCouponMap) throws Exception;

	/**
	 * 缓存领券活动，赠券活动优惠券
	 * 
	 * @throws Exception
	 */
	public void initActivityCouponCache() throws Exception;
	/**
	 * 初始化优惠码到缓存中
	 * @Method: initCouponCache 
	 * @throws Exception 
	 * @return void
	 */
	public void initCouponCache() ;
	
}
