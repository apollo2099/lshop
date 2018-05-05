package com.lshop.common.pojo.logic;

import com.gv.core.datastructure.Key;

/**
 * LvAccountPhysicalTicket entity. @author MyEclipse Persistence Tools
 */

public class LvAccountPhysicalTicket extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accountPrizeCode;
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

	// Constructors

	/** default constructor */
	public LvAccountPhysicalTicket() {
	}

	/** full constructor */
	public LvAccountPhysicalTicket(String accountPrizeCode, 
			String relCode, String relName, String postCode, String mobile,
			String tel, Integer contryId, String contryName,
			Integer provinceId, String provinceName, Integer cityId,
			String cityName, String adress) {
		this.accountPrizeCode = accountPrizeCode;
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
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountPrizeCode() {
		return this.accountPrizeCode;
	}

	public void setAccountPrizeCode(String accountPrizeCode) {
		this.accountPrizeCode = accountPrizeCode;
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

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}