package com.lshop.manage.lvExtendBalance.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvExtendBalanceDetails;
import com.lshop.manage.lvExtendBalance.service.LvExtendBalanceDetalsService;
import com.lshop.manage.lvOrder.service.impl.LvOrderDetailsServiceImpl;

@Service("LvExtendBalanceDetalsService")
public class LvExtendBalanceDetalsServiceImpl implements LvExtendBalanceDetalsService {
	private static final Log logger	= LogFactory.getLog(LvOrderDetailsServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Override
	public void save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceDetalsServiceImpl.save() method begin*****");
		}
		LvExtendBalanceDetails lvExtendBalanceDetals=(LvExtendBalanceDetails) dto.get("lvExtendBalanceDetals");
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			lvExtendBalanceDetals.setStoreId(dto.getAsString("flag"));
		}else{
			lvExtendBalanceDetals.setStoreId("tvpadcn");
		}
		dao.save(lvExtendBalanceDetals);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceDetalsServiceImpl.save() method end*****");
		}
		
	}
	@Override
	public boolean delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceDetalsServiceImpl.delete() method begin*****");
		}
		String balanceId=(String) dto.get("balanceId");
		String orderId=(String) dto.get("orderId");
		
		String hql="select count(DISTINCT s.orderId) from LvExtendBalanceDetails s,LvOrder o,LvOrderDetails ds " +
				"where s.orderId=o.oid AND o.payStatus=1 AND s.orderDetailsCode=ds.code " +
				"and s.balanceId='"+balanceId+"'  ";
		Long count= (Long) dao.findUnique(hql, null);
		if(count>1){
			hql="delete from LvExtendBalanceDetails where balanceId=:balanceId and orderId=:orderId ";
			Map param=new HashMap();
			param.put("balanceId", balanceId);
			param.put("orderId", orderId);
			dao.delete(hql, param);
		}else{
			return false;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceDetalsServiceImpl.delete() method end*****");
		}
		return true;
	}

}
