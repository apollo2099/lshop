package com.gv.base.user.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.manage.common.action.BaseManagerAction;
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
@Controller("BaseUserAction")
@Scope("prototype")
public class BaseUserAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(BaseUserAction.class);
	private BaseUsers baseUsers;
	
	public BaseUsers getBaseUsers() {
		return baseUsers;
	}

	public void setBaseUsers(BaseUsers baseUsers) {
		this.baseUsers = baseUsers;
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
			logger.info("***** BaseUserAction.list() method begin*****");
		}		

		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		page = (Pagination)super.doService("BaseUserService", "getList", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.list() method end*****");
		}	
		return LIST;
	}
	
	/**
	 * 
	 * 功能描述：  添加用户前
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
			logger.info("***** BaseUserAction.bfAdd() method begin*****");
		}		
		List<LvStore> storeList=(List<LvStore>) this.doService("LvStoreService", "getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.bfAdd() method end*****");
		}	
		return BFADD;
	}
	
	/**
	 * 
	 * 功能描述：   添加用户
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
			logger.info("***** BaseUserAction.add() method begin*****");
		}		
		
		dto.put("baseUsers", baseUsers);
		
		dto = (Dto) super.doService("BaseUserService", "save", dto);
        
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.add() method end*****");
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
			logger.info("***** BaseUserAction.bfEdit() method begin*****");
		}		
		
		dto.put("userCode", request.getParameter("userCode"));
		baseUsers = (BaseUsers) super.doService("BaseUserService", "get", dto);
		
		//获取选中的店铺信息集合
		List<LvStore> selectFlagList=new ArrayList<LvStore>();
		String storeFlag=baseUsers.getStoreFlag();
		String [] arr=storeFlag.split(",");
		String temp="";
		for (int i = 0; i < arr.length; i++) {
			if (ObjectUtils.isNotEmpty(arr[i])) {
				LvStore tmpStore=new LvStore();
				tmpStore.setStoreFlag(arr[i].trim());
				selectFlagList.add(tmpStore);
			}
		}
		this.getRequest().setAttribute("selectFlagList", selectFlagList);
		
		List<LvStore> storeList=(List<LvStore>) this.doService("LvStoreService", "getShopList", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.bfEdit() method end*****");
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
			logger.info("***** BaseUserAction.edit() method begin*****");
		}		
		
		dto.put("baseUsers", baseUsers);
		super.doService("BaseUserService", "update", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.edit() method end*****");
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
			logger.info("***** BaseUserAction.delete() method begin*****");
		}		
		
		dto.put("ids",ids);
		super.doService("BaseUserService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.delete() method end*****");
		}	
		return AJAX;
	}
	
	/**
	 * 
	 * 功能描述：   重置密码
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-24 下午06:15:56   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-24 下午06:15:56   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String resetPassword() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.resetPassword() method begin*****");
		}		
		
		dto.put("userCode", request.getParameter("userCode"));
		super.doService("BaseUserService", "updateResetPassword", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.resetPassword() method end*****");
		}	
		return AJAX;
	}
	
	/**
	 * 
	 * 功能描述：   修改密码前
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-24 下午06:50:37   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-24 下午06:50:37   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String bfChangePassword() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.bfChangePassword() method begin*****");
		}		

		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.bfChangePassword() method end*****");
		}	
		return "bfChangePassword";
	}
	
	/**
	 * 
	 * 功能描述：   修改密码
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-24 下午06:50:37   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-24 下午06:50:37   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String changePassword() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.changePassword() method begin*****");
		}		
		
		dto.put("userCode", request.getParameter("userCode"));
		dto.put("oldPassword", request.getParameter("oldPassword"));
		dto.put("newPassword", request.getParameter("newPassword"));
		super.doService("BaseUserService", "updateChangePassword", dto);
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserAction.changePassword() method end*****");
		}	
		return AJAX;
	}
}
