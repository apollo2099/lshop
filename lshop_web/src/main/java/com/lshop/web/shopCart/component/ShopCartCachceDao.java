package com.lshop.web.shopCart.component;

import org.springframework.data.redis.core.SetOperations;

import com.lshop.common.redis.RedisOperation;
import com.lshop.web.shopCart.vo.ShopCartCache;

public interface ShopCartCachceDao extends SetOperations<String, ShopCartCache>, RedisOperation<String, ShopCartCache>{

}
