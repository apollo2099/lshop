package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvWesternInfo entity. @author MyEclipse Persistence Tools
 */

public class LvWesternInfo extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 8721000567876105684L;
	private Integer id;
     private String remitter;
     private Float remittance;
     private Integer contryId;
     private String contryName;
     private String adress;
     private String transferTime;
     private String mtcn;
     private String oid;
     private Short status;
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
    public LvWesternInfo() {
    }

	/** minimal constructor */
    public LvWesternInfo(Float remittance, String oid, Short status, String storeId) {
        this.remittance = remittance;
        this.oid = oid;
        this.status = status;
        this.storeId = storeId;
    }
    
    /** full constructor */
    public LvWesternInfo(String remitter, Float remittance, Integer contryId, String contryName, String adress, String transferTime, String mtcn, String oid, Short status, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.remitter = remitter;
        this.remittance = remittance;
        this.contryId = contryId;
        this.contryName = contryName;
        this.adress = adress;
        this.transferTime = transferTime;
        this.mtcn = mtcn;
        this.oid = oid;
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

    public String getRemitter() {
        return this.remitter;
    }
    
    public void setRemitter(String remitter) {
        this.remitter = remitter;
    }

    public Float getRemittance() {
        return this.remittance;
    }
    
    public void setRemittance(Float remittance) {
        this.remittance = remittance;
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

    public String getAdress() {
        return this.adress;
    }
    
    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTransferTime() {
        return this.transferTime;
    }
    
    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public String getMtcn() {
        return this.mtcn;
    }
    
    public void setMtcn(String mtcn) {
        this.mtcn = mtcn;
    }

    public String getOid() {
        return this.oid;
    }
    
    public void setOid(String oid) {
        this.oid = oid;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
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

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}