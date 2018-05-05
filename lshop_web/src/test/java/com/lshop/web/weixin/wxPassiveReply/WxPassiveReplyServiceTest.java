package com.lshop.web.weixin.wxPassiveReply;

import java.util.Calendar;

import javax.annotation.Resource;
import org.junit.Test;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.gv.test.BaseTest;
import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxObtainNewsTpl;
import com.lshop.web.weixin.common.pojo.WxPassiveReply;
import com.lshop.web.weixin.message.BaseEventMessage;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.util.JAXBUtil;
import com.lshop.web.weixin.wxNewsMaterial.service.WxNewsMaterialService;
import com.lshop.web.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;
import com.lshop.web.weixin.wxPassiveReply.service.WxPassiveReplyService;
import com.lshop.web.weixin.wxTextMaterial.service.WxTextMaterialService;

public class WxPassiveReplyServiceTest extends BaseTest{
	@Resource WxPassiveReplyService service;
	@Resource WxNewsMaterialService newsMaterialService;
	@Resource WxTextMaterialService textMaterialService;

	@Test
	public void save() {
		WxPassiveReply wpr = new WxPassiveReply();
		wpr.setCreateTime(Calendar.getInstance().getTime());
		wpr.setDescription("蛋撻小王子ߌ");
		wpr.setMaterialId(1);
		wpr.setMaterialType(2);
		wpr.setWxhConfigId(1);
		BaseDto dto = new BaseDto();
		dto.put("model", wpr);
		service.save(dto);
	}	
	
	/**
	 * 测试 CoreServlet 关注公众号流程
	 */
	public void test1() {
		WxGzhConfig gzhConfig = new WxGzhConfig();
		gzhConfig.setId(1);
		String receiveXml = "<xml><ToUserName><![CDATA[gh_fa04fb4d39f5]]></ToUserName><FromUserName><![CDATA[oTqsQuKmNOhycpXRVK3P1mP1vnSs]]></FromUserName><CreateTime>1420684305</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>";
		StringBuffer sb = new StringBuffer();
		sb.append(receiveXml);
		BaseMessage bm = JAXBUtil.convertToJavaBean(sb.toString(), BaseMessage.class);
		
		BaseEventMessage baseEventMessage = JAXBUtil.convertToJavaBean(sb.toString(), BaseEventMessage.class);
		if (WxConstant.Event_subscribe.equals(baseEventMessage.getEvent())) {//关注公众号事件
			WxPassiveReply passiveReply = service.getByWxh(gzhConfig.getId());					
			 String xml = this.getMaterialXml(gzhConfig.getId(), passiveReply.getMaterialType(), passiveReply.getMaterialId(), bm);
			 System.out.println("==============xm:"+xml);
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
