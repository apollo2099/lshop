package com.lshop.web.lottery.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.gv.test.BaseTest;

public class LotteryChanceServiceTest extends BaseTest{
	@Resource LotteryChanceService servie;
	String userCode = "X7DV61H7I14029775045242107U3CRYS", lotteryCode = "60f3154f26dc431394eaa4d4a81e418c";
	@Test
	public void testGetChance() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddChance() {
		int add = 5;
		int cur = servie.getChanceNum(userCode, lotteryCode);
		servie.addChance(lotteryCode, add, userCode);
		assertEquals(cur+add, servie.getChanceNum(userCode, lotteryCode));
	}

	@Test
	public void testUseChance() {
		servie.useChance(userCode, lotteryCode);
	}

	@Test
	public void testGetChanceNum() {
		System.out.println(servie.getChanceNum(userCode, lotteryCode));
	}

}
