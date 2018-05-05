package com.lshop.manage.lvAccount.service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.user.LvAccount;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAccount.service.LvAccountService.java]  
 * @ClassName:    [LvAccountService]   
 * @Description:  [用户账户信息管理-接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午01:51:01]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午01:51:01]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [华扬v2.0] 
 *
 */
public interface LvAccountService {
    /**
     * 
     * @Method: getList 
     * @Description:  [分页查询用户账户信息列表信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-9 下午01:51:35]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-9 下午01:51:35]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param dto
     * @throws ServiceException 
     * @return Pagination
     */
	public Dto getList(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增用户账户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午01:51:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午01:51:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Dto
	 */
	public Dto save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午01:51:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午01:51:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Dto update(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [修改用户账户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午01:52:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午01:52:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: deleteByEmail 
	 * @Description:  [根据用户邮箱软删除用户账户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午01:52:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午01:52:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void deleteByEmail (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询用户账户信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午01:52:11]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午01:52:11]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return LvAccount
	 */
	public Dto get(Dto dto) throws ServiceException ;
	
	/**
	 * 导出excel
	 */
	public Dto exportExcel(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * @Method: getAccount 
	 * @Description:  [根据用户账户信息code查询账户信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午01:52:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午01:52:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return LvAccount
	 */
	public LvAccount getAccount(Dto dto) throws ServiceException ;
	public LvAccount getAccountByEmail(Dto dto) throws ServiceException ;
	public Boolean isExistAccountNickName(Dto dto)throws ServiceException;
	

}
