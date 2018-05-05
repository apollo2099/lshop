package com.lshop.web.order.service;

import java.util.List;

public interface OrderDetetailsService {
	public List findAllByOrderId(String orderId);
}
