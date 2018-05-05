package com.lshop.web.order.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.web.order.service.OrderCouponService;

@Service("OrderCouponService")
public class OrderCouponServiceImpl implements OrderCouponService {
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	public int countByCoupon(String couponCode){
		int num=0;
		if(ObjectUtils.isNotEmpty(couponCode)){
			String hql=" FROM LvOrderCoupon oc"
					 + " WHERE EXISTS (SELECT 1 FROM LvCoupon c WHERE c.couponCode=oc.couponCode AND c.couponStatus<>-1)"
					 + " and EXISTS(select 1 from LvOrder o where o.oid=oc.orderId)"
					 + " and oc.couponCode=:couponCode ";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("couponCode", couponCode);
			Finder finder=Finder.create(hql);
			finder.setParams(param);
			num=lvlogicReadDao.countQueryResult(finder, null);
		}
		return num;
	}
}
