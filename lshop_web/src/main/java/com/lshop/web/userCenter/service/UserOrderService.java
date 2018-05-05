package com.lshop.web.userCenter.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;

/**
 * 用户中心订单模块
 * @author zhengxue
 * @since 2.0 2012-08-03
 *
 */
public interface UserOrderService {
	
	/**
	 * 用户查看订单信息
	 * @return
	 */
	public Dto viewOrderInfo(Dto dto);
	
	/**
	 * 获取订单套餐详情表
	 * 根据订单详情表code来查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvOrderPackageDetails> getPackageDetails(Dto dto) throws ServiceException;
	
	/**
	 * 支付前确认订单信息
	 * @return
	 */
	public Dto toConfirmPay(Dto dto) throws ServiceException;
	
	/**
	 * 传递订单信息给支付页面
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto toPay(Dto dto) throws ServiceException;
	
	/**
	 * 确认收到货
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto confirmShouhuo(Dto dto) throws ServiceException;

	/**
	 * 获取第三方物流信息
	 * @param dto
	 * @return
	 */
	public Dto doLogisticsTrackingInfo(Dto dto);

	/**
	 *  保存评论信息
	 * @param dto
	 * @return
	 */
	public Dto saveComment(Dto dto);
	
	/**
	 * 提醒卖家发货
	 * @param dto
	 * @return
	 */
	public Dto remindOrder(Dto dto);
	
	/**
	 * 获取订单信息及订单地址信息
	 * @param dto
	 * @return
	 */
	public Dto getOrderInfo(Dto dto);
	/**
	 * 获得订单缩略信息
	 * @param String oid 订单号
	 * @return
	 */
	public LvOrder getOrderByCode(Dto dto);
}
