package com.lshop.manage.lvOrder.service;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvOrderRemind;
import com.lshop.manage.lvOrderRemind.service.LvOrderRemindService;
import com.lshop.manage.util.BeanFactoryUtil;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.test.LvOrderRemindServiceTest.java]  
 * @ClassName:    [LvOrderRemindServiceTest]   
 * @Description:  [订单提醒管理--单元测试 service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 下午04:19:06]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 下午04:19:06]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvOrderRemindServiceTest {
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
		LvOrderRemind lvOrderRemind=new LvOrderRemind();
		lvOrderRemind.setOrderId("CD000000000001");
		dto.put("lvOrderRemind", lvOrderRemind);
		LvOrderRemindService lv= (LvOrderRemindService) factory.getBean("LvOrderRemindService");
		lv.getList(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvOrderRemind lvOrderRemind=new LvOrderRemind();
		lvOrderRemind.setId(2);
		dto.put("lvOrderRemind", lvOrderRemind);
		LvOrderRemindService lv= (LvOrderRemindService) factory.getBean("LvOrderRemindService");
		lv.get(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		dto.put("ids", "4,3");
		LvOrderRemindService lv= (LvOrderRemindService) factory.getBean("LvOrderRemindService");
		lv.delete(dto);
	}

}
