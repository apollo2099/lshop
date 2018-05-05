package com.lshop.common.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	private static final Log logger = LogFactory.getLog(RedisClient.class);

	private static JedisPool pool = null;

	private RedisClient() {
	}

	public static Jedis getRedis(){
		if (pool == null) {
			String ip = (String) PropertiesHelper.getConfiguration().getProperty("redis.ip");
			Integer port = PropertiesHelper.getConfiguration().getInt("redis.port");
			pool = new JedisPool(new JedisPoolConfig(), ip, port);
		}
		Jedis re = pool.getResource();
		return re;
	}

	public static void closeRedis(Jedis jedis) {
		pool.returnResource(jedis);
	}

	/**
	 * 获取缓存String数据
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) throws Exception {
		Jedis reids = getRedis();
		String value = reids.get(key);
		closeRedis(reids);
		return value;
	}

	/**
	 * 设置缓存String数据
	 * 
	 * @param key
	 * @param obj
	 */
	public static void set(String key, String value) throws Exception {
		Jedis reids = getRedis();
		reids.set(key, value);
		closeRedis(reids);
	}

	/**
	 * 删除缓存String数据
	 * 
	 * @param key
	 * @return
	 */
	public static void delete(String key) throws Exception {
		Jedis reids = getRedis();
		reids.del(key);
		closeRedis(reids);
	}

	/**
	 * 删除缓存对象数据
	 * 
	 * @param key
	 * @param obj
	 */
	public static void delObj(String key) throws Exception {
		Jedis reids = getRedis();
		reids.del(key.getBytes());
		closeRedis(reids);
	}

	/**
	 * 获取map数据
	 * 
	 * @param key
	 * @param obj
	 */
	public static String hget(String mapkey, String field) throws Exception {
		Jedis reids = getRedis();
		String value = reids.hget(mapkey, field);
		closeRedis(reids);
		return value;
	}

	/**
	 * 获取map数据
	 * 
	 * @param key
	 * @param obj
	 */
	public static Map<String, String> hgetAll(String mapkey) throws Exception {
		Jedis reids = getRedis();
		Map<String, String> value = reids.hgetAll(mapkey);
		closeRedis(reids);
		return value;
	}

	/**
	 * 删除map 指定字段key数据
	 * 
	 * @param key
	 * @return
	 */
	public static void hdel(String key, String fields) throws Exception {
		Jedis reids = getRedis();
		reids.hdel(key, fields);
		closeRedis(reids);
	}

	/**
	 * 设置map数据
	 * 
	 * @param key
	 * @param obj
	 */
	public static void hset(String key, String field, String value) throws Exception {
		Jedis reids = getRedis();
		reids.hset(key, field, value);
		closeRedis(reids);
	}

	/**
	 * 设置list数据
	 * 
	 * @param key
	 * @param value
	 */
	public static void lpush(String key, String value) throws Exception {
		Jedis reids = getRedis();
		reids.lpush(key, value);
		closeRedis(reids);
	}

	/**
	 * 获取list数据
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> lrange(String key, long start, long end) throws Exception {
		Jedis reids = getRedis();
		List<String> value = reids.lrange(key, start, end);
		closeRedis(reids);
		return value;
	}

	/**
	 * 删除list 的value值
	 * 
	 * @param userKey
	 * @param oldcode
	 */
	public static void ldel(String key, String value) throws Exception {
		Jedis reids = getRedis();
		reids.lrem(key, 1, value);
		closeRedis(reids);
	}

	/**
	 * 
	 * @param sid
	 * @param usercode
	 * @param times
	 */
	public static void setByTime(String key, String value, int times) throws Exception {
		Jedis reids = getRedis();
		reids.setex(key, times, value);
		closeRedis(reids);
	}

}
