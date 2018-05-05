package com.lshop.web.recruit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.action.BaseAction;

/**
 * 招聘模块
 * @author zhengxue
 * @version 2.0 2012-08-27
 *
 */
@SuppressWarnings("serial")
@Controller("RecruitManageAction")
@Scope("prototype")
public class RecruitManageAction extends BaseAction {

	public String toRecruit() throws Exception{
		return "toRecruit";
	}
}
