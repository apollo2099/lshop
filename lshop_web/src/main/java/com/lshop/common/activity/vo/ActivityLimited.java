package com.lshop.common.activity.vo;

/**
 * 
 * @Description:限量活动
 * @author CYJ
 * @date 2014-6-18 下午3:46:13
 */
public class ActivityLimited {
	/**
	 * 活动号
	 */
	private String activityCode;
	/**
	 * 活动标题
	 */
	private String activityTitle;
	/**
	 * 商品编码
	 */
	private String productCode;
	/**
	 * 活动价
	 */
	private Float activityPrice;
	/**
	 * 总量
	 */
	private Integer limitTotal;
	/**
	 * 店铺编号
	 */
	private String storeId;

	public ActivityLimited() {
		super();
	}

	public ActivityLimited(String activityCode, String activityTitle, String productCode, Float activityPrice, Integer limitTotal, String storeId) {
		super();
		this.activityCode = activityCode;
		this.activityTitle = activityTitle;
		this.productCode = productCode;
		this.activityPrice = activityPrice;
		this.limitTotal = limitTotal;
		this.storeId = storeId;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Float getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(Float activityPrice) {
		this.activityPrice = activityPrice;
	}

	public Integer getLimitTotal() {
		return limitTotal;
	}

	public void setLimitTotal(Integer limitTotal) {
		this.limitTotal = limitTotal;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}
