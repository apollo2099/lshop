package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvExtendBalance entity. @author MyEclipse Persistence Tools
 */

public class LvExtendBalance extends com.gv.core.datastructure.impl.BasePo
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String balanceId;
	private Integer balanceNum;
	private Double balancePrice;
	private Date balanceTime;
	private Integer balanceStatus;
	private String couponCode;
	private String userEmail;
	private Integer uid;
	private Integer userType;
	private String storeId;
	private String code;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;
	private String atrr;
	private String atrr1;
	private String atrr2;
	private String atrr3;
	
	private String startTime;
	private String endTime;


	// Constructors

	/** default constructor */
	public LvExtendBalance() {
	}

	/** full constructor */
	public LvExtendBalance(String balanceId, Integer balanceNum,
			Double balancePrice, Date balanceTime, Integer balanceStatus,
			String couponCode, String userEmail, Integer uid,
			Integer userType, String storeId, String code,
			Date createTime, Date modifyTime, Integer modifyUserId,
			String modifyUserName, String atrr, String atrr1, String atrr2,
			String atrr3) {
		this.balanceId = balanceId;
		this.balanceNum = balanceNum;
		this.balancePrice = balancePrice;
		this.balanceTime = balanceTime;
		this.balanceStatus = balanceStatus;
		this.couponCode = couponCode;
		this.userEmail = userEmail;
		this.uid = uid;
		this.userType = userType;
		this.storeId = storeId;
		this.code = code;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
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

	public String getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public Integer getBalanceNum() {
		return this.balanceNum;
	}

	public void setBalanceNum(Integer balanceNum) {
		this.balanceNum = balanceNum;
	}

	public Double getBalancePrice() {
		return this.balancePrice;
	}

	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}

	public Date getBalanceTime() {
		return this.balanceTime;
	}

	public void setBalanceTime(Date balanceTime) {
		this.balanceTime = balanceTime;
	}

	public Integer getBalanceStatus() {
		return this.balanceStatus;
	}

	public void setBalanceStatus(Integer balanceStatus) {
		this.balanceStatus = balanceStatus;
	}

	public String getCouponCode() {
		return this.couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}