package com.lshop.manage.lvGroupBuy.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvGroupProperty.action.LvGroupPropertyAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvGroupBuy.action.LvGroupBuyAction.java]  
 * @ClassName:    [LvGroupBuyAction]   
 * @Description:  [团购信息管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-23 下午05:20:04]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-23 下午05:20:04]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvGroupBuyAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvGroupBuyAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvGroupBuyAction.class);
	private LvGroupBuy lvGroupBuy;
	
	private File img;//团购图片
	private String imgFileName;//团购图片名称

	/**
     * 
     * @Method: list 
     * @Description:  [分页查询团购信息列表]  
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
			  logger.info("***** LvGroupBuyAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvGroupBuy", lvGroupBuy);
		//产品信息分页集合对象
		page=(Pagination) this.doService("LvGroupBuyService", "getList", dto);
		
		//查询产品列表信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupBuyAction.list() method end*****");
		}
		return LIST;
	}
	
    /**
     * 
     * @Method: befEdit 
     * @Description:  [跳转到编辑团购信息页面]  
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
			  logger.info("***** lvGroupBuyAction.befEdit() method begin*****");
		}
		//查询产品信息信息
		dto.put("lvGroupBuy", lvGroupBuy);
		lvGroupBuy=(LvGroupBuy) this.doService("LvGroupBuyService", "get", dto);
		this.getRequest().setAttribute("lvGroupBuy", lvGroupBuy);
		
		
		//查询产品列表信息
		LvProduct lvProduct=new LvProduct();
		lvProduct.setStoreId(lvGroupBuy.getStoreId());
		dto.put("lvProduct", lvProduct);
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllProduct", dto);
		this.getRequest().setAttribute("productList", list);
		
		//查询店铺信息
		dto.put("storeFlag", lvGroupBuy.getStoreId());
		LvStore lvStore=(LvStore) this.doService("LvStoreService", "findStore", dto);
		this.getRequest().setAttribute("lvStore", lvStore);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupBuyAction.befEdit() method end*****");
		}
		return "edit";
	}
	
    /**
     * 
     * @Method: edit 
     * @Description:  [修改团购信息]  
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
			  logger.info("***** lvGroupBuyAction.edit() method begin*****");
		}
		dto.put("lvGroupBuy", lvGroupBuy);
		if (ObjectUtils.isNotEmpty(lvGroupBuy)) {
			if(lvGroupBuy.getStartTime().getTime()>lvGroupBuy.getEndTime().getTime()){
				this.json.setMessage("团购活动开始时间大于活动结束时间！");
				this.json.setStatusCode(300);
				return AJAX;
			}
		}
		
		//团购图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String imgPath=(String) this.doService("LvGroupBuyService", "upload", dto);
			lvGroupBuy.setGimage(imgPath);
		}
		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvGroupBuy.setModifyUserId(users.getId());
		lvGroupBuy.setModifyUserName(users.getUserName());
		lvGroupBuy.setModifyTime(new Date());
		dto.put("lvGroupBuy", lvGroupBuy);
		Boolean isFlag=(Boolean)this.doService("LvGroupBuyService", "update", dto);
//		if(!isFlag){
//			this.json.setMessage("该产品存在已经启动的团购!请先关闭该产品的其他团购！");
//			this.json.setStatusCode(300);
//		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupBuyAction.edit() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: befAdd 
     * @Description:  [跳转到团购信息新增页面]  
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
			  logger.info("***** lvGroupBuyAction.befAdd() method begin*****");
		}		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupBuyAction.befAdd() method add*****");
		}
		return "befAdd";
	}
    /**
     * 
     * @Method: add 
     * @Description:  [新增团购信息]  
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
			  logger.info("***** lvGroupBuyAction.add() method begin*****");
		}
		dto.put("lvGroupBuy", lvGroupBuy);
		
		
		if (ObjectUtils.isNotEmpty(lvGroupBuy)) {
			if(lvGroupBuy.getStartTime().getTime()>lvGroupBuy.getEndTime().getTime()){
				this.json.setMessage("团购活动开始时间大于活动结束时间！");
				this.json.setStatusCode(300);
				return AJAX;
			}
		}
		//团购图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String imgPath=(String) this.doService("LvGroupBuyService", "upload", dto);
			lvGroupBuy.setGimage(imgPath);
		}
		
		lvGroupBuy.setCode(CodeUtils.getCode());//code设置
		lvGroupBuy.setCreateTime(new Date());   //创建时间
		dto.put("lvGroupBuy", lvGroupBuy);
		Boolean isFlag=(Boolean) this.doService("LvGroupBuyService", "save", dto);
//		if(!isFlag){
//			this.json.setMessage("该产品存在已经启动的团购!请先关闭该产品的其他团购！");
//			this.json.setStatusCode(300);
//		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupBuyAction.add() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: view 
     * @Description:  [查看团购信息详情]  
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
			  logger.info("***** lvGroupBuyAction.view() method begin*****");
		}		
		//查询产品信息信息
		dto.put("lvGroupBuy", lvGroupBuy);
		lvGroupBuy=(LvGroupBuy) this.doService("LvGroupBuyService", "get", dto);
		
		//查询店铺信息
		dto.put("storeFlag", lvGroupBuy.getStoreId());
		LvStore lvStore=(LvStore) this.doService("LvStoreService", "findStore", dto);
		this.getRequest().setAttribute("lvStore", lvStore);
		
		
		//设置url地址必要条件：代理端口必须是80
		lvGroupBuy.setGroupUrl("http://"+lvStore.getDomainName()+"/web/group!toGroupBuy.action?code="+lvGroupBuy.getCode());
		this.getRequest().setAttribute("lvGroupBuy", lvGroupBuy);
		
		//查询产品列表信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupBuyAction.view() method end*****");
		}
		return "view";
	}
   /**
    * 
    * @Method: del 
    * @Description:  [删除团购信息]  
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
			  logger.info("***** lvGroupBuyAction.del() method begin*****");
		}
		dto.put("lvGroupBuy", lvGroupBuy);
		this.doService("LvGroupBuyService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupBuyAction.del() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: delList 
     * @Description:  [批量删除团购信息]  
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
			  logger.info("***** lvGroupBuyAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvGroupBuy=new LvGroupBuy();
				lvGroupBuy.setId(id);
				dto.put("lvGroupBuy", lvGroupBuy);
				//删除model
				this.doService("LvGroupBuyService", "delete", dto);
				}
			}
		}
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvGroupBuyAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public LvGroupBuy getLvGroupBuy() {
		return lvGroupBuy;
	}

	public void setLvGroupBuy(LvGroupBuy lvGroupBuy) {
		this.lvGroupBuy = lvGroupBuy;
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
