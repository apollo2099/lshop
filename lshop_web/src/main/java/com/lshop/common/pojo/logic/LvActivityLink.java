package com.lshop.common.pojo.logic;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;

/**
 * LvActivityLink entity. @author MyEclipse Persistence Tools
 */

public class LvActivityLink extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private Integer limitNum;
	private String activityHtml;
	private String givenTypeName;
	private String givenProductCode;
	private Integer grantTotal;
	private Integer uncollectedTotal;
	private Short givenTypeNum;
	private Short strategyType;
	private String code;
	private java.util.Date createTime;
	private java.util.Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors

	/** default constructor */
	public LvActivityLink() {
	}

	/** full constructor */
	public LvActivityLink(String activityCode, Integer limitNum,
			String activityHtml, String givenTypeName, String givenProductCode,
			Integer grantTotal, Integer uncollectedTotal, Short givenTypeNum,
			String code, Timestamp createTime, Timestamp modifyTime,
			Integer modifyUserId, String modifyUserName) {
		this.activityCode = activityCode;
		this.limitNum = limitNum;
		this.activityHtml = activityHtml;
		this.givenTypeName = givenTypeName;
		this.givenProductCode = givenProductCode;
		this.grantTotal = grantTotal;
		this.uncollectedTotal = uncollectedTotal;
		this.givenTypeNum = givenTypeNum;
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

	public Integer getLimitNum() {
		return this.limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getActivityHtml() {
		return this.activityHtml;
	}

	public void setActivityHtml(String activityHtml) {
		this.activityHtml = activityHtml;
	}

	public String getGivenTypeName() {
		return this.givenTypeName;
	}

	public void setGivenTypeName(String givenTypeName) {
		this.givenTypeName = givenTypeName;
	}

	public String getGivenProductCode() {
		return this.givenProductCode;
	}

	public void setGivenProductCode(String givenProductCode) {
		this.givenProductCode = givenProductCode;
	}

	public Integer getGrantTotal() {
		return this.grantTotal;
	}

	public void setGrantTotal(Integer grantTotal) {
		this.grantTotal = grantTotal;
	}

	public Integer getUncollectedTotal() {
		return this.uncollectedTotal;
	}

	public void setUncollectedTotal(Integer uncollectedTotal) {
		this.uncollectedTotal = uncollectedTotal;
	}

	public Short getGivenTypeNum() {
		return this.givenTypeNum;
	}

	public void setGivenTypeNum(Short givenTypeNum) {
		this.givenTypeNum = givenTypeNum;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(java.util.Date modifyTime) {
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

	
	public Short getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(Short strategyType) {
		this.strategyType = strategyType;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}