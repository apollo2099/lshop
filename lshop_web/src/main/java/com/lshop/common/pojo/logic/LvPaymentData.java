package com.lshop.common.pojo.logic;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * LvPaymentData entity. @author MyEclipse Persistence Tools
 */

public class LvPaymentData extends BasePo implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String payName;
	private Short payValue;

	// Constructors

	/** default constructor */
	public LvPaymentData() {
	}

	/** full constructor */
	public LvPaymentData(String payName, Short payValue) {
		this.payName = payName;
		this.payValue = payValue;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPayName() {
		return this.payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public Short getPayValue() {
		return this.payValue;
	}

	public void setPayValue(Short payValue) {
		this.payValue = payValue;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}