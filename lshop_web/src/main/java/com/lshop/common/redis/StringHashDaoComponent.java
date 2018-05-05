package com.lshop.common.redis;

import org.springframework.data.redis.core.HashOperations;

/**
 * 值为字符串的hash操作
 * @author caicl
 *
 */
public interface StringHashDaoComponent extends RedisOperation<String, String>, HashOperations<String, String, String>{

}
