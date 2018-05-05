
package com.gv.appstore.mng.service;

import com.gv.appstore.pojo.LvDeveloperApp;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

public interface DevAppMngService extends BaseService{

	/**
	 * 分页查询
	 */
	public Pagination findPage(Dto dto) throws ServiceException;

	/**
	 * 获得单独的实体信息
	 */
	public LvDeveloperApp get(Dto dto) throws ServiceException;
	
	/**
	 * 添加
	 */
	public LvDeveloperApp add(Dto dto) throws ServiceException;
	
	/**
	 *审核
	 */
	public void verify(Dto dto) throws ServiceException;


}
