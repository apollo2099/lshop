package com.lshop.web.shopCart.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponAid;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.web.shopCart.service.CouponService;


@Service("CouponService")
public class CouponServiceImpl implements CouponService {
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	public void saveCouponAid(Dto dto) throws ParseException{
		//获取优惠码信息
		String couponCode=dto.getAsString("couponCode");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql = "from LvCoupon where couponCode=:couponCode and isActivate=1 and isDel=0";
		HashMap param = new HashMap();
		param.put("couponCode", couponCode);
		List<LvCoupon> list =lvlogicReadDao.find(hql, param);
		LvCoupon lvCoupon=list.get(0);
		if(lvCoupon.getCouponType()==0){
			// 判断该优惠券是否已经使用
			hql = "from LvCouponAid where ccode='"+couponCode+"'";
			List listTmp=lvlogicReadDao.find(hql, null);
			if(ObjectUtils.isEmpty(listTmp)){
				LvCouponAid couponAid=new LvCouponAid();
				couponAid.setOid(lvOrder.getOid());
				couponAid.setCcode(couponCode);
				couponAid.setIsSettlement(Short.parseShort("0"));
				lvlogicWriteDao.save(couponAid);
			}
		}
		
	}
}
