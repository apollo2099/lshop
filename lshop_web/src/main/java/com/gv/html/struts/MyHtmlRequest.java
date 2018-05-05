package com.gv.html.struts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.mock.MockHttpSession;

public class MyHtmlRequest implements HttpServletRequest{
	public static final Log logger = LogFactory.getLog(MyHtmlRequest.class);
	
	/**
     * Request dispatcher state.
     */
    public static final String DISPATCHER_TYPE_ATTR =
        "org.apache.catalina.core.DISPATCHER_TYPE";
    public static String SCHEME = "http";
    public static String PROTOCAL_STRING = "http://";
    private String url;
    private String serverName;
    private String uri;
    private String queryString;
    
    protected Map<String, String> headers = new HashMap<String, String>();
	 /**
     * The attributes associated with this Request, keyed by attribute name.
     */
    protected HashMap<String, Object> attributes =
        new HashMap<String, Object>();
    
    /**
     * The preferred Locales associated with this Request.
     */
    protected Vector<Locale> locales = new Vector<Locale>();
    /**
     * Hash map used in the getParametersMap method.
     */
    protected HashMap<String, String[]> parameterMap = new HashMap<String, String[]>();
    
	protected ServletContext servletContext;
	
	protected HttpSession session;
	
	public MyHtmlRequest(String url){
		servletContext = ApplicationHolder.servletContext;
		session = new MockHttpSession(servletContext);
		//set exdev param
		parameterMap.put("exdev", new String[]{"true"});
		//parse url
		parseUrl(url);
		//tomcat process
		attributes.put(DISPATCHER_TYPE_ATTR, 2);
		//tomcat7
//		attributes.put(DISPATCHER_TYPE_ATTR, DispatcherType.REQUEST);
		//mock header
		headers.put("accept-language", "zh,zh-TW;q=0.8,en-US;q=0.6,en;q=0.4");
		//mock locals
		locales.add(Locale.CHINA);
		locales.add(Locale.US);
	}
	/**
	 * 配置URL
	 * @param request
	 * @param url
	 */
	private void parseUrl(String url) {
		if (url.startsWith(PROTOCAL_STRING)) {
			url = url.substring(PROTOCAL_STRING.length());
		} else {
			throw new RuntimeException("not support protocal!");
		}
		int i = url.indexOf("/"), j = url.indexOf("?");
		if (-1 != i) {
			serverName = url.substring(0, i);
			if (-1 != j) {
				uri = url.substring(i, j);
				queryString = url.substring(j+1);
			} else {
				uri = url.substring(i);
			}
		} else {
			//首页
			serverName = url;
			uri = "/web/index!toIndex.action";
		}
		//query param
		if (StringUtils.isNotBlank(queryString)) {
			String[] params = queryString.split("&");
			String[] ps, vals;
			for (int k = 0; k < params.length; k++) {
				ps = params[k].split("=");
				//TODO only one param
				if (2 == ps.length) {
					vals = new String[]{ps[1]};
					parameterMap.put(ps[0], vals);
				}
			}
		}
	}
	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		return "UTF-8";
	}

	@Override
	public void setCharacterEncoding(String env)
			throws UnsupportedEncodingException {
		logger.info("request invoke unimplemnt method");
		
	}

	@Override
	public int getContentLength() {
		logger.info("request invoke unimplemnt method");
		return 0;
	}

	@Override
	public String getContentType() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getParameter(String name) {
		String[] obj = parameterMap.get(name);
		if (null != obj) {
			return obj[0];
		}
		return null;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		return parameterMap.get(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	@Override
	public String getProtocol() {
		return getScheme();
	}

	@Override
	public String getScheme() {
		return SCHEME;
	}

	@Override
	public String getServerName() {
		return serverName;
	}

	@Override
	public int getServerPort() {
		if ("https".equals(getProtocol())) {
            return 443;
        } else {
            return 80;
        }
	}

	@Override
	public BufferedReader getReader() throws IOException {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getRemoteAddr() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getRemoteHost() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public void setAttribute(String name, Object o) {
		attributes.put(name, o);
		
	}

	@Override
	public void removeAttribute(String name) {
		attributes.remove(name);
		
	}

	@Override
	public Locale getLocale() {
		return locales.get(0);
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return locales.elements();
	}

	@Override
	public boolean isSecure() {
		logger.info("request invoke unimplemnt method");
		return false;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return servletContext.getRequestDispatcher(path);
	}

	@Override
	public String getRealPath(String path) {
		return  servletContext.getRealPath(path);
	}

	@Override
	public int getRemotePort() {
		logger.info("request invoke unimplemnt method");
		return 0;
	}

	@Override
	public String getLocalName() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getLocalAddr() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public int getLocalPort() {
		logger.info("request invoke unimplemnt method");
		return 0;
	}

	@Override
	public String getAuthType() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public long getDateHeader(String name) {
		logger.info("request invoke unimplemnt method");
		return 0;
	}

	@Override
	public String getHeader(String name) {
		return headers.get(name);
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		Vector<String> vs = new Vector<String>();
		String heads = getHeader(name);
		if (StringUtils.isNotBlank(heads)) {
			String[] hs = heads.split(";");
			for (int i = 0; i < hs.length; i++) {
				vs.add(hs[i]);
			}
			return vs.elements();
		} else {
			return new Vector<String>().elements();
		}
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public int getIntHeader(String name) {
		logger.info("request invoke unimplemnt method");
		return 0;
	}

	@Override
	public String getMethod() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getPathInfo() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getPathTranslated() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getContextPath() {
		return servletContext.getContextPath();
	}

	@Override
	public String getQueryString() {
		return queryString;
	}

	@Override
	public String getRemoteUser() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		logger.info("request invoke unimplemnt method");
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getRequestedSessionId() {
		logger.info("request invoke unimplemnt method");
		return null;
	}

	@Override
	public String getRequestURI() {
		return uri;
	}

	@Override
	public StringBuffer getRequestURL() {
		return new StringBuffer(url);
	}

	@Override
	public String getServletPath() {
		return servletContext.getContextPath();
	}

	@Override
	public HttpSession getSession(boolean create) {
		if (create && session == null) {
			session = new MockHttpSession(servletContext);
		}
		return getSession();
	}

	@Override
	public HttpSession getSession() {
		logger.info("mock session");
		return session;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		logger.info("request invoke unimplemnt method");
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		logger.info("request invoke unimplemnt method");
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		logger.info("request invoke unimplemnt method");
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		logger.info("request invoke unimplemnt method");
		return false;
	}

}
