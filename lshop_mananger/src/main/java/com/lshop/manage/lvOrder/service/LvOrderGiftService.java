package com.lshop.manage.lvOrder.service;

import java.util.List;

import com.gv.core.datastructure.Dto;

public interface LvOrderGiftService {
	
	
	/**
	 * 
	 * @Method: findAllByOrderId 
	 * @Description:  [根据订单号查询赠品详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 下午3:38:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 下午3:38:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return List
	 */
	public List findAllByOrderId(Dto dto);
		
	/**
	 * 
	 * @Method: findAllByOrderId 
	 * @Description:  [根据订单号查询赠品详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 下午3:38:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 下午3:38:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderId 订单号
	 * @return List
	 */
	public List findAllByOrderId(String orderId);
	/**
	 * 
	 * @Method: giftDeduplication 
	 * @Description:  [赠品信息去重，赠送数量叠加]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 上午10:45:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 上午10:45:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param giftList
	 * @return List<LvActivityGift>
	 */
	public List giftDeduplication(List giftList);

}
