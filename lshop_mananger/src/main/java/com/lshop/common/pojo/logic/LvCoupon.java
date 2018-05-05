package com.lshop.common.pojo.logic;
import java.util.Date;
import com.gv.core.datastructure.Key;

/**
 * LvCoupon entity. @author MyEclipse Persistence Tools
 */

public class LvCoupon extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String couponCode;
	private String couponTypeCode;
	private Short couponStatus;
	private String grantWay;
	private Short grantType;
	private String operator;
	private String obtain;
	private java.util.Date obtainTime;
	private String obtainName;
	private String apply;
	private String applyName;
	private java.util.Date applyTime;
	private String orderId;
	private String code;
	private java.util.Date createTime;

	// Constructors
	private String obtainStartTime;
	private String obtainEndTime;
	
	/** default constructor */
	public LvCoupon() {
	}



	/** full constructor */
	public LvCoupon(String couponCode, String couponTypeCode,
			Short couponStatus, String grantWay, Short grantType,
			String operator, String obtain,String obtainName, Date obtainTime, String apply,
			String applyName,Date applyTime, String orderId, String code,
			Date createTime) {
		this.couponCode = couponCode;
		this.couponTypeCode = couponTypeCode;
		this.couponStatus = couponStatus;
		this.grantWay = grantWay;
		this.grantType = grantType;
		this.operator = operator;
		this.obtain = obtain;
		this.obtainTime = obtainTime;
		this.apply = apply;
		this.applyTime = applyTime;
		this.orderId = orderId;
		this.code = code;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCouponCode() {
		return this.couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponTypeCode() {
		return this.couponTypeCode;
	}

	public void setCouponTypeCode(String couponTypeCode) {
		this.couponTypeCode = couponTypeCode;
	}

	public Short getCouponStatus() {
		return this.couponStatus;
	}

	public void setCouponStatus(Short couponStatus) {
		this.couponStatus = couponStatus;
	}

	public String getGrantWay() {
		return this.grantWay;
	}

	public void setGrantWay(String grantWay) {
		this.grantWay = grantWay;
	}

	public Short getGrantType() {
		return this.grantType;
	}

	public void setGrantType(Short grantType) {
		this.grantType = grantType;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getObtain() {
		return this.obtain;
	}

	public void setObtain(String obtain) {
		this.obtain = obtain;
	}

	public String getApply() {
		return this.apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public java.util.Date getObtainTime() {
		return obtainTime;
	}

	public void setObtainTime(java.util.Date obtainTime) {
		this.obtainTime = obtainTime;
	}

	public java.util.Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getObtainStartTime() {
		return obtainStartTime;
	}

	public void setObtainStartTime(String obtainStartTime) {
		this.obtainStartTime = obtainStartTime;
	}

	public String getObtainEndTime() {
		return obtainEndTime;
	}

	public void setObtainEndTime(String obtainEndTime) {
		this.obtainEndTime = obtainEndTime;
	}

	public String getObtainName() {
		return obtainName;
	}

	public void setObtainName(String obtainName) {
		this.obtainName = obtainName;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}


}