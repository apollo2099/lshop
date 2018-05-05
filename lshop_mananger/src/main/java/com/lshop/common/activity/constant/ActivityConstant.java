package com.lshop.common.activity.constant;

public class ActivityConstant {
	/**
	 * 启用
	 */
	public static final int STATUS_USE = 1;
	/**
	 * 关闭
	 */
	public static final int STATUS_DIS_USE = 0;
	
	/**
	 * 活动类型字典-限时
	 */
	public static final int TYPE_LIMIT_TIME = 1;
	/**
	 * 活动类型字典-限量
	 */
	public static final int TYPE_LIMIT_QUANTITY = 2;
	/**
	 * 活动类型字典-订单满就送活动
	 */
	public static final int TYPE_LIMIT_ORDER = 3;
	/**
	 * 活动类型字典-点击连接就送活动
	 */
	public static final int TYPE_LINK = 4;
	/**
	 * 活动类型字典-注册就送活动
	 */
	public static final int TYPE_REGISTER=5;
	/**
	 * 活动类型字典-分享就送活动
	 */
	public static final int TYPE_SHARE=6;
	/**
	 * 活动类型字典-抽奖活动
	 */
	public static final int TYPE_LOTTERY=7;
	/**
	 * 活动类型字典-购买指定商品就送活动
	 */
	public static final int TYPE_APPOINT_PRODUCT=8;
	/**
	 * 活动类型字典-购买指定商品就送活动
	 */
	public static final int TYPE_MAC=9;
	
	/**
	 * 赠送类型字典:赠送优惠券
	 */
	public static short GIVEN_TYPE_NUM_COUPON=1;//
	/**
	 * 赠送类型字典:赠送抽奖机会
	 */
	public static short GIVEN_TYPE_NUM_LOTTORY=2;//
	/**
	 * 赠送类型字典:赠送礼品
	 */
	public static short GIVEN_TYPE_NUM_GIFT=3;//

	/**
	 * 用户奖品状态字典:未兑奖
	 */
	public static short ACCOUNT_PRIZE_STATUS_LOTTERY_NO=0;
	/**
	 * 用户奖品状态字典:已兑奖
	 */
	public static short ACCOUNT_PRIZE_STATUS_LOTTERY_YES=1;
	/**
	 * 用户奖品状态字典:已发货
	 */
	public static short ACCOUNT_PRIZE_STATUS_LOTTERY_SEND=2;
	/**
	 * 用户奖项可抽状态字典:是
	 */
	public static short PRIZE_IS_DRAW_YES=1;
	/**
	 * 用户奖项可抽状态字典:否
	 */
	public static short PRIZE_IS_DRAW_NO=0;
	
	/**
	 * 订单活动状态(0=参与活动中)
	 */
	public static final int STATUS_ORDER_ACTIVITY = 0;
	/**
	 * 订单活动状态(1=参与活动完毕)
	 */
	public static final int STATUS_ORDER_ACTIVITY_FINISH = 1;
	/**
	 * 已领取(返回编码)
	 */
	public static final String RETURN_CODE_888 = "C888";
	/**
	 * 领取完(返回编码)
	 */
	public static final String RETURN_CODE_404 = "C404";
	/**
	 * 未开始(返回编码)
	 */
	public static final String RETURN_CODE_000 = "C000";
	/**
	 * 已结束(返回编码)
	 */
	public static final String RETURN_CODE_666 = "C666";

	/**
	 * 超出领券限额(领券活动)
	 */
	public static final String RETURN_CODE_LIMIT_888 = "L888";

	/**
	 * 并发异常(领券活动)
	 */
	public static final String RETURN_CODE_LIMIT_505 = "L505";
	
	/**
	 * 领券策略-无限制
	 */
	public static final int STRATEG_TYPE_LIMIT_NO=1;
	/**
	 * 领券策略-登录限制
	 */
	public static final int STRATEG_TYPE_LIMIT_LOGIN=2;
	/**
	 * 领券策略-IP限制
	 */
	public static final int STRATEG_TYPE_LIMIT_IP=3;
	
	
	

	public static String getActivityCouponKey(String activityCode) {
		return "ACTIVITY_COUPON_" + activityCode;
	}

	public static String getActivityUserKey(String activityCode, String username) {
		return "ACTIVITY_USER_" + activityCode + "_" + username;
	}
	
	public static String getActivityKey(String activityCode){
		return "ACTIVITY_" + activityCode;
	}
}
