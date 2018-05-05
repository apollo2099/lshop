package com.lshop.common.pojo.logic;

import com.gv.core.datastructure.Key;

/**
 * LvLogistics entity. @author MyEclipse Persistence Tools
 */

public class LvLogistics extends com.gv.core.datastructure.impl.BasePo
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String key;
	private String companyName;
	private String uid;
	private String pwd;
	private String url;
	private String other;

	// Constructors

	/** default constructor */
	public LvLogistics() {
	}

	/** minimal constructor */
	public LvLogistics(String key) {
		this.key = key;
	}

	/** full constructor */
	public LvLogistics(String key, String companyName, String uid, String pwd,
			String url, String other) {
		this.key = key;
		this.companyName = companyName;
		this.uid = uid;
		this.pwd = pwd;
		this.url = url;
		this.other = other;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}