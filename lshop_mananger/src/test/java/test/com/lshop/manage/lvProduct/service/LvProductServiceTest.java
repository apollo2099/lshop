package test.com.lshop.manage.lvProduct.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvProduct.service.LvProductService;
import com.lshop.manage.lvProductComment.service.LvProductCommentService;
import com.lshop.manage.lvProductLadder.service.LvProductLadderService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProduct.service.test.LvProductServiceTest.java]  
 * @ClassName:    [LvProductServiceTest]   
 * @Description:  [产品信息管理-单元测试]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-10 下午03:00:57]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-10 下午03:00:57]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public class LvProductServiceTest {
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
		LvProduct lvProduct=new LvProduct();
		lvProduct.setId(1);
		lvProduct.setIsSupport(1);
		dto.put("lvProduct", lvProduct);
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.getList(dto);
	}

	@Test
	public void testProductList() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.productList(dto);
	}


	
	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
		dto.put("ids", "7");
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.delete(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvProduct lvProduct=new LvProduct();
		lvProduct.setId(11);
		dto.put("lvProduct", lvProduct);
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.get(dto);
	}

	@Test
	public void testSave() {
		//套餐产品新增
		BaseDto dto=new BaseDto();
		
		LvProduct lvProduct=new LvProduct();
		lvProduct.setPcode("132436875197272UUU67TTTT@");
		lvProduct.setPimage("");
		lvProduct.setPmodel("M121");
		lvProduct.setPriceRmb(1100f);
		lvProduct.setPriceUsd(148f);
		lvProduct.setPtype("CD1341297270220");
		lvProduct.setIsActivity(0);
		lvProduct.setIsCommend(0);
		lvProduct.setIsLadder(0);
		lvProduct.setIsPreferences(0);
		lvProduct.setIsSetMeal(1);
		lvProduct.setIsSupport(0);
		lvProduct.setProductName("product test name package!");
		lvProduct.setStatus(1);
		lvProduct.setCode(CodeUtils.getCode());
		lvProduct.setCreateTime(new Date());
		
		List packageList=new ArrayList();
		LvProductPackage lvProductPackage=new LvProductPackage();
		lvProductPackage.setPackageNum(lvProduct.getCode());
		lvProductPackage.setProductCode("CD1341308735749");
		lvProductPackage.setPnum(10);
		lvProductPackage.setPriceRmb(2290f);
		lvProductPackage.setPriceUsd(450f);
		lvProductPackage.setPcode("132436875197272UUU67TTTT");
		packageList.add(lvProductPackage);
		
		
		lvProduct.setPackageList(packageList);
		dto.put("lvProduct", lvProduct);
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.save(dto);
	}

	//非套餐产品信息修改
	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvProduct product=new LvProduct();
		product.setId(7);
		dto.put("lvProduct", product);
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		LvProduct lvProduct=lv.get(dto);
        if(lvProduct!=null){
    		lvProduct.setPackageList(null);
    		lvProduct.setPcode("132436875197272UUU67TTTT@");
    		lvProduct.setPimage("");
    		lvProduct.setPmodel("M121");
    		lvProduct.setPriceRmb(1100f);
    		lvProduct.setPriceUsd(148f);
    		lvProduct.setPtype("CD1341297270220");
    		lvProduct.setIsActivity(0);
    		lvProduct.setIsCommend(0);
    		lvProduct.setIsLadder(0);
    		lvProduct.setIsPreferences(0);
    		lvProduct.setIsSetMeal(0);
    		lvProduct.setIsSupport(0);
    		lvProduct.setStatus(1);
    		lvProduct.setProductName("product test name for update!");
    		lvProduct.setCode(CodeUtils.getCode());
    		lvProduct.setCreateTime(new Date());
    		dto.put("lvProduct", lvProduct);
    		lv.update(dto);
        }
	}

	//查询所有产品信息列表包括套餐非套餐的
	@Test
	public void testGetAll() {
		BaseDto dto=new BaseDto();
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.getAll(dto);
	}

	//查询所有的非套餐产品信息
	@Test
	public void testGetAllProduct() {
		BaseDto dto=new BaseDto();
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.getAllProduct(dto);
	}

	//查询所有的套餐信息
	@Test
	public void testGetAllMealProduct() {
		BaseDto dto=new BaseDto();
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.getAllMealProduct(dto);
	}

	//产品上下架
	@Test
	public void testUpdateSupport() {
		BaseDto dto=new BaseDto();
		LvProduct lvProduct=new LvProduct();
		lvProduct.setCode("CD1341308735749");
		lvProduct.setIsSupport(1);
		dto.put("lvProduct", lvProduct);
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.updateSupport(dto);
	}

	//根据产品code查询产品信息
	@Test
	public void testGetProduct() {
		BaseDto dto=new BaseDto();
		LvProduct lvProduct=new LvProduct();
		lvProduct.setCode("CD1341308735749");
		dto.put("lvProduct", lvProduct);
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.getProduct(dto);
	}
	
	@Test
	public void testGetShopProductList(){
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvProduct lvProduct=new LvProduct();
		lvProduct.setProductName("TVpad M121型網路智能機頂盒");
		dto.put("lvProduct", lvProduct);
		dto.put("flag", "tvpad");
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		Pagination pageTmp=lv.getShopProductList(dto);
		assertNotNull(pageTmp.getList());
	}
	
	@Test
	public void testGetProductImage(){
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		LvProduct lvProduct=new LvProduct();
		LvProductImage lvProductImage=new LvProductImage();
		page.setPageNum(1);
		page.setNumPerPage(10);
		lvProduct.setCode("CD1341308735749");
		
		dto.put("page",page);
		dto.put("lvProduct",lvProduct);
		dto.put("lvProductImage",lvProductImage);
		dto.put("flag", "tvpad");
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		Pagination pageTmp= lv.getProductImage(dto);
		assertNotNull(pageTmp.getList());
	}
	@Test
	public void testGetProductProperty(){
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		LvProduct lvProduct=new LvProduct();
		LvProductImage lvProductImage=new LvProductImage();
		page.setPageNum(1);
		page.setNumPerPage(10);
		lvProduct.setCode("CD1341308735749");
		
		dto.put("page",page);
		dto.put("lvProduct",lvProduct);
		dto.put("lvProductImage",lvProductImage);
		dto.put("flag", "tvpad");
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		Pagination pageTmp= lv.getProductProperty(dto);
		assertNotNull(pageTmp.getList());
	}
	@Test
	public void testUpdateProduct(){
		
	}
	@Test
	public void testUpdateType(){
		BaseDto dto=new BaseDto();
		LvProduct lvProduct=new LvProduct();
		lvProduct.setShopProductType("17075e907eb14af883ea87021b111111");
		lvProduct.setModifyTime(new Date());
		lvProduct.setModifyUserId(2);
		lvProduct.setModifyUserName("admin_junit");
		dto.put("lvProduct",lvProduct);
		dto.put("ids", "28");
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.updateType(dto);
	}
	@Test
	public void testUpdateUnSupport(){
		BaseDto dto=new BaseDto();
		LvProduct lvProduct=new LvProduct();
		lvProduct.setUnsupportRemark("不卖了");
		lvProduct.setModifyTime(new Date());
		lvProduct.setModifyUserId(2);
		lvProduct.setModifyUserName("admin_junit");
		dto.put("lvProduct",lvProduct);
		dto.put("ids", "28");
		LvProductService lv= (LvProductService) factory.getBean("LvProductService");
		lv.updateUnSupport(dto);
		
	}

}
