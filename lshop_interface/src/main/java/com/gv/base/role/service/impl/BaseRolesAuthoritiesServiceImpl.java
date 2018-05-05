package com.gv.base.role.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.role.service.BaseRolesAuthoritiesService;
import com.gv.base.springSecurity.common.pojo.BaseRolesAuthorities;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.StringUtil;
@Service("BaseRolesAuthoritiesService")
public class BaseRolesAuthoritiesServiceImpl extends BaseServiceImpl implements BaseRolesAuthoritiesService{
	private static final Log logger	= LogFactory.getLog(BaseRolesAuthoritiesServiceImpl.class);
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
			logger.info("***** BaseRolesAuthoritiesServiceImpl.getList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		String roleCode = dto.getAsString("roleCode");
		String hql = "from BaseRolesAuthorities where status <> -1 ";
		
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			String itemHql = "select authoritiesCode from BaseAuthorities where authorityName like '%" + keyword.trim()+"%'";
			hql += " and authorityCode in ("+itemHql+")";
		}
		
		if (!StringUtil.IsNullOrEmpty(roleCode)) {
			hql += " and roleCode ='"+roleCode+"'";
		}
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesServiceImpl.getList() method end*****");
		}	
		return dao.find(Finder.create(hql), pageNo, pageSize);
	}
	

	/**
	 * 
	 * 功能描述：   添加前获取可添加的权限列表
	 * 创建人：yangzheng  
	 * 创建时间：2012-5-4 上午10:19:16   
	 * 修改人：yangzheng   
	 * 修改时间：2012-5-4 上午10:19:16   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getAuthoritieList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesServiceImpl.getResourcesList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		String roleCode = dto.getAsString("roleCode");
		String hql = "from BaseAuthorities where status <> -1 ";
		
		if (!StringUtil.IsNullOrEmpty(roleCode)) {
			String itemHql = "select authorityCode from BaseRolesAuthorities where roleCode ='" +roleCode+"'";
			hql += " and authoritiesCode not in  (" + itemHql+")";
		}
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			hql += " and authorityName like '%" + keyword.trim()+"%'";
		}

		
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesServiceImpl.getResourcesList() method end*****");
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
			logger.info("***** BaseRolesAuthoritiesServiceImpl.delete() method begin*****");
		}	
		
		String ids = dto.getAsString("ids");
		String hql = "delete  BaseRolesAuthorities where id in ("+ids+")";
		dao.delete(hql, null);
	
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesServiceImpl.delete() method end*****");
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
			logger.info("***** BaseRolesAuthoritiesServiceImpl.save() method begin*****");
		}		
		
		String authoritiesCode = dto.getAsString("authoritiesCode");
		String roleCode = dto.getAsString("roleCode");
		
		String[] authorityCodes = authoritiesCode.split(",");

		for (String authorityCode : authorityCodes) {
			BaseRolesAuthorities baseRolesAuthorities = new BaseRolesAuthorities();
			baseRolesAuthorities.setRoleCode(roleCode);
			baseRolesAuthorities.setAuthorityCode(authorityCode.replace(" ", ""));
			baseRolesAuthorities.setStatus(1);
			dao.save(baseRolesAuthorities);
		}

		dto.setAppCode(StatusCode.M_Success);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesServiceImpl.save() method end*****");
		}	
		
		return dto;
	}

}
