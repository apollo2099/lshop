package com.lshop.web.pay.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvOrder;

public interface OrderService {
	/**
	 * 修改订单状态
	 * @param dto
	 */
	public Boolean updateOrderStatus(Dto dto) throws ServiceException;
	/**
	 * 获取订单
	 */
	public LvOrder getOrder(Dto dto)throws ServiceException;
	/**
	 * 订单支付成功后回调
	 * @param dto oid 订单号
	 * @throws Exception
	 */
	public void orderPayCallBack(Dto dto) throws Exception;
}
