package test.com.lshop.manage.lvNavigation.service;


import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvNavigation;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvNavigation.service.LvNavigationService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvNavigation.service.test.LvNavigationServiceTest.java]  
 * @ClassName:    [LvNavigationServiceTest]   
 * @Description:  [店铺管理-导航信息管理-单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 下午01:35:25]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 下午01:35:25]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvNavigationServiceTest {

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
		LvNavigation lvNavigation=new LvNavigation();
		lvNavigation.setNavName("首页");
		dto.put("model", lvNavigation);
		LvNavigationService lv= (LvNavigationService) factory.getBean("LvNavigationService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvNavigation lvNavigation=new LvNavigation();
		lvNavigation.setId(1);
		dto.put("model", lvNavigation);
		LvNavigationService lv= (LvNavigationService) factory.getBean("LvNavigationService");
		lv.get(dto);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvNavigation lvNavigation=new LvNavigation();
		lvNavigation.setId(15);
		dto.put("model", lvNavigation);
		LvNavigationService lv= (LvNavigationService) factory.getBean("LvNavigationService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvNavigation lvNavigation=new LvNavigation();
		lvNavigation.setNavName("junit nav test");
		lvNavigation.setNavUrl("www.baidu.com");
		lvNavigation.setNavParentId(2);
		lvNavigation.setOrderValue(2);
		
		lvNavigation.setCode(CodeUtils.getCode());
		lvNavigation.setCreateTime(new Date());
		dto.put("model", lvNavigation);
		LvNavigationService lv= (LvNavigationService) factory.getBean("LvNavigationService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvNavigation lvNavigation=new LvNavigation();
		lvNavigation.setId(14);
		dto.put("model", lvNavigation);
		LvNavigationService lv= (LvNavigationService) factory.getBean("LvNavigationService");
		LvNavigation tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setNavName("junit nav test update");
        	tmp.setNavUrl("www.baidu.com/test/");
    		tmp.setNavParentId(2);
    		tmp.setOrderValue(2);
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
