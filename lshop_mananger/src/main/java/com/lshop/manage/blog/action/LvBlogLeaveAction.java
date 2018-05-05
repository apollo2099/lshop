package com.lshop.manage.blog.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvBlogLeave;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.action.LvBlogLeaveAction.java]  
 * @ClassName:    [LvBlogLeaveAction]   
 * @Description:  [后台管理员查询博客文章回复和管理评论信息-action业务层] 
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午03:54:47]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午03:54:47]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvBlogLeaveAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvBlogLeaveAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvBlogLeaveAction.class);
	private LvBlogLeave lvBlogLeave;
	private Pagination pagination;
	private Integer curPage = 1;
	private Integer pageSize = 20;// 页面大小
	
	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询所有的博客文章回复信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:56:29]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:56:29]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("model", lvBlogLeave);
		page = (Pagination)this.doService("LvBlogLeaveService", "list", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.list() method end*****");
		}
		return LIST;	
	}
	
    /**
     * 
     * @Method: del 
     * @Description:  [	删除博客文章回复]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午03:58:24]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午03:58:24]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String del(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.del() method begin*****");
		}
		dto.put("model", lvBlogLeave);
		this.doService("LvBlogLeaveService", "delete", dto);

		json.setStatusCode(200);
		json.setCallbackType(null);
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: deleteReplyLook 
	 * @Description:  [删除管理员回复的评论]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:58:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:58:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String deleteReplyLook(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.deleteReplyLook() method begin*****");
		}
		dto.put("model", lvBlogLeave);
		this.doService("LvBlogLeaveService", "delete", dto);
		//json.doForward();
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.deleteReplyLook() method end*****");
		}
		return AJAX;
	}
	
    /**
     * 
     * @Method: replyLook 
     * @Description:  [查看管理员回复信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午03:59:59]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午03:59:59]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String replyLook(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.replyLook() method begin*****");
		}
		dto.put("model", lvBlogLeave);
		dto.put("page", page);
		dto.put("request", this.getRequest());
		page = (Pagination)this.doService("LvBlogLeaveService", "replyLook", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.replyLook() method end*****");
		}
		return "replyLook";
	}
	//查看文章回复信息
	public String view(){
	
		return "view";
	}
	
	/**
	 * 
	 * @Method: doReply 
	 * @Description:  [管理员针对用户的文章评论进行回复操作]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:00:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:00:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String doReply() throws ParseException{	
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.doReply() method begin*****");
		}
		//查询用户登录信息
	    BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		String publishTime=this.getRequest().getParameter("publishTime");
		if(publishTime!=null&&!"".equals(publishTime.trim())){
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			lvBlogLeave.setCreateTime(format.parse(publishTime));
		}else {
			lvBlogLeave.setCreateTime(new Date());
		}
		
		lvBlogLeave.setUserId(users.getId());
		lvBlogLeave.setUserName(users.getUserName());
		dto.put("model", lvBlogLeave);
		this.doService("LvBlogLeaveService", "add", dto);
		json.doForward();
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveAction.doReply() method begin*****");
		}
		return AJAX;
	}
	
	
	public LvBlogLeave getLvBlogLeave() {
		return lvBlogLeave;
	}

	public void setLvBlogLeave(LvBlogLeave lvBlogLeave) {
		this.lvBlogLeave = lvBlogLeave;
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
	
}
