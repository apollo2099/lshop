package com.lshop.web.userCenter;

import com.lshop.common.util.PropertiesHelper;



public class UserConstant {
	public final static String USER_CENTER = "user";
	public final static String MD5_USER_CENTER = "MD5user";
	public final static String USER_ID = "userid";
	public final static String SHOPCART_NUM = "shopcartNum";
	public final static String SHOPCART = "shopcart";
	public final static String STORE = "store";

	public final static String BIZLINE_B = "0007";
	public final static String BIZLINE_T = "0008";

	public final static String TERMINAL_PC = "1";
	public final static String TERMINAL_ANDROID = "2";
	public final static String TERMINAL_TVPAD = "3";
	
	public final static String SESSION_CODE_NAME = "rcode";//验证码键
	
	public final static String AUTH_THIRD_TYPE_MOBILE = "mobile";
	public final static String AUTH_THIRD_TYPE_WX = "wx";
	
	public final static String WEIXIN = "micromessenger";
	public static String WEIXIN_APP_ID = "";
	public static String WEIXIN_APP_SECRET = "";
	public static String WEIXIN_REDIRECT_URL_TVPAD = "http://m.mtvpad.com/web/auth_redirect";
	public static String WEIXIN_REDIRECT_URL_BANANA = "http://m.bananatv.com/web/auth_redirect";
	public final static String WEIXIN_REQ_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={appid}&redirect_uri={url}&response_type=code&scope=snsapi_base&state={state}#wechat_redirect";
	
	public final static String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	
	static{
		WEIXIN_APP_ID = PropertiesHelper.getProperty("weixin_app_id");
		WEIXIN_APP_SECRET = PropertiesHelper.getProperty("weixin_app_secret");
	}
}
