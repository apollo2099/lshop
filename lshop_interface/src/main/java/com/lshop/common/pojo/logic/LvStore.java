package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.gv.core.datastructure.Key;

/**
 * LvStore entity. @author MyEclipse Persistence Tools
 */

public class LvStore extends com.gv.core.datastructure.impl.BasePo implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String number;
	private String name;
	private String domainName;
	private String logo;
	private Integer templetId;
	private String keyword;
	private String description;
	private String affiche;
	private String tel;
	private String footerInfo;
	private String statCode;
	private Short thirdPartyShippingMark;
	private String storeFlag;
	private String code;
	private Date createTime;
	private Integer createUserId;
	private String createUserName;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;
	private Short isCod;
	private Short isdel;
	private String atrr;
	private String atrr1;
	private String atrr2;
	private String atrr3;
	private String email;
	private Short isPreferences;
	private String parentCode;
	private String servicePromise;
	private Short status;
	private Short isHot;
	private Short isTemplet;
	private Short isCommend;
	private Integer countryId;
	private String country;
	private String city;
	private String address;
	private Integer orderValue;
	private String principal;
	private String serviceTel;
	private String urlFlag;
	private String continentCode;
	private String countryCode;
	private String cityCode;
	private String mallSystem;

	// Constructors
    //扩展属性
	private List<LvStoreArea> cityList;
	private String oldName;
	private String defaultTplModel;
	private Short oldStatus;

	

	/** default constructor */
	public LvStore() {
	}

	/** full constructor */
	public LvStore(String number, String name, String domainName, String logo,
			Integer templetId, String keyword, String description,
			String affiche, String tel, String footerInfo, String statCode,
			Short thirdPartyShippingMark, String storeFlag, String code,
			Date createTime, Integer createUserId, String createUserName,
			Date modifyTime, Integer modifyUserId, String modifyUserName,
			Short isCod, Short isdel, String atrr, String atrr1, String atrr2,
			String atrr3, String email, Short isPreferences, String parentCode,
			String servicePromise, Short status, Short isHot,Short isTemplet,Short isCommend,
			Short isRecommend, Integer countryId, String country, String city,
			String address,Integer orderValue,String principal,String serviceTel) {
		this.number = number;
		this.name = name;
		this.domainName = domainName;
		this.logo = logo;
		this.templetId = templetId;
		this.keyword = keyword;
		this.description = description;
		this.affiche = affiche;
		this.tel = tel;
		this.footerInfo = footerInfo;
		this.statCode = statCode;
		this.thirdPartyShippingMark = thirdPartyShippingMark;
		this.storeFlag = storeFlag;
		this.code = code;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
		this.isCod = isCod;
		this.isdel = isdel;
		this.atrr = atrr;
		this.atrr1 = atrr1;
		this.atrr2 = atrr2;
		this.atrr3 = atrr3;
		this.email = email;
		this.isPreferences = isPreferences;
		this.parentCode = parentCode;
		this.servicePromise = servicePromise;
		this.status = status;
		this.isHot = isHot;
		this.isTemplet=isTemplet;
		this.isCommend=isCommend;
		this.countryId = countryId;
		this.country = country;
		this.city = city;
		this.address = address;
		this.orderValue=orderValue;
		this.principal=principal;
		this.serviceTel=serviceTel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getTempletId() {
		return this.templetId;
	}

	public void setTempletId(Integer templetId) {
		this.templetId = templetId;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAffiche() {
		return this.affiche;
	}

	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFooterInfo() {
		return this.footerInfo;
	}

	public void setFooterInfo(String footerInfo) {
		this.footerInfo = footerInfo;
	}

	public String getStatCode() {
		return this.statCode;
	}

	public void setStatCode(String statCode) {
		this.statCode = statCode;
	}

	public Short getThirdPartyShippingMark() {
		return this.thirdPartyShippingMark;
	}

	public void setThirdPartyShippingMark(Short thirdPartyShippingMark) {
		this.thirdPartyShippingMark = thirdPartyShippingMark;
	}

	public String getStoreFlag() {
		return this.storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return this.modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public Short getIsCod() {
		return this.isCod;
	}

	public void setIsCod(Short isCod) {
		this.isCod = isCod;
	}

	public Short getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Short isdel) {
		this.isdel = isdel;
	}

	public String getAtrr() {
		return this.atrr;
	}

	public void setAtrr(String atrr) {
		this.atrr = atrr;
	}

	public String getAtrr1() {
		return this.atrr1;
	}

	public void setAtrr1(String atrr1) {
		this.atrr1 = atrr1;
	}

	public String getAtrr2() {
		return this.atrr2;
	}

	public void setAtrr2(String atrr2) {
		this.atrr2 = atrr2;
	}

	public String getAtrr3() {
		return this.atrr3;
	}

	public void setAtrr3(String atrr3) {
		this.atrr3 = atrr3;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getIsPreferences() {
		return this.isPreferences;
	}

	public void setIsPreferences(Short isPreferences) {
		this.isPreferences = isPreferences;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getServicePromise() {
		return this.servicePromise;
	}

	public void setServicePromise(String servicePromise) {
		this.servicePromise = servicePromise;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getIsHot() {
		return this.isHot;
	}

	public void setIsHot(Short isHot) {
		this.isHot = isHot;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Short getIsTemplet() {
		return isTemplet;
	}

	public void setIsTemplet(Short isTemplet) {
		this.isTemplet = isTemplet;
	}
	
	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public List<LvStoreArea> getCityList() {
		return cityList;
	}

	public void setCityList(List<LvStoreArea> cityList) {
		this.cityList = cityList;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getServiceTel() {
		return serviceTel;
	}

	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

	public String getUrlFlag() {
		return urlFlag;
	}

	public void setUrlFlag(String urlFlag) {
		this.urlFlag = urlFlag;
	}

	public Short getIsCommend() {
		return isCommend;
	}

	public void setIsCommend(Short isCommend) {
		this.isCommend = isCommend;
	}
	public String getContinentCode() {
		return continentCode;
	}

	public void setContinentCode(String continentCode) {
		this.continentCode = continentCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getDefaultTplModel() {
		return defaultTplModel;
	}

	public void setDefaultTplModel(String defaultTplModel) {
		this.defaultTplModel = defaultTplModel;
	}

	public Short getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(Short oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getMallSystem() {
		return mallSystem;
	}

	public void setMallSystem(String mallSystem) {
		this.mallSystem = mallSystem;
	}
	
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}