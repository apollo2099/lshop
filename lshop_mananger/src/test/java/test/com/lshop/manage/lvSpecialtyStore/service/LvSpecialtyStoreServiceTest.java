package test.com.lshop.manage.lvSpecialtyStore.service;

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
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvShopProductType.service.LvShopProductTypeService;
import com.lshop.manage.lvSpecialtyStore.service.LvSpecialtyStoreService;

import test.com.lshop.manage.util.BeanFactoryUtil;

public class LvSpecialtyStoreServiceTest {

	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testFindAllByType(){
		BaseDto dto=new BaseDto();
		dto.put("storeTypeCode", "c3e832b05da8460387fd502253ec0347");
		LvSpecialtyStoreService lv= (LvSpecialtyStoreService) factory.getBean("LvSpecialtyStoreService");
		List list= lv.findAllByType(dto);
		assertNotNull(list);
	}
	@Test
	public void testFindPage() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		LvSpecialtyStore lvSpecialtyStore=new LvSpecialtyStore();
		lvSpecialtyStore.setStoreName("");
		dto.put("model", lvSpecialtyStore);
		dto.put("page",page);
		LvSpecialtyStoreService lv= (LvSpecialtyStoreService) factory.getBean("LvSpecialtyStoreService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStore lvSpecialtyStore=new LvSpecialtyStore();
		lvSpecialtyStore.setId(1);
		dto.put("model", lvSpecialtyStore);
		LvSpecialtyStoreService lv= (LvSpecialtyStoreService) factory.getBean("LvSpecialtyStoreService");
		LvSpecialtyStore temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStore lvSpecialtyStore=new LvSpecialtyStore();
		lvSpecialtyStore.setId(7);
		dto.put("model", lvSpecialtyStore);
		LvSpecialtyStoreService lv= (LvSpecialtyStoreService) factory.getBean("LvSpecialtyStoreService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStore lvSpecialtyStore=new LvSpecialtyStore();
		lvSpecialtyStore.setStoreTypeCode("c3e832b05da8460387fd502253ec0347");
		lvSpecialtyStore.setStoreName("珍爱网");
		lvSpecialtyStore.setStoreUrl("http://www.zhengai.com/");
		lvSpecialtyStore.setStoreLogo("http://www.zhengai.com/xxx.jpg");
		lvSpecialtyStore.setOrderValue(6);
		lvSpecialtyStore.setStoreId("tvpad");
		lvSpecialtyStore.setCode(CodeUtils.getCode());
		lvSpecialtyStore.setCreateTime(new Date());
		dto.put("model", lvSpecialtyStore);
		LvSpecialtyStoreService lv= (LvSpecialtyStoreService) factory.getBean("LvSpecialtyStoreService");
		LvSpecialtyStore temp=lv.save(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvSpecialtyStore lvSpecialtyStore=new LvSpecialtyStore();
		lvSpecialtyStore.setId(6);
		dto.put("model", lvSpecialtyStore);
		LvSpecialtyStoreService lv= (LvSpecialtyStoreService) factory.getBean("LvSpecialtyStoreService");
		LvSpecialtyStore tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin_junit");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
