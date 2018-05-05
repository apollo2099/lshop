package test.com.lshop.manage.lvExchangeRate.service;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvExchangeRate;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvExchangeRate.service.LvExchangeRateService;
import test.com.lshop.manage.util.BeanFactoryUtil;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [test.com.lshop.manage.lvExchangeRate.service.LvExchangeRateServiceTest.java]  
 * @ClassName:    [LvExchangeRateServiceTest]   
 * @Description:  [商城汇率管理单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-12-25 下午03:56:46]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-12-25 下午03:56:46]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvExchangeRateServiceTest {
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
		dto.put("page",page);
		LvExchangeRateService lv= (LvExchangeRateService) factory.getBean("LvExchangeRateService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvExchangeRate lvExchangeRate=new LvExchangeRate();
		lvExchangeRate.setId(11);
		dto.put("model", lvExchangeRate);
		LvExchangeRateService lv= (LvExchangeRateService) factory.getBean("LvExchangeRateService");
		LvExchangeRate temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvExchangeRate lvExchangeRate=new LvExchangeRate();
		lvExchangeRate.setCurrency("EUR");
		lvExchangeRate.setCurrencyName("EUR  欧元");
		lvExchangeRate.setExchangeRate(0.98f);
		lvExchangeRate.setMainCurrency("USD");
		lvExchangeRate.setMainCurrencyName("USD 美元");
		lvExchangeRate.setStoreId("tvpad");
		lvExchangeRate.setCode(CodeUtils.getCode());
		lvExchangeRate.setCreateTime(new Date());
		dto.put("model", lvExchangeRate);
		LvExchangeRateService lv= (LvExchangeRateService) factory.getBean("LvExchangeRateService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvExchangeRate lvExchangeRate=new LvExchangeRate();
		lvExchangeRate.setId(12);
		dto.put("model", lvExchangeRate);
		LvExchangeRateService lv= (LvExchangeRateService) factory.getBean("LvExchangeRateService");
		LvExchangeRate tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setExchangeRate(0.98f);
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

	@Test
	public void testIsExistRate() {
		BaseDto dto=new BaseDto();
		LvExchangeRate lvExchangeRate=new LvExchangeRate();
		lvExchangeRate.setCurrency("CYN");
		dto.put("model", lvExchangeRate);
		LvExchangeRateService lv= (LvExchangeRateService) factory.getBean("LvExchangeRateService");
		Boolean isFlag=lv.isExistRate(dto);
		assertFalse(isFlag);
	}

}
