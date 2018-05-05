package com.lshop.common.pojo.logic;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;

/**
 * LvActivityLimitTime entity. @author MyEclipse Persistence Tools
 */

public class LvActivityLimitTime extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private String productCode;
	private Float activityPrice;
	private String storeId;
	private String code;
	private java.util.Date createTime;
	private java.util.Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors
    private String productName;
    private String oldProductCode;



	/** default constructor */
	public LvActivityLimitTime() {
	}

	/** full constructor */
	public LvActivityLimitTime(String activityCode, String productCode,
			Float activityPrice, String storeId, String code,
			Timestamp createTime, Timestamp modifyTime, Integer modifyUserId,
			String modifyUserName) {
		this.activityCode = activityCode;
		this.productCode = productCode;
		this.activityPrice = activityPrice;
		this.storeId = storeId;
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

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Float getActivityPrice() {
		return this.activityPrice;
	}

	public void setActivityPrice(Float activityPrice) {
		this.activityPrice = activityPrice;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOldProductCode() {
		return oldProductCode;
	}

	public void setOldProductCode(String oldProductCode) {
		this.oldProductCode = oldProductCode;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}