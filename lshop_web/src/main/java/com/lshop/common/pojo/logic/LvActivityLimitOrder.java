package com.lshop.common.pojo.logic;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;

/**
 * LvActivityLimitOrder entity. @author MyEclipse Persistence Tools
 */

public class LvActivityLimitOrder extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String activityCode;
	private Float limitTotalPrice;
	private String storeId;
	private String givenTypeName;
	private String givenProductCode;
	private Integer grantTotal;
	private Integer occupiedTotal;
	private Integer uncollectedTotal;
	private Short givenTypeNum;
	private Integer lotteryTotal;
	private String code;
	private java.util.Date createTime;
	private java.util.Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors
	private Integer addTotal;

	/** default constructor */
	public LvActivityLimitOrder() {
	}

	/** full constructor */
	public LvActivityLimitOrder(String activityCode, Float limitTotalPrice,
			String storeId, String givenTypeName, String givenProductCode,
			Integer grantTotal, Integer occupiedTotal,
			Integer uncollectedTotal, Short givenTypeNum, String code,
			Timestamp createTime, Timestamp modifyTime, Integer modifyUserId,
			String modifyUserName) {
		this.activityCode = activityCode;
		this.limitTotalPrice = limitTotalPrice;
		this.storeId = storeId;
		this.givenTypeName = givenTypeName;
		this.givenProductCode = givenProductCode;
		this.grantTotal = grantTotal;
		this.occupiedTotal = occupiedTotal;
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

	public Float getLimitTotalPrice() {
		return this.limitTotalPrice;
	}

	public void setLimitTotalPrice(Float limitTotalPrice) {
		this.limitTotalPrice = limitTotalPrice;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public Integer getOccupiedTotal() {
		return this.occupiedTotal;
	}

	public void setOccupiedTotal(Integer occupiedTotal) {
		this.occupiedTotal = occupiedTotal;
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

	public Integer getAddTotal() {
		return addTotal;
	}

	public void setAddTotal(Integer addTotal) {
		this.addTotal = addTotal;
	}
	
	public Integer getLotteryTotal() {
		return lotteryTotal;
	}

	public void setLotteryTotal(Integer lotteryTotal) {
		this.lotteryTotal = lotteryTotal;
	}
	
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}