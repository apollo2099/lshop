package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvRankfirstInfo entity. @author MyEclipse Persistence Tools
 */

public class LvRankfirstInfo extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -8101553261185833224L;
	private Integer id;
     private String realName;
     private String email;
     private String tel;
     private Short accountTypes;
     private String accountNumber;
     private Integer contryId;
     private String contryName;
     private Integer provinceId;
     private String provinceName;
     private Integer cityId;
     private String cityName;
     private String adress;
     private String description;
 	private Short settlementStatus=0;
	private Double settlementedAmount=0d;
	private Integer settlementedNum=0;
	private Double settlementAmount=0d;
	private Integer settlementNum=0;
	private Double nonSettlementAmount=0d;
	private Integer nonSettlementNum=0;
	private String userRating="0";
     private Integer uid;
     private Double specialAmount;
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
     
     private Boolean isFlag;


    // Constructors

    

	/** default constructor */
    public LvRankfirstInfo() {
    }

	/** minimal constructor */
    public LvRankfirstInfo(String realName, String email, Short settlementStatus, Double settlementedAmount, Integer settlementedNum, Double settlementAmount, Integer settlementNum, Double nonSettlementAmount, Integer nonSettlementNum, String userRating, String storeId) {
        this.realName = realName;
        this.email = email;
        this.settlementStatus = settlementStatus;
        this.settlementedAmount = settlementedAmount;
        this.settlementedNum = settlementedNum;
        this.settlementAmount = settlementAmount;
        this.settlementNum = settlementNum;
        this.nonSettlementAmount = nonSettlementAmount;
        this.nonSettlementNum = nonSettlementNum;
        this.userRating = userRating;
        this.storeId = storeId;
    }
    
    /** full constructor */
    public LvRankfirstInfo(String realName, String email, String tel, Short accountTypes, String accountNumber, Integer contryId, String contryName, Integer provinceId, String provinceName, Integer cityId, String cityName, String adress, String description, Short settlementStatus, Double settlementedAmount, Integer settlementedNum, Double settlementAmount, Integer settlementNum, Double nonSettlementAmount, Integer nonSettlementNum, String userRating, Integer uid, Double specialAmount, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.realName = realName;
        this.email = email;
        this.tel = tel;
        this.accountTypes = accountTypes;
        this.accountNumber = accountNumber;
        this.contryId = contryId;
        this.contryName = contryName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.adress = adress;
        this.description = description;
        this.settlementStatus = settlementStatus;
        this.settlementedAmount = settlementedAmount;
        this.settlementedNum = settlementedNum;
        this.settlementAmount = settlementAmount;
        this.settlementNum = settlementNum;
        this.nonSettlementAmount = nonSettlementAmount;
        this.nonSettlementNum = nonSettlementNum;
        this.userRating = userRating;
        this.uid = uid;
        this.specialAmount = specialAmount;
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

    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public Short getAccountTypes() {
        return this.accountTypes;
    }
    
    public void setAccountTypes(Short accountTypes) {
        this.accountTypes = accountTypes;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Short getSettlementStatus() {
        return this.settlementStatus;
    }
    
    public void setSettlementStatus(Short settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public Double getSettlementedAmount() {
        return this.settlementedAmount;
    }
    
    public void setSettlementedAmount(Double settlementedAmount) {
        this.settlementedAmount = settlementedAmount;
    }

    public Integer getSettlementedNum() {
        return this.settlementedNum;
    }
    
    public void setSettlementedNum(Integer settlementedNum) {
        this.settlementedNum = settlementedNum;
    }

    public Double getSettlementAmount() {
        return this.settlementAmount;
    }
    
    public void setSettlementAmount(Double settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Integer getSettlementNum() {
        return this.settlementNum;
    }
    
    public void setSettlementNum(Integer settlementNum) {
        this.settlementNum = settlementNum;
    }

    public Double getNonSettlementAmount() {
        return this.nonSettlementAmount;
    }
    
    public void setNonSettlementAmount(Double nonSettlementAmount) {
        this.nonSettlementAmount = nonSettlementAmount;
    }

    public Integer getNonSettlementNum() {
        return this.nonSettlementNum;
    }
    
    public void setNonSettlementNum(Integer nonSettlementNum) {
        this.nonSettlementNum = nonSettlementNum;
    }

    public String getUserRating() {
        return this.userRating;
    }
    
    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getSpecialAmount() {
        return this.specialAmount;
    }
    
    public void setSpecialAmount(Double specialAmount) {
        this.specialAmount = specialAmount;
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

    public Boolean getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(Boolean isFlag) {
		this.isFlag = isFlag;
	}
	
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}