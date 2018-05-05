package com.lshop.web.weixin.wxKeywordsReply;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import org.junit.Test;

import com.gv.core.proxy.ServiceConstants;
import com.gv.test.BaseTest;
import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxKeywordsReply;
import com.lshop.web.weixin.common.pojo.WxObtainNewsTpl;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.send.TextMessage;
import com.lshop.web.weixin.util.JAXBUtil;
import com.lshop.web.weixin.wxKeywordsReply.service.WxKeywordsReplyService;
import com.lshop.web.weixin.wxNewsMaterial.service.WxNewsMaterialService;
import com.lshop.web.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;
import com.lshop.web.weixin.wxTextMaterial.service.WxTextMaterialService;

public class WxKeywordsReplyServiceTest extends BaseTest{
	@Resource WxKeywordsReplyService keywordsReplyService;
	@Resource WxObtainNewsTplService obtainNewsTplService;
	@Resource WxNewsMaterialService newsMaterialService;
	@Resource WxTextMaterialService textMaterialService;

	@Test
	public void getByKeyword() {
		WxKeywordsReply reply = keywordsReplyService.getByKeyword(1,"10");
		System.out.println(reply.getName());
	}

	/**
	 * 测试 CoreServlet 关键词回复流程
	 */	
	@Test
	public void test1() {
		WxGzhConfig gzhConfig = new WxGzhConfig();
		gzhConfig.setId(1);
		String receiveXml = "<xml><ToUserName><![CDATA[gh_fa04fb4d39f5]]></ToUserName><FromUserName><![CDATA[oTqsQuKmNOhycpXRVK3P1mP1vnSs]]></FromUserName><CreateTime>1420701035</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[10]]></Content><MsgId>6101864482923436375</MsgId></xml>";
		StringBuffer sb = new StringBuffer();
		sb.append(receiveXml);
		BaseMessage bm = JAXBUtil.convertToJavaBean(sb.toString(), BaseMessage.class);
		
		String xml = null;
		TextMessage textMessage = JAXBUtil.convertToJavaBean(sb.toString(), TextMessage.class);
		String content = textMessage.getContent();
		WxObtainNewsTpl obtainNesTpl = obtainNewsTplService.getByWxhConfigId(gzhConfig.getId());
		//判断是否有领取活动
		boolean startObtainNews = true;
		if (obtainNesTpl == null) {
			startObtainNews = false;
		} else {
			Date endTime = obtainNesTpl.getEndTime();
			Calendar nowCal = Calendar.getInstance();	
			if (endTime.getTime() < nowCal.getTimeInMillis()) {
				startObtainNews = false;
			}
		}
		//1.领取活动
		if (startObtainNews) {
		} else {
			 WxKeywordsReply keywordsReply = keywordsReplyService.getByKeyword(gzhConfig.getId(), content);
			if (keywordsReply != null) {//2.关键词被动回复
				xml = this.getMaterialXml(gzhConfig.getId(), keywordsReply.getMaterialType(), keywordsReply.getMaterialId(), bm);
			} else {// 转发至多客服系统
				BaseMessage bmCustomer = new BaseMessage();
				bmCustomer.setCreateTime(bm.getCreateTime());
				bmCustomer.setFromUserName(bm.getToUserName());
				bmCustomer.setToUserName(bm.getFromUserName());
				bmCustomer.setMsgType("transfer_customer_service");
				xml = JAXBUtil.convertToXml(bmCustomer);
			}			
		}
		System.out.println("xml==="+xml);
	}
	
	/**
	 * 获取素材xml
	 * @param wxhConfigId
	 * @param materialType
	 * @param materialId
	 * @param textMessage
	 * @return
	 */
	private String getMaterialXml(int wxhConfigId, int materialType, int materialId, BaseMessage baseMessage) {
		String xml = null;
		if (WxConstant.material_type_news == materialType) { // 2.1回复图文消息
			xml = newsMaterialService.getNewsMessageXml(wxhConfigId, materialId, baseMessage);
		} else if (WxConstant.material_type_text == materialType) { // 2.2回复文本消息
			xml = textMaterialService.getTextMessageXml(wxhConfigId, materialId, baseMessage);
		}
		return xml;
	}	
}
