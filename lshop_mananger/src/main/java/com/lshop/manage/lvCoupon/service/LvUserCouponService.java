package com.lshop.manage.lvCoupon.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;



public interface LvUserCouponService {
	/**
	 * 获得单独的实体信息
	 */
	public Boolean isExistCoupon(Dto dto) throws ServiceException;
	/**
	 * 更新优惠码关联信息
	 */
	public void updateCoupon(Dto dto)throws ServiceException;
}
