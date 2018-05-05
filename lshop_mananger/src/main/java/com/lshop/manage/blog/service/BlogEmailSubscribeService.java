package com.lshop.manage.blog.service;
import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvBlogSubscribe;
public interface BlogEmailSubscribeService  extends Runnable{
	public void init();
	public void init2(Dto dto);
	public LvBlogSubscribe getSubscribe()throws ServiceException;
}
