package com.lshop.common.coupon.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 优惠券详情
 * @author caicl
 *
 */
public class CouponDetail implements Serializable{
	
	private static final long serialVersionUID = -1847806007947328552L;
	
	/**
	 * 优惠券码
	 */
	private String couponCode;
	/**
	 * 优惠券业务编码
	 */
	private String code;
	private String couponName;
	private String currency;
	private String faceValue;
	private Date startTime;
	private Date endTime;
	//规则
	private Short limitedType;
	private List<RuleItem> items;
	private String limitedAmount;
	
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
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
	public Short getLimitedType() {
		return limitedType;
	}
	public void setLimitedType(Short limitedType) {
		this.limitedType = limitedType;
	}
	public List<RuleItem> getItems() {
		return items;
	}
	public void setItems(List<RuleItem> items) {
		this.items = items;
	}
	public String getLimitedAmount() {
		return limitedAmount;
	}
	public void setLimitedAmount(String limitedAmount) {
		this.limitedAmount = limitedAmount;
	}
	@Override
	public String toString() {
		return "CouponDetail [couponCode=" + couponCode + ", code=" + code
				+ ", couponName=" + couponName + ", currency=" + currency
				+ ", faceValue=" + faceValue + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", limitedType=" + limitedType
				+ ", items=" + items + ", limitedAmount=" + limitedAmount + "]";
	}
	
}
