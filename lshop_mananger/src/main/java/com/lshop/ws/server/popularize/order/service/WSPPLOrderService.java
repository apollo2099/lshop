package com.lshop.ws.server.popularize.order.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.lshop.ws.server.popularize.order.bean.PCouponDtoPageResposne;
import com.lshop.ws.server.popularize.order.bean.PCouponDtoResposne;
import com.lshop.ws.server.popularize.order.bean.POrderDtoResposne;




@WebService(name = "WSPPLOrderService")
@SOAPBinding(style=Style.RPC)
public interface WSPPLOrderService {
	public POrderDtoResposne getOrderDetail(@WebParam(name = "orderListNo") String orderListNo);
	
	public PCouponDtoResposne checkCoupon(@WebParam(name = "couponCodes") String couponCodes);
	
	public PCouponDtoPageResposne queryCoupon(@WebParam(name = "couponName") String couponName,@WebParam(name = "pageNum") String pageNum,@WebParam(name = "numPerPage") String numPerPage);
	
	public PCouponDtoResposne getCoupon(@WebParam(name = "couponTypeCode") String couponTypeCode,@WebParam(name = "couponQuantity") String couponQuantity);

}
