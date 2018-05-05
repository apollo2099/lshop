package com.lshop.common.coupon.vo;

import java.util.Date;

import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvCouponType;

public class MyCouponViewVo {

	// Fields
	private String couponTypeCode;
	private Date applyTime;
	private String orderId;
	private LvCouponType lvCouponType; // 所属的优惠券类型
	private LvActivity grantActivity;
	private String limitProducts; // 限品类
	private String grantWay;

	public String getCouponTypeCode() {
		return couponTypeCode;
	}

	public void setCouponTypeCode(String couponTypeCode) {
		this.couponTypeCode = couponTypeCode;
	}

	public String getLimitProducts() {
		return limitProducts;
	}

	public void setLimitProducts(String limitProducts) {
		this.limitProducts = limitProducts;
	}

	public String getGrantWay() {
		return grantWay;
	}

	public void setGrantWay(String grantWay) {
		this.grantWay = grantWay;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LvCouponType getLvCouponType() {
		return lvCouponType;
	}

	public void setLvCouponType(LvCouponType lvCouponType) {
		this.lvCouponType = lvCouponType;
	}

	public LvActivity getGrantActivity() {
		return grantActivity;
	}

	public void setGrantActivity(LvActivity grantActivity) {
		this.grantActivity = grantActivity;
	}

}