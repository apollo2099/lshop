package com.lshop.manage.lvStore.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.lshop.manage.util.BeanFactoryUtil;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.manage.lvStore.service.InitStoreDataService;


public class InitStoreDataServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	@Test
	public void testInitData() {
		BaseDto dto=new BaseDto();
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		LvStore lvStore=new LvStore();
		lvStore.setStoreFlag("tvpadvs");
		dto.put("lvStore",lvStore);
		lvs.initData(dto);
	}

	@Test
	public void testInsertEmailTpl() {
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		lvs.insertEmailTpl("tvpadvs", "tvpadcn");
	}

	@Test
	public void testInsertAd() {
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		lvs.insertAd("tvpadvs", "tvpadcn");
	}

	@Test
	public void testInsertNavigation() {
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		lvs.insertNavigation("tvpadvs", "tvpadcn");
	}

	@Test
	public void testInsertLinkUrl() {
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		lvs.insertLinkUrl("tvpadvs", "tvpadcn");
	}



	@Test
	public void testInsertCarriageSet() {
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		lvs.insertCarriageSet("tvpadvs", "tvpadcn");
	}

	@Test
	public void testInsertProduct() {
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		lvs.insertProduct("tvpadvs", "tvpadcn");
	}

	@Test
	public void testUpdatePaymentStyle() {
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		lvs.updatePaymentStyle("tvpadvs", "tvpadcn");

	}

	@Test
	public void testInsertContent() {
		InitStoreDataService lvs= (InitStoreDataService) factory.getBean("InitStoreDataService");
		lvs.insertContent("tvpadvs","tvpadvs");
	}

}
