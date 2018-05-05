package com.lshop.common.activity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享活动
 * @author caicl
 *
 */
public class ShareActivity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7505225619346718799L;
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
	/**
	 * 每日可参加次数
	 */
	private Integer perDay;
	/**
	 * 分享的图片及文字内容
	 */
	private String shareImg;
	private String shareText;
	/**
	 * 微信分享链接
	 */
	private String shareLink;
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
	public Integer getPerDay() {
		return perDay;
	}
	public void setPerDay(Integer perDay) {
		this.perDay = perDay;
	}
	public String getShareImg() {
		return shareImg;
	}
	public void setShareImg(String shareImg) {
		this.shareImg = shareImg;
	}
	public String getShareText() {
		return shareText;
	}
	public void setShareText(String shareText) {
		this.shareText = shareText;
	}
	public String getShareLink() {
		return shareLink;
	}
	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}
	@Override
	public String toString() {
		return "ShareActivity [activityCode=" + activityCode
				+ ", activityTitle=" + activityTitle + ", startTime="
				+ startTime + ", endTime=" + endTime + ", giftType=" + giftType
				+ ", giftCode=" + giftCode + ", giftName=" + giftName
				+ ", remark=" + remark + ", restNum=" + restNum + ", perNum="
				+ perNum + ", perDay=" + perDay + ", shareImg=" + shareImg
				+ ", shareText=" + shareText + ", shareLink=" + shareLink + "]";
	}
}
