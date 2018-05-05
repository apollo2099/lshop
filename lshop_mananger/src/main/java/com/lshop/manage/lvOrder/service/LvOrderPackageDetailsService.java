package com.lshop.manage.lvOrder.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.LvOrderPackageDetailsService.java]  
 * @ClassName:    [LvOrderPackageDetailsService]   
 * @Description:  [订单套餐明细管理-接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:34:12]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:34:12]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvOrderPackageDetailsService {
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增订单套餐明细信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:34:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:34:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void save(Dto dto)throws ServiceException;
	
}
