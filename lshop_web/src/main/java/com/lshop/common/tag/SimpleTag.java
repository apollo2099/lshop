package com.lshop.common.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.util.Constants;
import com.opensymphony.xwork2.ActionInvocation;



/**
 * 形成 上一页，下一页， 下拉列表跳转到指定页面的分类指令
 * 
 * @author zhanxg
 * 
 */
public class SimpleTag extends BodyTagSupport {

	private String href; // productlist.action?catId=100&curPage=2
	private String language="cn";
	private String storeFlag="tvpadcn";

	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		String serverName =request.getServerName();
		storeFlag = Constants.STORE_IDS.get(serverName.trim());
		
		try {
			Pagination pagination = (Pagination) request.getAttribute("pagination");

			if (pagination != null) {
				if (ObjectUtils.isNotEmpty(language)) {
					if (language.equals("cn")) {
						doLinkTag(pagination, request, out);
					}else if(language.equals("en")){
						doEnLinkTag(pagination, request, out);
					}else if(language.equals("kn")){
						doKnLinkTag(pagination, request, out);
					}else{
						doLinkTag(pagination, request, out);
					}
				}
			} else
				if (ObjectUtils.isNotEmpty(language)) {
					if (language.equals("cn")) {
						doNoLinkTag(out);
					}else if(language.equals("en")){
						doEnNoLinkTag(out);
					}else if(language.equals("kn")){
						doKnNoLinkTag(out);
					}else{
						doNoLinkTag(out);
					}
				}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.doEndTag();

	}

	//无分页数据时-中文分页显示
	private void doNoLinkTag(JspWriter out) throws IOException {
       StringBuilder html=new StringBuilder();
		html.append("共有<span style='color:#f00'>" + "0" + "</span>條記錄");
		html.append("\n");

		html.append("<span style='margin-left:10px'>首頁</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'>上一頁</span>");
		html.append("\n");

		html.append("<span>下一頁</span>");
		html.append("<span style='margin-left:10px'>尾頁</span>");

		html.append("<span style='margin-left:10px'>頁次: <span style='color:#f00'>"
						+ 0 + "</span> / " + 1 + "</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'>本頁有<span style='color:red'>"
				+ 0 + "</span>記錄</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'>跳轉到");

		html.append("<select onchange='gotoPage(this.options[this.selectedIndex].value)'>");
		html.append("\n");

		html.append("<option selected='selected' value=" + 1 + ">第" + "1"
				+ "頁</option>");
		html.append("\n");
		html.append("\n");
		html.append("</select></span>");
		out.write(html.toString());
	}
	
	//无分页数据时-英文分页显示
	private void doEnNoLinkTag(JspWriter out) throws IOException {
	       StringBuilder html=new StringBuilder();
			html.append("All<span style='color:#f00'>" + "0" + "</span>records");
			html.append("\n");

			html.append("<span style='margin-left:10px'>Home</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'> Pre </span>");
			html.append("\n");

			html.append("<span> Next </span>");
			html.append("<span style='margin-left:10px'>Last Page</span>");

			html.append("<span style='margin-left:10px'>Page: <span style='color:#f00'>"
							+ 0 + "</span> / " + 1 + "</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'><span style='color:red'>"
					+ 0 + "</span>per page</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'>Go to ");

			html.append("<select onchange='gotoPage(this.options[this.selectedIndex].value)'>");
			html.append("\n");

			html.append("<option selected='selected' value=" + 1 + ">" + "1"
					+ "</option>");
			html.append("\n");
			html.append("\n");
			html.append("</select></span>");
			out.write(html.toString());
		}
	//无分页数据时-韩文分页显示
	private void doKnNoLinkTag(JspWriter out) throws IOException {
	       StringBuilder html=new StringBuilder();
			html.append("共有<span style='color:#f00'>" + "0" + "</span>條記錄");
			html.append("\n");

			html.append("<span style='margin-left:10px'>首頁</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'>上一頁</span>");
			html.append("\n");

			html.append("<span>下一頁</span>");
			html.append("<span style='margin-left:10px'尾頁</span>");

			html.append("<span style='margin-left:10px'>頁次: <span style='color:#f00'>"
							+ 0 + "</span> / " + 1 + "</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'>本頁有<span style='color:red'>"
					+ 0 + "</span>記錄</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'>跳轉到");

			html.append("<select onchange='gotoPage(this.options[this.selectedIndex].value)'>");
			html.append("\n");

			html.append("<option selected='selected' value=" + 1 + ">第" + "1"
					+ "頁</option>");
			html.append("\n");
			html.append("\n");
			html.append("</select></span>");
			out.write(html.toString());
		}
	
	//存在分页数据时-中文分页显示
	private void doLinkTag(Pagination pagination, HttpServletRequest request,
			JspWriter out) throws IOException {
		  StringBuilder html=new StringBuilder();
		html.append("共有<span style='color:#f00'>" + pagination.getTotalCount()
				+ "</span>條記錄");
		html.append("\n");

		if (pagination.getPageNum() > 1) {
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", "1") + "'>首頁</a></span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getPrePage())
					+ "'>上一頁</a></span>");
			html.append("\n");
		} else {
			html.append("<span style='margin-left:10px'>首頁</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'>上一頁</span>");
			html.append("\n");
		}

		if (pagination.getTotalPage() > pagination.getPageNum()) {
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getNextPage())
					+ "'>下一頁</a></span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getTotalPage())
					+ "'>尾頁</a></span>");
			html.append("\n");
		} else {
			html.append("<span>下一頁</span>");
			html.append("<span style='margin-left:10px'>尾頁</span>");
		}

		html.append("<span style='margin-left:10px'>頁次: <span style='color:#f00'>"
						+ pagination.getPageNum()
						+ "</span> / "
						+ pagination.getTotalPage() + "</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'>本頁有<span style='color:red'>"
				+ pagination.getList().size() + "</span>記錄</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'>跳轉到");

		html.append("<select onchange='gotoPage(this.options[this.selectedIndex].value)'>");
		html.append("\n");

		for (int i = 1; i <= pagination.getTotalPage(); i++) {
			if (i == pagination.getPageNum()) {
				html.append("<option selected='selected' value="
						+ pagination.getPageNum() + ">第"
						+ pagination.getPageNum() + "頁</option>");
				html.append("\n");
				continue;
			}
			html.append("<option value='" + i + "'>第" + i + "頁</option>");
			html.append("\n");

		}
		html.append("</select></span>");
		out.write(html.toString());
	}
	
	//存在分页数据时-英文分页显示
	private void doEnLinkTag(Pagination pagination, HttpServletRequest request,
			JspWriter out) throws IOException {
		  StringBuilder html=new StringBuilder();
		html.append("All<span style='color:#f00'>" + pagination.getTotalCount()
				+ "</span>records");
		html.append("\n");

		if (pagination.getPageNum() > 1) {
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", "1") + "'>Home</a></span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getPrePage())
					+ "'> Pre </a></span>");
			html.append("\n");
		} else {
			html.append("<span style='margin-left:10px'>Home</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'> Pre </span>");
			html.append("\n");
		}

		if (pagination.getTotalPage() > pagination.getPageNum()) {
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getNextPage())
					+ "'> Next </a></span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getTotalPage())
					+ "'>Last Page</a></span>");
			html.append("\n");
		} else {
			html.append("<span style='margin-left:10px'> Next </span>");
			html.append("<span style='margin-left:10px'>Last Page</span>");
		}

		html.append("<span style='margin-left:10px'>Page: <span style='color:#f00'>"
						+ pagination.getPageNum()
						+ "</span> / "
						+ pagination.getTotalPage() + "</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'><span style='color:red'>"
				+ pagination.getList().size() + "</span>per page</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'>Go to ");

		html.append("<select onchange='gotoPage(this.options[this.selectedIndex].value)'>");
		html.append("\n");

		for (int i = 1; i <= pagination.getTotalPage(); i++) {
			if (i == pagination.getPageNum()) {
				html.append("<option selected='selected' value="
						+ pagination.getPageNum() + ">"
						+ pagination.getPageNum() + "</option>");
				html.append("\n");
				continue;
			}
			html.append("<option value='" + i + "'>" + i + "</option>");
			html.append("\n");

		}
		html.append("</select></span>");
		out.write(html.toString());
	}

	//存在分页数据时-韩文分页显示
	private void doKnLinkTag(Pagination pagination, HttpServletRequest request,
			JspWriter out) throws IOException {
		  StringBuilder html=new StringBuilder();
		html.append("共有<span style='color:#f00'>" + pagination.getTotalCount()
				+ "</span>條記錄");
		html.append("\n");

		if (pagination.getPageNum() > 1) {
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", "1") + "'>首頁</a></span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getPrePage())
					+ "'>上一頁</a></span>");
			html.append("\n");
		} else {
			html.append("<span style='margin-left:10px'>首頁</span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'>上一頁</span>");
			html.append("\n");
		}

		if (pagination.getTotalPage() > pagination.getPageNum()) {
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getNextPage())
					+ "'>下一頁</a></span>");
			html.append("\n");
			html.append("<span style='margin-left:10px'><a href='"
					+ href.replace("@", ""+pagination.getTotalPage())
					+ "'>尾頁</a></span>");
			html.append("\n");
		} else {
			html.append("<span>下一頁</span>");
			html.append("<span style='margin-left:10px'>尾頁</span>");
		}

		html.append("<span style='margin-left:10px'>頁次: <span style='color:#f00'>"
						+ pagination.getPageNum()
						+ "</span> / "
						+ pagination.getTotalPage() + "</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'>本頁有<span style='color:red'>"
				+ pagination.getList().size() + "</span>記錄</span>");
		html.append("\n");
		html.append("<span style='margin-left:10px'>跳轉到");

		html.append("<select onchange='gotoPage(this.options[this.selectedIndex].value)'>");
		html.append("\n");

		for (int i = 1; i <= pagination.getTotalPage(); i++) {
			if (i == pagination.getPageNum()) {
				html.append("<option selected='selected' value="
						+ pagination.getPageNum() + ">第"
						+ pagination.getPageNum() + "頁</option>");
				html.append("\n");
				continue;
			}
			html.append("<option value='" + i + "'>第" + i + "頁</option>");
			html.append("\n");

		}
		html.append("</select></span>");
		out.write(html.toString());
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
