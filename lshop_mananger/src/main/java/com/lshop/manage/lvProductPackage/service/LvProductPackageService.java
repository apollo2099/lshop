package com.lshop.manage.lvProductPackage.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvProductPackage;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductPackage.service.LvProductPackageService.java]  
 * @ClassName:    [LvProductPackageService]   
 * @Description:  [产品套餐关联关系管理--接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午04:15:07]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午04:15:07]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvProductPackageService {
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品套餐关联关系]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:15:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:15:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Dto
	 */
	public Dto save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除产品套餐关联关系]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:15:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:15:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id获取产品套餐关联关系详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:16:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:16:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvProductPackage
	 */
	public LvProductPackage get(Dto dto) throws ServiceException ;
	/**
	 * 
	 * @Method: getProductPackage 
	 * @Description:  [根据套餐code查询套餐详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:16:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:16:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return List<LvProductPackage>
	 */
	public List<LvProductPackage> getProductPackage(Dto dto)throws ServiceException ;

}
