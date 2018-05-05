package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvAd entity. @author MyEclipse Persistence Tools
 */

public class LvAd extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -951741173456232912L;
	 private Integer id;
     private String adKey;
     private String adTitle;
     private String adContent;
     private String url;
     private Integer sortId;
	 private Short status;
     private String adDescription;
     private Date pickleTime;
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
    public LvAd() {
    }

    
    /** full constructor */
    public LvAd(String adKey, String adTitle, String adContent,String url ,Integer sortId,Short status,String adDescription,Date pickleTime, 
    		String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.adKey = adKey;
        this.adTitle = adTitle;
        this.adContent = adContent;
        this.url=url;
        this.sortId = sortId;
        this.status=status;
        this.adDescription=adDescription;
        this.pickleTime=pickleTime;
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
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    public String getAdKey() {
        return this.adKey;
    }
    
    public void setAdKey(String adKey) {
        this.adKey = adKey;
    }

    public String getAdTitle() {
        return this.adTitle;
    }
    
    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdContent() {
        return this.adContent;
    }
    
    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public Integer getSortId() {
        return this.sortId;
    }
    
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
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
    public Short getStatus() {
		return status;
	}


	public void setStatus(Short status) {
		this.status = status;
	}


	public String getAdDescription() {
		return adDescription;
	}


	public void setAdDescription(String adDescription) {
		this.adDescription = adDescription;
	}

	public Date getPickleTime() {
		return pickleTime;
	}

	public void setPickleTime(Date pickleTime) {
		this.pickleTime = pickleTime;
	}

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
	
}