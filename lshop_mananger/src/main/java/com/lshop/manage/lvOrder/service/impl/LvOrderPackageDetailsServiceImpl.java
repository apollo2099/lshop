package com.lshop.manage.lvOrder.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.manage.lvOrder.service.LvOrderPackageDetailsService;
@Service("LvOrderPackageDetailsService")
public class LvOrderPackageDetailsServiceImpl implements LvOrderPackageDetailsService {
	private static final Log logger	= LogFactory.getLog(LvOrderPackageDetailsServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;

	@Override
	public void save(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		LvOrderPackageDetails lvOrderPackageDetails=(LvOrderPackageDetails) dto.get("lvOrderPackageDetails");
		dao.save(lvOrderPackageDetails);
		
	}

}
