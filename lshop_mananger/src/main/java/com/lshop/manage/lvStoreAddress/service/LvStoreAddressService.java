package com.lshop.manage.lvStoreAddress.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvStoreAddress;


public interface LvStoreAddressService extends BaseService{

	public LvStoreAddress findStoreAddress(Dto dto)throws ServiceException ;
}
