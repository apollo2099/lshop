package com.lshop.manage.lvTplModel.service;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import com.lshop.manage.util.BeanFactoryUtil;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvTplModel;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvTplModel.service.LvTplModelService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvTplModel.service.test.LvTplModelServiceTest.java]  
 * @ClassName:    [LvTplModelServiceTest]   
 * @Description:  [店铺管理-模版信息管理-单元测试service]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-14 下午01:58:00]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-14 下午01:58:00]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvTplModelServiceTest {
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
		LvTplModel lvTplModel=new LvTplModel();
		lvTplModel.setModelName("模板2号");
		dto.put("model", lvTplModel);
		LvTplModelService lv= (LvTplModelService) factory.getBean("LvTplModelService");
		lv.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvTplModel lvTplModel=new LvTplModel();
		lvTplModel.setId(9);
		dto.put("model", lvTplModel);
		LvTplModelService lv= (LvTplModelService) factory.getBean("LvTplModelService");
		lv.get(dto);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvTplModel lvTplModel=new LvTplModel();
		lvTplModel.setId(31);
		dto.put("model", lvTplModel);
		LvTplModelService lv= (LvTplModelService) factory.getBean("LvTplModelService");
		lv.del(dto);
	}



	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvTplModel lvTplModel=new LvTplModel();
		lvTplModel.setModelName("模板3号");
		lvTplModel.setStoreFlag("cn");
		lvTplModel.setIsDefault(Short.parseShort("0"));
		lvTplModel.setCode(CodeUtils.getCode());
		lvTplModel.setCreateTime(new Date());
		dto.put("model", lvTplModel);
		LvTplModelService lv= (LvTplModelService) factory.getBean("LvTplModelService");
		lv.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvTplModel lvTplModel=new LvTplModel();
		lvTplModel.setId(30);
		dto.put("model", lvTplModel);
		LvTplModelService lv= (LvTplModelService) factory.getBean("LvTplModelService");
		LvTplModel tmp=lv.get(dto);
        if(tmp!=null){
        	tmp.setModelName("模板2号@");
        	tmp.setIsDefault(Short.parseShort("0"));
        	tmp.setModifyTime(new Date());
        	tmp.setModifyUserId(2);
        	tmp.setModifyUserName("admin@");
    		dto.put("model", tmp);
    		lv.update(dto);
        }
	}

	@Test
	public void testGetDefaultTplModel() {
		BaseDto dto=new BaseDto();
		LvTplModel lvTplModel=new LvTplModel();
		lvTplModel.setStoreFlag("cn");
		dto.put("model", lvTplModel);
		LvTplModelService lv= (LvTplModelService) factory.getBean("LvTplModelService");
		lv.getDefaultTplModel(dto);
	}

	@Test
	public void testUpdateDefaultSet() {
		BaseDto dto=new BaseDto();
		LvTplModel lvTplModel=new LvTplModel();
		lvTplModel.setStoreFlag("cn");
		lvTplModel.setId(31);
		
		dto.put("model", lvTplModel);
		LvTplModelService lv= (LvTplModelService) factory.getBean("LvTplModelService");
		lv.updateDefaultSet(dto);
	}

}
