package com.lshop.manage.lvBanner.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvBanner;
import com.lshop.common.pojo.logic.LvPageinfo;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvBanner.service.LvBannerService;
import com.lshop.manage.lvPageinfo.service.LvPageinfoService;
import com.lshop.manage.util.BeanFactoryUtil;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvBanner.service.test.LvBannerServiceTest.java]  
 * @ClassName:    [LvBannerServiceTest]   
 * @Description:  [总网站导航--banner图管理--单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 上午11:37:53]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 上午11:37:53]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvBannerServiceTest {
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
		LvBanner lvBanner=new LvBanner();
		lvBanner.setId(1);
		dto.put("model", lvBanner);
		LvBannerService lv= (LvBannerService) factory.getBean("LvBannerService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvBanner lvBanner=new LvBanner();
		lvBanner.setId(2);
		dto.put("model", lvBanner);
		LvBannerService lv= (LvBannerService) factory.getBean("LvBannerService");
		lv.get(dto);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvBanner lvBanner=new LvBanner();
		lvBanner.setId(3);
		dto.put("model", lvBanner);
		LvBannerService lv= (LvBannerService) factory.getBean("LvBannerService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvBanner lvBanner=new LvBanner();
		lvBanner.setBannerKey("KT");
		lvBanner.setTitle("junit banner test!");
		lvBanner.setBody("##########################");
		lvBanner.setOrderValue(3);
		lvBanner.setCode(CodeUtils.getCode());
		lvBanner.setCreateTime(new Date());
		dto.put("model", lvBanner);
		LvBannerService lv= (LvBannerService) factory.getBean("LvBannerService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvBanner lvBanner=new LvBanner();
		lvBanner.setId(2);
		dto.put("model", lvBanner);
		LvBannerService lv= (LvBannerService) factory.getBean("LvBannerService");
		LvBanner tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
