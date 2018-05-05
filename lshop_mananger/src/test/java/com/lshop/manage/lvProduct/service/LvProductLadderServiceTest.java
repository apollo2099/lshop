package com.lshop.manage.lvProduct.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvProductLadder.service.LvProductLadderService;
import com.lshop.manage.util.BeanFactoryUtil;

public class LvProductLadderServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testGetList() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvProductLadderService lv= (LvProductLadderService) factory.getBean("LvProductLadderService");
		lv.getList(dto);
	}



	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvProductLadder lvProductLadder=new LvProductLadder();
		lvProductLadder.setProductCode("CD1341308735749");
		lvProductLadder.setUpInterval(-1);
		lvProductLadder.setDownInterval(15);
		lvProductLadder.setPrice(229f);
		lvProductLadder.setCode(CodeUtils.getCode());
		lvProductLadder.setCreateTime(new Date());
		dto.put("lvProductLadder", lvProductLadder);
		LvProductLadderService lv= (LvProductLadderService) factory.getBean("LvProductLadderService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvProductLadder productLadder=new LvProductLadder();
		productLadder.setId(2);
		dto.put("lvProductLadder", productLadder);
		LvProductLadderService lv= (LvProductLadderService) factory.getBean("LvProductLadderService");
		LvProductLadder lvProductLadder=lv.get(dto);
        if(lvProductLadder!=null){
        	lvProductLadder.setProductCode("CD1341308735749");
    		lvProductLadder.setUpInterval(11);
    		lvProductLadder.setDownInterval(16);
    		lvProductLadder.setPrice(229f);
    		lvProductLadder.setModifyTime(new Date());
    		lvProductLadder.setModifyUserId(2);
    		lvProductLadder.setModifyUserName("admin");
    		dto.put("lvProductLadder", lvProductLadder);
    		lv.update(dto);
        }
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvProductLadder productLadder=new LvProductLadder();
		productLadder.setId(2);
		dto.put("lvProductLadder", productLadder);
		LvProductLadderService lv= (LvProductLadderService) factory.getBean("LvProductLadderService");
		LvProductLadder lvProductLadder=lv.get(dto);
	}

	public void testDelete() {

	}
}
