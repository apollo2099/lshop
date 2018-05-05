package com.lshop.manage.lvCoupon.service;

import static org.junit.Assert.*;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import com.lshop.common.BaseSpringTestCase;
import com.lshop.common.pojo.logic.LvCouponType;

public class CouponTaskServiceTest extends BaseSpringTestCase{
	
	@Resource
	private CouponTaskService couponTaskService;

	@Test
	public void testCreateBatchCoupon() {
		LvCouponType couponType=new LvCouponType();
		couponType.setCode("89854f928e2c4db2b207a3bf3d0ec51a");
		couponType.setModifyTime(new Date());
		couponType.setNoGrantNumber(1);
		//couponTaskService.createBatchCoupon(2, "d33447c0eba44572bdcc2f23a31f530a", couponType, Short.parseShort("2"));
		//couponTaskService.createBatchCoupon(1, "d33447c0eba44572bdcc2f23a31f530a", couponType, Short.parseShort("2"));
		System.out.println("Test");
//		couponTaskService.createBatchCoupon(1000, "d33447c0eba44572bdcc2f23a31f530a", couponType, Short.parseShort("2"));
//		couponTaskService.createBatchCoupon(1000, "d33447c0eba44572bdcc2f23a31f530a", couponType, Short.parseShort("2"));
//		couponTaskService.createBatchCoupon(1000, "d33447c0eba44572bdcc2f23a31f530a", couponType, Short.parseShort("2"));
	}

	@Test
	public void testCreateBatchCouponIntegerStringLvCouponTypeShortString() {
		fail("Not yet implemented");
	}

}
