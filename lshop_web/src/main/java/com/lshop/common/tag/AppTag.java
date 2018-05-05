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
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.util.Constants;
import com.lshop.web.product.service.ProductService;

/**
 * 应用及商城模块左侧标签
 * @author zhengxue
 * @version 1.0 2012-07-20
 *
 */
@SuppressWarnings("serial")
public class AppTag extends BodyTagSupport {
	
	private String language="cn"; //判断店铺语言，中文cn，英文en，默认是中文

	public Dto dto = new BaseDto();
	
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		StringBuilder html=new StringBuilder();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		//得到应用标签所涉及到的service
		ProductService productService=(ProductService)ServiceConstants.beanFactory.getBean("ProductService");
		
	    String serverName = pageContext.getRequest().getServerName();
	    String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
	    dto.put("storeFlag", storeFlag);
		
		//调用service中的某一个方法
		List<LvProductType> typeList=(List<LvProductType>)productService.getTypes(dto);
		
		//获取flag，用来标志是否为应用模块
		String flag=request.getAttribute("flag").toString();
			
		if(typeList!=null){
			
//			html.append("<div class='content_main'>");
//			html.append("<div class='left_frame'>");
			html.append("<div class='cm_n_1'>");
			if(language.equals("en")){
				html.append("<h3>Products&Accessories</h3>");
			}else{
				html.append("<h3>产品/配件</h3>");
			}
			
			//先获取有多少个类别
			for(LvProductType pType:typeList){
				
				if(flag=="app"&&pType.getTypeFlag()==2){
					html.append("<h4 class='choose'><a href='/web/applist!toApplist.action'>"+pType.getTypeName()+"</a></h4>");
				}else if(pType.getTypeFlag()==2){
					html.append("<h4><a href='/web/applist!toApplist.action'>"+pType.getTypeName()+"</a></h4>");
				}else{
					html.append("<h4>"+pType.getTypeName()+"</h4>");
				}
				
				html.append("<ul>");
				
				//判断是产品还是应用（1，表示产品；2，表示应用）
				if(pType.getTypeFlag()==1){
					//获取该类别所对应的所有产品或配件列表，产品和配件放在一张表中
					dto.put("ptype", pType.getCode());
					List<LvProduct> pList=(List<LvProduct>)productService.getProductByType(dto);
					if(pList!=null){
						for(LvProduct product:pList){
							if(flag.equals(product.getCode())){
								html.append("<li class='first'><a href='/products/"+product.getCode()+".html' title='"+product.getProductName()+"'>"+product.getProductName()+"</a></li>");
							}else{
								html.append("<li><a href='/products/"+product.getCode()+".html' title='"+product.getProductName()+"'>"+product.getProductName()+"</a></li>");
							}
							
						}
					}
				}
				
				html.append("</ul>");  	
			}
			
			html.append("</div>");
			
			try {
				out.write(html.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return super.doEndTag();

	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}

