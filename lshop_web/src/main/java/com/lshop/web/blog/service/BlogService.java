package com.lshop.web.blog.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogLeave;
import com.lshop.common.pojo.logic.LvBlogTags;
import com.lshop.common.pojo.logic.LvBlogType;

/**
 * 官方博客
 * @author zhengxue
 * @version 2.0 2012-06-29
 *
 */
public interface BlogService {

	/**
	 * 查看所有的博客文章信息
	 * @param dto
	 * @return Pagination
	 * @throws ServiceException
	 */
	public Pagination list(Dto dto) throws ServiceException;
	
	/**
	 * 根据博客文章id查询博客信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogContent getContent(Dto dto)throws ServiceException;
	
	/**
	 * 查询博客类别信息
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	public List getTypeList(Dto dto)throws ServiceException;
	
	/**
	 * 查询热门标签信息
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	public List getTagsList(Dto dto)throws ServiceException;
	
	/**
	 * 根据标签的id获取标签信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogTags getTags(Dto dto)throws ServiceException;
	
	/**
	 * 查询热门博客文章信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List getContentTop5(Dto dto)throws ServiceException;
	
	/**
	 * 汇总所有文章分类的条数
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	public List getTypeCount(Dto dto)throws ServiceException;
	
	/**
	 * 统计所有博客文章的总数
	 * @param dto
	 * @return Integer
	 * @throws ServiceException
	 */
	public Integer sumContent(Dto dto)throws ServiceException;
	
	/**
	 * 根据文章查询文章回复信息(评论)
	 * @param dto
	 * @return Pagination
	 * @throws ServiceException
	 */
	public Pagination leaveList(Dto dto)throws ServiceException;
	
	/**
	 * 查询管理员对评论的回复
	 * @param dto
	 * @return List
	 * @throws ServiceException
	 */
	public List manageLeaveList(Dto dto)throws ServiceException;
	
	/**
	 * 新增文章评论
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogLeave saveBlogLeave(Dto dto) throws ServiceException ;
	
	/**
	 * 更新博客的回复数
	 * @param dto
	 * @return LvBlogContent
	 * @throws ServiceException
	 */
	public LvBlogContent updateReplyNum(Dto dto) throws ServiceException;
	
	/**
	 * 更新博客的浏览数
	 * @param dto
	 * @return LvBlogContent
	 * @throws ServiceException
	 */
	public LvBlogContent updateClickNum(Dto dto) throws ServiceException;
	
	/**
	 * 获取上一篇博客信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogContent getUp(Dto dto)throws ServiceException;
	
	/**
	 * 获取下一篇博客信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogContent getNext(Dto dto)throws ServiceException;
	
	/**
	 * 获取默认博客分类（首页展示用，排序值最大，创建时间最新）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogType getDefaultBlogType(Dto dto) throws ServiceException;
	
	/**
	 * 根据博客分类获取博客内容（前四个）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvBlogContent> getContentsByType(Dto dto) throws ServiceException;
	
	/**
	 * 推荐博客内容（前四个）
	 * @param storeId 店铺标示
	 * @return 
	 * @throws ServiceException
	 */
	public List<LvBlogContent> getContentsByRecommend(String storeFlag) throws ServiceException;
	
	/**
	 * 根据分类ID获取所属博客分类
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvBlogType getBlogTypeById(Dto dto) throws ServiceException;
}
