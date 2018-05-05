package com.lshop.manage.lvExtendBalance.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

/**
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvExtendBalance.service.LvExtendBalanceDetalsService.java]  
 * @ClassName:    [LvExtendBalanceDetalsService]   
 * @Description:  [推广商结算详细信息]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:09:23]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:09:23]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvExtendBalanceDetalsService {
	
	/**
	 * @Method: save 
	 * @Description:  [新增推广商结算详细信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:09:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:09:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void save(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [根据结算单号和订单号删除结算清单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-11-26 上午09:38:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-11-26 上午09:38:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public boolean delete(Dto dto)throws ServiceException;
}
