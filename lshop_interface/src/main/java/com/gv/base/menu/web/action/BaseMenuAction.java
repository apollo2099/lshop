package com.gv.base.menu.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.menu.common.pojo.BaseMenu;
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
@Controller("BaseMenuAction")
@Scope("prototype")
public class BaseMenuAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(BaseMenuAction.class);
	private BaseMenu baseMenu;
	
	public BaseMenu getBaseMenu() {
		return baseMenu;
	}
	public void setBaseMenu(BaseMenu baseMenu) {
		this.baseMenu = baseMenu;
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
	public String getAllMenu() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuAction.getAllMenu() method begin*****");
		}		

		List<BaseMenu> baseMenulList = (List<BaseMenu>) super.doService("BaseMenuService", "getAllMenu", dto);

		request.setAttribute("MENU_LIST", baseMenulList);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuAction.getAllMenu() method end*****");
		}	
		return LIST;
	}
	
	/**
	 * 
	 * 功能描述：   创建新菜单前
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
			logger.info("***** BaseMenuAction.bfAdd() method begin*****");
		}		

		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuAction.bfAdd() method end*****");
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
			logger.info("***** BaseMenuAction.add() method begin*****");
		}		
		
		dto.put("baseMenu", baseMenu);
		super.doService("BaseMenuService", "save", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuAction.add() method end*****");
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
			logger.info("***** BaseMenuAction.bfEdit() method begin*****");
		}		
		
		dto.put("menuCode", request.getParameter("menuCode"));
		baseMenu = (BaseMenu) super.doService("BaseMenuService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuAction.bfEdit() method end*****");
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
			logger.info("***** BaseMenuAction.edit() method begin*****");
		}		
		
		dto.put("baseMenu", baseMenu);
		super.doService("BaseMenuService", "update", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuAction.edit() method end*****");
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
			logger.info("***** BaseMenuAction.delete() method begin*****");
		}		
		
		dto.put("menuCode",request.getParameter("menuCode"));
		super.doService("BaseMenuService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuAction.delete() method end*****");
		}	
		return AJAX;
	}
}
