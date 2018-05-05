package com.lshop.common.spring;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lshop.common.cache.component.CacheComponent;

/**
 * 顶层容器启动事件处理
 * @author caicl
 *
 */
@Component
public class ContainerRefreshEventListener implements ApplicationListener<ContextRefreshedEvent>{
	@Resource CacheComponent cacheComponent;

	/* 异步触发顶层容器事件
	 * (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	@Async
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			//顶层容器才触发
			cacheComponent.initStartup();
		}
	}

}
