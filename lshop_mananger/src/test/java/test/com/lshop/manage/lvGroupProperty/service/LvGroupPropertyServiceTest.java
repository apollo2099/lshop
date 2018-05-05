package test.com.lshop.manage.lvGroupProperty.service;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvGroupProperty.service.LvGroupPropertyService;




public class LvGroupPropertyServiceTest {

	private static BeanFactory factory=null;
	@BeforeClass
	public static void init(){
		factory=new BeanFactoryUtil().createBeanFactory();
	}
	
	
	
	@Test
	public void testGetList(){
		System.out.println("++++");
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvGroupPropertyService lv= (LvGroupPropertyService) factory.getBean("LvGroupPropertyService");
		lv.getList(dto);
		
	}
	@Test
	public void testGet(){
		BaseDto dto=new BaseDto();
		LvGroupProperty lvGroupProperty=new LvGroupProperty();
		lvGroupProperty.setId(1);
		dto.put("lvGroupProperty", lvGroupProperty);
		LvGroupPropertyService lv= (LvGroupPropertyService) factory.getBean("LvGroupPropertyService");
		lv.get(dto);
	}
	
	@Test
	public void testSave(){
		BaseDto dto=new BaseDto();
		LvGroupProperty lvGroupProperty=new LvGroupProperty();
		lvGroupProperty.setTitle("junit test case");
		lvGroupProperty.setContent("test content");
		lvGroupProperty.setGroupCode("CD1343037576718");
		lvGroupProperty.setSortId(3);
		lvGroupProperty.setCode(CodeUtils.getCode());
		lvGroupProperty.setCreateTime(new Date());
		dto.put("lvGroupProperty", lvGroupProperty);
		LvGroupPropertyService lv= (LvGroupPropertyService) factory.getBean("LvGroupPropertyService");
		lv.save(dto);
	}
	
	@Test
	public void testUpdate(){
		BaseDto dto=new BaseDto();
		LvGroupProperty lvGroupProperty=new LvGroupProperty();
		lvGroupProperty.setId(3);
		lvGroupProperty.setTitle("junit test case for update");
		lvGroupProperty.setContent("test content for update");
		lvGroupProperty.setGroupCode("CD1343037576718");
		lvGroupProperty.setSortId(3);
		lvGroupProperty.setCode(CodeUtils.getCode());
		lvGroupProperty.setCreateTime(new Date());
		dto.put("lvGroupProperty", lvGroupProperty);
		LvGroupPropertyService lv= (LvGroupPropertyService) factory.getBean("LvGroupPropertyService");
		lv.update(dto);
	}
	
	@Test
	public void testDelete(){
		BaseDto dto=new BaseDto();
		LvGroupProperty lvGroupProperty=new LvGroupProperty();
		lvGroupProperty.setId(3);
		dto.put("lvGroupProperty", lvGroupProperty);
		LvGroupPropertyService lv= (LvGroupPropertyService) factory.getBean("LvGroupPropertyService");
		lv.delete(dto);
	}
	
}
