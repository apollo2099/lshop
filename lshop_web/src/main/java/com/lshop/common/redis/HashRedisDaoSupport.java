package com.lshop.common.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;

import com.lshop.common.redis.impl.RedisOperationImpl;


public abstract class HashRedisDaoSupport<K, HK, HV> extends RedisOperationImpl<K, HV> implements HashOperations<K, HK, HV>, RedisOperation<K, HV>{

	public HashRedisDaoSupport() {
		super();
	}

	@Override
	public void delete(K key, Object hashKey) {
		redisTemplate.opsForHash().delete(key, hashKey);
	}

	@Override
	public Boolean hasKey(K key, Object hashKey) {
		return redisTemplate.opsForHash().hasKey(key, hashKey);
	}

	@Override
	public HV get(K key, Object hashKey) {
		HashOperations<K, HK, HV> operations = redisTemplate.opsForHash();
		return operations.get(key, hashKey);
	}

	@Override
	public List<HV> multiGet(K key, Collection<HK> hashKeys) {
		HashOperations<K, HK, HV> operations = redisTemplate.opsForHash();
		return operations.multiGet(key, hashKeys);
	}

	@Override
	public Long increment(K key, HK hashKey, long delta) {
		return redisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	@Override
	public Set<HK> keys(K key) {
		HashOperations<K, HK, HV> operations = redisTemplate.opsForHash();
		return operations.keys(key);
	}

	@Override
	public Long size(K key) {
		return redisTemplate.opsForHash().size(key);
	}

	@Override
	public void putAll(K key, Map<? extends HK, ? extends HV> m) {
		redisTemplate.opsForHash().putAll(key, m);
	}

	@Override
	public void put(K key, HK hashKey, HV value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	@Override
	public Boolean putIfAbsent(K key, HK hashKey, HV value) {
		return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	@Override
	public List<HV> values(K key) {
		HashOperations<K, HK, HV> operations = redisTemplate.opsForHash();
		return operations.values(key);
	}

	@Override
	public Map<HK, HV> entries(K key) {
		HashOperations<K, HK, HV> operations = redisTemplate.opsForHash();
		return operations.entries(key);
	}

	@Override
	public RedisOperations<K, ?> getOperations() {
		return redisTemplate.opsForHash().getOperations();
	}
	
}