package com.lshop.manage.lvStoreAddress.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.lshop.common.pojo.logic.LvStoreAddress;
import com.lshop.manage.lvStoreAddress.service.LvStoreAddressService;
@Service("LvStoreAddressService")
public class LvStoreAddressServiceImpl extends BaseServiceImpl implements LvStoreAddressService {
	@Resource private HibernateBaseDAO dao;

	@Override
	public LvStoreAddress findStoreAddress(Dto dto) throws ServiceException {
		String storeCode=(String) dto.get("storeCode");
		String hql="from LvStoreAddress where storeCode=:storeCode";
		Map param=new HashMap();
		param.put("storeCode", storeCode);
		LvStoreAddress lv=(LvStoreAddress) dao.findUnique(hql, param);
		return lv;
	}
}
