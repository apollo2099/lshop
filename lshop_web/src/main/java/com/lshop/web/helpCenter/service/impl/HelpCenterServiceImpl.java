package com.lshop.web.helpCenter.service.impl;

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
import com.lshop.common.pojo.logic.LvHelp;
import com.lshop.common.pojo.logic.LvHelpType;
import com.lshop.web.helpCenter.service.HelpCenterService;

/**
 * 帮助中心
 * @author zhengxue
 * @version 2.0 2012-07-09
 *
 */
@Service("HelpCenterService")
public class HelpCenterServiceImpl implements HelpCenterService {

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	private static final Log logger	= LogFactory.getLog(HelpCenterServiceImpl.class);
	
	/**
	 * 查询所有的帮助类别
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvHelpType> getHelpTypes(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** HelpCenterService.getHelpTypes() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String hql="from LvHelpType where storeId=:storeFlag order by orderValue";
		
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		List<LvHelpType> helpTypes=(List<LvHelpType>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** HelpCenterService.getHelpTypes() method end*****");
		}	
		
		return helpTypes;
	}

	/**
	 * 根据帮助类别，查询其对应的所有帮助名称
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvHelp> getHelpsByType(Dto dto) throws ServiceException {

		if (logger.isInfoEnabled()){
			logger.info("***** HelpCenterService.getHelpsByType() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String hql="from LvHelp where helpId=:helpId and storeId=:storeFlag order by orderValue";
		
		Map params=new HashMap();
		params.put("helpId", dto.get("helpId"));
		params.put("storeFlag", storeFlag);
		List<LvHelp> helps=(List<LvHelp>)lvlogicReadDao.find(hql, params);
		
		if (logger.isInfoEnabled()){
			logger.info("***** HelpCenterService.getHelpsByType() method end*****");
		}	
		return helps;
	}

	/**
	 * 查询所有帮助名称
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvHelp> getHelpList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** HelpCenterService.getHelpList() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String hql="from LvHelp where storeId=:storeFlag order by orderValue";
		
		Map param=new HashMap();
		param.put("storeFlag", storeFlag);
		List<LvHelp> helps=(List<LvHelp>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** HelpCenterService.getHelpList() method end*****");
		}	
		
		return helps;
	}

	public HibernateBaseDAO getLvlogicReadDao() {
		return lvlogicReadDao;
	}

	public void setLvlogicReadDao(HibernateBaseDAO lvlogicReadDao) {
		this.lvlogicReadDao = lvlogicReadDao;
	}
}
