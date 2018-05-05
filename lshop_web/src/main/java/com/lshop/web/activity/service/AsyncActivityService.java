package com.lshop.web.activity.service;

import java.lang.reflect.Method;

public interface AsyncActivityService {
	/**
	 * 异步执行服务方法
	 * @param method
	 * @param service
	 * @param args
	 */
	void asyncServiceInvoke(Method method, Object service, Object... args);
}
