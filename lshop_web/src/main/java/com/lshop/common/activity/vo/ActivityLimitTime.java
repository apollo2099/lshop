package com.lshop.common.activity.vo;

import java.util.Date;

/**
 * 
 * @Description:限时活动
 * @author CYJ
 * @date 2014-6-18 下午3:38:30
 */
public class ActivityLimitTime {
	/**
	 * 活动号
	 */
	private String activityCode;
	/**
	 * 活动标题
	 */
	private String activityTitle;
	/**
	 * 活动开始时间
	 */
	private Date startTime;
	/**
	 * 活动结束时间
	 */
	private Date endTime;
	/**
	 * 商品编码
	 */
	private String productCode;
	/**
	 * 活动价
	 */
	private Float activityPrice;
	/**
	 * 店铺编号
	 */
	private String storeId;

	public ActivityLimitTime() {
		super();
	}

	public ActivityLimitTime(String activityCode, String activityTitle, Date startTime, Date endTime, String productCode, Float activityPrice,
			String storeId) {
		super();
		this.activityCode = activityCode;
		this.activityTitle = activityTitle;
		this.startTime = startTime;
		this.endTime = endTime;
		this.productCode = productCode;
		this.activityPrice = activityPrice;
		this.storeId = storeId;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
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

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
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

}
