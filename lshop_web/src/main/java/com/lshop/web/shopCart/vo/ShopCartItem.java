package com.lshop.web.shopCart.vo;

import java.io.Serializable;

import com.lshop.common.pojo.ShoppingCartBeanForCookie;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvShopCart;

/**
 * 购物车商品项
 * @author caicl
 *
 */
public class ShopCartItem implements Serializable{
	private static final long serialVersionUID = 5245892274506225255L;
	private LvShopCart shopCart;//登陆后购物车
	private ShoppingCartBeanForCookie cookie;//登陆前购物车
	private LvProduct product;
	private float sum;//小计
	
	public ShopCartItem() {
		super();
	}
	public ShopCartItem(LvShopCart shopCart, LvProduct product, float sum) {
		super();
		this.shopCart = shopCart;
		this.product = product;
		this.sum = sum;
	}
	public ShopCartItem(ShoppingCartBeanForCookie cookie, LvProduct product,
			float sum) {
		super();
		this.cookie = cookie;
		this.product = product;
		this.sum = sum;
	}
	public ShoppingCartBeanForCookie getCookie() {
		return cookie;
	}
	public void setCookie(ShoppingCartBeanForCookie cookie) {
		this.cookie = cookie;
	}
	public LvShopCart getShopCart() {
		return shopCart;
	}
	public void setShopCart(LvShopCart shopCart) {
		this.shopCart = shopCart;
	}
	public LvProduct getProduct() {
		return product;
	}
	public void setProduct(LvProduct product) {
		this.product = product;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
}
