package com.lshop.common.pojo.user;

import java.util.Date;

import com.gv.core.datastructure.Key;


/**
 * LvAccount entity. @author MyEclipse Persistence Tools
 */

public class LvAccount extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -550129404197653460L;
	private Integer id;
    private String email;
    private String nickname;
    private String pwd;
    private Date lastTime;
    private Short userType;
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
    
    //扩展属性
    private String startTime;//开始时间
	private String endTime;  //结束时间
	private String newPwd;   //新密码
	private String oldNickname;
 	




	public static long getSerialversionuid() {
		return serialVersionUID;
	}


    // Constructors

    /** default constructor */
    public LvAccount() {
    }

	/** minimal constructor */
    public LvAccount(Short userType, String storeId) {
        this.userType = userType;
        this.storeId = storeId;
    }
    
    /** full constructor */
    public LvAccount(String email, String nickname, String pwd, Date lastTime, Short userType, Short status, String storeId, String code, Date createTime, Date modifyTime, Integer modifyUserId, String modifyUserName, String atrr, String atrr1, String atrr2, String atrr3) {
        this.email = email;
        this.nickname = nickname;
        this.pwd = pwd;
        this.lastTime = lastTime;
        this.userType = userType;
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

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getLastTime() {
        return this.lastTime;
    }
    
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
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
    public String getNewPwd() {
		return newPwd;
	}


	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
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
	
	public String getOldNickname() {
		return oldNickname;
	}


	public void setOldNickname(String oldNickname) {
		this.oldNickname = oldNickname;
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