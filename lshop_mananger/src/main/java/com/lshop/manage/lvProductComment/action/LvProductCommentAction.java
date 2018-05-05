package com.lshop.manage.lvProductComment.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvProduct.action.LvProductAction;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductComment.action.LvProductCommentAction.java]  
 * @ClassName:    [LvProductCommentAction]   
 * @Description:  [评论管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-26 下午06:06:40]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-26 下午06:06:40]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvProductCommentAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvProductCommentAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvProductCommentAction.class);
	private LvProductComment lvProductComment;
	private File img;//评论图片
	private String imgFileName;//评论图片名称


	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询评论信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:06:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:06:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProductComment", lvProductComment);
		page=(Pagination) this.doService("LvProductCommentService", "getList", dto);
		
		//查询所有的产品信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 
	 * @Method: befAdd 
	 * @Description:  [跳转到添加系统评论页面（假评）]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:06:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:06:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befAdd(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.list() method begin*****");
		}
//		//查询所有的产品信息
//		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
//		this.getRequest().setAttribute("productList", list);
		//查询所以国家区域信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
    	
    	//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		return "befAdd";
	}
	
	/**
	 * 
	 * @Method: add 
	 * @Description:  [添加系统评论页面（假评）]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:06:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:06:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String add(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.add() method begin*****");
		}
		//评论图片主图片上传
		if(img!=null){			
			dto.put("dir", "comment");
			dto.put("storeFlag", lvProductComment.getStoreId());  
			dto.put("filedata", img);
			dto.put("filedataFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String imgPath=(String) this.doService("UploadService", "uploadImg", dto);
			lvProductComment.setCommentImages(imgPath);
		}
		
		lvProductComment.setIsDelete(Short.parseShort("0"));//设置为未删除
		lvProductComment.setCode(CodeUtils.getCode());  //code设置
//		lvProductComment.setCreateTime(new Date());     //创建时间
		//根据评分判断评论等级(评论等级 1=>差评,2=>中评,3=>好评)
		if(ObjectUtils.isNotEmpty(lvProductComment.getScore())){
			if(lvProductComment.getScore()<3){
				lvProductComment.setGrade(Short.parseShort("1"));
			}else if(lvProductComment.getScore()==3){
				lvProductComment.setGrade(Short.parseShort("2"));
			}else if(lvProductComment.getScore()>3){
				lvProductComment.setGrade(Short.parseShort("3"));
			}
		}
		
		dto.put("lvProductComment", lvProductComment);
		this.doService("LvProductCommentService", "save", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.add() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看评论详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:06:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:06:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.view() method begin*****");
		}
		dto.put("lvProductComment", lvProductComment);
		lvProductComment=(LvProductComment) this.doService("LvProductCommentService", "get", dto);
		this.getRequest().setAttribute("lvProductComment", lvProductComment);
		
		//查询所有的产品信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		
		//查询所以国家区域信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
    	
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.view() method end*****");
		}
		return VIEW;
	}
	

	/**
	 * 
	 * @Method: audit 
	 * @Description:  [评论信息审核(支持批量)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-27 下午02:30:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-27 下午02:30:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String audit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.audit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
	    if(lvProductComment==null){
	    	lvProductComment=new LvProductComment();
	    }	
		lvProductComment.setModifyUserId(users.getId());
		lvProductComment.setModifyUserName(users.getUserName());
		lvProductComment.setModifyTime(new Date());
		
		dto.put("ids", ids);
		dto.put("lvProductComment", lvProductComment);
		this.doService("LvProductCommentService", "updateAudit", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.audit() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除评论信息(或者批量)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-27 下午02:30:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-27 下午02:30:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.del() method begin*****");
		}
		dto.put("ids", ids);
		dto.put("lvProductComment", lvProductComment);
		this.doService("LvProductCommentService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.del() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: deleteReply 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-25 下午04:35:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-25 下午04:35:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String deleteReply(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.deleteReply() method begin*****");
		}
		dto.put("lvProductComment", lvProductComment);
		this.doService("LvProductCommentService", "deleteReply", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.deleteReply() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: toReply 
	 * @Description:  [跳转到评论回复页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-27 下午02:43:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-27 下午02:43:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String toReply(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.toReply() method begin*****");
		}
		//查询当前评论信息
		dto.put("lvProductComment", lvProductComment);
		lvProductComment=(LvProductComment) this.doService("LvProductCommentService", "get", dto);
		this.getRequest().setAttribute("lvProductComment", lvProductComment);
		
		//查询管理员回复信息toReply
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProductComment", lvProductComment);
		page=(Pagination) this.doService("LvProductCommentService", "getReplyList", dto);
		
		//查询所有的产品信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		
    	if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.toReply() method end*****");
		}
    	

		return "reply";
	}
	
	/**
	 * 
	 * @Method: reply 
	 * @Description:  [订单评论信息回复]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-27 下午02:40:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-27 下午02:40:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String reply(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.reply() method begin*****");
		}
		//获取登录用户信息
		lvProductComment.setCode(CodeUtils.getCode());
		lvProductComment.setCreateTime(new Date());
		lvProductComment.setReplyId(lvProductComment.getId());
		
		dto.put("lvProductComment", lvProductComment);
		this.doService("LvProductCommentService", "save", dto);
		json.doForward();
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommentAction.reply() method end*****");
		}
		return AJAX;
	}

	
	public LvProductComment getLvProductComment() {
		return lvProductComment;
	}

	public void setLvProductComment(LvProductComment lvProductComment) {
		this.lvProductComment = lvProductComment;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	
	
}
