package com.lshop.manage.lvOrderLogs.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvProductImage.action.LvProductImageAction;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrderLogs.action.LvOrderLogsAction.java]  
 * @ClassName:    [LvOrderLogsAction]   
 * @Description:  [订单日志管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-11 上午10:39:52]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-11 上午10:39:52]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvOrderLogsAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvOrderLogsAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvOrderLogsAction.class);
	private LvOrderLogs lvOrderLogs;

	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询订单日志列表]  
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
			  logger.info("***** LvOrderLogsAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvOrderLogs", lvOrderLogs);
		//产品效果图分页集合对象
		page=(Pagination) this.doService("LvOrderLogsService", "getList", dto);
		return LIST;
	}
	
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看订单日志详情]  
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
			  logger.info("***** LvOrderLogsAction.view() method begin*****");
		}
		dto.put("lvOrderLogs", lvOrderLogs);
		lvOrderLogs=(LvOrderLogs) this.doService("LvOrderLogsService", "get", dto);
		this.getRequest().setAttribute("lvOrderLogs", lvOrderLogs);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderLogsAction.view() method end*****");
		}
		return "view";
	}
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除订单日志信息]  
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
			  logger.info("***** LvOrderLogsAction.del() method begin*****");
		}
		dto.put("lvOrderLogs", lvOrderLogs);
		this.doService("LvOrderLogsService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderLogsAction.view() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除订单日志信息]  
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
			  logger.info("***** LvOrderLogsAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvOrderLogs=new LvOrderLogs();
				lvOrderLogs.setId(id);
				dto.put("lvOrderLogs", lvOrderLogs);
				//删除model
				this.doService("LvOrderLogsService", "delete", dto);
				}
			}
		}
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderLogsAction.delList() method end*****");
		}
		return AJAX;
	}

	public LvOrderLogs getLvOrderLogs() {
		return lvOrderLogs;
	}

	public void setLvOrderLogs(LvOrderLogs lvOrderLogs) {
		this.lvOrderLogs = lvOrderLogs;
	}

}
