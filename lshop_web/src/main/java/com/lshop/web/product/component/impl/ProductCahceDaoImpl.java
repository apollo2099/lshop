package com.lshop.web.product.component.impl;

import org.springframework.stereotype.Component;

import com.lshop.common.redis.ValueRedisDaoSupport;
import com.lshop.web.product.component.ProductCacheDao;
import com.lshop.web.product.vo.ProdCache;
@Component
public class ProductCahceDaoImpl extends ValueRedisDaoSupport<String, ProdCache> implements ProductCacheDao{

	
}
