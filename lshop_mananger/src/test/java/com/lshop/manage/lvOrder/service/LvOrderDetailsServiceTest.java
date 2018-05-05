package com.lshop.manage.lvOrder.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvOrder.service.LvOrderDetailsService;
import com.lshop.manage.util.BeanFactoryUtil;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.test.LvOrderDetailsServiceTest.java]  
 * @ClassName:    [LvOrderDetailsServiceTest]   
 * @Description:  [订单详情管理--单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 下午03:52:24]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 下午03:52:24]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvOrderDetailsServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testGetOrderDetails() {
		BaseDto dto=new BaseDto();
		dto.put("orderId", "W2012081315413967711");
		LvOrderDetailsService lv= (LvOrderDetailsService) factory.getBean("LvOrderDetailsService");
		lv.getOrderDetails(dto);
	}

	@Test
	public void testGetCouponCode() {
		BaseDto dto=new BaseDto();
		dto.put("orderId", "W2012081315413967711");
		LvOrderDetailsService lv= (LvOrderDetailsService) factory.getBean("LvOrderDetailsService");
		lv.getCouponCode(dto);
	}

	@Test
	public void testGetDetailsByOid() {
		BaseDto dto=new BaseDto();
		dto.put("orderId", "W2012081315413967711");
		LvOrderDetailsService lv= (LvOrderDetailsService) factory.getBean("LvOrderDetailsService");
		lv.getDetailsByOid(dto);
	}

	@Test
	public void testGetDetailsByCouponCode() {
		BaseDto dto=new BaseDto();
		dto.put("couponCode", "99378765");
		LvOrderDetailsService lv= (LvOrderDetailsService) factory.getBean("LvOrderDetailsService");
		lv.getDetailsByCouponCode(dto);
	}

	@Test
	public void testSave() {
		//TS000000000001
		BaseDto dto=new BaseDto();
		LvOrderDetails lvOrderDetails=new LvOrderDetails();
		lvOrderDetails.setOrderId("TS000000000001");
		lvOrderDetails.setProductCode("CD1341308735749");
		lvOrderDetails.setOprice(1109f);
		lvOrderDetails.setOremark("test");
		lvOrderDetails.setPnum(11);
		lvOrderDetails.setCurrency("RMB");
		lvOrderDetails.setIsDelete(0);
		lvOrderDetails.setIsPackage(0);
		lvOrderDetails.setPcode("132436875197272UUU67TTTT");
	   
		lvOrderDetails.setCode(CodeUtils.getCode());
		lvOrderDetails.setCreateTime(new Date());
		dto.put("lvOrderDetails", lvOrderDetails);
		LvOrderDetailsService lv= (LvOrderDetailsService) factory.getBean("LvOrderDetailsService");
		lv.save(dto);
	}

}
