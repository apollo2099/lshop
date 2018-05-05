package com.gv.html.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.gv.html.component.HtmlComponent;
import com.gv.html.core.HtmlEngine;
import com.gv.html.core.HtmlMonitor;
import com.lshop.common.action.BaseAction;

/**
 * 静态链接访问动态页面
 * @author caicl
 *
 */
@Controller("HtmlAction")
public class HtmlAction extends BaseAction{
	private static final long serialVersionUID = 1255993770538892375L;
	private String html;//访问的静态路径
	@Resource
	private HtmlComponent htmlComponent;
	@Resource HtmlEngine htmlEngine;

	/**
	 * 执行静态化后门
	 * @throws IOException 
	 */
//	public void html() throws IOException {
//		String shopFlag = this.getRequest().getParameter("shopFlag");
//		String resu = htmlEngine.asynchronExcute(shopFlag.split(",")) ? "success" : "wait";
//		getResponse().setContentType("text/html;charset=UTF-8");
//		PrintWriter out = getResponse().getWriter();
//		out.append(resu);
//		out.close();
//	}
	/**
	 * 查看引擎状态
	 * @throws IOException
	 */
	public void status() throws IOException {
		getResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out = getResponse().getWriter();
		out.append(htmlEngine.getStatus()).append("-->").append(HtmlMonitor.report());
		out.close();
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
}
