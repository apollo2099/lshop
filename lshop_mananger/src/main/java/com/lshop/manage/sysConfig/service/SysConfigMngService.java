
package com.lshop.manage.sysConfig.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvSysConfig;

public interface SysConfigMngService extends BaseService{

	/**
	 * 分页查询
	 */
	public Pagination findPage(Dto dto) throws ServiceException;

	/**
	 * 获得单独的实体信息
	 */
	public LvSysConfig getById(Dto dto) throws ServiceException;
	
	/**
	 * 添加
	 */
	public LvSysConfig save(Dto dto) throws ServiceException;
	
	/**
	 * 是否已经存在
	 */
	public Boolean isExist(Dto dto) throws ServiceException;
	
	/**
	 * 初始化数据
	 */
	public Boolean initData(Dto dto) throws ServiceException;
	
	/**
	 * 批量删除
	 */
	public Boolean delList(Dto dto) throws ServiceException;

}
