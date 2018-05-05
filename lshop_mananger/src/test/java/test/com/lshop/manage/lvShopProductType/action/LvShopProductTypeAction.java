package test.com.lshop.manage.lvShopProductType.action;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

public class LvShopProductTypeAction {

	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testList() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testBefSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}



	@Test
	public void testView() {
		fail("Not yet implemented");
	}

	@Test
	public void testBefEdit() {
		fail("Not yet implemented");
	}

	@Test
	public void testEdit() {
		fail("Not yet implemented");
	}

	@Test
	public void testDel() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelList() {
		fail("Not yet implemented");
	}

}
