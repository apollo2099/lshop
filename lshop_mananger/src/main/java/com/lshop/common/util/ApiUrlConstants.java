package com.lshop.common.util;

public class ApiUrlConstants {
	public final static String URL_ROOT = PropertiesHelper.getProperty("api.host");
	
	public final static String bizline = PropertiesHelper.getProperty("epg.bizline");
	
	public final static String terminal = PropertiesHelper.getProperty("epg.terminal");
	
	public final static String USER_LIST = URL_ROOT + "/api/userlist?bizline=" + bizline;
	
	public final static String USER_EDIT = URL_ROOT + "/api/useredit?bizline=" + bizline + "&terminal=" + terminal;
}
