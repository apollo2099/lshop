/**   
 * @filename: SysConfig.java
 * @description: TODO
 * @author：dh
 * @time：2014年9月22日 下午1:44:27
 * @version：V1.0   
 */

package com.lshop.manage.sysConfig.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.appstore.util.LogUtil;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvSysConfig;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**   
 * @author：dh
 * @time：2014年9月22日 下午1:44:27
 * @version：V1.0   
 * @description: TODO
 */
@Controller("SysConfigMngAction")
@Scope("prototype")
public class SysConfigMngAction extends  BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(SysConfigMngAction.class);
	private static final long serialVersionUID = -4714454156781212368L;
	
	private LvSysConfig config;
	
	public String list(){
		LogUtil.log(logger, "***** SysConfigMngAction.list() method begin*****");
		
		this.dto.put("page", this.page);
		this.dto.put("config", this.config);

		this.page = (Pagination) this.doService("SysConfigMngService", "findPage", dto);
		
		LogUtil.log(logger, "***** SysConfigMngAction.list() method end*****");
		return LIST;
	}
	
	/**
	 * 进入添加页面
	 */
	public String befAdd(){
		return BFADD;
	}
	
	/**
	 * 添加
	 */
	public String add(){
		if (logger.isInfoEnabled()){
			logger.info("***** SysConfigMngAction.save() method begin*****");
		}
		BaseUsers buser = (BaseUsers) this.getSession().getAttribute("USER");
		this.config.setCode(CodeUtils.getCode());
		Date date = new Date();
		this.config.setCreateTime(date);
		this.config.setUpdateTime(date);
		this.config.setStatus(1);
		this.config.setModifyUserName(buser.getUserAccount());
		this.config.setModifyUserCode(buser.getUserCode());
		this.dto.put("config", this.config);
		
		Boolean f = (Boolean)this.doService("SysConfigMngService", "isExist", dto);
		if(!f){
			this.doService("SysConfigMngService", "save", dto);
			json.setStatusCode(200);
		}else{
			json.setStatusCode(300);
			json.setMessage("该配置已存在");
		}

		if (logger.isInfoEnabled()){
			logger.info("***** SysConfigMngAction.save() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 跳入编辑页面
	 */
	public String befEdit(){
		this.dto.put("id", this.config.getId());
		this.config = (LvSysConfig)this.doService("SysConfigMngService", "getById", dto);
		return "edit";
	}
	
	public String view(){
		this.dto.put("id", this.config.getId());
		this.config = (LvSysConfig)this.doService("SysConfigMngService", "getById", dto);
		return "view";
	}
	
	/**
	 * 编辑
	 */
	public String edit(){
		BaseUsers buser = (BaseUsers) this.getSession().getAttribute("USER");
		this.config.setUpdateTime(new Date());
		this.config.setModifyUserName(buser.getUserAccount());
		this.config.setModifyUserCode(buser.getUserCode());
		this.dto.put("config", this.config);
		
		Boolean f = (Boolean)this.doService("SysConfigMngService", "isExist", dto);
		if(!f){
			this.doService("SysConfigMngService", "save", dto);
			json.setStatusCode(200);
		}else{
			json.setStatusCode(300);
			json.setMessage("该配置已存在");
		}
		return AJAX;
	}
	
	/**
	 * 删除
	 */
	public String del(){
		this.dto.put("ids", this.config.getId()+"");
		this.doService("SysConfigMngService", "delList", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		return AJAX;
	}
	
	/**
	 * 批量删除
	 */
	public String delList(){
		dto.put("ids", this.ids);
		this.doService("SysConfigMngService", "delList", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		return AJAX;
	}
	
	/**
	 * 初始化数据
	 */
	public String initData(){
		this.doService("SysConfigMngService", "initData", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		return AJAX;
	}

	public LvSysConfig getConfig() {
		return config;
	}

	public void setConfig(LvSysConfig config) {
		this.config = config;
	}

	
}
