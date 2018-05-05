package com.lshop.manage.blog.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvBlogTags;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.blog.service.LvBlogTagsService;
import com.lshop.manage.blog.service.LvBlogTypeService;
import com.lshop.manage.util.BeanFactoryUtil;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.test.LvBlogTagsServiceTest.java]  
 * @ClassName:    [LvBlogTagsServiceTest]   
 * @Description:  [一句话描述该类的功能]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-15 下午02:47:10]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-15 下午02:47:10]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvBlogTagsServiceTest {
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
		LvBlogTags lvBlogTags=new LvBlogTags();
		lvBlogTags.setId(1);
		dto.put("model", lvBlogTags);
		LvBlogTagsService lv= (LvBlogTagsService) factory.getBean("LvBlogTagsService");
		lv.list(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvBlogTags lvBlogTags=new LvBlogTags();
		lvBlogTags.setId(5);
		dto.put("model", lvBlogTags);
		LvBlogTagsService lv= (LvBlogTagsService) factory.getBean("LvBlogTagsService");
		lv.delete(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvBlogTags lvBlogTags=new LvBlogTags();
		lvBlogTags.setId(2);
		dto.put("model", lvBlogTags);
		LvBlogTagsService lv= (LvBlogTagsService) factory.getBean("LvBlogTagsService");
		lv.get(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvBlogTags lvBlogTags=new LvBlogTags();
		lvBlogTags.setTagName("tvpad test");
		lvBlogTags.setOrderId(5);
		lvBlogTags.setIsShow(1);
		
		lvBlogTags.setCode(CodeUtils.getCode());
		lvBlogTags.setCreateTime(new Date());
		dto.put("model", lvBlogTags);
		LvBlogTagsService lv= (LvBlogTagsService) factory.getBean("LvBlogTagsService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvBlogTags lvBlogTags=new LvBlogTags();
		lvBlogTags.setId(3);
		dto.put("model", lvBlogTags);
		LvBlogTagsService lv= (LvBlogTagsService) factory.getBean("LvBlogTagsService");
		LvBlogTags adTmp=lv.get(dto);
        if(adTmp!=null){
        	adTmp.setTagName("tvpad test update");
        	adTmp.setOrderId(4);
    		adTmp.setIsShow(1);
        	adTmp.setModifyTime(new Date());
        	adTmp.setModifyUserId(2);
        	adTmp.setModifyUserName("admin@");
    		dto.put("model", adTmp);
    		lv.update(dto);
        }
	}



}
