package com.lshop.manage.blog.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.blog.service.LvBlogTypeService;
import com.lshop.manage.util.BeanFactoryUtil;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.test.LvBlogTypeServiceTest.java]  
 * @ClassName:    [LvBlogTypeServiceTest]   
 * @Description:  [一句话描述该类的功能]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-15 下午02:46:57]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-15 下午02:46:57]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvBlogTypeServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testList() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvBlogType lvBlogType=new LvBlogType();
		lvBlogType.setId(1);
		dto.put("model", lvBlogType);
		LvBlogTypeService lv= (LvBlogTypeService) factory.getBean("LvBlogTypeService");
		lv.list(dto);
	}
	

	
	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvBlogType lvBlogType=new LvBlogType();
		lvBlogType.setId(12);
		dto.put("model", lvBlogType);
		LvBlogTypeService lv= (LvBlogTypeService) factory.getBean("LvBlogTypeService");
		lv.delete(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvBlogType lvBlogType=new LvBlogType();
		lvBlogType.setId(13);
		dto.put("model", lvBlogType);
		LvBlogTypeService lv= (LvBlogTypeService) factory.getBean("LvBlogTypeService");
		LvBlogType adTmp=lv.get(dto);
        if(adTmp!=null){
        	adTmp.setType("junit test update");
        	adTmp.setOrderId(Short.parseShort("4"));
        	adTmp.setModifyTime(new Date());
        	adTmp.setModifyUserId(2);
        	adTmp.setModifyUserName("admin@");
    		dto.put("model", adTmp);
    		lv.update(dto);
        }
	}

	@Test
	public void testTypelist() {
		BaseDto dto=new BaseDto();
		LvBlogTypeService lv= (LvBlogTypeService) factory.getBean("LvBlogTypeService");
		lv.typelist(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvBlogType lvBlogType=new LvBlogType();
		lvBlogType.setType("junit test");
		lvBlogType.setOrderId(Short.parseShort("4"));
		
		lvBlogType.setCode(CodeUtils.getCode());
		lvBlogType.setCreateTime(new Date());
		dto.put("model", lvBlogType);
		LvBlogTypeService lv= (LvBlogTypeService) factory.getBean("LvBlogTypeService");
		lv.save(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvBlogType lvBlogType=new LvBlogType();
		lvBlogType.setId(2);
		dto.put("model", lvBlogType);
		LvBlogTypeService lv= (LvBlogTypeService) factory.getBean("LvBlogTypeService");
		lv.get(dto);
	}

	
}
