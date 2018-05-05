package com.lshop.common.activity.vo;

import java.util.Date;

public class Activity {
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
	 * 活动类型 (1限时，2限量)
	 */
	private Integer type;

	public Activity() {
		super();
	}

	public Activity(String activityCode, String activityTitle, Date startTime, Date endTime, Integer type) {
		super();
		this.activityCode = activityCode;
		this.activityTitle = activityTitle;
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
