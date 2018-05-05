package com.lshop.common.pojo.logic;

import java.sql.Time;
import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvBlogSubscribe entity. @author MyEclipse Persistence Tools
 */

public class LvBlogSubscribe extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 6252634501322143939L;
	private Integer id;
     private String title;
     private Integer sendCycle;
 	 private Time sendTime;
     private String adBanner;
     private String adUrl;
     private String storeId;
     private String code;
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
    public LvBlogSubscribe() {
    }

	/** minimal constructor */
    public LvBlogSubscribe(String storeId) {
        this.storeId = storeId;
    }
    
    /** full constructor */
    public LvBlogSubscribe(String title, Integer sendCycle, Time sendTime, String adBanner, String adUrl, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.title = title;
        this.sendCycle = sendCycle;
        this.sendTime = sendTime;
        this.adBanner = adBanner;
        this.adUrl = adUrl;
        this.storeId = storeId;
        this.code = code;
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

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSendCycle() {
        return this.sendCycle;
    }
    
    public void setSendCycle(Integer sendCycle) {
        this.sendCycle = sendCycle;
    }

    public Time getSendTime() {
        return this.sendTime;
    }
    
    public void setSendTime(Time sendTime) {
        this.sendTime = sendTime;
    }

    public String getAdBanner() {
        return this.adBanner;
    }
    
    public void setAdBanner(String adBanner) {
        this.adBanner = adBanner;
    }

    public String getAdUrl() {
        return this.adUrl;
    }
    
    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getStoreId() {
        return this.storeId;
    }
    
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}