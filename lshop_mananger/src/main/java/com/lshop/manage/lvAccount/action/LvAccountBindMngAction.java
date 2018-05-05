package com.lshop.manage.lvAccount.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvUserTh;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 *后台授权绑定管理
 *add by dingh
 */
@Controller("LvAccountBindMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvAccountBindMngAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvAccountBindMngAction.class);
	private String useremail;
	private LvUserTh lvUserTh;
	private String createDateStart;
	private String createDateEnd;


	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.list() method begin*****");
		}
		dto.put("page",page);
		this.dto.put("lvUserTh", this.lvUserTh);
		this.dto.put("createDateStart", this.createDateStart);
		this.dto.put("createDateEnd", this.createDateEnd);
		this.dto.put("useremail", this.useremail);
		this.page = (Pagination)this.doService("LvAccountBindService", "getPagination", dto);
		return LIST;
	}

	public String disable(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.disable() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		dto.put("id",this.lvUserTh.getId());
		dto.put("modifyuser", users.getUserName());
		this.doService("LvAccountBindService", "del", dto);
		json.doNavTabTodo();
		return AJAX;
	}
	
	public String disableList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.disableList() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		dto.put("ids",ids);
		dto.put("modifyuser", users.getUserName());
		this.doService("LvAccountBindService", "delList", dto);
		json.doNavTabTodo();
		return AJAX;
	}

	public LvUserTh getLvUserTh() {
		return lvUserTh;
	}

	public void setLvUserTh(LvUserTh lvUserTh) {
		this.lvUserTh = lvUserTh;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}


}
