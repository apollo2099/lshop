package com.lshop.manage.lvProductLadder.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvProductLadder;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductLadder.service.LvProductLadderService.java]  
 * @ClassName:    [LvProductLadderService]   
 * @Description:  [产品阶梯价管理--接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午04:11:52]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午04:11:52]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvProductLadderService {
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品阶梯价列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:11:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:11:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Pagination
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品阶梯价]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:12:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:12:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Dto
	 */
	public Boolean save(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改产品阶梯价]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:12:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:12:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Boolean update(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除产品阶梯价]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:12:10]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:12:10]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete (Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id获取产品阶梯价详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午04:12:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午04:12:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvProductLadder
	 */
	public LvProductLadder get(Dto dto) throws ServiceException ;
	
	/**
	 * @Method: getProductLadder 
	 * @Description:  [根据产品编号查询阶梯价信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-21 上午10:09:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-21 上午10:09:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto     产品编号code
	 * @throws ServiceException 
	 * @return List<LvProductLadder> 当前产品所有阶梯价信息
	 */
	public List<LvProductLadder> getProductLadder(Dto dto)throws ServiceException ;
	public List<LvProductLadder> getProductLadderNoId(Dto dto)throws ServiceException ;
}
