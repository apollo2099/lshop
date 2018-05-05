package com.lshop.common.coupon.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.lshop.common.coupon.vo.KeyValueVo;

/**
 * 
 * @Description: 重复使用
 * @author CYJ
 * @date 2013-12-10 下午3:54:02
 */
public class ReuseType {
	/**
	 * 是
	 */
	public static final Integer reuse = 1;
	/**
	 * 否
	 */
	public static final Integer use = 0;

	public static Map<Integer, String> map = new HashMap<Integer, String>();

	private static List<KeyValueVo> list = new ArrayList<KeyValueVo>();

	public static void initMap() {
		map.put(reuse, "是");
		map.put(use, "否");
	}

	public static void initList() {
		list.add(new KeyValueVo(reuse.toString(), "是"));
		list.add(new KeyValueVo(use.toString(), "否"));
	}

	public static String getName(Integer type) {
		String name = "";
		if (MapUtils.isEmpty(map)) {
			initMap();
		}
		if (map.containsKey(type)) {
			name = map.get(type);
		}
		return name;
	}

	public static List<KeyValueVo> getList() {
		if (CollectionUtils.isEmpty(list)) {
			initList();
		}
		return list;
	}
}
