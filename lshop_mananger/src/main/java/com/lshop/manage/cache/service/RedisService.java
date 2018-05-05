/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.cache.service;

import java.util.Map;

import com.lshop.common.pojo.logic.LvCoupon;

public interface RedisService {
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
}
