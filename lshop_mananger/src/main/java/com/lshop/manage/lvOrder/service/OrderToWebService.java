package com.lshop.manage.lvOrder.service;

public interface OrderToWebService {
	public void sendMessageForWebActivity(String orderId, String userEmail,String storeId);
}
