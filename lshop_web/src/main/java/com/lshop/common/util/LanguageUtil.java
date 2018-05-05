/**   
 * @filename: LanguageUtil.java
 * @description: TODO
 * @author：dh
 * @time：2014年3月31日 下午3:36:17
 * @version：V1.0   
 */

package com.lshop.common.util;

import com.gv.core.util.StringUtil;

/**   
 * @author：dh
 * @time：2014年3月31日 下午3:36:17
 * @version：V1.0   
 * @description: TODO
 */
public class LanguageUtil {
	public static final String ZH = "zh_CN";
	public static final String TW = "zh_TW";
	public static final String EN = "en_US";

	/**
	 * 根据店铺标记获取语言标记
	 */
	public static String getLan(String flag){
		if(StringUtil.IsNullOrEmpty(flag)){
			return EN;
		}
		if(flag.endsWith("en")){
			return EN;
		}
		if(flag.endsWith("cn") || flag.equals("www")){
			return ZH;
		}
		return "";
	} 
}
