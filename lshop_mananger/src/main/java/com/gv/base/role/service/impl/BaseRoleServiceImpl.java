package com.gv.base.role.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.dictionary.service.BaseDictionaryService;
import com.gv.base.role.service.BaseRoleService;
import com.gv.base.springSecurity.common.pojo.BaseDictionarys;
import com.gv.base.springSecurity.common.pojo.BaseRoles;
import com.gv.base.util.BaseUtil;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.StringUtil;
@Service("BaseRoleService")
public class BaseRoleServiceImpl extends BaseServiceImpl implements BaseRoleService{
	private static final Log logger	= LogFactory.getLog(BaseRoleServiceImpl.class);
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
			logger.info("***** BaseRoleServiceImpl.getList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");
		String departmentCode = dto.getAsString("departmentCode");
		String hql = "from BaseRoles where status <> -1 ";
		
		
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			hql += " and roleName like '%" + keyword.trim()+"%'";
		}
		if (!StringUtil.IsNullOrEmpty(departmentCode)) {
			hql += " and departmentCode = '"+departmentCode+"'";
		}
		
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRoleServiceImpl.getList() method end*****");
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
			logger.info("***** BaseRoleServiceImpl.delete() method begin*****");
		}	
		
		String ids = dto.getAsString("ids");
		String hql = "update from BaseRoles set status = -1 where id in ("+ids+")";
		dao.update(hql,null);
		String getCodeHql = "select roleCode from BaseRoles where id in ("+ids+")";
		dto.put("parentCode", "BASE_ROLE");
		dto.put("getCodeHql", getCodeHql);
		baseDictionaryService.deleteByCode(dto);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRoleServiceImpl.delete() method end*****");
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
			logger.info("***** BaseRoleServiceImpl.save() method begin*****");
		}		
		
		BaseRoles baseRoles = (BaseRoles) dto.get("baseRoles");
		String hql = "from BaseRoles where roleName='"+baseRoles.getRoleName()+"' and status=1 and storeFlag='"+baseRoles.getStoreFlag()+"'";
		
		if (dao.find(hql, null).size()>0) {
			dto.setAppCode(StatusCode.M_Failure);
			dto.setAppMsg("该角色名称已经存在!");
			return dto;
		}
		

		String roleCode = BaseUtil.getCode("ROLE");
		baseRoles.setRoleCode(roleCode);
		baseRoles.setStatus(1);
		dao.save(baseRoles);
		
		BaseDictionarys baseDictionarys = new BaseDictionarys();
		baseDictionarys.setDictionaryCode(roleCode);
		baseDictionarys.setCodeName(baseRoles.getRoleName());
		baseDictionarys.setDescription(baseRoles.getRoleDesc());
		baseDictionarys.setLevelNo(3);
		baseDictionarys.setParentCode("BASE_ROLE");
		baseDictionarys.setStatus(1);
		dto.put("baseDictionarys", baseDictionarys);
		baseDictionaryService.save(dto);
		dto.setAppCode(StatusCode.M_Success);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRoleServiceImpl.save() method end*****");
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
	public BaseRoles get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRoleServiceImpl.update() method get*****");
		}		

		String roleCode = dto.getAsString("roleCode");
		BaseRoles baseRoles = (BaseRoles) dao.findUniqueByProperty(BaseRoles.class, "roleCode", roleCode);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRoleServiceImpl.update() method get*****");
		}	
		
		return baseRoles;
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
			logger.info("***** BaseRoleServiceImpl.update() method begin*****");
		}		

		BaseRoles baseRoles = (BaseRoles) dto.get("baseRoles");
	
		dao.update(baseRoles);
		
		String dictionaryCode = dto.getAsString("dictionaryCode");
		String parentCode = dto.getAsString("parentCode");
		dto.put("dictionaryCode", baseRoles.getRoleCode());
		dto.put("parentCode", "BASE_ROLE");
		BaseDictionarys baseDictionarys = baseDictionaryService.get(dto);
		baseDictionarys.setCodeName(baseRoles.getRoleName());
		baseDictionarys.setDescription(baseRoles.getRoleDesc());
		
		dto.put("baseDictionarys", baseDictionarys);
		baseDictionaryService.update(dto);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRoleServiceImpl.update() method end*****");
		}	
	}

}
