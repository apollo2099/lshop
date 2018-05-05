package com.lshop.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvShopActivity;
import com.lshop.common.util.Constants;
import com.lshop.web.store.service.StoreService;

/**
 * 首页栏目信息
 * @author zhengxue
 * @version 2.0 2013-04-19
 *
 */
@SuppressWarnings("serial")
public class ShopActivityTag extends BodyTagSupport {
	
	private String language = "cn"; //判断商城语言，中文cn，英文en，默认是中文
	private Boolean name = true; //判断是否显示活动名称 
	private Boolean time = true; //判断是否显示活动时间
	
	public Dto dto = new BaseDto();
	
	public int doEndTag() throws JspException {

	    String serverName =  pageContext.getRequest().getServerName();;
	    String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
        JspWriter out=pageContext.getOut(); 
        StringBuilder html=new StringBuilder();
        
		//获取service
		StoreService storeService=(StoreService)ServiceConstants.beanFactory.getBean("StoreService");

		//获取促销活动列表
		dto.put("storeFlag", storeFlag);
		List<LvShopActivity> activityList=(List<LvShopActivity>)storeService.getShopActivityList(dto);
		if(null!=activityList && activityList.size()>0){
			html.append("<div class='cuxiao_hd'>");
			//判断语言
			if(language.equals("en")){
				html.append("<h1 class='cxhd_bt'><p class='cx'>Promotion</p></h1>");
			}else{
				html.append("<h1 class='cxhd_bt'><p class='cx'>促銷活動</p></h1>");
			}
			
			html.append("<ul>");
			int i=0;
			for(LvShopActivity activity:activityList){
				if(i<5){
					html.append("<li>");
					//判断是否显示活动名称
					if(name){
						html.append("<a href='"+activity.getAvtivityUrl()+"'>"+activity.getAvtivityName()+"</a>");
					}
					//判断是否显示活动时间
					if(time){
						html.append("<br /><span>"+activity.getAvtivityTime()+"</span>");
					}
					html.append("</li>");
				}
				i++;
			}
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getName() {
		return name;
	}

	public void setName(Boolean name) {
		this.name = name;
	}

	public Boolean getTime() {
		return time;
	}

	public void setTime(Boolean time) {
		this.time = time;
	}
}

