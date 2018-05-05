
package com.gv.appstore.mng.service;

import com.gv.appstore.pojo.LvDeveloperTemplet;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

public interface EmailMngService extends BaseService{

	/**
	 * 分页查询
	 */
	public Pagination findPage(Dto dto) throws ServiceException;

	/**
	 * 获得单独的实体信息
	 */
	public LvDeveloperTemplet get(Dto dto) throws ServiceException;
	
	/**
	 * 根据相关参数获得单独的实体信息
	 */
	public LvDeveloperTemplet getBy(Dto dto) throws ServiceException;
	
	/**
	 * 添加
	 */
	public LvDeveloperTemplet add(Dto dto) throws ServiceException;
	
	/**
	 * 修改
	 */
	public LvDeveloperTemplet edit(Dto dto) throws ServiceException;
	
	/**
	 * 删除单个实体
	 */
	public void del(Dto dto) throws ServiceException;

	/**
	 * 删除单个或多个实体
	 */
	public void delList(Dto dto) throws ServiceException;
	
	/**
	 * 是否已经添加
	 */
	public Boolean isExist(Dto dto) throws ServiceException;

}
