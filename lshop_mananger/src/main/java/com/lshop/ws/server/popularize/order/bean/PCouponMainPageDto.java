package com.lshop.ws.server.popularize.order.bean;

import java.util.ArrayList;
import java.util.List;

public class PCouponMainPageDto {
	/**
	 * 优惠券类型编码
	 */
	private String code;
	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 优惠券金额
	 */
	private double amount;
	/**
	 * 指定金额
	 */
	private double limitAmount;
	/**
	 * 有效起始时间
	 */
	private String startTime;
	/**
	 * 有效结束时间
	 */
	private String endTime;
	/**
	 * 描述信息
	 */
	private String remark;
	/**
	 * 优惠券名称
	 */
	private String currency;
	/**
	 * 重复使用
	 */
	private int reuse;

	private List<PCouponProductDto> couponProducts = new ArrayList<PCouponProductDto>();

	public PCouponMainPageDto() {
		super();
	}

	public PCouponMainPageDto(String code, String name, double amount, double limitAmount, String startTime, String endTime, String remark, String currency, int reuse,
			List<PCouponProductDto> couponProducts) {
		super();
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.limitAmount = limitAmount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.remark = remark;
		this.currency = currency;
		this.reuse = reuse;
		this.couponProducts = couponProducts;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(double limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<PCouponProductDto> getCouponProducts() {
		return couponProducts;
	}

	public void setCouponProducts(List<PCouponProductDto> couponProducts) {
		this.couponProducts = couponProducts;
	}

	public int getReuse() {
		return reuse;
	}

	public void setReuse(int reuse) {
		this.reuse = reuse;
	}

}
