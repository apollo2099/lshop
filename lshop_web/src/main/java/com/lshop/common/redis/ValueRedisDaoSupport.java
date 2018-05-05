package com.lshop.common.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.lshop.common.redis.impl.RedisOperationImpl;
public abstract class ValueRedisDaoSupport<K, V> extends RedisOperationImpl<K, V> implements ValueOperations<K, V>, RedisOperation<K, V>{
	
	public ValueRedisDaoSupport() {
		super();
	}

	@Override
	public void set(K key, V value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void set(K key, V value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	@Override
	public Boolean setIfAbsent(K key, V value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	@Override
	public void multiSet(Map<? extends K, ? extends V> m) {
		redisTemplate.opsForValue().multiSet(m);
	}

	@Override
	public void multiSetIfAbsent(Map<? extends K, ? extends V> m) {
		redisTemplate.opsForValue().multiSetIfAbsent(m);
	}

	@Override
	public V get(Object key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public V getAndSet(K key, V value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	@Override
	public List<V> multiGet(Collection<K> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public Long increment(K key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Integer append(K key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}

	@Override
	public String get(K key, long start, long end) {
		return redisTemplate.opsForValue().get(key, start, end);
	}

	@Override
	public void set(K key, V value, long offset) {
		redisTemplate.opsForValue().set(key, value, offset);
	}

	@Override
	public Long size(K key) {
		return redisTemplate.opsForValue().size(key);
	}

	@Override
	public RedisOperations<K, V> getOperations() {
		return redisTemplate.opsForValue().getOperations();
	}

}
