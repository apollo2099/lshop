package com.gv.base.user.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseAuthoritiesResources;
import com.gv.base.springSecurity.common.pojo.BaseRoles;
import com.gv.base.springSecurity.common.pojo.BaseUsersRoles;
import com.gv.base.user.service.BaseUsersRolesService;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.StringUtil;
@Service("BaseUsersRolesService")
public class BaseUsersRolesServiceImpl extends BaseServiceImpl implements BaseUsersRolesService{
	private static final Log logger	= LogFactory.getLog(BaseUsersRolesServiceImpl.class);
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
			logger.info("***** BaseUsersRolesServiceImpl.getList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		String userCode = dto.getAsString("userCode");
		String hql = "from BaseUsersRoles where status <> -1 ";
		
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			String itemHql = "select roleCode from BaseRoles where roleName like '%" + keyword.trim()+"%'";
			hql += " and roleCode in ("+itemHql+")";
		}
		
		if (!StringUtil.IsNullOrEmpty(userCode)) {
			hql += " and userCode ='"+userCode+"'";
		}
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesServiceImpl.getList() method end*****");
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
	public Pagination getRoleList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesServiceImpl.getResourcesList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		String userCode = dto.getAsString("userCode");
		String hql = "from BaseRoles where status <> -1 ";
		
		if (!StringUtil.IsNullOrEmpty(userCode)) {
			String itemHql = "select roleCode from BaseUsersRoles where userCode ='" +userCode+"'";
			hql += " and roleCode not in  (" + itemHql+")";
		}
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			hql += " and roleName like '%" + keyword.trim()+"%'";
		}
		if(!StringUtil.IsNullOrEmpty(dto.getAsString("flag"))){//店铺标示
			hql+=" and storeFlag='"+dto.getAsString("flag")+"'";
		}

		
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesServiceImpl.getResourcesList() method end*****");
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
			logger.info("***** BaseUsersRolesServiceImpl.delete() method begin*****");
		}	
		
		String ids = dto.getAsString("ids");
		String hql = "delete  BaseUsersRoles where id in ("+ids+")";
		dao.delete(hql, null);
	
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesServiceImpl.delete() method end*****");
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
			logger.info("***** BaseUsersRolesServiceImpl.save() method begin*****");
		}		
		
		String userCode = dto.getAsString("userCode");
		String roleCode = dto.getAsString("roleCode");
		
		String[] roleCodes = roleCode.split(",");

		for (String rCode : roleCodes) {
			BaseUsersRoles baseUsersRoles = new BaseUsersRoles();
			baseUsersRoles.setUserCode(userCode);
			baseUsersRoles.setRoleCode(rCode);
			baseUsersRoles.setStatus(1);
			dao.save(baseUsersRoles);
		}

		dto.setAppCode(StatusCode.M_Success);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesServiceImpl.save() method end*****");
		}	
		
		return dto;
	}

}
