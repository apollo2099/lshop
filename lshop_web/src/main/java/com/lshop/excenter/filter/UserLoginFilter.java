package com.lshop.excenter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lshop.common.util.CookieUtil;
import com.lshop.excenter.ExcenterConstant;

public class UserLoginFilter  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		Object obj=null;
		try {
			obj = CookieUtil.getValue(request, ExcenterConstant.EXUSER_ID, false);
		} catch (Exception e) {
		
		}
		String uri = request.getRequestURI();
		
		
		if (uri.endsWith(".jsp")||uri.endsWith(".action")) {
			if(uri.equals("/excenter/pwdresult.jsp")){
				chain.doFilter(req, res);
				return;
			}
			if (!uri.equals("/excenter/promtManager!getUserDetail.action")&&!uri.equals("/excenter/index.jsp")&&!uri.equals("/excenter/logon_ts.jsp")&&!uri.equals("/excenter/findpwd.jsp")&&!uri.equals("/excenter/login.jsp")&&!uri.equals("/excenter/regedit.action")&&!uri.equals("/excenter/excenter!login.action")&&!uri.equals("/excenter/imager.jsp")&&!uri.equals("/excenter/image.jsp")&&!uri.equals("/excenter/password.action")&&!uri.equals("/excenter/excenter!isExistsUser.action")){
				if (obj==null||"".equals(obj.toString().trim())) {
					HttpServletResponse response = (HttpServletResponse) res;
					response.sendRedirect("/excenter/login.jsp");
					return ;
				}
			
			}
		}
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
