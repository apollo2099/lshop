package com.lshop.manage.lvOrder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOrderCoupon;
import com.lshop.manage.lvOrder.service.LvOrderCouponService;
import com.lshop.ws.server.popularize.order.bean.PCouponDto;

@Service("LvOrderCouponService")
public class LvOrderCouponServiceImpl implements LvOrderCouponService {
	private static final Log logger	= LogFactory.getLog(LvOrderCouponServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 
	 * @Method: findDetailsByCouponCode 
	 * @Description:  [根据优惠券查找订单优惠券详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-26 下午2:23:08]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-26 下午2:23:08]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List<LvOrderCoupon> findDetailsByCouponCode(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderCouponServiceImpl.findDetailsByCouponCode() method begin*****");
		}
		String couponCode=(String) dto.get("couponCode");
		String hql="from LvOrderCoupon where couponCode=:couponCode";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("couponCode", couponCode);
		List<LvOrderCoupon> list=dao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderCouponServiceImpl.findDetailsByCouponCode() method end*****");
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: findDetailsByOrderId 
	 * @Description:  [根据订单查询订单优惠券关联详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-26 下午2:41:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-26 下午2:41:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List<LvOrderCoupon> findDetailsByOrderId(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderCouponServiceImpl.findDetailsByCouponCode() method begin*****");
		}
		String orderId=(String) dto.get("orderId");
		String hql="from LvOrderCoupon where orderId=:orderId";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("orderId", orderId);
		List<LvOrderCoupon> list=dao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderCouponServiceImpl.findDetailsByCouponCode() method end*****");
		}
		return list;
	}
	@Override
	public List<PCouponDto> findByOrderNo(String orderNo) throws ServiceException {

		List<PCouponDto> list = new ArrayList<PCouponDto>();

		if (StringUtils.isNotBlank(orderNo)) {
			Map<String, Object> param = new HashMap<String, Object>();
			StringBuilder sql = new StringBuilder("select o from LvOrderCoupon o where o.orderId =:orderId");
			param.put("orderId", orderNo);

			List<LvOrderCoupon> tempList = dao.find(sql.toString(), param);

			if (CollectionUtils.isNotEmpty(tempList)) {
				for (LvOrderCoupon lvOrderCoupon : tempList) {
					String couponTypeCode = lvOrderCoupon.getCouponTypeCode();// 优惠券类型编码
					String couponCode = lvOrderCoupon.getCouponCode();// 优惠码

					PCouponDto vo = new PCouponDto(couponTypeCode, couponCode);

					list.add(vo);
				}
			}

		}
		return list;

	}
}
