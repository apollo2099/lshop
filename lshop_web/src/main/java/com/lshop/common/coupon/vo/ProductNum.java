package com.lshop.common.coupon.vo;

public class ProductNum {
	/**
	 * 商品编码
	 */
	private String productNo;
	/**
	 * 数量
	 */
	private Integer num;

	public ProductNum() {
		super();
	}

	public ProductNum(String productNo, Integer num) {
		super();
		this.productNo = productNo;
		this.num = num;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
