package com.lshop.manage.weixin.wxUser.quartz;

import com.gv.core.exception.ServiceException;

public interface WxUserQuartz extends Runnable{
	public void init()throws ServiceException;
	public void getAllWxUser();
}
