package com.lshop.common.pojo.logic;

import java.util.Date;
import java.util.List;

import com.gv.core.datastructure.Key;


/**
 * LvNavigation entity. @author MyEclipse Persistence Tools
 */

public class LvNavigation extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -5845826233722256398L;
	private Integer id;
     private String navName;
     private String navUrl;
     private Integer navParentId;
	 private String storeId;
     private String code;
     private Integer orderValue;
     private Integer openTarget;//打开方式
     private Date createTime;
     private Date modifyTime;
     private Integer modifyUserId;
     private String modifyUserName;
     private String atrr;
     private String atrr1;
     private String atrr2;
     private String atrr3;
     private String navFlag;
     private String navImage;

     //扩展属性
     private List<LvNavigation> children;//子菜单

    // Constructors


	/** default constructor */
    public LvNavigation() {
    }

	/** minimal constructor */
    public LvNavigation(String storeId) {
        this.storeId = storeId;
    }
    
    /** full constructor */
    public LvNavigation(String navName, String navUrl, Integer navParentId, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.navName = navName;
        this.navUrl = navUrl;
        this.navParentId = navParentId;
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

    public String getNavName() {
        return this.navName;
    }
    
    public void setNavName(String navName) {
        this.navName = navName;
    }

    public String getNavUrl() {
        return this.navUrl;
    }
    
    public void setNavUrl(String navUrl) {
        this.navUrl = navUrl;
    }

    public Integer getNavParentId() {
        return this.navParentId;
    }
    
    public void setNavParentId(Integer navParentId) {
        this.navParentId = navParentId;
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

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public Integer getOpenTarget() {
		return openTarget;
	}

	public void setOpenTarget(Integer openTarget) {
		this.openTarget = openTarget;
	}

	public String getNavImage() {
		return navImage;
	}

	public void setNavImage(String navImage) {
		this.navImage = navImage;
	}

	public String getNavFlag() {
		return navFlag;
	}

	public void setNavFlag(String navFlag) {
		this.navFlag = navFlag;
	}

	public List<LvNavigation> getChildren() {
		return children;
	}

	public void setChildren(List<LvNavigation> children) {
		this.children = children;
	}
}