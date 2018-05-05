package com.lshop.common.pojo.logic;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;

/**
 * LvActivityLogs entity. @author MyEclipse Persistence Tools
 */

public class LvActivityLogs extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private String orderId;
	private String productInfo;
	private String actors;
	private String actorsIp;
	private java.util.Date actorsTime;

	// Constructors

	/** default constructor */
	public LvActivityLogs() {
	}

	/** full constructor */
	public LvActivityLogs(String activityCode, String orderId,
			String productInfo, String actors, String actorsIp,
			Timestamp actorsTime) {
		this.activityCode = activityCode;
		this.orderId = orderId;
		this.productInfo = productInfo;
		this.actors = actors;
		this.actorsIp = actorsIp;
		this.actorsTime = actorsTime;
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

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductInfo() {
		return this.productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getActors() {
		return this.actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getActorsIp() {
		return this.actorsIp;
	}

	public void setActorsIp(String actorsIp) {
		this.actorsIp = actorsIp;
	}

	public java.util.Date getActorsTime() {
		return this.actorsTime;
	}

	public void setActorsTime(java.util.Date actorsTime) {
		this.actorsTime = actorsTime;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}