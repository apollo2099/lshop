package com.lshop.manage.lvGroupProperty.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvGroupProperty;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvGroupProperty.service.LvGroupPropertyService.java]  
 * @ClassName:    [LvGroupPropertyService]   
 * @Description:  [团购扩展属性信息-接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午01:20:38]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午01:20:38]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
public interface LvGroupPropertyService {
    /**
     * 
     * @Method: getList 
     * @Description:  [分页查询 团购扩展属性信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-9 下午01:41:15]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-9 下午01:41:15]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param dto
     * @return Pagination
     * @throws ServiceException
     */
	public Pagination getList(Dto dto) throws ServiceException;
    /**
     * 
     * @Method: save 
     * @Description:  [新增团购扩展属性信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-9 下午01:44:42]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-9 下午01:44:42]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param dto
     * @throws ServiceException 
     * @return Dto
     */
	public Dto save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改团购扩展属性信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午01:46:59]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午01:46:59]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void update(Dto dto) throws ServiceException;
    /**
     * 
     * @Method: delete 
     * @Description:  [删除团购扩展属性信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-9 下午01:47:44]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-9 下午01:47:44]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param dto
     * @throws ServiceException 
     * @return void
     */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [查看团购扩展属性信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午01:47:59]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午01:47:59]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto 
	 * @throws ServiceException 
	 * @return LvGroupProperty 团购信息
	 */
	public LvGroupProperty get(Dto dto) throws ServiceException ;
}
