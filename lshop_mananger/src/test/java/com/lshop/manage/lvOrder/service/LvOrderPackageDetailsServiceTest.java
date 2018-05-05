package com.lshop.manage.lvOrder.service;

import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvOrder.service.LvOrderPackageDetailsService;
import com.lshop.manage.util.BeanFactoryUtil;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.test.LvOrderPackageDetailsServiceTest.java]  
 * @ClassName:    [LvOrderPackageDetailsServiceTest]   
 * @Description:  [套餐订单关联关系--单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 下午04:14:29]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 下午04:14:29]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvOrderPackageDetailsServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testSave() {
		//TS000000000001
		BaseDto dto=new BaseDto();
		LvOrderPackageDetails lvOrderPackageDetails=new LvOrderPackageDetails();
		lvOrderPackageDetails.setOrderDetailsCode("CD1344845107656");
		lvOrderPackageDetails.setProductCode("CD1341308735749");
		lvOrderPackageDetails.setOprice(1109f);
		lvOrderPackageDetails.setPnum(11);
		lvOrderPackageDetails.setCurrency("RMB");
		lvOrderPackageDetails.setPcode("132436875197272UUU67TTTT");
	   
		lvOrderPackageDetails.setCode(CodeUtils.getCode());
		lvOrderPackageDetails.setCreateTime(new Date());
		dto.put("lvOrderPackageDetails", lvOrderPackageDetails);
		LvOrderPackageDetailsService lv= (LvOrderPackageDetailsService) factory.getBean("LvOrderPackageDetailsService");
		lv.save(dto);
	}

}
