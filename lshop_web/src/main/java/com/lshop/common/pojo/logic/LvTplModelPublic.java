package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * LvTplModelPublic entity. @author MyEclipse Persistence Tools
 */

public class LvTplModelPublic extends BasePo
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String modelName;
	private Integer modifyUserId;
	private String modifyUserName;
	private Date createTime;
	private Date modifyTime;
	private String atrr;
	private String atrr2;
	private String atrr4;
	private String atrr3;
	private String code;

	// Constructors

	/** default constructor */
	public LvTplModelPublic() {
	}

	/** full constructor */
	public LvTplModelPublic(String modelName, Integer modifyUserId,
			String modifyUserName, Date createTime, Date modifyTime,
			String atrr, String atrr2, String atrr4, String atrr3, String code) {
		this.modelName = modelName;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.atrr = atrr;
		this.atrr2 = atrr2;
		this.atrr4 = atrr4;
		this.atrr3 = atrr3;
		this.code = code;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public String getAtrr() {
		return this.atrr;
	}

	public void setAtrr(String atrr) {
		this.atrr = atrr;
	}

	public String getAtrr2() {
		return this.atrr2;
	}

	public void setAtrr2(String atrr2) {
		this.atrr2 = atrr2;
	}

	public String getAtrr4() {
		return this.atrr4;
	}

	public void setAtrr4(String atrr4) {
		this.atrr4 = atrr4;
	}

	public String getAtrr3() {
		return this.atrr3;
	}

	public void setAtrr3(String atrr3) {
		this.atrr3 = atrr3;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}