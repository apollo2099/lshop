package com.lshop.common.redis;

import org.springframework.data.redis.core.ValueOperations;

/**
 * 值是字符串的缓存访问类
 * @author caicl
 *
 */
public interface StringValueCacheDao extends ValueOperations<String, String>, RedisOperation<String, String>{

}
