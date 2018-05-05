package com.lshop.manage.lvPayLogs.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvOrderLogs.action.LvOrderLogsAction;
/** 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvPayLogs.action.LvPayLogsAction.java]  
 * @ClassName:    [LvPayLogsAction]   
 * @Description:  [支付日志管理-action业务层 ]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-11 上午10:47:00]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-11 上午10:47:00]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvPayLogsAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvPayLogsAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvPayLogsAction.class);
	private LvPayLogs lvPayLogs;
	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询支付日志列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-11 上午10:40:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-11 上午10:40:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPayLogsAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvPayLogs", lvPayLogs);
		//产品效果图分页集合对象
		page=(Pagination) this.doService("LvPayLogsService", "getList", dto);
		return LIST;
	}
	
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看支付日志详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-11 上午10:40:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-11 上午10:40:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPayLogsAction.view() method begin*****");
		}
		dto.put("lvPayLogs", lvPayLogs);
		lvPayLogs=(LvPayLogs) this.doService("LvPayLogsService", "get", dto);
		this.getRequest().setAttribute("lvPayLogs", lvPayLogs);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPayLogsAction.view() method end*****");
		}
		return "view";
	}
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除支付日志信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-11 上午10:41:17]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-11 上午10:41:17]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPayLogsAction.del() method begin*****");
		}
		dto.put("lvPayLogs", lvPayLogs);
		this.doService("LvPayLogsService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPayLogsAction.view() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除支付日志信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-11 上午10:41:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-11 上午10:41:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String delList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPayLogsAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvPayLogs=new LvPayLogs();
				lvPayLogs.setId(id);
				dto.put("lvPayLogs", lvPayLogs);
				//删除model
				this.doService("LvPayLogsService", "delete", dto);
				}
			}
		}
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPayLogsAction.delList() method end*****");
		}
		return AJAX;
	}
	public LvPayLogs getLvPayLogs() {
		return lvPayLogs;
	}

	public void setLvPayLogs(LvPayLogs lvPayLogs) {
		this.lvPayLogs = lvPayLogs;
	}
}
