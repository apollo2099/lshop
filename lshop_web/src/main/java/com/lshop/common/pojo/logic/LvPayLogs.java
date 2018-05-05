package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvPayLogs entity. @author MyEclipse Persistence Tools
 */

public class LvPayLogs extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1453981814079130948L;
	private Integer id;
     private String oid;
     private Short paymethod;
     private Date paydate;
     private String checkno;
     private Float amount;
     private Short status;
     private String bankorderid;
     private String remark;
     private String currency;
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
     
     //扩展属性
     private String startTime;
	 private String endTime;


    // Constructors

    /** default constructor */
    public LvPayLogs() {
    }

    
    /** full constructor */
    public LvPayLogs(String oid, Short paymethod, Date paydate, String checkno, Float amount, Short status, String bankorderid, String remark, String currency, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.oid = oid;
        this.paymethod = paymethod;
        this.paydate = paydate;
        this.checkno = checkno;
        this.amount = amount;
        this.status = status;
        this.bankorderid = bankorderid;
        this.remark = remark;
        this.currency = currency;
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

    public String getOid() {
        return this.oid;
    }
    
    public void setOid(String oid) {
        this.oid = oid;
    }

    public Short getPaymethod() {
        return this.paymethod;
    }
    
    public void setPaymethod(Short paymethod) {
        this.paymethod = paymethod;
    }

    public Date getPaydate() {
        return this.paydate;
    }
    
    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getCheckno() {
        return this.checkno;
    }
    
    public void setCheckno(String checkno) {
        this.checkno = checkno;
    }

    public Float getAmount() {
        return this.amount;
    }
    
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }

    public String getBankorderid() {
        return this.bankorderid;
    }
    
    public void setBankorderid(String bankorderid) {
        this.bankorderid = bankorderid;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}