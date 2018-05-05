package test.com.lshop.manage.lvApp.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvApp;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvApp.service.LvAppService;
import com.lshop.manage.lvGroupProperty.service.LvGroupPropertyService;
import com.lshop.manage.lvProductLadder.service.LvProductLadderService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvApp.service.test.LvAppServiceTest.java]  
 * @ClassName:    [LvAppServiceTest]   
 * @Description:  [产品应用管理--单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 上午10:06:53]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 上午10:06:53]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvAppServiceTest {
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
		LvApp lvApp=new LvApp();
		lvApp.setId(1);
		dto.put("lvApp", lvApp);
		LvAppService lv= (LvAppService) factory.getBean("LvAppService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvApp lvApp=new LvApp();
		lvApp.setId(3);
		dto.put("lvApp", lvApp);
		LvAppService lv= (LvAppService) factory.getBean("LvAppService");
		lv.delete(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvApp lvApp=new LvApp();
		lvApp.setName("app name junit!");
		lvApp.setVersion("1.0");
		lvApp.setApplyModel("M_TEST");
		lvApp.setApplyVersion("gv3.0");
		lvApp.setLanguage("cn");
		lvApp.setThirdParty("BETV網絡電視");
		lvApp.setAppSize("1024kb");
		lvApp.setGrade(3);
		lvApp.setDownload("www.test.com");
		lvApp.setIntroduce("Junit test!");
		lvApp.setTypeCode("CD1341297270219");
		lvApp.setAppImage("/web/tvpadcn/images/app_03.gif");
		lvApp.setSortId(3);
		lvApp.setCode(CodeUtils.getCode());
		lvApp.setCreateTime(new Date());
		dto.put("lvApp", lvApp);
		LvAppService lv= (LvAppService) factory.getBean("LvAppService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvApp lvApp=new LvApp();
		lvApp.setId(2);
		dto.put("lvApp", lvApp);
		LvAppService lv= (LvAppService) factory.getBean("LvAppService");
		LvApp appTmp=lv.get(dto);
        if(appTmp!=null){
    		appTmp.setModifyTime(new Date());
    		appTmp.setModifyUserId(2);
    		appTmp.setModifyUserName("admin@");
    		dto.put("lvApp", appTmp);
    		lv.update(dto);
        }
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvApp lvApp=new LvApp();
		lvApp.setId(2);
		dto.put("lvApp", lvApp);
		LvAppService lv= (LvAppService) factory.getBean("LvAppService");
		lv.get(dto);
	}

}
