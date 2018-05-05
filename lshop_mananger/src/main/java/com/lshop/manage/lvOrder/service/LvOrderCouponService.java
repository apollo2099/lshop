package com.lshop.manage.lvOrder.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvOrderCoupon;
import com.lshop.ws.server.popularize.order.bean.PCouponDto;

public interface LvOrderCouponService {
	public List<LvOrderCoupon> findDetailsByCouponCode(Dto dto);
	public List<LvOrderCoupon> findDetailsByOrderId(Dto dto);
	/**
	 * 获取优惠券
	 * 
	 * @param orderNo
	 *            订单号
	 * @return
	 * @throws ServiceException
	 */
	public List<PCouponDto> findByOrderNo(String orderNo) throws ServiceException;

}
