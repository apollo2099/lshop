package com.lshop.common.pojo.logic;

import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvCoupon entity. @author MyEclipse Persistence Tools
 */
public class LvServiceNetwork extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private Integer contryId;
	private String country;
	private String city;
	private String channelName;
	private String address;
	private String responsiblePerson;
	private java.util.Date createTime;
	private java.util.Date modifyTime;
	private Integer orderValue;
	private String webLanguage;
	private String storeId;
	
	//扩展属性
	private String icon; //国旗
	

	// Constructors

	/** default constructor */
	public LvServiceNetwork() {
	}

	/** full constructor */
	public LvServiceNetwork(Integer contryId, String country,
			 String city, String channelName, String address,
			 String responsiblePerson, Date createTime,Date modifyTime,Integer orderValue, String webLanguage,
			 String storeId) {
		this.contryId = contryId;
		this.country = country;
		this.city = city;
		this.channelName = channelName;
		this.address = address;
		this.responsiblePerson = responsiblePerson;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.orderValue = orderValue;
		this.webLanguage = webLanguage;
		this.storeId = storeId;
		
	}

	// Property accessors

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContryId() {
		return contryId;
	}

	public void setContryId(Integer contryId) {
		this.contryId = contryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getWebLanguage() {
		return webLanguage;
	}

	public void setWebLanguage(String webLanguage) {
		this.webLanguage = webLanguage;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}


}