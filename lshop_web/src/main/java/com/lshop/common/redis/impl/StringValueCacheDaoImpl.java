package com.lshop.common.redis.impl;

import org.springframework.stereotype.Component;

import com.lshop.common.redis.StringValueCacheDao;
import com.lshop.common.redis.ValueRedisDaoSupport;

@Component("StringValueCacheDao")
public class StringValueCacheDaoImpl extends ValueRedisDaoSupport<String, String> implements StringValueCacheDao{

}
