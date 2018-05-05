package com.lshop.manage.coupon.server;

import java.util.Map;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvCoupon;

public interface CouponManageServer {

/**
 * 批量生成优惠劵
 * @param dto
 * @return
 */
public Integer createCoupon(Dto dto);
/**
 * 优惠劵列表
 * @param dto
 * @return
 */
public Pagination couponlist(Dto dto);

/**
 * 激活优惠劵
 * @param dto
 * @return
 */
public Dto doActivationCoupon(Dto dto);
/**
 * 批量删除
 * @param dto
 * @return
 */
Integer doDel(Dto dto);
/**
 * 根据订单id获取优惠券
 * @param dto
 * @return
 */
public LvCoupon getCoupon(Dto dto);
/**
 * 发送推广邮件优惠券
 * @param dto
 * @return
 */
public Map createSpreadCoupon(Dto dto);
public Map createRankCoupon(Dto dto);
/**
 * 根据优惠券的id获取优惠券
 */
public LvCoupon getCouponInfo(Dto dto);
/**
 * 修改优惠券
 */
public void update(Dto dto)throws ServiceException;
}
