package com.gv.base.springSecurity.common.pojo;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseRolesAuthorities   
 * 类描述：   BaseRolesAuthorities entity.
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:06:18   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:06:18   
 * 修改备注：   
 * @version
 */
public class BaseRolesAuthorities extends BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private String roleCode;
	private String authorityCode;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;

	// Constructors

	/** default constructor */
	public BaseRolesAuthorities() {
	}

	/** full constructor */
	public BaseRolesAuthorities(Integer version, String roleCode,
			String authorityCode, Integer status, String createdBy,
			Timestamp createdDate, String lastUpdatedBy,
			Timestamp lastUpdatedDate) {
		this.version = version;
		this.roleCode = roleCode;
		this.authorityCode = authorityCode;
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

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getAuthorityCode() {
		return this.authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
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