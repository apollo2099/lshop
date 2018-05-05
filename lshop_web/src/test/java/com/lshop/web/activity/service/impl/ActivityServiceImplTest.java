package com.lshop.web.activity.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.test.base.BaseSpringTestCase;
import com.lshop.common.activity.vo.Activity;
import com.lshop.common.activity.vo.ActivityLimitOrder;
import com.lshop.common.activity.vo.ActivityLimitTime;
import com.lshop.common.activity.vo.ActivityLimited;
import com.lshop.common.activity.vo.GeneralActivity;
import com.lshop.common.activity.vo.OrderActivity;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.web.activity.service.ActivityService;

public class ActivityServiceImplTest extends BaseSpringTestCase {

	@Resource
	private ActivityService activityService;

	@Test
	public void testGetActivityVoByProductNo() throws Exception {

		// 有效商品编码并存在活动
		Activity activity01 = activityService.getActivityVoByProductNo("720ed6ae06304e4daaa1ce6ae2885af2");

		if (null == activity01) {
			fail("商品无法获取活动信息");
		}

		// 有效商品编码并不存在活动
		Activity activity02 = activityService.getActivityVoByProductNo("10210544");

		if (null != activity02) {
			fail("非参与活动商品存在活动信息");
		}

		// 无效商品编码
		Activity activity03 = activityService.getActivityVoByProductNo("132");

		if (null != activity03) {
			fail("无效商品存在活动信息");
		}

		// 空商品编码
		Activity activity04 = activityService.getActivityVoByProductNo(null);

		if (null != activity04) {
			fail("空商品编码存在活动信息");
		}

	}

	@Test
	public void testGetActivityLimitTimeByCode() throws Exception {

		// 有效活动号
		ActivityLimitTime activityLimitTime01 = activityService.getActivityLimitTimeByCode("xs001");
		if (null == activityLimitTime01) {
			fail("无法获取活动");
		}

		// 无效活动号
		ActivityLimitTime activityLimitTime02 = activityService.getActivityLimitTimeByCode("021");
		if (null != activityLimitTime02) {
			fail("无效活动号存在活动");
		}

		// 空活动号
		ActivityLimitTime activityLimitTime03 = activityService.getActivityLimitTimeByCode(null);
		if (null != activityLimitTime03) {
			fail("空活动号存在活动");
		}
	}

	@Test
	public void testGetActivityLimitedByCode() throws Exception {

		// 有效活动号
		ActivityLimited activityLimited01 = activityService.getActivityLimitedByCode("xl001");
		if (null == activityLimited01) {
			fail("无法获取活动");
		}

		// 无效活动号
		ActivityLimited activityLimited02 = activityService.getActivityLimitedByCode("01");
		if (null != activityLimited02) {
			fail("无效活动号存在活动");
		}

		// 空活动号
		ActivityLimited activityLimited03 = activityService.getActivityLimitedByCode(null);
		if (null != activityLimited03) {
			fail("空活动号存在活动");
		}
	}

	@Test
	public void testGetActivityLimitOrder() throws Exception {

		ActivityLimitOrder activityLimitOrders01 = activityService.getActivityLimitOrder("bscn", Float.valueOf(300));

		if (null == activityLimitOrders01) {
			fail("无法获取赠品信息");
		}
	}

	@Test
	public void testGetGeneralActivityByProductNo() throws Exception {

		// 有效商品编码并存在活动
		GeneralActivity activity01 = activityService.getGeneralActivityByProductNo("720ed6ae06304e4daaa1ce6ae2885af2");

		if (null == activity01) {
			fail("商品无法获取活动信息");
		}

		// 有效商品编码并不存在活动
		GeneralActivity activity02 = activityService.getGeneralActivityByProductNo("10210544");

		if (null != activity02) {
			fail("非参与活动商品存在活动信息");
		}

		// 无效商品编码
		GeneralActivity activity03 = activityService.getGeneralActivityByProductNo("132");

		if (null != activity03) {
			fail("无效商品存在活动信息");
		}

		// 空商品编码
		GeneralActivity activity04 = activityService.getGeneralActivityByProductNo(null);

		if (null != activity04) {
			fail("空商品编码存在活动信息");
		}

	}

	@Test
	public void testGetCouponByActivityLink() throws Exception {

		ResultMsg resultMsg = activityService.getCouponByActivityLink("dffe92012ba44218bff594d996e49837", "X7DV61H7I14029775045242107U3CRYS", "www");

		if (!resultMsg.isSuccess()) {
			fail("领券失败");
		}
	}

	@Test
	public void testCreateOrderActivity() throws Exception {
		Set<String> productSet = new HashSet<String>();
		productSet.add("7a28c1fcc3fc47bea6c65417b0829cf2");

		OrderActivity orderActivity = new OrderActivity("TO0001", "bscn", Float.valueOf(6000), productSet);

		ResultMsg resultMsg = activityService.createOrderActivity(orderActivity);

		if (!resultMsg.isSuccess()) {
			fail("生成订单活动关系失败");
		}
	}

	@Test
	public void testFinishOrderActivity() throws Exception {

		Map<String, Integer> productQuantity = new HashMap<String, Integer>();
		productQuantity.put("720ed6ae06304e4daaa1ce6ae2885af2", 10);

		ResultMsg resultMsg = activityService.finishOrderActivity("TO0001", "4D79V21Z01402909461453PV473HVJ7W", productQuantity);

		if (!resultMsg.isSuccess()) {
			fail("完成订单相关活动失败");
		}
	}

	@Test
	public void testIsbelowCouponLimit() throws Exception {

		activityService.isbelowCouponLimit("ac001", "4D79V21Z01402909461453PV473HVJ7W", 90);
	}

	@Test
	public void testInitActivityUserCache() throws Exception {

		activityService.initActivityUserCache();
	}
	@Test
	public void testGetActivityLimitOrderLottery() throws Exception {
		System.out.println(activityService.getActivityLimitOrderLottery("tvpadcn", 300f));
	}
	@Test
	public void testGetActivityLimitOrderByCode() throws Exception {
		System.out.println(activityService.getActivityLimitOrderByCode("4b2decc7dad04292b443908f4db06d0d"));
	}
}
