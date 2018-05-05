package com.lshop.common.util;

import org.junit.Test;
import com.gv.test.BaseTest;

public class I18nCache2Test extends BaseTest{


	@Test
	public void testGetValue() {
		String s = I18nCache2.getValue("shopCart.empty", "bmcn");
		System.out.println(s);
	}

}
