package com.lshop.manage.lvAccount.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvAd;
import com.lshop.common.pojo.user.LvAccountInfo;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvAccount.service.LvAccountInfoService;
import com.lshop.manage.lvAd.service.LvAdService;
import com.lshop.manage.util.BeanFactoryUtil;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAccount.service.test.LvAccountInfoServiceTest.java]  
 * @ClassName:    [LvAccountInfoServiceTest]   
 * @Description:  [用户登录信息管理-单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 下午02:15:27]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 下午02:15:27]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvAccountInfoServiceTest {
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
		LvAccountInfoService lv= (LvAccountInfoService) factory.getBean("LvAccountInfoService");
		lv.getList(dto);
	}


	@Test
	public void testSave() {
//		BaseDto dto=new BaseDto();
//		LvAccountInfo lvAccountInfo=new LvAccountInfo();
//		lvAccountInfo.setUserCode("465424137@qq.com");
//		lvAccountInfo.setSex(Byte.parseByte("1"));
//		lvAccountInfo.setQq("123456");
//		lvAccountInfo.setContryId(100023);
//		lvAccountInfo.setContryName("中国");
//		lvAccountInfo.setCode(CodeUtils.getCode());
//		lvAccountInfo.setCreateTime(new Date());
//		dto.put("lvAccountInfo", lvAccountInfo);
//		LvAccountInfoService lv= (LvAccountInfoService) factory.getBean("LvAccountInfoService");
//		lv.save(dto);
	}

	@Test
	public void testGetAccountInfo() {
		BaseDto dto=new BaseDto();
		dto.put("usercode","cd123456");
		LvAccountInfoService lv= (LvAccountInfoService) factory.getBean("LvAccountInfoService");
		lv.getAccountInfo(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		dto.put("usercode","CD1344822098546");
		LvAccountInfoService lv= (LvAccountInfoService) factory.getBean("LvAccountInfoService");
		LvAccountInfo lvAccountInfo=lv.getAccountInfo(dto);;
        if(lvAccountInfo!=null){
    		lvAccountInfo.setSex(Byte.parseByte("1"));
    		lvAccountInfo.setQq("123456");
    		lvAccountInfo.setMsn("liaoxj@msn.cn");
    		lvAccountInfo.setName("liaoxj text");
    		lvAccountInfo.setTel("0755123456");
    		lvAccountInfo.setTel("13927409420");
    		lvAccountInfo.setContryId(100023);
    		lvAccountInfo.setContryName("中国");
    		lvAccountInfo.setProvinceName("guanzhou");
    		lvAccountInfo.setCityName("shenzheng");
    		lvAccountInfo.setModifyTime(new Date());
    		lvAccountInfo.setModifyUserId(2);
    		lvAccountInfo.setModifyUserName("admin@");
    		dto.put("lvAccountInfo", lvAccountInfo);
    		lv.update(dto);
        }
	}

}
