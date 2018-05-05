package com.lshop.web.weixin.wxMenu;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.test.BaseTest;
import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxMenu;
import com.lshop.web.weixin.message.BaseEventMessage;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.receive.ClickEventMessage;
import com.lshop.web.weixin.util.JAXBUtil;
import com.lshop.web.weixin.wxMenu.service.WxMenuService;
import com.lshop.web.weixin.wxNewsMaterial.service.WxNewsMaterialService;
import com.lshop.web.weixin.wxTextMaterial.service.WxTextMaterialService;

public class WxMenuServiceTest extends BaseTest{
	@Resource WxMenuService service;
	@Resource WxNewsMaterialService newsMaterialService;
	@Resource WxTextMaterialService textMaterialService;
	
	/**
	 * 测试 CoreServlet 关注公众号流程
	 */
	@Test
	public void test1() {
		WxGzhConfig gzhConfig = new WxGzhConfig();
		gzhConfig.setId(2);
		String receiveXml = "<xml><ToUserName><![CDATA[gh_fa04fb4d39f5]]></ToUserName><FromUserName><![CDATA[oTqsQuKmNOhycpXRVK3P1mP1vnSs]]></FromUserName><CreateTime>1421822580</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[menu_new01]]></EventKey></xml>";
		StringBuffer sb = new StringBuffer();
		sb.append(receiveXml);
		BaseMessage baseMessage = JAXBUtil.convertToJavaBean(sb.toString(), BaseMessage.class);
		
		BaseEventMessage baseEventMessage = JAXBUtil.convertToJavaBean(sb.toString(), BaseEventMessage.class);
		if (WxConstant.Event_click.equals(baseEventMessage.getEvent().toLowerCase())) {//关注公众号事件
			ClickEventMessage clickEventMessage = JAXBUtil.convertToJavaBean(sb.toString(), ClickEventMessage.class);
			String eventKey = clickEventMessage.getEventKey();
			WxMenu wxMenu = service.getByMenuKey(gzhConfig.getId(), eventKey);
			if (WxConstant.Event_click.equals(wxMenu.getMenuType())) {
				 String xml = this.getMaterialXml(gzhConfig.getId(), wxMenu.getMaterialType(), wxMenu.getMaterialId(), baseMessage);
				 System.out.println(xml);
			}
		}
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
