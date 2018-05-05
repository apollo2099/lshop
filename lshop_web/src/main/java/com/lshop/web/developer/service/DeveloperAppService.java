package com.lshop.web.developer.service;


import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvDeveloperApp;

/**
 * 
* @author luohaidong
* @Description: 开发者
* @date 2014年6月27日 下午3:36:54 
* @version V1.0
 */
public interface DeveloperAppService {

	/**
	 * 查询应用列表
	* @author:luohaidong 
	* @param  @param dto
	* @param  @return
	* @param  @throws ServiceException 
	* @return Pagination
	* @throws
	 */
	public Pagination list(Dto dto) throws ServiceException;
	
	/**
	 * 保存上传应用信息
	* @author:luohaidong 
	* @param  @param dto
	* @param  @return
	* @param  @throws ServiceException 
	* @return Integer
	* @throws
	 */
	public Integer save(Dto dto) throws ServiceException;
	
	/**
	 * 完善应用信息
	* @author:luohaidong 
	* @param  @param dto
	* @param  @return
	* @param  @throws ServiceException 
	* @return Integer
	* @throws
	 */
	public Integer saveinfo(Dto dto) throws ServiceException;
	
	/**
	 * 完善应用信息
	 * @author:luohaidong 
	 * @param  @param dto
	 * @param  @return
	 * @param  @throws ServiceException 
	 * @return Integer
	 * @throws
	 */
	public LvDeveloperApp info(Dto dto) throws ServiceException;


}
