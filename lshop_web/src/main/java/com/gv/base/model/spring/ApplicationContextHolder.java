package com.gv.base.model.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：ApplicationContextHolder   
 * 类描述：   用于持有spring的applicationContext,一个系统只能有一个ApplicationContextHolder 
 * 			使用方法:<bean class="com.gv.cc.common.spring.ApplicationContextHolder"/>
 * 			在java代码中则可以如此使用: 
 * 			BlogDao dao = (BlogDao)ApplicationContextHolder.getBean("blogDao");
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:07:30   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:07:30   
 * 修改备注：   
 * @version
 */
		
public class ApplicationContextHolder implements ApplicationContextAware{
	
	private static Log log = LogFactory.getLog(ApplicationContextHolder.class);
	private static ApplicationContext applicationContext;
	@SuppressWarnings("all")
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if(this.applicationContext != null) {
			throw new IllegalStateException("ApplicationContextHolder already holded 'applicationContext'.");
		}
		this.applicationContext = context;
		log.info("holded applicationContext,displayName:"+applicationContext.getDisplayName());
	}
	
	public static ApplicationContext getApplicationContext() {
		if(applicationContext == null)
			throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
		return applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}
	
	public static void cleanHolder() {
		applicationContext = null;
	}
}
