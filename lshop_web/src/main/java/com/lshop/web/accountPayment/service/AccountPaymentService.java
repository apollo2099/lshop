package com.lshop.web.accountPayment.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvAccountPayment;
import com.lshop.common.pojo.logic.LvPaymentStyle;

/**
 * 用户店铺支付方式
 * @author caicl
 *
 */
public interface AccountPaymentService {
	/**
	 * 增加或者修改用户店铺默认支付方式
	 * @param String userCode 用户号
	 * @param int payment 支付方式
	 * @param String storeFlag 店铺标识
	 * @return
	 * @throws ServiceException
	 */
	void MergePayment(Dto dto) throws ServiceException;
	/**
	 * 获取用户店铺默认支付方式
	 * @param userCode
	 * @param storeFlag
	 * @return
	 * @throws ServiceException
	 */
	Integer getUserPayment(String userCode, String storeFlag) throws ServiceException;
	/**
	 * 获得用户店铺默认支付方式
	 * @param String userCode 用户号
	 * @param String storeFlag 店铺标识
	 * @return
	 * @throws ServiceException
	 */
	LvPaymentStyle getUserPaystyle(Dto dto) throws ServiceException;
}
