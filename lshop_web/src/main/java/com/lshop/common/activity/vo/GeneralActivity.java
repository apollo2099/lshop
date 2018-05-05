package com.lshop.common.activity.vo;

public class GeneralActivity {
	/**
	 * 活动类型 (1限时，2限量,8购买指定商品就送活动)
	 */
	private Integer type;
	/**
	 * 限时活动
	 */
	private ActivityLimitTime activityLimitTime;
	/**
	 * 限量活动
	 */
	private ActivityLimited activityLimited;
	/**
	 * 购买指定商品就送活动 
	 */
	private ActivityAppoindProduct activityAppoindProduct;
	

	public GeneralActivity() {
		super();
	}

	public GeneralActivity(Integer type, ActivityLimitTime activityLimitTime, ActivityLimited activityLimited) {
		super();
		this.type = type;
		this.activityLimitTime = activityLimitTime;
		this.activityLimited = activityLimited;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ActivityLimitTime getActivityLimitTime() {
		return activityLimitTime;
	}

	public void setActivityLimitTime(ActivityLimitTime activityLimitTime) {
		this.activityLimitTime = activityLimitTime;
	}

	public ActivityLimited getActivityLimited() {
		return activityLimited;
	}

	public void setActivityLimited(ActivityLimited activityLimited) {
		this.activityLimited = activityLimited;
	}

	public ActivityAppoindProduct getActivityAppoindProduct() {
		return activityAppoindProduct;
	}

	public void setActivityAppoindProduct(ActivityAppoindProduct activityAppoindProduct) {
		this.activityAppoindProduct = activityAppoindProduct;
	}
	

}
