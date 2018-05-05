package com.gv.base.springSecurity.common.pojo;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseUsers   
 * 类描述：   BaseUsers entity.
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:06:29   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:06:29   
 * 修改备注：   
 * @version
 */
public class BaseUsers extends BasePo implements java.io.Serializable  {

	// Fields

	private Integer id;
	private Integer version;
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
	private String storeFlag;

	

	/** default constructor */
	public BaseUsers() {
	}

	/** minimal constructor */
	public BaseUsers(String userCode) {
		this.userCode = userCode;
	}

	/** full constructor */
	public BaseUsers(Integer version, String userCode, String userAccount,
			String userName, String userPassword, String userDesc,
			String userLoginip, Timestamp userLogintime, String userLastip,
			Timestamp userLasttime, Integer status, String createdBy,
			Timestamp createdDate, String lastUpdatedBy,
			Timestamp lastUpdatedDate) {
		this.version = version;
		this.userCode = userCode;
		this.userAccount = userAccount;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userDesc = userDesc;
		this.userLoginip = userLoginip;
		this.userLogintime = userLogintime;
		this.userLastip = userLastip;
		this.userLasttime = userLasttime;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserDesc() {
		return this.userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getUserLoginip() {
		return this.userLoginip;
	}

	public void setUserLoginip(String userLoginip) {
		this.userLoginip = userLoginip;
	}

	public Timestamp getUserLogintime() {
		return this.userLogintime;
	}

	public void setUserLogintime(Timestamp userLogintime) {
		this.userLogintime = userLogintime;
	}

	public String getUserLastip() {
		return this.userLastip;
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
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStoreFlag() {
		return storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}

}