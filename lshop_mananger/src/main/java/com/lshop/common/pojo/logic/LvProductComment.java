package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvProductComment entity. @author MyEclipse Persistence Tools
 */

public class LvProductComment extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	 private static final long serialVersionUID = -6433859042113714891L;
	 private Integer id;
     private String uid;
     private String nickname;
     private String oid;
     private String productCode;
     private String content;
     private Integer replyId;
     private Integer score;
     private Short grade;
     private Short isCheck;
     private Integer sortId;
     private String commentImages;
     private String ip;
     private Integer contryId;
     private Float oprice;
	 private Integer pnum;
     private String currency;
     private Short isDelete;
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
    public LvProductComment() {
    }

    
    /** full constructor */
    public LvProductComment(String uid, String nickname, String oid, String productCode, String content, Integer replyId, Integer score, Short grade, Short isCheck, Integer sortId, String ip, Integer contryId, Float oprice, Integer pnum, String currency, Short isDelete, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.uid = uid;
        this.nickname = nickname;
        this.oid = oid;
        this.productCode = productCode;
        this.content = content;
        this.replyId = replyId;
        this.score = score;
        this.grade = grade;
        this.isCheck = isCheck;
        this.sortId = sortId;
        this.ip = ip;
        this.contryId = contryId;
        this.oprice = oprice;
        this.pnum = pnum;
        this.currency = currency;
        this.isDelete = isDelete;
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

    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyId() {
        return this.replyId;
    }
    
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }

    public Short getGrade() {
        return this.grade;
    }
    
    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public Short getIsCheck() {
        return this.isCheck;
    }
    
    public void setIsCheck(Short isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getSortId() {
        return this.sortId;
    }
    
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPnum() {
        return this.pnum;
    }
    
    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Short getIsDelete() {
        return this.isDelete;
    }
    
    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
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


	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public Integer getContryId() {
		return contryId;
	}


	public void setContryId(Integer contryId) {
		this.contryId = contryId;
	}

    public Float getOprice() {
		return oprice;
	}


	public void setOprice(Float oprice) {
		this.oprice = oprice;
	}


	public String getCommentImages() {
		return commentImages;
	}


	public void setCommentImages(String commentImages) {
		this.commentImages = commentImages;
	}



   








}