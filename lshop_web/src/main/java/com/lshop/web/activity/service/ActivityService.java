package com.lshop.web.activity.service;

import java.util.Map;

import com.lshop.common.activity.vo.Activity;
import com.lshop.common.activity.vo.ActivityLimitOrder;
import com.lshop.common.activity.vo.ActivityLimitTime;
import com.lshop.common.activity.vo.ActivityLimited;
import com.lshop.common.activity.vo.ActivityLink;
import com.lshop.common.activity.vo.GeneralActivity;
import com.lshop.common.activity.vo.OrderActivity;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvActivity;

/**
 * 
 * @Description:活动管理
 * @author CYJ
 * @date 2014-6-18 下午3:52:35
 */
public interface ActivityService {
	/**
	 * 
	 * @Description:获取活动信息
	 * @author CYJ
	 * @date 2014-6-18 下午3:54:09
	 * @param productNo
	 *            商品编码
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public Activity getActivityVoByProductNo(String productNo) throws Exception;

	/**
	 * 
	 * @Description:获取限时活动信息
	 * @author CYJ
	 * @date 2014-6-18 下午5:25:26
	 * @param activityCode
	 *            活动号
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public ActivityLimitTime getActivityLimitTimeByCode(String activityCode) throws Exception;

	/**
	 * 
	 * @Description:获取限量活动信息
	 * @author CYJ
	 * @date 2014-6-18 下午5:25:26
	 * @param activityCode
	 *            活动号
	 * @return
	 * @throws Exception
	 */
	public ActivityLimited getActivityLimitedByCode(String activityCode) throws Exception;

	/**
	 * 
	 * @Description:获取订单参加赠送优惠券活动信息
	 * @author CYJ
	 * @date 2014-6-24 下午4:16:33
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ActivityLimitOrder getActivityLimitOrder(String storeId, Float dAmount) throws Exception;
	/**
	 * 获取订单满就送抽奖机会活动信息
	 * @param storeId
	 * @param dAmount
	 * @return
	 * @throws Exception
	 */
	public ActivityLimitOrder getActivityLimitOrderLottery(String storeId, Float dAmount) throws Exception;

	/**
	 * 
	 * @Description:获取商品对应的有效活动（限时，限量。包括即将开始，正在进行）
	 * @author CYJ
	 * @date 2014-6-27 上午10:07:26
	 * @param productNo
	 * @return
	 * @throws Exception
	 */
	public GeneralActivity getGeneralActivityByProductNo(String productNo) throws Exception;

	/**
	 * 
	 * @Description:获取商品对应的有效活动（限时，限量。正在进行的活动）
	 * @author CYJ
	 * @date 2014-6-29 上午11:42:43
	 * @param productNo
	 * @return
	 * @throws Exception
	 */
	public GeneralActivity getNowGeneralActivityByProductNo(String productNo) throws Exception;

	/**
	 * 
	 * @Description:通过活动获取优惠券
	 * @author CYJ
	 * @date 2014-6-27 下午3:17:02
	 * @param activityCode
	 *            活动业务编码
	 * @param type
	 *            活动类型
	 * @param username
	 *            用户
	 * @return
	 * @throws Exception
	 */
	public ResultMsg getCouponByActivity(String activityCode, Integer type, String username) throws Exception;
	/**
	 * 从缓存获取优惠券业务编码
	 * @param activityCode
	 * @return
	 * @throws Exception 
	 */
	public String getCouponByCache(String activityCode) throws Exception;
	/**
	 * 
	 * @Description:领取优惠券（领券活动）
	 * @author CYJ
	 * @date 2014-6-20 上午11:26:27
	 * @param activityCode
	 *            活动号
	 * @param username
	 *            用户名
	 * @param flag 
	 * @return
	 * @throws Exception
	 */
	public ResultMsg getCouponByActivityLink(String activityCode, String username, String flag) throws Exception;

	/**
	 * 
	 * @Description:生成订单与活动关系信息
	 * @author CYJ
	 * @date 2014-6-29 上午10:47:17
	 * @param orderActivity
	 * @return
	 * @throws Exception
	 */
	public ResultMsg createOrderActivity(OrderActivity orderActivity) throws Exception;

	/**
	 * 
	 * @Description:订单已支付,完成订单相关活动(送券，扣减送券库存，扣减限量库存)
	 * @author CYJ
	 * @date 2014-6-29 下午5:46:48
	 * @param orderNo
	 * @param username
	 * @param Map
	 *            <String, Integer> productQuantity
	 * @return
	 * @throws Exception
	 */
	public ResultMsg finishOrderActivity(String orderNo, String username, Map<String, Integer> productQuantity) throws Exception;

	/**
	 * 
	 * @Description:更新链接领券活动缓存
	 * @author CYJ
	 * @date 2014-7-2 上午10:00:32
	 * @param activityLink
	 * @return
	 * @throws Exception
	 */
	public ResultMsg updateLinkActivityMap(ActivityLink activityLink) throws Exception;

	/**
	 * 检查用户是否满足领取数量限制，并预占缓存
	 * 
	 * @param activityCode
	 * @param username
	 * @param limitNum
	 * @return
	 * @throws Exception
	 */
	public ResultMsg isbelowCouponLimit(String activityCode, String username, int limitNum) throws Exception;

	/**
	 * 初始化活动用户领券数量
	 * 
	 * @throws Exception
	 */
	public void initActivityUserCache() throws Exception;
	/**
	 * 根据活动号返回订单活动详情
	 * @param activityCode
	 * @return
	 * @throws Exception
	 */
	public ActivityLimitOrder getActivityLimitOrderByCode(String activityCode) throws Exception;
	/**
	 * 根据活动号返回活动主信息
	 * @param activityCode
	 * @return
	 * @throws Exception
	 */
	public LvActivity getActivity(String activityCode) throws Exception;
}
