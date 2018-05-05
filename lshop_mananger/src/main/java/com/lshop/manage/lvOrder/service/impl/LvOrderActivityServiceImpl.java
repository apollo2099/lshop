package com.lshop.manage.lvOrder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrderActivity;
import com.lshop.manage.lvOrder.service.LvOrderActivityService;

@Service("LvOrderActivityService")
public class LvOrderActivityServiceImpl implements LvOrderActivityService {

	private static final Log logger	= LogFactory.getLog(LvOrderActivityServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Override
	public List<LvOrderActivity> findOrderActivityByOrderId(Dto dto) {
		String orderId=(String) dto.get("orderId");
		Integer activityType=(Integer) dto.get("activityType");
	    String hql="select t from LvOrderActivity t where t.orderId=:orderId ";
	    Map<String,Object> param=new HashMap<String, Object>();
	    param.put("orderId", orderId);
	    if(ObjectUtils.isNotEmpty(activityType)){
	    	hql+="and t.activityType=:activityType";
	    	param.put("activityType", activityType);
	    }
	    List<LvOrderActivity> list=dao.find(hql, param);
		return list;
	}

}
