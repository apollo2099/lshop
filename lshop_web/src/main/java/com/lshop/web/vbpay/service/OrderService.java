package com.lshop.web.vbpay.service;


import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvRecharge;


public interface OrderService {
	/**
	 * 修改订单状态
	 * @param dto
	 */
	public Boolean updateOrderStatus(Dto dto) throws ServiceException;
	/**
	 * 获取订单
	 */
	public LvRecharge getOrder(Dto dto)throws ServiceException;
	
}
