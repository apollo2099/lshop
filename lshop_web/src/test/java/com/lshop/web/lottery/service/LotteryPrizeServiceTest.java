package com.lshop.web.lottery.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.test.BaseTest;

public class LotteryPrizeServiceTest extends BaseTest{
	@Resource LotteryPrizeService service;
	String userCode = "X7DV61H7I14029775045242107U3CRYS", prizeCode = "df12b308c9564cafbada587bf98be7b9", addrCode = "11d0896c7eb549568a43928c1f3dd3ad";
	@Test
	public void testFindPrizePage() {
		System.out.println(service.findPrizePage(userCode, 1, 10, null).getList());
	}

	@Test
	public void testAddPrize() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaterialDeliver() {
		System.out.println(service.getMaterialDeliver(prizeCode));
	}

	@Test
	public void testAddDeliver() {
		service.addDeliver(addrCode, userCode, prizeCode);
	}

}
