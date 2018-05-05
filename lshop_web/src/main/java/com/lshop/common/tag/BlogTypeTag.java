package com.lshop.common.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.util.Constants;
import com.lshop.web.blog.service.BlogService;

/**
 * 首页默认博客分类模块
 * @author zhengxue
 * @version 1.0 2013-11-25
 *
 */
@SuppressWarnings("serial")
public class BlogTypeTag extends BodyTagSupport {

	public Dto dto = new BaseDto();
	private String language="cn"; //判断语言，中文：cn,英文：en,默认为中文

	public int doEndTag() throws JspException{
		
		JspWriter out = pageContext.getOut();
		StringBuilder html=new StringBuilder();
		
		String serverName = pageContext.getRequest().getServerName();
	    String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
	    //获取资源管理域名配置
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
		
		//得到应用标签所涉及到的service
		BlogService blogService = (BlogService)ServiceConstants.beanFactory.getBean("BlogService");;
		
		//获取默认（排序值最大且最新创建）的博客分类
		dto.put("storeFlag", storeFlag);
		LvBlogType lvBlogType = blogService.getDefaultBlogType(dto);
		
		if(null!=lvBlogType){
			
			//获取该分类下的博客内容
			dto.put("typeId", lvBlogType.getId());
			dto.put("storeFlag", storeFlag);
			List<LvBlogContent> blogs = blogService.getContentsByType(dto);
			
			if(null!=blogs && blogs.size()>0){
				
				html.append("<div class='news_x'>");
				
				html.append("<h1>");
				if(blogs.size()>4){
					//html.append("<a href='/web/blog/blogManage!list.action?lvBlogContent.type="+lvBlogType.getId()+"'><span><img src='"+resDomain+"/"+storeFlag+"/"+"/res/images/more.jpg' /></span></a>");
					if(language.equals("en")){
						html.append("<a href='/blog/blogType"+lvBlogType.getId()+".html'><span>More>></span></a>");
					}else{
						html.append("<a href='/blog/blogType"+lvBlogType.getId()+".html'><span>更多>></span></a>");
					}
					
				}
				html.append(lvBlogType.getType());
				html.append("</h1>");
				
				html.append("<ul>");
				int num = 1;
				for(LvBlogContent blog : blogs){
					if(num>4){
						break;
					}
					html.append("<li>");
					html.append("<p class='text'>");
					//html.append("<a href='/web/blog/blogManage!view.action?lvBlogContent.id="+blog.getId()+"'>"+blog.getTitle()+"</a><span title='"+blog.getIntor()+"'>"+blog.getIntor()+"</span>");
					html.append("<a href='/blog/blogInfo"+blog.getId()+".html'>"+blog.getTitle()+"</a><span title='"+blog.getIntor()+"'>"+blog.getIntor()+"</span>");
					html.append("</p>");
					html.append("<p class='date'>");
					SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
					html.append(format.format(blog.getCreateTime()));
					html.append("</p>");
					html.append("</li>");
					num++;
				}
				html.append("</ul>");
				
				html.append("</div>");
				
				try {
					out.write(html.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
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
