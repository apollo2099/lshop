package com.lshop.common.coupon.constant;

public class CouponConstant {
	/**
	 * 启用
	 */
	public static final int STATUS_USE = 1;
	/**
	 * 关闭
	 */
	public static final int STATUS_DIS_USE = 0;
	/**
	 * 线上
	 */
	public static final String TYPE_ONLINE_NAME = "TYPE_ONLINE";
	/**
	 * 线上
	 */
	public static final short TYPE_ONLINE = 1;
	/**
	 * 线下
	 */
	public static final String TYPE_BELOW_LINE_NAME = "TYPE_BELOW_LINE_NAME";
	/**
	 * 线下
	 */
	public static final short TYPE_BELOW_LINE = 2;
	/**
	 * 未领取，未使用
	 */
	public static final short STATUS_DISGET_DISUSE = 0;
	/**
	 * 已领取，未使用
	 */
	public static final short STATUS_GET_DISUSE = 1;
	/**
	 * 已领取，已使用
	 */
	public static final short STATUS_GET_USE = 2;
	/**
	 * 关联类型(商品类型)
	 */
	public static final int SCOPE_TYPE = 1;
	/**
	 * 关联类型(商品)
	 */
	public static final int SCOPE_PRODUCT = 2;
	/**
	 * 币种（美元）
	 */
	public static final String CURRENCY_USD = "USD";
	/**
	 * 币种（人民币）
	 */
	public static final String CURRENCY_CNY = "CNY";
	/**
	 * 存入Redis关键字
	 */	
	public static String getCouponKey(String couponCode) {
		return "COUPON_"+couponCode;
	}

}
