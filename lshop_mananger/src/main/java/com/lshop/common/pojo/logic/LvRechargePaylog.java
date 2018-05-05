package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * TRechargePaylog entity. @author MyEclipse Persistence Tools
 */

public class LvRechargePaylog extends BasePo implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String RNum;
	private Short paymethod;
	private Date paydate;
	private String checkno;
	private Float amount;
	private Short status;
	private String bankorderid;
	private String remark;
	private String currency;

	// Constructors

	/** default constructor */
	public LvRechargePaylog() {
	}

	/** full constructor */
	public LvRechargePaylog(String RNum, Short paymethod, Date paydate,
			String checkno, Float amount, Short status, String bankorderid,
			String remark, String currency) {
		this.RNum = RNum;
		this.paymethod = paymethod;
		this.paydate = paydate;
		this.checkno = checkno;
		this.amount = amount;
		this.status = status;
		this.bankorderid = bankorderid;
		this.remark = remark;
		this.currency = currency;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRNum() {
		return this.RNum;
	}

	public void setRNum(String RNum) {
		this.RNum = RNum;
	}

	public Short getPaymethod() {
		return this.paymethod;
	}

	public void setPaymethod(Short paymethod) {
		this.paymethod = paymethod;
	}

	public Date getPaydate() {
		return this.paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public String getCheckno() {
		return this.checkno;
	}

	public void setCheckno(String checkno) {
		this.checkno = checkno;
	}

	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getBankorderid() {
		return this.bankorderid;
	}

	public void setBankorderid(String bankorderid) {
		this.bankorderid = bankorderid;
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

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}