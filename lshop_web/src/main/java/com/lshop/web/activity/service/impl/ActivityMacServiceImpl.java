package com.lshop.web.activity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.ActivityMac;
import com.lshop.web.activity.service.ActivityMacService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_web] 
 * @Package:      [com.lshop.web.activity.service.impl.ActivityMacServiceImpl.java]  
 * @ClassName:    [ActivityMacServiceImpl]   
 * @Description:  [输入mac就送活动-数据库访问服务层]   
 * @Author:       [liaoxj]   
 * @CreateDate:   [2015年7月14日 下午7:34:12]   
 * @UpdateUser:   [liaoxj]   
 * @UpdateDate:   [2015年7月14日 下午7:34:12]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.0]
 */
@Service("ActivityMacService")
public class ActivityMacServiceImpl implements ActivityMacService {
	private static final Log logger = LogFactory.getLog(ActivityMacServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	
	/**
	 * 
	 * @Method: findByProduct 
	 * @Description:  [根据产品编码查询是否符合条件的输入mac就送活动]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月14日 下午7:35:15]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月14日 下午7:35:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode 产品编码
	 * @return ActivityMac
	 */
	public ActivityMac findByProduct(String productCode){
		if (logger.isInfoEnabled()){
			logger.info("***** ActivityMacServiceImpl.findByProduct() method begin*****");
			logger.info("***** ActivityMacServiceImpl.findByProduct() productCode=>"+productCode);
		}
		ActivityMac activityMac=null;
		if(ObjectUtils.isNotEmpty(productCode)){
			Date now = new Date();
			String hql="select t.activityTitle as activityTitle,t.code as activityCode,am.amount as amount,am.exchangeNum as exchangeNum "
					 + " from LvActivity t,LvActivityMac am "
					 + " where t.code=am.activityCode"
					 + " and t.typeKey=:typeKey "
					 + " and t.status=:status"
					 + " and t.startTime<=:startTime "
					 + " and t.endTime>:endTime "
					 + " and EXISTS(select 1 from LvActivityProduct ap where t.code=ap.activityCode and ap.productCode=:productCode)";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("typeKey", ActivityConstant.TYPE_MAC);
			param.put("status", ActivityConstant.STATUS_USE);
			param.put("startTime", now);
			param.put("endTime", now);
			param.put("productCode", productCode);
			List list= lvlogicReadDao.getMapListByHql(hql, param).getList();
			if(ObjectUtils.isNotEmpty(list)){
				Map result=(Map) list.get(0);
				if(ObjectUtils.isNotEmpty(result)){
					activityMac=new ActivityMac();
					if(ObjectUtils.isNotEmpty(result.get("activityCode"))){
						activityMac.setActivityCode(String.valueOf(result.get("activityCode")));	
					}
					if(ObjectUtils.isNotEmpty(result.get("activityTitle"))){
					    activityMac.setActivityTitle(String.valueOf(result.get("activityTitle")));	
					}
					if(ObjectUtils.isNotEmpty(result.get("amount"))){
						activityMac.setAmount(Double.valueOf(result.get("amount").toString()));
					}
					if(ObjectUtils.isNotEmpty(result.get("exchangeNum"))){
						activityMac.setExchangeNum(Integer.valueOf(result.get("exchangeNum").toString()));
					}
				}
			}
		}
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** ActivityMacServiceImpl.findByProduct() method end*****");
		}
		return activityMac;
	}
	
	
}
