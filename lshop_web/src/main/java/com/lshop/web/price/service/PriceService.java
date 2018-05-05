package com.lshop.web.price.service;

import java.util.Map;

import com.lshop.common.price.vo.ProductPrice;

/**
 * 
 * @Description:商品价格管理
 * @author CYJ
 * @date 2014-6-23 下午5:14:12
 * 用商品查询批量价格代替
 */
@Deprecated
public interface PriceService {
	/**
	 * 
	 * @Description:获取商品单价
	 * @author CYJ
	 * @date 2014-6-24 上午10:36:37
	 * @param storeId
	 *            店铺标识
	 * @param productNumMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, ProductPrice> getProductPrice(String storeId, Map<String, Integer> productNumMap) throws Exception;
	/**
	 * 
	 * @Method: getProductPrice 
	 * @Description:  [根据商品编码查询商品价格信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-20 上午11:27:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-20 上午11:27:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param storeId  
	 *               店铺标示
	 * @param productCode
	 *               商品编码
	 * @throws Exception 
	 * @return Map<String,ProductPrice>
	 */
	public ProductPrice getProductPrice(String storeId, String productCode,Integer num) throws Exception;

}
