package com.lshop.manage.cache.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.lshop.manage.cache.service.RedisService;
import com.lshop.manage.util.BeanFactoryUtil;

public class RedisServiceTest {
	private static BeanFactory factory = null;

	@BeforeClass
	public static void createBeanFactory() {
		new BeanFactoryUtil();
		factory = BeanFactoryUtil.createBeanFactory();
	}

	@Test
	public void testInitActivityCouponCache() throws Exception {
		RedisService redisService = (RedisService) factory.getBean("RedisService");
		redisService.initActivityCouponCache();
	}

}
