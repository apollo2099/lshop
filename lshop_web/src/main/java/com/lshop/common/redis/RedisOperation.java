package com.lshop.common.redis;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.DataType;

public interface RedisOperation<K, V> {
	Boolean hasKey(K key);

	void delete(K key);

	void delete(Collection<K> key);

	DataType type(K key);

	/**
	 * 原keys方法,因与hash操作中的keys冲突,故改名
	 * @param pattern
	 * @return
	 */
	Set<K> rkeys(K pattern);

	K randomKey();

	void rename(K oldKey, K newKey);

	Boolean renameIfAbsent(K oldKey, K newKey);

	Boolean expire(K key, long timeout, TimeUnit unit);

	Boolean expireAt(K key, Date date);

	Boolean persist(K key);

	Boolean move(K key, int dbIndex);

	Long getExpire(K key);

}
