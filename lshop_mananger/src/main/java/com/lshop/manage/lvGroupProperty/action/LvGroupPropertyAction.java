package com.lshop.manage.lvGroupProperty.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvProductProperty.action.LvProductPropertyAction;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvGroupProperty.action.LvGroupPropertyAction.java]  
 * @ClassName:    [LvGroupPropertyAction]   
 * @Description:  [团购扩展属性管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-23 下午04:57:05]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-23 下午04:57:05]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvGroupPropertyAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvGroupPropertyAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvGroupPropertyAction.class);
	private LvGroupProperty lvGroupProperty;
	


	/**
     * 
     * @Method: list 
     * @Description:  [分页查询团购扩展属性列表]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午05:01:18]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午05:01:18]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvGroupProperty", lvGroupProperty);
		//产品扩展属性分页集合对象
		page=(Pagination) this.doService("LvGroupPropertyService", "getList", dto);
		
		//查询团购列表信息
		List<LvGroupBuy> list=(List<LvGroupBuy>) this.doService("LvGroupBuyService", "getAll", dto);
		this.getRequest().setAttribute("groupList", list);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.list() method end*****");
		}
		return LIST;
	}
	
    /**
     * 
     * @Method: befEdit 
     * @Description:  [跳转到编辑团购扩展属性页面]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午05:02:30]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午05:02:30]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.befEdit() method begin*****");
		}
		//查询产品扩展属性信息
		dto.put("lvGroupProperty", lvGroupProperty);
		lvGroupProperty=(LvGroupProperty) this.doService("LvGroupPropertyService", "get", dto);
		this.getRequest().setAttribute("lvGroupProperty", lvGroupProperty);
		
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.befEdit() method end*****");
		}
		return "edit";
	}
	
    /**
     * 
     * @Method: edit 
     * @Description:  [修改团购扩展属性]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午05:03:07]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午05:03:07]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvGroupProperty.setModifyUserId(users.getId());
		lvGroupProperty.setModifyUserName(users.getUserName());
		lvGroupProperty.setModifyTime(new Date());
		dto.put("lvGroupProperty", lvGroupProperty);
		this.doService("LvGroupPropertyService", "update", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.edit() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: befAdd 
     * @Description:  [跳转到团购扩展属性新增页面]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午05:03:38]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午05:03:38]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String befAdd(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.befAdd() method begin*****");
		}

		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.befAdd() method add*****");
		}
		return "befAdd";
	}
    /**
     * 
     * @Method: add 
     * @Description:  [新增团购扩展属性]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午05:04:06]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午05:04:06]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String add(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.add() method begin*****");
		}
		lvGroupProperty.setCode(CodeUtils.getCode());//code设置
		lvGroupProperty.setCreateTime(new Date());   //创建时间
		dto.put("lvGroupProperty", lvGroupProperty);
		this.doService("LvGroupPropertyService", "save", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.add() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: view 
     * @Description:  [查看团购扩展属性详情]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午05:04:36]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午05:04:36]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.view() method begin*****");
		}
		//查询产品扩展属性信息
		dto.put("lvGroupProperty", lvGroupProperty);
		lvGroupProperty=(LvGroupProperty) this.doService("LvGroupPropertyService", "get", dto);
		this.getRequest().setAttribute("lvGroupProperty", lvGroupProperty);
		
		//查询团购列表信息
		List<LvGroupBuy> list=(List<LvGroupBuy>) this.doService("LvGroupBuyService", "getAll", dto);
		this.getRequest().setAttribute("groupList", list);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.view() method end*****");
		}
		return "view";
	}
   /**
    * 
    * @Method: del 
    * @Description:  [删除团购扩展属性]  
    * @Author:       [liaoxiongjian]     
    * @CreateDate:   [2012-7-23 下午05:05:07]   
    * @UpdateUser:   [liaoxiongjian]     
    * @UpdateDate:   [2012-7-23 下午05:05:07]   
    * @UpdateRemark: [说明本次修改内容]  
    * @return String
    * @throws
    */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.del() method begin*****");
		}
		dto.put("lvGroupProperty", lvGroupProperty);
		this.doService("LvGroupPropertyService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.del() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: delList 
     * @Description:  [批量删除团购扩展属性]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午05:05:36]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午05:05:36]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String delList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvGroupProperty=new LvGroupProperty();
				lvGroupProperty.setId(id);
				dto.put("lvGroupProperty", lvGroupProperty);
				//删除model
				this.doService("LvGroupPropertyService", "delete", dto);
				}
			}
		}
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupPropertyAction.delList() method end*****");
		}
		return AJAX;
	}
	
    public LvGroupProperty getLvGroupProperty() {
		return lvGroupProperty;
	}

	public void setLvGroupProperty(LvGroupProperty lvGroupProperty) {
		this.lvGroupProperty = lvGroupProperty;
	}
}
