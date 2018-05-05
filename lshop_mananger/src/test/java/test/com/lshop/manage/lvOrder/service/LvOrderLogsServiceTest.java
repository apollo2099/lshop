package test.com.lshop.manage.lvOrder.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvNavigation;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvOrderRemind;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvNavigation.service.LvNavigationService;
import com.lshop.manage.lvOrderLogs.service.LvOrderLogsService;
import com.lshop.manage.lvOrderRemind.service.LvOrderRemindService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.test.LvOrderLogsServiceTest.java]  
 * @ClassName:    [LvOrderLogsServiceTest]   
 * @Description:  [订单日志管理--单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 下午05:49:35]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 下午05:49:35]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvOrderLogsServiceTest {
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
		LvOrderLogs lvOrderLogs=new LvOrderLogs();
        lvOrderLogs.setOrd("C2011082618224039286");
		dto.put("lvOrderLogs", lvOrderLogs);
		LvOrderLogsService lv= (LvOrderLogsService) factory.getBean("LvOrderLogsService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvOrderLogs lvOrderLogs=new LvOrderLogs();
		lvOrderLogs.setId(3);
		dto.put("lvOrderLogs", lvOrderLogs);
		LvOrderLogsService lv= (LvOrderLogsService) factory.getBean("LvOrderLogsService");
		lv.delete(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvOrderLogs lvOrderLogs=new LvOrderLogs();
		lvOrderLogs.setOrd("W2012080915301059700");
		lvOrderLogs.setOperate("junit test");
		lvOrderLogs.setUname("admin");
		lvOrderLogs.setCode(CodeUtils.getCode());
		lvOrderLogs.setCreateTime(new Date());
		dto.put("lvOrderLogs", lvOrderLogs);
		LvOrderLogsService lv= (LvOrderLogsService) factory.getBean("LvOrderLogsService");
		lv.save(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvOrderLogs lvOrderLogs=new LvOrderLogs();
		lvOrderLogs.setId(1);
		dto.put("lvOrderLogs", lvOrderLogs);
		LvOrderLogsService lv= (LvOrderLogsService) factory.getBean("LvOrderLogsService");
		lv.get(dto);
	}

}
