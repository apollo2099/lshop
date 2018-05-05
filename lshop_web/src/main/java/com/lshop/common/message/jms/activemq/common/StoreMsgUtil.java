package com.lshop.common.message.jms.activemq.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StoreMsgUtil {
	
	/**
	 * 
	 * @Method: processStoreMSGString 
	 * @Description:  [将JSONObject转换成Map<String,String>数据格式输出]    
	 * @param obj JSONObject
	 * @return Map<String,String>
	 */
	public static Map<String,String> processStoreMSGString(JSONObject obj){
		Map<String,String> param=new HashMap<String, String>();
        for (Iterator iter = obj.keys(); iter.hasNext();) { 
            String key= (String)iter.next();
            param.put(key, obj.getString(key));
        }
	    return param;
	}
	/**
	 * 
	 * @Method: processStoreMSGString 
	 * @Description:  [将JSONObject转换成Map<String,Integer>数据格式输出]    
	 * @param obj
	 * @return Map<String,Integer>
	 */
	public static Map<String,Integer> processStoreMSGInteger(JSONObject obj){
		Map<String,Integer> param=new HashMap<String, Integer>();
		for (Iterator iter = obj.keys(); iter.hasNext();) { 
	            String key= (String)iter.next();
	            System.out.println();
	            param.put(key, Integer.parseInt(obj.getString(key)));
	    }
	    return param;
	}
	
	/**
	 * 
	 * @Method: processStoreMSGString 
	 * @Description: [将JSONObject转换成Map<String,List<String>>数据格式输出]    
	 * @param obj
	 * @return Map<String,List<String>>
	 */
	public static Map<String,List<String>> processStoreMSGList(JSONObject obj){
		Map<String,List<String>> param=new HashMap<String, List<String>>();
		for (Iterator iter = obj.keys(); iter.hasNext();) { 
            String key= (String)iter.next();
            JSONArray ja=  obj.getJSONArray(key);
			  List<String> list=new ArrayList<String>();
			  for (int j = 0; j < ja.size(); j++) {
				    String tempJSON=(String)ja.get(j);
				    list.add(tempJSON);
			  }
			  param.put(key, list);
        }
	    return param;
	}
	
	
	public static final String TVPAD_PRE = "gv_tvpad@";
	public static final String BANANATV_PRE = "gv_bananatv@";
	public static final String TVPAD_BASEDOMAIN = ".mtvpad.com";
	public static final String BANANATV_BASEDOMAIN = ".bananatv.com";
	/**
	 * @Method: processConfigParam 
	 * @Description:  [将JSONObject转换成Map<String,String>数据格式输出
	 * 还原配置名称项
	 * @param obj JSONObject
	 * @return Map<String,String>
	 */
	public static Map<String,String> processConfigParam(JSONObject obj,String flag){
		Map<String,String> param=new HashMap<String, String>();
        for (Iterator iter = obj.keys(); iter.hasNext();) { 
            String key= (String)iter.next();
            if(flag.equals(TVPAD_BASEDOMAIN)){
            	if(key.startsWith(BANANATV_PRE)){continue;};
            	param.put(key, obj.getString(key));
            }
            if(flag.equals(BANANATV_BASEDOMAIN)){
            	if(key.startsWith(TVPAD_PRE)){continue;};
            	param.put(key, obj.getString(key));
            }
        }
	    return param;
	}

}
