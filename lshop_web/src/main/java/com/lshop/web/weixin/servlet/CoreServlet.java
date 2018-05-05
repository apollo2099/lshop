package com.lshop.web.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.util.Constants;
import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxKeywordsReply;
import com.lshop.web.weixin.common.pojo.WxMenu;
import com.lshop.web.weixin.common.pojo.WxObtainNewsTpl;
import com.lshop.web.weixin.common.pojo.WxPassiveReply;
import com.lshop.web.weixin.message.BaseEventMessage;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.receive.ClickEventMessage;
import com.lshop.web.weixin.message.send.TextMessage;
import com.lshop.web.weixin.util.JAXBUtil;
import com.lshop.web.weixin.wxGzhConfig.service.WxGzhConfigService;
import com.lshop.web.weixin.wxKeywordsReply.service.WxKeywordsReplyService;
import com.lshop.web.weixin.wxMenu.service.WxMenuService;
import com.lshop.web.weixin.wxNewsMaterial.service.WxNewsMaterialService;
import com.lshop.web.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;
import com.lshop.web.weixin.wxPassiveReply.service.WxPassiveReplyService;
import com.lshop.web.weixin.wxTextMaterial.service.WxTextMaterialService;
import com.lshop.web.weixin.wxUser.service.WxUserService;

public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = -2776902810130266533L;
	private static final Log logger	= LogFactory.getLog(CoreServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        // 微信加密签名  
	       String signature = request.getParameter("signature");  
	       // 时间戳  
	       String timestamp = request.getParameter("timestamp");  
	       // 随机数  
	       String nonce = request.getParameter("nonce");  
	       // 随机字符串  
	       String echostr = request.getParameter("echostr");  
	 
	       PrintWriter out = response.getWriter();  
	       out.print(echostr);		
/*		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		String[] str = { TOKEN, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes())
				.toLowerCase();

		System.out.println("digest: " + digest);
		// 确认请求来至微信
		if (digest.equals(signature)) {
			resp.getWriter().print(echostr);
		}*/
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serverName = request.getServerName();
		String storeId = Constants.STORE_IDS.get(serverName.trim());		
		if (storeId == null) {
			logger.error("storeId 公众平台号为空！");
		}
		WxGzhConfigService gzhConfigService = (WxGzhConfigService)ServiceConstants.beanFactory.getBean("WxGzhConfigService");
		WxGzhConfig gzhConfig = gzhConfigService.getByStoreId(storeId);
		if (gzhConfig == null) {
			logger.error("WxGzhConfig公众号配置为空,storeId:"+storeId);			
		}
		Scanner scanner = new Scanner(request.getInputStream());
		response.setContentType("application/xml");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			StringBuffer sb = new StringBuffer(100);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			logger.info("recieve message:"+sb.toString());
			BaseMessage baseMessage = JAXBUtil.convertToJavaBean(sb.toString(), BaseMessage.class);
			if (WxConstant.MsgType_text.equals(baseMessage.getMsgType())) {//接收文本消息
				String xml = null;
				TextMessage textMessage = JAXBUtil.convertToJavaBean(sb.toString(), TextMessage.class);
				String content = textMessage.getContent();
				WxObtainNewsTplService obtainNewsTplService = (WxObtainNewsTplService)ServiceConstants.beanFactory.getBean("WxObtainNewsTplService");
				WxObtainNewsTpl obtainNewsTpl = obtainNewsTplService.getByWxhConfigId(gzhConfig.getId());
				if (obtainNewsTpl == null) {// 转发至多客服系统
					xml = this.getKeywordsReplyXml(gzhConfig.getId(), content, baseMessage);
					logger.info("response message:"+xml);
					printWriter.print(xml);
					printWriter.flush();
					return;
				}
				//判断是否有领取活动
				boolean hasObtainNews = true;
				Date endTime = obtainNewsTpl.getEndTime();
				if (endTime != null) {
					Calendar nowCal = Calendar.getInstance();	
					if (endTime.getTime() < nowCal.getTimeInMillis()) {
						hasObtainNews = false;
					}					
				}
				//1.领取活动
				if (hasObtainNews) {
					 if (content.equals(obtainNewsTpl.getPushKeyword())) {//1.1推送消息
						 WxNewsMaterialService newsMaterialService = (WxNewsMaterialService)ServiceConstants.beanFactory.getBean("WxNewsMaterialService");
						 xml = newsMaterialService.getNewsMessageXml(gzhConfig.getId(), obtainNewsTpl.getNewsId(), baseMessage);
					 } else if (content.equals(obtainNewsTpl.getQueryKeyword())) {//1.2查询领取详情
						 WxUserService userService = (WxUserService)ServiceConstants.beanFactory.getBean("WxUserService");
						 xml = userService.getTextMessageXml(gzhConfig.getId(), baseMessage);
					 } else { //关键词回复
						 xml = this.getKeywordsReplyXml(gzhConfig.getId(), content, baseMessage);
					 }
				} else {
					xml = this.getKeywordsReplyXml(gzhConfig.getId(), content, baseMessage);
				}
				logger.info("response message:"+xml);
				 printWriter.print(xml);
				 printWriter.flush();				
			} else if (WxConstant.MsgType_event.equals(baseMessage.getMsgType())) {//接收事件消息
				BaseEventMessage baseEventMessage = JAXBUtil.convertToJavaBean(sb.toString(), BaseEventMessage.class);
				if (WxConstant.Event_subscribe.equals(baseEventMessage.getEvent().toLowerCase())) {//关注公众号事件
					WxPassiveReplyService passiveReplyService = (WxPassiveReplyService)ServiceConstants.beanFactory.getBean("WxPassiveReplyService");
					WxPassiveReply passiveReply = passiveReplyService.getByWxh(gzhConfig.getId());					
					 String xml = this.getMaterialXml(gzhConfig.getId(), passiveReply.getMaterialType(), passiveReply.getMaterialId(), baseMessage);
					 logger.info("response message:"+xml);
					 printWriter.print(xml);
					 printWriter.flush();
				} else if (WxConstant.Event_click.equals(baseEventMessage.getEvent().toLowerCase())) {//菜单事件
					ClickEventMessage clickEventMessage = JAXBUtil.convertToJavaBean(sb.toString(), ClickEventMessage.class);
					String eventKey = clickEventMessage.getEventKey();
					WxMenuService wxMenuService = (WxMenuService)ServiceConstants.beanFactory.getBean("WxMenuService");
					WxMenu wxMenu = wxMenuService.getByMenuKey(gzhConfig.getId(), eventKey);
					if (WxConstant.Event_click.equals(wxMenu.getMenuType())) {
						 String xml = this.getMaterialXml(gzhConfig.getId(), wxMenu.getMaterialType(), wxMenu.getMaterialId(), baseMessage);
						 logger.info("response message:"+xml);
						 printWriter.print(xml);
						 printWriter.flush();						
					}
				} else if (WxConstant.Event_view.equals(baseEventMessage.getEvent().toLowerCase())) {//菜单URL事件
					
				}
			}
		} catch (Exception e) {
			logger.info(e);
		} finally {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
			if (printWriter != null) {
				printWriter.close();
				printWriter = null;
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
			WxNewsMaterialService newsMaterialService = (WxNewsMaterialService) ServiceConstants.beanFactory.getBean("WxNewsMaterialService");
			xml = newsMaterialService.getNewsMessageXml(wxhConfigId, materialId, baseMessage);
		} else if (WxConstant.material_type_text == materialType) { // 2.2回复文本消息
			WxTextMaterialService textMaterialService = (WxTextMaterialService) ServiceConstants.beanFactory.getBean("WxTextMaterialService");
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
		 WxKeywordsReplyService keywordsReplyService = (WxKeywordsReplyService)ServiceConstants.beanFactory.getBean("WxKeywordsReplyService");
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
