package com.lshop.web.product.vo;

import java.io.Serializable;

/**
 * 橱窗商品视图
 * @author caicl
 *
 */
public class ProdShowVo implements Serializable{

	private static final long serialVersionUID = -1490081471980182535L;
	/**
	 * 商品号
	 */
	private String productNo;
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
	 * 原价价格展示
	 */
	private String priceShow;
	/**
	 * 活动价格展示
	 */
	private String activityPriceShow;
	public ProdShowVo() {
		super();
	}
	public ProdShowVo(String productNo, int type, Boolean isGift,
			String currency, String priceShow, String activityPriceShow) {
		super();
		this.productNo = productNo;
		this.type = type;
		this.isGift = isGift;
		this.currency = currency;
		this.priceShow = priceShow;
		this.activityPriceShow = activityPriceShow;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
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
	public String getPriceShow() {
		return priceShow;
	}
	public void setPriceShow(String priceShow) {
		this.priceShow = priceShow;
	}
	public String getActivityPriceShow() {
		return activityPriceShow;
	}
	public void setActivityPriceShow(String activityPriceShow) {
		this.activityPriceShow = activityPriceShow;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
