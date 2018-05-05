package com.lshop.manage.lvCarriageSet.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvCarriageSet.service.LvCarriageSetService;
import com.lshop.manage.util.BeanFactoryUtil;

public class LvCarriageSetServiceTest {
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
		LvCarriageSetService lv= (LvCarriageSetService) factory.getBean("LvCarriageSetService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvCarriageSet lvCarriageSet=new LvCarriageSet();
		lvCarriageSet.setId(15);
		dto.put("lvCarriageSet", lvCarriageSet);
		LvCarriageSetService lv= (LvCarriageSetService) factory.getBean("LvCarriageSetService");
		lv.delete(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvCarriageSet lvCarriageSet=new LvCarriageSet();
		lvCarriageSet.setId(1);
		dto.put("lvCarriageSet", lvCarriageSet);
		LvCarriageSetService lv= (LvCarriageSetService) factory.getBean("LvCarriageSetService");
		lv.get(dto);
	}

	@Test
	public void testGetCarriageSet() {
		BaseDto dto=new BaseDto();
		dto.put("deliveryId", 100023);
		LvCarriageSetService lv= (LvCarriageSetService) factory.getBean("LvCarriageSetService");
		lv.getCarriageSet(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvCarriageSet lvCarriageSet=new LvCarriageSet();
		lvCarriageSet.setDeliveryId(100015);
		lvCarriageSet.setCarriage(119f);
		lvCarriageSet.setCurrency("RMB");
		
		lvCarriageSet.setCode(CodeUtils.getCode());
		lvCarriageSet.setCreateTime(new Date());
		dto.put("lvCarriageSet", lvCarriageSet);
		LvCarriageSetService lv= (LvCarriageSetService) factory.getBean("LvCarriageSetService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvCarriageSet lvCarriageSet=new LvCarriageSet();
		lvCarriageSet.setId(15);
		dto.put("lvCarriageSet", lvCarriageSet);
		LvCarriageSetService lv= (LvCarriageSetService) factory.getBean("LvCarriageSetService");
		LvCarriageSet tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setDeliveryId(100018);
        	tmp.setCarriage(999f);
    		tmp.setCurrency("USD");
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("lvCarriageSet", tmp);
    		lv.update(dto);
        }
	}

}
