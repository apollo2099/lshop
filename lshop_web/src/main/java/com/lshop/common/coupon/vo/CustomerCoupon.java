package com.lshop.common.coupon.vo;

import java.util.Date;

public class CustomerCoupon {
	/**
	 * 优惠券业务编码
	 */
	private String code;
	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 优惠金额
	 */
	private Float amount;
	/**
	 * 币种
	 */
	private String currency;
	/**
	 * 优惠券编码
	 */
	private String couponCode;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;

	public CustomerCoupon() {
		super();
	}

	public CustomerCoupon(String code, String name, Float amount, String currency, String couponCode, Date startTime, Date endTime) {
		super();
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.currency = currency;
		this.couponCode = couponCode;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
