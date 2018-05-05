package com.lshop.manage.lvCoupon.vo;

import java.util.concurrent.atomic.AtomicInteger;

import com.lshop.common.pojo.logic.LvCouponType;

public class CouponVo {

	private AtomicInteger createNum;//创建优惠券数目
	private String couponTypeCode;// 优惠券编码
	private Short couponStatus;// 优惠码状态
	private String operator; //操作人
	private String activityCode;//活动编码
	private Short couponType;//优惠券类型（1线上，2线下）
	private String prizeCode;//奖品编码
	public CouponVo(String activityCode,AtomicInteger createNum, String couponTypeCode,Short couponStatus,String operator,
		   Short couponType, String prizeCode) {
		super();
		this.createNum = createNum;
		this.couponTypeCode=couponTypeCode;
		this.couponStatus=couponStatus;
		this.operator=operator;
		this.activityCode = activityCode;
		this.couponType = couponType;
		this.prizeCode = prizeCode;
	}
	public CouponVo(String activityCode,AtomicInteger createNum,  String couponTypeCode,Short couponStatus,String operator,Short couponType) {
		super();
		this.createNum = createNum;
		this.couponTypeCode=couponTypeCode;
		this.couponStatus=couponStatus;
		this.operator=operator;
		this.activityCode = activityCode;
		this.couponType = couponType;
	}
	public CouponVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AtomicInteger getCreateNum() {
		return createNum;
	}
	public void setCreateNum(AtomicInteger createNum) {
		this.createNum = createNum;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public Short getCouponType() {
		return couponType;
	}
	public void setCouponType(Short couponType) {
		this.couponType = couponType;
	}
	public String getPrizeCode() {
		return prizeCode;
	}
	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}
	public String getCouponTypeCode() {
		return couponTypeCode;
	}
	public void setCouponTypeCode(String couponTypeCode) {
		this.couponTypeCode = couponTypeCode;
	}
	public Short getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(Short couponStatus) {
		this.couponStatus = couponStatus;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	
}
