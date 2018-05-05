package com.lshop.common.pojo.logic;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;

/**
 * LvOrderCoupon entity. @author MyEclipse Persistence Tools
 */

public class LvOrderCoupon extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderId;
	private String couponTypeCode;
	private String couponName;
	private Float couponPrice;
	private String couponCode;
	private String code;
	private java.util.Date createTime;
	private java.util.Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors

	/** default constructor */
	public LvOrderCoupon() {
	}

	/** full constructor */
	public LvOrderCoupon(String orderId, String couponTypeCode,
			String couponName, Float couponPrice, String couponCode,
			String code, Timestamp createTime, Timestamp modifyTime,
			Integer modifyUserId, String modifyUserName) {
		this.orderId = orderId;
		this.couponTypeCode = couponTypeCode;
		this.couponName = couponName;
		this.couponPrice = couponPrice;
		this.couponCode = couponCode;
		this.code = code;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCouponTypeCode() {
		return this.couponTypeCode;
	}

	public void setCouponTypeCode(String couponTypeCode) {
		this.couponTypeCode = couponTypeCode;
	}

	public String getCouponName() {
		return this.couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Float getCouponPrice() {
		return this.couponPrice;
	}

	public void setCouponPrice(Float couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getCouponCode() {
		return this.couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return this.modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}