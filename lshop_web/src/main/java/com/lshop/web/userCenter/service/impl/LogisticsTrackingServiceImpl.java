package com.lshop.web.userCenter.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvLogistics;
import com.lshop.web.userCenter.service.LogisticsTrackingService;

/**
 * 物流信息
 * @author zhengxue
 * @since 2.0 2012-08-25
 *
 */
public abstract class  LogisticsTrackingServiceImpl implements LogisticsTrackingService {

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	protected LvLogistics getLogisticsrInfoByKey(String key){
		
		Map param = new HashMap();
		param.put("key", key);
		LvLogistics lvLogisticsr=	(LvLogistics) lvlogicReadDao.findUnique("FROM LvLogistics WHERE key=:key", param);
		return lvLogisticsr;
		
	}
	public void init(){
		
		
	}

}
