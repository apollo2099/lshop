package com.lshop.common.redis;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SetOperations;

import com.lshop.common.redis.impl.RedisOperationImpl;
public abstract class SetRedisDaoSupport<K, V> extends RedisOperationImpl<K, V> implements SetOperations<K, V>, RedisOperation<K, V>{
	public SetRedisDaoSupport() {
		super();
	}
	@Override
	public Set<V> difference(K key, K otherKey) {
		return redisTemplate.opsForSet().difference(key, otherKey);
	}
	@Override
	public Set<V> difference(K key, Collection<K> otherKeys) {
		return redisTemplate.opsForSet().difference(key, otherKeys);
	}
	@Override
	public Long differenceAndStore(K key, K otherKey, K destKey) {
		return redisTemplate.opsForSet().differenceAndStore(otherKey, otherKey, destKey);
	}
	@Override
	public Long differenceAndStore(K key, Collection<K> otherKeys, K destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
	}
	@Override
	public Set<V> intersect(K key, K otherKey) {
		return redisTemplate.opsForSet().intersect(key, otherKey);
	}
	@Override
	public Set<V> intersect(K key, Collection<K> otherKeys) {
		return redisTemplate.opsForSet().intersect(key, otherKeys);
	}
	@Override
	public Long intersectAndStore(K key, K otherKey, K destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
	}
	@Override
	public Long intersectAndStore(K key, Collection<K> otherKeys, K destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
	}
	@Override
	public Set<V> union(K key, K otherKey) {
		return redisTemplate.opsForSet().union(key, otherKey);
	}
	@Override
	public Set<V> union(K key, Collection<K> otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}
	@Override
	public Long unionAndStore(K key, K otherKey, K destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
	}
	@Override
	public Long unionAndStore(K key, Collection<K> otherKeys, K destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
	}
	@Override
	public Boolean add(K key, V value) {
		return redisTemplate.opsForSet().add(key, value);
	}
	@Override
	public Boolean isMember(K key, Object o) {
		return redisTemplate.opsForSet().isMember(key, o);
	}
	@Override
	public Set<V> members(K key) {
		return redisTemplate.opsForSet().members(key);
	}
	@Override
	public Boolean move(K key, V value, K destKey) {
		return redisTemplate.opsForSet().move(key, value, destKey);
	}
	@Override
	public V randomMember(K key) {
		return redisTemplate.opsForSet().randomMember(key);
	}
	@Override
	public Boolean remove(K key, Object o) {
		return redisTemplate.opsForSet().remove(key, o);
	}
	@Override
	public V pop(K key) {
		return redisTemplate.opsForSet().pop(key);
	}
	@Override
	public Long size(K key) {
		return redisTemplate.opsForSet().size(key);
	}
	@Override
	public RedisOperations<K, V> getOperations() {
		return redisTemplate.opsForSet().getOperations();
	}

}
