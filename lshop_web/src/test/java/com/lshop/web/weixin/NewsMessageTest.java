package com.lshop.web.weixin;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lshop.web.weixin.message.send.NewsArticlesItem;
import com.lshop.web.weixin.message.send.NewsMessage;
import com.lshop.web.weixin.util.JAXBUtil;

/**
 * 图文消息测试
 * @author jinjian 2014-12-25
 *
 */
public class NewsMessageTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void convertToXml(){
		NewsMessage nm = new NewsMessage();
		nm.setFromUserName("fromUser1");
		nm.setCreateTime(888L);
		nm.setMsgType("news");
		nm.setToUserName("toUser1");
		nm.setArticleCount(1);
		
		List<NewsArticlesItem> itemList = new ArrayList<NewsArticlesItem>();
		NewsArticlesItem nai = new NewsArticlesItem();
		nai.setDescription("des1");
		nai.setPicUrl("http://img0.imgtn.bdimg.com/it/u=1187579687,1554146131&fm=21&gp=0.jpg");
		nai.setUrl("u1");
		nai.setTitle("t1");
		itemList.add(nai);
		nm.setItemList(itemList);
		
		String s = JAXBUtil.convertToXml(nm);
		System.out.println(s);
	}
	
	@Test
	public void convertToJavaBean(){
		String xml = "<xml><ToUserName><![CDATA[toUser1]]></ToUserName><FromUserName><![CDATA[fromUser1]]></FromUserName><CreateTime>888</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[t1]]></Title><Description><![CDATA[des1]]></Description><PicUrl><![CDATA[http://img0.imgtn.bdimg.com/it/u=1187579687,1554146131&fm=21&gp=0.jpg]]></PicUrl><Url><![CDATA[u1]]></Url></item></Articles></xml>";
		NewsMessage nm = JAXBUtil.convertToJavaBean(xml, NewsMessage.class);
		Assert.assertEquals("fromUser1", nm.getFromUserName());
	}	
	
	@Test
	public void convertToJavaBean0(){
/*		String xml = "<xml><ToUserName>oTqsQuKmNOhycpXRVK3P1mP1vnSs</ToUserName><FromUserName>gh_fa04fb4d39f5</FromUserName><CreateTime>145922222</CreateTime><MsgType>news</MsgType>"+
	    	"<ArticleCount>1</ArticleCount><Acticles><item><Description>des1</Description><PicUrl>http://img0.imgtn.bdimg.com/it/u=1187579687,1554146131&fm=21&gp=0.jpg</PicUrl><Title>t1</Title><Url>http://www.baidu.com/</Url></item></Acticles></xml>";
		NewsMessage nm = JAXBUtil.convertToJavaBean(xml, NewsMessage.class);
		Assert.assertEquals("fromUser1", nm.getFromUserName());*/
	}	

	@After
	public void tearDown() throws Exception {
	}

}
