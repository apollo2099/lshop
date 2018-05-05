package com.lshop.web.helpCenter.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvHelp;
import com.lshop.common.pojo.logic.LvHelpType;

/**
 * 帮助中心
 * @author zhengxue
 * @version 2.0 2012-07-09
 *
 */
public interface HelpCenterService {
	
	/**
	 * 查询所有的帮助类别
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvHelpType> getHelpTypes(Dto dto) throws ServiceException;
	
	/**
	 * 根据帮助类别，查询其对应的所有帮助名称
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvHelp> getHelpsByType(Dto dto) throws ServiceException;
	
	/**
	 * 查询所有帮助名称
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvHelp> getHelpList(Dto dto) throws ServiceException;

}
