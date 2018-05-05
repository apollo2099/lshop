package com.lshop.web.weixin.wxUser;

import java.util.Calendar;

import javax.annotation.Resource;
import org.junit.Test;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.test.BaseTest;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxKeywordsReply;
import com.lshop.web.weixin.common.pojo.WxObtainNewsTpl;
import com.lshop.web.weixin.common.pojo.WxUser;
import com.lshop.web.weixin.util.HttpClientUtil;
import com.lshop.web.weixin.wxGzhConfig.service.WxGzhConfigService;
import com.lshop.web.weixin.wxKeywordsReply.service.WxKeywordsReplyService;
import com.lshop.web.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;
import com.lshop.web.weixin.wxUser.service.WxUserService;

public class WxUserServiceTest extends BaseTest{
	@Resource WxUserService service;

	public void getByKeyword() {
		WxUser user = new WxUser();
		user.setOpenid("ss");
		user.setCreateTime(Calendar.getInstance().getTime());
		user.setNickname("d");
		user.setWxhConfigId(1);
		user.setObtainAmount(0);
		BaseDto dto =new BaseDto();
		dto.put("model", user);
		service.save(dto);
	}

	@Test
	public void getUserInfo() {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=e3Jz1zjOuLptLgwTdu5wwf3uRhs8ofETCZdPTeysdGBLLHLQ0eRtyKFrWKHgJFIjiAZRipMhkrhUMgBnvSYExcxWMYB767X8dn57Q-Bfp2s&openid=oTqsQuKmNOhycpXRVK3P1mP1vnSs&lang=zh_CN";
		String res = HttpClientUtil.get(url);
		System.out.println(res);
	}
}
