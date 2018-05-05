package test.ws.server.popularize.order.service;

import java.util.List;

import com.lshop.common.util.DateUtils;

public class WSPPLOrderServiceTest {

	public static void main(String[] args) {
		System.out.println(DateUtils.convertToDateTime("2013-3-8"));
		WSPPLOrderServiceService ssl=new WSPPLOrderServiceService();
		WSPPLOrderService service =ssl.getWSPPLOrderServicePort();
		POrderDtoResposne lv=service.getOrderDetail("C872014040216450290145");
		List<POrderDto> list=lv.getPOrderList();
		for (POrderDto orderDto : list) {
			System.out.println(orderDto.getOrderNo()+"=>"+orderDto.getCreateTime());
		}
		
	}

}
