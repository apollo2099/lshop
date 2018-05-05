package com.lshop.manage.weixin.wxUser.service.impl;



import java.util.Date;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.BaseSpringTestCase;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.weixin.pojo.WxUser;
import com.lshop.manage.weixin.util.HttpClientUtil;

public class wxUserTest extends BaseSpringTestCase{

	@Resource private HibernateBaseDAO dao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testZm() throws Exception{
		String getWxUserInfo_url_pre="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
		getWxUserInfo_url_pre +="jCheyz-WeKXzCtWFf_TyLdoQ7Gw3g3_R8ynI6y800RefsYUflLvCAlo82_2jP8PCJOeVtaAiwTSQ-_sky-M3P7QH3DWRbUSqgxlto_cDyk4";
		getWxUserInfo_url_pre+="&openid="+"oLaXhsuQ1H1YeRkmHzthigAPx0RE";
		getWxUserInfo_url_pre+="&lang=zh_CN";
		String userInfoJson ="";
		userInfoJson = HttpClientUtil.post(getWxUserInfo_url_pre);
		//请求成功
		String openId = JSONObject.fromObject(userInfoJson).getString("openid");
		String nickname = JSONObject.fromObject(userInfoJson).getString("nickname");
		Pattern emoji = Pattern . compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]" ,Pattern . UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
		String reg = emoji.pattern();
		nickname =nickname.replaceAll(reg, "");
		WxUser user = new WxUser();
		user.setNickname(nickname);
		user.setOpenid(openId);
		user.setObtainAmount(0);
		user.setWxhConfigId(1);
		user.setCreateTime(new Date());
		user.setCreateTimeString(DateUtils.formatDate(user.getCreateTime(), "yyyy-MM-dd hh:mm:ss"));
		user.setModifyTime(new Date());
		user.setModifyTimeString(DateUtils.formatDate(user.getModifyTime(), "yyyy-MM-dd hh:mm:ss"));
		
		dao.save(user);
		System.out.print("OK");
	}

}
