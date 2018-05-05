package com.lshop.web.coupon.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponMac;
import com.lshop.web.coupon.service.CouponMacService;

@Service("CouponMacService")
public class CouponMacServiceImpl implements CouponMacService{
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;

	/**
	 * 
	 * @Method: save 
	 * @Description:  [保存mac换取优惠券记录]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月7日 下午4:09:19]   
	 * @throws
	 */
	@Override
	public Boolean save(String mac,String couponCode,String userEmail,String ip,String sourceUrl) {
	    if(ObjectUtils.isNotEmpty(mac)){
	    	LvCouponMac lvCouponMac=new LvCouponMac();
	    	lvCouponMac.setMac(mac);
	    	lvCouponMac.setCouponCode(couponCode);
	    	lvCouponMac.setUserEmail(userEmail);
	    	lvCouponMac.setSourceUrl(sourceUrl);
	    	lvCouponMac.setIp(ip);
	    	lvCouponMac.setCreateTime(new Date());
	    	lvCouponMac.setStatus((short)0);
	    	lvlogicWriteDao.save(lvCouponMac);
			return true;
	    }
		return false;
	}

	/**
	 * 
	 * @Method: findByMac 
	 * @Description:  [根据mac查询是否存在兑换优惠券的记录]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月7日 下午4:04:35]      
	 * @throws
	 */
	@Override
	public LvCouponMac findByMac(String mac) {
		LvCouponMac couponMac=null;
		if(ObjectUtils.isNotEmpty(mac)){
			String hql="from LvCouponMac where mac=:mac and status<>-1";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("mac", mac);
			List<LvCouponMac> list= lvlogicReadDao.find(hql, param);
			if(ObjectUtils.isNotEmpty(list)){
				couponMac=list.get(0);
			}
		}
		return couponMac;
	}

	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [更新mac以旧换新记录状态]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月8日 下午3:49:19]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月8日 下午3:49:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean updateStatus(String couponCode) {
		LvCoupon coupon= (LvCoupon) lvlogicReadDao.findUniqueByProperty(LvCoupon.class, "code", couponCode);
		if(ObjectUtils.isNotEmpty(coupon)){
			String hql="update LvCouponMac set status=:status where couponCode=:couponCode";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("status", (short)1);
			param.put("couponCode", coupon.getCouponCode());
			lvlogicWriteDao.update(hql, param);
			return true;
		}
		return false;
	}
}
