package com.lshop.web.userCenter.three.facebook.utils;

import net.sf.json.JSONObject;

import com.lshop.common.util.Constants;



public class FacebookConfig {

	private String appId;//应用编号
	private String secret;//应用密钥
	private String appUrl;//应用回调地址
	
	
	public FacebookConfig(){
		String config=Constants.ConfigParam.getMallConfigParam("facebook.config");
		JSONObject  json= JSONObject.fromObject(config);
		appId=json.getString("client_ID");
		secret=json.getString("client_SERCRET");
		appUrl=json.getString("redirect_URI");
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	
	




	
	
}
