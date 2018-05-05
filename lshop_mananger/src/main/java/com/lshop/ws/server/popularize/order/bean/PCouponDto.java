package com.lshop.ws.server.popularize.order.bean;

public class PCouponDto {
	/**
	 * 优惠券类型编码
	 */
	private String couponTypeCode;
	/**
	 * 优惠码
	 */
	private String couponCode;

	public PCouponDto() {
		super();
	}

	public PCouponDto(String couponTypeCode, String couponCode) {
		super();
		this.couponTypeCode = couponTypeCode;
		this.couponCode = couponCode;
	}

	public String getCouponTypeCode() {
		return couponTypeCode;
	}

	public void setCouponTypeCode(String couponTypeCode) {
		this.couponTypeCode = couponTypeCode;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

}
