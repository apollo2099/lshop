package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvArea entity. @author MyEclipse Persistence Tools
 */

public class LvArea extends com.gv.core.datastructure.impl.BasePo implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String namecn;
	private String nametw;
	private String namekn;
	private String nameen;
	private Integer parentid;
	private String icon;
	private String code;
	private String storeId;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;
	private String atrr;
	private String atrr1;
	private String atrr2;
	private String atrr3;

	// Constructors

	/** default constructor */
	public LvArea() {
	}

	/** full constructor */
	public LvArea(String namecn, String nametw, String namekn, String nameen,
			Integer parentid, String icon, String code, String storeId,
			String copyOfCode, Date createTime, Date modifyTime,
			Integer modifyUserId, String modifyUserName, String atrr,
			String atrr1, String atrr2, String atrr3) {
		this.namecn = namecn;
		this.nametw = nametw;
		this.namekn = namekn;
		this.nameen = nameen;
		this.parentid = parentid;
		this.icon = icon;
		this.code = code;
		this.storeId = storeId;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
		this.atrr = atrr;
		this.atrr1 = atrr1;
		this.atrr2 = atrr2;
		this.atrr3 = atrr3;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNamecn() {
		return this.namecn;
	}

	public void setNamecn(String namecn) {
		this.namecn = namecn;
	}

	public String getNametw() {
		return this.nametw;
	}

	public void setNametw(String nametw) {
		this.nametw = nametw;
	}

	public String getNamekn() {
		return this.namekn;
	}

	public void setNamekn(String namekn) {
		this.namekn = namekn;
	}

	public String getNameen() {
		return this.nameen;
	}

	public void setNameen(String nameen) {
		this.nameen = nameen;
	}

	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public String getAtrr() {
		return this.atrr;
	}

	public void setAtrr(String atrr) {
		this.atrr = atrr;
	}

	public String getAtrr1() {
		return this.atrr1;
	}

	public void setAtrr1(String atrr1) {
		this.atrr1 = atrr1;
	}

	public String getAtrr2() {
		return this.atrr2;
	}

	public void setAtrr2(String atrr2) {
		this.atrr2 = atrr2;
	}

	public String getAtrr3() {
		return this.atrr3;
	}

	public void setAtrr3(String atrr3) {
		this.atrr3 = atrr3;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}