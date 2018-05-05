package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvSwitchOrderLogs entity. @author MyEclipse Persistence Tools
 */

public class LvSwitchOrderLogs extends com.gv.core.datastructure.impl.BasePo
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderId;
	private String oldStoreCode;
	private String targetStoreCode;
	private String code;
	private Date createTime;
	private Integer createUserId;
	private String createUserName;
	private String atrr;
	private String atrr1;
	private String atrr2;
	private String atrr3;

	// Constructors

	/** default constructor */
	public LvSwitchOrderLogs() {
	}

	/** full constructor */
	public LvSwitchOrderLogs(String orderId, String oldStoreCode,
			String targetStoreCode, String code, Date createTime,
			Integer createUserId, String createUserName, String atrr,
			String atrr1, String atrr2, String atrr3) {
		this.orderId = orderId;
		this.oldStoreCode = oldStoreCode;
		this.targetStoreCode = targetStoreCode;
		this.code = code;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.atrr = atrr;
		this.atrr1 = atrr1;
		this.atrr2 = atrr2;
		this.atrr3 = atrr3;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOldStoreCode() {
		return this.oldStoreCode;
	}

	public void setOldStoreCode(String oldStoreCode) {
		this.oldStoreCode = oldStoreCode;
	}

	public String getTargetStoreCode() {
		return this.targetStoreCode;
	}

	public void setTargetStoreCode(String targetStoreCode) {
		this.targetStoreCode = targetStoreCode;
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

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getAtrr() {
		return this.atrr;
	}

	public void setAtrr(String atrr) {
		this.atrr = atrr;
	}

	public String getAtrr1() {
		return this.atrr1;
	}

	public void setAtrr1(String atrr1) {
		this.atrr1 = atrr1;
	}

	public String getAtrr2() {
		return this.atrr2;
	}

	public void setAtrr2(String atrr2) {
		this.atrr2 = atrr2;
	}

	public String getAtrr3() {
		return this.atrr3;
	}

	public void setAtrr3(String atrr3) {
		this.atrr3 = atrr3;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}