package com.lshop.web.accountPayment.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.test.BaseTest;

public class AccountPaymentServiceTest extends BaseTest{
	@Resource AccountPaymentService service;
	String userCode = "3e87613c83f74b1987a1612f8153ed2f";
	String storeFlag = "bmcn";
	@Test
	public void testMergePayment() {
		Dto dto = new BaseDto();
		dto.put("userCode", userCode);
		dto.put("storeFlag", storeFlag);
		dto.put("payment", 12);
		service.MergePayment(dto);
	}

	@Test
	public void testGetUserPayment() {
		fail("Not yet implemented");
	}

}
