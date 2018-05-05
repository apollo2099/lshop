package com.lshop.web.order.service;

import com.lshop.common.pojo.logic.LvOrderMac;

public interface OrderMacService {
	/**
	 * 
	 * @Method: saveOrderMac
	 * @Description: [保存订单mac关联信息]
	 * @Author: [liaoxj]
	 * @param orderMac
	 * @return Boolean
	 */
	public Boolean saveOrderMac(LvOrderMac orderMac);
	/**
	 * 
	 * @Method: findByMac
	 * @Description: [查询当前mac当天是否存在销售订单]
	 * @Author: [liaoxj]
	 * @param mac
	 *            Mac编码
	 * @return LvOrderMac
	 */
	public LvOrderMac findByMac(String mac);

	/**
	 * 
	 * @Method: findByOrderId
	 * @Description: [一句话描述该类的功能]
	 * @Author: [liaoxj]
	 * @param orderId
	 *            订单编号
	 * @return LvOrderMac
	 */
	public LvOrderMac findByOrderId(String orderId);

	/**
	 * 
	 * @Method: countByMac
	 * @Description: [根据mac统计mac下单情况]
	 * @Author: [liaoxj]
	 * @param mac
	 * @return int
	 */
	public int countByMac(String mac);
}
	

