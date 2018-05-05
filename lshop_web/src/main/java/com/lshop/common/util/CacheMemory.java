package com.lshop.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lshop.common.pojo.logic.LvLinkUrl;

/**
 * 缓存信息
 * 
 * @author xusl
 * 
 */
public class CacheMemory {


	public static List<LvLinkUrl> PAGELINKLIST = null;// 静态页面列表
	public static String DOMAINADDRESS="http://www.lwstb.com";//域名地址
	public static String HTMLPATH="";//地址
	public static Map<String,String> LOGISTICSSERVICEID=new HashMap<String, String>();//物流公司信息
	static{
		LOGISTICSSERVICEID.put("DHL", "DHLLogisticsTrackingService");
		
	}
	
}
