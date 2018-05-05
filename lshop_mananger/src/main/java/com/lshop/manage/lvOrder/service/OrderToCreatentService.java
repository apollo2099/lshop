package com.lshop.manage.lvOrder.service;

public interface OrderToCreatentService {

	/**
	 * 
	 * @Method: sendOrderMSGToCreatent 
	 * @Description:  [Tvpad商城订单同步到启创接口定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-18 下午2:42:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-18 下午2:42:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderNum 订单号
	 * @return boolean
	 */
	public boolean sendOrderMSGToCreatent(String orderNum);
}
