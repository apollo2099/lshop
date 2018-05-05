package com.lshop.ws.server.bz.order.service;

import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.lshop.ws.server.bz.order.bean.LvOrderDtoResposne;


@WebService(name = "WSOrderService")
@SOAPBinding(style=Style.RPC)
public interface WSOrderService  {

	/**
	 * 同步已经审核的订单到商务接口定义
	 * @Method: synAuditOrderToBZ 
	 * @Description:  
	 * @param startTime  审核开始时间
	 * @param endTime    审核结算时间
	 * @return OrderList
	 */
	public LvOrderDtoResposne synAuditOrderToBZ(
			@WebParam(name = "startTime") String startTime,
			@WebParam(name = "endTime") String endTime);
	
}
