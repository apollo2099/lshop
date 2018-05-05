package com.lshop.web.weixin.message.send;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.lshop.web.weixin.message.CDATAdapter;

/**
 * 图文消息
 * @author jinjian 2014-12-24
 *
 */
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"toUserName", "fromUserName", "createTime", "msgType", "articleCount","itemList"})
public class NewsMessage {
	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private int articleCount;
	private List<NewsArticlesItem> itemList;	

	public String getToUserName() {
		return toUserName;
	}
	

	@XmlElement(name = "ToUserName")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}
	
	@XmlElement(name = "FromUserName")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	@XmlElement(name = "CreateTime")
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	@XmlElement(name = "MsgType")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public int getArticleCount() {
		return articleCount;
	}

	@XmlElement(name = "ArticleCount")
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}


	public List<NewsArticlesItem> getItemList() {
		return itemList;
	}

    @XmlElementWrapper(name = "Articles")
    @XmlElement(name = "item")	
	public void setItemList(List<NewsArticlesItem> itemList) {
		this.itemList = itemList;
	}
}
