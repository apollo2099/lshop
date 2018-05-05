package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvAlipayoutDictionary entity. @author MyEclipse Persistence Tools
 */

public class LvAlipayoutDictionary extends com.gv.core.datastructure.impl.BasePo
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer int_;
	private String errorKey;
	private String errorMsg;
	private String storeFlag;
	private Date createTime;
	private Date modifyTime;

	// Constructors

	/** default constructor */
	public LvAlipayoutDictionary() {
	}


	// Property accessors

	public Integer getInt_() {
		return this.int_;
	}

	public void setInt_(Integer int_) {
		this.int_ = int_;
	}

	public String getErrorKey() {
		return this.errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}


	public String getStoreFlag() {
		return this.storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
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


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}