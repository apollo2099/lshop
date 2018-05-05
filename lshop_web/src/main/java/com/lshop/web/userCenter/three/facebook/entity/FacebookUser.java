package com.lshop.web.userCenter.three.facebook.entity;

import java.io.Serializable;


/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_web] 
 * @Package:      [com.lshop.web.userCenter.three.facebook.entity.FacebookUser.java]  
 * @ClassName:    [FacebookUser]   
 * @Description:  [Facebook用户返回简单封装]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2014-12-8 下午6:36:14]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2014-12-8 下午6:36:14]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class FacebookUser implements Serializable {

	/** */
	private static final long serialVersionUID = -7680775505799165658L;

	private String snsId;//用户标示编码
	private String name;
	private String email;//用户邮箱
	private String userName;//用户昵称

	public String getSnsId() {
		return snsId;
	}

	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}