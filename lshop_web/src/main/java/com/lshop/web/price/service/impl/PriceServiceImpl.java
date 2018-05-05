package com.lshop.web.price.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lshop.common.price.vo.ProductPrice;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.price.service.PriceService;
import com.lshop.web.product.service.ProductService;

/**
 * 
 * @Description:价格管理
 * @author CYJ
 * @date 2014-6-24 上午10:38:16
 */
@Service("PriceService")
public class PriceServiceImpl implements PriceService {
	private static final Log logger = LogFactory.getLog(PriceServiceImpl.class);

	@Resource
	private OnlineMallService onlineMallServie;

	@Resource
	private ProductService productService;


	@Override
	public Map<String, ProductPrice> getProductPrice(String storeId, Map<String, Integer> productNumMap) throws Exception {
		return productService.getBachProductPrice(productNumMap);
	}

	
	public ProductPrice getProductPrice(String storeId, String productCode,Integer num) throws Exception{
		return productService.getSalePrice(productCode, num);
	}
}
