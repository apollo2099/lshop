package com.lshop.manage.lvPageinfo.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvApp;
import com.lshop.common.pojo.logic.LvPageinfo;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvApp.service.LvAppService;
import com.lshop.manage.lvPageinfo.service.LvPageinfoService;
import com.lshop.manage.util.BeanFactoryUtil;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvPageinfo.service.test.LvPageinfoServiceTest.java]  
 * @ClassName:    [LvPageinfoServiceTest]   
 * @Description:  [总网站导航--页面管理-单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 上午11:37:07]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 上午11:37:07]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvPageinfoServiceTest {
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
		LvPageinfo lvPageinfo=new LvPageinfo();
		lvPageinfo.setId(1);
		dto.put("lvPageinfo", lvPageinfo);
		LvPageinfoService lv= (LvPageinfoService) factory.getBean("LvPageinfoService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvPageinfo lvPageinfo=new LvPageinfo();
		lvPageinfo.setId(4);
		dto.put("lvPageinfo", lvPageinfo);
		LvPageinfoService lv= (LvPageinfoService) factory.getBean("LvPageinfoService");
		lv.delete(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvPageinfo lvPageinfo=new LvPageinfo();
		lvPageinfo.setId(2);
		dto.put("lvPageinfo", lvPageinfo);
		LvPageinfoService lv= (LvPageinfoService) factory.getBean("LvPageinfoService");
		lv.get(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvPageinfo lvPageinfo=new LvPageinfo();
		lvPageinfo.setTitle("junit test!");
		lvPageinfo.setContent("test all time");
		lvPageinfo.setCode(CodeUtils.getCode());
		lvPageinfo.setCreateTime(new Date());
		dto.put("lvPageinfo", lvPageinfo);
		LvPageinfoService lv= (LvPageinfoService) factory.getBean("LvPageinfoService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvPageinfo lvPageinfo=new LvPageinfo();
		lvPageinfo.setId(2);
		dto.put("lvPageinfo", lvPageinfo);
		LvPageinfoService lv= (LvPageinfoService) factory.getBean("LvPageinfoService");
		LvPageinfo pageTmp=lv.get(dto);
        if(pageTmp!=null){
        	pageTmp.setModifyTime(new Date());
        	pageTmp.setModifyUserId(2);
    		pageTmp.setModifyUserName("admin@");
    		dto.put("lvPageinfo", pageTmp);
    		lv.update(dto);
        }
	}

}
