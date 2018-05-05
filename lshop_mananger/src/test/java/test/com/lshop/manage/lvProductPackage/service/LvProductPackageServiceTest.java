package test.com.lshop.manage.lvProductPackage.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvProductPackage.service.LvProductPackageService;
import com.lshop.manage.lvProductProperty.service.LvProductPropertyService;

public class LvProductPackageServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}


	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvProductPackage lvProductPackage=new LvProductPackage();
		lvProductPackage.setId(16);
		dto.put("lvProductPackage", lvProductPackage);
		LvProductPackageService lv= (LvProductPackageService) factory.getBean("LvProductPackageService");
		lv.get(dto);
	}

	@Test
	public void testSave() {
		//28,"CD1343799962484",NULL,"CD1341308735749",2,1466,244,"132436875197272UUU67TTTT",NULL,NULL,"CD1343804294062","2012-08-01 14:58:14",NULL,NULL,NULL,NULL,NULL,NULL,NULL
		BaseDto dto=new BaseDto();
		LvProductPackage lvProductPackage=new LvProductPackage();
		lvProductPackage.setPackageNum(CodeUtils.getCode());
		lvProductPackage.setPackageTitle("package test!");
		lvProductPackage.setProductCode("CD1341308735749");
		lvProductPackage.setPnum(2);
		lvProductPackage.setPriceRmb(1999f);
		lvProductPackage.setPriceUsd(200f);
		lvProductPackage.setPcode("132436875197272UUU67TTTT");
		lvProductPackage.setCode(CodeUtils.getCode());
		lvProductPackage.setCreateTime(new Date());
		dto.put("lvProductPackage", lvProductPackage);
		LvProductPackageService lv= (LvProductPackageService) factory.getBean("LvProductPackageService");
		lv.save(dto);
	}

	@Test
	public void testGetProductPackage() {
		BaseDto dto=new BaseDto();
		dto.put("productCode", "CD1343799962484");
		LvProductPackageService lv= (LvProductPackageService) factory.getBean("LvProductPackageService");
		lv.getProductPackage(dto);
	}
	@Test
	public void testDelete() {//注：删除套餐是根据套餐code删除所有的套餐关联关系
		BaseDto dto=new BaseDto();
		LvProductPackage lvProductPackage=new LvProductPackage();
		lvProductPackage.setPackageNum("CD1344825651140");
		dto.put("lvProductPackage", lvProductPackage);
		LvProductPackageService lv= (LvProductPackageService) factory.getBean("LvProductPackageService");
		lv.delete(dto);
	}
}
