package com.gv.base.springSecurity.common.pojo;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseResources   
 * 类描述：   BaseResources entity.
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:06:00   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:06:00   
 * 修改备注：   
 * @version
 */
public class BaseResources extends BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private String resourceCode;
	private String resourceName;
	private String resourceDesc;
	private Integer resourceType;
	private String resourceString;
	private String menuCode;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;

	// Constructors

	/** default constructor */
	public BaseResources() {
	}

	/** minimal constructor */
	public BaseResources(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	/** full constructor */
	public BaseResources(Integer version, String resourceCode,
			String resourceName, String resourceDesc, Integer resourceType,
			String resourceString,String menuCode, Integer status, String createdBy,
			Timestamp createdDate, String lastUpdatedBy,
			Timestamp lastUpdatedDate) {
		this.version = version;
		this.resourceCode = resourceCode;
		this.resourceName = resourceName;
		this.resourceDesc = resourceDesc;
		this.resourceType = resourceType;
		this.resourceString = resourceString;
		this.menuCode = menuCode;
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

	public String getResourceCode() {
		return this.resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceDesc() {
		return this.resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public Integer getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceString() {
		return this.resourceString;
	}

	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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