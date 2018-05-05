/**
 * 
 */
package com.lshop.common.cache.component;

/**
 * 缓存初始化设置
 * @author caicl
 *
 */
public interface CacheComponent {
	/**
	 * spring容器或者应用启动后回调些接口
	 */
	void initStartup();
	/**
	 * 清除指定模式缓存
	 * @param pattern
	 */
	void clearCache(String pattern);
	/**
	 * 从jvm缓存得到缓存对象
	 * @param key
	 * @return
	 */
	Object getCache(String key);
	/**
	 * 设置JVM缓存对象
	 * @param key
	 * @param value
	 */
	void setCache(String key, Object value);
	/**
	 * 清除易变缓存
	 * @return
	 */
	boolean clearMutableCacle();
}
