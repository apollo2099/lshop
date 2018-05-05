package com.lshop.manage.blog.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.DwzJsonResponse;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.common.action.BaseManagerAction;

/** 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.action.LvBlogContentAction.java]  
 * @ClassName:    [LvBlogContentAction]   
 * @Description:  [华扬博客文章管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午03:07:22]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午03:07:22]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvBlogContentAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvBlogContentAction extends BaseManagerAction{
	
	private static final Log logger = LogFactory.getLog(LvBlogContentAction.class);
	private LvBlogContent lvBlogContent;
	private LvBlogType lvBlogType;
	private Pagination pagination;
	private Integer curPage = 1;
	private Integer pageSize = 20;// 页面大小
	private Integer id;
	private String type;
	private String title;
	private Short orderid;
	private String publishTime;
	@Resource
	private InitLinkDataService initLinkDataService;
	
    /**
     * 
     * @Method: doAdd 
     * @Description:  [跳转到新增博客文章页面(发布文章)]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午03:09:13]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午03:09:13]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String doAdd() throws ParseException{
		    if(logger.isInfoEnabled()){
			  logger.info("***** LvBlogContentAction.list() method begin*****");
		    }
		    BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
			lvBlogContent.setUserId(users.getId());
			lvBlogContent.setUserName(users.getUserName());
			if(ObjectUtils.isEmpty(lvBlogContent.getCreateTime())){
				lvBlogContent.setCreateTime(new Date());
			}
		    lvBlogContent.setCode(CodeUtils.getCode());
		    lvBlogContent.setIsRed((short)0);
		    lvBlogContent.setIsTop((short)0);
		    
		    
	        dto.put("model", lvBlogContent);
	        dto.put("page", page);
	        Dto result=(Dto) this.doService("LvBlogContentService", "add",dto);
	        lvBlogContent=(LvBlogContent) result.get("lvBlogContent");
	        
	        sendHtmlToWeb();
	        
	        sendHtmlToWeb();
	        if(logger.isInfoEnabled()){
				  logger.info("***** LvBlogContentAction.list() method end*****");
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
		String htmlPath = "/blog/blogInfo"+lvBlogContent.getId()+".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, lvBlogContent.getStoreId());
		htmlPath = "/blog.html";
		initLinkDataService.sendHtmlToWeb(htmlPath, lvBlogContent.getStoreId());
	}
	
    /**
     * 
     * @Method: list 
     * @Description:  [	查询博客文章管理列表]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午03:22:53]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午03:22:53]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String list(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("model", lvBlogContent);
		page=(Pagination) doService("LvBlogContentService", "list", dto);

		//查询博客类别列表信息
		dto.put("model", lvBlogType);
		List typeList=((Pagination)doService("LvBlogTypeService", "list", dto)).getList();
		this.getRequest().setAttribute("typeList", typeList);
		
		if(lvBlogContent!=null){
		   this.getRequest().setAttribute("tempType", this.lvBlogContent.getType());	
		}
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.list() method end*****");
		}	
		return "list";
	}

    /**
     * 
     * @Method: doUpdate 
     * @Description:  [修改博客文章信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午03:27:05]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午03:27:05]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String doUpdate() throws ParseException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.doUpdate() method begin*****");
		}
		//获取用户登录信息
		BaseUsers user =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		Integer userid=user.getId();
		String username=user.getUserName();
		if(ObjectUtils.isEmpty(lvBlogContent.getCreateTime())){
			lvBlogContent.setCreateTime(new Date());
		}
		lvBlogContent.setModifyTime(new Date());
		lvBlogContent.setUserName(username);
		lvBlogContent.setUserId(userid);
		//获取登录用户信息
	
		lvBlogContent.setModifyUserId(user.getId());
		lvBlogContent.setModifyUserName(user.getUserName());
		dto.put("model", lvBlogContent);
		//调用保存包括文章的service
		this.doService("LvBlogContentService", "update", dto);
		
		
        //执行单个页面静态化任务
		sendHtmlToWeb();
		json.doClose();
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.doUpdate() method end*****");
		}
		return AJAX;
	}
	
    /**
     * 
     * @Method: del 
     * @Description:  [删除博客文章信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午03:28:36]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午03:28:36]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String del(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.del() method begin*****");
		}
		dto.put("model", lvBlogContent);
		this.doService("LvBlogContentService", "delete", dto);
		json.doForward();
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除博客文章信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:29:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:29:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvBlogContent=new LvBlogContent();
				lvBlogContent.setId(id);
				dto.put("model", lvBlogContent);
				//删除model
				this.doService("LvBlogContentService", "delete", dto);
				}
			}
		}
		
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.delList() method end*****");
		}
		return AJAX;
	}
	
	
    /**
     * 
     * @Method: view 
     * @Description:  [查看博客详情信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午03:29:31]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午03:29:31]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** BlogLeaveServiceAction.view() method begin*****");
		}
		dto.put("model", lvBlogContent);
		lvBlogContent = (LvBlogContent)this.doService("LvBlogContentService", "get", dto);
		
		//查询博客类别列表信息
		dto.put("model", lvBlogType);
		dto.put("page", page);
		
		//查询博客类型信息
		List typeList=((Pagination)doService("LvBlogTypeService", "list", dto)).getList();
		this.getRequest().setAttribute("typeList", typeList);
		if (logger.isInfoEnabled()){
			logger.info("***** BlogLeaveServiceAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到博客编辑界面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:29:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:29:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** BlogLeaveServiceAction.befEdit() method begin*****");
		}
		//根据博客文章编号查看博客信息
		dto.put("model", lvBlogContent);
		lvBlogContent = (LvBlogContent)this.doService("LvBlogContentService", "get", dto);
		
		
		//查询博客类别列表信息
		dto.put("lvBlogType", lvBlogType);
		List<LvBlogType> typeList=(List<LvBlogType>) doService("LvBlogTypeService", "typelist", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BlogLeaveServiceAction.befEdit() method end*****");
		}
		return "edit";
	}
	
	/**
	 * 
	 * @Method: befAdd 
	 * @Description:  [跳转到新增博客页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:30:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:30:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befAdd()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.befAdd() method begin*****");
		}
//	    dto.put("page", page);
//		List typeList=((Pagination)doService("LvBlogTypeService", "list", dto)).getList();
//		this.getRequest().setAttribute("typeList", typeList);
//		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentAction.befAdd() method end*****");
		}
		return "add";
	}
	
	/**
	 * 
	 * @Method: toBlogType 
	 * @Description:  [根据店铺标志查询博客列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-3-27 下午06:07:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-3-27 下午06:07:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toBlogType(){
		PrintWriter out = null;
		if(lvBlogType!=null){
			dto.put("lvBlogType", lvBlogType);
			List<LvBlogType> list=(List<LvBlogType>) doService("LvBlogTypeService", "typelist", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvBlogType lvBlogType=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("typeName", lvBlogType.getType());
					map.put("typeId", lvBlogType.getId());
					listTmp.add(map);
					
				}
				mapTmp.put("listTmp", listTmp);
				try {
					response.setContentType("text/html;charset=UTF-8");   
					response.setCharacterEncoding("UTF-8");
				    out=this.getResponse().getWriter();
				    String jsonTmp = JSONObject.fromObject(mapTmp).toString();
				    logger.info("json_message"+jsonTmp);
					out.print(jsonTmp );
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		return null;
	}
	
	
	
	
	public LvBlogType getLvBlogType() {
		return lvBlogType;
	}
	public void setLvBlogType(LvBlogType lvBlogType) {
		this.lvBlogType = lvBlogType;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Short getOrderid() {
		return orderid;
	}
	public void setOrderid(Short orderid) {
		this.orderid = orderid;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public LvBlogContent getLvBlogContent() {
		return lvBlogContent;
	}
	public void setLvBlogContent(LvBlogContent lvBlogContent) {
		this.lvBlogContent = lvBlogContent;
	}
	
}
