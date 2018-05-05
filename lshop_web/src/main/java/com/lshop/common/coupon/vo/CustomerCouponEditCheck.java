package com.lshop.common.coupon.vo;

import java.util.Map;

public class CustomerCouponEditCheck {
	/**
	 * 优惠券编码
	 */
	private String couponCode;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 店铺标识
	 */
	private String storeId;
	/**
	 * 订单商品编码数量
	 */
	private Map<String, Integer> productNumMap;

	public CustomerCouponEditCheck() {
		super();
	}

	public CustomerCouponEditCheck(String couponCode, String username, String storeId, Map<String, Integer> productNumMap) {
		super();
		this.couponCode = couponCode;
		this.username = username;
		this.storeId = storeId;
		this.productNumMap = productNumMap;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Map<String, Integer> getProductNumMap() {
		return productNumMap;
	}

	public void setProductNumMap(Map<String, Integer> productNumMap) {
		this.productNumMap = productNumMap;
	}

}
