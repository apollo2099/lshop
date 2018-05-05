package com.lshop.ws.server.popularize.order.bean;

public class PCouponDetailDto {

	/**
	 * 优惠码
	 */
	private String couponCode;

	public PCouponDetailDto() {
		super();
	}

	public PCouponDetailDto(String couponCode) {
		super();
		this.couponCode = couponCode;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

}
