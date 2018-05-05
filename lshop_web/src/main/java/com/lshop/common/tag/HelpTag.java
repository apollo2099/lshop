package com.lshop.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvHelp;
import com.lshop.common.pojo.logic.LvHelpType;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.helpCenter.service.HelpCenterService;

/**
 * 帮助模块标签
 * @author zhengxue
 * @version 1.0 2012-07-09 
 *
 */
@SuppressWarnings("serial")
public class HelpTag extends BodyTagSupport {

	public Dto dto = new BaseDto();
	private String style = "1"; //1，表示华扬商城     2，表示banana中文商城  3，表示banana英文商城
	
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		StringBuilder html=new StringBuilder();
		
        HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
        String serverName =  request.getServerName();;
        String flag = Constants.STORE_IDS.get(serverName.trim());//获取标志
        
	    //获取当前商城标识
		String storeFlag=StoreHelp.getParentFlagByFlag(flag);
		storeFlag=storeFlag==null?flag:storeFlag;
	    dto.put("storeFlag", storeFlag);
	    
	    //添加资源管理域名配置
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
	    
		//得到帮助标签所涉及到的service
		HelpCenterService helpCenterService=(HelpCenterService)ServiceConstants.beanFactory.getBean("HelpCenterService");
		//调用service中的某一个方法
		List<LvHelpType> helpTypes=(List<LvHelpType>)helpCenterService.getHelpTypes(dto);
		
		//获取当前店铺所对应的商城域名
		String domainName = StoreHelp.getStoreDomainByFlag(storeFlag);
		
		if(helpTypes!=null){
			//华扬商城
			if("1".equals(style)){
				html.append("<div class='help'>");
				html.append("<div class='help_center'>");
				html.append("<ul>");
				
				//显示帮助类别及名称列表
				for(LvHelpType lvHelpType:helpTypes){
					html.append("<li>");
					//显示某一个帮助类别
					html.append("<p><font class='fontwz bfont'>"+lvHelpType.getName()+"</font></p>");
					//显示该类别下面所对应的所有帮助子名称
					dto.put("helpId", lvHelpType.getId());
					List<LvHelp> lvHelps=(List<LvHelp>)helpCenterService.getHelpsByType(dto);
					if(lvHelps!=null){
						for(LvHelp lvHelp:lvHelps){
							html.append("<p><a href=http://"+domainName+"/help"+lvHelpType.getId()+".html"+"#M_"+lvHelp.getId()+">"+lvHelp.getName()+"</a></p>");
						}
					}
					html.append("</li>");
				}
				
				//显示咨询电话
				html.append("<li class='tel'>");
				if (storeFlag.equals("en")) {
					html.append("<p><font class='fontwz bfont'>Contact Us</font></p>");
				} else {
					html.append("<p><font class='fontwz bfont'>聯繫我們</font></p>");
				}
				html.append("<p class='tel_contect'>(00852)2134-9910</p>");
				html.append("</li>");
				html.append("</ul>");
				html.append("<div class='cb'></div>	");
				html.append("</div>");
				html.append("</div>");
			}
			
			
			//banana商城
			if("2".equals(style) || "3".equals(style)){
				
				html.append("<div class='help'>");
				
				html.append("<div class='help1'>");
				//显示帮助类别及名称列表
				for(LvHelpType lvHelpType:helpTypes){
					html.append("<ul>");
					//显示某一个帮助类别
					html.append("<li class='title'>"+lvHelpType.getName()+"</li>");
					//显示该类别下面所对应的所有帮助子名称
					dto.put("helpId", lvHelpType.getId());
					List<LvHelp> lvHelps=(List<LvHelp>)helpCenterService.getHelpsByType(dto);
					if(lvHelps!=null){
						for(LvHelp lvHelp:lvHelps){
							html.append("<li><a href=http://"+domainName+"/help"+lvHelpType.getId()+".html"+"#M_"+lvHelp.getId()+">"+lvHelp.getName()+"</a></li>");
						}
					}
					html.append("</ul>");
				}
				html.append("</div>");
				
				//显示咨询电话
				if("2".equals(style)){
				    html.append("<a href='http://www8.53kf.com/webCompany.php?arg=lulucute&style=2' target='_blank'> <div class='phone'> <ul><li>7*24小时服务</li><li>国内："
				                +"         <p class=\"blue2\">0755-26520101</p> </li> <li>海外： <p class=\"blue2\">00852-31-158-518</p></li>"
				                		 +"   <li class=\"bt\"> "
				                +"<img src='"+resDomain+"/"+storeFlag+"/res/images/phone.jpg' /></li></ul></div></a>");
				}
				if("3".equals(style)){
					html.append("<a href='http://www8.53kf.com/webCompany.php?arg=lulucute&style=2' target='_blank'><div class='phone'><p><span class='blue2'>00852-31-158-518</span>");
					html.append("7*24 Customer Service");
					html.append("</p></div></a>");
				}
				html.append("</div>");
			}
			
			try {
				out.write(html.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return super.doEndTag();

	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
