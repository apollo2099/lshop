package com.lshop.common.activity.vo;

public class StoreAmount {
	/**
	 * 店铺标识
	 */
	private String storeId;
	/**
	 * 总金额
	 */
	private Float amount;

	public StoreAmount() {
		super();
	}

	public StoreAmount(String storeId, Float amount) {
		super();
		this.storeId = storeId;
		this.amount = amount;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
