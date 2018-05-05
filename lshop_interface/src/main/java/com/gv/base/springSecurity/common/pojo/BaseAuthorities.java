package com.gv.base.springSecurity.common.pojo;

import java.sql.Timestamp;

import org.springframework.security.core.GrantedAuthority;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseAuthorities   
 * 类描述：   BaseAuthorities entity.
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:05:23   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:05:23   
 * 修改备注：   
 * @version
 */

public class BaseAuthorities extends BasePo implements GrantedAuthority , java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private String authoritiesCode;
	private String authorityName;
	private String authorityDesc;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;

	// Constructors

	/** default constructor */
	public BaseAuthorities() {
	}

	/** minimal constructor */
	public BaseAuthorities(String authoritiesCode) {
		this.authoritiesCode = authoritiesCode;
	}

	/** full constructor */
	public BaseAuthorities(Integer version, String authoritiesCode,
			String authorityName, String authorityDesc, Integer status,
			String createdBy, Timestamp createdDate, String lastUpdatedBy,
			Timestamp lastUpdatedDate) {
		this.version = version;
		this.authoritiesCode = authoritiesCode;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
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

	public String getAuthoritiesCode() {
		return this.authoritiesCode;
	}

	public void setAuthoritiesCode(String authoritiesCode) {
		this.authoritiesCode = authoritiesCode;
	}

	public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return this.authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
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

	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authoritiesCode;
	}

}