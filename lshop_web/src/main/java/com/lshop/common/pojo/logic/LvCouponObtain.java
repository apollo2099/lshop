package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvCouponObtain entity. @author MyEclipse Persistence Tools
 */

public class LvCouponObtain extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String couponCode;
	private String obtain;
	private String obtainName;
	private Date obtainTime;

	// Constructors

	/** default constructor */
	public LvCouponObtain() {
	}

	/** full constructor */
	public LvCouponObtain(String code, String couponCode, String obtain,
			String obtainName, Date obtainTime) {
		this.code = code;
		this.couponCode = couponCode;
		this.obtain = obtain;
		this.obtainName = obtainName;
		this.obtainTime = obtainTime;
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

	public String getObtain() {
		return this.obtain;
	}

	public void setObtain(String obtain) {
		this.obtain = obtain;
	}

	public String getObtainName() {
		return this.obtainName;
	}

	public void setObtainName(String obtainName) {
		this.obtainName = obtainName;
	}

	public Date getObtainTime() {
		return this.obtainTime;
	}

	public void setObtainTime(Date obtainTime) {
		this.obtainTime = obtainTime;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}