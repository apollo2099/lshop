
package com.gv.appstore.mng.service;

import com.gv.appstore.pojo.LvDeveloper;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

public interface DeveloperMngService extends BaseService{

	/**
	 * 分页查询
	 */
	public Pagination findPage(Dto dto) throws ServiceException;

	/**
	 * 获得单独的实体信息
	 */
	public LvDeveloper get(Dto dto) throws ServiceException;
	
	/**
	 * 添加
	 */
	public LvDeveloper add(Dto dto) throws ServiceException;
	
	/**
	 *审核
	 */
	public LvDeveloper verify(Dto dto) throws ServiceException;


}
