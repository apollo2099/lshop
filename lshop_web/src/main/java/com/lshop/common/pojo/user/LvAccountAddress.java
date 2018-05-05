package com.lshop.common.pojo.user;

import java.util.Date;
import java.util.List;

import com.gv.core.datastructure.Key;


/**
 * LvAccountAddress entity. @author MyEclipse Persistence Tools
 */

public class LvAccountAddress extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -5208770491937156632L;
	private Integer id;
     private String relCode;
     private String relName;
     private String postCode;
     private String mobile;
     private String tel;
     private Integer contryId;
     private String contryName;
     private Integer provinceId;
     private String provinceName;
     private Integer cityId;
     private String cityName;
     private String adress;
     private String storeId;
     private String code;
     private Short isDefault;
	 private Date createTime;
     private Date modifyTime;
     private Integer modifyUserId;
     private String modifyUserName;
     private String atrr;
     private String atrr1;
     private String atrr2;
     private String atrr3;


     //扩展属性
     private List<LvAccountAddress> addressList;
    // Constructors




	/** default constructor */
    public LvAccountAddress() {
    }

    
    /** full constructor */
    public LvAccountAddress(String relCode, String relName, String postCode, String mobile, String tel, Integer contryId, String contryName, Integer provinceId, String provinceName, Integer cityId, String cityName, String adress, String storeId, String code, Short isDefault,Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.relCode = relCode;
        this.relName = relName;
        this.postCode = postCode;
        this.mobile = mobile;
        this.tel = tel;
        this.contryId = contryId;
        this.contryName = contryName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.adress = adress;
     
        this.storeId = storeId;
        this.code = code;
        this.isDefault = isDefault;
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

    public String getRelCode() {
        return this.relCode;
    }
    
    public void setRelCode(String relCode) {
        this.relCode = relCode;
    }

    public String getRelName() {
        return this.relName;
    }
    
    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getPostCode() {
        return this.postCode;
    }
    
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getContryId() {
        return this.contryId;
    }
    
    public void setContryId(Integer contryId) {
        this.contryId = contryId;
    }

    public String getContryName() {
        return this.contryName;
    }
    
    public void setContryName(String contryName) {
        this.contryName = contryName;
    }

    public Integer getProvinceId() {
        return this.provinceId;
    }
    
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return this.provinceName;
    }
    
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAdress() {
        return this.adress;
    }
    
    public void setAdress(String adress) {
        this.adress = adress;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

	public Short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	public List<LvAccountAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<LvAccountAddress> addressList) {
		this.addressList = addressList;
	}







}