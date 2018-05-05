package com.gv.base.springSecurity.common.pojo;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseUsersRoles   
 * 类描述：   BaseUsersRoles entity.
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:06:37   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:06:37   
 * 修改备注：   
 * @version
 */
public class BaseUsersRoles extends BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private String userCode;
	private String roleCode;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;

	// Constructors

	/** default constructor */
	public BaseUsersRoles() {
	}

	/** full constructor */
	public BaseUsersRoles(Integer version, String userCode, String roleCode,
			Integer status, String createdBy, Timestamp createdDate,
			String lastUpdatedBy, Timestamp lastUpdatedDate) {
		this.version = version;
		this.userCode = userCode;
		this.roleCode = roleCode;
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

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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

}