package com.lshop.manage.lvAccount.service;

import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.manage.lvAccount.service.LvAccountService;
import com.lshop.manage.util.BeanFactoryUtil;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAccount.service.test.LvAccountServiceTest.java]  
 * @ClassName:    [LvAccountServiceTest]   
 * @Description:  [用户登录账户信息管理-单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 下午01:34:24]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 下午01:34:24]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvAccountServiceTest {
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
		LvAccount lvAccount=new LvAccount();
		lvAccount.setId(1);
		dto.put("lvAccount", lvAccount);
		LvAccountService lv= (LvAccountService) factory.getBean("LvAccountService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		dto.put("ids", "11,13");
		LvAccountService lv= (LvAccountService) factory.getBean("LvAccountService");
		lv.delete(dto);
	}

	@Test
	public void testDeleteByEmail() {
		BaseDto dto=new BaseDto();
		LvAccount lvAccount=new LvAccount();
		lvAccount.setEmail("123@qq.com");
		dto.put("lvAccount", lvAccount);
		LvAccountService lv= (LvAccountService) factory.getBean("LvAccountService");
		lv.deleteByEmail(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvAccount lvAccount=new LvAccount();
		lvAccount.setId(2);
		dto.put("lvAccount", lvAccount);
		LvAccountService lv= (LvAccountService) factory.getBean("LvAccountService");
		lv.get(dto);
	}



	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvAccount lvAccount=new LvAccount();
		lvAccount.setId(1);
		dto.put("lvAccount", lvAccount);
		LvAccountService lv= (LvAccountService) factory.getBean("LvAccountService");
		LvAccount adTmp=(LvAccount) lv.get(dto);
        if(adTmp!=null){
        	adTmp.setModifyTime(new Date());
        	adTmp.setModifyUserId(2);
        	adTmp.setModifyUserName("admin@");
    		dto.put("lvAccount", adTmp);
    		lv.update(dto);
        }
	}

	@Test
	public void testGetAccount() {
		BaseDto dto=new BaseDto();
		dto.put("accountCode", "cd123456");
		LvAccountService lv= (LvAccountService) factory.getBean("LvAccountService");
		lv.getAccount(dto);
	}

}
