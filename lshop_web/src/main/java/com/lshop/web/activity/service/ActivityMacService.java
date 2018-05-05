package com.lshop.web.activity.service;

import com.lshop.common.activity.vo.ActivityMac;

public interface ActivityMacService {
	/**
	 * 
	 * @Method: findByProduct 
	 * @Description:  [根据产品编码查询是否符合条件的输入mac就送活动]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月14日 下午7:35:15]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月14日 下午7:35:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode 产品编码
	 * @return ActivityMac
	 */
	public ActivityMac findByProduct(String productCode);
}
