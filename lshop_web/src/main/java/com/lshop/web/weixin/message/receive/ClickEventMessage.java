package com.lshop.web.weixin.message.receive;

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
@XmlType(propOrder = {"toUserName", "fromUserName", "createTime", "msgType", "event", "eventKey"})
public class ClickEventMessage {
	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private String event;
	private String eventKey;

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

	public String getEvent() {
		return event;
	}

	@XmlElement(name = "Event")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	@XmlElement(name = "EventKey")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
