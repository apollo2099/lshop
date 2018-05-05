package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvBlogContent entity. @author MyEclipse Persistence Tools
 */

public class LvBlogContent extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -4519354727540373719L;
	private Integer id;
     private Integer userId;
     private String userName;
     private String title;
     private String content;
     private String intor;
     private Integer type;
     private Short isPrivate;
     private Short isTop;
     private Short isRed;
     private Short isRecommend;
     private Integer replyNum;
     private Integer clickNum;
     private Short isDelete;
     private Integer orderId;
     private String keyword;
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
    public LvBlogContent() {
    }

	/** minimal constructor */
    public LvBlogContent(Integer userId, String userName, String title, String content, String intor, String storeId) {
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.intor = intor;
        this.storeId = storeId;
    }
    
    /** full constructor */
    public LvBlogContent(Integer userId, String userName, String title, String content, String intor, Integer type, Short isPrivate, Short isTop, Short isRed, Integer replyNum, Integer clickNum, Short isDelete, Integer orderId, String keyword, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.intor = intor;
        this.type = type;
        this.isPrivate = isPrivate;
        this.isTop = isTop;
        this.isRed = isRed;
        this.replyNum = replyNum;
        this.clickNum = clickNum;
        this.isDelete = isDelete;
        this.orderId = orderId;
        this.keyword = keyword;
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

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getIntor() {
        return this.intor;
    }
    
    public void setIntor(String intor) {
        this.intor = intor;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public Short getIsPrivate() {
        return this.isPrivate;
    }
    
    public void setIsPrivate(Short isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Short getIsTop() {
        return this.isTop;
    }
    
    public void setIsTop(Short isTop) {
        this.isTop = isTop;
    }

    public Short getIsRed() {
        return this.isRed;
    }
    
    public void setIsRed(Short isRed) {
        this.isRed = isRed;
    }

    public Integer getReplyNum() {
        return this.replyNum;
    }
    
    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getClickNum() {
        return this.clickNum;
    }
    
    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Short getIsDelete() {
        return this.isDelete;
    }
    
    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getKeyword() {
        return this.keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

	public Short getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Short isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
   








}