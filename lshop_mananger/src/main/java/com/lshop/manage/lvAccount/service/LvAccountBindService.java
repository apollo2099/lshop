package com.lshop.manage.lvAccount.service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvUserTh;

public interface LvAccountBindService {

	public Pagination getPagination(Dto dto) throws ServiceException;
	
	public void del(Dto dto) throws ServiceException;
	
	public void delList(Dto dto) throws ServiceException;
	
	public LvUserTh get(Dto dto) throws ServiceException ;

	

}
