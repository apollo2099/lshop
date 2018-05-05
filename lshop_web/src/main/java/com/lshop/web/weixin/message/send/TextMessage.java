package com.lshop.web.weixin.message.send;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.lshop.web.weixin.message.CDATAdapter;

/**
 * 文本消息
 * @author jinjian 2014-12-25
 *
 */
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"toUserName", "fromUserName", "createTime", "msgType", "content"})
public class TextMessage {
	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private String content;

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

	public String getContent() {
		return content;
	}

	@XmlElement(name = "Content")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setContent(String content) {
		this.content = content;
	}

}
