package com.lshop.web.weixin;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.send.NewsArticlesItem;
import com.lshop.web.weixin.message.send.NewsMessage;
import com.lshop.web.weixin.util.JAXBUtil;

/**
 * 基本消息测试
 * @author jinjian 2014-12-26
 *
 */
public class BaseMessageTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void convertToXml(){
		BaseMessage bm = new BaseMessage();
		bm.setFromUserName("fromUser1");
		bm.setCreateTime(888L);
		bm.setMsgType("news");
		bm.setToUserName("toUser1");
		String s = JAXBUtil.convertToXml(bm);
		System.out.println(s);
	}
	
	@Test
	public void convertToJavaBean(){
		String xml = "<xml><ToUserName><![CDATA[toUser1]]></ToUserName><FromUserName><![CDATA[fromUser1]]></FromUserName><CreateTime>888</CreateTime><MsgType><![CDATA[news]]></MsgType></xml>";
		BaseMessage bm = JAXBUtil.convertToJavaBean(xml, BaseMessage.class);
		Assert.assertEquals("fromUser1", bm.getFromUserName());
	}	

	@After
	public void tearDown() throws Exception {
	}

}
