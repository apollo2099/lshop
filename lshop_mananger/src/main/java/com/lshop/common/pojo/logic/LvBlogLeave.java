package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvBlogLeave entity. @author MyEclipse Persistence Tools
 */

public class LvBlogLeave extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -997859011373045289L;
	private Integer id;
     private Integer contentId;
     private Integer userId;
     private String userName;
     private Integer whoId;
     private String whoName;
     private String content;
     private String ipAddress;
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
 	private LvBlogContent lvBlogContent;



    public LvBlogLeave() {
    }

	/** minimal constructor */
    public LvBlogLeave(Integer contentId, Integer userId, String userName, String storeId) {
        this.contentId = contentId;
        this.userId = userId;
        this.userName = userName;
        this.storeId = storeId;
    }
    
    /** full constructor */
    public LvBlogLeave(Integer contentId, Integer userId, String userName, Integer whoId, String whoName, String content, String ipAddress, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.contentId = contentId;
        this.userId = userId;
        this.userName = userName;
        this.whoId = whoId;
        this.whoName = whoName;
        this.content = content;
        this.ipAddress = ipAddress;
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

    public Integer getContentId() {
        return this.contentId;
    }
    
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getWhoId() {
        return this.whoId;
    }
    
    public void setWhoId(Integer whoId) {
        this.whoId = whoId;
    }

    public String getWhoName() {
        return this.whoName;
    }
    
    public void setWhoName(String whoName) {
        this.whoName = whoName;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
    public LvBlogContent getLvBlogContent() {
		return lvBlogContent;
	}

	public void setLvBlogContent(LvBlogContent lvBlogContent) {
		this.lvBlogContent = lvBlogContent;
	}
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}