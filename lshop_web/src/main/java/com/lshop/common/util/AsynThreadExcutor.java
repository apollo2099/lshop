package com.lshop.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;


/**
 * 系统异步线程执行器
 * @author caicl
 *
 */
public class AsynThreadExcutor {
	private static Log log = LogFactory.getLog(AsynThreadExcutor.class);
	private static AsynThreadExcutor excutor;
	
	protected AsynThreadExcutor() {
		super();
	}

	/**
	 * 应用single模式
	 * @return
	 */
	public static synchronized AsynThreadExcutor getInstance() {
		if (null == excutor) {
			excutor = new AsynThreadExcutor();
		}
		return excutor;
	}
	/**
	 * 异步执行
	 * @param method
	 * @param service
	 * @param args
	 */
	@Async
	public void asyncInvoke(Method method, Object service,
			Object... args) {
		try {
			method.invoke(service, args);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
		} catch (InvocationTargetException e) {
			log.error(e.getMessage());
		}
	}
}
