package com.lshop.ws.server.bz.order.bean;

public class LvProductDto {

	private String pcode;  // 产品型号Code
	private float oprice;  // 单价
	private int punm;      // 数量
	private String coupons;// 优惠码
	private float specialprice;// 优惠金额

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public float getOprice() {
		return oprice;
	}

	public void setOprice(float oprice) {
		this.oprice = oprice;
	}

	public int getPunm() {
		return punm;
	}

	public void setPunm(int punm) {
		this.punm = punm;
	}

	public String getCoupons() {
		return coupons;
	}

	public void setCoupons(String coupons) {
		this.coupons = coupons;
	}

	public float getSpecialprice() {
		return specialprice;
	}

	public void setSpecialprice(float specialprice) {
		this.specialprice = specialprice;
	}
}
