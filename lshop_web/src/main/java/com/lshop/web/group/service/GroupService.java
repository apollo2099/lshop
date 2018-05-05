package com.lshop.web.group.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.pojo.logic.LvOrder;

/**
 * 团购模块
 * @author zhengxue
 * @version 2.0 2012-08-08
 *
 */
public interface GroupService {
	
	/**
	 * 获取团购信息lv_group_buy
	 * @param dto groupCode
	 * @return
	 * @throws ServiceException
	 */
	public LvGroupBuy getGroupByCode(Dto dto) throws ServiceException;
	
	/**
	 * 获取团购属性信息lv_group_property
	 * @param dto groupCode
	 * @return
	 * @throws ServiceException
	 */
	public List<LvGroupProperty> getGroupPropertyList(Dto dto) throws ServiceException;
	
	/**
	 * 获取团购信息
	 * 根据code获取
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvGroupBuy getGroupBuyByCode(Dto dto) throws ServiceException;
	
	/**
	 * 保存订单信息——团购中
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto saveOrderForGroup(Dto dto) throws ServiceException;
	
	/**
	 * 检验该用户之前是否团购过此产品
	 * 需传递参数uid,groupCode
	 * @param dto
	 * @return
	 * @throws ServiceExceptionn
	 */
	public LvOrder checkGroupOrder(Dto dto) throws ServiceException;
	/**
	 * 订单支付后团购处理
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public ResultMsg finishOrderGroup(LvOrder order) throws Exception;
}
