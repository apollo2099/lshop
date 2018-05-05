package com.lshop.ws.server.bz.order.bean;

import java.util.Date;
import java.util.List;

public class LvOrderDto {
	private String oid;
	private String relname;
	private String contryId;
	private String contryname;
	private String provincename;
	private String cityname;
	private String adress;
	private String postcode;
	private String tel;
	private String mobile;
	private String email;
	private String postage;
	private float totalPrice;
	private Integer paymethod;
	private Date overtime;
	private String paynumber;
	private String source;
	private Date sendtime;
	private String oremark;
	private Date createtime;
	private String sendremark;
	private String description;
	private String currency;
	private List<LvProductDto> products;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getContryId() {
		return contryId;
	}

	public void setContryId(String contryId) {
		this.contryId = contryId;
	}

	public String getContryname() {
		return contryname;
	}

	public void setContryname(String contryname) {
		this.contryname = contryname;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(Integer paymethod) {
		this.paymethod = paymethod;
	}

	public Date getOvertime() {
		return overtime;
	}

	public void setOvertime(Date overtime) {
		this.overtime = overtime;
	}

	public String getPaynumber() {
		return paynumber;
	}

	public void setPaynumber(String paynumber) {
		this.paynumber = paynumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public String getOremark() {
		return oremark;
	}

	public void setOremark(String oremark) {
		this.oremark = oremark;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getSendremark() {
		return sendremark;
	}

	public void setSendremark(String sendremark) {
		this.sendremark = sendremark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<LvProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<LvProductDto> products) {
		this.products = products;
	}
}
