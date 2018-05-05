package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvProductLadder entity. @author MyEclipse Persistence Tools
 */

public class LvProductLadder extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1389968751032467772L;
	private Integer id;
     private Integer upInterval;
     private Integer downInterval;
     private Float price;
     private String productCode;
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
     
     //扩展属性，用于阶梯价区间判断
     private Integer oldUpInterval;
	 private Integer oldDownInterval;

    // Constructors

    /** default constructor */
    public LvProductLadder() {
    }

    
    /** full constructor */
    public LvProductLadder(Integer upInterval, Integer downInterval, Float price, String productCode, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.upInterval = upInterval;
        this.downInterval = downInterval;
        this.price = price;
        this.productCode = productCode;
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

    public Integer getUpInterval() {
        return this.upInterval;
    }
    
    public void setUpInterval(Integer upInterval) {
        this.upInterval = upInterval;
    }

    public Integer getDownInterval() {
        return this.downInterval;
    }
    
    public void setDownInterval(Integer downInterval) {
        this.downInterval = downInterval;
    }

    public Float getPrice() {
        return this.price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }

    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public Integer getOldUpInterval() {
		return oldUpInterval;
	}


	public void setOldUpInterval(Integer oldUpInterval) {
		this.oldUpInterval = oldUpInterval;
	}


	public Integer getOldDownInterval() {
		return oldDownInterval;
	}


	public void setOldDownInterval(Integer oldDownInterval) {
		this.oldDownInterval = oldDownInterval;
	}


	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}