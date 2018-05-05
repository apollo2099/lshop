package com.lshop.manage.message.service;

import com.gv.core.datastructure.Dto;

public interface OrderMsgService {
	
	/**
	 * 发送订单信息
	 * @param oid
	 */
	 public void sendOrderMSGToWMS(String oid,String source);
	 
	 /**
	  * 接收发货信息
	  * @param dto
	  */
     public void  processOrderMSGToWMS(Dto dto);

}
