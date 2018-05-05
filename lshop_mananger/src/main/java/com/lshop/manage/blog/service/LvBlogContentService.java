package com.lshop.manage.blog.service;


import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvBlogContent;

public interface LvBlogContentService {
	/**
	 * 添加博客
	 * @param vo
	 * @return
	 */
	public Dto add(Dto dto) throws ServiceException;
	
	/**
	 * 修改博客
	 * @param vo
	 * @return
	 */
	public Dto update(Dto dto) throws ServiceException;
	
	/**
	 * 博客列表
	 * @param vo
	 * @return
	 */	
	public Pagination list(Dto dto) throws ServiceException;
	
	/**
	 * 删除博客
	 * @return
	 */
	public Dto delete(Dto dto) throws ServiceException;
	/**
	 * 查看博客文章
	 * @param vo
	 * @returnupdateType
	 */
	public LvBlogContent get(Dto dto) throws ServiceException ;
	
	/**
	 * 取最新的10条博文信息
	 */
	public Pagination listTop10(Dto dto) throws ServiceException;
}
