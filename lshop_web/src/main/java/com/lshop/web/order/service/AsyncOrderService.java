/**
 * 
 */
package com.lshop.web.order.service;

import com.lshop.web.order.vo.OrderCahce;

/**
 * 从缓存同步订单到数据库
 * @author caicl
 *
 */
public interface AsyncOrderService {
	/**
	 * 异步同步订单
	 * @param orderNo
	 */
	void asyncOrder(OrderCahce orderCahce);
	/**
	 * 定时检查没有同步成功的订单
	 */
	void scheduleSyncOrder();
}
