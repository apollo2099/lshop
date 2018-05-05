package com.lshop.common.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpRequstUtil {
	/**
	 * 批量设置属性
	 * @param request
	 * @param params
	 */
	public static void setAttributes(HttpServletRequest request, Map<String, Object> params) {
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			request.setAttribute(key, params.get(key));
		}
	}
}
