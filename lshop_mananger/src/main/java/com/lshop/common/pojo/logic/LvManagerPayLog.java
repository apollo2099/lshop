package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvManagerPayLog entity. @author MyEclipse Persistence Tools
 */

public class LvManagerPayLog extends com.gv.core.datastructure.impl.BasePo
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String oid;
	private String uname;
	private String remark;
	private Date createTime;

	// Constructors

	/** default constructor */
	public LvManagerPayLog() {
	}

	/** full constructor */
	public LvManagerPayLog(String oid, String uname, String remark,
			Date createTime) {
		this.oid = oid;
		this.uname = uname;
		this.remark = remark;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}