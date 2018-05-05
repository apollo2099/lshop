package com.lshop.web.weixin.wxNewsMaterialItem;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;

import com.gv.core.proxy.ServiceConstants;
import com.gv.test.BaseTest;
import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxKeywordsReply;
import com.lshop.web.weixin.common.pojo.WxNewsMaterialItem;
import com.lshop.web.weixin.common.pojo.WxObtainNewsTpl;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.send.TextMessage;
import com.lshop.web.weixin.util.JAXBUtil;
import com.lshop.web.weixin.wxKeywordsReply.service.WxKeywordsReplyService;
import com.lshop.web.weixin.wxNewsMaterial.service.WxNewsMaterialService;
import com.lshop.web.weixin.wxNewsMaterialItem.service.WxNewsMaterialItemService;
import com.lshop.web.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;
import com.lshop.web.weixin.wxTextMaterial.service.WxTextMaterialService;

public class WxNewsMaterialItemServiceTest extends BaseTest{
	@Resource WxNewsMaterialItemService wxNewsMaterialItemService;
	@Test
	public void getByKeyword() {
		List<WxNewsMaterialItem> list = wxNewsMaterialItemService.getByNewsId(4,5);
		System.out.println(list.size());
	}

	
}
