package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvSettlementLog entity. @author MyEclipse Persistence Tools
 */

public class LvSettlementLog extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 6197199972004816946L;
	private Integer id;
     private String aids;
     private Date clearingTime;
     private String settlementPeople;
     private Integer settlementedNum;
     private Double settlementedAmount;
     private Integer uid;
     private Short userType;
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
    public LvSettlementLog() {
    }

	/** minimal constructor */
    public LvSettlementLog(String settlementPeople, String storeId) {
        this.settlementPeople = settlementPeople;
        this.storeId = storeId;
    }
    
    /** full constructor */
    public LvSettlementLog(String aids, Date clearingTime, String settlementPeople, Integer settlementedNum, Double settlementedAmount, Integer uid, Short userType, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.aids = aids;
        this.clearingTime = clearingTime;
        this.settlementPeople = settlementPeople;
        this.settlementedNum = settlementedNum;
        this.settlementedAmount = settlementedAmount;
        this.uid = uid;
        this.userType = userType;
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

    public String getAids() {
        return this.aids;
    }
    
    public void setAids(String aids) {
        this.aids = aids;
    }

    public Date getClearingTime() {
        return this.clearingTime;
    }
    
    public void setClearingTime(Date clearingTime) {
        this.clearingTime = clearingTime;
    }

    public String getSettlementPeople() {
        return this.settlementPeople;
    }
    
    public void setSettlementPeople(String settlementPeople) {
        this.settlementPeople = settlementPeople;
    }

    public Integer getSettlementedNum() {
        return this.settlementedNum;
    }
    
    public void setSettlementedNum(Integer settlementedNum) {
        this.settlementedNum = settlementedNum;
    }

    public Double getSettlementedAmount() {
        return this.settlementedAmount;
    }
    
    public void setSettlementedAmount(Double settlementedAmount) {
        this.settlementedAmount = settlementedAmount;
    }

    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Short getUserType() {
        return this.userType;
    }
    
    public void setUserType(Short userType) {
        this.userType = userType;
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

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}