package com.gv.base.authorities.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.authorities.service.BaseAuthoritiesService;
import com.gv.base.dictionary.service.BaseDictionaryService;
import com.gv.base.model.spring.ApplicationContextHolder;
import com.gv.base.springSecurity.common.pojo.BaseAuthorities;
import com.gv.base.springSecurity.common.pojo.BaseDictionarys;
import com.gv.base.springSecurity.service.impl.FilterInvocationSecurityMetadataSourceImpl;
import com.gv.base.util.BaseUtil;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.StringUtil;
@Service("BaseAuthoritiesService")
public class BaseAuthoritiesServiceImpl extends BaseServiceImpl implements BaseAuthoritiesService{
	private static final Log logger	= LogFactory.getLog(BaseAuthoritiesServiceImpl.class);
	@Resource private HibernateBaseDAO dao;
	
	@Resource private BaseDictionaryService baseDictionaryService;
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
			logger.info("***** BaseAuthoritiesServiceImpl.getList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		
		String hql = "from BaseAuthorities where status <> -1 ";
		
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			hql += " and authorityName like '%" + keyword.trim()+"%'";
		}
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.getList() method end*****");
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
			logger.info("***** BaseAuthoritiesServiceImpl.delete() method begin*****");
		}	
		
		String ids = dto.getAsString("ids");
		String hql = "update from BaseAuthorities set status = -1 where id in ("+ids+")";
		dao.update(hql,null);
		
		String getCodeHql = "select authoritiesCode from BaseAuthorities where id in ("+ids+")";
		dto.put("parentCode", "BASE_AUTHORITIES");
		dto.put("getCodeHql", getCodeHql);
		baseDictionaryService.deleteByCode(dto);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.delete() method end*****");
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
			logger.info("***** BaseAuthoritiesServiceImpl.save() method begin*****");
		}		
		
		BaseAuthorities baseAuthorities = (BaseAuthorities) dto.get("baseAuthorities");

//		int count = dao.countByProperty(BaseAuthorities.class, "authorityName",baseAuthorities.getAuthorityName());
		String hql="from BaseAuthorities where authorityName=:authorityName and status<>-1";
		Map param=new HashMap();
		param.put("authorityName",baseAuthorities.getAuthorityName());
		int count=dao.countQueryResult(Finder.create(hql), param);
		
		if (count>0) {
			dto.setAppCode(StatusCode.M_Failure);
			dto.setAppMsg("该名称已经存在!");
			return dto;
		}
		

		String authoritiesCode = BaseUtil.getCode("AUTHORITIES");
		baseAuthorities.setAuthoritiesCode(authoritiesCode);
		baseAuthorities.setStatus(1);
		dao.save(baseAuthorities);
		BaseDictionarys baseDictionarys = new BaseDictionarys();
		baseDictionarys.setDictionaryCode(authoritiesCode);
		baseDictionarys.setCodeName(baseAuthorities.getAuthorityName());
		baseDictionarys.setDescription(baseAuthorities.getAuthorityDesc());
		baseDictionarys.setLevelNo(3);
		baseDictionarys.setParentCode("BASE_AUTHORITIES");
		baseDictionarys.setStatus(1);
		dto.put("baseDictionarys", baseDictionarys);
		baseDictionaryService.save(dto);
		
		dto.setAppCode(StatusCode.M_Success);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.save() method end*****");
		}	
		
		return dto;
	}

	/**
	 * 
	 * 功能描述： 获取单个信息
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:51   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:51   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public BaseAuthorities get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.update() method get*****");
		}		

		String authoritiesCode = dto.getAsString("authoritiesCode");
		BaseAuthorities baseAuthorities = (BaseAuthorities) dao.findUniqueByProperty(BaseAuthorities.class, "authoritiesCode", authoritiesCode);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.update() method get*****");
		}	
		
		return baseAuthorities;
	}
	
	/**
	 * 
	 * 功能描述：   修改
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:51   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:51   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.update() method begin*****");
		}		

		BaseAuthorities baseAuthorities = (BaseAuthorities) dto.get("baseAuthorities");
	
		dao.update(baseAuthorities);
		
		String dictionaryCode = dto.getAsString("dictionaryCode");
		String parentCode = dto.getAsString("parentCode");
		dto.put("dictionaryCode", baseAuthorities.getAuthoritiesCode());
		dto.put("parentCode", "BASE_AUTHORITIES");
		BaseDictionarys baseDictionarys = baseDictionaryService.get(dto);
		baseDictionarys.setCodeName(baseAuthorities.getAuthorityName());
		baseDictionarys.setDescription(baseAuthorities.getAuthorityDesc());
		
		dto.put("baseDictionarys", baseDictionarys);
		baseDictionaryService.update(dto);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.update() method end*****");
		}	
	}
	
	/**
	 * 
	 * 功能描述：   刷新缓存
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:42   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:42   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void refreshCache(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.refreshCache() method begin*****");
		}	
		
		FilterInvocationSecurityMetadataSourceImpl fSource = (FilterInvocationSecurityMetadataSourceImpl) ServiceConstants.beanFactory.getBean("securityMetadataSource");
		fSource.refreshResource();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesServiceImpl.refreshCache() method end*****");
		}	
	}

}
