/**
 * <p>Title: CookieUtil.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */
package com.lshop.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dengby
 * 
 */
@SuppressWarnings("serial")
public class CookieUtil extends ActionSupport {
	
	public HttpServletResponse createCookie(HttpServletResponse response,
			String name, String value, boolean isEncode, int maxAge) throws Exception {
		if (isEncode == true)
			value = EncoderUtil.encode(value);
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		// cookie.setDomain(getText("domain.base.name"));
		response.addCookie(cookie);
		return response;
	}
	
	public HttpServletResponse createCookie(HttpServletResponse response,
			String name, String value, boolean isEncode, int maxAge, String domain) throws Exception {
		if (isEncode == true)
			value = EncoderUtil.encode(value);
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
	    if(domain!=null&&!"".equals(domain.trim())){//设置跨子域cookie
		cookie.setDomain(domain.trim());
//		cookie.setMaxAge(100000000);
	    }
		response.addCookie(cookie);
		return response;
	}

	/**
	 * 从cookie中删除cookieName标志
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	public void removeByCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		Cookie[] cookies = request.getCookies();
		// String cookieValue = null;
		if (cookies != null && cookieName != null) {
			for (Cookie cookie : cookies) {
				// for (String cookieName : cookieNames) {
				if (cookieName.equals(cookie.getName())) {
					// cookieValue = cookie.getValue();
					cookie.setMaxAge(0);
					cookie.setValue("");
					cookie.setPath("/");
					// cookie.setDomain(getText("domain.base.name"));
					response.addCookie(cookie);
					break;
				}
				// }
			}
		}
		// return cookieValue;
	}
	
	public void removeByCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String domain) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookieName != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					cookie.setValue("");
					cookie.setPath("/");
				    if(domain!=null&&!"".equals(domain.trim())){
						 cookie.setDomain(domain.trim());
					    }
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	public static String getValue(HttpServletRequest request, String name, boolean isDecode) throws Exception {
		String value = "";
		Cookie[] cookie = request.getCookies();
		if(null == cookie){return value;}
		for (int i = 0; i < cookie.length; i++) {
			if (cookie[i].getName().equals(name)) {
				cookie[i].setPath("/");
				value = cookie[i].getValue();
				if (isDecode) {
					value = EncoderUtil.dencode(value);
				}
				break;
			}
		}
		return value;
	}
	
	public static String getValue(HttpServletRequest request, String name, boolean isDecode, String domain) throws Exception {
		String value = "";
		Cookie[] cookie = request.getCookies();
		for (int i = 0; i < cookie.length; i++) {
			if (cookie[i].getName().equals(name)) {
				cookie[i].setPath("/");
				cookie[i].setDomain(domain);
				value = cookie[i].getValue();
				if (isDecode) {
					value = EncoderUtil.dencode(value);
				}
				break;
			}
		}
		return value;
	}

}
