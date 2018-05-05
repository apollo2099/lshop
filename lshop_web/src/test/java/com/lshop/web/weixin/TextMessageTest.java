package com.lshop.web.weixin;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lshop.web.weixin.message.send.TextMessage;
import com.lshop.web.weixin.util.JAXBUtil;

/**
 * 文本消息测试
 * @author jinjian 2014-12-26
 *
 */
public class TextMessageTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void convertToXml(){
		TextMessage msg = new TextMessage();
		msg.setFromUserName("fromUser1");
		msg.setCreateTime(888L);
		msg.setMsgType("news");
		msg.setToUserName("toUser1");
		msg.setContent("content1");
		String s = JAXBUtil.convertToXml(msg);
		System.out.println(s);
	}
	
	@Test
	public void convertToJavaBean(){
		String xml = "<xml><ToUserName><![CDATA[toUser1]]></ToUserName><FromUserName><![CDATA[fromUser1]]></FromUserName><CreateTime>888</CreateTime><MsgType><![CDATA[news]]></MsgType><Content><![CDATA[content1]]></Content></xml>";
		TextMessage msg = JAXBUtil.convertToJavaBean(xml, TextMessage.class);
		Assert.assertEquals("fromUser1", msg.getFromUserName());
	}	

	@After
	public void tearDown() throws Exception {
	}

}
