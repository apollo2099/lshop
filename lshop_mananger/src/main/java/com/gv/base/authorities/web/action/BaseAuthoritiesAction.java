package com.gv.base.authorities.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseAuthorities;
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
@Controller("BaseAuthoritiesAction")
@Scope("prototype")
public class BaseAuthoritiesAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(BaseAuthoritiesAction.class);
	private BaseAuthorities baseAuthorities;
	

	public BaseAuthorities getBaseAuthorities() {
		return baseAuthorities;
	}


	public void setBaseAuthorities(BaseAuthorities baseAuthorities) {
		this.baseAuthorities = baseAuthorities;
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
			logger.info("***** BaseAuthoritiesAction.list() method begin*****");
		}		

		dto.put("orderField", orderField);
		dto.put("pageNo", page.getPageNum());
		dto.put("pageSize", page.getNumPerPage());
		dto.put("keyword", keyword);
		dto.put("orderDirection", orderDirection);
		json.doClose();
		page = (Pagination)super.doService("BaseAuthoritiesService", "getList", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesAction.list() method end*****");
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
			logger.info("***** BaseAuthoritiesAction.bfAdd() method begin*****");
		}		

		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesAction.bfAdd() method end*****");
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
			logger.info("***** BaseAuthoritiesAction.add() method begin*****");
		}		
		
		dto.put("baseAuthorities", baseAuthorities);
		
		dto = (Dto) super.doService("BaseAuthoritiesService", "save", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesAction.add() method end*****");
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
			logger.info("***** BaseAuthoritiesAction.bfEdit() method begin*****");
		}		
		
		dto.put("authoritiesCode", request.getParameter("authoritiesCode"));
		baseAuthorities = (BaseAuthorities) super.doService("BaseAuthoritiesService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesAction.bfEdit() method end*****");
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
			logger.info("***** BaseAuthoritiesAction.edit() method begin*****");
		}		
		
		dto.put("baseAuthorities", baseAuthorities);
		super.doService("BaseAuthoritiesService", "update", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesAction.edit() method end*****");
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
			logger.info("***** BaseAuthoritiesAction.delete() method begin*****");
		}		
		
		dto.put("ids",ids);
		super.doService("BaseAuthoritiesService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesAction.delete() method end*****");
		}	
		return AJAX;
	}
	
	/**
	 * 
	 * 功能描述：   刷新缓存
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:04:53   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:04:53   
	 * 修改备注：
	 * @return
	 * @throws ActionException
	 */
	public String refreshCache() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesAction.delete() method begin*****");
		}		
		
		super.doService("BaseAuthoritiesService", "refreshCache", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseAuthoritiesAction.delete() method end*****");
		}	
		return AJAX;
	}

}
