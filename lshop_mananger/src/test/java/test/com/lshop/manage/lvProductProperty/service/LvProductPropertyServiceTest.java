package test.com.lshop.manage.lvProductProperty.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvProductProperty.service.LvProductPropertyService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductProperty.service.LvProductPropertyServiceTest.java]  
 * @ClassName:    [LvProductPropertyServiceTest]   
 * @Description:  [产品扩展属性管理-单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-10 上午09:34:26]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-10 上午09:34:26]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvProductPropertyServiceTest {
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
		LvProductPropertyService lv= (LvProductPropertyService) factory.getBean("LvProductPropertyService");
		lv.getList(dto);
	}

	

	@Test
	public void testSave() {//CD1341308735749
		BaseDto dto=new BaseDto();
		LvProductProperty lvProductProperty=new LvProductProperty();
		lvProductProperty.setTitle("junit test!");
		lvProductProperty.setContent("junit test content");
		lvProductProperty.setProductCode("CD1341308735749");
		lvProductProperty.setSortId(5);
		lvProductProperty.setCode(CodeUtils.getCode());
		lvProductProperty.setCreateTime(new Date());
		dto.put("lvProductProperty", lvProductProperty);
		LvProductPropertyService lv= (LvProductPropertyService) factory.getBean("LvProductPropertyService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvProductProperty productProperty=new LvProductProperty();
		productProperty.setId(6);
		dto.put("lvProductProperty", productProperty);
		LvProductPropertyService lv= (LvProductPropertyService) factory.getBean("LvProductPropertyService");
		LvProductProperty lvProductProperty=lv.get(dto);
        if(lvProductProperty!=null){
    		lvProductProperty.setTitle("开发测试@!");
    		lvProductProperty.setContent("开发测试内容@");
    		lvProductProperty.setSortId(5);
    		lvProductProperty.setModifyTime(new Date());
    		lvProductProperty.setModifyUserId(2);
    		lvProductProperty.setModifyUserName("admin");
    		dto.put("lvProductProperty", lvProductProperty);
    		lv.update(dto);
        }
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvProductProperty productProperty=new LvProductProperty();
		productProperty.setId(6);
		dto.put("lvProductProperty", productProperty);
		LvProductPropertyService lv= (LvProductPropertyService) factory.getBean("LvProductPropertyService");
		LvProductProperty lvProductProperty=lv.get(dto);
	}
	
	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvProductProperty productProperty=new LvProductProperty();
		productProperty.setId(7);
		dto.put("lvProductProperty", productProperty);
		LvProductPropertyService lv= (LvProductPropertyService) factory.getBean("LvProductPropertyService");
		lv.delete(dto);
	}

}
