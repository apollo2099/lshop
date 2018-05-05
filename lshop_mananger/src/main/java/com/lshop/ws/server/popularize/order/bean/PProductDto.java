package com.lshop.ws.server.popularize.order.bean;

public class PProductDto {

	private Integer buyNum;  //购买数量
	private float buyPrice; //单价
	private String productNo;     //产品code
	private Integer status;  //状态:用于部分还款（暂时不需要）
	
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public float getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
