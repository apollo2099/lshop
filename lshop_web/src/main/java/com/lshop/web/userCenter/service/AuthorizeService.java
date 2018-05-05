package com.lshop.web.userCenter.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvUserTh;

/**
 * 授权
 * @author dingh
 *
 */
public interface AuthorizeService {
	
	public LvUserTh getLvUserTh(Dto dto) throws ServiceException;
	
	public boolean isBind(Dto dto) throws ServiceException;
	
	public LvUserTh bind(Dto dto) throws ServiceException;
	
}
