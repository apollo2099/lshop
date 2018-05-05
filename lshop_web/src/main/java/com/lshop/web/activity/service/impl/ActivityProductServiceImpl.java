package com.lshop.web.activity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvActivityProduct;
import com.lshop.web.activity.service.ActivityProductService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_web] 
 * @Package:      [com.lshop.web.activity.service.impl.ActivityProductServiceImpl.java]  
 * @ClassName:    [ActivityProductServiceImpl]   
 * @Description:  [活动产品关联服务层实现]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2015-1-14 上午9:21:32]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2015-1-14 上午9:21:32]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
@Service("ActivityProductService")
public class ActivityProductServiceImpl implements ActivityProductService{

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	public List<LvActivityProduct> findByProductCode(String productCode){
		List<LvActivityProduct> list=null;
		if(ObjectUtils.isNotEmpty(productCode)){
			String hql=" select ap from LvActivityProduct ap " +
					   " where EXISTS(select 1 from LvActivity ac where ac.code=ap.activityCode and ac.status=1 and ac.startTime<=:startTime and ac.endTime>=:endTime) " +
					   " and productCode=:productCode ";
			Map<String,Object> param=new HashMap<String, Object>(); 
			param.put("productCode", productCode);
			param.put("startTime", new Date());
			param.put("endTime", new Date());
			list=lvlogicReadDao.find(hql, param);
		}
		
		return list;
	}
	
	

}
