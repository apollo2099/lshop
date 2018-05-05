package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvCoupon entity. @author MyEclipse Persistence Tools
 */

public class LvDealerApplication extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;
	private String applyCmp;//申请公司
	private String applyName;//联系人
	private String applyTel;//联系电话
	private String applyEmail;//申请email
	private String applyAddr;//通讯地址
	private String applyArea;//申请代理区域
	private String applyReason;//申请理由
	private String applyIntro;//申请人/公司介绍
	private String applyPlan;//营销计划
	private String appySuggest;//对banana TV公司的建议
	private String type;//方式
	private String otherText;
	private String storeId;
	private Date createTime;
	
	public LvDealerApplication(){}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getApplyCmp() {
		return applyCmp;
	}


	public void setApplyCmp(String applyCmp) {
		this.applyCmp = applyCmp;
	}


	public String getApplyName() {
		return applyName;
	}


	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}


	public String getApplyTel() {
		return applyTel;
	}


	public void setApplyTel(String applyTel) {
		this.applyTel = applyTel;
	}


	public String getApplyEmail() {
		return applyEmail;
	}


	public void setApplyEmail(String applyEmail) {
		this.applyEmail = applyEmail;
	}


	public String getApplyAddr() {
		return applyAddr;
	}


	public void setApplyAddr(String applyAddr) {
		this.applyAddr = applyAddr;
	}


	public String getApplyArea() {
		return applyArea;
	}


	public void setApplyArea(String applyArea) {
		this.applyArea = applyArea;
	}


	public String getApplyReason() {
		return applyReason;
	}


	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}


	public String getApplyIntro() {
		return applyIntro;
	}


	public void setApplyIntro(String applyIntro) {
		this.applyIntro = applyIntro;
	}


	public String getApplyPlan() {
		return applyPlan;
	}


	public void setApplyPlan(String applyPlan) {
		this.applyPlan = applyPlan;
	}


	public String getAppySuggest() {
		return appySuggest;
	}


	public void setAppySuggest(String appySuggest) {
		this.appySuggest = appySuggest;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getOtherText() {
		return otherText;
	}


	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}


	public String getStoreId() {
		return storeId;
	}


	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}


	public Date getCreateTime() {
		return createTime;
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