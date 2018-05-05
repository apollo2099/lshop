package com.lshop.web.weixin;

public class WxConstant {
	/**
	 * 获取access token 接口凭证url
	 */
	public static final String url_access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	/**
	 * 用户信息
	 */
	public static final String url_user_info = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN";
	
	/**
	 * 消息类型常量
	 */
	public static final String MsgType_text = "text"; //文本消息
	public static final String MsgType_news = "news"; //图文消息
	public static final String MsgType_event = "event"; //事件消息
	
	/**
	 * 事件类型
	 */
	public static final String Event_click = "click"; //自定义菜单单击事件
	public static final String Event_view = "view"; //自定义菜单URL事件
	public static final String Event_subscribe = "subscribe"; //订阅
	
	/**
	 * 素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文 
	 */
	public static final int material_type_text = 1;
	public static final int material_type_image = 2;
	public static final int material_type_voice = 3;
	public static final int material_type_video = 4;
	public static final int material_type_music = 5;
	public static final int material_type_news = 6;
	
	/**
	 * 每次领取的金额(单位：元)
	 */
	public static final int obtain_amount = 100;
	
	public static final String responseTemplate = "'{'res:\"{0}\",msg:\"{1}\"'}'";
	public static final String response_res_ok = "ok";
	public static final String response_res_error = "error";
	
	public static String WEIXIN_AUTH_REDIRECT = "/web/wxAuth_redirect";
	
	/**
	 * cookie数据项
	 */
	public static final String cookie_obtainDate_for_friend = "obtainDateForFriend";
	public static final String cookie_obtainDate_self = "obtainDateSelf";
}
