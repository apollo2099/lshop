package com.lshop.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 资源国际化工具类
 * 说明：I18nCache2为I18nCache工具类型的升级版本。由于原来的I18nCache类中语种与店铺实际语种不能一一对应，如：www店铺对应的却是zh_CN资源文件，很混乱。
 *       故对原类I18nCache进行升级并取名为I18nCache2。旧的工具类I18nCache在后续中应该逐步丢弃。
 * @author jinjian 2014-12-17
 *
 */
public class I18nCache2 {
	public static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
	private static Map<String, String> storeLangMap = new HashMap<String, String>();
	private final static String propertiesPrefix = "i18n_";
	private final static String propertiesSuffix = ".properties";
	
	/**
	 * 店铺与资源文件名称映射
	 */
	static {
		//Tvpad 商城体系映射
		storeLangMap.put("www", "zh_TW");
		storeLangMap.put("tvpadcn", "zh_TW");
		storeLangMap.put("en", "en");
		storeLangMap.put("tvpaden", "en");
		storeLangMap.put("mtmcn", "zh_CN");
		storeLangMap.put("mtscn", "zh_CN");
		//BananaTv 商城体系映射		
		storeLangMap.put("bmcn", "zh_CN");
		storeLangMap.put("bscn", "zh_CN");
		storeLangMap.put("bmen", "en");
		storeLangMap.put("bsen", "en");
		storeLangMap.put("mbmcn", "zh_CN");
		storeLangMap.put("mbscn", "zh_CN");
	}
	
	/**
	 * 获取资源的值
	 * @param key
	 * @param storeFlag 根据浏览器页面访问时的域名对应的店铺标识，通常在action中通过this.getFlag()获得
	 * @return
	 */
	public static String getValue(String key, String storeFlag) {
		storeFlag = storeFlag.toLowerCase();
		String lang = storeLangMap.get(storeFlag);
		try {
			Properties bundle = getProperties(getPropertiesName(lang));
			String value = bundle.getProperty(key);
			if (value != null) {
				value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
				value = value.trim();
			}
			return value;			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}	

	/**
	 * 获取Properties文件对象
	 * @param propertiesName
	 * @return
	 * @throws IOException
	 */
	private static Properties getProperties(String propertiesName) throws IOException {
		if (propertiesMap.get(propertiesName) == null) {
			Properties prop = new Properties();
			InputStream in = I18nCache2.class.getResourceAsStream("/" + propertiesName);
			prop.load(in);
			propertiesMap.put(propertiesName, prop);
			return prop;
		} else {
			return propertiesMap.get(propertiesName);
		}
	}
	
	private static String getPropertiesName(String lang){
		return propertiesPrefix + lang + propertiesSuffix;
	}

	public static void main(String[] args) {
		String s = I18nCache2.getValue("shopCart.empty", "www");
		System.out.println(s);
	}

}
