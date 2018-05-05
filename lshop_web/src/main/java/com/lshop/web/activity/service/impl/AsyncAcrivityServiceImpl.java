package com.lshop.web.activity.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lshop.web.activity.service.AsyncActivityService;
import com.lshop.web.activity.service.RegistService;

@Service("AsyncActivityService")
@Scope("singleton")
public class AsyncAcrivityServiceImpl implements AsyncActivityService{
	private static Log log = LogFactory.getLog(AsyncAcrivityServiceImpl.class);
	private Thread activityThread;//活动回调任务线程
	private List<CallbackTask> callbackList;//回调队伍
	
	public AsyncAcrivityServiceImpl() {
		super();
		callbackList = new Vector<CallbackTask>();
		activityThread = new Thread(new ActivityThread());
		activityThread.setDaemon(true);
		activityThread.start();
		activityThread.setName("Async Activity");
		System.out.println("启动活动回调线程");
	}


	/**
	 * 活动线程
	 * @author caicl
	 *
	 */
	class ActivityThread implements Runnable{
		
		public ActivityThread() {
			super();
		}

		@Override
		public void run() {
			while(true){
				synchronized (callbackList) {
					if (callbackList.size() == 0) {
						try {
							callbackList.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						CallbackTask task = callbackList.remove(0);
						try {
							//excute task
							task.getMethod().invoke(task.service, task.params);
						} catch (IllegalArgumentException e) {
							log.error(e.getMessage());
						} catch (IllegalAccessException e) {
							log.error(e.getMessage());
						} catch (InvocationTargetException e) {
							log.error(e.getMessage());
						} catch (Exception e) {
							log.error(e.getMessage());
						} finally{
							task = null;
						}
					}
				}
			}
		}
		
	}
	/**
	 * 回调任务
	 * @author caicl
	 *
	 */
	class CallbackTask{
		private Method method;
		private Object service;
		private Object[] params;
		public CallbackTask() {
			super();
		}
		public CallbackTask(Method method, Object service, Object[] params) {
			super();
			this.method = method;
			this.service = service;
			this.params = params;
		}
		public Method getMethod() {
			return method;
		}
		public void setMethod(Method method) {
			this.method = method;
		}
		public Object[] getParams() {
			return params;
		}
		public void setParams(Object[] params) {
			this.params = params;
		}
		public Object getService() {
			return service;
		}
		public void setService(Object service) {
			this.service = service;
		}
		
	}
	@Override
	public void asyncServiceInvoke(Method method, Object service,
			Object... args) {
		CallbackTask task = new CallbackTask(method, service, args);
		synchronized (callbackList) {
			callbackList.add(task);
			callbackList.notify();
		}
	}
}