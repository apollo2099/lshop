package com.lshop.common.redis.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

import com.lshop.common.redis.RedisOperation;

public class RedisOperationImpl<K, V> implements RedisOperation<K, V>{
	@Resource
	protected RedisTemplate<K, V> redisTemplate;
	
	@Override
	public Boolean hasKey(K key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public void delete(K key) {
		redisTemplate.delete(key);
	}

	@Override
	public void delete(Collection<K> key) {
		redisTemplate.delete(key);
	}

	@Override
	public DataType type(K key) {
		return redisTemplate.type(key);
	}

	@Override
	public Set<K> rkeys(K pattern) {
		return redisTemplate.keys(pattern);
	}

	@Override
	public K randomKey() {
		return redisTemplate.randomKey();
	}

	@Override
	public void rename(K oldKey, K newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	@Override
	public Boolean renameIfAbsent(K oldKey, K newKey) {
		return redisTemplate.renameIfAbsent(oldKey, newKey);
	}

	@Override
	public Boolean expire(K key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public Boolean expireAt(K key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	@Override
	public Boolean persist(K key) {
		return redisTemplate.persist(key);
	}

	@Override
	public Boolean move(K key, int dbIndex) {
		return redisTemplate.move(key, dbIndex);
	}

	@Override
	public Long getExpire(K key) {
		return redisTemplate.getExpire(key);
	}

}
