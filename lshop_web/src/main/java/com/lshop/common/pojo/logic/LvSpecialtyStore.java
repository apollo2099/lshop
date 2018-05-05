package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvSpecialtyStore entity. @author MyEclipse Persistence Tools
 */

public class LvSpecialtyStore extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String storeTypeCode;
     private String storeName;
     private String storeUrl;
     private String storeLogo;
     private Integer orderValue;
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
     
     //扩展属性
     private String parentCode; 


    // Constructors

	/** default constructor */
    public LvSpecialtyStore() {
    }

    
    /** full constructor */
    public LvSpecialtyStore(String storeTypeCode, String storeName, String storeUrl, String storeLogo, Integer orderValue, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.storeTypeCode = storeTypeCode;
        this.storeName = storeName;
        this.storeUrl = storeUrl;
        this.storeLogo = storeLogo;
        this.orderValue = orderValue;
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

    public String getStoreTypeCode() {
        return this.storeTypeCode;
    }
    
    public void setStoreTypeCode(String storeTypeCode) {
        this.storeTypeCode = storeTypeCode;
    }

    public String getStoreName() {
        return this.storeName;
    }
    
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreUrl() {
        return this.storeUrl;
    }
    
    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getStoreLogo() {
        return this.storeLogo;
    }
    
    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public Integer getOrderValue() {
        return this.orderValue;
    }
    
    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
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
    public String getParentCode() {
		return parentCode;
	}


	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}