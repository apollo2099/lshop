package com.lshop.web.order.service;

import java.util.List;

public interface OrderGiftService {
	public List findAllByOrderId(String orderId);
	public List giftDeduplication(List giftList);
}
