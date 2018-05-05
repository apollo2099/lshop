package com.lshop.manage.blog.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvBlogTags;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.action.LvBlogTagsAction.java]  
 * @ClassName:    [LvBlogTagsAction]   
 * @Description:  [Blog标签管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午04:19:29]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午04:19:29]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvBlogTagsAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvBlogTagsAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvBlogTagsAction.class);
	private LvBlogTags lvBlogTags;

	
    /**
     * 
     * @Method: list 
     * @Description:  [	分页查询标签分类信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:20:46]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:20:46]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("model", lvBlogTags);
		page = (Pagination)this.doService("LvBlogTagsService", "list", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 
	 * @Method: select 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:22:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:22:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String select(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.select() method begin*****");
		}
		dto.put("page", page);
		dto.put("model", lvBlogTags);
		page = (Pagination)this.doService("LvBlogTagsService", "list", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.select() method end*****");
		}
		return "select";
	}
	
    /**
     * 
     * @Method: del 
     * @Description:  [删除标签分类信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:23:17]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:23:17]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.del() method begin*****");
		}
	    dto.put("model", lvBlogTags);
	    this.doService("LvBlogTagsService", "delete", dto);
	    
	    json.setStatusCode(200);
		json.setCallbackType(null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.del() method begin*****");
		}
	    return AJAX;	
	}
	
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除标签分类信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:24:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:24:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvBlogTags=new LvBlogTags();
				lvBlogTags.setId(id);
				dto.put("model", lvBlogTags);
				//删除model
				this.doService("LvBlogTagsService", "delete", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.delList() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到标签分类修改页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:37:20]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:37:20]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.befEdit() method begin*****");
		}
		dto.put("model", lvBlogTags);
		lvBlogTags = (LvBlogTags)this.doService("LvBlogTagsService", "get", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.befEdit() method end*****");
		}
		return "edit";
	}
	
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [修改标签分类信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:38:10]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:38:10]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.edit() method begin*****");
		}
		dto.put("model", lvBlogTags);
		this.doService("LvBlogTagsService", "update", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.edit() method end*****");
		}
		return AJAX;	
	}
	
	/**
	 * 
	 * @Method: befSave 
	 * @Description:  [跳转到新增标签分类页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:39:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:39:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befSave()throws ActionException{
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "befSave";
	}
	
	/**
	 * 
	 * @Method: save 
	 * @Description:  [保存标签分类信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:39:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:39:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.save() method begin*****");
		}
		dto.put("model", lvBlogTags);
		Boolean flag=(Boolean) this.doService("LvBlogTagsService", "save", dto);
		if(!flag){
			this.json.setStatusCode(300);
			this.json.setMessage("Blog标签已经存在！");
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsAction.save() method end*****");
		}
		return AJAX;	
	}
	
	
	
	
	public LvBlogTags getLvBlogTags() {
		return lvBlogTags;
	}
	public void setLvBlogTags(LvBlogTags lvBlogTags) {
		this.lvBlogTags = lvBlogTags;
	}

	
}
