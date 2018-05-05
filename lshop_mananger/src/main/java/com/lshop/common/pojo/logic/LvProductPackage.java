package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvProductPackage entity. @author MyEclipse Persistence Tools
 */

public class LvProductPackage extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1803899597227938593L;
	private Integer id;
     private String packageNum;
     private String packageTitle;
     private String productCode;
	 private Integer pnum;
     private Float priceRmb;
	 private Float priceUsd;
	 private Float price;//最新价格字段
     private String pcode;
     private Integer sortId;
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
     private String pubPackageDetailsCode;
	 private String pubProductCode;


    // Constructors

    /** default constructor */
    public LvProductPackage() {
    }

    
    /** full constructor */
    public LvProductPackage(String packageNum, String packageTitle, String productCode,Integer pnum,String pcode,
    		Integer sortId ,String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, 
    		String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.packageNum = packageNum;
        this.packageTitle = packageTitle;
        this.productCode = productCode;
        this.pnum=pnum;
        this.pcode=pcode;
        this.sortId=sortId;
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
    public Integer getSortId() {
		return sortId;
	}


	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}


    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPackageNum() {
        return this.packageNum;
    }
    
    public void setPackageNum(String packageNum) {
        this.packageNum = packageNum;
    }

    public String getPackageTitle() {
        return this.packageTitle;
    }
    
    public void setPackageTitle(String packageTitle) {
        this.packageTitle = packageTitle;
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
    public Integer getPnum() {
		return pnum;
	}


	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}


	public String getPcode() {
		return pcode;
	}


	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
    public Float getPriceRmb() {
		return priceRmb;
	}

	public void setPriceRmb(Float priceRmb) {
		this.priceRmb = priceRmb;
	}

	public Float getPriceUsd() {
		return priceUsd;
	}

	public void setPriceUsd(Float priceUsd) {
		this.priceUsd = priceUsd;
	}

	public String getPubPackageDetailsCode() {
		return pubPackageDetailsCode;
	}

	public void setPubPackageDetailsCode(String pubPackageDetailsCode) {
		this.pubPackageDetailsCode = pubPackageDetailsCode;
	}

	public String getPubProductCode() {
		return pubProductCode;
	}

	public void setPubProductCode(String pubProductCode) {
		this.pubProductCode = pubProductCode;
	}
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}