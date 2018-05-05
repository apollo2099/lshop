package com.lshop.common;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"/conf/service/spring-base-context.xml", 
		"/conf/service/spring-base-datasource.xml",
		"/conf/service/spring-base-proxy.xml",
		"/conf/service/spring-base-remote.xml",
		"/conf/service/spring-base-security.xml", 
		"/conf/service/spring-sys-jms.xml",
		"/conf/service/spring-sys-schedule.xml",
		"/conf/service/spring-task-schedule.xml"
		})
public class BaseSpringTestCase extends TestCase {

}
