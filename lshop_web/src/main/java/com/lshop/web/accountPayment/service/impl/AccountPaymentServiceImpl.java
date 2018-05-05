package com.lshop.web.accountPayment.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvAccountPayment;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.redis.RedisExpire;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.redis.StringHashDaoComponent;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.web.accountPayment.service.AccountPaymentService;

/**
 * @author caicl
 *
 */
/**
 * @author caicl
 *
 */
@Service("AccountPaymentService")
public class AccountPaymentServiceImpl implements AccountPaymentService{
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private StringHashDaoComponent stringHashDaoComponent;
	
	@Override
	public void MergePayment(Dto dto) throws ServiceException {
		String userCode = dto.getAsString("userCode");
		String storeFlag = dto.getAsString("storeFlag");
		Integer payment = dto.getAsInteger("payment");
		
		Integer originPayment = getUserPayment(userCode, storeFlag);
		if (ObjectUtils.isEmpty(originPayment)) {
			LvAccountPayment newPayment = new LvAccountPayment();
			newPayment.setCode(CodeUtils.getCode());
			newPayment.setCreateTime(new Date());
			newPayment.setModifyTime(newPayment.getCreateTime());
			newPayment.setPaymethod(payment);
			newPayment.setStoreId(storeFlag);
			newPayment.setUserCode(userCode);
			lvlogicWriteDao.save(newPayment);
		} else {
			String hql = "update LvAccountPayment set paymethod=:paymethod , modifyTime=:modifyTime where userCode=:userCode and storeId=:storeId";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("storeId", storeFlag);
			param.put("userCode", userCode);
			param.put("modifyTime", new Date());
			param.put("paymethod", payment);
			lvlogicWriteDao.update(hql, param);
		}
		//修改或者设置缓存
		stringHashDaoComponent.put(RedisKeyConstant.UserPayment(userCode), storeFlag, payment.toString());
		stringHashDaoComponent.expire(RedisKeyConstant.UserPayment(userCode), RedisExpire.UserPaymentDay, TimeUnit.DAYS);
	}
	/**
	 * 查看用户店铺默认支付方式
	 * @param userCode
	 * @param storeFlag
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Integer getUserPayment(String userCode, String storeFlag) throws ServiceException {
		String pay = stringHashDaoComponent.get(RedisKeyConstant.UserPayment(userCode), storeFlag);
		if (StringUtils.isEmpty(pay)) {
			Integer payment = getAccountPaymentFromDB(userCode, storeFlag);
			if (null != payment) {
				//set cache
				stringHashDaoComponent.put(RedisKeyConstant.UserPayment(userCode), storeFlag, payment.toString());
				stringHashDaoComponent.expire(RedisKeyConstant.UserPayment(userCode), RedisExpire.UserPaymentDay, TimeUnit.DAYS);
				return payment;
			} else {
				//set cache
				stringHashDaoComponent.put(RedisKeyConstant.UserPayment(userCode), storeFlag, RedisKeyConstant.EMPTY);
				stringHashDaoComponent.expire(RedisKeyConstant.UserPayment(userCode), RedisExpire.UserPaymentDay, TimeUnit.DAYS);
				return null;
			}
		} else if (RedisKeyConstant.EMPTY.equals(pay)) {
			return null;
		} else {
			return Integer.valueOf(pay);
		}
	}
	/**
	 * 从数据库查找用户店铺默认支付方式
	 * @param userCode
	 * @param storeFlag
	 * @return
	 */
	private Integer getAccountPaymentFromDB(String userCode, String storeFlag) {
		String hql = "select paymethod from LvAccountPayment where userCode=:userCode and storeId=:storeFlag";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userCode", userCode);
		param.put("storeFlag", storeFlag);
		return (Integer) lvlogicReadDao.findUnique(hql, param);
	}
	@Override
	public LvPaymentStyle getUserPaystyle(Dto dto) throws ServiceException {
		String userCode = dto.getAsString("userCode");
		String storeFlag = dto.getAsString("storeFlag");
		Integer originPayment = getUserPayment(userCode, storeFlag);
		if (ObjectUtils.isEmpty(originPayment)) {
			//没有默认支付方式
			return null;
		}
		//获得店铺所有支付方式
		List<LvPaymentStyle> paymentList = (List<LvPaymentStyle>) dto.get("paymentList");
		if (ObjectUtils.isEmpty(paymentList)) {
			String hql = "from LvPaymentStyle where isActivity=1  and storeFlag=:storeFlag and payType="+ Constants.PAYTYPE_LINEPAY +" order by orderValue asc";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("storeFlag", dto.get("storeFlag"));
			paymentList = (List<LvPaymentStyle>)lvlogicReadDao.find(hql,param);
		}
		//找出并验支付方式
		LvPaymentStyle dPaymentStyle;
		for (Iterator<LvPaymentStyle> iterator = paymentList.iterator(); iterator.hasNext();) {
			dPaymentStyle = iterator.next();
			if (originPayment.intValue() == dPaymentStyle.getPayValue()) {
				return dPaymentStyle;
			}
		}
		return null;
	}
}
