package com.lshop.common.pojo.logic;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvExchangeRate entity. @author MyEclipse Persistence Tools
 */

public class LvExchangeRate extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String currency;
     private String currencyName;
     private Float exchangeRate;
     private String mainCurrency;
     private String mainCurrencyName;
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
     private String oldCurrency;


    // Constructors

   


	/** default constructor */
    public LvExchangeRate() {
    }

    
    /** full constructor */
    public LvExchangeRate(String currency, String currencyName, Float exchangeRate, String mainCurrency, String mainCurrencyName, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.currency = currency;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.mainCurrency = mainCurrency;
        this.mainCurrencyName = mainCurrencyName;
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

    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyName() {
        return this.currencyName;
    }
    
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Float getExchangeRate() {
        return this.exchangeRate;
    }
    public String getExchangeRateStr() {
    	DecimalFormat df = new DecimalFormat("#.###");    
        return df.format(this.exchangeRate);//比如去掉如0.0050后的0,只保留3位小数显示
    }
    
    
    public void setExchangeRate(Float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getMainCurrency() {
        return this.mainCurrency;
    }
    
    public void setMainCurrency(String mainCurrency) {
        this.mainCurrency = mainCurrency;
    }

    public String getMainCurrencyName() {
        return this.mainCurrencyName;
    }
    
    public void setMainCurrencyName(String mainCurrencyName) {
        this.mainCurrencyName = mainCurrencyName;
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

    public String getOldCurrency() {
		return oldCurrency;
	}


	public void setOldCurrency(String oldCurrency) {
		this.oldCurrency = oldCurrency;
	}
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}