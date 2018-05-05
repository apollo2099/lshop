package com.lshop.common.coupon.vo;

/**
 * 优惠券规则指定商品类型或者商品条目
 * @author caicl
 *
 */
public class RuleItem {
	private String itemName;
	private String itemStore;
	
	public RuleItem(String itemName, String itemStore) {
		super();
		this.itemName = itemName;
		this.itemStore = itemStore;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemStore() {
		return itemStore;
	}
	public void setItemStore(String itemStore) {
		this.itemStore = itemStore;
	}
	@Override
	public String toString() {
		return "RuleItem [itemName=" + itemName + ", itemStore=" + itemStore
				+ "]";
	}
	
}
