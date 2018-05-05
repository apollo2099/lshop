package test.com.lshop.manage.lvHelpType.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvHelpType;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvHelpType.service.LvHelpTypeService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvHelpType.service.test.LvHelpTypeServiceTest.java]  
 * @ClassName:    [LvHelpTypeServiceTest]   
 * @Description:  [帮助类别管理-单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 下午01:10:37]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 下午01:10:37]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvHelpTypeServiceTest {
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
		dto.put("page",page);
		LvHelpType lvHelpType=new LvHelpType();
		lvHelpType.setName("購物流程");
		dto.put("model", lvHelpType);
		LvHelpTypeService lv= (LvHelpTypeService) factory.getBean("LvHelpTypeService");
		lv.findPage(dto);
	}

	@Test
	public void testFindAll() {
		BaseDto dto=new BaseDto();
		LvHelpTypeService lv= (LvHelpTypeService) factory.getBean("LvHelpTypeService");
		lv.findAll(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvHelpType lvHelpType=new LvHelpType();
		lvHelpType.setId(1);
		dto.put("model", lvHelpType);
		LvHelpTypeService lv= (LvHelpTypeService) factory.getBean("LvHelpTypeService");
		lv.get(dto);
	}

	@Test
	public void testDel() {
		fail("Not yet implemented");
	}



	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvHelpType lvHelpType=new LvHelpType();
        lvHelpType.setName("junit test");
        lvHelpType.setOrderValue(9);
        lvHelpType.setWebLanguage("cn");
		
		lvHelpType.setCode(CodeUtils.getCode());
		lvHelpType.setCreateTime(new Date());
		dto.put("model", lvHelpType);
		LvHelpTypeService lv= (LvHelpTypeService) factory.getBean("LvHelpTypeService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvHelpType lvHelpType=new LvHelpType();
		lvHelpType.setId(8);
		dto.put("model", lvHelpType);
		LvHelpTypeService lv= (LvHelpTypeService) factory.getBean("LvHelpTypeService");
		LvHelpType tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setName("junit test update");
        	tmp.setOrderValue(8);
        	tmp.setWebLanguage("cn");
    		
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

	@Test
	public void testGetHelpType() {
		BaseDto dto=new BaseDto();
		dto.put("typeCode", "cd11");
		LvHelpTypeService lv= (LvHelpTypeService) factory.getBean("LvHelpTypeService");
		lv.getHelpType(dto);
	}

}
