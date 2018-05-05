package test.com.lshop.manage.lvGroupBuy.service;

import static org.junit.Assert.*;

import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvGroupBuy.service.LvGroupBuyService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvGroupBuy.service.test.LvGroupBuyServiceTest.java]  
 * @ClassName:    [LvGroupBuyServiceTest]   
 * @Description:  [团购信息管理--单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 上午10:28:41]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 上午10:28:41]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvGroupBuyServiceTest {
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
		LvGroupBuyService lv= (LvGroupBuyService) factory.getBean("LvGroupBuyService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvGroupBuy lvGroupBuy=new LvGroupBuy();
		lvGroupBuy.setId(3);
		dto.put("lvGroupBuy", lvGroupBuy);
		LvGroupBuyService lv= (LvGroupBuyService) factory.getBean("LvGroupBuyService");
		lv.delete(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvGroupBuy lvGroupBuy=new LvGroupBuy();
		lvGroupBuy.setProductCode("CD1341308735749");
		lvGroupBuy.setTitle("junit test");
		lvGroupBuy.setPrimeCost(45.5f);
		lvGroupBuy.setDiscount(9);
		lvGroupBuy.setPresentPrice(159f);
		lvGroupBuy.setGroupNum(10);
		lvGroupBuy.setPurchasedNum(0);
		lvGroupBuy.setStartTime(new Date());
		lvGroupBuy.setEndTime(DateUtils.parseDateTime("2012-8-20", "yyyy-MM-dd HH:mm:ss"));
		lvGroupBuy.setStatus(1);
		
		lvGroupBuy.setCode(CodeUtils.getCode());
		lvGroupBuy.setCreateTime(new Date());
		dto.put("lvGroupBuy", lvGroupBuy);
		LvGroupBuyService lv= (LvGroupBuyService) factory.getBean("LvGroupBuyService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvGroupBuy lvGroupBuy=new LvGroupBuy();
		lvGroupBuy.setId(2);
		dto.put("lvGroupBuy", lvGroupBuy);
		LvGroupBuyService lv= (LvGroupBuyService) factory.getBean("LvGroupBuyService");
		LvGroupBuy tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setTitle("junit test update");
        	tmp.setPrimeCost(49.5f);
        	tmp.setDiscount(8);
        	tmp.setPresentPrice(149f);
        	tmp.setGroupNum(8);
        	tmp.setPurchasedNum(8);
        	tmp.setStartTime(new Date());
        	tmp.setEndTime(DateUtils.parseDateTime("2012-8-20", "yyyy-MM-dd HH:mm:ss"));
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("lvGroupBuy", tmp);
    		lv.update(dto);
        }
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvGroupBuy lvGroupBuy=new LvGroupBuy();
		lvGroupBuy.setId(1);
		dto.put("lvGroupBuy", lvGroupBuy);
		LvGroupBuyService lv= (LvGroupBuyService) factory.getBean("LvGroupBuyService");
		lv.get(dto);
	}

	@Test
	public void testGetAll() {
		BaseDto dto=new BaseDto();
		LvGroupBuyService lv= (LvGroupBuyService) factory.getBean("LvGroupBuyService");
		lv.getAll(dto);
	}

}
