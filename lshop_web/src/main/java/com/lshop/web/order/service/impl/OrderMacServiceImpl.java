package com.lshop.web.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrderMac;
import com.lshop.common.util.DateUtils;
import com.lshop.web.order.service.OrderMacService;

@Service("OrderMacService")
public class OrderMacServiceImpl implements OrderMacService {
	private static final Log logger	= LogFactory.getLog(OrderMacServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	public Boolean saveOrderMac(LvOrderMac orderMac){
		lvlogicWriteDao.save(orderMac);
		return true;
	}
	
	/**
	 * 
	 * @Method: findByMac
	 * @Description: [查询当前mac当天是否存在销售订单]
	 * @Author: [liaoxj]
	 * @param mac
	 *            Mac编码
	 * @return LvOrderMac
	 */
	public LvOrderMac findByMac(String mac){
		LvOrderMac orderMac=null;
		if(ObjectUtils.isNotEmpty(mac)){
			Date date=new Date();
			String today=DateUtils.formatDate(date, "yyyy-MM-dd");
			String hql="from LvOrderMac where mac=:mac and createTime>:startTime and createTime<:endTime and status<>-1";
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("mac", mac);
        	params.put("startTime", DateUtils.convertToDateTime(today+" 00:00:00"));
        	params.put("endTime", DateUtils.convertToDateTime(today+" 23:59:59"));
        	List<LvOrderMac> list= lvlogicReadDao.find(hql, params);
        	if(ObjectUtils.isNotEmpty(list)){
        		orderMac=list.get(0);
        	}
		}
		return orderMac;
	}
	
	
	public int countByMac(String mac){
		int count=0;
		if(ObjectUtils.isNotEmpty(mac)){
			String hql="from LvOrderMac where mac=:mac and status<>-1";
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("mac", mac);
			Finder finder=Finder.create(hql);
			count=lvlogicReadDao.countQueryResult(finder, params);
		}
		return count;
	}
	/**
	 * 
	 * @Method: findByOrderId
	 * @Description: [一句话描述该类的功能]
	 * @Author: [liaoxj]
	 * @param orderId
	 *            订单编号
	 * @return LvOrderMac
	 */
	public LvOrderMac findByOrderId(String orderId){
		LvOrderMac orderMac=null;
		if(ObjectUtils.isNotEmpty(orderId)){
			String hql="from LvOrderMac where orderId=:orderId and status<>-1";
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("orderId", orderId);
        	List<LvOrderMac> list= lvlogicReadDao.find(hql, params);
        	if(ObjectUtils.isNotEmpty(list)){
        		orderMac=list.get(0);
        	}
		}
		return orderMac;
	}
	
}
