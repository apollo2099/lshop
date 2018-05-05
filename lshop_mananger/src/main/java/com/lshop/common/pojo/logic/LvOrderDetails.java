package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvOrderDetails entity. @author MyEclipse Persistence Tools
 */

public class LvOrderDetails extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1219870780831055392L;
	private Integer id;
     private String orderId;
     private String productCode;
     private Float oprice;
     private String oremark;
     private Integer pnum;
     private Float postPrice;
     private String currency;
     private Integer isDelete;
	 private Integer isPackage;
     private String pcode;
     private String couponCode;
     private Float couponPrice;
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
     private String targetProductCode;

    

	/** default constructor */
    public LvOrderDetails() {
    }

    
    /** full constructor */
    public LvOrderDetails(String orderId, String productCode, Float oprice, String oremark, Integer pnum, Float postPrice, String currency, Integer isDelete, Integer isPackage, String pcode, String couponCode, Float couponPrice, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.orderId = orderId;
        this.productCode = productCode;
        this.oprice = oprice;
        this.oremark = oremark;
        this.pnum = pnum;
        this.postPrice = postPrice;
        this.currency = currency;
        this.isDelete = isDelete;
        this.isPackage = isPackage;
        this.pcode = pcode;
        this.couponCode = couponCode;
        this.couponPrice = couponPrice;
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

    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Float getOprice() {
        return this.oprice;
    }
    
    public void setOprice(Float oprice) {
        this.oprice = oprice;
    }

    public String getOremark() {
        return this.oremark;
    }
    
    public void setOremark(String oremark) {
        this.oremark = oremark;
    }

    public Integer getPnum() {
        return this.pnum;
    }
    
    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    public Float getPostPrice() {
        return this.postPrice;
    }
    
    public void setPostPrice(Float postPrice) {
        this.postPrice = postPrice;
    }

    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getIsDelete() {
        return this.isDelete;
    }
    
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsPackage() {
		return isPackage;
	}


	public void setIsPackage(Integer isPackage) {
		this.isPackage = isPackage;
	}

    public String getPcode() {
        return this.pcode;
    }
    
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCouponCode() {
        return this.couponCode;
    }
    
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Float getCouponPrice() {
        return this.couponPrice;
    }
    
    public void setCouponPrice(Float couponPrice) {
        this.couponPrice = couponPrice;
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
    
    public String getTargetProductCode() {
		return targetProductCode;
	}

	public void setTargetProductCode(String targetProductCode) {
		this.targetProductCode = targetProductCode;
	}


	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}