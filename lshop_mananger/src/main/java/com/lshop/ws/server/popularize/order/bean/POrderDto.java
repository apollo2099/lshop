package com.lshop.ws.server.popularize.order.bean;

import java.util.List;

public class POrderDto {

	private String orderNo;// 订单号
	private String currency;// 订单币种
	private String payTime; // 支付时间
	private String createTime;// 订单生成时间
	private Integer status; // 状态：1已支付，正常订单,2已退货或已经删除，
	private List<PProductDto> products;
	private List<PCouponDto> coupons;// 优惠券

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<PProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<PProductDto> products) {
		this.products = products;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<PCouponDto> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<PCouponDto> coupons) {
		this.coupons = coupons;
	}

}
