package com.lshop.manage.LvShopCart.service;

import java.text.ParseException;
import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.pojo.logic.LvPaymentStyle;

/**
 * 购物车模块
 * @author zhengxue
 * @since 2.0 2012-07-30
 *
 */
public interface LvShopCartService {
	
	/**
	 * 支付时更改剩余量或已购买量
	 * 判断产品类型，如果是团购产品则更改已购买人数；如果是限量产品，则更改剩余产品量
	 * 传入参数订单号oid
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto changeCount(Dto dto) throws ServiceException;
}
