package com.lshop.common.pojo.logic;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * LvLinkUrl entity. @author MyEclipse Persistence Tools
 */

public class LvLinkUrl extends BasePo implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String dynamicurl;
	private String staticurl;
	private String hql;
	private String note;
	private String storeFlag;

	// Constructors

	/** default constructor */
	public LvLinkUrl() {
	}

	/** full constructor */
	public LvLinkUrl(String name, String dynamicurl, String staticurl,
			String hql, String note, String storeFlag) {
		this.name = name;
		this.dynamicurl = dynamicurl;
		this.staticurl = staticurl;
		this.hql = hql;
		this.note = note;
		this.storeFlag = storeFlag;
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

	public String getDynamicurl() {
		return this.dynamicurl;
	}

	public void setDynamicurl(String dynamicurl) {
		this.dynamicurl = dynamicurl;
	}

	public String getStaticurl() {
		return this.staticurl;
	}

	public void setStaticurl(String staticurl) {
		this.staticurl = staticurl;
	}

	public String getHql() {
		return this.hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStoreFlag() {
		return this.storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}