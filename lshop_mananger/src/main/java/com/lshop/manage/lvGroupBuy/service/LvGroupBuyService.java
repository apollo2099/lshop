package com.lshop.manage.lvGroupBuy.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvGroupBuy;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvGroupBuy.service.LvGroupBuyService.java]  
 * @ClassName:    [LvGroupBuyService]   
 * @Description:  [团购信息管理-接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:15:10]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:15:10]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvGroupBuyService {
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询团购信息管理列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:15:16]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:15:16]   
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
	 * @Description:  [新增团购信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:15:20]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:15:20]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return Dto
	 */
	public Boolean save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改团购信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:15:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:15:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Boolean update(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除团购信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:15:29]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:15:29]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id删除团购信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:15:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:15:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvGroupBuy
	 */
	public LvGroupBuy get(Dto dto) throws ServiceException ;
	/**
	 * 
	 * @Method: getAll 
	 * @Description:  [查询所有团购信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:15:37]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:15:37]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return List<LvGroupBuy>
	 */
	public List<LvGroupBuy> getAll(Dto dto)throws ServiceException;
	public Boolean isExistGroupBy(Dto dto)throws ServiceException;
	public String upload(Dto dto);
	
}
