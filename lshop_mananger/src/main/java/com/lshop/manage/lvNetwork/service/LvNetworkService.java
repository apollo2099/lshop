package com.lshop.manage.lvNetwork.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvNetwork;

/**
 * 服务网点
 * @author zhengxue
 *
 */
public interface LvNetworkService extends BaseService{
	

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
	public LvNetwork get(Dto dto) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvNetwork save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvNetwork update(Dto dto)throws ServiceException;

}
