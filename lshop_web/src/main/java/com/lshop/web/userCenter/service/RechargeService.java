package com.lshop.web.userCenter.service;

import java.util.List;
import java.util.Map;

import com.gv.business.ws.boss.rechargeCard.SOAPException_Exception;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvRecharge;
import com.lshop.common.pojo.logic.LvVbPlans;
import com.lshop.web.userCenter.pojo.BossUser;

public interface RechargeService {
/**
 * 保存订单
 * @param dto
 * @throws RuntimeException
 */
public LvRecharge saveOrder(Dto dto)throws ServiceException;
/**
 * 订单记录列表分页
 * @param dto
 * @return
 * @throws RuntimeException
 */
public Pagination findPage(Dto dto)throws ServiceException;

/**
 * 获取订单
 */
public LvRecharge getOrder(Dto dto)throws ServiceException;

/**
 * 充值卡充值
 */
public Map cardCharge(Dto dto)throws ServiceException, SOAPException_Exception;

/**
 * 修改订单
 */
public LvRecharge updateOrder(Dto dto)throws ServiceException;

/**
 * 充值记录
 */
public Dto findChargeRecordList(Dto dto)throws ServiceException;
/**
 * 消费记录
 */
public Dto findConsumeRecordList(Dto dto)throws ServiceException;


/**
 * 系统定时取消10天后未支付的订单
 */
public void sysCalcelOrder()throws ServiceException;

public BossUser baseinfo(Dto dto) throws ServiceException;

/**
 * 获取支付方式列表
 * @param dto
 * @return
 * @throws ServiceException
 */
public List<LvPaymentStyle> getPaymentStyleList(Dto dto) throws ServiceException;
/**
 * 获取套餐选项
 */
public List<LvVbPlans> getLvVbPlansList(Dto dto)throws ServiceException;
/**
 * 修改对应的支付方式
 */
public void updatePayMethod(Dto dto)throws ServiceException;
}
