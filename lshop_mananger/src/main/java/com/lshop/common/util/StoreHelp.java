package com.lshop.common.util;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
		Set<String> keyset=Constants.STORE_LIST.keySet();
		for (String key : keyset) {
			if(Constants.STORE_LIST.get(key).toString().indexOf("'"+flag+"'")!=-1){
				return key;
			}
		}
		return null;
	}
	
}
