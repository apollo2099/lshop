package com.lshop.manage.lvProductProperty.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.CodeUtils;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductProperty.action.LvProductPropertyAction.java]  
 * @ClassName:    [LvProductPropertyAction]   
 * @Description:  [产品扩展属性管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-4 上午11:56:05]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-4 上午11:56:05]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvProductPropertyAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvProductPropertyAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvProductPropertyAction.class);
	private LvProductProperty lvProductProperty;
	@Resource
	private InitLinkDataService initLinkDataService;
    /**
     * 
     * @Method: list 
     * @Description:  [分页查询产品扩展属性列表]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-4 上午11:56:55]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-4 上午11:56:55]   
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
		dto.put("lvProductProperty", lvProductProperty);
		//产品扩展属性分页集合对象
		page=(Pagination) this.doService("LvProductPropertyService", "getList", dto);
		
		//查询产品列表信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到编辑产品扩展属性页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.befEdit() method begin*****");
		}
		//查询产品扩展属性信息
		dto.put("lvProductProperty", lvProductProperty);
		lvProductProperty=(LvProductProperty) this.doService("LvProductPropertyService", "get", dto);
		this.getRequest().setAttribute("lvProductProperty", lvProductProperty);
		
		//查询产品列表信息
		LvProduct lvProduct=new LvProduct();
		lvProduct.setStoreId(lvProductProperty.getStoreId());
		dto.put("lvProduct", lvProduct);
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllProduct", dto);
		this.getRequest().setAttribute("productList", list);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.befEdit() method end*****");
		}
		return "edit";
	}
	
	public String befShopEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.befShopEdit() method begin*****");
		}
		//查询产品扩展属性信息
		dto.put("lvProductProperty", lvProductProperty);
		lvProductProperty=(LvProductProperty) this.doService("LvProductPropertyService", "get", dto);
		this.getRequest().setAttribute("lvProductProperty", lvProductProperty);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.befShopEdit() method end*****");
		}
		return "befShopEdit";
		
	}
	
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [修改产品扩展属性]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:22]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvProductProperty.setModifyUserId(users.getId());
		lvProductProperty.setModifyUserName(users.getUserName());
		lvProductProperty.setModifyTime(new Date());
		dto.put("lvProductProperty", lvProductProperty);
		this.doService("LvProductPropertyService", "update", dto);
		//单个页面静态化任务
		this.sendHtmlToWeb(lvProductProperty);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.edit() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: befAdd 
	 * @Description:  [跳转到产品扩展属性新增页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befAdd(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.befAdd() method begin*****");
		}
		//查询产品列表信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllProduct", dto);
		this.getRequest().setAttribute("productList", list);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.befAdd() method add*****");
		}
		return "befAdd";
	}
	
	public String befShopAdd(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.befAdd() method begin*****");
		}
		dto.put("lvProductProperty", lvProductProperty);
		this.getRequest().setAttribute("lvProductProperty", lvProductProperty);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.befAdd() method add*****");
		}
		return "befShopAdd";
	}
	/**
	 * 
	 * @Method: add 
	 * @Description:  [新增产品扩展属性]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String add(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.add() method begin*****");
		}
		lvProductProperty.setCode(CodeUtils.getCode());//code设置
		lvProductProperty.setCreateTime(new Date());   //创建时间
		dto.put("lvProductProperty", lvProductProperty);
		this.doService("LvProductPropertyService", "save", dto);
		//单个页面静态化任务
		this.sendHtmlToWeb(lvProductProperty);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.add() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看产品扩展属性详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.view() method begin*****");
		}
		//查询产品扩展属性信息
		dto.put("lvProductProperty", lvProductProperty);
		lvProductProperty=(LvProductProperty) this.doService("LvProductPropertyService", "get", dto);
		this.getRequest().setAttribute("lvProductProperty", lvProductProperty);
		
		//查询产品列表信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.view() method end*****");
		}
		return "view";
	}
	
	public String shopView(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.view() method begin*****");
		}
		//查询产品扩展属性信息
		dto.put("lvProductProperty", lvProductProperty);
		lvProductProperty=(LvProductProperty) this.doService("LvProductPropertyService", "get", dto);
		this.getRequest().setAttribute("lvProductProperty", lvProductProperty);
		return "shopView";
	}
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除产品扩展属性]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:41]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.del() method begin*****");
		}
		//获取原扩展页，日志截取用
		dto.put("lvProductProperty", lvProductProperty);
		LvProductProperty oldProductProperty = (LvProductProperty)this.doService("LvProductPropertyService", "get", dto);
		request.setAttribute("propertyTitle", oldProductProperty.getTitle());
		request.setAttribute("productCode", oldProductProperty.getProductCode());
		
		//删除原扩展页
		dto.put("lvProductProperty", lvProductProperty);
		this.doService("LvProductPropertyService", "delete", dto);
		json.doNavTabTodo();
		
		//单个页面静态化任务
		this.sendHtmlToWeb(oldProductProperty);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.del() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除产品扩展属性]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String delList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvProductProperty=new LvProductProperty();
				lvProductProperty.setId(id);
				dto.put("lvProductProperty", lvProductProperty);
				//删除model
				this.doService("LvProductPropertyService", "delete", dto);
				}
			}
		}
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductPropertyAction.delList() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: sendHtmlToWeb 
	 * @Description:  [执行单个页面静态化任务]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-9-29 上午11:46:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-9-29 上午11:46:44]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	private void sendHtmlToWeb(LvProductProperty productProperty) {
		// 执行单个页面静态化任务
		String htmlPath = "/products/" + productProperty.getProductCode() + ".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, productProperty.getStoreId());
	}
	
	public LvProductProperty getLvProductProperty() {
		return lvProductProperty;
	}

	public void setLvProductProperty(LvProductProperty lvProductProperty) {
		this.lvProductProperty = lvProductProperty;
	}

}
