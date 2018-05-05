package com.lshop.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvShopPartner;
import com.lshop.common.util.Constants;
import com.lshop.web.store.service.StoreService;

/**
 * 首页栏目信息
 * @author zhengxue
 * @version 1.0 2012-12-19
 *
 */
@SuppressWarnings("serial")
public class ShopPartnerTag extends BodyTagSupport {
	
	public Dto dto = new BaseDto();
	
	public int doEndTag() throws JspException {

	    String serverName =  pageContext.getRequest().getServerName();;
	    String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
        JspWriter out=pageContext.getOut(); 
        StringBuilder html=new StringBuilder();
        
		//获取service
		StoreService storeService=(StoreService)ServiceConstants.beanFactory.getBean("StoreService");

		//获取合作商家列表
		dto.put("storeFlag", storeFlag);
		List<LvShopPartner> partnerList=storeService.getShopPartnerList(dto);
		if(null!=partnerList &&partnerList.size()>0){
			html.append("<div class='shangjia'>");
			html.append("<h2 class='bt2'>");
			html.append("<p class='cx'>商家信息<span class='yw'> Business Information </span></p>");
			html.append("</h2>");
			html.append("<ul>");
			for(LvShopPartner partner:partnerList){
				html.append("<li><a href='"+partner.getShopUrl()+"'>");
				//判断商家展现形式：1，图片 2，文字
				if(partner.getExhibitType()==1){
						html.append("<img src='"+partner.getShopLogo()+"' border='0' width='120px' height='58px'/>");
				}else{
					html.append(partner.getShopName());
				}
				html.append("</a></li>");
			}
			html.append("<div class='cb'></div>");
			html.append("</ul>");
			html.append("</div>");
		}
		try {
			out.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doEndTag();

	}
}

