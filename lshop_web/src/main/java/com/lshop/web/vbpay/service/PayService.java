package com.lshop.web.vbpay.service;


import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvPaymentStyle;


public interface PayService {

	/**
	 * 添加支付日志
	 * @param dto
	 */
     public void	addPayLog(Dto dto) throws ServiceException;
     
     /**
 	 * 获取支付方式
 	 */
     public LvPaymentStyle getLvPaymentStyle(Dto dto) throws ServiceException;
}
