package com.lshop.common.pojo.logic;


import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvAccountLottery entity. @author MyEclipse Persistence Tools
 */

public class LvAccountLottery extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private String userCode;
	private Integer lotteryCount;
	private String code;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors

	/** default constructor */
	public LvAccountLottery() {
	}

	/** full constructor */
	public LvAccountLottery(String activityCode, String userCode,
			Integer lotteryCount, String code, Date createTime,
			Date modifyTime, Integer modifyUserId, String modifyUserName) {
		this.activityCode = activityCode;
		this.userCode = userCode;
		this.lotteryCount = lotteryCount;
		this.code = code;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
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

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getLotteryCount() {
		return this.lotteryCount;
	}

	public void setLotteryCount(Integer lotteryCount) {
		this.lotteryCount = lotteryCount;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return this.modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}