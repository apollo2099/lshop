package com.lshop.manage.lvOrder.quartz.service;

import com.gv.core.exception.ServiceException;

public interface OrderCommentQuartzService extends Runnable{
	public void init()throws ServiceException;
	public void updateOrderIsReceipt();
}
