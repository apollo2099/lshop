package com.lshop.web.pay.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.test.BaseTest;

public class OrderServiceTest extends BaseTest{
	@Resource OrderService service;
	@Test
	public void testUpdateOrderStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrderPayCallBack() throws Exception {
		String oid = "C832014091816124833793", uid = "38D1Q3VJV1410851036301G67FR63EM6";
		Dto dto = new BaseDto();
		dto.put("oid", oid);
		dto.put("uid", uid);
		service.orderPayCallBack(dto);
	}

}
