package com.gv.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseUtil {

	/**
	 * 
	 * 功能描述： 生成  Code
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:49:38   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:49:38   
	 * 修改备注：
	 * @param prefix
	 * @return
	 */
	public static String getCode(String prefix){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss"); 
		int memidlen = prefix.toString().length();
		if(memidlen < 10) {
			for (int j = 1; j <= 10 - memidlen; j++) {
				prefix = "0" + prefix;
			}
		}else if (memidlen>10) {
			prefix = prefix.substring(0, 10);
		}
		return prefix + sf.format(new Date());
	}
}
