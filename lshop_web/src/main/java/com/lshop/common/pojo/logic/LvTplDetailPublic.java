package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * LvTplDetailPublic entity. @author MyEclipse Persistence Tools
 */

public class LvTplDetailPublic extends BasePo
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tplModelCode;
	private String name;
	private String content;
	private Integer modifyUserId;
	private String modifyUserName;
	private Date createTime;
	private Date modifyTime;
	private String atrr;
	private String atrr2;
	private String atrr4;
	private String atrr3;
	private String pagePath;
	private String code;

	// Constructors

	/** default constructor */
	public LvTplDetailPublic() {
	}

	/** full constructor */
	public LvTplDetailPublic(String tplModelCode, String name, String content,
			Integer modifyUserId, String modifyUserName, Date createTime,
			Date modifyTime, String atrr, String atrr2, String atrr4,
			String atrr3, String pagePath) {
		this.tplModelCode = tplModelCode;
		this.name = name;
		this.content = content;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.atrr = atrr;
		this.atrr2 = atrr2;
		this.atrr4 = atrr4;
		this.atrr3 = atrr3;
		this.pagePath = pagePath;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTplModelCode() {
		return this.tplModelCode;
	}

	public void setTplModelCode(String tplModelCode) {
		this.tplModelCode = tplModelCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getPagePath() {
		return this.pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}