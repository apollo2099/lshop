package com.gv.base.springSecurity.web.support;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：SysAuthenticationSuccessHandler   
 * 类描述：   
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:04:39   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:04:39   
 * 修改备注：   
 * @version
 */
public class BaseAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired private HibernateBaseDAO dao;
	
	private BaseUsers baseUser;
	private String lastIp;
	private Date lastTime;
	
	
	public BaseUsers getBaseUser() {
		return baseUser;
	}

	public void setBaseUser(BaseUsers baseUser) {
		this.baseUser = baseUser;
	}

	/**
	 * 	
	 * 功能描述：   登陆认证成功后调用
	 * 创建人：yangzheng  
	 * 创建时间：2011-8-22 下午06:03:13   
	 * 修改人：yangzheng   
	 * 修改时间：2011-8-22 下午06:03:13   
	 * 修改备注：
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication)throws IOException, ServletException {
		   	HttpSession session = request.getSession();
		    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		    String loginIp = getIpAddr(request);
		    updateUserDetailsIp(userDetails, loginIp);
		    session.setAttribute("USER", baseUser);
		    session.setAttribute("lastIP", lastIp);
		    if (null!=lastTime) {
			    session.setAttribute("lastTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(lastTime).toString());
			}
		    super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * 
	 * 功能描述：   获取访问IP
	 * 创建人：yangzheng  
	 * 创建时间：2011-8-31 上午11:43:48   
	 * 修改人：yangzheng   
	 * 修改时间：2011-8-31 上午11:43:48   
	 * 修改备注：
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {    
		String ip = request.getHeader("x-forwarded-for");  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
			ip = request.getHeader("Proxy-Client-IP");             }           
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {              
			ip = request.getHeader("WL-Proxy-Client-IP");             }           
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {           
			ip = request.getRemoteAddr();             }            
		return ip;        
		}   
	
	/**
	 * 
	 * 功能描述：   用户登录成功后修改登录IP及登录时间
	 * 创建人：yangzheng  
	 * 创建时间：2011-8-29 上午11:27:06   
	 * 修改人：yangzheng   
	 * 修改时间：2011-8-29 上午11:27:06   
	 * 修改备注：
	 * @param userDetails
	 * @throws ServiceException
	 */
	
	public void updateUserDetailsIp(UserDetails userDetails,String loginIp )throws ServiceException {
		String username = userDetails.getUsername();
//		baseUser = (BaseUsers) dao.findUniqueByProperty(BaseUsers.class, "userAccount", username);
		String hql="from BaseUsers where status<>-1 and userAccount=:userAccount";
		Map param=new HashMap();
		param.put("userAccount", username);
		baseUser =(BaseUsers)dao.findUnique(hql, param);
		lastIp = baseUser.getUserLoginip();
		lastTime = baseUser.getUserLogintime();
		baseUser.setUserLastip(baseUser.getUserLastip());
		baseUser.setUserLasttime(baseUser.getUserLogintime());
		baseUser.setUserLoginip(loginIp);
		baseUser.setUserLogintime(new Timestamp(System.currentTimeMillis()));

		dao.update(baseUser);

	}
}
