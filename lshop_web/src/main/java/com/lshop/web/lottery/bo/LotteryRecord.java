package com.lshop.web.lottery.bo;

/**
 * 中奖榜记录
 * @author caicl
 *
 */
public class LotteryRecord {
	private String userName;
	private String prizeName;
	
	public LotteryRecord() {
		super();
	}
	public LotteryRecord(String userName, String prizeName) {
		super();
		this.userName = userName;
		this.prizeName = prizeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	@Override
	public String toString() {
		return "LotteryRecord [userName=" + userName
				+ ", prizeName=" + prizeName + "]";
	}
	
}
