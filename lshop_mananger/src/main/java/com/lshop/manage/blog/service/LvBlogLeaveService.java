package com.lshop.manage.blog.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvBlogLeave;
import com.lshop.common.pojo.logic.LvBlogType;

public interface LvBlogLeaveService {
	/**
	 * 添加回复
	 * @param vo
	 * @return
	 */
	public Dto add(Dto dto) throws ServiceException;
	
	/**
	 * 修改回复
	 * @param vo
	 * @return
	 */
	public LvBlogLeave update(Dto dto) throws ServiceException;
	
	/**
	 * 回复列表
	 * @param vo
	 * @return
	 */	
	public Pagination list(Dto dto) throws ServiceException;
	
	/**
	 * 删除回复
	 * @return
	 */
	public void delete(Dto dto) throws ServiceException;
	
	/**
	 * 保存回复
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogLeave save(Dto dto) throws ServiceException;
	
	public LvBlogLeave get(Dto dto) throws ServiceException;
	
	public Pagination replyLook(Dto dto)throws ServiceException;
}
