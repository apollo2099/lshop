/**
 * 
 */
package com.gv.base.dictionary.service;

import com.gv.base.springSecurity.common.pojo.BaseDictionarys;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;

/**
 * @author panjin
 *
 */
public interface BaseDictionaryService {

	/**
	 * 
	 * 功能描述：   获取所有
	 * 创建人：panjin  
	 * 创建时间：2012-5-3 下午02:38:49   
	 * 修改人：panjin   
	 * 修改时间：2012-5-3 下午02:38:49  
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：   添加
	 * 创建人：panjin  
	 * 创建时间：2012-5-3 下午02:38:49   
	 * 修改人：panjin   
	 * 修改时间：2012-5-3 下午02:38:49  
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto save(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：   更新
	 * 创建人：panjin  
	 * 创建时间：2012-5-3 下午02:38:49   
	 * 修改人：panjin   
	 * 修改时间：2012-5-3 下午02:38:49  
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public void update(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：  删除
	 * 创建人：panjin  
	 * 创建时间：2012-5-3 下午02:38:49   
	 * 修改人：panjin   
	 * 修改时间：2012-5-3 下午02:38:49  
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public void delete (Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：   其他基础表删除操作时同步删除相应的数据字段信息
	 * 创建人：yangzheng  
	 * 创建时间：2012-5-14 上午11:59:33   
	 * 修改人：yangzheng   
	 * 修改时间：2012-5-14 上午11:59:33   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void deleteByCode(Dto dto) throws ServiceException ;
	
	/**
	 * 
	 * 功能描述：  删除该节点及该节点下的叶子节点
	 * 创建人：panjin  
	 * 创建时间：2012-5-3 下午02:38:49   
	 * 修改人：panjin   
	 * 修改时间：2012-5-3 下午02:38:49  
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public void deleteNode (Dto dto) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：   查询单个实体
	 * 创建人：panjin  
	 * 创建时间：2012-5-3 下午02:38:49   
	 * 修改人：panjin   
	 * 修改时间：2012-5-3 下午02:38:49  
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public BaseDictionarys get(Dto dto) throws ServiceException;
	
	/**
	 * 获取数据字典树结构对象集合
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto getDictionary(Dto dto) throws ServiceException;
	
}
