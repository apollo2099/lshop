package com.lshop.common.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lshop.common.pojo.logic.LvArea;

public class LvAreaCache {
	public static List<LvArea> list = new ArrayList<LvArea>();
	
	/**
	 * 根据国家code获取英文国家名称
	 * @param countryCode	国家code
	 * @return
	 */
	public static String getCountryNameen(String countryCode) {
		LvArea area = getCountry(countryCode);
		if (area == null) {
			return null;
		}
		return area.getNameen();
	}
	
	/**
	 * 根据国家code获取国家对象
	 * @param countryCode	国家code
	 * @return
	 */
	public static LvArea getCountry(String countryCode) {
		LvArea lvArea = null;
		List<LvArea> countryList = getCountryList();
		
		if (StringUtils.isBlank(countryCode)) {
			return null;
		}
		for (int i = 0; i < countryList.size(); i++) {
			lvArea = (LvArea) countryList.get(i);
			if (countryCode.equals(lvArea.getCode())) {
				break;
			}
		}
		return lvArea;
	}

	/**
	 * 获取国家对象列表
	 * @return
	 */
	public static List<LvArea> getCountryList() {
		List<LvArea> countryList = new ArrayList<LvArea>();
		if (list != null) {
			LvArea area = null;
			for (int i = 0; i < list.size(); i++) {
				area = list.get(i);
				if (area.getParentid() == null && StringUtils.isNotBlank(area.getCode())) {
					countryList.add(area);
				}
			}
		}
		return countryList;
	}

	public static void setList(List<LvArea> list) {
		LvAreaCache.list = list;
	}
	
}
