package com.gv.base.role.web.action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.base.springSecurity.common.pojo.BaseRoles;
import com.gv.core.datastructure.page.Pagination;
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
@Controller("BaseRoleAction")
@Scope("prototype")
public class BaseRoleAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(BaseRoleAction.class);
	private BaseRoles baseRoles;
	private String storeFlagList;

	/**
	 * 
	 * 功能描述：   获取List
	 * 创建人：yangzheng  
	 * 创建时间：2012-2-24 下午03:06:18   
	 * 修改人：yangzheng   
	 * 修改时间：2012-2-24 下午03:06:18   
	 * 修改备注：
	 * @return
	 * @throws Exception
	 */
	public String list() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.list() method begin*****");
		}		

		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		
		dto.put("departmentCode", request.getParameter("code"));
		page = (Pagination)super.doService("BaseRoleService", "getList", dto);
		
	
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.list() method end*****");
		}	
		return LIST;
	}
	
	/**
	 * 
	 * 功能描述：   添加前
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
	 * 功能描述：   添加
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
		
		dto.put("baseRoles", baseRoles);
		super.doService("BaseRoleService", "save", dto);
		
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
		
		dto.put("roleCode", request.getParameter("roleCode"));
		baseRoles = (BaseRoles) super.doService("BaseRoleService", "get", dto);
		
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
		
		dto.put("baseRoles", baseRoles);
		super.doService("BaseRoleService", "update", dto);
		
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
		
		dto.put("ids",ids);
		super.doService("BaseRoleService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDepartmentAction.delete() method end*****");
		}	
		return AJAX;
	}
	
	public String getStoreFlagList() {
		return storeFlagList;
	}

	public void setStoreFlagList(String storeFlagList) {
		this.storeFlagList = storeFlagList;
	}

	public BaseRoles getBaseRoles() {
		return baseRoles;
	}

	public void setBaseRoles(BaseRoles baseRoles) {
		this.baseRoles = baseRoles;
	}
}
