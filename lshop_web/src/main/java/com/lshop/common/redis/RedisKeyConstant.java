package com.lshop.common.redis;

/**
 * redis键
 * @author caicl
 *
 */
public class RedisKeyConstant {
	/**
	 * 空值
	 */
	public static final String EMPTY = "EMPTY";
	/**
	 * 商品缓存key
	 * @param prodNo
	 * @return
	 */
	public static final String ProducKey(String prodNo) {
		return "PROD_" + prodNo;
	}
	/**
	 * 购物车缓存key
	 * @param userCode
	 * @param storeId
	 * @return
	 */
	public static final String ShopCartKey(String userCode, String storeId) {
		return "SC_"+storeId+"_"+userCode;
	}
	/**
	 * 缓存订单key
	 * @param orderNo
	 * @return
	 */
	public static final String OrderKey(String orderNo) {
		return "ORDER_" + orderNo;
	}
	/**
	 * jsp片断
	 * @param uri
	 * @return
	 */
	public static final String FrameKey(String uri) {
		return "FRAME_"+uri;
	}
	/**
	 * 广告内容html缓存key
	 * @param storeFlag
	 * @param adKey
	 * @return
	 */
	public static final String ADKey(String storeFlag, String adKey) {
		return "AD_"+storeFlag+"_"+adKey;
	}
	public static final String ADKey(String storeAdkey) {
		return "AD_"+storeAdkey;
	}
	/**
	 * 收货地址运费key
	 * @param storeFlag
	 * @param deliverId
	 * @return
	 */
	public static final String CarriageKey(String storeFlag, String deliverId) {
		return "CARRIAGE_"+storeFlag+"_"+deliverId;
	}
	public static final String CarriageKey(String storeDeliver) {
		return "CARRIAGE_"+storeDeliver;
	}
	/**
	 * 店铺支付方式key
	 * @param storeFlag
	 * @return
	 */
	public static final String PaymentKey(String storeFlag) {
		return "PAYMENT_"+storeFlag;
	}
	/**
	 * 用户收货地址
	 * @param userCode
	 * @return
	 */
	public static final String AccountAddressKey(String userCode) {
		return "ADDR_"+userCode;
	}
	/**
	 * 用户默认支付方式key
	 * @param userCode
	 * @return
	 */
	public static final String UserPayment(String userCode) {
		return "USER_PAYMENT_"+userCode;
	}
}
