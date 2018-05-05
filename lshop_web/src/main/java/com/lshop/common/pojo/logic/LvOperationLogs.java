package com.lshop.common.pojo.logic;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;

/**
 * LvOperationLogs entity. @author MyEclipse Persistence Tools
 */

public class LvOperationLogs extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String operator;
	private String operationModule;
	private String operationKey;
	private String operationDetails;
	private java.util.Date createtime;

	// Constructors

	/** default constructor */
	public LvOperationLogs() {
	}

	/** full constructor */
	public LvOperationLogs(String operator, String operationModule,
			String operationKey, String operationDetails, Timestamp createtime) {
		this.operator = operator;
		this.operationModule = operationModule;
		this.operationKey = operationKey;
		this.operationDetails = operationDetails;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperationModule() {
		return this.operationModule;
	}

	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}

	public String getOperationKey() {
		return this.operationKey;
	}

	public void setOperationKey(String operationKey) {
		this.operationKey = operationKey;
	}

	public String getOperationDetails() {
		return this.operationDetails;
	}

	public void setOperationDetails(String operationDetails) {
		this.operationDetails = operationDetails;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}