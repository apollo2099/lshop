package com.lshop.common.activity.vo;

import java.util.Date;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_web] 
 * @Package:      [com.lshop.common.activity.vo.ActivityMac.java]  
 * @ClassName:    [ActivityMac]   
 * @Description:  [输入mac就送活动实体]   
 * @Author:       [liaoxj]   
 * @CreateDate:   [2015年7月14日 上午10:42:13]   
 * @UpdateUser:   [liaoxj]   
 * @UpdateDate:   [2015年7月14日 上午10:42:13]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.0]
 */
public class ActivityMac {
	/**
	 * 活动号
	 */
	private String activityCode;
	/**
	 * 活动标题
	 */
	private String activityTitle;
	/**
	 * 活动标题(EN)
	 */
	private String activityTitleEn;
	/**
	 * 活动开始时间
	 */
	private Date startTime;
	/**
	 * 活动结束时间
	 */
	private Date endTime;
	/**
	 * 活动优惠金额
	 */
	private Double amount;
	/**
	 * Mac兑换次数
	 */
	private Integer exchangeNum;
	
	public ActivityMac(){
		super();
	}
	
	public ActivityMac(String activityCode, String activityTitle,
			String activityTitleEn, Date startTime, Date endTime,
			Double amount, Integer exchangeNum) {
		super();
		this.activityCode = activityCode;
		this.activityTitle = activityTitle;
		this.activityTitleEn = activityTitleEn;
		this.startTime = startTime;
		this.endTime = endTime;
		this.amount = amount;
		this.exchangeNum = exchangeNum;
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
	public String getActivityTitleEn() {
		return activityTitleEn;
	}
	public void setActivityTitleEn(String activityTitleEn) {
		this.activityTitleEn = activityTitleEn;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getExchangeNum() {
		return exchangeNum;
	}
	public void setExchangeNum(Integer exchangeNum) {
		this.exchangeNum = exchangeNum;
	}
	
	
	
	
}
