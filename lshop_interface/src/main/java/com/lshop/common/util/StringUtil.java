package com.lshop.common.util;

import java.util.Map;

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
		
	}

}
