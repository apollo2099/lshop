package com.lshop.manage.lvPayLogs.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvPayLogs;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvPayLogs.service.LvPayLogsService.java]  
 * @ClassName:    [LvPayLogsService]   
 * @Description:  [支付日志管理--接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:45:15]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:45:15]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvPayLogsService {
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询支付日志列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:45:42]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:45:42]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Pagination
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增支付日志]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:45:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:45:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Dto
	 */
	public Dto save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除支付日志]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:45:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:45:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id获取支付日志]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:45:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:45:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvPayLogs
	 */
	public LvPayLogs get(Dto dto) throws ServiceException ;
}
