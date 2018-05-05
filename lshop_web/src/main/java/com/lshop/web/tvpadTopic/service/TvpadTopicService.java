package com.lshop.web.tvpadTopic.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvServiceNetwork;

/**
 * 专题模块
 * @author zhengxue
 *
 */
public interface TvpadTopicService {

	/**
	 * 获取服务网点
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvServiceNetwork> getNetworkList(Dto dto) throws ServiceException;
	
	/**
	 * 根据ID获取LvArea
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvArea getAreaById(Dto dto) throws ServiceException;
	
	/**
	 * 保存经销商申请信息
	 * @param dto
	 * @return
	 */
	public Boolean saveApplication(Dto dto);
	
}
