package com.gv.base.department.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.department.common.pojo.BaseDepartment;
import com.gv.core.exception.ActionException;
import com.gv.core.web.action.BaseAction;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseDepartmentAction   
 * 类描述：   
 * 创建人：  yangzheng
 * 创建时间：2012-5-4 下午02:57:45   
 * 修改人：yangzheng   
 * 修改时间：2012-5-4 下午02:57:45   
 * 修改备注：   
 * @version
 */

@SuppressWarnings("serial")
@Controller("BaseDepartmentAction")
@Scope("prototype")
public class BaseDepartmentAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(BaseDepartmentAction.class);
	private BaseDepartment baseDepartment;
	
	
	public BaseDepartment getBaseDepartment() {
		return baseDepartment;
	}

	public void setBaseDepartment(BaseDepartment baseDepartment) {
		this.baseDepartment = baseDepartment;
	}

	/**
	 * 
	 * 功能描述：   获取菜单
	 * 创建人：yangzheng  
	 * 创建时间：2012-2-24 下午03:06:18   
	 * 修改人：yangzheng   
	 * 修改时间：2012-2-24 下午03:06:18   
	 * 修改备注：
	 * @return
	 * @throws Exception
	 */
	public String getAllDepartment() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.getAllDepartment() method begin*****");
		}		

		List<BaseDepartment> baseDepartmentlList = (List<BaseDepartment>) super.doService("BaseDepartmentService", "getAllDepartment", dto);

		request.setAttribute("DEPARTMENT_LIST", baseDepartmentlList);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.getAllDepartment() method end*****");
		}	
		return LIST;
	}
	
	/**
	 * 
	 * 功能描述：   创建部门单前
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:01:38   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:01:38   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String bfAdd() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.bfAdd() method begin*****");
		}		

		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.bfAdd() method end*****");
		}	
		return BFADD;
	}
	
	/**
	 * 
	 * 功能描述：   创建新菜单
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:02:26   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:02:26   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String add() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.add() method begin*****");
		}		
		
		dto.put("baseDepartment", baseDepartment);
		super.doService("BaseDepartmentService", "save", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.add() method end*****");
		}	
		return AJAX;
	}
	
	/**
	 * 
	 * 功能描述：   修改前
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:03:16   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:03:16   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String bfEdit() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.bfEdit() method begin*****");
		}		
		
		dto.put("departmentCode", request.getParameter("departmentCode"));
		baseDepartment = (BaseDepartment) super.doService("BaseDepartmentService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.bfEdit() method end*****");
		}	
		return BFEDIT;
	}
	
	/**
	 * 
	 * 功能描述：  修改 
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:03:44   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:03:44   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String edit() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.edit() method begin*****");
		}		
		
		dto.put("baseDepartment", baseDepartment);
		super.doService("BaseDepartmentService", "update", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.edit() method end*****");
		}	
		return AJAX;
	}
	
	/**
	 * 
	 * 功能描述：   删除
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:04:53   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:04:53   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String delete() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.delete() method begin*****");
		}		
		
		dto.put("departmentCode",request.getParameter("departmentCode"));
		super.doService("BaseDepartmentService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.delete() method end*****");
		}	
		return AJAX;
	}
}
