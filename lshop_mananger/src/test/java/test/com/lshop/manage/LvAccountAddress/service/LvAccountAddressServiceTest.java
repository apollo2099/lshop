package test.com.lshop.manage.LvAccountAddress.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvAd;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.LvAccountAddress.service.LvAccountAddressService;
import com.lshop.manage.lvAd.service.LvAdService;

public class LvAccountAddressServiceTest {
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
		
		LvAccountAddress lvAccountAddress=new LvAccountAddress();
		lvAccountAddress.setRelName("apollo");
		dto.put("lvAccountAddress", lvAccountAddress);
		LvAccountAddressService lv= (LvAccountAddressService) factory.getBean("LvAccountAddressService");
		lv.getList(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		LvAccountAddress lvAccountAddress=new LvAccountAddress();
		lvAccountAddress.setId(57);
		dto.put("lvAccountAddress", lvAccountAddress);
		LvAccountAddressService lv= (LvAccountAddressService) factory.getBean("LvAccountAddressService");
		lv.delete(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvAccountAddress lvAccountAddress=new LvAccountAddress();
		lvAccountAddress.setId(59);
		dto.put("lvAccountAddress", lvAccountAddress);
		LvAccountAddressService lv= (LvAccountAddressService) factory.getBean("LvAccountAddressService");
		lv.get(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvAccountAddress lvAccountAddress=new LvAccountAddress();
        lvAccountAddress.setRelCode("CD1344822098546");
        lvAccountAddress.setRelName("test~junit");
        lvAccountAddress.setPostCode("410000");
        lvAccountAddress.setMobile("15874031190");
        lvAccountAddress.setTel("07334156202");
        lvAccountAddress.setContryId(100023);
        lvAccountAddress.setContryName("中国");
        lvAccountAddress.setProvinceName("广州");
        lvAccountAddress.setCityName("深圳");
        lvAccountAddress.setAdress("福田");
        lvAccountAddress.setIsDefault(Short.parseShort("0"));
        
		lvAccountAddress.setCode(CodeUtils.getCode());
		lvAccountAddress.setCreateTime(new Date());
		dto.put("lvAccountAddress", lvAccountAddress);
		LvAccountAddressService lv= (LvAccountAddressService) factory.getBean("LvAccountAddressService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvAccountAddress lvAccountAddress=new LvAccountAddress();

		lvAccountAddress.setId(58);
		dto.put("lvAccountAddress", lvAccountAddress);
		LvAccountAddressService lv= (LvAccountAddressService) factory.getBean("LvAccountAddressService");
		LvAccountAddress tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setRelCode("CD1344822098546");
            tmp.setRelName("test~junit~update");
            tmp.setPostCode("410000");
            tmp.setMobile("15874031190");
            tmp.setTel("07334156202");
            tmp.setContryId(100023);
            tmp.setContryName("中国");
            tmp.setProvinceName("广州");
            tmp.setCityName("深圳");
            tmp.setAdress("福田");
            tmp.setIsDefault(Short.parseShort("0"));
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("lvAccountAddress", tmp);
    		lv.update(dto);
        }
	}

}
