package com.lshop.manage.blog.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvBlogTags;


public interface LvBlogTagsService {
    /**
     * 添加标签信息
     * @param dto
     * @return
     * @throws ServiceException
     */
	public Dto add(Dto dto) throws ServiceException;
	
	/**
	 * 修改标签信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogTags update(Dto dto) throws ServiceException;
	
	/**
	 * 查询标签列表信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination list(Dto dto) throws ServiceException;
    
	/**
	 * 删除标签信息
	 * @param dto
	 * @throws ServiceException
	 */
	public Dto delete(Dto dto) throws ServiceException;
	
	
	/**
	 * 保持标签信息
	 * @param dto 
	 * @return
	 * @throws ServiceException
	 */
	public Boolean save(Dto dto) throws ServiceException;
	
	/**
	 * 根据标签编号获取标签信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogTags get(Dto dto) throws ServiceException;
}
