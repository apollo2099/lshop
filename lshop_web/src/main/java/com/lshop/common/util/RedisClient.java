package com.lshop.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	private static final Log logger = LogFactory.getLog(RedisClient.class);

	private static RedisClient redisClient = null;

	private static Map<String, JedisPool> jedisPoolMap = new HashMap<String, JedisPool>();

	public static synchronized RedisClient getInstance() {

		if (redisClient == null) {
			redisClient = new RedisClient();
		}
		return redisClient;
	}

	public JedisPool getJedisPool(RedisPoolConfig redisPoolConfig) throws Exception {
		String ip = redisPoolConfig.getIp();
		int port = redisPoolConfig.getPort();

		JedisPool jedisPool = null;

		String key = ip + "_" + port;
		if (jedisPoolMap.containsKey(key)) {
			jedisPool = jedisPoolMap.get(key);
		} else {
			jedisPool = new JedisPool(new JedisPoolConfig(), ip, port);
			jedisPoolMap.put(key, jedisPool);
		}

		return jedisPool;
	}

	public static void closeRedis(JedisPool pool, Jedis jedis) {
		pool.returnResource(jedis);
	}

}
