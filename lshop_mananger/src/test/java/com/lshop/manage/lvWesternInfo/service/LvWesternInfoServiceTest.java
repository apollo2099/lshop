package com.lshop.manage.lvWesternInfo.service;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.lshop.manage.util.BeanFactoryUtil;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.manage.lvWesternInfo.service.LvWesternInfoService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvWesternInfo.service.test.LvWesternInfoServiceTest.java]  
 * @ClassName:    [LvWesternInfoServiceTest]   
 * @Description:  [西联汇款管理--单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 上午10:27:54]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 上午10:27:54]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvWesternInfoServiceTest {
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
		LvWesternInfo lvWesternInfo=new LvWesternInfo();
		lvWesternInfo.setOid("CD1343896556062");
		lvWesternInfo.setStatus(Short.parseShort("1"));
		LvWesternInfoService lv= (LvWesternInfoService) factory.getBean("LvWesternInfoService");
		lv.getList(dto);
	}

	@Test
	public void testTruePay() {
		BaseDto dto=new BaseDto();
		LvWesternInfo lvWesternInfo=new LvWesternInfo();
		lvWesternInfo.setId(2);
		lvWesternInfo.setStatus(Short.parseShort("1"));
		dto.put("lvWesternInfo", lvWesternInfo);
		LvWesternInfoService lv= (LvWesternInfoService) factory.getBean("LvWesternInfoService");
		lv.truePay(dto);
	}

}
