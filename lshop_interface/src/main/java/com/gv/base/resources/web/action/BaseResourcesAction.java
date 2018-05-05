package com.gv.base.resources.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.menu.common.pojo.BaseMenu;
import com.gv.base.springSecurity.common.pojo.BaseResources;
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
@Controller("BaseResourcesAction")
@Scope("prototype")
public class BaseResourcesAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(BaseResourcesAction.class);
	private BaseResources baseResources;

	public BaseResources getBaseResources() {
		return baseResources;
	}

	public void setBaseResources(BaseResources baseResources) {
		this.baseResources = baseResources;
	}

	/**
	 * 
	 * 功能描述：   获取菜单资源
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-26 上午11:29:33   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-26 上午11:29:33   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String getMenuResources() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.getMenuResources() method begin*****");
		}		

		List<BaseMenu> baseMenulList = (List<BaseMenu>) super.doService("BaseMenuService", "getAllMenu", dto);

		request.setAttribute("MENU_LIST", baseMenulList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.getMenuResources() method end*****");
		}	
		return "menuResources";
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
			logger.info("***** BaseResourcesAction.list() method begin*****");
		}		

		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		
		dto.put("menuCode", request.getParameter("code"));
		page = (Pagination)super.doService("BaseResourcesService", "getList", dto);
	
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.list() method end*****");
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
			logger.info("***** BaseResourcesAction.bfAdd() method begin*****");
		}		

		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.bfAdd() method end*****");
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
			logger.info("***** BaseResourcesAction.add() method begin*****");
		}		
		
		dto.put("baseResources", baseResources);
		
		dto = (Dto) super.doService("BaseResourcesService", "save", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.add() method end*****");
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
			logger.info("***** BaseResourcesAction.bfEdit() method begin*****");
		}		
		
		dto.put("resourceCode", request.getParameter("resourceCode"));
		baseResources = (BaseResources) super.doService("BaseResourcesService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.bfEdit() method end*****");
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
			logger.info("***** BaseResourcesAction.edit() method begin*****");
		}		
		
		dto.put("baseResources", baseResources);
		super.doService("BaseResourcesService", "update", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.edit() method end*****");
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
			logger.info("***** BaseResourcesAction.delete() method begin*****");
		}		
		
		dto.put("ids",ids);
		super.doService("BaseResourcesService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseResourcesAction.delete() method end*****");
		}	
		return AJAX;
	}
	

}
