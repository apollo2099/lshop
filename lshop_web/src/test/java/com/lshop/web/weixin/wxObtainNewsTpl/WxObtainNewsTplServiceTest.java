package com.lshop.web.weixin.wxObtainNewsTpl;

import javax.annotation.Resource;
import org.junit.Test;

import com.gv.core.proxy.ServiceConstants;
import com.gv.test.BaseTest;
import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxKeywordsReply;
import com.lshop.web.weixin.common.pojo.WxObtainNewsTpl;
import com.lshop.web.weixin.common.pojo.WxPassiveReply;
import com.lshop.web.weixin.message.BaseEventMessage;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.send.TextMessage;
import com.lshop.web.weixin.util.JAXBUtil;
import com.lshop.web.weixin.wxKeywordsReply.service.WxKeywordsReplyService;
import com.lshop.web.weixin.wxNewsMaterial.service.WxNewsMaterialService;
import com.lshop.web.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;
import com.lshop.web.weixin.wxTextMaterial.service.WxTextMaterialService;
import com.lshop.web.weixin.wxUser.service.WxUserService;

public class WxObtainNewsTplServiceTest extends BaseTest{
	@Resource WxObtainNewsTplService service;
	@Resource WxNewsMaterialService newsMaterialService;
	@Resource WxTextMaterialService textMaterialService;
	@Resource WxKeywordsReplyService keywordsReplyService;
	@Resource WxUserService userService;

	@Test
	public void getByWxhConfigId() {
		WxObtainNewsTpl tpl = service.getByWxhConfigId(100);
		if (tpl == null) {
			System.out.println("==============tpl null");
		} else {
			System.out.println(tpl.getPushKeyword());			
		}
	}

	/**
	 * 测试 CoreServlet 领取活动流程
	 */	
	public void test1() {
		WxGzhConfig gzhConfig = new WxGzhConfig();
		gzhConfig.setId(1);
		String receiveXml = "<xml><ToUserName><![CDATA[gh_fa04fb4d39f5]]></ToUserName><FromUserName><![CDATA[oTqsQuKmNOhycpXRVK3P1mP1vnSs]]></FromUserName><CreateTime>1420684200</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[11]]></Content><MsgId>6101792177148998152</MsgId></xml>";
		StringBuffer sb = new StringBuffer();
		sb.append(receiveXml);
		BaseMessage baseMessage = JAXBUtil.convertToJavaBean(sb.toString(), BaseMessage.class);
		
		String xml = null;
		TextMessage textMessage = JAXBUtil.convertToJavaBean(sb.toString(), TextMessage.class);
		String content = textMessage.getContent();
		WxObtainNewsTpl obtainNesTpl = service.getByWxhConfigId(gzhConfig.getId());
		//1.领取活动
		if (obtainNesTpl != null) {
			 if (content.equals(obtainNesTpl.getPushKeyword())) {//1.1推送消息
				 xml = newsMaterialService.getNewsMessageXml(gzhConfig.getId(), obtainNesTpl.getNewsId(), baseMessage);
			 } else if (content.equals(obtainNesTpl.getQueryKeyword())) {//1.2查询领取详情
				 xml = userService.getTextMessageXml(gzhConfig.getId(), baseMessage);
			 } else {
				 xml = this.getKeywordsReplyXml(gzhConfig.getId(), content, baseMessage);
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
	
	/**
	 * 获取关键词回复xml
	 * @param wxhConfigId
	 * @param keyword
	 * @param baseMessage
	 * @return
	 */
	private String getKeywordsReplyXml(int wxhConfigId, String keyword, BaseMessage baseMessage) {
		String xml = null;
		 WxKeywordsReply keywordsReply = keywordsReplyService.getByKeyword(wxhConfigId, keyword);
		if (keywordsReply != null) {//2.关键词被动回复
			xml = this.getMaterialXml(wxhConfigId, keywordsReply.getMaterialType(), keywordsReply.getMaterialId(), baseMessage);
		} else {// 转发至多客服系统
			xml = this.getTransferCustomerXml(baseMessage);
		}
		return xml;
	}
	
	/**
	 * 获取多客服xml
	 * @param baseMessage
	 * @return
	 */
	private String getTransferCustomerXml(BaseMessage baseMessage){
		BaseMessage bmCustomer = new BaseMessage();
		bmCustomer.setCreateTime(baseMessage.getCreateTime());
		bmCustomer.setFromUserName(baseMessage.getToUserName());
		bmCustomer.setToUserName(baseMessage.getFromUserName());
		bmCustomer.setMsgType("transfer_customer_service");
		String xml = JAXBUtil.convertToXml(bmCustomer);
		return xml;
	}	
}
