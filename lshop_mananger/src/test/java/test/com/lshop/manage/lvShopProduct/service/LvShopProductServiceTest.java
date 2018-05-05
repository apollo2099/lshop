package test.com.lshop.manage.lvShopProduct.service;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import test.com.lshop.manage.util.BeanFactoryUtil;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.manage.lvShopProduct.service.LvShopProductService;

public class LvShopProductServiceTest {
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
		LvShopProduct lvShopProduct=new LvShopProduct();
		lvShopProduct.setSubjectType("code2");
		dto.put("model", lvShopProduct);
		dto.put("page",page);
		LvShopProductService lv= (LvShopProductService) factory.getBean("LvShopProductService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvShopProduct lvShopProduct=new LvShopProduct();
		lvShopProduct.setId(1);
		dto.put("model", lvShopProduct);
		LvShopProductService lv= (LvShopProductService) factory.getBean("LvShopProductService");
		LvShopProduct temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvShopProduct lvShopProduct=new LvShopProduct();
		lvShopProduct.setId(34);
		dto.put("model", lvShopProduct);
		LvShopProductService lv= (LvShopProductService) factory.getBean("LvShopProductService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvShopProduct lvShopProduct=new LvShopProduct();
		lvShopProduct.setSubjectType("code2");
		lvShopProduct.setStoreFlag("tvpad");
		dto.put("model", lvShopProduct);
		dto.put("ids", "CD1343799962484");
		LvShopProductService lv= (LvShopProductService) factory.getBean("LvShopProductService");
		Boolean isFlag=lv.save(dto);
		assertTrue(isFlag);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvShopProduct lvShopProduct=new LvShopProduct();
		lvShopProduct.setId(1);
		dto.put("model", lvShopProduct);
		LvShopProductService lv= (LvShopProductService) factory.getBean("LvShopProductService");
		LvShopProduct tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
