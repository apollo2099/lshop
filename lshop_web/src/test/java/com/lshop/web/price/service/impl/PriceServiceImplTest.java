package com.lshop.web.price.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import com.gv.test.base.BaseSpringTestCase;
import com.lshop.common.price.vo.ProductPrice;
import com.lshop.web.price.service.PriceService;

public class PriceServiceImplTest extends BaseSpringTestCase {

	@Resource
	private PriceService priceService;

	@Test
	public void testGetProductPrice() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("7a28c1fcc3fc47bea6c65417b0829cf2", 1);

		Map<String, ProductPrice> productPrices01 = priceService.getProductPrice("tvpadcn", map);

		if (MapUtils.isEmpty(productPrices01)) {
			fail("商品无法获取商品价格信息");
		}
	}
}
