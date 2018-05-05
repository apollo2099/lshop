package com.gv.base.department.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.department.common.pojo.BaseDepartment;
import com.gv.base.department.service.BaseDepartmentService;
import com.gv.base.util.BaseUtil;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
@Service("BaseDepartmentService")
public class BaseDepartmentServiceImpl extends BaseServiceImpl implements BaseDepartmentService{
	private static final Log logger	= LogFactory.getLog(BaseDepartmentServiceImpl.class);
	@Resource private HibernateBaseDAO dao;
	
	
	/**
	 * 
	 * 功能描述：   获取所有部门
	 * 创建人：yangzheng  
	 * 创建时间：2012-5-4 下午01:25:35   
	 * 修改人：yangzheng   
	 * 修改时间：2012-5-4 下午01:25:35   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<BaseDepartment> getAllDepartment(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentServiceImpl.getAllDepartment() method begin*****");
		}		
		String hql = "from BaseDepartment where status=1 order by departmentDisorder ASC";
		List<BaseDepartment> baseDepartmentList = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentServiceImpl.getAllDepartment() method end*****");
		}	
	    return baseDepartmentList;
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
	public void save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentServiceImpl.save() method begin*****");
		}		

		BaseDepartment baseDepartment = (BaseDepartment) dto.get("baseDepartment");
		baseDepartment.setDepartmentCode(BaseUtil.getCode("DEP"));
		dao.save(baseDepartment);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentServiceImpl.save() method end*****");
		}	
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
	public BaseDepartment get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentServiceImpl.get() method get*****");
		}		

		String departmentCode = dto.getAsString("departmentCode");
		BaseDepartment baseDepartment = (BaseDepartment) dao.findUniqueByProperty(BaseDepartment.class, "departmentCode", departmentCode);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentServiceImpl.get() method get*****");
		}	
		
		return baseDepartment;
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
			logger.info("***** BaseDepartmentServiceImpl.update() method begin*****");
		}		

		BaseDepartment baseDepartment = (BaseDepartment) dto.get("baseDepartment");
		dao.update(baseDepartment);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentServiceImpl.update() method end*****");
		}	
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
	public Dto delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.delete() method begin*****");
		}	
		
		String departmentCode = dto.getAsString("departmentCode");
		
		int count = dao.countByProperty(BaseDepartment.class, "departmentParent",departmentCode);
		
		if (count>0) {
			dto.setAppCode(StatusCode.M_Failure);
			dto.setAppMsg("请先删除子部门!");
			return dto;
		}
		
		String hql = "delete BaseDepartment where departmentCode = '"+departmentCode+"'";
		dao.delete(hql,null);
		dto.setAppCode(StatusCode.M_Success);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.delete() method end*****");
		}	
		
		return dto;
	}
}
