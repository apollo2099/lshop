package com.lshop.web.applist.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvApp;

/**
 * 应用模块
 * @author zhengxue
 * @since 2.0 2012-07-20
 *
 */
public interface ApplistService {
	
	/**
	 * 获取所有的应用列表
	 * @param dto
	 * @return
	 * @throws ServiceExcetpion
	 */
	public List<LvApp> getApps(Dto dto) throws ServiceException;
	
	/**
	 * 通过code查找对应的LvApp
	 * 需要传递参数code
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvApp getAppByCode(Dto dto) throws ServiceException;
}
