package com.lshop.web.shopCart.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvShopCart;

public class ShopCartVo {
	private String storeId;
	private List<ShopCartItem> items;
	private int totalNum;//合计数量
	private float totalAmount;//合计金额
	public ShopCartVo() {
		super();
	}
	public ShopCartVo(String storeId, List<ShopCartItem> items, int totalNum,
			float totalAmount) {
		super();
		this.storeId = storeId;
		this.items = items;
		this.totalNum = totalNum;
		this.totalAmount = totalAmount;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public List<ShopCartItem> getItems() {
		return items;
	}
	public void setItems(List<ShopCartItem> items) {
		this.items = items;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 返回视图中的登陆购物车列表
	 * @return
	 */
	public List<LvShopCart> getLvShopCarts() {
		List<LvShopCart> shopCarts = null;
		if (ObjectUtils.isNotEmpty(items)) {
			shopCarts = new ArrayList<LvShopCart>();
			for (Iterator<ShopCartItem> iterator = items.iterator(); iterator.hasNext();) {
				ShopCartItem it = iterator.next();
				if (ObjectUtils.isNotEmpty(it.getShopCart())) {
					shopCarts.add(it.getShopCart());
				}
			}
		}
		return shopCarts;
	}
	/**
	 * 把视图转成缓存存储
	 * @return
	 */
	public Set<ShopCartCache> getCaches(){
		Set<ShopCartCache> caches = null;
		if (ObjectUtils.isNotEmpty(items)) {
			caches = new HashSet<ShopCartCache>();
			for (Iterator<ShopCartItem> iterator = items.iterator(); iterator.hasNext();) {
				ShopCartItem it = iterator.next();
				if (ObjectUtils.isNotEmpty(it.getShopCart())) {
					LvShopCart cart = it.getShopCart();
					caches.add(new ShopCartCache(cart.getProductCode(), cart.getStoreId(), cart.getShopNum(), cart.getCode()));
				}
			}
		}
		return caches;
	}
}
