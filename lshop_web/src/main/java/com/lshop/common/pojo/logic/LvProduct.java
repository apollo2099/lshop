package com.lshop.common.pojo.logic;

import java.util.Date;
import java.util.List;

import com.gv.core.datastructure.Key;


/**
 * LvProduct entity. @author MyEclipse Persistence Tools
 */

public class LvProduct extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	 private static final long serialVersionUID = 1498590839354827789L;
	 private Integer id;
     private String productName;
     private String pcode;
     private Float priceRmb;
     private Float priceUsd;
     private String description;
     private String pmodel;
     private String ptype;
     private String shopProductType;
	 private String pimage;
     private String pimageAd;
	 private Integer isActivity;
     private Integer isSupport;
     private Integer isLadder;
     private Integer isPreferences;
     private Integer isSetMeal;
     private Integer isCommend;
	 private Integer status;
     private String  unsupportRemark;
	 private Date unsupportTime;
	 private String pubProductCode;
	 private String pubPackageName;
	 private Float price;
	 


	 private String storeId;
     private String code;
     private Integer orderId;
     private Date createTime;
     private Date modifyTime;
     private Integer modifyUserId;
     private String modifyUserName;
     private String atrr;
     private String atrr1;
     private String atrr2;
     private String atrr3;

     //扩展属性
     private List<LvProductPackage> packageList;
     private String oldProductName;
     private String oldPType;
     private String oldPubProductCode;
     private List<LvProductType> typeList;

    // Constructors

	/** default constructor */
    public LvProduct() {
    }

    
    /** full constructor */
    public LvProduct(String productName, String pcode, Float priceRmb, Float priceUsd, String description, 
    		String pmodel, String ptype, String pimage,String pimageAd, Integer isActivity, Integer isSupport,
    		Integer isLadder, Integer isPreferences, Integer isSetMeal, Integer isCommend, 
    		Integer status ,String mealCode,String mealName,String shopProductType,String unsupportRemark,Date unsupportTime,
    		String storeId, String code, Date createTime,
    		Date modifyTime,Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.productName = productName;
        this.pcode = pcode;
        this.priceRmb = priceRmb;
        this.priceUsd = priceUsd;
        this.description = description;
        this.pmodel = pmodel;
        this.ptype = ptype;
        this.pimage = pimage;
        this.pimageAd=pimageAd;
        this.isActivity = isActivity;
        this.isSupport = isSupport;
        this.isLadder = isLadder;
        this.isPreferences = isPreferences;
        this.isSetMeal = isSetMeal;
        this.isCommend = isCommend;
        this.status=status;
        this.shopProductType=shopProductType;
        this.unsupportRemark=unsupportRemark;
        this.unsupportTime=unsupportTime;
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

    public String getPcode() {
        return this.pcode;
    }
    
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public Float getPriceRmb() {
        return this.priceRmb;
    }
    
    public void setPriceRmb(Float priceRmb) {
        this.priceRmb = priceRmb;
    }

    public Float getPriceUsd() {
        return this.priceUsd;
    }
    
    public void setPriceUsd(Float priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPmodel() {
        return this.pmodel;
    }
    
    public void setPmodel(String pmodel) {
        this.pmodel = pmodel;
    }

    public String getPtype() {
        return this.ptype;
    }
    
    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPimage() {
        return this.pimage;
    }
    
    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public Integer getIsActivity() {
        return this.isActivity;
    }
    
    public void setIsActivity(Integer isActivity) {
        this.isActivity = isActivity;
    }

    public Integer getIsSupport() {
        return this.isSupport;
    }
    
    public void setIsSupport(Integer isSupport) {
        this.isSupport = isSupport;
    }

    public Integer getIsLadder() {
        return this.isLadder;
    }
    
    public void setIsLadder(Integer isLadder) {
        this.isLadder = isLadder;
    }

    public Integer getIsPreferences() {
        return this.isPreferences;
    }
    
    public void setIsPreferences(Integer isPreferences) {
        this.isPreferences = isPreferences;
    }

    public Integer getIsSetMeal() {
        return this.isSetMeal;
    }
    
    public void setIsSetMeal(Integer isSetMeal) {
        this.isSetMeal = isSetMeal;
    }

    public Integer getIsCommend() {
        return this.isCommend;
    }
    
    public void setIsCommend(Integer isCommend) {
        this.isCommend = isCommend;
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
    
    public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

    public String getPimageAd() {
		return pimageAd;
	}
    
	public void setPimageAd(String pimageAd) {
		this.pimageAd = pimageAd;
	}

    public String getShopProductType() {
		return shopProductType;
	}


	public void setShopProductType(String shopProductType) {
		this.shopProductType = shopProductType;
	}

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
   
    public String getOldProductName() {
		return oldProductName;
	}


	public void setOldProductName(String oldProductName) {
		this.oldProductName = oldProductName;
	}

	public List<LvProductPackage> getPackageList() {
		return packageList;
	}


	public void setPackageList(List<LvProductPackage> packageList) {
		this.packageList = packageList;
	}

	public String getUnsupportRemark() {
		return unsupportRemark;
	}

	public void setUnsupportRemark(String unsupportRemark) {
		this.unsupportRemark = unsupportRemark;
	}

	public Date getUnsupportTime() {
		return unsupportTime;
	}

	public void setUnsupportTime(Date unsupportTime) {
		this.unsupportTime = unsupportTime;
	}

	public String getOldPType() {
		return oldPType;
	}

	public void setOldPType(String oldPType) {
		this.oldPType = oldPType;
	}

	public String getPubProductCode() {
		return pubProductCode;
	}

	public void setPubProductCode(String pubProductCode) {
		this.pubProductCode = pubProductCode;
	}


	public String getPubPackageName() {
		return pubPackageName;
	}

	public void setPubPackageName(String pubPackageName) {
		this.pubPackageName = pubPackageName;
	}

	public String getOldPubProductCode() {
		return oldPubProductCode;
	}

	public void setOldPubProductCode(String oldPubProductCode) {
		this.oldPubProductCode = oldPubProductCode;
	}

	public List<LvProductType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<LvProductType> typeList) {
		this.typeList = typeList;
	}
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}