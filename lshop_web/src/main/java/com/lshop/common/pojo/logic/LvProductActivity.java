package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvProductActivity entity. @author MyEclipse Persistence Tools
 */

public class LvProductActivity extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 8455382688104417113L;
	private Integer id;
     private String productCode;
     private String activityTitle;
     private Integer activityType;
     private Date startTime;
     private Date endTime;
     private Integer counts;
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
    public LvProductActivity() {
    }

    
    /** full constructor */
    public LvProductActivity(String productCode, String activityTitle, Integer activityType, Date startTime, Date endTime, Integer counts, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.productCode = productCode;
        this.activityTitle = activityTitle;
        this.activityType = activityType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.counts = counts;
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

    public String getActivityTitle() {
        return this.activityTitle;
    }
    
    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Integer getActivityType() {
        return this.activityType;
    }
    
    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
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

    public Integer getCounts() {
        return this.counts;
    }
    
    public void setCounts(Integer counts) {
        this.counts = counts;
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