package com.lshop.manage.LvAccountAddress.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.user.LvAccountAddress;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.LvAccountAddress.service.LvAccountAddressService.java]  
 * @ClassName:    [LvAccountAddressService]   
 * @Description:  [用户收货地址管理--接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午02:07:54]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午02:07:54]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvAccountAddressService {
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询用户收货地址管理列表定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:12:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:12:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Pagination 分页对象
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增用户收货地址信息定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:13:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:13:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Dto
	 */
	public Dto save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改用户收货地址信息定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:13:09]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:13:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void update(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除用户收货地址信息定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:13:13]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:13:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询用户收货地址详情信息定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:13:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:13:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return LvAccountAddress
	 */
	public LvAccountAddress get(Dto dto) throws ServiceException ;
}
