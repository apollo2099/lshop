package com.lshop.manage.lvProductProperty.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvProductProperty;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductProperty.service.LvProductPropertyService.java]  
 * @ClassName:    [LvProductPropertyService]   
 * @Description:  [产品扩展属性信息管理--接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午04:17:49]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午04:17:49]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvProductPropertyService {
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品扩展属性信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:17:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:17:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Pagination
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品扩展属性信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:18:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:18:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Dto
	 */
	public Dto save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [更新产品扩展属性信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:18:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:18:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void update(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除产品扩展属性信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:18:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:18:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品扩展属性信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:18:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:18:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvProductProperty
	 */
	public LvProductProperty get(Dto dto) throws ServiceException ;
}
