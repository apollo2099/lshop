package com.lshop.common.activity.vo;

import java.util.Set;

public class OrderActivity {
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 店铺标识
	 */
	private String storeId;
	/**
	 * 订单总金额
	 */
	private Float dAmount;
	/**
	 * 订单商品
	 */
	private Set<String> productSet;

	public OrderActivity() {
		super();
	}

	public OrderActivity(String orderNo, String storeId, Float dAmount, Set<String> productSet) {
		super();
		this.orderNo = orderNo;
		this.storeId = storeId;
		this.dAmount = dAmount;
		this.productSet = productSet;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Float getdAmount() {
		return dAmount;
	}

	public void setdAmount(Float dAmount) {
		this.dAmount = dAmount;
	}

	public Set<String> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<String> productSet) {
		this.productSet = productSet;
	}

}
