package com.lshop.common.coupon.vo;

public class KeyValueVo {
	private String key;
	private String value;

	public KeyValueVo() {
		super();
	}

	public KeyValueVo(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
