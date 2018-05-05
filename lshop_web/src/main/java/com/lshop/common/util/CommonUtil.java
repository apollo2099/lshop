package com.lshop.common.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

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
	 * 检查文件是否存在
	* @author:luohaidong 
	* @param  @param path
	* @param  @return 
	* @return boolean
	* @throws
	 */
	public static boolean checkFiles(String path){
		path = rehabPath(path);
		File file = new File(path);
		if (file.exists()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取随机文件名
	 * @return
	 */
	public static String getRandomFileName() {
		String randomNumber = getRandomNumberAndEdh(10);
		String fileName = System.currentTimeMillis() + randomNumber;
		return fileName;
	}
	
	/**
	 * 获取随机字符（字母 + 数字）
	 * 
	 * @param length
	 *            获取几位
	 * @return
	 */
	public static String getRandomNumberAndEdh(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			double d = Math.random() * 1;	// 只产生0和1
			long type = Math.round(d);
			if (type == 0) {
				sb.append(getRandomEdh());		// 字母
			} else if(type == 1) {
				sb.append(getRandomNumber());	// 数字
			}
		}
		return sb.toString();
	}
	
	
	
	/**
	 * 获取一位随机字母
	 */
	public static String getRandomEdh() {
		String[] edhs = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		double d = Math.random() * (edhs.length - 1);
		int i = (int) Math.round(d);
		return edhs[i];
	}

	/**
	 * 获取随机数
	 * 
	 * @param length
	 *            获取几位
	 * @return
	 */
	public static String getRandomNumber(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(getRandomNumber());
		}
		return sb.toString();
	}

	/**
	 * 获取一位随机数
	 * 
	 * @return
	 */
	public static int getRandomNumber() {
		double d = Math.random() * 9;
		long l = Math.round(d);
		return (int) l;
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
	
}
