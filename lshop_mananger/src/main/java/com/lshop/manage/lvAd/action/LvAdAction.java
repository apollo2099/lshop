package com.lshop.manage.lvAd.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvAd;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvPageinfo;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvPageinfo.action.LvPageinfoAction;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAd.action.LvAdAction.java]  
 * @ClassName:    [LvAdAction]   
 * @Description:  [广告信息管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-26 上午10:45:36]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-26 上午10:45:36]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvAdAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvAdAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvAdAction.class);
	private LvAd lvAd;
	
	 /**
	    * 
	    * @Method: list 
	    * @Description:  [分页查询广告信息列表信息]  
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
				  logger.info("***** lvAdAction.list() method begin*****");
			}
			dto.put("orderField", orderField);
			dto.put("orderDirection", orderDirection);
			dto.put("page",page);
			dto.put("lvAd", lvAd);
			page=(Pagination) this.doService("LvAdService", "getList", dto);
			
			//查询所有店铺信息
			List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
			this.getRequest().setAttribute("storeList", storeList);
			
			if(logger.isInfoEnabled()){
				  logger.info("***** lvAdAction.list() method end*****");
			}
			return LIST;
		}
	    /**
	     * 
	     * @Method: befEdit 
	     * @Description:  [跳转到广告信息信息页面]  
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
				  logger.info("***** lvAdAction.befEdit() method begin*****");
			}
			dto.put("lvAd", lvAd);
			lvAd=(LvAd) this.doService("LvAdService", "get", dto);
			this.getRequest().setAttribute("lvAd", lvAd);
			
			//查询所有店铺信息
			List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
			this.getRequest().setAttribute("storeList", storeList);
			if(logger.isInfoEnabled()){
				  logger.info("***** lvAdAction.befEdit() method end*****");
			}
			return "edit";
		}
	    /**
	     * 
	     * @Method: edit 
	     * @Description:  [修改广告信息信息]  
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
				  logger.info("***** lvAdAction.edit() method begin*****");
			}
			//获取登录用户信息
			BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
			lvAd.setModifyUserId(users.getId());
			lvAd.setModifyUserName(users.getUserName());
			lvAd.setModifyTime(new Date());
			
			dto.put("lvAd", lvAd);
			this.doService("LvAdService", "update", dto);
			if(logger.isInfoEnabled()){
				  logger.info("***** lvAdAction.edit() method end*****");
			}
			return AJAX;
		}
	    /**
	     * 
	     * @Method: befAdd 
	     * @Description:  [跳转广告信息信息新增页面]  
	     * @Author:       [liaoxiongjian]     
	     * @CreateDate:   [2012-7-26 上午10:25:49]   
	     * @UpdateUser:   [liaoxiongjian]     
	     * @UpdateDate:   [2012-7-26 上午10:25:49]   
	     * @UpdateRemark: [说明本次修改内容]  
	     * @return String
	     * @throws
	     */
		public String befAdd(){
			//查询所有店铺信息
			List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
			this.getRequest().setAttribute("storeList", storeList);
			return "befAdd";
		}
	   /**
	    * 
	    * @Method: add 
	    * @Description:  [新增广告信息信息]  
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
				  logger.info("***** lvAdAction.save() method begin*****");
			}
			lvAd.setCode(CodeUtils.getCode());//code设置
			lvAd.setCreateTime(new Date());   //创建时间
			dto.put("lvAd", lvAd);
			this.doService("LvAdService", "save", dto);
			
			if(logger.isInfoEnabled()){
				  logger.info("***** lvAdAction.save() method end*****");
			}
		    return AJAX; 	
		}
	    /**
	     * 
	     * @Method: del 
	     * @Description:  [删除广告信息信息]  
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
				  logger.info("***** lvAdAction.del() method begin*****");
			}
			dto.put("lvAd", lvAd);
			this.doService("LvAdService", "delete", dto);
			json.doNavTabTodo();
			if(logger.isInfoEnabled()){
				  logger.info("***** lvAdAction.del() method end*****");
			}
			
		    return AJAX;	
		}
	    /**
	     * 
	     * @Method: delList 
	     * @Description:  [批量删除广告信息信息]  
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
				  logger.info("***** lvAdAction.delList() method begin*****");
			}
			if (ids != null && ids.length()> 0 )
			{
				String[] temp_ids= ids.split(",");
				for( int i=0; i<temp_ids.length; i++)
				{
					if (temp_ids[i].length()>0)
					{
					int id=Integer.parseInt(temp_ids[i].trim());
					lvAd=new LvAd();
					lvAd.setId(id);
					dto.put("lvAd", lvAd);
					//删除model
					this.doService("LvAdService", "delete", dto);
					}
				}
			}
		
			if(logger.isInfoEnabled()){
				  logger.info("***** lvAdAction.delList() method end*****");
			}
			json.doNavTabTodo();
			return AJAX;
		}
	    /**
	     * 
	     * @Method: view 
	     * @Description:  [查看广告信息信息详情]  
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
				  logger.info("***** lvAdAction.view() method begin*****");
			}
			dto.put("lvAd", lvAd);
			lvAd=(LvAd) this.doService("LvAdService", "get", dto);
			this.getRequest().setAttribute("lvAd", lvAd);
			
			//查询所有店铺信息
			List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
			this.getRequest().setAttribute("storeList", storeList);
			if(logger.isInfoEnabled()){
				  logger.info("***** lvAdAction.view() method end*****");
			}
			return VIEW;
		}
		
		/**
		 * 
		 * @Method: toStore 
		 * @Description:  [根据广告商家标志查询对应的商家信息]  
		 * @Author:       [liaoxiongjian]     
		 * @CreateDate:   [2013-1-11 上午09:41:53]   
		 * @UpdateUser:   [liaoxiongjian]     
		 * @UpdateDate:   [2013-1-11 上午09:41:53]   
		 * @UpdateRemark: [说明本次修改内容]  
		 * @return 
		 * @return String
		 */
		public String toStore(){
			PrintWriter out = null;
			dto.put("storeFlag", lvAd.getStoreId());
			LvStore lvStore=(LvStore) this.doService("LvStoreService", "findStore", dto);
			this.getRequest().setAttribute("lvStore", lvStore);
			
			try {
			    out=this.getResponse().getWriter();
			    String jsonTmp = JSONObject.fromObject(lvStore).toString();
			    logger.info("json_message"+jsonTmp);
				out.print(jsonTmp );
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public LvAd getLvAd() {
			return lvAd;
		}
		public void setLvAd(LvAd lvAd) {
			this.lvAd = lvAd;
		}
		
}
