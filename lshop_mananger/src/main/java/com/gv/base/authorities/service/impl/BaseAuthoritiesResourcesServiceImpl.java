package com.gv.base.authorities.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.authorities.service.BaseAuthoritiesResourcesService;
import com.gv.base.springSecurity.common.pojo.BaseAuthoritiesResources;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
@Service("BaseAuthoritiesResourcesService")
public class BaseAuthoritiesResourcesServiceImpl extends BaseServiceImpl implements BaseAuthoritiesResourcesService{
	private static final Log logger	= LogFactory.getLog(BaseAuthoritiesResourcesServiceImpl.class);
	@Resource private HibernateBaseDAO dao;
	
	
	/**
	 * 
	 * 功能描述：   分页获取信息
	 * 创建人：yangzheng  
	 * 创建时间：2011-6-10 下午04:58:19   
	 * 修改人：yangzheng   
	 * 修改时间：2011-6-10 下午04:58:19   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getList(Dto dto) throws ServiceException {
	
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesServiceImpl.getList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		String authoritiesCode = dto.getAsString("authoritiesCode");
		String hql = "from BaseAuthoritiesResources where status <> -1 ";
		
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			String itemHql = "select resourceCode from BaseResources where resourceName like '%" + keyword.trim()+"%'";
			hql += " and resourceCode in ("+itemHql+")";
		}
		
		if (!StringUtil.IsNullOrEmpty(authoritiesCode)) {
			hql += " and authorityCode ='"+authoritiesCode+"'";
		}
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesServiceImpl.getList() method end*****");
		}	
		return dao.find(Finder.create(hql), pageNo, pageSize);
	}
	

	/**
	 * 
	 * 功能描述：   添加前获取可添加的资源列表
	 * 创建人：yangzheng  
	 * 创建时间：2012-5-4 上午10:19:16   
	 * 修改人：yangzheng   
	 * 修改时间：2012-5-4 上午10:19:16   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getResourcesList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesServiceImpl.getResourcesList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		String authoritiesCode = dto.getAsString("authoritiesCode");
		String hql = "from BaseResources where status <> -1 ";
		
		if (!StringUtil.IsNullOrEmpty(authoritiesCode)) {
			String itemHql = "select resourceCode from BaseAuthoritiesResources where authorityCode ='" +authoritiesCode+"'";
			hql += " and resourceCode not in  (" + itemHql+")";
		}
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			hql += " and resourceName like '%" + keyword.trim()+"%'";
		}

		
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesServiceImpl.getResourcesList() method end*****");
		}	
		return dao.find(Finder.create(hql), pageNo, pageSize);
	}
	
	
	/**
	 * 
	 * 功能描述：   删除
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:42   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:42   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesServiceImpl.delete() method begin*****");
		}	
		
		String ids = dto.getAsString("ids");
		String hql = "delete  BaseAuthoritiesResources where id in ("+ids+")";
		dao.delete(hql, null);
	
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesServiceImpl.delete() method end*****");
		}	
	}
	
	/**
	 * 
	 * 功能描述：添加   
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:34   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:34   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesServiceImpl.save() method begin*****");
		}		
		
		String authoritiesCode = dto.getAsString("authoritiesCode");
		String resourcesCode = dto.getAsString("resourcesCode");
		if (ObjectUtils.isNotEmpty(resourcesCode)) {
			String[] resourcesCodes = resourcesCode.split(",");
			for (String rCode : resourcesCodes) {
    			BaseAuthoritiesResources baseAuthoritiesResources = new BaseAuthoritiesResources();
    			baseAuthoritiesResources.setAuthorityCode(authoritiesCode);
    			baseAuthoritiesResources.setResourceCode(rCode.replace(" ", ""));
    			baseAuthoritiesResources.setStatus(1);
    			dao.save(baseAuthoritiesResources);
    		}
			dto.setAppCode(StatusCode.M_Success);
		}

		
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesServiceImpl.save() method end*****");
		}	
		
		return dto;
	}

}
