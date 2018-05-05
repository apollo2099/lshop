package com.lshop.web.userCenter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.lshop.web.weixin.WxConstant;
import com.lshop.common.action.BaseAction;
import com.lshop.common.util.Constants;
import com.lshop.common.util.CookieUtil;
import com.lshop.common.util.HttpUtil;
import com.lshop.common.util.StoreHelp;

public class UserCommon extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	public UserCommon(){
		super();
	}

	/**
	 * 根据商城及店铺获取业务线
	 */
	public static String getBizline(String f) {
		if (f.equals("bmcn") || f.equals("bmen") || f.equals("mbmcn")
				|| f.equals("bscn") || f.equals("bsen"))
			return UserConstant.BIZLINE_B;
		if (f.equals("www") || f.equals("en") || f.equals("mtmcn")
				|| f.equals("tvpadcn") || f.equals("tvpaden"))
			return UserConstant.BIZLINE_T;
		return "";
	}

	/**
	 * 获取终端类型
	 */
	public static String getTerminal(String f) {
		if (f.equals("mbmcn") || f.equals("mtmcn"))
			return UserConstant.TERMINAL_ANDROID;
		return UserConstant.TERMINAL_PC;
	}
	
	/**
	 * 匹配cookie，判断是否登录
	 */
	public static Boolean compareUser(HttpServletRequest request){

		//获取两个cookie
		Map<String, String> user = null;
		Map<String, String> md5User = null;
		try {
			user = getCookieValueToMap(UserConstant.USER_CENTER, true, request);
			md5User = getCookieValueToMap(UserConstant.MD5_USER_CENTER, true, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(ObjectUtils.isNotEmpty(user) && ObjectUtils.isNotEmpty(md5User)){
			//获取前台cookie加密后的属性
			String MD5_uid = MD5.convert32(user.get("uid"));
			String MD5_id = MD5.convert32(user.get("id"));
			String MD5_email = MD5.convert32(user.get("email"));
			
			//两个cookie属性值进行比较
			if(md5User.get("uid").equals(MD5_uid) && md5User.get("id").equals(MD5_id) 
					&& md5User.get("email").equals(MD5_email)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	public static Map<String, String> getCookieValueToMap(String name,
			boolean isDecode, HttpServletRequest request) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		String cookieValue = getCookieValue(name, isDecode, request);
		if (cookieValue == null || "".equals(cookieValue)
				|| cookieValue.indexOf("*") < 0) {
			return resultMap;
		}

		StringTokenizer token = new StringTokenizer(cookieValue, "*");
		while (token.hasMoreTokens()) {
			String value = token.nextToken();
			StringTokenizer bToken = new StringTokenizer(value, "=");
			String mapKey = bToken.nextToken();
			String mapValue = bToken.nextToken();
			resultMap.put(mapKey, mapValue);
		}

		return resultMap;
	}
	
	public static String getCookieValue(String name, boolean isDecode, HttpServletRequest request)
			throws Exception {
		String value = CookieUtil.getValue(request, name, isDecode);
		if (value == null || value.length() == 0) {
			return null;
		} else {
			return value;
		}
	}
	
	/**
	 * 判断验证码
	 */
	public static boolean validateCode(String code, HttpSession session){
		code = (null==code)?"":code;
		if(!code.trim().equals(session.getAttribute(UserConstant.SESSION_CODE_NAME))){
			return false;
		}
		return true;
	}
	
	/**
	 * 微信授权
	 * 获取微信授权token
	 * 此处token为授权成功后获取到的openId
	 */
	public static String getWXRequestUrl(String flag,String sessionId,String type){
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(flag);
		String url = "";
		String redirect = "";
		if(mallSystem.equals("tvpad")){
			try {
				redirect = URLEncoder.encode(UserConstant.WEIXIN_REDIRECT_URL_TVPAD+"?sessionId="+sessionId+"&type="+type, "utf-8");
			} catch (UnsupportedEncodingException e) {
			}
			url = UserConstant.WEIXIN_REQ_URL.replace("{appid}", UserConstant.WEIXIN_APP_ID).
					replace("{url}", redirect).
					replace("{state}", "123");
		}
		if(mallSystem.equals("banana")){
			try {
				redirect = URLEncoder.encode(UserConstant.WEIXIN_REDIRECT_URL_BANANA+"?sessionId="+sessionId+"&type="+type, "utf-8");
			} catch (UnsupportedEncodingException e) {
			}
			url = UserConstant.WEIXIN_REQ_URL.replace("{appid}", UserConstant.WEIXIN_APP_ID).
					replace("{url}", redirect).replace("{state}", "123");
		}
		return  url;
	}
	
	/**
	 * 微信授权
	 * 获取微信授权token
	 * 此处token为授权成功后获取到的openId
	 */
	public static String getWXRequestUrl(String flag, String appid){
		String url = "";
		String storeDomain = StoreHelp.getStoreDomainByFlag(flag);
		String redirect = "http://"+storeDomain+WxConstant.WEIXIN_AUTH_REDIRECT;
		try {
			redirect = URLEncoder.encode(redirect, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		url = UserConstant.WEIXIN_REQ_URL.replace("{appid}", appid).
				replace("{url}", redirect).
				replace("{state}", "123");
		return url;
	}
	public static String getWXOauthUrl(String flag, String appid, String redirectUri){
		String url = "";
		String storeDomain = StoreHelp.getStoreDomainByFlag(flag);
		String redirect = "http://"+storeDomain+redirectUri;
		try {
			redirect = URLEncoder.encode(redirect, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		url = UserConstant.WEIXIN_REQ_URL.replace("{appid}", appid).
				replace("{url}", redirect).
				replace("{state}", "123");
		return url;
	}	
	
	public static void commonLoginout(String flag,HttpServletRequest request, HttpServletResponse response){
		CookieUtil cookieUtil = new CookieUtil();
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(flag); //获取当前店铺所属系统（华扬orBanana）
		cookieUtil.removeByCookie(request, response,UserConstant.USER_ID,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		cookieUtil.removeByCookie(request, response,UserConstant.USER_CENTER,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		cookieUtil.removeByCookie(request, response,UserConstant.MD5_USER_CENTER,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		cookieUtil.removeByCookie(request, response,UserConstant.SHOPCART_NUM,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
	}
	
	/**
	 * 将json数据转换成map
	 */
	public static Map<String,Object> getMapFromJson(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map<String,Object> map = new HashMap<String,Object>();
		for (Iterator<Object> iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}
	
	/**
	 * 获取微信openID
	 */
	public static String getWXOpenId(String code){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + UserConstant.WEIXIN_APP_ID
				+ "&secret=" + UserConstant.WEIXIN_APP_SECRET+"&code=" + code + "&grant_type=authorization_code";
		String res = HttpUtil.get(url);
		Map<String, Object> map = UserCommon.getMapFromJson(res);
		String openId = (String)map.get("openid");
		openId = openId==null?"":openId;
		return openId;
	}
	
	/**
	 * 获取微信openID
	 */
	public static String getWXOpenId(String code, String appid, String secret){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid
				+ "&secret=" + secret +"&code=" + code + "&grant_type=authorization_code";
		String res = HttpUtil.get(url);
		Map<String, Object> map = UserCommon.getMapFromJson(res);
		String openId = (String)map.get("openid");
		openId = openId==null?"":openId;
		return openId;
	}	
	
	/**
	 * 过滤跳转url
	 */
	public static String setSuitableJumpUrl(String jumpurl) {
		if (!StringUtil.IsNullOrEmpty(jumpurl)) {
			try {
				jumpurl = URLDecoder.decode(jumpurl, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if ((!StringUtil.IsNullOrEmpty(jumpurl))
				&& jumpurl.contains("?")
				&& jumpurl.contains("jumpurl=")) {
			String url = jumpurl;
			url = url.substring(url.indexOf("?") + 1);
			url = url.substring(url.indexOf("jumpurl=") + 8);
			if (url.contains("&")) {
				url = url.substring(0, url.indexOf("&"));
			}
			jumpurl = url;
		}
		
		if((!StringUtil.IsNullOrEmpty(jumpurl)) && jumpurl.contains("#")){
			jumpurl = jumpurl.substring(0, jumpurl.indexOf("#"));
		}

		if (StringUtil.IsNullOrEmpty(jumpurl)
				|| jumpurl.contains("/userCenterManage!login.action")
				|| jumpurl.contains("/userCenterManage!logout.action")
				|| jumpurl.contains("/userCenterManage!toRegister.action")) {
			jumpurl = "";
		}
		return jumpurl;
	}

}
