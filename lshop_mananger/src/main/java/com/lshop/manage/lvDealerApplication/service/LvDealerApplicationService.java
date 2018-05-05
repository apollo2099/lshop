package com.lshop.manage.lvDealerApplication.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvDealerApplication;

/**
 * 经销商申请
 * @author zhengxue
 *
 */
public interface LvDealerApplicationService extends BaseService{
	

	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException;

	/**
	 * 获得单独的实体信息
	 */
	public LvDealerApplication get(Dto dto) throws ServiceException;


}
