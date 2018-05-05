package com.lshop.common.pojo.logic;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;

/**
 * LvOrderActivity entity. @author MyEclipse Persistence Tools
 */

public class LvOrderActivity extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private Integer activityType;
	private String orderId;
	private String code;
	private java.util.Date createTime;
	private java.util.Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors

	/** default constructor */
	public LvOrderActivity() {
	}

	/** full constructor */
	public LvOrderActivity(String activityCode,Integer activityType, String orderId, String code,
			Timestamp createTime, Timestamp modifyTime, Integer modifyUserId,
			String modifyUserName) {
		this.activityCode = activityCode;
		this.activityType=activityType;
		this.orderId = orderId;
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

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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



	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}