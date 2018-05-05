package com.gv.base.role.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseRolesAuthorities;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.web.action.BaseAction;
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
@Controller("BaseRolesAuthoritiesAction")
@Scope("prototype")
public class BaseRolesAuthoritiesAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(BaseRolesAuthoritiesAction.class);
	private BaseRolesAuthorities baseRolesAuthorities;
	

	public BaseRolesAuthorities getBaseRolesAuthorities() {
		return baseRolesAuthorities;
	}

	public void setBaseRolesAuthorities(BaseRolesAuthorities baseRolesAuthorities) {
		this.baseRolesAuthorities = baseRolesAuthorities;
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
			logger.info("***** BaseRolesAuthoritiesAction.list() method begin*****");
		}		

		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		dto.put("roleCode", request.getParameter("roleCode"));
		page = (Pagination)super.doService("BaseRolesAuthoritiesService", "getList", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesAction.list() method end*****");
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
			logger.info("***** BaseRolesAuthoritiesAction.bfAdd() method begin*****");
		}		
		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		dto.put("roleCode", request.getParameter("roleCode"));
		
		page = (Pagination)super.doService("BaseRolesAuthoritiesService", "getAuthoritieList", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesAction.bfAdd() method end*****");
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
			logger.info("***** BaseRolesAuthoritiesAction.add() method begin*****");
		}		
		
		dto.put("authoritiesCode", ids);
		dto.put("roleCode", request.getParameter("roleCode"));
		
		dto = (Dto) super.doService("BaseRolesAuthoritiesService", "save", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesAction.add() method end*****");
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
			logger.info("***** BaseRolesAuthoritiesAction.delete() method begin*****");
		}		
		
		dto.put("ids",ids);
		super.doService("BaseRolesAuthoritiesService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseRolesAuthoritiesAction.delete() method end*****");
		}	
		return AJAX;
	}
	

}
