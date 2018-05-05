package com.lshop.web.ad.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvAd;
import com.lshop.web.ad.AdService;

/**
 * 广告模块
 * @author zhengxue
 * @since 2.0 2012-07-20
 *
 */
@Service("AdService")
public class adServiceImpl implements AdService {

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	private static final Log logger	= LogFactory.getLog(adServiceImpl.class);
	
	/**
	 * 根据key获取其对应的广告
	 * 需要传入参数key
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvAd> getAdByKey(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** adServiceImpl.getAdByKey() method begin*****");
		}	
		
		String key=dto.getAsString("adKey");
		String storeFlag=dto.getAsString("storeFlag");
		
		HashMap param = new HashMap();
		param.put("key", key);
		param.put("storeFlag", storeFlag);
		String hql = "from LvAd where adKey=:key and storeId=:storeFlag and status=1 order by sortId asc";
		List<LvAd> adList=(List<LvAd>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** adServiceImpl.getAdByKey() method end*****");
		}	
		return adList;
	}

}
