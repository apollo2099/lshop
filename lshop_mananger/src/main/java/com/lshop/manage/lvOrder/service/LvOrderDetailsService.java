package com.lshop.manage.lvOrder.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvOrderDetails;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.LvOrderDetailsService.java]  
 * @ClassName:    [LvOrderDetailsService]   
 * @Description:  [订单详情管理--接口管理]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:28:11]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:28:11]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvOrderDetailsService {
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增订单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:28:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:28:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void save(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: getOrderDetails 
	 * @Description:  [根据订单编号查询订单详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:28:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:28:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return List<LvOrderDetails>
	 */
	public List<LvOrderDetails> getOrderDetails(Dto dto) throws ServiceException ;
	public List<LvOrderDetails> getOrderDetails(String orderId) throws ServiceException;
	public Pagination getChangeOrderDetails(Dto dto) throws ServiceException ;
	/**
	 * 
	 * @Method: getCouponCode 
	 * @Description:  [根据订单号查询当前订单的所有优惠券信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:28:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:28:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return List
	 */
	public List getCouponCode(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: getDetailsByCouponCode 
	 * @Description:  [根据优惠券信息查询当前订单详情的列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:28:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:28:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return List<LvOrderDetails>
	 */
	public List<LvOrderDetails> getDetailsByCouponCode(Dto dto) throws ServiceException ;
	/**
	 * 
	 * @Method: getDetailsByOid 
	 * @Description:  [根据订单号查询当前订单的所有产品信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:28:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:28:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return List
	 */
	public List getDetailsByOid(Dto dto) throws ServiceException ;
	/**
	 * 
	 * @Method: isExistsByProductCode 
	 * @Description:  [根据商品code和第三方订单号查询商品详情是否存在]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-25 下午3:15:47]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-25 下午3:15:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode 商品编码
	 * @param thirdOrderNum 第三方订单号
	 * @return boolean 
	 */
	public boolean isExistsByProductCode(String productCode,String thirdOrderNum)throws ServiceException;
	
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改订单详情状态]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-31 下午5:16:24]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-31 下午5:16:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderId 订单号
	 * @param status  状态（0正常，-1删除）
	 * @param userId  修改人编号
	 * @param userName 修改人
	 * @throws ServiceException 
	 * @return void
	 */
	public void updateStatus(String orderId,Integer status,Integer userId,String userName) throws ServiceException ;
	
	
}
