package com.lshop.manage.blog.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.action.LvBlogTypeAction.java]  
 * @ClassName:    [LvBlogTypeAction]   
 * @Description:  [博客类型管理-action业务层] 
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午02:37:44]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午02:37:44]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvBlogTypeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvBlogTypeAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvBlogTypeAction.class);
	private LvBlogType lvBlogType;
	@Resource
	private InitLinkDataService initLinkDataService;
	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询博客类型列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:43:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:43:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
	
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("model", lvBlogType);
		page = (Pagination)this.doService("LvBlogTypeService", "list", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
    	if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.list() method end*****");
		}
		return LIST;	
	}
	
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [修改博客类型]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:44:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:44:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.edit() method begin*****");
		}
		dto.put("model", lvBlogType);
		lvBlogType = (LvBlogType)this.doService("LvBlogTypeService", "update", dto);
		
		this.sendHtmlToWeb();
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除博客类型]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:45:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:45:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.del() method begin*****");
		}
		dto.put("model", lvBlogType);
		this.doService("LvBlogTypeService", "delete", dto);
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除博客类型]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:46:00]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:46:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvBlogType=new LvBlogType();
				lvBlogType.setId(id);
				dto.put("model", lvBlogType);
				//删除model
				this.doService("LvBlogTypeService", "delete", dto);
				}
			}
		}
		
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.delList() method end*****");
		}
		return AJAX;
	}
	

	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到编辑博客类型页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:46:23]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:46:23]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvBlogType);
		lvBlogType = (LvBlogType)this.doService("LvBlogTypeService", "get", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeMngAction.befEdit() method end*****");
		}
		return "edit";
	}
	
	
	/**
	 * 
	 * @Method: befSave 
	 * @Description:  [跳转到新增博客类型页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:47:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:47:46]   
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
     * @Description:  [一句话描述该类的功能]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午02:29:36]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午02:29:36]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.save() method begin*****");
		}
		dto.put("model", lvBlogType);
		Dto result=(Dto) this.doService("LvBlogTypeService", "save", dto);
		Boolean flag=(Boolean) result.get("flag");
		if(!flag){
			this.json.setStatusCode(300);
			this.json.setMessage("璇ュ崥瀹㈢被鍒凡缁忓瓨鍦紒");
		}else{
			lvBlogType=(LvBlogType) result.get("lvBlogType");
			this.sendHtmlToWeb();
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeAction.save() method begin*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: sendHtmlToWeb 
	 * @Description:  [执行单个页面静态化任务]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-9-29 上午11:33:37]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-9-29 上午11:33:37]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	private void sendHtmlToWeb() {
		String htmlPath = "/blog/blogType"+lvBlogType.getId()+".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, lvBlogType.getStoreId());
		htmlPath = "/blog.html";
		initLinkDataService.sendHtmlToWeb(htmlPath, lvBlogType.getStoreId());
	}
	
	
	public LvBlogType getLvBlogType() {
		return lvBlogType;
	}

	public void setLvBlogType(LvBlogType lvBlogType) {
		this.lvBlogType = lvBlogType;
	}

	public static Log getLogger() {
		return logger;
	}
}
