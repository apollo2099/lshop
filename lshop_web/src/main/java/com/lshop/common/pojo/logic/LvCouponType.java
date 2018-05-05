package com.lshop.common.pojo.logic;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;

/**
 * LvCouponType entity. @author MyEclipse Persistence Tools
 */

public class LvCouponType extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Short status;
	private Short relationType;
	private Float amount;
	private Float limitAmount;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private Integer total;
	private Integer noGrantNumber;
	private Integer grantNumber;
	private Integer usedNumber;
	private String disableReasons;
	private String remark;
	private String currency;
	private String code;
	private java.util.Date createTime;
	private Integer createUserId;
	private String createUserName;
	private java.util.Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;
	private int reuse;
	private int reuseNum;

	// Constructors


	/** default constructor */
	public LvCouponType() {
	}

	/** full constructor */
	public LvCouponType(String name, Short status, Short relationType,
			Float amount, Float limitAmount, Timestamp startTime,
			Timestamp endTime, Integer total,Integer noGrantNumber, Integer grantNumber,
			Integer usedNumber, String disableReasons, String remark,
			String currency, String code, Timestamp createTime,
			Integer createUserId, String createUserName, Timestamp modifyTime,
			Integer modifyUserId, String modifyUserName,int reuse) {
		this.name = name;
		this.status = status;
		this.relationType = relationType;
		this.amount = amount;
		this.limitAmount = limitAmount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.total=total;
		this.noGrantNumber = noGrantNumber;
		this.grantNumber = grantNumber;
		this.usedNumber = usedNumber;
		this.disableReasons = disableReasons;
		this.remark = remark;
		this.currency = currency;
		this.code = code;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
		this.reuse=reuse;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getRelationType() {
		return this.relationType;
	}

	public void setRelationType(Short relationType) {
		this.relationType = relationType;
	}

	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getLimitAmount() {
		return this.limitAmount;
	}

	public void setLimitAmount(Float limitAmount) {
		this.limitAmount = limitAmount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getNoGrantNumber() {
		return this.noGrantNumber;
	}

	public void setNoGrantNumber(Integer noGrantNumber) {
		this.noGrantNumber = noGrantNumber;
	}

	public Integer getGrantNumber() {
		return this.grantNumber;
	}

	public void setGrantNumber(Integer grantNumber) {
		this.grantNumber = grantNumber;
	}

	public Integer getUsedNumber() {
		return this.usedNumber;
	}

	public void setUsedNumber(Integer usedNumber) {
		this.usedNumber = usedNumber;
	}

	public String getDisableReasons() {
		return this.disableReasons;
	}

	public void setDisableReasons(String disableReasons) {
		this.disableReasons = disableReasons;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
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



	public int getReuse() {
		return reuse;
	}

	public void setReuse(int reuse) {
		this.reuse = reuse;
	}
	
	public int getReuseNum() {
		return reuseNum;
	}

	public void setReuseNum(int reuseNum) {
		this.reuseNum = reuseNum;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
	

}