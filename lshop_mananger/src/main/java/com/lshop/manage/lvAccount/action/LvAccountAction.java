package com.lshop.manage.lvAccount.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.cryption.MD5;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.cache.LvAreaCache;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountInfo;
import com.lshop.common.pojo.user.UnifiedUser;
import com.lshop.common.util.CommonUtil;
import com.lshop.common.util.Constants;
import com.lshop.manage.blog.action.LvBlogContentAction;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAccount.action.LvAccountAction.java]  
 * @ClassName:    [LvAccountAction]   
 * @Description:  [用户信息管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-2 上午10:56:26]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-2 上午10:56:26]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0] 
 *
 */
@Controller("LvAccountAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvAccountAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvAccountAction.class);
	private LvAccount lvAccount;
	private LvAccountInfo lvAccountInfo;
	private UnifiedUser unifiedUser;

	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询用户列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 上午11:02:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 上午11:02:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.list() method begin*****");
		}
		dto.put("page",page);
		dto.put("unifiedUser", unifiedUser);
		
		// 保存查询条件，方便导出
		request.getSession().setAttribute("SEARCH_CONDITION", dto);
		
		dto = (Dto) this.doService("LvAccountService", "getList", dto);
		int status = dto.getAsInteger(Constants.STATUS_KEY);
		if (status == Constants.STATUS_CODE_SUCCESS) {
			page = dto.getDefaultPage();
			return LIST;
		} else {
			json.setStatusCode(300);
			json.setMessage(dto.getAsString(Constants.MESSAGE_KEY));
			return AJAX;
		}
	}
	
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到编辑用户信息页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 上午11:04:49]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 上午11:04:49]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.befEdit() method begin*****");
		}
		dto.put("unifiedUser", unifiedUser);
		dto = (Dto) this.doService("LvAccountService", "get", dto);
		
		this.lvAccount = (LvAccount) dto.get("lvAccount");
		this.unifiedUser = (UnifiedUser) dto.get("unifiedUser");
		request.setAttribute("areaList", LvAreaCache.getCountryList());
    	
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.befEdit() method end*****");
		}
		return  "edit";
	}
	
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [编辑用户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 上午11:07:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 上午11:07:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.edit() method begin*****");
		}
		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvAccount = new LvAccount();
		lvAccount.setModifyUserId(users.getId());
		lvAccount.setModifyUserName(users.getUserName());
		lvAccount.setModifyTime(new Date());
		lvAccount.setCode(unifiedUser.getCode());
		
		String oldpwd = request.getParameter("oldpwd");
		if (unifiedUser.getPassword().equals(oldpwd)) {	// 没有修改过密码
			unifiedUser.setPassword(null);
		}
		
		//修改用户账户信息
		dto.put("lvAccount", lvAccount);
		dto.put("unifiedUser", unifiedUser);
		
		dto = (Dto) this.doService("LvAccountService", "update", dto);
		int status = dto.getAsInteger(Constants.STATUS_KEY);
		if (status == Constants.STATUS_CODE_FIAL) {
			json.setStatusCode(300);
			json.setMessage(dto.getAsString(Constants.MESSAGE_KEY));
		}
		return AJAX;
	}
	

	public String befSave(){
		return "add";
	}
	
	public String save(){
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除用户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 上午11:09:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 上午11:09:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.del() method begin*****");
		}
		dto.put("ids",ids);
		this.doService("LvAccountService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.del() method end*****");
		}
		return AJAX;
	}
	

	public String deleteByEmail(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.deleteByEmail() method begin*****");
		}
		dto.put("ids",ids);
		dto.put("lvAccount", lvAccount);
		this.doService("LvAccountService", "deleteByEmail", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.deleteByEmail() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看用户账户信息和用户登录详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午04:07:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午04:07:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.view() method begin*****");
		}
		dto.put("unifiedUser", unifiedUser);
		dto = (Dto) this.doService("LvAccountService", "get", dto);
		int status = dto.getAsInteger(Constants.STATUS_KEY);
		if (status == Constants.STATUS_CODE_SUCCESS) {
			Map item = (Map) dto.get("item");
			this.unifiedUser = (UnifiedUser) dto.get("unifiedUser");
			this.lvAccount = (LvAccount) dto.get("lvAccount");
			return VIEW;
		} else {
			json.setStatusCode(300);
			json.setMessage(dto.getAsString(Constants.MESSAGE_KEY));
			return AJAX;
		}
	}
	
	public String findUserOrder()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserMngAction.findUserOrder() method begin*****");
		}
		LvOrder lvOrder =new LvOrder();
		lvOrder.setUserEmail(lvAccount.getEmail());
		dto.put("model", lvOrder);
		dto.put("request", this.getRequest());
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		page = (Pagination) this.doService("LvOrderService", "getAll",
				dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserMngAction.findUserOrder() method end*****");
		}
		return "orderlist";
	}
	
	public String findUserCoupon()throws ActionException{
		dto.put("uid", lvAccount.getId());
		dto.put("page", this.page);
		this.page=(Pagination)this.doService("LvUserService", "findUserCoupon", dto);
		return "usercoupon";
	}
	
	// 导出excel
	public String export() throws ActionException {
		String codes = request.getParameter("ids");
		BaseUsers user = (BaseUsers) getSession().getAttribute("USER");

		String path = CommonUtil.getRealPath() + Constants.TEMP_FILE_PATH
				+ user.getUserCode() + File.separatorChar;
		CommonUtil.checkCreateFiles(path);
		path += "用户账户信息" + ".xls";			
		path = CommonUtil.rehabPath(path);

		dto = (Dto) request.getSession().getAttribute("SEARCH_CONDITION");
		dto.put("path", path);
		dto.put("codes", codes);
		this.doService("LvAccountService", "exportExcel", dto);
		
		request.setAttribute("path", path);		// 临时文件路径
		request.setAttribute("delete", true);	// 导出完成后删除临时文件
		return "download";
	}
	
	public LvAccount getLvAccount() {
		return lvAccount;
	}

	public void setLvAccount(LvAccount lvAccount) {
		this.lvAccount = lvAccount;
	}
	public LvAccountInfo getLvAccountInfo() {
		return lvAccountInfo;
	}

	public void setLvAccountInfo(LvAccountInfo lvAccountInfo) {
		this.lvAccountInfo = lvAccountInfo;
	}
	public UnifiedUser getUnifiedUser() {
		return unifiedUser;
	}

	public void setUnifiedUser(UnifiedUser unifiedUser) {
		this.unifiedUser = unifiedUser;
	}

}
