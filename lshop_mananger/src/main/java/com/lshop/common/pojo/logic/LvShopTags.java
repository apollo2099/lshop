package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvShopTags entity. @author MyEclipse Persistence Tools
 */

public class LvShopTags extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String tagName;
     private String tagUrl;
     private String tagFontColor;
     private String tagFontStyle;
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


    // Constructors

    /** default constructor */
    public LvShopTags() {
    }

    
    /** full constructor */
    public LvShopTags(String tagName, String tagUrl, String tagFontColor, String tagFontStyle, Integer orderValue, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.tagName = tagName;
        this.tagUrl = tagUrl;
        this.tagFontColor = tagFontColor;
        this.tagFontStyle = tagFontStyle;
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

    public String getTagName() {
        return this.tagName;
    }
    
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagUrl() {
        return this.tagUrl;
    }
    
    public void setTagUrl(String tagUrl) {
        this.tagUrl = tagUrl;
    }

    public String getTagFontColor() {
        return this.tagFontColor;
    }
    
    public void setTagFontColor(String tagFontColor) {
        this.tagFontColor = tagFontColor;
    }

    public String getTagFontStyle() {
        return this.tagFontStyle;
    }
    
    public void setTagFontStyle(String tagFontStyle) {
        this.tagFontStyle = tagFontStyle;
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
	
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}