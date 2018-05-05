package test.com.lshop.manage.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.BeanFactory;
public  class BeanFactoryUtil {
	public static BeanFactory createBeanFactory(){
		BeanFactory factory = new 
		ClassPathXmlApplicationContext(new String[]{"conf/service/spring-base-context.xml",
										 "conf/service/spring-base-datasource.xml",
										 "conf/service/spring-base-proxy.xml",
										 "conf/service/spring-base-remote.xml",
										 "conf/service/spring-base-security.xml",
										 "conf/service/spring-sys-jms.xml",
										 "conf/service/spring-sys-schedule.xml",
										 "conf/cxf/cxf-bz-server.xml",
										 "conf/cxf/cxf-bz-client.xml"
//										 "conf/cxf/cxf-bz-base.xml",
//										
//										
						                 });

		System.out.println("factory=>"+factory);
		return factory;
	}
}
