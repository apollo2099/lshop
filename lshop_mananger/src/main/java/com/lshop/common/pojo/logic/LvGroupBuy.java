package com.lshop.common.pojo.logic;

import java.util.Date;
import java.util.List;

import com.gv.core.datastructure.Key;


/**
 * LvGroupBuy entity. @author MyEclipse Persistence Tools
 */

public class LvGroupBuy extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 3587010801812243756L;
	private Integer id;
     private String productCode;
     private String title;
     private String gimage;
     private Float primeCost;
     private Integer discount;
     private Float presentPrice;
     private Integer groupNum;
     private Integer purchasedNum;
     private Date startTime;
     private Date endTime;
     private Integer status;
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
     //扩展属性：团购URL
     private String groupUrl;
     private List<LvProduct> productList;




	// Constructors
	/** default constructor */
    public LvGroupBuy() {
    }

    
    /** full constructor */
    public LvGroupBuy(String productCode, String title, Float primeCost, Integer discount, Float presentPrice, Integer groupNum, Integer purchasedNum, Date startTime, Date endTime, Integer status, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.productCode = productCode;
        this.title = title;
        this.primeCost = primeCost;
        this.discount = discount;
        this.presentPrice = presentPrice;
        this.groupNum = groupNum;
        this.purchasedNum = purchasedNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
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

    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrimeCost() {
        return this.primeCost;
    }
    
    public void setPrimeCost(Float primeCost) {
        this.primeCost = primeCost;
    }

    public Integer getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Float getPresentPrice() {
        return this.presentPrice;
    }
    
    public void setPresentPrice(Float presentPrice) {
        this.presentPrice = presentPrice;
    }

    public Integer getGroupNum() {
        return this.groupNum;
    }
    
    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Integer getPurchasedNum() {
        return this.purchasedNum;
    }
    
    public void setPurchasedNum(Integer purchasedNum) {
        this.purchasedNum = purchasedNum;
    }

    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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
    
    public String getGroupUrl() {
		return groupUrl;
	}

	public void setGroupUrl(String groupUrl) {
		this.groupUrl = groupUrl;
	}

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGimage() {
		return gimage;
	}

	public void setGimage(String gimage) {
		this.gimage = gimage;
	}

	public List<LvProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<LvProduct> productList) {
		this.productList = productList;
	}

}