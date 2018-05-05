package com.gv.base.user.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsersRoles;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.action.BaseManagerAction;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：MenuAction   
 * 类描述：   
 * 创建人：  yangzheng
 * 创建时间：2012-2-24 下午02:46:05   
 * 修改人：yangzheng   
 * 修改时间：2012-2-24 下午02:46:05   
 * 修改备注：   
 * @version
 */

@SuppressWarnings("serial")
@Controller("BaseUsersRolesAction")
@Scope("prototype")
public class BaseUsersRolesAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(BaseUsersRolesAction.class);
	private BaseUsersRoles baseUsersRoles;
	
	public BaseUsersRoles getBaseUsersRoles() {
		return baseUsersRoles;
	}

	public void setBaseUsersRoles(BaseUsersRoles baseUsersRoles) {
		this.baseUsersRoles = baseUsersRoles;
	}

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
			logger.info("***** BaseUsersRolesAction.list() method begin*****");
		}		

		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		dto.put("userCode", request.getParameter("userCode"));
		page = (Pagination)super.doService("BaseUsersRolesService", "getList", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesAction.list() method end*****");
		}	
		return LIST;
	}
	
	/**
	 * 
	 * 功能描述：  添加前
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
			logger.info("***** BaseUsersRolesAction.bfAdd() method begin*****");
		}		
		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		dto.put("userCode", request.getParameter("userCode"));
		
		page = (Pagination)super.doService("BaseUsersRolesService", "getRoleList", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesAction.bfAdd() method end*****");
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
			logger.info("***** BaseUsersRolesAction.add() method begin*****");
		}		
		
		dto.put("roleCode", ids);
		dto.put("userCode", request.getParameter("userCode"));
		
		dto = (Dto) super.doService("BaseUsersRolesService", "save", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesAction.add() method end*****");
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
			logger.info("***** BaseUsersRolesAction.delete() method begin*****");
		}		
		
		dto.put("ids",ids);
		super.doService("BaseUsersRolesService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUsersRolesAction.delete() method end*****");
		}	
		return AJAX;
	}
	

}
