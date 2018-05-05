package com.lshop.common.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class I18nCache {

	public static Map<String, ResourceBundle> resourceMap = new HashMap<String, ResourceBundle>();
	public static Map<String, String[]> keyMap = new HashMap<String, String[]>();
	
	/* 配置对应模块所使用的资源文件 */
	static {
		keyMap.put("bmcn", new String[]{"i18n_common", "zh_CN"});
		keyMap.put("bmen",  new String[]{"i18n_common", "en_US"});
		keyMap.put("www", new String[]{"i18n_common", "zh_CN"});
		keyMap.put("en",  new String[]{"i18n_common", "en_US"});
		keyMap.put("mtmcn", new String[]{"i18n_common", "zh_CN"});
		keyMap.put("mbmcn",  new String[]{"i18n_common", "zh_CN"});
	}

	/**
	 * 获取用户国际级资源对象
	 * 
	 * @return
	 */
	private static ResourceBundle getResourceBundle(String name, String loacaltype) {
		String key = name + loacaltype;
		ResourceBundle bundle = null;
		if (resourceMap.get(key) == null) {
			String language = loacaltype.substring(0, loacaltype.indexOf("_"));
			String country = loacaltype.substring(loacaltype.indexOf("_") + 1);

			Locale loacal = new Locale(language, country);
			bundle = ResourceBundle.getBundle(name, loacal);
			resourceMap.put(key, bundle);
		} else {
			bundle = (ResourceBundle) resourceMap.get(key);
		}
		return bundle;
	}

	/**
	 * 获取国际化资源
	 * 
	 * @param resKey	资源文件中定义的key
	 * @param storeFlag	店铺标识
	 * @return
	 */
	public static String getResourceValue(String resKey, String storeFlag) {
		String[] item = keyMap.get(storeFlag);
		ResourceBundle bundle = getResourceBundle(item[0], item[1]);
		return bundle.getString(resKey);
	}
	
	public static void main(String[] args) {
		String s = getResourceValue("common.service.tel", "bmcn");
		System.out.println(s);
	}

}
