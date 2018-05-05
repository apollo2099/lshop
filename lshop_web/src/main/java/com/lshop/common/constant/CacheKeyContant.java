package com.lshop.common.constant;

/**
 * CacheComponent的jmv缓存键
 * @author caicl
 *
 */
public class CacheKeyContant {
	/**
	 * 国家列表对象
	 */
	public final static String COUNTRY_LIST = "COUNTRY_LIST";
	/**
	 * 返回地区的父级地区键
	 * @param childId
	 * @return
	 */
	public final static String AreaParent(Integer childId) {
		return "AREA_P_"+childId;
	}
}
