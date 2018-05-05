package com.lshop.manage.lvOperationLogs.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvOperationLogs;

/**
 * 操作日志模块
 * @author zhengxue 2014-06-24
 *
 */
public interface LvOperationLogsService {

	/**
	 * 操作日志-查询列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	
	/**
	 * 操作日志-查看详情
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvOperationLogs view(Dto dto) throws ServiceException;
	
	/**
	 * 操作日志-单个删除
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public void delete(Dto dto) throws ServiceException;
	
	/**
	 * 操作日志-导出eccel
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List exportOperationLogs(Dto dto) throws ServiceException;
	
	/**
	 * 操作日志-新增
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto save(Dto dto) throws ServiceException;
	
	
}
