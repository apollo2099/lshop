package com.lshop.web.shopCart.component.impl;

import org.springframework.stereotype.Component;

import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.redis.SetRedisDaoSupport;
import com.lshop.web.shopCart.component.ShopCartCachceDao;
import com.lshop.web.shopCart.vo.ShopCartCache;
@Component
public class ShopCartCacheDaoImpl extends SetRedisDaoSupport<String, ShopCartCache> implements ShopCartCachceDao{

}
