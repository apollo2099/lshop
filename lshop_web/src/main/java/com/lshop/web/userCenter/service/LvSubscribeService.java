package com.lshop.web.userCenter.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvUserSubscribe;

public interface LvSubscribeService {
	public List list(Dto dto) throws ServiceException;
	public LvUserSubscribe save(Dto dto)throws ServiceException;
	public LvUserSubscribe get(Dto dto)throws ServiceException;
	public LvUserSubscribe delete(Dto dto)throws ServiceException;
	public LvUserSubscribe getUserSubscribe(Dto dto)throws  ServiceException;
}
