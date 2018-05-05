package test.com.lshop.manage.lvOrder.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvOrderLogs.service.LvOrderLogsService;
import com.lshop.manage.lvPayLogs.service.LvPayLogsService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.test.LvPayLogsServiceTest.java]  
 * @ClassName:    [LvPayLogsServiceTest]   
 * @Description:  [支付日志管理-单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 下午05:51:47]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 下午05:51:47]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvPayLogsServiceTest {
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
		LvPayLogs lvPayLogs=new LvPayLogs();
		lvPayLogs.setOid("C2011092113392234811");
		dto.put("lvPayLogs", lvPayLogs);
		LvPayLogsService lv= (LvPayLogsService) factory.getBean("LvPayLogsService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvPayLogs lvPayLogs=new LvPayLogs();
		lvPayLogs.setId(3);
		dto.put("lvPayLogs", lvPayLogs);
		LvPayLogsService lv= (LvPayLogsService) factory.getBean("LvPayLogsService");
		lv.delete(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvPayLogs lvPayLogs=new LvPayLogs();
		lvPayLogs.setOid("CD000000000002");
		lvPayLogs.setPaymethod(Short.parseShort("0"));
		lvPayLogs.setPaydate(new Date());
		lvPayLogs.setCheckno("liaoxiongjian.2008@163.com");
		lvPayLogs.setAmount(10f);
		lvPayLogs.setStatus(Short.parseShort("1"));
		lvPayLogs.setBankorderid("bk000000002222");
		lvPayLogs.setCode(CodeUtils.getCode());
		lvPayLogs.setCreateTime(new Date());
		dto.put("lvPayLogs", lvPayLogs);
		LvPayLogsService lv= (LvPayLogsService) factory.getBean("LvPayLogsService");
		lv.save(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvPayLogs lvPayLogs=new LvPayLogs();
		lvPayLogs.setId(1);
		dto.put("lvPayLogs", lvPayLogs);
		LvPayLogsService lv= (LvPayLogsService) factory.getBean("LvPayLogsService");
		lv.get(dto);
	}

}
