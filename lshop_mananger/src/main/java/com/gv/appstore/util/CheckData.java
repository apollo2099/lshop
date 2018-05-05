package com.gv.appstore.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gv.appstore.pojo.LvDeveloperTemplet;

public class CheckData {
	public static String checkData(String data) {
		String regEx = "[`~!#$%^&*()+=|{}':;',//[//]<>/?~！#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(data);
		data =  m.replaceAll("").trim();
		return data;
	}
	
	public static boolean matcherDate(String data){
		String regEx = "[`~!#$%^&*()+=|{}':;',//[//]<>/?~！#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(data);
		if(m.find()){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		String s = "1232111@sds";
		System.out.println(matcherDate(s));
	}
}
