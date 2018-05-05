package com.lshop.common.payutil.kq;


public class ParamConn {
	
	/** 
	 * 功能函数：
	 * 将变量值不为空的参数组成字符串 
	 * */
	public static StringBuffer appendParam(StringBuffer returnStr, String paramId,
			String paramValue) {
		if(paramValue==null){
			paramValue="";
		}
		if(returnStr==null){
			returnStr=new StringBuffer();
			if(!"".equals(paramValue)){
				returnStr.append(paramId).append("=").append(paramValue);
			}
		}else{
			if(!"".equals(paramValue)){
				returnStr.append("&").append(paramId).append("=").append(paramValue);
			}
		}
		return returnStr;
	}
}
