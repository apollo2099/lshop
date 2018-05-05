package com.lshop.web.lottery.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 抽奖活动详情
 * @author caicl
 *
 */
public class LotteryDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -503207577465824927L;

	private String lotteryCode;
	private String title;
	private Date startTime;
	private Date endTime;
	private String rule;
	private String mallFlag;
	private Date expiryDate;
	private List<LotteryItem> items;
	public String getLotteryCode() {
		return lotteryCode;
	}
	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public List<LotteryItem> getItems() {
		return items;
	}
	public void setItems(List<LotteryItem> items) {
		this.items = items;
	}
	public String getMallFlag() {
		return mallFlag;
	}
	public void setMallFlag(String mallFlag) {
		this.mallFlag = mallFlag;
	}
	@Override
	public String toString() {
		return "LotteryDetail [lotteryCode=" + lotteryCode + ", title=" + title
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", rule=" + rule + ", mallFlag=" + mallFlag + ", expiryDate="
				+ expiryDate + ", items=" + items + "]";
	}
}
