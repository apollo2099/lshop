package com.lshop.web.applist.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvApp;
import com.lshop.web.applist.service.ApplistService;

/**
 * 应用模块
 * @author zhengxue
 * @since 2.0 2012-07-20
 *
 */
@Service("ApplistService")
public class ApplistServiceImpl implements ApplistService {

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	private static final Log logger	= LogFactory.getLog(ApplistServiceImpl.class);
	
	/**
	 * 通过code查找对应的LvApp
	 * 需要传递参数code
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvApp getAppByCode(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ApplistServiceImpl.getAppByCode() method begin*****");
		}	
		
		String code=dto.getAsString("appCode");
		LvApp app=(LvApp)lvlogicReadDao.findUniqueByProperty(LvApp.class, "code", code);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ApplistServiceImpl.getAppByCode() method end*****");
		}	
		
		return app;
	}

	/**
	 * 获取所有的应用列表
	 * @param dto
	 * @return
	 * @throws ServiceExcetpion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvApp> getApps(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ApplistServiceImpl.getApps() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String ptype=dto.getAsString("ptype");
		
		HashMap param = new HashMap();
		param.put("ptype", ptype);
		param.put("storeFlag", storeFlag);
		String hql = "from LvApp where typeCode=:ptype and storeId=:storeFlag order by sortId asc";
		List<LvApp> appList=(List<LvApp>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ApplistServiceImpl.getApps() method end*****");
		}	
		
		return appList;
	}

}
