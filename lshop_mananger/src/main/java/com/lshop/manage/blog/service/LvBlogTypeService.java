package com.lshop.manage.blog.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvBlogType;

public interface LvBlogTypeService {
	/**
	 * 添加博客类型
	 * @param vo
	 * @return
	 */
	public Dto add(Dto dto) throws ServiceException;
	
	/**
	 * 修改博客类型
	 * @param vo
	 * @return
	 */
	public LvBlogType update(Dto dto) throws ServiceException;
	
	/**
	 * 博客类型列表
	 * @param vo
	 * @return
	 */	
	public Pagination list(Dto dto) throws ServiceException;
	public List typelist(Dto dto)throws ServiceException;
	
	/**
	 * 删除博客类型
	 * @return
	 */
	public void delete(Dto dto) throws ServiceException;
	
	public Dto save(Dto dto) throws ServiceException;
	
	public LvBlogType get(Dto dto) throws ServiceException;
	
}
