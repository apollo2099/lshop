
package com.lshop.web.weixin.util;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * 加载微信属性文件
 * @author JinJian 2015-01-13
 *
 */
public class WeiXinProperties {
	private static PropertiesConfiguration config = null;
	static {
		try {
			config = new PropertiesConfiguration("weixin.properties");
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private WeiXinProperties() {
	}
	
	public static String get(String key) {
		Object value = config.getProperty(key);
		if (value == null) {
			return null;
		}
		return value.toString().trim();
	}
	
	public static void main(String[] args) {
		System.out.println(WeiXinProperties.get("weixin.res.url"));
	}	
}
