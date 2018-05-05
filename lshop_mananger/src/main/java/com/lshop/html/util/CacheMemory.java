package com.lshop.html.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存信息
 * 
 * @author xusl
 * 
 */
public class CacheMemory {



	public static Map<String,String>LOGISTICSSERVICEID=new HashMap<String, String>();//物流公司信息
	static{
		LOGISTICSSERVICEID.put("DHL", "DHLLogisticsTrackingService");
		
	}
	
}
