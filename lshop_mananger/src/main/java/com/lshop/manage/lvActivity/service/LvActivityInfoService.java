package com.lshop.manage.lvActivity.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvActivityInfo;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvActivity.service.LvActivityInfoService.java]  
 * @ClassName:    [LvActivityInfoService]   
 * @Description:  [产品活动扩展属性信息--接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午02:22:43]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午02:22:43]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvActivityInfoService {
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品活动扩展属性信息列表定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:23:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:23:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Pagination
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品活动扩展属性信息定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:23:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:23:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return Dto
	 */
	public Dto save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改产品活动扩展属性信息定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:23:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:23:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void update(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除产品活动扩展属性信息定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:23:37]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:23:37]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品活动扩展属性详情信息定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:23:41]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:23:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvActivityInfo
	 */
	public LvActivityInfo get(Dto dto) throws ServiceException ;
}
