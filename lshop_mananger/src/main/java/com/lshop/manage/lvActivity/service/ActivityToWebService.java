package com.lshop.manage.lvActivity.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

public interface ActivityToWebService {
	public void sendJSONToWeb(Dto dto)throws ServiceException;
}
