package com.lshop.web.coupon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.gv.test.base.BaseSpringTestCase;
import com.lshop.common.coupon.vo.CustomerCoupon;
import com.lshop.common.coupon.vo.CustomerCouponEditCheck;
import com.lshop.common.coupon.vo.CustomerCouponQuery;
import com.lshop.common.coupon.vo.CustomerCouponSubmitCheck;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.web.coupon.service.CouponService;

public class CouponServiceImplTest extends BaseSpringTestCase {
	@Resource
	private CouponService couponService;

	@Test
	public void testGetCustomerCoupon() throws Exception {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("22e6447c7bfb4a729eaa0c099d56401d", 3);
		map.put("270e9cada488419da5286b3f54b0328b", 4);
		CustomerCouponQuery customerCouponQuery = new CustomerCouponQuery("78S7419Y414042806339209ENZ3NDO30", "tvpadcn", map);

		List<CustomerCoupon> customerCoupons = couponService.getCustomerCoupon(customerCouponQuery);
		if (CollectionUtils.isEmpty(customerCoupons)) {
			fail("无法获取客户优惠券");
		}
	}

	@Test
	public void testCheckCustomerCoupon() throws Exception {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("7a28c1fcc3fc47bea6c65417b0829cf2", 2);
		map.put("270e9cada488419da5286b3f54b0328b", 4);

		CustomerCouponEditCheck customerCouponEditCheck = new CustomerCouponEditCheck("140474041725", "78S7419Y414042806339209ENZ3NDO30", "tvpadcn", map);

		ResultMsg resultMsg = couponService.checkCustomerCoupon(customerCouponEditCheck);
		if (!resultMsg.isSuccess()) {
			fail("无效客户优惠券");
		}
	}

	@Test
	public void testUseCustomerCoupon() throws Exception {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("7a28c1fcc3fc47bea6c65417b0829cf2", 3);
		map.put("270e9cada488419da5286b3f54b0328b", 4);

		CustomerCouponSubmitCheck customerCouponSubmitCheck = new CustomerCouponSubmitCheck("9133778626b049c99f5eb0c9c79efd94", "78S7419Y414042806339209ENZ3NDO30", "tvpadcn", map);

		ResultMsg resultMsg = couponService.useCustomerCoupon("TO201406261113", customerCouponSubmitCheck);
		if (!resultMsg.isSuccess()) {
			fail("无法使用优惠券");
		}
	}
	@Test
	public void testGetDetail() throws Exception {
		System.out.println(couponService.getCouponDetail("c1c41638a35d4535ba76f62e3c3e82bd").toString());
	}
	@Test
	public void testObtainCouponByLottery(){
		couponService.obtainCouponByLottery("140069101718");
	}
}
