package com.lshop.common.coupon.vo;

public class CouponView {
	/**
	 * 活动标题
	 */
	private String activityTitle;
	/**
	 * 面值
	 */
	private String faceValue;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 品类商品
	 */
	private String typeProduct;
	/**
	 * 限额
	 */
	private String limitAmount;

	/**
	 * 有效期
	 */
	private String validDate;
	/**
	 * 优惠码
	 */
	private String couponCode;
	/**
	 * 重复使用名称
	 */
	private String reuseName;
	/**
	 * 重复使用
	 */
	private Integer reuse;

	public CouponView() {
		super();
	}

	public CouponView(String activityTitle, String faceValue, String type, String typeProduct, String limitAmount, String validDate, String couponCode, String reuseName,
			Integer reuse) {
		super();
		this.activityTitle = activityTitle;
		this.faceValue = faceValue;
		this.type = type;
		this.typeProduct = typeProduct;
		this.limitAmount = limitAmount;
		this.validDate = validDate;
		this.couponCode = couponCode;
		this.reuseName = reuseName;
		this.reuse = reuse;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}

	public String getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}

	public String getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getReuseName() {
		return reuseName;
	}

	public void setReuseName(String reuseName) {
		this.reuseName = reuseName;
	}

	public Integer getReuse() {
		return reuse;
	}

	public void setReuse(Integer reuse) {
		this.reuse = reuse;
	}

}
