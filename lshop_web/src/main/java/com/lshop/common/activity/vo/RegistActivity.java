package com.lshop.common.activity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 注册活动
 * @author caicl
 *
 */
public class RegistActivity implements Serializable{

	private static final long serialVersionUID = 5974884128422754770L;
	private String activityCode;
	private String activityTitle;
	private Date startTime;
	private Date endTime;
	private Integer giftType;
	private String giftCode;
	private String giftName;
	private String remark;
	/**
	 * 未发放赠品数
	 */
	private Integer restNum;
	/**
	 * 每次发放数量
	 */
	private Integer perNum;
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
	public Integer getGiftType() {
		return giftType;
	}
	public void setGiftType(Integer giftType) {
		this.giftType = giftType;
	}
	public String getGiftCode() {
		return giftCode;
	}
	public void setGiftCode(String giftCode) {
		this.giftCode = giftCode;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRestNum() {
		return restNum;
	}
	public void setRestNum(Integer restNum) {
		this.restNum = restNum;
	}
	public Integer getPerNum() {
		return perNum;
	}
	public void setPerNum(Integer perNum) {
		this.perNum = perNum;
	}
	@Override
	public String toString() {
		return "RegistActivity [activityCode=" + activityCode
				+ ", activityTitle=" + activityTitle + ", startTime="
				+ startTime + ", endTime=" + endTime + ", giftType=" + giftType
				+ ", giftCode=" + giftCode + ", giftName=" + giftName
				+ ", remark=" + remark + ", restNum=" + restNum + ", perNum="
				+ perNum + "]";
	}
}
