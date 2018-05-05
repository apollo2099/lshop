package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvShopProduct entity. @author MyEclipse Persistence Tools
 */

public class LvShopProduct extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String productName;
     private String productCode;
     private String url;
     private String subjectType;
     private Integer orderValue;
     private String pimage;
	 private String pimageAd;
     private String storeId;
	 private String storeFlag;
	 private Short isGift;
     private String code;
     private Date createTime;
     private Date modifyTime;
     private Integer modifyUserId;
     private String modifyUserName;
     private Float productPrice;
     private String atrr;
     private String atrr1;
     private String atrr2;
     private String atrr3;
     
     private Float orignalPrice;
     //
     private Integer exhibitType;


    // Constructors




	/** default constructor */
    public LvShopProduct() {
    }

    
    /** full constructor */
    public LvShopProduct(String productName, String productCode, String url, String subjectType, Integer orderValue, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.productName = productName;
        this.productCode = productCode;
        this.url = url;
        this.subjectType = subjectType;
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

    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubjectType() {
        return this.subjectType;
    }
    
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
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
    public String getPimage() {
		return pimage;
	}


	public void setPimage(String pimage) {
		this.pimage = pimage;
	}


	public String getPimageAd() {
		return pimageAd;
	}


	public void setPimageAd(String pimageAd) {
		this.pimageAd = pimageAd;
	}
	
    public String getStoreFlag() {
		return storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}


	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}


	public Float getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getExhibitType() {
		return exhibitType;
	}

	public void setExhibitType(Integer exhibitType) {
		this.exhibitType = exhibitType;
	}


	public Float getOrignalPrice() {
		return orignalPrice;
	}


	public void setOrignalPrice(Float orignalPrice) {
		this.orignalPrice = orignalPrice;
	}


	public Short getIsGift() {
		return isGift;
	}


	public void setIsGift(Short isGift) {
		this.isGift = isGift;
	}





}