package com.lshop.manage.lvExtendBalance.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvExtendBalance;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvExtendBalance.service.LvExtendBalanceService.java]  
 * @ClassName:    [LvExtendBalanceService]   
 * @Description:  [推广商结算信息管理--接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:07:14]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:07:14]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvExtendBalanceService {
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询推广商结算信息列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:08:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:08:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return Pagination
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增推广商结算信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:08:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:08:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void save(Dto dto)throws ServiceException;

	/**
	 * 
	 * @Method: getOrderInfo 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:08:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:08:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void getOrderInfo(Dto dto)throws ServiceException;
    /**
     * 
     * @Method: getBalanceDetails 
     * @Description:  [查看结算清单详情信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-20 上午11:57:54]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-20 上午11:57:54]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param dto
     * @return
     * @throws ServiceException 
     * @return Pagination
     */
	public Pagination getBalanceDetails(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * @Method: updateBalance 
	 * @Description:  [财务确认结算]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 上午11:57:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 上午11:57:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void updateBalance(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: exportBalance 
	 * @Description:  [导出结算清单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 上午11:58:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 上午11:58:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return List
	 */
	public List exportBalance(Dto dto)throws ServiceException;
	public List exportDetailsBalance(Dto dto)throws ServiceException;
	public LvExtendBalance get(Dto dto)throws ServiceException;
	public LvExtendBalance getBalance(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: saveStatement 
	 * @Description:  [生成结算清单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 下午03:24:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 下午03:24:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Boolean saveStatement(Dto dto)throws ServiceException;
	public Boolean isExistBalance(Dto dto)throws ServiceException;
	public Boolean updateStatement(Dto dto)throws ServiceException;
	public Boolean isVersionFailure(Dto dto) throws ServiceException;
	
}
