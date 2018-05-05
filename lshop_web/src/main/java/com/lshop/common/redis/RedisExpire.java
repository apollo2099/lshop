package com.lshop.common.redis;

/**
 * 缓存失效时间
 * @author caicl
 *
 */
public class RedisExpire {
	/**
	 * 广告,html页面片断
	 */
	public static long CacheSecond = 600;
	/**
	 * 用户店铺支付方式
	 */
	public static long UserPaymentDay = 7;
	/**
	 * 用户收货地址
	 */
	public static long AccountAddressDay = 7;
	/**
	 * 地区运费
	 */
	public static long CarriageDay = 2;
	/**
	 * 店铺支付方式
	 */
	public static long PaymentDay = 2;
}
