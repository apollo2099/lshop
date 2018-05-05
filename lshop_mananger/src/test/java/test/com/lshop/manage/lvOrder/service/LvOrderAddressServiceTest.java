package test.com.lshop.manage.lvOrder.service;


import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvOrder.service.LvOrderAddressService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.test.LvOrderAddressServiceTest.java]  
 * @ClassName:    [LvOrderAddressServiceTest]   
 * @Description:  [订单地址详情--单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-13 下午03:52:03]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-13 下午03:52:03]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvOrderAddressServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	@Test
	public void testGetOrderAddress() {
		BaseDto dto=new BaseDto();
		dto.put("orderId", "W2012081315413967711");
		LvOrderAddressService lv= (LvOrderAddressService) factory.getBean("LvOrderAddressService");
		lv.getOrderAddress(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvOrderAddress lvOrderAddress=new LvOrderAddress();
		lvOrderAddress.setOrderId("TS000000000001");
		lvOrderAddress.setRelCode("CD1309834713468");
		lvOrderAddress.setRelName("junit test user");
		lvOrderAddress.setPostCode("412315");
		lvOrderAddress.setMobile("15016726482");
		lvOrderAddress.setTel("07552222333");
		lvOrderAddress.setContryId(100023);
		lvOrderAddress.setContryName("中国");
		lvOrderAddress.setProvinceName("广州");
		lvOrderAddress.setCityName("深圳");
		lvOrderAddress.setAdress("福田");
       
		lvOrderAddress.setCode(CodeUtils.getCode());
		lvOrderAddress.setCreateTime(new Date());
		dto.put("lvOrderAddress", lvOrderAddress);
		LvOrderAddressService lv= (LvOrderAddressService) factory.getBean("LvOrderAddressService");
		lv.save(dto);
	}

}
