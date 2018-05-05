package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * LvContent entity. @author MyEclipse Persistence Tools
 */

public class LvContent extends BasePo implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String templatePath;
	private String pageName;
	private String pageTitle;
	private Short isHasContent;
	private String pageContent;
	private String pageKeywords;
	private String pageDescription;
	private String pagePath;
	private Integer modifyUserId;
	private String modifyUserName;
	private Date createTime;
	private Date modifyTime;
	private Short isdel;
	private String storeFlag;
	private String atrr;
	private String atrr2;
	private String atrr4;
	private String atrr3;
	private String code;

	// Constructors

	/** default constructor */
	public LvContent() {
	}

	/** full constructor */
	public LvContent(String templatePath, String pageName, String pageTitle,
			Short isHasContent, String pageContent, String pageKeywords,
			String pageDescription, String pagePath, Integer modifyUserId,
			String modifyUserName, Date createTime, Date modifyTime,
			Short isdel, String storeFlag, String atrr, String atrr2,
			String atrr4, String atrr3, String code) {
		this.templatePath = templatePath;
		this.pageName = pageName;
		this.pageTitle = pageTitle;
		this.isHasContent = isHasContent;
		this.pageContent = pageContent;
		this.pageKeywords = pageKeywords;
		this.pageDescription = pageDescription;
		this.pagePath = pagePath;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.isdel = isdel;
		this.storeFlag = storeFlag;
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

	public String getTemplatePath() {
		return this.templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public Short getIsHasContent() {
		return this.isHasContent;
	}

	public void setIsHasContent(Short isHasContent) {
		this.isHasContent = isHasContent;
	}

	public String getPageContent() {
		return this.pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getPageKeywords() {
		return this.pageKeywords;
	}

	public void setPageKeywords(String pageKeywords) {
		this.pageKeywords = pageKeywords;
	}

	public String getPageDescription() {
		return this.pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public String getPagePath() {
		return this.pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
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

	public Short getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Short isdel) {
		this.isdel = isdel;
	}

	public String getStoreFlag() {
		return this.storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
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