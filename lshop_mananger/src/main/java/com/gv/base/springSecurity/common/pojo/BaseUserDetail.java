package com.gv.base.springSecurity.common.pojo;

import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class BaseUserDetail extends User{
	
	private String userCode;
	private String userAccount;
	private String userName;
	private String userPassword;
	private String userDesc;
	private String userLoginip;
	private Timestamp userLogintime;
	private String userLastip;
	private Timestamp userLasttime;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;
	public BaseUserDetail(String userAccount, String userName,String password,String userCode, boolean enabled,boolean accountNonExpired, boolean credentialsNonExpired,boolean accountNonLocked,Collection<? extends GrantedAuthority> authorities) {
		
		super(userAccount, password, enabled, accountNonExpired, credentialsNonExpired,accountNonLocked, authorities);
		this.userCode = userCode;
		this.userName = userName;
		// TODO Auto-generated constructor stub
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getUserLoginip() {
		return userLoginip;
	}
	public void setUserLoginip(String userLoginip) {
		this.userLoginip = userLoginip;
	}
	public Timestamp getUserLogintime() {
		return userLogintime;
	}
	public void setUserLogintime(Timestamp userLogintime) {
		this.userLogintime = userLogintime;
	}
	public String getUserLastip() {
		return userLastip;
	}
	public void setUserLastip(String userLastip) {
		this.userLastip = userLastip;
	}
	public Timestamp getUserLasttime() {
		return userLasttime;
	}
	public void setUserLasttime(Timestamp userLasttime) {
		this.userLasttime = userLasttime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}


}
