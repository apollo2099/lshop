package test.com.lshop.manage.lvAd.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvAd;
import com.lshop.common.pojo.logic.LvApp;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvAd.service.LvAdService;
import com.lshop.manage.lvApp.service.LvAppService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAd.service.test.LvAdServiceTest.java]  
 * @ClassName:    [LvAdServiceTest]   
 * @Description:  [广告信息管理--单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 上午10:55:59]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 上午10:55:59]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvAdServiceTest {
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
		LvAd lvAd=new LvAd();
		lvAd.setId(1);
		dto.put("lvAd", lvAd);
		LvAdService lv= (LvAdService) factory.getBean("LvAdService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvAd lvAd=new LvAd();
		lvAd.setId(11);
		dto.put("lvAd", lvAd);
		LvAdService lv= (LvAdService) factory.getBean("LvAdService");
		lv.delete(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvAd lvAd=new LvAd();
		lvAd.setId(2);
		dto.put("lvAd", lvAd);
		LvAdService lv= (LvAdService) factory.getBean("LvAdService");
		lv.get(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvAd lvAd=new LvAd();
        lvAd.setAdKey("AD_LOCATION_APP");
        lvAd.setAdTitle("应用广告");
        lvAd.setAdContent("<div class=\"cm_ad_left\"><img src=\"/web/tvpadcn/images/pic02.gif\" width=\"200\" height=\"250\" /></div>");
		lvAd.setUrl("www.tvpadtest.com");
		lvAd.setSortId(2);
		lvAd.setCode(CodeUtils.getCode());
		lvAd.setCreateTime(new Date());
		dto.put("lvAd", lvAd);
		LvAdService lv= (LvAdService) factory.getBean("LvAdService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvAd lvAd=new LvAd();
		lvAd.setId(2);
		dto.put("lvAd", lvAd);
		LvAdService lv= (LvAdService) factory.getBean("LvAdService");
		LvAd adTmp=lv.get(dto);
        if(adTmp!=null){
        	adTmp.setModifyTime(new Date());
        	adTmp.setModifyUserId(2);
        	adTmp.setModifyUserName("admin@");
    		dto.put("lvAd", adTmp);
    		lv.update(dto);
        }
	}

}
