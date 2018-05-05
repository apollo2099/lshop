package com.lshop.common.activity.vo;

import java.util.Date;

public class ActivityLink {
	private String activityCode;
	private String activityTitle;
	private Date startTime;
	private Date endTime;
	private Integer status;
	private Integer limitNum;
	private String givenProductCode;
	private Integer grantTotal;
	private Integer uncollectedTotal;
	private Integer strategyType;

	public ActivityLink() {
		super();
	}

	public ActivityLink(String activityCode, String activityTitle, Date startTime, Date endTime, Integer status, Integer limitNum,
			String givenProductCode, Integer grantTotal, Integer uncollectedTotal,Integer strategyType) {
		super();
		this.activityCode = activityCode;
		this.activityTitle = activityTitle;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.limitNum = limitNum;
		this.givenProductCode = givenProductCode;
		this.grantTotal = grantTotal;
		this.uncollectedTotal = uncollectedTotal;
		this.strategyType=strategyType;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getGivenProductCode() {
		return givenProductCode;
	}

	public void setGivenProductCode(String givenProductCode) {
		this.givenProductCode = givenProductCode;
	}

	public Integer getGrantTotal() {
		return grantTotal;
	}

	public void setGrantTotal(Integer grantTotal) {
		this.grantTotal = grantTotal;
	}

	public Integer getUncollectedTotal() {
		return uncollectedTotal;
	}

	public void setUncollectedTotal(Integer uncollectedTotal) {
		this.uncollectedTotal = uncollectedTotal;
	}

	public Integer getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(Integer strategyType) {
		this.strategyType = strategyType;
	}
}
