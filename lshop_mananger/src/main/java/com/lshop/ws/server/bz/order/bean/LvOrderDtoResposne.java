package com.lshop.ws.server.bz.order.bean;
import java.util.ArrayList;
import java.util.List;

public class LvOrderDtoResposne extends BasePojo{

	protected List<LvOrderDto> orderList=new ArrayList<LvOrderDto>();

	public List<LvOrderDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<LvOrderDto> orderList) {
		this.orderList = orderList;
	}
}
