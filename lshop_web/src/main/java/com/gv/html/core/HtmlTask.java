package com.gv.html.core;

/**
 * 封闭静态任务参数
 * @author caicl
 *
 */
public class HtmlTask {
	private String dynamicUrl;
	private String htmlfile;
	private String name;
	
	public HtmlTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HtmlTask(String name) {
		super();
		this.name = name;
	}
	public String getDynamicUrl() {
		return dynamicUrl;
	}
	public void setDynamicUrl(String dynamicUrl) {
		this.dynamicUrl = dynamicUrl;
	}
	public String getHtmlfile() {
		return htmlfile;
	}
	public void setHtmlfile(String htmlfile) {
		this.htmlfile = htmlfile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
