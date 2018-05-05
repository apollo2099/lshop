package com.lshop.common.util;

public class RedisPoolConfig {
	private String ip;
	private int port;

	public RedisPoolConfig() {
		super();
	}

	public RedisPoolConfig(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
