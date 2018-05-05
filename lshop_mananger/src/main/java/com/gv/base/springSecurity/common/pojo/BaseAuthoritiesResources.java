package com.gv.base.springSecurity.common.pojo;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseAuthoritiesResources   
 * 类描述：   BaseAuthoritiesResources entity
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:05:46   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:05:46   
 * 修改备注：   
 * @version
 */


public class BaseAuthoritiesResources extends BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private String authorityCode;
	private String resourceCode;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;

	// Constructors

	/** default constructor */
	public BaseAuthoritiesResources() {
	}

	/** full constructor */
	public BaseAuthoritiesResources(Integer version, String authorityCode,
			String resourceCode, Integer status, String createdBy,
			Timestamp createdDate, String lastUpdatedBy,
			Timestamp lastUpdatedDate) {
		this.version = version;
		this.authorityCode = authorityCode;
		this.resourceCode = resourceCode;
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

	public String getAuthorityCode() {
		return this.authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getResourceCode() {
		return this.resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
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