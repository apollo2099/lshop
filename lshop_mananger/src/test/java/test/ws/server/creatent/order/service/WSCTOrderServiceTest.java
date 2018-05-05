package test.ws.server.creatent.order.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.lshop.common.BaseSpringTestCase;
import com.lshop.manage.lvActivity.service.LvAccountPrizeService;
import com.lshop.ws.server.creatent.order.service.WSCTOrderService;

public class WSCTOrderServiceTest extends BaseSpringTestCase{

	@Resource
	private  WSCTOrderService wsCTOrderService;

	@Test
	public void testSynSendOrderToCreatent() {
		wsCTOrderService.synLogisticsFromCreatent("C012014100916412597274", "DHL", "DHL", "888899999", 1, "2014-10-28 13:55:22");
	}
	
	
	@Test
	public void testSynAuditOrderToCreatent(){
		wsCTOrderService.synAuditOrderToCreatent("2014-11-1 00:00:00", "2014-12-30 00:00:00");
	}

}
