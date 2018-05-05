package com.lshop.ws.server.popularize.order.bean;

import java.util.ArrayList;
import java.util.List;

public class POrderDtoResposne extends BasePojo{
	List<POrderDto> POrderList=new ArrayList<POrderDto>();

	public List<POrderDto> getPOrderList() {
		return POrderList;
	}

	public void setPOrderList(List<POrderDto> pOrderList) {
		POrderList = pOrderList;
	}


	
}
