package com.lshop.web.lottery.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.test.BaseTest;
import com.lshop.web.lottery.bo.LotteryDetail;
import com.lshop.web.lottery.bo.LotteryRecord;
import com.lshop.web.lottery.bo.LotteryResult;

public class LotteryServiceTest extends BaseTest{
	String userCode = "X7DV61H7I14029775045242107U3CRYS", userName = "gvxieco", lotteryCode = "4270f6fd6ded46488e70fc3d899f93ed";
	@Resource LotteryService service;
	@Test
	public void testGetLotteryDetail() {
		System.out.println(service.getLotteryDetail(lotteryCode));
	}

	@Test
	public void testLotteryDraw() throws Exception {
		String res = "";
		for (int i = 0; i < 5; i++) {
			LotteryResult record =  service.lotteryDraw(userCode, lotteryCode, userName);
			if (null != record) {
				res += record.toString();
			} else {
				break;
			}
		}
		System.out.println(res);
	}

	@Test
	public void testAddLotteryRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLatestRecord() {
		System.out.println(service.getLatestRecord(lotteryCode));
	}

}
