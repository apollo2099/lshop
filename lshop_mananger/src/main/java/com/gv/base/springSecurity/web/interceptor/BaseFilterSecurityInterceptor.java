package com.gv.base.springSecurity.web.interceptor;

import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：SysFilterSecurityInterceptor   
 * 类描述：   
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:04:23   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:04:23   
 * 修改备注：   
 * @versiongetAttributes
 */
public class BaseFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	private static final Log logger = LogFactory.getLog(BaseFilterSecurityInterceptor.class);
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	 
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public void invoke(FilterInvocation fi) throws IOException,ServletException {
		/*处理登录后的URL权限认证*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ServletContext servletContext=fi.getHttpRequest().getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        Map<String, WebInvocationPrivilegeEvaluator> wipes = ctx.getBeansOfType(WebInvocationPrivilegeEvaluator.class);

        if(authentication!=null){//判断登录后URL是否在权限资源控制范围内。防止URL跨权限访问
    	   String url=fi.getRequestUrl(),rescode="";
    	   //这是针对action请求，其它的资源请求均不进入URL拦截，并且不进行权限判断
		   if(!(url.matches(".+\\.jsp")||url.matches(".+\\.gif")
			  ||url.matches(".+\\.png")||url.matches(".+\\.jpg")
			  ||url.matches(".+\\.css")||url.matches(".+\\.js"))){
			   int dwzIndex=url.indexOf("dwz.frag.xml");
			   if(dwzIndex==-1){
				   int index=url.indexOf("json.navTabId=");
		           int prefix=url.indexOf("/manager/mainfream/");
		           int endFlag=url.indexOf("&_=");
		           if(index!=-1&&prefix!=-1){
		        	   if(url.length()==index+"json.navTabId=".length()+ "jbsxBox&_=1352971103421".length()){
		        		   logger.info("资源菜单访问");
		               }else{//验证当前url是否属于当前用户权限
		        			Integer num= url.indexOf("?");
		        		    if(num>0){
		        			    rescode =url.substring("/manager/mainfream/".length(), num);
		        			    String jsonTemp="";
		        			    if(url.length()-(index+"json.navTabId=".length())>24){
		        			    		if(index+"json.navTabId=".length()+24==endFlag){
			        			    		jsonTemp=url.substring(index+"json.navTabId=".length(), index+"json.navTabId=".length()+24);
			        			    	}else if(endFlag!=-1){
			        			    		jsonTemp=url.substring(index+"json.navTabId=".length(), endFlag);
			        			    	}
		        			    }else{
		        			    	jsonTemp=url.substring(index+"json.navTabId=".length(), url.length());
		        			    }
		        			     
		        			    rescode+="?json.navTabId="+jsonTemp;
		        		    }
		            	    WebInvocationPrivilegeEvaluator wips=(WebInvocationPrivilegeEvaluator) wipes.values().toArray()[0];
		            	    Boolean isFlag=wips.isAllowed(rescode, authentication);
		            	    logger.info("*********************=>"+isFlag);
		        	        if(!isFlag) {
		        	    	   fi.getHttpResponse().sendRedirect("/manager/mainfream/index.jsp?error=4");
		        	    	   return;
		        		    }
		        	   }
		           }
//		           else{//请求URL格式非法必须包含前缀/manager/mainfream/和dwz的json.navTabId=信息
//		           	   fi.getHttpResponse().sendRedirect("/manager/mainfream/index.jsp?error=4");
//		           } 
			   }
		   }
        }
    
	    InterceptorStatusToken token = super.beforeInvocation(fi);
	    try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}
	
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		if(logger.isInfoEnabled()){
			  logger.info("***** abc=======================edf*****");
		}
		this.securityMetadataSource = securityMetadataSource;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public void init(FilterConfig filterconfig) throws ServletException {
		
	}

	 
}
