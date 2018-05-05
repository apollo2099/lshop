package com.lshop.manage.lvOrder.quartz.service;

import com.gv.core.exception.ServiceException;

public interface OrderFromCreatentService {
	public void synOrderLogisticsFromCreatent();

	public void orderFromCreatentTask() throws ServiceException;
}
