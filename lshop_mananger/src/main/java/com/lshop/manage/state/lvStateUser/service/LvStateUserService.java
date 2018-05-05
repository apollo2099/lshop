package com.lshop.manage.state.lvStateUser.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;

public interface LvStateUserService{

	public Pagination getList(Dto dto) throws ServiceException;
	public Boolean synchronousData(Dto dto)throws ServiceException;
	public List exportUserOrder(Dto dto)throws Exception ;
}
