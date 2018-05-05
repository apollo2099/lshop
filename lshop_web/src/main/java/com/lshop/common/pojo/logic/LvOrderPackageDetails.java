package com.lshop.common.pojo.logic;

import java.sql.Timestamp;
import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvOrderPackageDetails entity. @author MyEclipse Persistence Tools
 */

public class LvOrderPackageDetails extends
		com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderDetailsCode;
	private String couponCode;
	private Integer pnum;
	private String productCode;
	private Float couponPrice;
	private String pcode;
	private String currency;
	private String code;
	private Float oprice;
	private Date modifyTime;
	private Date createTime;
	private Integer modifyUserId;
	private String modifyUserName;
	private String atrr;
	private String atrr1;
	private String atrr2;
	private String atrr3;

	// Constructors

	/** default constructor */
	public LvOrderPackageDetails() {
	}

	/** minimal constructor */
	public LvOrderPackageDetails(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public LvOrderPackageDetails(Integer id, String orderDetailsCode,
			String couponCode, Integer pnum, String productCode,
			Float couponPrice, String pcode, String code, Date modifyTime,
			Date createTime, Integer modifyUserId, String modifyUserName,
			String atrr, String atrr1, String atrr2, String atrr3) {
		this.id = id;
		this.orderDetailsCode = orderDetailsCode;
		this.couponCode = couponCode;
		this.pnum = pnum;
		this.productCode = productCode;
		this.couponPrice = couponPrice;
		this.pcode = pcode;
		this.code = code;
		this.modifyTime = modifyTime;
		this.createTime = createTime;
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
	public Float getOprice() {
		return oprice;
	}

	public void setOprice(Float oprice) {
		this.oprice = oprice;
	}

	public String getOrderDetailsCode() {
		return this.orderDetailsCode;
	}

	public void setOrderDetailsCode(String orderDetailsCode) {
		this.orderDetailsCode = orderDetailsCode;
	}

	public String getCouponCode() {
		return this.couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getPnum() {
		return this.pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Float getCouponPrice() {
		return this.couponPrice;
	}

	public void setCouponPrice(Float couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getPcode() {
		return this.pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "LvOrderPackageDetails [id=" + id + ", orderDetailsCode="
				+ orderDetailsCode + ", couponCode=" + couponCode + ", pnum="
				+ pnum + ", productCode=" + productCode + ", couponPrice="
				+ couponPrice + ", pcode=" + pcode + ", currency=" + currency
				+ ", code=" + code + ", oprice=" + oprice + ", modifyTime="
				+ modifyTime + ", createTime=" + createTime + ", modifyUserId="
				+ modifyUserId + ", modifyUserName=" + modifyUserName
				+ ", atrr=" + atrr + ", atrr1=" + atrr1 + ", atrr2=" + atrr2
				+ ", atrr3=" + atrr3 + "]";
	}

}