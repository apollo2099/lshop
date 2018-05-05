package com.lshop.common.price.vo;

import com.lshop.web.product.vo.ProdCache;

public class ProductPrice {
	/**
	 * 商品编码
	 */
	private String productNo;
	/**
	 * 单价,在售价格
	 */
	private Float price;
	/**
	 * 商品原价
	 */
	private Float plainPrice;
	
	/**
	 * 小计
	 */
	private Float amount;
	/**
	 * 价格类型（0原价，1限时，2限量，3阶梯价）
	 */
	private int type;
	/**
	 * 是否包含赠品
	 */
	private Boolean isGift;
	/**
	 * 币种
	 */
	private String currency;

	/**
	 * 商品信息缓存
	 */
	private ProdCache prodCache;
	
	public ProductPrice() {
		super();
	}

	public ProductPrice(String productNo, Float price,Float plainPrice, Float amount, int type) {
		super();
		this.productNo = productNo;
		this.plainPrice = plainPrice;
		this.price = price;
		this.amount = amount;
		this.type = type;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getPlainPrice() {
		return plainPrice;
	}

	public void setPlainPrice(Float plainPrice) {
		this.plainPrice = plainPrice;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Boolean getIsGift() {
		return isGift;
	}

	public void setIsGift(Boolean isGift) {
		this.isGift = isGift;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public ProdCache getProdCache() {
		return prodCache;
	}

	public void setProdCache(ProdCache prodCache) {
		this.prodCache = prodCache;
	}

}
