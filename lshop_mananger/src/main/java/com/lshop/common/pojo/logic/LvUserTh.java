package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvActivity entity. @author MyEclipse Persistence Tools
 */

public class LvUserTh extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userCode;
	private String thCode;
	private Integer thType;
	private Date createTime;
	private Integer status;
	private Date updateTime;
	private String modifyUserName;
	private String mallFlag;

	public LvUserTh() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getThCode() {
		return thCode;
	}

	public void setThCode(String thCode) {
		this.thCode = thCode;
	}

	public Integer getThType() {
		return thType;
	}

	public void setThType(Integer thType) {
		this.thType = thType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public String getMallFlag() {
		return mallFlag;
	}

	public void setMallFlag(String mallFlag) {
		this.mallFlag = mallFlag;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}