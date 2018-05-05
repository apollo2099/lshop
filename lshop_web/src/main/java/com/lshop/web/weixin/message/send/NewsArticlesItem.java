package com.lshop.web.weixin.message.send;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.lshop.web.weixin.message.CDATAdapter;

/**
 * 图文消息
 * @author jinjian 2014-12-25
 *
 */
@XmlRootElement
@XmlType(propOrder = {"title", "description", "picUrl", "url"})
public class NewsArticlesItem {
	private String title;
	private String description;
	private String picUrl;
	private String url;
	
	public String getTitle() {
		return title;
	}
	
	@XmlElement(name = "Title")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name = "Description")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	@XmlElement(name = "PicUrl")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	@XmlElement(name = "Url")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setUrl(String url) {
		this.url = url;
	}
}
