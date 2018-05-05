package com.lshop.manage.lvOrderRemind.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvOrderRemind;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvOrderLogs.action.LvOrderLogsAction;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrderRemind.action.LvOrderRemindAction.java]  
 * @ClassName:    [LvOrderRemindAction]   
 * @Description:  [订单提醒信息-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-23 上午09:46:50]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-23 上午09:46:50]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvOrderRemindAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvOrderRemindAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvOrderRemindAction.class);
	private LvOrderRemind lvOrderRemind;
	

    /**
     * 
     * @Method: list 
     * @Description:  [分页查询订单提醒信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 上午09:47:22]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 上午09:47:22]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderRemindAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvOrderRemind", lvOrderRemind);
		//产品效果图分页集合对象
		page=(Pagination) this.doService("LvOrderRemindService", "getList", dto);
		return LIST;
	}
	
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查询订单提醒详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-23 上午09:52:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-23 上午09:52:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderRemindAction.view() method begin*****");
		}
		dto.put("lvOrderRemind", lvOrderRemind);
		lvOrderRemind=(LvOrderRemind) this.doService("LvOrderRemindService", "get", dto);
		this.getRequest().setAttribute("lvOrderRemind", lvOrderRemind);
		
		//根据用户code查询用户信息
		dto.put("accountCode", lvOrderRemind.getUserCode());
		LvAccount lvAccount=(LvAccount) this.doService("LvAccountService", "getAccount", dto);
		this.getRequest().setAttribute("lvAccount", lvAccount);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderRemindAction.view() method end*****");
		}
		return VIEW;
	}
	
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除订单提醒信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-23 上午09:52:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-23 上午09:52:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderRemindAction.del() method begin*****");
		}
		dto.put("ids",ids);
		this.doService("LvOrderRemindService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderRemindAction.del() method end*****");
		}
		return AJAX;
	}
	
	public LvOrderRemind getLvOrderRemind() {
		return lvOrderRemind;
	}

	public void setLvOrderRemind(LvOrderRemind lvOrderRemind) {
		this.lvOrderRemind = lvOrderRemind;
	}
}
