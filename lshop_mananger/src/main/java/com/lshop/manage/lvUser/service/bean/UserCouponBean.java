package com.lshop.manage.lvUser.service.bean;

public class UserCouponBean {
private String couponCode;//优惠券码
private Integer count;//优惠使用次数
private String couponName;//优惠券名称
private String couponType;//优惠券类型
public String getCouponCode() {
	return couponCode;
}
public void setCouponCode(String couponCode) {
	this.couponCode = couponCode;
}
public Integer getCount() {
	return count;
}
public void setCount(Integer count) {
	this.count = count;
}
public String getCouponName() {
	return couponName;
}
public void setCouponName(String couponName) {
	this.couponName = couponName;
}
public String getCouponType() {
	return couponType;
}
public void setCouponType(String couponType) {
	this.couponType = couponType;
}

}
