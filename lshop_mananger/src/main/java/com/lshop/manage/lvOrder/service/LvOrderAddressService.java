package com.lshop.manage.lvOrder.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvOrderAddress;
/**
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.LvOrderAddressService.java]  
 * @ClassName:    [LvOrderAddressService]   
 * @Description:  [一句话描述该类的功能]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:22:02]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:22:02]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvOrderAddressService {
	/**
	 * @Method: save 
	 * @Description:  [新增订单地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:22:17]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:22:17]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void save(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [更新订单地址详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-15 上午10:13:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-15 上午10:13:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void update(Dto dto)throws ServiceException;
	/**
	 * @Method: getOrderAddress 
	 * @Description:  [根据订单编号查询订单地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:22:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:22:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return LvOrderAddress
	 */
	public LvOrderAddress getOrderAddress(Dto dto) throws ServiceException ;
	public LvOrderAddress getOrderAddress(String orderId) throws ServiceException ;
	public boolean isExistsByThirdOrderNum(String thirdOrderNum)throws ServiceException;
}
