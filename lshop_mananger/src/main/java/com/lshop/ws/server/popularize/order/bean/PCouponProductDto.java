package com.lshop.ws.server.popularize.order.bean;

public class PCouponProductDto {
	/**
	 * 商品编码
	 */
	private String productCode;

	public PCouponProductDto() {
		super();
	}

	public PCouponProductDto(String productCode) {
		super();
		this.productCode = productCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

}
