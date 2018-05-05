package com.lshop.web.userCenter.filter;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gv.epg.common.cache.FlushCacheData;
import com.gv.epg.syncdata.activiemq.MqController;
import com.gv.epg.syncdata.socket.SocketController;
import com.gv.epg.ucapi.mqqueue.recevie.MqQueueReceController;
import com.gv.epg.ucapi.mqqueue.send.MqQueueSendController;
import com.gv.monitor.UEMonitorController;
import com.lshop.common.util.Constants;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.web.userCenter.UserCommon;

public class UserLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
	    String serverName = request.getServerName();
	    String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
	    
	    if(storeFlag==null || storeFlag.equals("")){
	    	String url="http://www"+(String)PropertiesHelper.getConfiguration().getProperty("domain.base.name")+"/index.html";
	    	HttpServletResponse response = (HttpServletResponse) res;
	    	response.sendRedirect(url);
	    	return;
	    }
	    
	    //添加资源管理域名配置
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
	    request.setAttribute("resDomain", resDomain);
	    
	    
		String uri = request.getRequestURI();
		
		if (uri.endsWith(".jsp")||uri.endsWith(".action")) {
			if(uri.equals("/web/"+storeFlag+"/pwdresult.jsp")){
				chain.doFilter(req, res);
				return;
			}
			if (uri.equals("/web/blog/blogManage!review.action")||uri.equals("/web/group!saveGroup.action")
			  ||uri.equals("/web/group!toConfirmGroup.action")||uri.equals("/web/group!saveOrderForGroup.action")
			  ||uri.equals("/web/group!checkGroupOrder.action")||uri.equals("/web/shopCart!addAddress.action")
			  ||uri.equals("/web/group!backToGroupOrderInfo.action")||uri.equals("/web/shopCart!toOrderInfo.action")
			  ||uri.equals("/web/shopCart!getCarriage.action")||uri.equals("/web/shopCart!addAddress.action")
			  ||uri.equals("/web/shopCart!delAddress.action")||uri.equals("/web/shopCart!checkCouponCode.action")
			  ||uri.equals("/web/shopCart!toConfirmOrder.action")||uri.equals("/web/shopCart!backToOrderInfo.action")
			  ||uri.equals("/web/shopCart!saveOrder.action")||uri.equals("/web/shopCart!saveWesternInfo.action")
			  ||uri.equals("/web/tvpad/shopCart!toPayOrder.action")
			  ||uri.equals("/web/userCenterManage!getAccount.action")||uri.equals("/web/userCenterManage!getInfoDetail.action")
			  ||uri.equals("/web/userCenterManage!editInfo.action")||uri.equals("/web/userCenterManage!toUpdatePassword.action")
			  ||uri.equals("/web/userCenterManage!updatePassword.action")||uri.equals("/web/userCenterManage!getCommentList.action")
			  ||uri.equals("/web/userCenterManage!getReplyList.action")||uri.equals("/web/userCenterManage!getAddressList.action")
			  ||uri.equals("/web/userCenterManage!setDefaultAddress.action")||uri.equals("/web/userCenterManage!deleteAddress.action")
			  ||uri.equals("/web/userCenterManage!toAddAddress.action")||uri.equals("/web/userCenterManage!addAddress.action")
			  ||uri.equals("/web/userCenterManage!toEditAddress.action")||uri.equals("/web/userCenterManage!editAddress.action")
			  ||uri.equals("/web/userCenterManage!getCart.action")||uri.equals("/web/userCenterManage!delCartList.action")
			  ||uri.equals("/web/userCenterManage!subscribe.action")||(uri.indexOf("/web/userOrder")!=-1)
			  ||uri.equals("/web/device!myDevice.action")||(uri.indexOf("/web/developer")!=-1)||(uri.indexOf("/web/app")!=-1)
			  ||uri.indexOf("/web/recharge", 0) != -1||uri.equals("/web/activity!getCouponByActivityLink.action")
			  ||uri.equals("/web/couponManage!getCouponList.action") || uri.startsWith("/web/prize!")
			  ||uri.equals("/web/activity!wxqrcode.action")){
				if(uri.indexOf("/web/recharge")!=-1&&!uri.equals("/web/recharge!tradeList.action")){
					chain.doFilter(req, res);
					return;
				}
				if (!UserCommon.compareUser(request)) {
					HttpServletResponse response = (HttpServletResponse) res;
					Enumeration params=request.getParameterNames();
					String paramstr="";
					while (params.hasMoreElements()) {
						String key = (String) params.nextElement();
						paramstr+="&"+key+"="+request.getParameter(key);
					}
					if (!"".equals(paramstr)) {
						uri+=paramstr.replaceFirst("&", "?");
					}
					if(storeFlag.equals("www")||storeFlag.equals("en")){
						response.sendRedirect("/web/"+storeFlag+"/userCenterManage!toLoginRegister.action?jumpFlag=1&jumpurl="+URLEncoder.encode(uri, "UTF-8"));
					}else{
						response.sendRedirect("/web/"+storeFlag+"/noLoginInfo.jsp?jumpurl="+URLEncoder.encode(uri, "UTF-8"));
					}
										
					return ;
				}
			}
		}
		chain.doFilter(req, res);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		FlushCacheData.flush();		// 更新缓存
//		//初始化实时同步控制器
		SocketController.getInstance();
//		logger.info("实时同步接口启动");
//		//初始化非实时同步控制器
//		MqController.getInstance();
		MqController.getInstance();
//		logger.info("非实时同步接口启动");
		
		MqQueueReceController.getInstance();
		MqQueueSendController.getInstance();
		
		//EPG监控
		UEMonitorController.getInstance();
	}

}
