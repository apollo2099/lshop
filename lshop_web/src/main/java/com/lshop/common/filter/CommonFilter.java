package com.lshop.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lshop.common.util.CookieUtil;
import com.lshop.web.userCenter.UserConstant;


public class CommonFilter extends HttpServlet implements Filter {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getRequestURI();
		String target = url.substring(url.lastIndexOf("/") + 1);
		// 文件上传不经过struts
		if ("upload.do".equals(target)) {
			request.getRequestDispatcher(request.getServletPath()).forward(request, response);
			return;
		}
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	
}
