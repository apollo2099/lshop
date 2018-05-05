package com.lshop.web.weixin.wxGzhConfig;

import javax.annotation.Resource;
import org.junit.Test;
import com.gv.test.BaseTest;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxKeywordsReply;
import com.lshop.web.weixin.common.pojo.WxObtainNewsTpl;
import com.lshop.web.weixin.wxGzhConfig.service.WxGzhConfigService;
import com.lshop.web.weixin.wxKeywordsReply.service.WxKeywordsReplyService;
import com.lshop.web.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;

public class WxGzhConfigServiceTest extends BaseTest{
	@Resource WxGzhConfigService service;

	@Test
	public void getByKeyword() {
		WxGzhConfig conf = service.getByCode("c1b7e1e9bd434dd892dddeea43dcaf18");
		if (conf == null) {
			System.out.println("conf is null");
		} else {
			System.out.println(conf.getWxhName());			
		}
	}

}
