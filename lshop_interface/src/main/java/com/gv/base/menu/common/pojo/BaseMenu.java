package com.gv.base.menu.common.pojo;

import java.sql.Timestamp;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseMenu   
 * 类描述：   BaseMenu entity.
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:09:27   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:09:27   
 * 修改备注：   
 * @version
 */

public class BaseMenu extends BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private String menuCode;
	private String menuUrl;
	private String menuName;
	private String menuParent;
	private Integer menuDisorder;
	private Integer status;
	private String createdBy;
	private Timestamp createdDate;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedDate;

	// Constructors

	/** default constructor */
	public BaseMenu() {
	}

	/** minimal constructor */
	public BaseMenu(Integer menuDisorder) {
		this.menuDisorder = menuDisorder;
	}

	/** full constructor */
	public BaseMenu(Integer version, String menuCode, String menuUrl,
			String menuName, String menuParent, Integer menuDisorder,
			Integer status, String createdBy, Timestamp createdDate,
			String lastUpdatedBy, Timestamp lastUpdatedDate) {
		this.version = version;
		this.menuCode = menuCode;
		this.menuUrl = menuUrl;
		this.menuName = menuName;
		this.menuParent = menuParent;
		this.menuDisorder = menuDisorder;
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

	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuParent() {
		return this.menuParent;
	}

	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}

	public Integer getMenuDisorder() {
		return this.menuDisorder;
	}

	public void setMenuDisorder(Integer menuDisorder) {
		this.menuDisorder = menuDisorder;
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