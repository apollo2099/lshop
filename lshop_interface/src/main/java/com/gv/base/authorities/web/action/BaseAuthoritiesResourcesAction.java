package com.gv.base.authorities.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseAuthoritiesResources;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
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
@Controller("BaseAuthoritiesResourcesAction")
@Scope("prototype")
public class BaseAuthoritiesResourcesAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(BaseAuthoritiesResourcesAction.class);
	private BaseAuthoritiesResources baseAuthoritiesResources ;
	
	public BaseAuthoritiesResources getBaseAuthoritiesResources() {
		return baseAuthoritiesResources;
	}

	public void setBaseAuthoritiesResources(
			BaseAuthoritiesResources baseAuthoritiesResources) {
		this.baseAuthoritiesResources = baseAuthoritiesResources;
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
			logger.info("***** BaseAuthoritiesResourcesAction.list() method begin*****");
		}		

		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		dto.put("authoritiesCode", request.getParameter("authoritiesCode"));
		page = (Pagination)super.doService("BaseAuthoritiesResourcesService", "getList", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesAction.list() method end*****");
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
			logger.info("***** BaseAuthoritiesResourcesAction.bfAdd() method begin*****");
		}		
		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		dto.put("authoritiesCode", request.getParameter("authoritiesCode"));
		
		page = (Pagination)super.doService("BaseAuthoritiesResourcesService", "getResourcesList", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesAction.bfAdd() method end*****");
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
			logger.info("***** BaseAuthoritiesResourcesAction.add() method begin*****");
		}		
		
		dto.put("resourcesCode", ids);
		dto.put("authoritiesCode", request.getParameter("authoritiesCode"));
		if (ObjectUtils.isEmpty(ids)&&ids==null) {
			json.setMessage("请先选择资源!");
			json.setStatusCode(300);
			return AJAX;
		}
		
		dto = (Dto) super.doService("BaseAuthoritiesResourcesService", "save", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesAction.add() method end*****");
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
			logger.info("***** BaseAuthoritiesResourcesAction.delete() method begin*****");
		}		
		
		dto.put("ids",ids);
		super.doService("BaseAuthoritiesResourcesService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesResourcesAction.delete() method end*****");
		}	
		return AJAX;
	}
	

}
