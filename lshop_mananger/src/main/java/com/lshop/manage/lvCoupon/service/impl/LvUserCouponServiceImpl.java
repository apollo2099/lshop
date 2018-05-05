package com.lshop.manage.lvCoupon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.lshop.manage.lvCoupon.service.LvUserCouponService;

@Service("LvUserCouponService")
public class LvUserCouponServiceImpl extends BaseServiceImpl implements LvUserCouponService {
	 private static final Log logger	= LogFactory.getLog(LvUserCouponServiceImpl.class);
	 @Resource 
	 private HibernateBaseDAO dao;
	 
	 /**
	  * 
	  * @Method: isExistCoupon 
	  * @Description:  [根据优惠码查询优惠码和二级推广商关联信息]  
	  * @Author:       [liaoxiongjian]     
	  * @CreateDate:   [2014-3-11 下午2:18:39]   
	  * @UpdateUser:   [liaoxiongjian]     
	  * @UpdateDate:   [2014-3-11 下午2:18:39]   
	  * @UpdateRemark: [说明本次修改内容]  
	  * @throws
	  */
	@Override
	public Boolean isExistCoupon(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserCouponServiceImpl.isExistCoupon() method begin*****");
		}
		String couponCode=(String) dto.get("couponCode");
		String hql="from LvUserCoupon where couponCode=:couponCode";
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("couponCode", couponCode);
		List list=dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserCouponServiceImpl.isExistCoupon() method end*****");
		}
		return false;
	}
	
	public void updateCoupon(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserCouponServiceImpl.updateCoupon() method begin*****");
		}
		String newCouponCode=(String) dto.get("newCouponCode");
		String oldCouponCode=(String) dto.get("oldCouponCode");
		String hql = "update LvUserCoupon set couponCode=:newCouponCode where couponCode=:oldCouponCode";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("newCouponCode", newCouponCode);
		param.put("oldCouponCode", oldCouponCode);
		dao.update(hql, param);
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserCouponServiceImpl.updateCoupon() method begin*****");
		}
	}

}
