package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvEmailTpl entity. @author MyEclipse Persistence Tools
 */

public class LvEmailTpl extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1618773727394171848L;
	private Integer id;
     private String zh;
     private String en;
     private String tplKey;
     private String zhTitle;
     private String enTitle;
     private String tplDescribe;
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
     private String oldTplKey;

    // Constructors

	/** default constructor */
    public LvEmailTpl() {
    }

    
    /** full constructor */
    public LvEmailTpl(String zh, String en, String tplKey, String zhTitle, String enTitle, String tplDescribe, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.zh = zh;
        this.en = en;
        this.tplKey = tplKey;
        this.zhTitle = zhTitle;
        this.enTitle = enTitle;
        this.tplDescribe = tplDescribe;
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

    public String getZh() {
        return this.zh;
    }
    
    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getEn() {
        return this.en;
    }
    
    public void setEn(String en) {
        this.en = en;
    }

    public String getTplKey() {
        return this.tplKey;
    }
    
    public void setTplKey(String tplKey) {
        this.tplKey = tplKey;
    }

    public String getZhTitle() {
        return this.zhTitle;
    }
    
    public void setZhTitle(String zhTitle) {
        this.zhTitle = zhTitle;
    }

    public String getEnTitle() {
        return this.enTitle;
    }
    
    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public String getTplDescribe() {
        return this.tplDescribe;
    }
    
    public void setTplDescribe(String tplDescribe) {
        this.tplDescribe = tplDescribe;
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
	
	public String getOldTplKey() {
		return oldTplKey;
	}

	public void setOldTplKey(String oldTplKey) {
		this.oldTplKey = oldTplKey;
	}

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}