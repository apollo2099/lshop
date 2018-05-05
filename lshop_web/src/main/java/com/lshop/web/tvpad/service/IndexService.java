package com.lshop.web.tvpad.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvStore;

/**
 * 店铺模块
 * @author zhengxue
 * @since 2.0 2012-07-20
 *
 */
public interface IndexService {
	
	/**
	 * 获取店铺信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvStore getStore(Dto dto) throws ServiceException;
	
	/**
	 * 获取国家及关联省列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto getAreas(Dto dto) throws ServiceException;
	
	/**
	 * 获取国家及关联省列表（如果是中国则显示中文，其他显示英文）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto getCnAreas(Dto dto) throws ServiceException;

}

