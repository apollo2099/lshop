package com.lshop.common.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.page.Pagination;



/**
 * 华扬二期分页样式
 * @author zhengxue
 * @version 2.0 2013-04-19
 *
 */
@SuppressWarnings("serial")
public class NewPageTag extends BodyTagSupport {

	private String href; 
	private String language="cn"; //判断商城语言，中文cn，英文en，默认是中文

	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
//		String serverName =request.getServerName();
//		String storeFlag = Constants.STORE_IDS.get(serverName.trim());
		
		Pagination pagination = (Pagination) request.getAttribute("pagination");
		if (pagination != null) {
			StringBuilder html=new StringBuilder();
			html.append("<div class='fenye' id='fenye'>");
			html.append("<ul class='fy'>");
			//判断当前页是否为第一页，如果当前页数大于1，则链接到上一页，否则不可点击
			if (pagination.getPageNum() > 1) {
				html.append("<li class='sf'><a href='"+ href.replace("@", ""+pagination.getPrePage())+ "'><</a></li>");
			}else{
				html.append("<li class='sf'><a href='#'><</a></li>");
			}
			
			//判断总页数是否超过10页，如果超过要用省略号，不超过则全部罗列出来
			if (pagination.getTotalPage() > 10){
				
				//如果当前页小于等于第4页，则之前全部显示，后面显示省略号加尾页
				if(pagination.getPageNum()<=4){
					for(int i=1; i<=6; i++){
						if(i==pagination.getPageNum()){ 
							html.append(" <li class='fy_dq'><a href='"+ href.replace("@", ""+i)+ "'>"+i+"</a></li>");
						}else{
							html.append(" <li><a href='"+ href.replace("@", ""+i)+ "'>"+i+"</a></li>");
						}
					}
					html.append("<li>…</li>");
					if(pagination.getTotalPage()==pagination.getPageNum()){ 
						html.append("<li class='fy_dq'><a href='"+ href.replace("@", ""+pagination.getTotalPage())+ "'>"+pagination.getTotalPage()+"</a></li>");
					}else{
						html.append("<li><a href='"+ href.replace("@", ""+pagination.getTotalPage())+ "'>"+pagination.getTotalPage()+"</a></li>");
					}
					
					
				//如果当前页大于等于总页数-3，则后面连续显示，前面显示首页加省略号
				}else if(pagination.getPageNum()>=pagination.getTotalPage()-3){
					if(1==pagination.getPageNum()){ 
						html.append("<li class='fy_dq'><a href='"+ href.replace("@", "1")+ "'>"+1+"</a></li>");
					}else{
						html.append("<li><a href='"+ href.replace("@", "1")+ "'>"+1+"</a></li>");
					}
					
					html.append("<li>…</li>");
					for(int i=pagination.getTotalPage()-5; i<=pagination.getTotalPage(); i++){
						if(i==pagination.getPageNum()){ 
							html.append(" <li class='fy_dq'><a href='"+ href.replace("@", ""+i)+ "'>"+i+"</a></li>");
						}else{
							html.append(" <li><a href='"+ href.replace("@", ""+i)+ "'>"+i+"</a></li>");
						}
					}
					
				//否则，两边都要显示省略号
				}else{
					if(1==pagination.getPageNum()){ 
						html.append("<li class='fy_dq'><a href='"+ href.replace("@", "1")+ "'>"+1+"</a></li>");
					}else{
						html.append("<li><a href='"+ href.replace("@", "1")+ "'>"+1+"</a></li>");
					}
					html.append("<li>…</li>");
					for(int i=pagination.getPageNum()-2; i<=pagination.getPageNum()+2; i++){
						if(i==pagination.getPageNum()){ 
							html.append(" <li class='fy_dq'><a href='"+ href.replace("@", ""+i)+ "'>"+i+"</a></li>");
						}else{
							html.append(" <li><a href='"+ href.replace("@", ""+i)+ "'>"+i+"</a></li>");
						}
					}
					html.append("<li>…</li>");
					if(pagination.getTotalPage()==pagination.getPageNum()){ 
						html.append("<li class='fy_dq'><a href='"+ href.replace("@", ""+pagination.getTotalPage())+ "'>"+pagination.getTotalPage()+"</a></li>");
					}else{
						html.append("<li><a href='"+ href.replace("@", ""+pagination.getTotalPage())+ "'>"+pagination.getTotalPage()+"</a></li>");
					}
				}
			}else{
				for(int i=1; i<=pagination.getTotalPage(); i++){
					if(i==pagination.getPageNum()){ 
						html.append(" <li class='fy_dq'><a href='"+ href.replace("@", ""+i)+ "'>"+i+"</a></li>");
					}else{
						html.append(" <li><a href='"+ href.replace("@", ""+i)+ "'>"+i+"</a></li>");
					}
				}
			}

			//判断当前页是否是最后一页，若是不加链接，否则要加
			if (pagination.getTotalPage() > pagination.getPageNum()){
				html.append("<li class='sf'><a href='"+ href.replace("@", ""+pagination.getNextPage())+ "'>></a></li>");
			}else{
				html.append("<li class='sf'><a href='#'>></a></li>");
			}
			html.append("</ul>");
			if(language.equals("en")){
				html.append("<p>Jump to</p><p><input id='pageValue' name='pageValue' type='text' class='pro_bd'/></p>");
			}else{
				html.append("<p>跳转到</p><p><input id='pageValue' name='pageValue' type='text' class='pro_bd'/></p>");
			}
			html.append("<p><input name='' type='button'  class='button03' onclick='toPage();' value='GO' /></p>");
			html.append("</div>");
			
			try {
				out.write(html.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 

		return super.doEndTag();

	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
