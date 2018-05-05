package com.lshop.manage.lvShopProductType.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvShopProductType.service.LvShopProductTypeService;
import com.lshop.manage.util.BeanFactoryUtil;



public class LvShopProductTypeServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testFindPage() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		LvShopProductType lvShopProductType=new LvShopProductType();
		lvShopProductType.setTypeName("数码");
		dto.put("model", lvShopProductType);
		dto.put("page",page);
		LvShopProductTypeService lv= (LvShopProductTypeService) factory.getBean("LvShopProductTypeService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvShopProductType lvShopProductType=new LvShopProductType();
		lvShopProductType.setId(1);
		dto.put("model", lvShopProductType);
		LvShopProductTypeService lv= (LvShopProductTypeService) factory.getBean("LvShopProductTypeService");
		LvShopProductType temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvShopProductType lvShopProductType=new LvShopProductType();
		lvShopProductType.setId(3);
		dto.put("model", lvShopProductType);
		LvShopProductTypeService lv= (LvShopProductTypeService) factory.getBean("LvShopProductTypeService");
		lv.del(dto);
		
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvShopProductType lvShopProductType=new LvShopProductType();
		lvShopProductType.setTypeName("美食");
		lvShopProductType.setStoreId("tvpad");
		lvShopProductType.setCode(CodeUtils.getCode());
		lvShopProductType.setCreateTime(new Date());
		dto.put("model", lvShopProductType);
		LvShopProductTypeService lv= (LvShopProductTypeService) factory.getBean("LvShopProductTypeService");
		LvShopProductType temp=lv.save(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvShopProductType lvShopProductType=new LvShopProductType();
		lvShopProductType.setId(2);
		dto.put("model", lvShopProductType);
		LvShopProductTypeService lv= (LvShopProductTypeService) factory.getBean("LvShopProductTypeService");
		LvShopProductType tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin_junit");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
