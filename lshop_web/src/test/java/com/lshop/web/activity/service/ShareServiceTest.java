package com.lshop.web.activity.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.gv.test.BaseTest;

public class ShareServiceTest extends BaseTest{
	@Resource ShareService service;
	String lotteryCode = "480f7970e8b04d709797097a4ccdbde5";
	String userCode = "X7DV61H7I14029775045242107U3CRYS", shareCode = "20010eb0442a4f9ab36ef4c178b6ebab";
	@Test
	public void testGetFirstLotteryShare() throws Exception {
		System.out.println(service.getFirstLotteryShare(lotteryCode));
	}

	@Test
	@Rollback(false)
	public void testShareSuccess() throws Exception {
		service.shareSuccess(userCode, shareCode);
	}

	@Test
	public void testGetShareDetail() throws Exception{
		System.out.println(service.getShareDetail(shareCode));
	}
}
