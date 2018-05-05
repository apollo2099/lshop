package com.lshop.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.web.store.service.StoreService;

/**
 * 首页栏目信息
 * @author zhengxue
 * @version 2.0 2013-04-19
 *
 */
@SuppressWarnings("serial")
public class RecommendStoreTag extends BodyTagSupport {
	
	private String language = "cn"; //判断商城语言，中文cn，英文en，默认是中文
	private Boolean logo = true; //是否显示logo，默认是true
	private Boolean name = true; //是否显示店铺名称，默认是true
	private Boolean address = true; //是否显示国家城市，默认是true
	
	public Dto dto = new BaseDto();
	
	public int doEndTag() throws JspException {

	    String serverName =  pageContext.getRequest().getServerName();;
	    String storeFlag = Constants.STORE_IDS.get(serverName.trim());  //获取当前商城标识
        JspWriter out=pageContext.getOut(); 
        StringBuilder html=new StringBuilder();
        
		//获取service
		StoreService storeService=(StoreService)ServiceConstants.beanFactory.getBean("StoreService");
		int count = 0;

		//获取商城信息
		dto.put("storeFlag", storeFlag);
		LvStore mStore = storeService.getStoreByFlag(dto);
		
		//获取推荐店铺信息（只要前两行数据）
		dto.put("parentCode", mStore.getCode());
		List<LvStore> storeList = storeService.getRecommendStoreList(dto);
		if(null!=storeList && storeList.size()>0){
			html.append("<div class='cuxiao'>");
			//判断语言类型
			if(language.equals("en")){
				html.append("<h2 class='bt2'><p class='cx'>Recommended Store<span class='yw'></span></p></h2>");
			}else{
				html.append("<h2 class='bt2'><p class='cx'>推薦店鋪<span class='yw'></span></p></h2>");
			}
			
			html.append("<ul class='pro_zs2'>");
			for(LvStore store: storeList){
				if(count<8){
					String tmp = "javascript:saveCookieForStore('"+store.getCode()+"','"+store.getName()+"','"+store.getDomainName()+"');";
					String tmp1 = "/web/store!toShopList.action?continentCode="+store.getContinentCode()+"&countryCode="+store.getCountryCode();
					String tmp2 = "/web/store!toShopList.action?continentCode="+store.getContinentCode()+"&countryCode="+store.getCountryCode()+"&cityCode="+store.getCityCode();
					html.append("<li>");
					//判断是否显示logo
					if(logo){
						html.append("<a href="+tmp+"><img src='"+store.getLogo()+"' border='0' width='120px' height='40px'/></a>");
					}
					//判断是否显示名称
					html.append("<p>");
					if(name){
						html.append("<span class='name'><a href="+tmp+">"+store.getName()+"</a></span>");
					}
					//判断是否显示国家城市
					if(address){
						html.append("<span class='country1'><a href="+tmp1+">"+store.getCountry()+"</a></span><span class='city1'> - <a href="+tmp2+">"+store.getCity()+"</a></span>");
					}
					html.append("</p>");
					html.append("</li>");
					count++;
				}else{
					break;
				}
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getLogo() {
		return logo;
	}

	public void setLogo(Boolean logo) {
		this.logo = logo;
	}

	public Boolean getName() {
		return name;
	}

	public void setName(Boolean name) {
		this.name = name;
	}

	public Boolean getAddress() {
		return address;
	}

	public void setAddress(Boolean address) {
		this.address = address;
	}
}

