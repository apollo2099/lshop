package com.lshop.ws.web.trheeOrder;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.test.base.BaseSpringTestCase;
import com.lshop.ws.web.creatent.order.WSThreeOrderService;

public class WSThreeOrderServiceTest extends BaseSpringTestCase {
	@Resource
	private WSThreeOrderService wsThreeOrderService;

	@Test
	public void updateOrderStatusTest() {
		wsThreeOrderService.updateOrderStatus("B012014102910545987837", 2);
	}

}
