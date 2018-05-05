package com.gv.appstore.mng.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.appstore.pojo.LvDeveloperTemplet;
import com.gv.appstore.util.LogUtil;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.manage.common.action.BaseManagerAction;

@Controller("EmailMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class EmailMngAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(EmailMngAction.class);
	private LvDeveloperTemplet templ;

	public String list(){
		LogUtil.log(logger, "***** EmailMngAction.list() method begin*****");
		
		this.dto.put("page", this.page);
		this.dto.put("templ", this.templ);
		this.page = (Pagination)this.doService("EmailMngService", "findPage", dto);
		
		LogUtil.log(logger, "***** EmailMngAction.list() method end*****");
		return this.LIST;
	}
	
	public String befAdd(){
		
		return this.ADD;
	}
	
	public String add(){
		LogUtil.log(logger, "***** EmailMngAction.add() method begin*****");

		BaseUsers bu = (BaseUsers) this.getSession().getAttribute("USER");
		this.templ.setUpdateUser(bu.getUserAccount());
		templ.setCreateTime(new Date());
		templ.setStatus(1);
		dto.put("templ", this.templ);
		
		boolean flag = (Boolean)this.doService("EmailMngService", "isExist", dto);
		if(!flag){
			this.doService("EmailMngService", "add", dto);
			json.setStatusCode(200);
			json.setMessage("操作成功");
		}else{
			json.setStatusCode(300);
			json.setMessage("该邮件模版已经存在");
		}

		LogUtil.log(logger, "***** EmailMngAction.add() method end*****");
		return AJAX;
	}
	
	public String befEdit(){
		dto.put("id", templ.getId());
		this.templ = (LvDeveloperTemplet)this.doService("EmailMngService", "get", dto);
		return this.BFEDIT;
	}
	
	public String edit(){
		LogUtil.log(logger, "***** EmailMngAction.edit() method begin*****");
		
		BaseUsers bu = (BaseUsers) this.getSession().getAttribute("USER");
		this.templ.setUpdateUser(bu.getUserAccount());
		this.templ.setUpdateTime(new Date());
		dto.put("templ", this.templ);
		boolean flag = (Boolean)this.doService("EmailMngService", "isExist", dto);
		if(!flag){
			this.doService("EmailMngService", "edit", dto);
			json.setStatusCode(200);
			json.setMessage("操作成功");
		}else{
			json.setStatusCode(300);
			json.setMessage("该邮件模版已经存在");
		}
		
		LogUtil.log(logger, "***** EmailMngAction.edit() method end*****");
		return AJAX;
	}
	
	public String view(){
		dto.put("id", templ.getId());
		this.templ = (LvDeveloperTemplet)this.doService("EmailMngService", "get", dto);
		return this.VIEW;
	}
	
	public String delList(){
		LogUtil.log(logger, "***** EmailMngAction.delList() method begin*****");

		dto.put("ids", this.ids);
		this.doService("EmailMngService", "delList", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);

		LogUtil.log(logger, "***** EmailMngAction.add() method end*****");
		return AJAX;
	}

	public LvDeveloperTemplet getTempl() {
		return templ;
	}

	public void setTempl(LvDeveloperTemplet templ) {
		this.templ = templ;
	}
	
}
