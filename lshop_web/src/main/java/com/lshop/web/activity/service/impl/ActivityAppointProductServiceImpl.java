package com.lshop.web.activity.service.impl;

import java.util.ArrayList;
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
import com.lshop.common.activity.vo.GeneralActivity;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.web.activity.service.ActivityAppointProductService;


/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_web] 
 * @Package:      [com.lshop.web.activity.service.impl.ActivityAppointProductServiceImpl.java]  
 * @ClassName:    [ActivityAppointProductServiceImpl]   
 * @Description:  [购买指定商品就送活动服务层实现]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2015-1-17 上午9:46:18]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2015-1-17 上午9:46:18]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
@Service("ActivityAppointProductService")
public class ActivityAppointProductServiceImpl implements ActivityAppointProductService{
	private static final Log logger = LogFactory.getLog(ActivityAppointProductServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	
	/**
	 * 
	 * @Method: findActivityByProductCode 
	 * @Description:  [根据商品编码查询有效的购买指定商品就送活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-17 上午10:00:49]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-17 上午10:00:49]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List findActivityByProductCode(String productCode) throws Exception {
		List list=null;
		if(ObjectUtils.isNotEmpty(productCode)){
			String hql=	" SELECT o.code as activityCode,o.activityTitle as activityTitle,t.isUseCoupon as isUseCoupon,t.storeId as storeId,t.uncollectedTotal as uncollectedTotal," +
					" t.givenTypeName as givenTypeName,t.givenProductCode as givenProductCode,t.givenTypeNum as givenTypeNum,t.lotteryTotal as lotteryTotal" +
					" FROM LvActivity o,LvActivityAppointProduct t " +
					" WHERE o.code=t.activityCode " +
					" AND EXISTS (SELECT  1 FROM LvActivityProduct ap WHERE o.code=ap.activityCode AND ap.productCode=:productCode) " +
					" AND o.status=:status" +
					" and (o.startTime<=:startTime AND o.endTime>=:endTime)";
			Map<String,Object> param=new HashMap<String, Object>(); 
			param.put("status", ActivityConstant.STATUS_USE);
			param.put("startTime", new Date());
			param.put("endTime", new Date());
			param.put("productCode", productCode);
			list= lvlogicReadDao.getMapListByHql(hql, param).getList();
		}else{
			logger.error("ActivityAppointProductServiceImpl.findByProductCode商品编码为空");
		}
		return list;
		
	}
	
	/**
	 * 
	 * @Method: findActivityByProductCode 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [处理逻辑：
	 * 1.更加商品查询活动信息
	 * 2.根据活动查询赠品xinx]     
	 * @CreateDate:   [2015-1-17 下午2:42:20]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-17 下午2:42:20]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List findActivityByProductCode(String [] productArr) throws Exception {
		List list=new ArrayList();
		if(ObjectUtils.isNotEmpty(productArr)){
			for (int i = 0; i < productArr.length; i++) {
				 String productCode=productArr[i];
				 List activityList= this.findActivityByProductCode(productCode);
				 list.addAll(activityList);
			}
		}
		//重复活动去重
		List dedupList=acitvityDeduplication(list);
		return dedupList;
	}
	
	
	
	/**
	 * 
	 * @Method: giftDeduplication 
	 * @Description:  [活动信息去重]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-21 上午10:45:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-21 上午10:45:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param giftList
	 * @return List<LvActivityGift>
	 */
	public List acitvityDeduplication(List activityList){
		List resultList=new ArrayList();
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(ObjectUtils.isNotEmpty(activityList)){
			for (int i = 0; i < activityList.size(); i++) {
				Map activityMap=(Map) activityList.get(i);
				String activityCode=(String) activityMap.get("activityCode");
				if (!map.containsKey(activityCode)) { 
					map.put(activityCode, activityMap); 
				}
			}
		}
		return new ArrayList( map.values());	
	}
}
