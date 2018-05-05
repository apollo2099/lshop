package test.com.lshop.manage.lvShopActivity.service;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvShopActivity;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvShopActivity.service.LvShopActivityService;
import test.com.lshop.manage.util.BeanFactoryUtil;

public class LvShopActivityServiceTest {

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
		LvShopActivity lvShopActivity=new LvShopActivity();
		lvShopActivity.setAvtivityName("tvpad");
		dto.put("model", lvShopActivity);
		dto.put("page",page);
		LvShopActivityService lv= (LvShopActivityService) factory.getBean("LvShopActivityService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvShopActivity lvShopActivity=new LvShopActivity();
		lvShopActivity.setId(2);
		dto.put("model", lvShopActivity);
		LvShopActivityService lv= (LvShopActivityService) factory.getBean("LvShopActivityService");
		LvShopActivity temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvShopActivity lvShopActivity=new LvShopActivity();
		lvShopActivity.setId(8);
		dto.put("model", lvShopActivity);
		LvShopActivityService lv= (LvShopActivityService) factory.getBean("LvShopActivityService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvShopActivity lvShopActivity=new LvShopActivity();
		lvShopActivity.setAvtivityName("活动促销");
		lvShopActivity.setAvtivityUrl("www.baidu.com");
		lvShopActivity.setAvtivityTime("1月1日-2月1日");
		lvShopActivity.setStoreId("tvpad");
		lvShopActivity.setCode(CodeUtils.getCode());
		lvShopActivity.setCreateTime(new Date());
		dto.put("model", lvShopActivity);
		LvShopActivityService lv= (LvShopActivityService) factory.getBean("LvShopActivityService");
		LvShopActivity temp=lv.save(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvShopActivity lvShopActivity=new LvShopActivity();
		lvShopActivity.setId(7);
		dto.put("model", lvShopActivity);
		LvShopActivityService lv= (LvShopActivityService) factory.getBean("LvShopActivityService");
		LvShopActivity tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin_junit");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
