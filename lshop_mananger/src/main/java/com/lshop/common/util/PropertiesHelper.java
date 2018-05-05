
package com.lshop.common.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * 加载属性文件
 * @author xusl
 *
 */
public class PropertiesHelper {

	private static PropertiesConfiguration config = null;


	static {

		try {
			config = new PropertiesConfiguration("lshop.properties");
			config.setReloadingStrategy(new FileChangedReloadingStrategy());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PropertiesHelper() {

	}

	public static Configuration getConfiguration() {
		return config;
	}
	
	public synchronized static String getProperty(String key) {
		Object value = config.getProperty(key);
		if (value == null) {
			return null;
		}
		return value.toString().trim();
	}

}
