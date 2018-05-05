package com.lshop.common.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {
	

	/**
	 * 
	 * @Method: getIpAddr 
	 * @Description:  [获取访问IP地址信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月8日 上午11:28:40]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月8日 上午11:28:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param request
	 * @return String
	 */
	public static String getIpAddr(HttpServletRequest request) {    
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	} 
}
