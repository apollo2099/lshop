package com.lshop.manage.lvShopTags.service;

import static org.junit.Assert.*;

import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvShopTags;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvShopTags.service.LvShopTagsService;
import com.lshop.manage.util.BeanFactoryUtil;



public class LvShopTagsServiceTest {

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
		LvShopTags lvShopTags=new LvShopTags();
		lvShopTags.setTagName("iphone");
		dto.put("model", lvShopTags);
		dto.put("page",page);
		LvShopTagsService lv= (LvShopTagsService) factory.getBean("LvShopTagsService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvShopTags lvShopTags=new LvShopTags();
		lvShopTags.setId(1);
		dto.put("model", lvShopTags);
		LvShopTagsService lv= (LvShopTagsService) factory.getBean("LvShopTagsService");
		LvShopTags temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvShopTags lvShopTags=new LvShopTags();
		lvShopTags.setId(7);
		dto.put("model", lvShopTags);
		LvShopTagsService lv= (LvShopTagsService) factory.getBean("LvShopTagsService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvShopTags lvShopTags=new LvShopTags();
		lvShopTags.setTagName("小米M2");
		lvShopTags.setTagUrl("http://www.xiaomi.com");
		lvShopTags.setTagFontStyle("<b>tagFontStyle</b>");
		lvShopTags.setTagFontColor("#FF00FF");
		lvShopTags.setStoreId("tvpad");
		lvShopTags.setCode(CodeUtils.getCode());
		lvShopTags.setCreateTime(new Date());
		dto.put("model", lvShopTags);
		LvShopTagsService lv= (LvShopTagsService) factory.getBean("LvShopTagsService");
		LvShopTags temp=lv.save(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvShopTags lvShopTags=new LvShopTags();
		lvShopTags.setId(6);
		dto.put("model", lvShopTags);
		LvShopTagsService lv= (LvShopTagsService) factory.getBean("LvShopTagsService");
		LvShopTags tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin_junit");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
