package com.lshop.web.userCenter.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvEmailTpl;

/**
 * 邮件发送——找回密码模块
 * @author zhengxue
 * @since 2.0 2012-07-03
 *
 */
public interface LvEmailManageService {
	public LvEmailTpl get(Dto dto) throws ServiceException;
}
