package com.lshop.web.shopCart.vo;

import java.io.Serializable;

/**
 * 购物车缓存
 * @author caicl
 *
 */
public class ShopCartCache implements Serializable{

	private static final long serialVersionUID = 2862286460182668776L;
	private String pcode;
	private String storeId;
	private int num;
	private String code;
	
	public ShopCartCache() {
		super();
	}
	public ShopCartCache(String pcode, String storeId, int num, String code) {
		super();
		this.pcode = pcode;
		this.storeId = storeId;
		this.num = num;
		this.code = code;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pcode == null) ? 0 : pcode.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopCartCache other = (ShopCartCache) obj;
		if (pcode == null) {
			if (other.pcode != null)
				return false;
		} else if (!pcode.equals(other.pcode))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		return true;
	}
	
}
