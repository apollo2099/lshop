package com.lshop.manage.lvOrder.service;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.BaseSpringTestCase;
import com.lshop.manage.lvOrder.service.LvOrderActivityService;

public class LvOrderActivityServiceTest extends BaseSpringTestCase {
	
	@Resource
	private LvOrderActivityService lvOrderActivityService;
	
	@Test
	public void testFindOrderActivityByOrderId() {
		BaseDto dto=new BaseDto();
		dto.put("orderId", "B832014062711485888169");
		List list=lvOrderActivityService.findOrderActivityByOrderId(dto);
		System.out.println(list.size());
	}
}
