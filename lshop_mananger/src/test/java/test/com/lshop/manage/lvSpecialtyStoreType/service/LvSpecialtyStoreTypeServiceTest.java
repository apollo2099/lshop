package test.com.lshop.manage.lvSpecialtyStoreType.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvSpecialtyStore;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvShopProductType.service.LvShopProductTypeService;
import com.lshop.manage.lvSpecialtyStore.service.LvSpecialtyStoreService;
import com.lshop.manage.lvSpecialtyStoreType.service.LvSpecialtyStoreTypeService;

import test.com.lshop.manage.util.BeanFactoryUtil;

public class LvSpecialtyStoreTypeServiceTest {

	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testFindAllPareat() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		List<LvSpecialtyStoreType> list= lv.findAllPareat(dto);
		assertNotNull(list);
	}

	@Test
	public void testFindAll() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		List<LvSpecialtyStoreType> list= lv.findAll(dto);
		assertNotNull(list);
	}

	@Test
	public void testFindAllByPareat() {
		BaseDto dto=new BaseDto();
		dto.put("parentCode","94a2cc7a8ef0477a80c5c054e0864e59");
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		List<LvSpecialtyStoreType> list= lv.findAllByPareat(dto);
		assertNotNull(list);
	}

	@Test
	public void testFindPage() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		LvSpecialtyStoreType lvSpecialtyStoreType=new LvSpecialtyStoreType();
		lvSpecialtyStoreType.setStoreTypeName("其他地区");
		dto.put("model", lvSpecialtyStoreType);
		dto.put("page",page);
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		Pagination  pt=lv.findPage(dto);
		assertNotNull(pt);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStoreType lvSpecialtyStoreType=new LvSpecialtyStoreType();
		lvSpecialtyStoreType.setId(1);
		dto.put("model", lvSpecialtyStoreType);
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		LvSpecialtyStoreType temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testGetSpecialtyStoreType() {
		BaseDto dto=new BaseDto();
		dto.put("storeTypeCode","d01bdf7170ba4de0a596a4d329112546");
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		LvSpecialtyStoreType temp=lv.getSpecialtyStoreType(dto);
		assertNotNull(temp);
		
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStoreType lvSpecialtyStoreType=new LvSpecialtyStoreType();
		lvSpecialtyStoreType.setId(5);
		dto.put("model", lvSpecialtyStoreType);
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		Boolean isFlag=lv.del(dto);
		assertFalse(isFlag);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStoreType lvSpecialtyStoreType=new LvSpecialtyStoreType();
		lvSpecialtyStoreType.setStoreTypeName("东亚地区");
		lvSpecialtyStoreType.setParentCode("94a2cc7a8ef0477a80c5c054e0864e59");
		lvSpecialtyStoreType.setOrderValue(5);
		lvSpecialtyStoreType.setStoreId("tvpad");
		lvSpecialtyStoreType.setCode(CodeUtils.getCode());
		lvSpecialtyStoreType.setCreateTime(new Date());
		dto.put("model", lvSpecialtyStoreType);
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		LvSpecialtyStoreType temp=lv.save(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStoreType lvSpecialtyStoreType=new LvSpecialtyStoreType();
		lvSpecialtyStoreType.setId(5);
		dto.put("model", lvSpecialtyStoreType);
		LvSpecialtyStoreTypeService lv= (LvSpecialtyStoreTypeService) factory.getBean("LvSpecialtyStoreTypeService");
		LvSpecialtyStoreType tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin_junit");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
