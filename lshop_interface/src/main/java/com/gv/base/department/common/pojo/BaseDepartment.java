package com.gv.base.department.common.pojo;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * BaseDepartment entity. @author MyEclipse Persistence Tools
 */

public class BaseDepartment extends BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private String departmentCode;
	private String departmentParent;
	private String departmentName;
	private String departmentDesc;
	private Integer departmentDisorder;
	private Integer departmentLevel;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;

	// Constructors

	/** default constructor */
	public BaseDepartment() {
	}

	/** full constructor */
	public BaseDepartment(Integer version, String departmentCode,
			String departmentParent, String departmentName,
			String departmentDesc, Integer departmentDisorder,
			Integer departmentLevel, Integer status, String createdBy,
			Timestamp createdDate, String lastUpdatedBy,
			Timestamp lastUpdatedDate) {
		this.version = version;
		this.departmentCode = departmentCode;
		this.departmentParent = departmentParent;
		this.departmentName = departmentName;
		this.departmentDesc = departmentDesc;
		this.departmentDisorder = departmentDisorder;
		this.departmentLevel = departmentLevel;
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

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentParent() {
		return this.departmentParent;
	}

	public void setDepartmentParent(String departmentParent) {
		this.departmentParent = departmentParent;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDesc() {
		return this.departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	public Integer getDepartmentDisorder() {
		return this.departmentDisorder;
	}

	public void setDepartmentDisorder(Integer departmentDisorder) {
		this.departmentDisorder = departmentDisorder;
	}

	public Integer getDepartmentLevel() {
		return this.departmentLevel;
	}

	public void setDepartmentLevel(Integer departmentLevel) {
		this.departmentLevel = departmentLevel;
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