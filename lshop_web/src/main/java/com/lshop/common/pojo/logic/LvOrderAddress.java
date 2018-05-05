package com.lshop.common.pojo.logic;


import java.util.Date;
import com.gv.core.datastructure.Key;

/**
 * LvOrderAddress entity. @author MyEclipse Persistence Tools
 */

public class LvOrderAddress extends com.gv.core.datastructure.impl.BasePo
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderId;
	private String relCode;
	private String relName;
	private String postCode;
	private String mobile;
	private String tel;
	private Integer contryId;
	private String contryName;
	private Integer provinceId;
	private String provinceName;
	private Integer cityId;
	private String cityName;
	private String adress;
	private Integer bestDeliveryTime;
	private String storeId;
	private String code;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;
	private String atrr;
	private String atrr1;
	private String atrr2;
	private String atrr3;

	// Constructors

	/** default constructor */
	public LvOrderAddress() {
	}

	/** full constructor */
	public LvOrderAddress(String orderId, String relCode, String relName,
			String postCode, String mobile, String tel, Integer contryId,
			String contryName, Integer provinceId, String provinceName,
			Integer cityId, String cityName, String adress, Integer bestDeliveryTime,
			String storeId,String code, Date createTime, Date modifyTime,
			Integer modifyUserId, String modifyUserName, String atrr,
			String atrr1, String atrr2, String atrr3) {
		this.orderId = orderId;
		this.relCode = relCode;
		this.relName = relName;
		this.postCode = postCode;
		this.mobile = mobile;
		this.tel = tel;
		this.contryId = contryId;
		this.contryName = contryName;
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.cityId = cityId;
		this.cityName = cityName;
		this.adress = adress;
		this.storeId = storeId;
		this.code = code;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
		this.atrr = atrr;
		this.atrr1 = atrr1;
		this.atrr2 = atrr2;
		this.atrr3 = atrr3;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRelCode() {
		return this.relCode;
	}

	public void setRelCode(String relCode) {
		this.relCode = relCode;
	}

	public String getRelName() {
		return this.relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getContryId() {
		return this.contryId;
	}

	public void setContryId(Integer contryId) {
		this.contryId = contryId;
	}

	public String getContryName() {
		return this.contryName;
	}

	public void setContryName(String contryName) {
		this.contryName = contryName;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public Integer getBestDeliveryTime() {
		return bestDeliveryTime;
	}

	public void setBestDeliveryTime(Integer bestDeliveryTime) {
		this.bestDeliveryTime = bestDeliveryTime;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "LvOrderAddress [id=" + id + ", orderId=" + orderId
				+ ", relCode=" + relCode + ", relName=" + relName
				+ ", postCode=" + postCode + ", mobile=" + mobile + ", tel="
				+ tel + ", contryId=" + contryId + ", contryName=" + contryName
				+ ", provinceId=" + provinceId + ", provinceName="
				+ provinceName + ", cityId=" + cityId + ", cityName="
				+ cityName + ", adress=" + adress + ", bestDeliveryTime="
				+ bestDeliveryTime + ", storeId=" + storeId + ", code=" + code
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", modifyUserId=" + modifyUserId + ", modifyUserName="
				+ modifyUserName + ", atrr=" + atrr + ", atrr1=" + atrr1
				+ ", atrr2=" + atrr2 + ", atrr3=" + atrr3 + "]";
	}

}