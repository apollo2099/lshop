package test.com.lshop.manage.lvShopPartner.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvShopPartner;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvShopPartner.service.LvShopPartnerService;
import com.lshop.manage.lvShopProduct.service.LvShopProductService;

import test.com.lshop.manage.util.BeanFactoryUtil;

public class LvShopPartnerServiceTest {
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
//		LvShopPartner lvShopPartner=new LvShopPartner();
//		lvShopProduct.setSubjectType("code2");
//		dto.put("model", lvShopProduct);
		dto.put("page",page);
		LvShopPartnerService lv= (LvShopPartnerService) factory.getBean("LvShopPartnerService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvShopPartner lvShopPartner=new LvShopPartner();
		lvShopPartner.setId(1);
		dto.put("model", lvShopPartner);
		LvShopPartnerService lv= (LvShopPartnerService) factory.getBean("LvShopPartnerService");
		LvShopPartner temp=lv.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvShopPartner lvShopPartner=new LvShopPartner();
		lvShopPartner.setId(8);
		dto.put("model", lvShopPartner);
		LvShopPartnerService lv= (LvShopPartnerService) factory.getBean("LvShopPartnerService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvShopPartner lvShopPartner=new LvShopPartner();
		lvShopPartner.setShopName("test");
		lvShopPartner.setShopUrl("http://www.12306.cn");
		lvShopPartner.setExhibitType(Short.parseShort("1"));
		lvShopPartner.setStoreId("tvpad");
		lvShopPartner.setCode(CodeUtils.getCode());
		lvShopPartner.setCreateTime(new Date());
		dto.put("model", lvShopPartner);
		LvShopPartnerService lv= (LvShopPartnerService) factory.getBean("LvShopPartnerService");
		LvShopPartner temp=lv.save(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvShopPartner lvShopPartner=new LvShopPartner();
		lvShopPartner.setId(6);
		dto.put("model", lvShopPartner);
		LvShopPartnerService lv= (LvShopPartnerService) factory.getBean("LvShopPartnerService");
		LvShopPartner tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setShopLogo("/res/ttt.jpg");
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
