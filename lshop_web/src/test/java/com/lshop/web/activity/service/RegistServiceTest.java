package com.lshop.web.activity.service;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.test.BaseTest;

public class RegistServiceTest extends BaseTest{
	@Resource RegistService service;
	String userCode = "4D79V21Z01402909461453PV473HVJ7W";
	@Test
	public void testGetAllRegistActivitie() throws Exception {
		System.out.println(service.getAllRegistActivitie("tvpad"));
	}

	@Test
	public void testUserRegisted() throws Exception {
		service.userRegisted(userCode, null);
	}

}
