package com.lshop.common.util;

public class RedisConstant {
	/**
	 * 活动优惠券配置
	 * 
	 * @return
	 */
	public static RedisPoolConfig getRedisPoolConfigAC() {
		String IP_AC = (String) PropertiesHelper.getConfiguration().getProperty("redis.ac.ip");
		int PORT_AC = PropertiesHelper.getConfiguration().getInt("redis.ac.port");
		return new RedisPoolConfig(IP_AC, PORT_AC);
	}
}
