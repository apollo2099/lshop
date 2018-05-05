package com.lshop.ws.server.creatent.order.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.lshop.ws.server.bz.order.bean.LvOrderDtoResposne;
import com.lshop.ws.server.creatent.order.bean.Result;

@WebService(name = "WSCTOrderService")
@SOAPBinding(style=Style.RPC)
public interface WSCTOrderService {
	/**
	 * 
	 * @Method: synLogisticsFromCreatent 
	 * @Description:  [同步发货订单物流信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-10-28 上午11:48:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-10-28 上午11:48:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderNum   订单号
	 * @param expressCompany 物流公司key
	 * @param expressName    物流公司名称
	 * @param expressNum     物流单号
	 * @param status         订单状态
	 * @param shipTime       发货时间
	 * @return Result
	 */
	public Result synLogisticsFromCreatent(
			@WebParam(name = "orderNum") String orderNum,
			@WebParam(name = "expressCompany") String expressCompany,
			@WebParam(name = "expressName") String expressName,
			@WebParam(name = "expressNum") String expressNum,
			@WebParam(name = "status") Integer status,
			@WebParam(name = "shipTime") String shipTime);
	
	
	
	/**
	 * 同步已经审核的订单到启创接口定义
	 * @Method: synAuditOrderToBZ 
	 * @Description:  
	 * @param startTime  审核开始时间
	 * @param endTime    审核结算时间
	 * @return OrderList
	 */
	public Result synAuditOrderToCreatent(
			@WebParam(name = "startTime") String startTime,
			@WebParam(name = "endTime") String endTime);
}
