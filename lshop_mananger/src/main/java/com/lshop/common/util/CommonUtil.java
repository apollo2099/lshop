package com.lshop.common.util;

import java.io.File;
import java.math.BigDecimal;
import java.net.ConnectException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.https.HTTPSSecureProtocolSocketFactory;
import com.lshop.common.xml.XmlParse;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author shenyw
 *
 */
public class CommonUtil extends ActionSupport {
	
	/**
	 * 过滤sql中的部分关键字
	 */
	public static String filterLike(String str) {
		return str.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
	}
	
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
	
	/**
	 * 修复路径，兼容windows、linux
	 * @return 修复后的路径
	 */
	public static String rehabPath(String path) {
		char wc = '\\';
		char lc = '/';
		
		// 是windows系统
		if (File.separatorChar == wc) {
			path = path.replace(lc, wc);
			if (path.charAt(0) == wc) {
				path = path.substring(1);
			}
		} else {
			path = path.replace(wc, lc);
		}
		
		StringBuffer newpath = new StringBuffer();
		char pre = ' ';
		
		// 多个连在一起的斜杠或反斜杠都替换为一个
		for (int i = 0; i < path.length(); i++) {
			char s = path.charAt(i);
			if ((s == wc || s == lc) && s == pre) {
				continue;
			}
			newpath.append(s);
			pre = s;
		}
		return newpath.toString();
	}
	
	/**
	 * 获取根目录路径
	 */
	public static String getRealPath() {
		String rootpath = CommonUtil.class.getClassLoader().getResource("").getPath();
		rootpath = rootpath.substring(0, rootpath.indexOf("WEB-INF/"));
		return rehabPath(rootpath);
	}
	
	/**
	 * 根据path创建相应的文件目录，目录不存在才创建
	 * @return 是否创建了目录 
	 */
	public static boolean checkCreateFiles(String path) {
		path = rehabPath(path);
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
			return true;
		}
		return false;
	}
	
	/**
	 * 删除指定的单个文件
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 过滤无用小数，如：12.0过滤后为12
	 * @param number
	 */
	public static String filterFrivolousDecimal(Number number) {
		if (number == null) {
			return "";
		}
		String str = String.valueOf(number);
		str = new BigDecimal(str).toPlainString();
		if (str.indexOf(".") != -1) {
			while (str.charAt(str.length() - 1) == '0') {
				str = str.substring(0, str.length() - 1);
			}
			if (str.charAt(str.length() - 1) == '.') {
				str = str.substring(0, str.length() - 1);
			}
		}
		return str;
	}
	
}
