/**
 * 
 */
package com.gv.base.springSecurity.common.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseDictionary   
 * 类描述：BaseDictionary entity.
 * 创建人：panjin
 * 创建时间：2012-5-3 下午01:50:00   
 * 修改人：panjin   
 * 修改时间：2012-5-3 下午01:50:00   
 * 修改备注：   
 * @version
 */
public class BaseDictionarys extends BasePo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private Integer id;
	private Integer version;
	private String dictionaryCode;
	private String parentCode;
	private String codeName;
	private String description;
	private Integer levelNo;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;
	
	// Constructors
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the dictionaryCode
	 */
	public String getDictionaryCode() {
		return dictionaryCode;
	}

	/**
	 * @param dictionaryCode the dictionaryCode to set
	 */
	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the levelNo
	 */
	public Integer getLevelNo() {
		return levelNo;
	}

	/**
	 * @param levelNo the levelNo to set
	 */
	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastUpdatedBy
	 */
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * @param lastUpdatedBy the lastUpdatedBy to set
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/** default constructor */
	public BaseDictionarys() {
		
	}
	
	/** minimal constructor */
	public BaseDictionarys(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}
	
	@Override
	public Key getPk() {
		return null;
	}

}
