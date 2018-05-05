package com.lshop.manage.blog.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvBlogSubscribe;
import com.lshop.common.pojo.logic.LvBlogTags;

public interface LvBlogSubscribeService {
	
	
	 /**
     * 添加邮件订阅
     * @param dto
     * @return
     * @throws ServiceException
     */
	public Dto add(Dto dto) throws ServiceException;
	
	/**
	 * 设置邮件订阅
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogSubscribe update(Dto dto) throws ServiceException;
	
	/**
	 * 查询邮件订阅列表信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination list(Dto dto) throws ServiceException;
    
	/**
	 * 删除邮件订阅信息
	 * @param dto
	 * @throws ServiceException
	 */
	public Dto delete(Dto dto) throws ServiceException;
	
	
	/**
	 * 保持邮件订阅信息
	 * @param dto 
	 * @return
	 * @throws ServiceException
	 */
	public Boolean save(Dto dto) throws ServiceException;
	
	/**
	 * 根据邮件订阅编号获取邮件订阅信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogSubscribe get(Dto dto) throws ServiceException;
	
	

}
