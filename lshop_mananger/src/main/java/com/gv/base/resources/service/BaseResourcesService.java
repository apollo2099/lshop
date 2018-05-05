package com.gv.base.resources.service;

import com.gv.base.springSecurity.common.pojo.BaseResources;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;

public interface BaseResourcesService {

	/**
	 * 
	 * 功能描述：   获取所有
	 * 创建人：yangzheng  
	 * 创建时间：2012-2-28 下午01:16:03   
	 * 修改人：yangzheng   
	 * 修改时间：2012-2-28 下午01:16:03   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：   添加
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:37:55   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:37:55   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public Dto save(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：   更新
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:38:27   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:38:27   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void update(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：   删除
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:38:49   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:38:49   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public void delete (Dto dto) throws ServiceException;
	
	
	/**
	 * 
	 * 功能描述： 获取单个信息
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:51   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:51   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public BaseResources get(Dto dto) throws ServiceException ;

}
