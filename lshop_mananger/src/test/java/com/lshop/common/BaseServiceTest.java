package com.lshop.common;

import org.junit.BeforeClass;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.manage.util.BeanFactoryUtil;

/**
 * 公共继承模块
 * @author zhengxue
 * @since 1.0 2013-04-27
 *
 */
public class BaseServiceTest {
	
	public static BeanFactory factory=null;
	public BaseDto dto = new BaseDto();
	
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory = BeanFactoryUtil.createBeanFactory();
	}
}
