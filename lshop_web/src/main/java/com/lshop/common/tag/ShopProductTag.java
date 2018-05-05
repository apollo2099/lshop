package com.lshop.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.web.store.service.StoreService;

/**
 * 首页栏目信息
 * @author zhengxue
 * @version 2.0 2013-04-18
 */
@SuppressWarnings("serial")
public class ShopProductTag extends BodyTagSupport {
	
	private Boolean image=true; //是否显示图片，默认为true
	private Boolean name=true; //是否显示名字，默认为true
	private Boolean price=true; //是否显示价格，默认为true
	private Boolean address=true; //是否显示地址，默认为true
	private String language="cn"; //判断语言，中文：cn,英文：en,默认为中文
	private String style = "1"; //1，表示华扬商城     2，表示banana商城   3，表示华扬移动商城  4，表示banana移动商城
	
	public Dto dto = new BaseDto();
	
	public int doEndTag() throws JspException {

	    String serverName =  pageContext.getRequest().getServerName();;
	    String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
        JspWriter out=pageContext.getOut(); 
        StringBuilder html=new StringBuilder();
        
        //获取资源管理域名配置
  		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
  	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
  	    String resDomain="http://res"+domainPostfix;
        
		//获取service
		StoreService storeService=(StoreService)ServiceConstants.beanFactory.getBean("StoreService");
		
		//获取栏目列表
		dto.put("storeFlag", storeFlag);
		List<LvShopSubject> subjectList=storeService.getShopSubjectList(dto);
		if(null!=subjectList && subjectList.size()>0){
			for(LvShopSubject subject:subjectList){
				//获取该栏目所对就的商品信息
				dto.put("storeFlag", storeFlag);
				dto.put("subjectType", subject.getCode());
				List<LvShopProduct> productList=(List<LvShopProduct>)storeService.getShopProductList(dto);
				//保证在有商品信息的情况下才显示栏目
				if(null!=productList && productList.size()>0){
					//华扬商城
					if("1".equals(style)){
						html.append("<div class='cuxiao'>");
						html.append("<h2 class='bt2'>");
						html.append("<p class='cx'>"+subject.getSubjectName()+"</p>");
						if(productList.size()>4){
							html.append("<span class='more'><a href='/web/store!showMoreProducts.action?subjectType="+subject.getCode()+"&exhibitType="+subject.getExhibitType()+"'>");
							if(language.equals("en")){
								html.append("More>>");
							}else{
								html.append("更多>>");
							}
							html.append("</a></span>");
						}
						html.append("</h2>");
						//判断栏目显示类型：1，广告图 2，商品列表信息
						if(subject.getExhibitType()==1){
							int i=0;
							for(LvShopProduct product:productList){
								if(i<4){ //最多只显示4个
									html.append("<p class='zq_image'><a href='"+product.getUrl()+"'><img src='"+product.getPimageAd()+"' border='0' width='706px' height='220px'/></a></p>");
									i++;
								}
							} 
						}else{
							html.append("<ul class='pro_zs1'>");
							int j=0;
							for(LvShopProduct product:productList){
								if(j<4){ //最多只显示4个
									//获取该商品所属店铺信息
									dto.put("storeFlag", product.getStoreId());
									LvStore store = storeService.getStoreByFlag(dto);
									
									html.append("<li>");
									html.append("<a href='"+product.getUrl()+"'>");
									//判断是否显示图片
									if(image){
										String pimage = product.getPimage();
										if ((pimage == null) || "null".equals(pimage)) {
											pimage = "";
										}
										html.append("<img src='"+pimage+"' width='158px' height='122px'/>");
									}
									html.append("<p>");
									//判断是否显示名称
									if(name){
										html.append("<span class='mingcheng'>"+product.getProductName()+"</span>");
									}
									//判断是否显示价格
									if(price){
										if(language.equals("en")){
											html.append("<span class='price'>Price：<span class='price1 p_price' f='"+product.getStoreId()+"' p='"+product.getProductCode()+"' >USD "+product.getProductPrice()+"</span></span>");
										}else{
											html.append("<span class='price'>價格：<span class='price1 p_price' f='"+product.getStoreId()+"' p='"+product.getProductCode()+"' >USD "+product.getProductPrice()+"</span></span>");
										}
										
									}
									//判断是否显示地址
									if(address){
										html.append("<span class='country'>"+store.getCountry()+"</span><span class='city'> - "+store.getCity()+"</span>");
									}
									html.append("</p>");
									html.append("</a>");
									html.append("</li>");
									j++;
								}

							}
							html.append("<div class='cb'></div>");
							html.append("</ul>");
						}
						
						html.append("</div>");
					}
					
					//banana商城
					if("2".equals(style)){
						String currency = "USD";
						//判断栏目显示类型：1，广告图 2，商品列表信息
						if(subject.getExhibitType()==1){
							html.append("<div class='new_product'>");
							html.append("<h1>"+subject.getSubjectName()+"</h1>");
							html.append("<dl>");
							int i=0;
							for(LvShopProduct product:productList){
								//获取店铺币种
								String shop_currency = Constants.STORE_TO_CURRENCY.get(product.getStoreId());
								if(StringUtils.isNotEmpty(shop_currency)){
									currency = shop_currency;
								}
								if(i==0){ //最多只显示3个
									html.append("<dt><a href='"+product.getUrl()+"'><img src='"+product.getPimageAd()+"' border='0' width='489' height='310'/><span></span></a></dt>");
								}
								if(i==1){
									html.append("<dd>");
									html.append("<p><a href='"+product.getUrl()+"'><img src='"+product.getPimageAd()+"' border='0' width='489' height='150'/><span></span></a></p>");
									if(productList.size()==1){
										html.append("</dd>");
									}
								}
								if(i==2){
									html.append("<p class='bot'><a href='"+product.getUrl()+"'><img src='"+product.getPimageAd()+"' border='0' width='489' height='150'/><span></span></a></p>");
									if(productList.size()>1){
										html.append("</dd>");
									}
								}
								if(i>2){
									break;
								}
								i++;
							} 
							html.append("</dl>");
							html.append("</div>");
						}else{
							html.append("<div class='hot_accessories'>");
							html.append("<h1>");
							if(productList.size()>4){
								html.append("<a href='/web/store!showMoreProducts.action?subjectType="+subject.getCode()+"&exhibitType="+subject.getExhibitType()+"'><span><img src='"+resDomain+"/"+storeFlag+"/"+"/res/images/more.jpg' border='0' /></span></a>");
							}
							html.append(subject.getSubjectName());
							html.append("</h1>");
							int j=0;
							html.append("<ul>");
							for(LvShopProduct product:productList){
								//获取店铺币种
								String shop_currency = Constants.STORE_TO_CURRENCY.get(product.getStoreId());
								if(StringUtils.isNotEmpty(shop_currency)){
									currency = shop_currency;
								}
								
								if(j>3){
									break;
								}
								html.append("<li");
								if(j==3){
									html.append(" class='no_mg'");
								}
								html.append(">");
								String pimage = product.getPimage();
								if ((pimage == null) || "null".equals(pimage)) {
									pimage = "";
								}								
								html.append("<a href='"+product.getUrl()+"'><img src='"+pimage+"' border='0' width='230' height='187'/><span>");
								html.append(product.getProductName()+"<br /><font class='blue2 p_price' f='"+product.getStoreId()+"' p='"+product.getProductCode()+"' style='font-size:14px;' >");
								html.append(currency+" "+product.getProductPrice()+"</font></span></a>");
								html.append("</li>");
								j++;
							}
							html.append("</ul>");
							html.append("</div>");
						}
					}
					
					//banana移动商城
					if("4".equals(style)){
						String currency = "USD";
						//判断栏目显示类型：1，广告图 2，商品列表信息
						if(subject.getExhibitType()==1){
							int i=1;
							html.append("<section class='pbg'>");
							for(LvShopProduct product:productList){
								//获取店铺币种
								String shop_currency = Constants.STORE_TO_CURRENCY.get(product.getStoreId());
								if(StringUtils.isNotEmpty(shop_currency)){
									currency = shop_currency;
								}
								//最多只显示4个
								if(i>4){
									break;
								}
								if(i==productList.size() || i==4){
									html.append("<div class='product' style='margin-bottom:0'>");
								}else{
									html.append("<div class='product'>");
								}
								html.append("<a href='"+product.getUrl()+"'>");
								html.append("<div><img src='"+product.getPimageAd()+"' width='100%'></div>");
								html.append("<div class='Price'>");
								html.append("<span>"+product.getProductName()+"</span>");
								html.append("<i><em></em><em class='p_price' f='"+product.getStoreId()+"' p='"+product.getProductCode()+"'>"+currency+" "+product.getProductPrice()+"</em></i>");
								html.append("</div>");
								html.append("</a>");
								html.append("</div>");
								i++;
							}
							html.append("</section>");
						}else{
							html.append("<section class='pbg1'>");
							
							html.append("<div class='productlist'>");
							html.append("<ul>");
							int i=1;
							for(LvShopProduct product:productList){
								//获取店铺币种
								String shop_currency = Constants.STORE_TO_CURRENCY.get(product.getStoreId());
								if(StringUtils.isNotEmpty(shop_currency)){
									currency = shop_currency;
								}
								//最多只显示四个，超过显示更多
								if(i>4){
									break;
								}
								html.append("<li>");
								html.append("<div class='libox'>");
								html.append("<a href='"+product.getUrl()+"'>");
								String pimage = product.getPimage();
								if ((pimage == null) || "null".equals(pimage)) {
									pimage = "";
								}								
								html.append("<div class='proimg'><img src='"+pimage+"' width='80%'></div>");
								html.append("<div class='imtitile'><h2>"+product.getProductName()+"</h2></div>");
								html.append("<div class='imgjia'><span></span><span class='p_price' f='"+product.getStoreId()+"' p='"+product.getProductCode()+"'>"+currency+" "+product.getProductPrice()+"</span>");
								//判断是否包含赠品信息
//							    if(ObjectUtils.isNotEmpty(product.getIsGift())&&product.getIsGift()==1){
//							    	html.append("<span><img src='http://res.itvpad.com/mtmcn/res/images/zp.jpg' /></span>");
//							    }
								html.append("</div>");
								html.append("</a>");
								html.append("</div>");
								html.append("</li>");
								i++;
							}
							html.append("<div class='clear'></div>");
							html.append("</ul>");
							html.append("</div>");
							
							if(productList.size()>4){
								html.append("<div class='more'>");
								html.append("<a href='/web/store!showMoreProducts.action?numPage=10&subjectType="+subject.getCode()+"&exhibitType="+subject.getExhibitType()+"'><span>查看更多</span></a>");
								html.append("</div>");
							}
						}
					}
				}
			}
		}

		try {
			out.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doEndTag();

	}

	public Boolean getImage() {
		return image;
	}

	public void setImage(Boolean image) {
		this.image = image;
	}

	public Boolean getName() {
		return name;
	}

	public void setName(Boolean name) {
		this.name = name;
	}

	public Boolean getPrice() {
		return price;
	}

	public void setPrice(Boolean price) {
		this.price = price;
	}

	public Boolean getAddress() {
		return address;
	}

	public void setAddress(Boolean address) {
		this.address = address;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}

