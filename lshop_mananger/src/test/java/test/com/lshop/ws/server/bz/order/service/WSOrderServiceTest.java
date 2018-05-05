package test.com.lshop.ws.server.bz.order.service;

import java.util.List;

import com.lshop.common.util.DateUtils;

public class WSOrderServiceTest {

	public static void main(String[] args) {
		System.out.println(DateUtils.convertToDateTime("2013-3-8"));
		WSOrderServiceService ssl=new WSOrderServiceService();
		WSOrderService service1 =ssl.getWSOrderServicePort();
		LvOrderDtoResposne lv=service1.synAuditOrderToBZ("2013-3-8 00:00:00", "2013-3-25 00:00:00");
		List<LvOrderDto> list=lv.getOrderList();
		for (LvOrderDto lvOrderDto : list) {
			System.out.println(lvOrderDto.getOid());
		}
		
	}

}
