package test.com.lshop.manage.lvProductType.service;

import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvProductType.service.LvProductTypeService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductType.service.test.LvProductTypeServiceTest.java]  
 * @ClassName:    [LvProductTypeServiceTest]   
 * @Description:  [产品类型管理-单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 上午11:31:45]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 上午11:31:45]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
public class LvProductTypeServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testGetList(){
		System.out.println("++++");
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvProductTypeService lv= (LvProductTypeService) factory.getBean("LvProductTypeService");
		lv.getList(dto);
		
	}
	@Test
	public void testGet(){
		BaseDto dto=new BaseDto();
		LvProductType lvProductType=new LvProductType();
		lvProductType.setId(1);
		dto.put("lvProductType", lvProductType);
		LvProductTypeService lv= (LvProductTypeService) factory.getBean("LvProductTypeService");
		lv.get(dto);
	}
	
	@Test
	public void testSave(){
		BaseDto dto=new BaseDto();
		LvProductType lvProductType=new LvProductType();
		lvProductType.setTypeName("test");
		lvProductType.setTypeFlag(1);
		lvProductType.setCode(CodeUtils.getCode());
		lvProductType.setCreateTime(new Date());
		dto.put("lvProductType", lvProductType);
		LvProductTypeService lv= (LvProductTypeService) factory.getBean("LvProductTypeService");
		lv.save(dto);
	}
	
	@Test
	public void testUpdate(){
		BaseDto dto=new BaseDto();
		LvProductType lvProductType=new LvProductType();
		lvProductType.setId(10);
		lvProductType.setTypeName("test for update");
		lvProductType.setTypeFlag(1);
		lvProductType.setCode(CodeUtils.getCode());
		lvProductType.setCreateTime(new Date());
		dto.put("lvProductType", lvProductType);
		LvProductTypeService lv= (LvProductTypeService) factory.getBean("LvProductTypeService");
		lv.update(dto);
	}
	
	
	@Test
	public void testDelete(){
		BaseDto dto=new BaseDto();
		LvProductType lvProductType=new LvProductType();
		lvProductType.setId(11);
		dto.put("lvProductType", lvProductType);
		LvProductTypeService lv= (LvProductTypeService) factory.getBean("LvProductTypeService");
		lv.delete(dto);
	}
	
}
