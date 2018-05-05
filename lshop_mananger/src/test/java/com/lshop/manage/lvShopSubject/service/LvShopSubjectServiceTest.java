package com.lshop.manage.lvShopSubject.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvExchangeRate;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvExchangeRate.service.LvExchangeRateService;
import com.lshop.manage.lvShopSubject.service.LvShopSubjectService;
import com.lshop.manage.util.BeanFactoryUtil;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [test.com.lshop.manage.lvShopSubject.service.LvShopSubjectServiceTest.java]  
 * @ClassName:    [LvShopSubjectServiceTest]   
 * @Description:  [栏目管理单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-12-25 下午03:57:14]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-12-25 下午03:57:14]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvShopSubjectServiceTest {

	
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
		LvShopSubject lvShopSubject=new LvShopSubject();
		lvShopSubject.setSubjectName("TVpad專區");
		dto.put("model", lvShopSubject);
		dto.put("page",page);
		LvShopSubjectService lv= (LvShopSubjectService) factory.getBean("LvShopSubjectService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvShopSubject lvShopSubject=new LvShopSubject();
		lvShopSubject.setId(1);
		dto.put("model", lvShopSubject);
		LvShopSubjectService lv= (LvShopSubjectService) factory.getBean("LvShopSubjectService");
		LvShopSubject temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvShopSubject lvShopSubject=new LvShopSubject();
		lvShopSubject.setId(8);
		dto.put("model", lvShopSubject);
		LvShopSubjectService lv= (LvShopSubjectService) factory.getBean("LvShopSubjectService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvShopSubject lvShopSubject=new LvShopSubject();
		lvShopSubject.setExhibitType(Short.parseShort("1"));
		lvShopSubject.setSubjectName("test");
		lvShopSubject.setOrderValue(0);
		lvShopSubject.setStoreId("tvpad");
		lvShopSubject.setCode(CodeUtils.getCode());
		lvShopSubject.setCreateTime(new Date());
		dto.put("model", lvShopSubject);
		LvShopSubjectService lv= (LvShopSubjectService) factory.getBean("LvShopSubjectService");
		LvShopSubject temp=lv.save(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvShopSubject lvShopSubject=new LvShopSubject();
		lvShopSubject.setId(7);
		dto.put("model", lvShopSubject);
		LvShopSubjectService lv= (LvShopSubjectService) factory.getBean("LvShopSubjectService");
		LvShopSubject tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}
	@Test
	public void testIsExistSubject(){
		BaseDto dto=new BaseDto();
		LvShopSubject lvShopSubject=new LvShopSubject();
		lvShopSubject.setSubjectName("TVpad專區");
		dto.put("model", lvShopSubject);
		LvShopSubjectService lv= (LvShopSubjectService) factory.getBean("LvShopSubjectService");
		Boolean isFlag=lv.IsExistSubject(dto);
		assertFalse(isFlag);
	}

}
