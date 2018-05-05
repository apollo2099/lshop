package com.lshop.web.lottery.bo;

import java.io.Serializable;

/**
 * 点击抽奖按钮抽奖结果
 * @author caicl
 *
 */
public class LotteryResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1529068336389892172L;
	/**
	 * 抽奖结果:1表示中奖,2表示没有抽中,3表示用户没有抽奖机会,4表示活动异常,5表示没有奖品
	 */
	private Integer status;
	/**
	 * 奖项号
	 */
	private String prizeNo;
	private String userName;
	private String prizeName;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPrizeNo() {
		return prizeNo;
	}
	public void setPrizeNo(String prizeNo) {
		this.prizeNo = prizeNo;
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
	
}
