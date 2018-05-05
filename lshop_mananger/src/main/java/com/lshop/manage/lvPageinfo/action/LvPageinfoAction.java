package com.lshop.manage.lvPageinfo.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.pojo.logic.LvPageinfo;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvGroupProperty.action.LvGroupPropertyAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvPageinfo.action.LvPageinfoAction.java]  
 * @ClassName:    [LvPageinfoAction]   
 * @Description:  [网站首页导航页面管理信息管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-26 上午10:24:09]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-26 上午10:24:09]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvPageinfoAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvPageinfoAction extends BaseAction{
	private static final Log logger = LogFactory.getLog(LvPageinfoAction.class);
	private LvPageinfo lvPageinfo;
	

   /**
    * 
    * @Method: list 
    * @Description:  [分页查询网站首页导航页面管理列表信息]  
    * @Author:       [liaoxiongjian]     
    * @CreateDate:   [2012-7-26 上午10:23:59]   
    * @UpdateUser:   [liaoxiongjian]     
    * @UpdateDate:   [2012-7-26 上午10:23:59]   
    * @UpdateRemark: [说明本次修改内容]  
    * @return String
    * @throws
    */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvPageinfo", lvPageinfo);
		page=(Pagination) this.doService("LvPageinfoService", "getList", dto);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.list() method end*****");
		}
		return LIST;
	}
    /**
     * 
     * @Method: befEdit 
     * @Description:  [跳转到网站首页导航页面管理信息页面]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-26 上午10:25:00]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-26 上午10:25:00]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.befEdit() method begin*****");
		}
		dto.put("lvPageinfo", lvPageinfo);
		lvPageinfo=(LvPageinfo) this.doService("LvPageinfoService", "get", dto);
		this.getRequest().setAttribute("lvPageinfo", lvPageinfo);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.befEdit() method end*****");
		}
		return "edit";
	}
    /**
     * 
     * @Method: edit 
     * @Description:  [修改网站首页导航页面管理信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-26 上午10:25:30]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-26 上午10:25:30]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvPageinfo.setModifyUserId(users.getId());
		lvPageinfo.setModifyUserName(users.getUserName());
		lvPageinfo.setModifyTime(new Date());
		
		dto.put("lvPageinfo", lvPageinfo);
		this.doService("LvPageinfoService", "update", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.edit() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: befAdd 
     * @Description:  [跳转网站首页导航页面管理信息新增页面]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-26 上午10:25:49]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-26 上午10:25:49]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String befAdd(){
		return "befAdd";
	}
   /**
    * 
    * @Method: add 
    * @Description:  [新增网站首页导航页面管理信息]  
    * @Author:       [liaoxiongjian]     
    * @CreateDate:   [2012-7-26 上午10:26:33]   
    * @UpdateUser:   [liaoxiongjian]     
    * @UpdateDate:   [2012-7-26 上午10:26:33]   
    * @UpdateRemark: [说明本次修改内容]  
    * @return String
    * @throws
    */
	public String add(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.save() method begin*****");
		}
		lvPageinfo.setCode(CodeUtils.getCode());//code设置
		lvPageinfo.setCreateTime(new Date());   //创建时间
		dto.put("lvPageinfo", lvPageinfo);
		this.doService("LvPageinfoService", "save", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.save() method end*****");
		}
	    return AJAX; 	
	}
    /**
     * 
     * @Method: del 
     * @Description:  [删除网站首页导航页面管理信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-26 上午10:26:59]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-26 上午10:26:59]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.del() method begin*****");
		}
		dto.put("lvPageinfo", lvPageinfo);
		this.doService("LvPageinfoService", "delete", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.del() method end*****");
		}
		json.doNavTabTodo();
	    return AJAX;	
	}
    /**
     * 
     * @Method: delList 
     * @Description:  [批量删除网站首页导航页面管理信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-26 上午10:27:22]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-26 上午10:27:22]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String delList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvPageinfo=new LvPageinfo();
				lvPageinfo.setId(id);
				dto.put("lvPageinfo", lvPageinfo);
				//删除model
				this.doService("LvPageinfoService", "delete", dto);
				}
			}
		}
	
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.delList() method end*****");
		}
		json.doNavTabTodo();
		return AJAX;
	}
    /**
     * 
     * @Method: view 
     * @Description:  [查看网站首页导航页面管理信息详情]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-26 上午10:27:37]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-26 上午10:27:37]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.view() method begin*****");
		}
		dto.put("lvPageinfo", lvPageinfo);
		lvPageinfo=(LvPageinfo) this.doService("LvPageinfoService", "get", dto);
		this.getRequest().setAttribute("lvPageinfo", lvPageinfo);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvPageinfoAction.view() method end*****");
		}
		return VIEW;
	}
	
	public LvPageinfo getLvPageinfo() {
		return lvPageinfo;
	}
	public void setLvPageinfo(LvPageinfo lvPageinfo) {
		this.lvPageinfo = lvPageinfo;
	}
	
}
