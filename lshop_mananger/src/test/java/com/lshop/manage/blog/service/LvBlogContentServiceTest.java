package com.lshop.manage.blog.service;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.blog.service.LvBlogContentService;
import com.lshop.manage.util.BeanFactoryUtil;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.test.LvBlogContentServiceTest.java]  
 * @ClassName:    [LvBlogContentServiceTest]   
 * @Description:  [华扬博客文章管理]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-15 下午04:34:51]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-15 下午04:34:51]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvBlogContentServiceTest {
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
		LvBlogContent lvBlogContent=new LvBlogContent();
		lvBlogContent.setId(1);
		dto.put("model", lvBlogContent);
		LvBlogContentService lv= (LvBlogContentService) factory.getBean("LvBlogContentService");
		lv.list(dto);
	}

	@Test
	public void testAdd() {
		BaseDto dto=new BaseDto();
		LvBlogContent lvBlogContent=new LvBlogContent();
		lvBlogContent.setUserId(2);
		lvBlogContent.setUserName("admin");
		lvBlogContent.setTitle("test title");
		lvBlogContent.setContent("test content");
		lvBlogContent.setIntor("test intor");
		lvBlogContent.setType(2);
		
		lvBlogContent.setCode(CodeUtils.getCode());
		lvBlogContent.setCreateTime(new Date());
		dto.put("model", lvBlogContent);
		LvBlogContentService lv= (LvBlogContentService) factory.getBean("LvBlogContentService");
		lv.add(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvBlogContent lvBlogContent=new LvBlogContent();
		lvBlogContent.setId(167);
		dto.put("model", lvBlogContent);
		LvBlogContentService lv= (LvBlogContentService) factory.getBean("LvBlogContentService");
		lv.delete(dto);
	}

	@Test
	public void testUpdate() {		
		BaseDto dto=new BaseDto();
		LvBlogContent lvBlogContent=new LvBlogContent();
		lvBlogContent.setId(175);
		dto.put("model", lvBlogContent);
		LvBlogContentService lv= (LvBlogContentService) factory.getBean("LvBlogContentService");
		LvBlogContent adTmp=lv.get(dto);
        if(adTmp!=null){
 
        	adTmp.setModifyTime(new Date());
        	adTmp.setModifyUserId(2);
        	adTmp.setModifyUserName("admin@");
    		dto.put("model", adTmp);
    		lv.update(dto);
        }
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvBlogContent lvBlogContent=new LvBlogContent();
		lvBlogContent.setId(175);
		dto.put("model", lvBlogContent);
		LvBlogContentService lv= (LvBlogContentService) factory.getBean("LvBlogContentService");
		lv.get(dto);
	}

	@Test
	public void testListTop10() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvBlogContentService lv= (LvBlogContentService) factory.getBean("LvBlogContentService");
		lv.listTop10(dto);
	}

}
