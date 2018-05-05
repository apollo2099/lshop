package com.lshop.manage.lvProductType.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvAccount.action.LvAccountAction;

/**  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductType.action.LvProductTypeAction.java]  
 * @ClassName:    [LvProductTypeAction]   
 * @Description:  [产品类型管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-3 上午11:33:50]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-3 上午11:33:50]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvProductTypeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvProductTypeAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvProductTypeAction.class);
	private LvProductType lvProductType;
	
	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询产品类型列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:33:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:33:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProductType", lvProductType);
		page=(Pagination) this.doService("LvProductTypeService", "getList", dto);
		

    	//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.list() method end*****");
		}
		return LIST;
	}
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到修改产品类型页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:33:59]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:33:59]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.befEdit() method begin*****");
		}
		dto.put("lvProductType", lvProductType);
		lvProductType=(LvProductType) this.doService("LvProductTypeService", "get", dto);
		this.getRequest().setAttribute("lvProductType", lvProductType);
		

    	//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [修改产品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:34:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:34:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvProductType.setModifyUserId(users.getId());
		lvProductType.setModifyUserName(users.getUserName());
		lvProductType.setModifyTime(new Date());
		
		dto.put("lvProductType", lvProductType);
		Boolean isFlag=(Boolean) this.doService("LvProductTypeService", "update", dto);
		if(!isFlag){
			json.setMessage("该产品类型名称已经存在，请重新输入！");
			json.setStatusCode(300);
		}
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.edit() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: befSave 
	 * @Description:  [跳转到产品类型新增页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:34:09]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:34:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befAdd(){

    	//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		return "befAdd";
	}
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:34:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:34:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String add(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.save() method begin*****");
		}
		lvProductType.setCode(CodeUtils.getCode());//code设置
		lvProductType.setCreateTime(new Date());   //创建时间
		dto.put("lvProductType", lvProductType);
		Boolean isFlag=(Boolean) this.doService("LvProductTypeService", "save", dto);
		if(!isFlag){
			json.setMessage("该产品类型名称已经存在，请重新输入！");
			json.setStatusCode(300);
		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.save() method end*****");
		}
	    return AJAX; 	
	}
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除产品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:34:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:34:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.del() method begin*****");
		}
		dto.put("lvProductType", lvProductType);
		this.doService("LvProductTypeService", "delete", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.del() method end*****");
		}
		json.doNavTabTodo();
	    return AJAX;	
	}
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除产品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:34:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:34:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String delList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvProductType=new LvProductType();
				lvProductType.setId(id);
				dto.put("lvProductType", lvProductType);
				//删除model
				this.doService("LvProductTypeService", "delete", dto);
				}
			}
		}
	
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.delList() method end*****");
		}
		json.doNavTabTodo();
		return AJAX;
	}
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看产品类型详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:34:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:34:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.view() method begin*****");
		}
		dto.put("lvProductType", lvProductType);
		lvProductType=(LvProductType) this.doService("LvProductTypeService", "get", dto);
		this.getRequest().setAttribute("lvProductType", lvProductType);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductTypeAction.view() method end*****");
		}
		return VIEW;
	}
	
	
	public LvProductType getLvProductType() {
		return lvProductType;
	}
	public void setLvProductType(LvProductType lvProductType) {
		this.lvProductType = lvProductType;
	}
}
