package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvCouponApply entity. @author MyEclipse Persistence Tools
 */

public class LvCouponApply extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String couponCode;
	private String apply;
	private String applyName;
	private Date applyTime;
	private String orderId;

	// Constructors

	/** default constructor */
	public LvCouponApply() {
	}

	/** full constructor */
	public LvCouponApply(String code, String couponCode, String apply,
			String applyName, Date applyTime, String orderId) {
		this.code = code;
		this.couponCode = couponCode;
		this.apply = apply;
		this.applyName = applyName;
		this.applyTime = applyTime;
		this.orderId = orderId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCouponCode() {
		return this.couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getApply() {
		return this.apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getApplyName() {
		return this.applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}