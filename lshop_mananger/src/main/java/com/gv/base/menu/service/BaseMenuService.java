package com.gv.base.menu.service;

import java.util.List;

import com.gv.base.menu.common.pojo.BaseMenu;
import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

public interface BaseMenuService {

	/**
	 * 
	 * 功能描述：   获取所有菜单
	 * 创建人：yangzheng  
	 * 创建时间：2012-2-28 下午01:16:03   
	 * 修改人：yangzheng   
	 * 修改时间：2012-2-28 下午01:16:03   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<BaseMenu> getAllMenu(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：   添加菜单
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:37:55   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:37:55   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void save(Dto dto) throws ServiceException;
	
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
	 * 功能描述： 获取单个菜单信息
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:51   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:51   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public BaseMenu get(Dto dto) throws ServiceException ;
}
