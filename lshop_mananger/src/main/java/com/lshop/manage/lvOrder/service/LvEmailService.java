package com.lshop.manage.lvOrder.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvEmailTpl;

public interface LvEmailService {
	public LvEmailTpl get(Dto dto) throws ServiceException;
	public Integer doNoticeFa(Dto dto)throws Exception;
}
