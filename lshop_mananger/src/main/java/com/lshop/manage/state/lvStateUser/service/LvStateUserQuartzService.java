package com.lshop.manage.state.lvStateUser.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

public interface LvStateUserQuartzService extends Runnable{
	public void init()throws ServiceException;
	public void start(Dto dto)throws ServiceException;

}
