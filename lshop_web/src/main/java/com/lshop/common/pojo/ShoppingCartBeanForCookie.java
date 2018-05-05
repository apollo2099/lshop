package com.lshop.common.pojo;

/**
 * 购物车cookie对象
 * @author zhengxue
 * @since 2.0 2012-08-21
 *
 */
public class ShoppingCartBeanForCookie {

	private String storeId; //产品所在店铺标志
	private String code; //产品code
	private int shopNum; //产品数量
	private float shopPrice; //产品最终价格
	
	public ShoppingCartBeanForCookie(){
	}
	
	public ShoppingCartBeanForCookie(String storeId, String code, int shopNum,
			float shopPrice) {
		super();
		this.storeId = storeId;
		this.code = code;
		this.shopNum = shopNum;
		this.shopPrice = shopPrice;
	}

	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public int getShopNum() {
		return shopNum;
	}

	public void setShopNum(int shopNum) {
		this.shopNum = shopNum;
	}

	public float getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(float shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
