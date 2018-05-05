package com.gv.base.resources.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gv.base.dictionary.service.BaseDictionaryService;
import com.gv.base.resources.service.BaseResourcesService;
import com.gv.base.springSecurity.common.pojo.BaseDictionarys;
import com.gv.base.springSecurity.common.pojo.BaseResources;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.base.util.BaseUtil;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.StringUtil;
@Service("BaseResourcesService")
public class BaseResourcesServiceImpl extends BaseServiceImpl implements BaseResourcesService{
	private static final Log logger	= LogFactory.getLog(BaseResourcesServiceImpl.class);
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
			logger.info("***** BaseResourcesServiceImpl.getList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		String menuCode = dto.getAsString("menuCode");
		String hql = "from BaseResources where status <> -1 ";
		
		
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			hql += " and resourceName like '%" + keyword.trim()+"%'";
		}
		if (!StringUtil.IsNullOrEmpty(menuCode)) {
			hql += " and menuCode = '"+menuCode+"'";
		}
		
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.getList() method end*****");
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
			logger.info("***** BaseResourcesServiceImpl.delete() method begin*****");
		}	
		
		String ids = dto.getAsString("ids");
		String hql = "update from BaseResources set status = -1 where id in ("+ids+")";
		dao.update(hql,null);
		
		String getCodeHql = "select resourceCode from BaseResources where id in ("+ids+")";
		dto.put("parentCode", "BASE_RESOURCE");
		dto.put("getCodeHql", getCodeHql);
		baseDictionaryService.deleteByCode(dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.delete() method end*****");
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
			logger.info("***** BaseResourcesServiceImpl.save() method begin*****");
		}		
		
		BaseResources baseResources = (BaseResources) dto.get("baseResources");

		//int count = dao.countByProperty(BaseResources.class, "resourceString",baseResources.getResourceString());
		
		String hql="from BaseResources where resourceString=:resourceString and status<>-1";
		Map map=new HashMap();
		map.put("resourceString", baseResources.getResourceString());
		Finder finder = Finder.create(hql);
		int count=dao.countQueryResult(finder, map);
		
		
		if (count>0) {
			dto.setAppCode(StatusCode.M_Failure);
			dto.setAppMsg("该资源标识已经存在!");
			return dto;
		}
		String resourceCode="";
		if (StringUtil.IsNullOrEmpty(baseResources.getResourceCode())) {
			resourceCode = BaseUtil.getCode("RESOURCE");
		}else {
			resourceCode = baseResources.getResourceCode();
		}
		baseResources.setResourceCode(resourceCode);
		baseResources.setStatus(1);
		if (null==baseResources.getResourceType()) {
			baseResources.setResourceType(2);
		}

		dao.save(baseResources);
		BaseDictionarys baseDictionarys = new BaseDictionarys();
		baseDictionarys.setDictionaryCode(resourceCode);
		baseDictionarys.setCodeName(baseResources.getResourceName());
		baseDictionarys.setDescription(baseResources.getResourceDesc());
		baseDictionarys.setLevelNo(3);
		baseDictionarys.setParentCode("BASE_RESOURCE");
		baseDictionarys.setStatus(1);
		dto.put("baseDictionarys", baseDictionarys);
		baseDictionaryService.save(dto);
		
		dto.setAppCode(StatusCode.M_Success);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.save() method end*****");
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
	public BaseResources get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.update() method get*****");
		}		

		String resourceCode = dto.getAsString("resourceCode");
		BaseResources baseResources = (BaseResources) dao.findUniqueByProperty(BaseResources.class, "resourceCode", resourceCode);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.update() method get*****");
		}	
		
		return baseResources;
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
			logger.info("***** BaseResourcesServiceImpl.update() method begin*****");
		}		

		BaseResources baseResources = (BaseResources) dto.get("baseResources");
	
		dao.update(baseResources);

		dto.put("dictionaryCode", baseResources.getResourceCode());
		dto.put("parentCode", "BASE_RESOURCE");
		BaseDictionarys baseDictionarys = baseDictionaryService.get(dto);
		baseDictionarys.setCodeName(baseResources.getResourceName());
		baseDictionarys.setDescription(baseResources.getResourceDesc());
		
		dto.put("baseDictionarys", baseDictionarys);
		baseDictionaryService.update(dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.update() method end*****");
		}	
	}
	
	/**
	 * 
	 * 功能描述：   重置密码
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-24 下午06:19:27   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-24 下午06:19:27   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void updateResetPassword(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.updateResetPassword() method begin*****");
		}		

		String userCode = dto.getAsString("userCode");
		BaseUsers baseUsers = (BaseUsers) dao.findUniqueByProperty(BaseUsers.class, "userCode", userCode);
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String password = md5.encodePassword("gv123456", userCode);
		baseUsers.setUserPassword(password);
		dao.update(baseUsers);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.updateResetPassword() method end*****");
		}	
	}
	
	/**
	 * 
	 * 功能描述：  修改密码 
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-24 下午06:59:14   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-24 下午06:59:14   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto updateChangePassword(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.updateChangePassword() method begin*****");
		}		

		String userCode = dto.getAsString("userCode");
		String oldPassword = dto.getAsString("oldPassword");
		String newPassword = dto.getAsString("newPassword");
		BaseUsers baseUsers = (BaseUsers) dao.findUniqueByProperty(BaseUsers.class, "userCode", userCode);
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		oldPassword = md5.encodePassword(oldPassword, userCode);
		if (oldPassword.equals(baseUsers.getUserPassword())) {
			newPassword = md5.encodePassword(newPassword, userCode);
			baseUsers.setUserPassword(newPassword);
			dao.update(baseUsers);
			dto.setAppCode(StatusCode.M_Success);
		}else {
			dto.setAppCode(StatusCode.M_Failure);
			dto.setAppMsg("旧密码不正确!");
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesServiceImpl.updateChangePassword() method end*****");
		}	
		
		return dto;
	}
	
}
