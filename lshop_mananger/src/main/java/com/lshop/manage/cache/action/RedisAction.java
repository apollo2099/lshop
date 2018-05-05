package com.lshop.manage.cache.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.cryption.MD5;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.cache.LvAreaCache;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountInfo;
import com.lshop.common.pojo.user.UnifiedUser;
import com.lshop.common.util.CommonUtil;
import com.lshop.common.util.Constants;
import com.lshop.manage.blog.action.LvBlogContentAction;
import com.lshop.manage.cache.service.RedisService;
import com.lshop.manage.common.action.BaseManagerAction;

@Controller("RedisAction")
@Scope("prototype")
@SuppressWarnings("all")
public class RedisAction extends BaseManagerAction {
	private static final Log logger = LogFactory.getLog(RedisAction.class);

	@Resource
	private RedisService redisService;

	public void initActivityCouponCache() throws Exception {
		redisService.initActivityCouponCache();
	}

}
