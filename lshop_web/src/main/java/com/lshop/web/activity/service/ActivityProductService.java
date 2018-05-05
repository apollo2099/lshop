package com.lshop.web.activity.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvActivityProduct;

public interface ActivityProductService {

	
	/**
	 * 
	 * @Method: findByProductCode 
	 * @Description:  [根据商品编码查询活动商品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-14 下午4:53:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-14 下午4:53:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode 商品编码
	 * @return List<LvActivityProduct>
	 */
	public List<LvActivityProduct> findByProductCode(String productCode);
}
