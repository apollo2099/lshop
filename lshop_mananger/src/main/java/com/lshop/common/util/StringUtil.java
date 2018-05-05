package com.lshop.common.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.gv.core.util.ObjectUtils;

public class StringUtil {

	
	/**
	 * 
	 * @Method: stringFormart 
	 * @Description:  [更加截取标识截取字符串拼接成字符窜返回: 返回例子如 ('aa','bb','cc')]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-5-10 下午05:11:02]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-5-10 下午05:11:02]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param source 目标源字符串
	 * @param splitFlag 截取标识
	 * @return String
	 */
	public static String stringFormart(String source,String splitFlag){
		String [] arr=source.split(splitFlag);
		String temp="";
		for (int i = 0; i < arr.length; i++) {
			if (ObjectUtils.isNotEmpty(arr[i])) {
				if(i==arr.length-1){
					temp+="'"+arr[i].trim()+"'";
				}else{
					temp+="'"+arr[i].trim()+"',";
				}
			}
		}
		return temp;
	}
	
	public static String stringSpelling(String source,String SpellingFlag){
		return SpellingFlag+source+SpellingFlag;
	}
	
	
	/**
	 * 
	 * @Method: getFileExtention 
	 * @Description:  [获取文件扩展名]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-7-9 下午04:21:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-7-9 下午04:21:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param fileName
	 * @return 
	 * @return String
	 */
	public static String getFileExtention(String fileName){
		String extention = "";
		if (fileName.length() > 0 && fileName != null) { // --截取文件名
			int i = fileName.lastIndexOf(".");
			if (i > -1 && i < fileName.length()) {
				extention = fileName.substring(i + 1); // --扩展名
				}
		} 
		return extention;
	}
	
	
	/**
	 * 
	 * @Method: isExistChineseCharacter 
	 * @Description:  [判断是否存在汉字]  
	 * @Author:       [liaoxiongjian]     
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param str  检测内容 
	 * @return Boolean
	 */
	public static Boolean isExistChineseCharacter(String str){
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;

		
	}
	
	/**
	 * 
	 * @Method: isExistRepeat 
	 * @Description:  [判断数组内容是否重复值]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-10-14 下午03:58:10]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-10-14 下午03:58:10]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param ary  参数数组
	 * @return boolean 返回发false重复，返回true表示不重复
	 */
	public static boolean isExistRepeat(String[] ary) {
		for (int i = 0; i < ary.length; i++) {
			for (int j = 0; j < ary.length; j++) {
				if (ObjectUtils.isNotEmpty(ary[i])&& ObjectUtils.isNotEmpty(ary[j])) {
					if (ary[i].trim().equals(ary[j].trim()) && i != j) {
						return false;// 如果返回false表示有重复的
					}
				}
			}
		}
		return true;// 如果返回true表示没有重复的
	}
	
	/**
	 * 
	 * @Method: isDouble 
	 * @Description:  [判断是否存在是double类型]  
	 * @Author:       [liaoxiongjian]     
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param str  检测内容 
	 * @return Boolean
	 */
	public static Boolean isDouble(String str){
		try {
			double b = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * 
	 * @Method: isFloat 
	 * @Description:  [判断当前类型是否为float类型]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-27 上午11:53:03]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-27 上午11:53:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param floatStr
	 * @return Boolean
	 */
	public static Boolean isFloat(String floatStr){
		Pattern pattern = Pattern.compile("[0-9]*(\\.)[0-9]*");     //浮点型的吗应该是一定有 "."
		return pattern.matcher(floatStr).matches();     //判断控件输入的是否为浮点数
	}
	
	
	/** 
     * 判断number参数是否是整型数表示方式 
     * @param number 
     * @return 
     */  
    public static boolean isIntegerNumber(String number){  
        number=number.trim();  
        String intNumRegex="\\-{0,1}\\d+";//整数的正则表达式  
        if(number.matches(intNumRegex))  
            return true;  
        else  
            return false;  
    }  
      
    /** 
     * 判断number参数是否是浮点数表示方式 
     * @param number 
     * @return 
     */  
    public static boolean isFloatPointNumber(String number){  
        number=number.trim();  
        String pointPrefix="(\\-|\\+){0,1}\\d*\\.\\d+";//浮点数的正则表达式-小数点在中间与前面  
        String pointSuffix="(\\-|\\+){0,1}\\d+\\.";//浮点数的正则表达式-小数点在后面  
        if(number.matches(pointPrefix)||number.matches(pointSuffix))  
            return true;  
        else  
            return false;  
    }  
	
	/**
	 * 
	 * @Method: FormatPrice 
	 * @Description:  [价格格式化，保留2位小数]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-8-26 下午01:33:26]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-8-26 下午01:33:26]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param price 
	 * @return Double
	 */
	public static Double FormatPrice(Double price){
		String priceUsd= String.format("%.2f",price);
		return Double.parseDouble(priceUsd);
	}
	
	
	/**
	 * 
	 * @Method: isValidChar 
	 * @Description:  [验证数字，字母，汉字，","为有效输入字符]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-10-21 上午10:08:41]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-10-21 上午10:08:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param ch
	 * @return boolean
	 */
	private static boolean isValidChar(char ch) {
		if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z')|| (ch >= 'a' && ch <= 'z'))
			return true;
		if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))
			return true;// 简体中文汉字编码
		if(ch==',')
			return true;
		return false;
	}
	
	/**
	 * 
	 * @Method: isValidString 
	 * @Description:  [验证字符串是否符合有效字符输入]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-10-21 上午10:09:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-10-21 上午10:09:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param content
	 * @return boolean
	 */
	public static boolean isValidString(String content){
		for (int i = 0; i < content.length(); i++) {
			char ch = content.charAt(i);
			if (isValidChar(ch) == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: removeSpecialChar 
	 * @Description:  [去掉内容的特殊字符公共方法]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-23 下午3:30:34]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-23 下午3:30:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param content 输入内容
	 * @param specialChar 特殊字符
	 * @return String 组装返回
	 */
	public static String removeSpecialChar(String content,String specialChar){
		String returnStr="";
		Boolean isFlag=content.contains(specialChar);
		if(isFlag){
			char [] arr=content.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				String str=String.valueOf(arr[i]);
				if(!str.equals(specialChar)){
					returnStr+=str;
				}
			}		
		}else{
			returnStr=content;
		}
		return returnStr;
	}
	
	
	public static String replaceChars(String str) {
		String ret = str;	
//		ret = ret.replaceAll("&", "&amp;"); // must call it in first
//		ret = ret.replaceAll("<", "&lt;");
//		ret = ret.replaceAll(">", "&gt;");
//		ret = ret.replaceAll("\"", "&quot;");
		ret = ret.replaceAll("'", "\'");
		return ret;
	}
	
	
	public static void main(String[] args) {
		String flagTmp="tvpad,tvpaden";
		String [] arr=flagTmp.split(",");
		String flag="";
		for (int i = 0; i < arr.length; i++) {
			
			if(i==arr.length-1){
				flag+="'"+arr[i]+"'";
			}else{
				flag+="'"+arr[i]+"',";
			}
			
			System.out.println(arr[i]);
		}
		System.out.println(flag);
	
		String flagTmp2="tvpad";
		String [] arr2=flagTmp2.split(",");
		String flag2="";
		for (int j = 0; j < arr2.length; j++) {
			if(j==arr2.length-1){
				flag2+="'"+arr2[j]+"'";
			}else{
				flag2+="'"+arr2[j]+"',";
			}
			
			System.out.println(arr2[j]);
		}
		System.out.println(flag2);
		
		System.out.println(StringUtil.stringFormart("tvpad2", ","));
		
		String result= StringUtil.removeSpecialChar("$100.2$$1", "$");
		System.out.println(result);
		
		
		String floatString="30a";
		System.out.println(StringUtil.isFloat(floatString));
		System.out.println(StringUtil.isFloatPointNumber(floatString));
		
	}

}
