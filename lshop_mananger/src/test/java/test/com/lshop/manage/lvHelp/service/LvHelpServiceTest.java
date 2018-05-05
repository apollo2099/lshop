package test.com.lshop.manage.lvHelp.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvHelp;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvGroupBuy.service.LvGroupBuyService;
import com.lshop.manage.lvHelp.service.LvHelpService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvHelp.service.test.LvHelpServiceTest.java]  
 * @ClassName:    [LvHelpServiceTest]   
 * @Description:  [帮助管理-单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 上午11:23:21]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 上午11:23:21]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvHelpServiceTest {
	
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
		LvHelp lvHelp=new LvHelp();
		lvHelp.setHelpId(111);
		dto.put("model", lvHelp);
		LvHelpService lv= (LvHelpService) factory.getBean("LvHelpService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvHelp lvHelp=new LvHelp();
		lvHelp.setId(1);
		dto.put("model", lvHelp);
		LvHelpService lv= (LvHelpService) factory.getBean("LvHelpService");
		lv.get(dto);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvHelp lvHelp=new LvHelp();
		lvHelp.setId(18);
		dto.put("model", lvHelp);
		LvHelpService lv= (LvHelpService) factory.getBean("LvHelpService");
		lv.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvHelp lvHelp=new LvHelp();
		lvHelp.setHelpId(111);
		lvHelp.setName("junit test");
		lvHelp.setContent("test");
		lvHelp.setOrderValue(18);
		lvHelp.setUrl("www.tvpad.cn");
		lvHelp.setWebLanguage("tw");
		
		lvHelp.setCode(CodeUtils.getCode());
		lvHelp.setCreateTime(new Date());
		dto.put("model", lvHelp);
		LvHelpService lv= (LvHelpService) factory.getBean("LvHelpService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvHelp lvHelp=new LvHelp();
		lvHelp.setId(16);
		dto.put("model", lvHelp);
		LvHelpService lv= (LvHelpService) factory.getBean("LvHelpService");
		LvHelp tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setName("junit test update");
        	tmp.setContent("junit test update");
        	tmp.setOrderValue(18);
        	tmp.setUrl("www.tvpadtest.cn");
        	tmp.setWebLanguage("tw");
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

}
