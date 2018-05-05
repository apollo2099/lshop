package test.com.lshop.manage.blog.service;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvBlogSubscribe;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.blog.service.LvBlogSubscribeService;

public class LvBlogSubscribeServiceTest {
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
		LvBlogSubscribeService lv= (LvBlogSubscribeService) factory.getBean("LvBlogSubscribeService");
		lv.list(dto);
	}



	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvBlogSubscribe lvBlogSubscribe=new LvBlogSubscribe();
		lvBlogSubscribe.setId(4);
		dto.put("model", lvBlogSubscribe);
		LvBlogSubscribeService lv= (LvBlogSubscribeService) factory.getBean("LvBlogSubscribeService");
		lv.delete(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvBlogSubscribe lvBlogSubscribe=new LvBlogSubscribe();
		lvBlogSubscribe.setId(2);
		dto.put("model", lvBlogSubscribe);
		LvBlogSubscribeService lv= (LvBlogSubscribeService) factory.getBean("LvBlogSubscribeService");
		lv.get(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvBlogSubscribe lvBlogSubscribe=new LvBlogSubscribe();
		lvBlogSubscribe.setTitle("test");
		lvBlogSubscribe.setSendCycle(1);
		
		lvBlogSubscribe.setAdUrl("www.google.cn");
		
		lvBlogSubscribe.setCode(CodeUtils.getCode());
		lvBlogSubscribe.setCreateTime(new Date());
		dto.put("model", lvBlogSubscribe);
		LvBlogSubscribeService lv= (LvBlogSubscribeService) factory.getBean("LvBlogSubscribeService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvBlogSubscribe lvBlogSubscribe=new LvBlogSubscribe();
		lvBlogSubscribe.setId(3);
		dto.put("model", lvBlogSubscribe);
		LvBlogSubscribeService lv= (LvBlogSubscribeService) factory.getBean("LvBlogSubscribeService");
		LvBlogSubscribe adTmp=lv.get(dto);
        if(adTmp!=null){
        	adTmp.setTitle("update test");
        	adTmp.setSendCycle(1);
    		adTmp.setAdUrl("www.google.cn");
    		
        	adTmp.setModifyTime(new Date());
        	adTmp.setModifyUserId(2);
        	adTmp.setModifyUserName("admin@");
    		dto.put("model", adTmp);
    		lv.update(dto);
        }
	}


}
