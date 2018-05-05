package com.lshop.common.pojo.logic;


import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvActivityLottery entity. @author MyEclipse Persistence Tools
 */

public class LvActivityLottery extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private String activityRule;
	private Date endTicketDate;
	private String storeId;
	private String activityUrl;
	private String code;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors

	/** default constructor */
	public LvActivityLottery() {
	}

	/** full constructor */
	public LvActivityLottery(String activityCode, String activityRule,
			Date endTicketDate,String storeId,String activityUrl,
			String code, Date createTime,Date modifyTime, Integer modifyUserId, String modifyUserName) {
		this.activityCode = activityCode;
		this.activityRule = activityRule;
		this.endTicketDate = endTicketDate;
		this.storeId=storeId;
		this.activityUrl=activityUrl;
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

	public String getActivityRule() {
		return this.activityRule;
	}

	public void setActivityRule(String activityRule) {
		this.activityRule = activityRule;
	}

	public Date getEndTicketDate() {
		return this.endTicketDate;
	}

	public void setEndTicketDate(Date endTicketDate) {
		this.endTicketDate = endTicketDate;
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

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getActivityUrl() {
		return activityUrl;
	}

	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}


	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}