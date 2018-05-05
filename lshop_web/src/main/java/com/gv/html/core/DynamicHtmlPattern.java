package com.gv.html.core;

/**
 * 动态地址模板类
 * @author caicl
 *
 */
public class DynamicHtmlPattern {
	private String duri;
	private String huri;
	private String partten;
	public DynamicHtmlPattern() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DynamicHtmlPattern(String duri, String huri, String partten) {
		super();
		this.duri = duri;
		this.huri = huri;
		this.partten = partten;
	}
	public String getDuri() {
		return duri;
	}
	public void setDuri(String duri) {
		this.duri = duri;
	}
	public String getHuri() {
		return huri;
	}
	public void setHuri(String huri) {
		this.huri = huri;
	}
	public String getPartten() {
		return partten;
	}
	public void setPartten(String partten) {
		this.partten = partten;
	}
	
}
