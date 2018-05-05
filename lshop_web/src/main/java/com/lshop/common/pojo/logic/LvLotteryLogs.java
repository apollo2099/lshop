package com.lshop.common.pojo.logic;



import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvLotteryLogs entity. @author MyEclipse Persistence Tools
 */

public class LvLotteryLogs extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private String activityName;
	private String prizeName;
	private String prizeCode;
	private String userName;
	private String userCode;
	private Date createTime;
	private Short isSystemFlag;
	private Integer prizeNum;
	private String accountPrizeCode;

	// Constructors

	/** default constructor */
	public LvLotteryLogs() {
	}

	/** full constructor */
	public LvLotteryLogs(String activityCode, String activityName,
			String prizeName, String prizeCode, String userName,
			String userCode, Date createTime, Short isSystemFlag,Integer prizeNum,String accountPrizeCode) {
		this.activityCode = activityCode;
		this.activityName = activityName;
		this.prizeName = prizeName;
		this.prizeCode = prizeCode;
		this.userName = userName;
		this.userCode = userCode;
		this.createTime = createTime;
		this.isSystemFlag = isSystemFlag;
		this.prizeNum=prizeNum;
		this.accountPrizeCode=accountPrizeCode;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityCode() {
		return this.activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getPrizeName() {
		return this.prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeCode() {
		return this.prizeCode;
	}

	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getIsSystemFlag() {
		return this.isSystemFlag;
	}

	public void setIsSystemFlag(Short isSystemFlag) {
		this.isSystemFlag = isSystemFlag;
	}

	public Integer getPrizeNum() {
		return prizeNum;
	}

	public void setPrizeNum(Integer prizeNum) {
		this.prizeNum = prizeNum;
	}

	public String getAccountPrizeCode() {
		return accountPrizeCode;
	}

	public void setAccountPrizeCode(String accountPrizeCode) {
		this.accountPrizeCode = accountPrizeCode;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}