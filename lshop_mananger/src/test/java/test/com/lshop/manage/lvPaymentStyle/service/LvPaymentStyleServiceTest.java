package test.com.lshop.manage.lvPaymentStyle.service;

import static org.junit.Assert.*;

import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvPaymentStyle.service.LvPaymentStyleService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvPaymentStyle.service.test.LvPaymentStyleServiceTest.java]  
 * @ClassName:    [LvPaymentStyleServiceTest]   
 * @Description:  [支付方式管理--单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 下午03:37:09]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 下午03:37:09]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvPaymentStyleServiceTest {
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
		LvPaymentStyle lvPaymentStyle=new LvPaymentStyle();
		lvPaymentStyle.setPayName("上门安装");
		dto.put("model", lvPaymentStyle);
		LvPaymentStyleService lv= (LvPaymentStyleService) factory.getBean("LvPaymentStyleService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvPaymentStyle lvPaymentStyle=new LvPaymentStyle();
		lvPaymentStyle.setId(1);
		dto.put("model", lvPaymentStyle);
		LvPaymentStyleService lv= (LvPaymentStyleService) factory.getBean("LvPaymentStyleService");
		lv.get(dto);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvPaymentStyle lvPaymentStyle=new LvPaymentStyle();
		lvPaymentStyle.setId(26);
		dto.put("model", lvPaymentStyle);
		LvPaymentStyleService lv= (LvPaymentStyleService) factory.getBean("LvPaymentStyleService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvPaymentStyle lvPaymentStyle=new LvPaymentStyle();
		lvPaymentStyle.setPayName("pay style test");
		lvPaymentStyle.setPayValue(Short.parseShort("0"));
		lvPaymentStyle.setIsActivity(0);
		lvPaymentStyle.setOrderValue(9);
		lvPaymentStyle.setStoreFlag("en");
		
		lvPaymentStyle.setCode(CodeUtils.getCode());
		lvPaymentStyle.setCreateTime(new Date());
		dto.put("model", lvPaymentStyle);
		LvPaymentStyleService lv= (LvPaymentStyleService) factory.getBean("LvPaymentStyleService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvPaymentStyle lvPaymentStyle=new LvPaymentStyle();
		lvPaymentStyle.setId(13);
		dto.put("model", lvPaymentStyle);
		LvPaymentStyleService lv= (LvPaymentStyleService) factory.getBean("LvPaymentStyleService");
		LvPaymentStyle tmp=lv.get(dto);
        if(tmp!=null){
    		tmp.setIsActivity(1);
    		tmp.setOrderValue(9);
    		tmp.setStoreFlag("en");
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
