package com.lshop.web.developer.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvDeveloper;

/**
 * 
* @author luohaidong
* @Description: 开发者
* @date 2014年6月27日 下午3:36:54 
* @version V1.0
 */
public interface DeveloperService {

	/**
	 * 
	* @author:luohaidong 
	* @Description:(查询开发者信息) 
	* @param  @param dto
	* @param  @return
	* @param  @throws ServiceException 
	* @return LvDeveloper
	* @throws
	 */
	public LvDeveloper getInfo(Dto dto) throws ServiceException;
	
	/**
	 * 保存开发者信息
	* @author:luohaidong 
	* @param  @param dto
	* @param  @return
	* @param  @throws ServiceException 
	* @return Integer
	* @throws
	 */
	public Integer save(Dto dto) throws ServiceException;


}
