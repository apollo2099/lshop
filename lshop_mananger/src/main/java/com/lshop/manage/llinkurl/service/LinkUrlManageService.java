package com.lshop.manage.llinkurl.service;

import java.util.List;

import javax.servlet.ServletException;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvLinkUrl;



/**
 * 页面静态化
 * @author tangd
 *
 */
public interface LinkUrlManageService {

	/**
	 * 静态化链接列表
	 * @param dto 刷选条件
	 * @return
	 */
	public List<LvLinkUrl> findAllLink(Dto dto);
	/**
	 * 链接列表(分页)
	 * @param dto 刷选条件
	 * @return
	 */
	public Pagination list(Dto dto);
	
	/**
	 * 添加资源
	 * @param dto
	 * @return
	 */
	public Integer addres(Dto dto);
	
	/**
	 * 修改资源
	 * @param dto
	 * @return
	 */
	public Integer updateres(Dto dto);
	
	/**
	 * 查看资源详细信息
	 * @param dto
	 * @return
	 */
	public BasePo viewres(Dto dto);
	/**
	 * 删除资源信息
	 * @param dto
	 * @return
	 */
	public Integer delres(Dto dto);
	
	/**
	 *单个页面静态化实现
	 * @param dto
	 * @return
	 */
	public void htmlStatic(Dto dto)throws ServiceException;
	public void htmlStaticBatch(Dto dto)throws ServiceException;
}
