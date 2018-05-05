package com.lshop.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author shenyw
 *
 */
public class CommonUtil extends ActionSupport {
	
	/**
	 * 返回没有null的字符串，如果是null则返回""
	 * @param obj
	 * @return
	 */
	public static String getStringABS(Object obj) {
		String result = "";
		if (obj != null) {
			result = obj.toString();
		}
		return result;
	}
	
	/**
	 * 按字节截取字符串
	 * @param str	要截取的字符串
	 * @param length	截取长度（以字节为单位）
	 * @return
	 */
	public static String subStrByByte(String str, int length) {
		for (int i = 0, j = 0; i < str.length(); i++) {
			String s = str.substring(i, i + 1);
			boolean isTwoByte = s.matches("[^\\x00-\\xff]");
			j += isTwoByte ? 2 : 1;
			if (j >= length) {
				return str.substring(0, i + 1);
			}
		}
		return str;
	}
	
	public static String getSecondLevelDomain(HttpServletRequest request) {
		String domain = "";
		String host = request.getHeader("Host");
		int index = host.indexOf(".");
		int lastindex=host.indexOf(":");
		if (index != -1) {
			domain = host.substring(index);
		}
		if (lastindex!=-1) {
			domain=domain.substring(0,lastindex);
		}
		return domain;
	}
	
}
