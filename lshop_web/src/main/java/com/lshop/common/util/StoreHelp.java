package com.lshop.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.core.util.ObjectUtils;

public class StoreHelp {
	private static final Log logger = LogFactory.getLog(StoreHelp.class);
	
	/**
	 * 根据域名获取店铺标记
	 * @param domain
	 * @return
	 */
	public static String getStoreFlagByDomain(String domain) {
		if(domain==null)return null;
		return Constants.STORE_IDS.get(domain.trim());
	}
	/**
	 * 根据店铺标识获取域名
	 * @param flag
	 * @return
	 */
	public static String getStoreDomainByFlag(String flag) {
		if (flag == null)
            return null;
		Set<String> keyset=Constants.STORE_IDS.keySet();
		for (String key : keyset) {
			if(flag.equals(Constants.STORE_IDS.get(key).toString())){
				return key;
			}
		}
		 return null;
		
	}
	/**
	 * 根据店铺标识获取商城标识
	 * @param flag
	 * @return
	 */
	public  static String getParentFlagByFlag(String flag) {
		if (flag == null)
			return null;
		Map<String, String> storeMap = Constants.STORE_LIST;
		String key = null;
		for (Iterator<String> iterator = storeMap.keySet().iterator(); iterator.hasNext();) {
			key = iterator.next();
			if(storeMap.get(key).indexOf("'"+flag+"'")!=-1){
				return key;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @Method: getCurrencyByStoreId 
	 * @Description:  [根据店铺标志获取店铺币种信息]  
	 * @param storeId 店铺标志
	 * @return String 币种信息
	 */
	public static String getCurrencyByStoreId(String storeId){
		if(ObjectUtils.isNotEmpty(storeId)){
			return  Constants.STORE_TO_CURRENCY.get(storeId);
		}
		return null;
	}
	/**
	 * 根据店铺标识获取充值来源
	 * @param storeFlag	店铺标识
	 * @return
	 */
	public static int getRechargeResource(String flag) {
		int resource = 0;
		
		if (flag.equals("www") || flag.equals("tvpadcn")) {
			resource = 4;	// 华扬中文商城
		} else if (flag.equals("en") || flag.equals("tvpaden")) {
			resource = 5;	// 华扬英文商城
		} else if (flag.equals("mtmcn") || flag.equals("mtscn")) {
			resource = 6;	// 华扬中文移动商城
		} else if (flag.equals("bmcn") || flag.equals("bscn")) {
			resource = 8;	// bananna中文商城
		} else if (flag.equals("bmen") || flag.equals("bsen")) {
			resource = 9;	// bananna英文商城
		} else if (flag.equals("mbmcn") || flag.equals("mbscn")) {
			resource = 10;	// bananna中文移动商城
		}
		return resource;
	}
	
}
