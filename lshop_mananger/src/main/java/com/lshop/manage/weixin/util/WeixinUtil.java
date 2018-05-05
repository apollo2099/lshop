package com.lshop.manage.weixin.util;



import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.lshop.manage.weixin.pojo.Menu;

public class WeixinUtil {
	private static final Log logger	= LogFactory.getLog(WeixinUtil.class);  
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	/** 
	 * 创建菜单 
	 *  
	 * @param menu 菜单实例 
	 * @param accessToken 有效的access_token 
	 * @return 0表示成功，其他值表示失败 
	 */  
	public static int createMenu(Menu menu, String accessToken) {  
	    int result = 0;  
	  
	    // 拼装创建菜单的url  
	    String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
	    // 将菜单对象转换成json字符串  
	    String jsonMenu = JSONObject.fromObject(menu).toString();  
	    // 调用接口创建菜单  
	    logger.info("创建微信公众号菜单信息："+url+",data:"+jsonMenu);
	    String responseStr = HttpClientUtil.post(url, jsonMenu); 
	    JSONObject jsonObject = JSONObject.fromObject(responseStr);
	    logger.info("创建微信公众号菜单结果："+jsonObject);
	  
	    if (null != jsonObject ) {  
	        if (0 != jsonObject.getInt("errcode")) {  
	            result = jsonObject.getInt("errcode");  
	        }  
	    }  
	  
	    return result;  
	}  
}
