package com.lshop.common.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

public class SqlMapUtils {

	public static void merge(Map<String, List<Object[]>> src, Map<String, List<Object[]>> other) {

		if (null != src && MapUtils.isNotEmpty(other)) {

			for (Map.Entry<String, List<Object[]>> entry : other.entrySet()) {

				if (src.containsKey(entry.getKey())) {

					src.get(entry.getKey()).addAll(entry.getValue());

				} else {

					src.put(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	public static void mergeAll(Map<String, List<Object[]>> src, Map<String, List<Object[]>>... others) {

		if (src != null && !ArrayUtils.isEmpty(others)) {

			for (Map<String, List<Object[]>> other : others) {

				for (Map.Entry<String, List<Object[]>> entry : other.entrySet()) {

					if (src.containsKey(entry.getKey())) {

						src.get(entry.getKey()).addAll(entry.getValue());

					} else {

						src.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
	}

}
