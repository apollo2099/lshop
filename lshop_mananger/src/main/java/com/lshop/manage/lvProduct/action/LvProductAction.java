package com.lshop.manage.lvProduct.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductCommend;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvPubPackage;
import com.lshop.common.pojo.logic.LvPubPackageDetails;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.message.service.ProductMsgService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProduct.action.LvProductAction.java]  
 * @ClassName:    [LvProductAction]   
 * @Description:  [产品信息管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-3 下午03:17:19]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-3 下午03:17:19]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvProductAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvProductAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvProductAction.class);
	private LvProductType lvProductType;
	private LvProduct lvProduct;
	private LvProductProperty lvProductProperty;
	private LvProductImage lvProductImage;
	private LvProductLadder lvProductLadder;
	private LvPubPackage lvPubPackage;
	private File img;//产品详情产品图片
	private String imgFileName;//产品详情产品名称
	private String imgSrc;
	private File imgAd;//表页面主产品图(广告)
	private String imgAdFileName;//表页面主产品图(广告)产品名称
	private String userEmail;// 用户邮箱
	private String content;  // 邮件内容
	private String title;    // 邮件标题
	@Resource
	private InitLinkDataService initLinkDataService;
	
	@Resource(name="productMsgService")
	private ProductMsgService productMsgService;

	/**
     * 
     * @Method: list 
     * @Description:  [分页查询产品列表信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-3 下午03:28:42]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-3 下午03:28:42]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProduct", lvProduct);
		page=(Pagination) this.doService("LvProductService", "getList", dto);
		

    	//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
				
		//查询所有商城商品分类
		List<LvShopProductType> shopProductTypeList=(List<LvShopProductType>) this.doService("LvShopProductTypeService","findAll", dto);
		this.getRequest().setAttribute("shopProductTypeList", shopProductTypeList); 
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 
	 * @Method: shopList 
	 * @Description:  [商城商家商品管理信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 上午10:59:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 上午10:59:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String shopProductList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProduct", lvProduct);
		page=(Pagination) this.doService("LvProductService", "getShopProductList", dto);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		//查询所有的商城商品分类信息
		List<LvShopProductType> productTypList= (List<LvShopProductType>) this.doService("LvShopProductTypeService","findAll", dto);
		this.getRequest().setAttribute("productTypList", productTypList);
		
		return "shopProductList";
	}

	
	/**
	 * 
	 * @Method: recycle 
	 * @Description:  [分页查询已经下架的产品列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-9 下午03:24:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-9 下午03:24:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String recycle(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.recycle() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProduct", lvProduct);
		page=(Pagination) this.doService("LvProductService", "getList", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		//查询所有商城商品分类
		List<LvShopProductType> shopProductTypeList=(List<LvShopProductType>) this.doService("LvShopProductTypeService","findAll", dto);
		this.getRequest().setAttribute("shopProductTypeList", shopProductTypeList); 
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.recycle() method end*****");
		}
		return "recycle";
	}
	
	/**
	 * 
	 * @Method: commendList 
	 * @Description:  [跳转到选择产品推荐组合列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-5 下午02:21:49]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-5 下午02:21:49]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String commendList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.commendList() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProduct", lvProduct);
		page=(Pagination) this.doService("LvProductService", "productList", dto);
		
		//查询当前产品对应的产品推荐组合信息列表
		dto.put("productCode", lvProduct.getCode());
		List<LvProductCommend> commendList=(List<LvProductCommend>) this.doService("LvProductCommendService", "getProductCommend", dto);
		this.getRequest().setAttribute("commendList", commendList);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.commendList() method end*****");
		}
		return "commendList";
	}
	
	/**
	 * 
	 * @Method: commend 
	 * @Description:  [新增产品推荐组合信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-5 下午04:00:38]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-5 下午04:00:38]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String commend(){
		//判断当前产品是否存在推荐组合信息，如果存在则先删除推荐组合信息，再新增
		LvProductCommend lvProductCommendTmp=new LvProductCommend();
		lvProductCommendTmp.setProductCode(lvProduct.getCode());
		dto.put("lvProductCommend", lvProductCommendTmp);
		dto.put("productCode", lvProductCommendTmp.getProductCode());
		List<LvProductCommend> commendList=(List<LvProductCommend>) this.doService("LvProductCommendService", "getProductCommend", dto);
		if(commendList!=null&&commendList.size()>0){
			this.doService("LvProductCommendService", "deleteCommend", dto);
		}
		
		//新增产品推荐组合列表
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				LvProductCommend lvProductCommend=new LvProductCommend();
				lvProductCommend.setProductCode(lvProduct.getCode());
				lvProductCommend.setCommendCode(temp_ids[i].trim());
				lvProductCommend.setCreateTime(new Date());
				lvProductCommend.setCode(CodeUtils.getCode());
				lvProductCommend.setStoreId(lvProduct.getStoreId());
				dto.put("lvProductCommend", lvProductCommend);
				//删除model
				this.doService("LvProductCommendService", "save", dto);
				}
			}
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: select 
	 * @Description:  [选择产品设置当前产品的套餐信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-10 上午09:59:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-10 上午09:59:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String select(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.select() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProduct", lvProduct);
		page=(Pagination) this.doService("LvProductService", "productList", dto);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.select() method end*****");
		}
		return "select";
	}
	
	
	/**
	 * 
	 * @Method: selectProduct 
	 * @Description:  [根据商品信息，返回分页列表(选择带个商品带回)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String selectSimpleProduct()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductAction.selectSimpleProduct() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvProduct", lvProduct);		
		page = (Pagination)this.doService("LvProductService", "getList", dto);
				
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductAction.selectSimpleProduct() method end*****");
		}
		return "selectSimpleProduct";
	}
	
	
	/**
	 * 
	 * @Method: selectProduct 
	 * @Description:  [根据商品信息，返回分页列表(可以选择多个商品带回)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String selectMultipleProduct()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductAction.selectMultipleProduct() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvProduct", lvProduct);		
		page = (Pagination)this.doService("LvProductService", "getList", dto);
		
		//查询店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService", "getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductAction.selectMultipleProduct() method end*****");
		}
		return "selectMultipleProduct";
	}
	
	
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到产品信息编辑页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午03:31:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午03:31:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.befEdit() method begin*****");
		}
		//查看产品详情信息
		dto.put("lvProduct", lvProduct);
		lvProduct=(LvProduct) this.doService("LvProductService", "get", dto);
		this.getRequest().setAttribute("lvProduct", lvProduct);
		
		//查询产品列表信息
		//List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllNoMealProduct", dto);
		List<LvPubProduct> list=(List<LvPubProduct>) this.doService("LvPubProductService", "findAll", dto);
		this.getRequest().setAttribute("productList", list);
		
		//查询所有的产品类型信息
		List<LvProductType> typeList=(List<LvProductType>) this.doService("LvProductTypeService", "getAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
		
		//查询商城产品分类
		dto.put("storeFlag", lvProduct.getStoreId());
		LvStore lvStore=(LvStore) this.doService("LvStoreService", "getParentStore", dto);
		System.out.println(lvStore.getStoreFlag());
		LvShopProductType lvShopProductType=new LvShopProductType();
		lvShopProductType.setStoreId(lvStore.getStoreFlag());
		dto.put("lvShopProductType", lvShopProductType);
		List<LvShopProductType> shopProductTypeList=(List<LvShopProductType>) this.doService("LvShopProductTypeService", "findAll", dto);
		this.getRequest().setAttribute("shopProductTypeList", shopProductTypeList);
		
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.befEdit() method end*****");
		}
		return "edit";
	}
	
	public String befShopEdit(){
		//查看产品详情信息
		dto.put("lvProduct", lvProduct);
		lvProduct=(LvProduct) this.doService("LvProductService", "get", dto);
		this.getRequest().setAttribute("lvProduct", lvProduct);
		
		//查询所有的产品类型信息
		dto.put("lvProduct", lvProduct);
		List<LvProductType> typeList=(List<LvProductType>) this.doService("LvProductTypeService", "getAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
		//查询所有的商城商品分类信息
		List<LvShopProductType> productTypList= (List<LvShopProductType>) this.doService("LvShopProductTypeService","findAll", dto);
		this.getRequest().setAttribute("productTypList", productTypList);
		
		return "befShopEdit";
	}
	
	public String updateProduct() throws FileNotFoundException, IOException{
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.edit() method begin*****");
		}
		dto.put("lvProduct", lvProduct);
		//产品详情主图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
			if (!(type.toLowerCase().equals(".jpg")
					|| type.toLowerCase().equals(".jpeg")
					|| type.toLowerCase().equals(".png")
					|| type.toLowerCase().equals(".gif") 
					)) {
				json.setMessage("只能上传JPG、JPEG、PNG、GIF格式的图片文件，请重新选择！");
				json.setStatusCode(300);
				return AJAX;
			}
			if (new FileInputStream(img).available() > 1024 * 1024 * 2) {
				json.setMessage("文件大小过大，请压缩至2M以内！");
				json.setStatusCode(300);
				return AJAX;
			}
			String imgPath=(String) this.doService("LvProductService", "upload", dto);
			lvProduct.setPimage(imgPath);
		}
		//页面主产品图(广告)
		if(imgAd!=null){
			dto.put("img", imgAd);
			dto.put("imgFileName", imgAdFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String type=imgAdFileName.substring(imgAdFileName.lastIndexOf("."), imgAdFileName.length());
			if (!(type.toLowerCase().equals(".jpg")
					|| type.toLowerCase().equals(".jpeg")
					|| type.toLowerCase().equals(".png")
					|| type.toLowerCase().equals(".gif"))) {
				json.setMessage("只能上传JPG、JPEG、PNG、GIF格式的图片文件，请重新选择！");
				json.setStatusCode(300);
				return AJAX;
			}
			if (new FileInputStream(imgAd).available() > 1024 * 1024 * 2) {
				json.setMessage("文件大小过大，请压缩至2M以内！");
				json.setStatusCode(300);
				return AJAX;
			}
			String imgPath=(String) this.doService("LvProductService", "upload", dto);
			lvProduct.setPimageAd(imgPath);
		}
		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvProduct.setModifyUserId(users.getId());
		lvProduct.setModifyUserName(users.getUserName());
		lvProduct.setModifyTime(new Date());
		
		dto.put("lvProduct", lvProduct);
		Boolean isFlag=(Boolean) this.doService("LvProductService", "updateProduct", dto);
		if(!isFlag){
			json.setMessage("同一产品类型产品名称不能重复，请重新输入！");
			json.setStatusCode(300);
		}
		
		//发送消息通知前台变更产品缓存信息
		productMsgService.sendProductMsg(lvProduct.getCode());
		
		//执行页面静态化请求
		//sendHtmlToWeb();
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [编辑产品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午03:31:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午03:31:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.edit() method begin*****");
		}
		dto.put("lvProduct", lvProduct);
		dto.put("resPath", this.getText("res.domain.path"));
		//产品详情主图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String imgPath=(String) this.doService("LvProductService", "upload", dto);
			lvProduct.setPimage(imgPath);
		}
		//页面主产品图(广告)
		if(imgAd!=null){
			dto.put("img", imgAd);
			dto.put("imgFileName", imgAdFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String imgPath=(String) this.doService("LvProductService", "upload", dto);
			lvProduct.setPimageAd(imgPath);
		}
		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvProduct.setModifyUserId(users.getId());
		lvProduct.setModifyUserName(users.getUserName());
		lvProduct.setModifyTime(new Date());
		
		dto.put("lvProduct", lvProduct);
		dto.put("lvPubPackage", lvPubPackage);
		/**
		 * 验证规则定义:商品库中的商品只能与一个商城的下的商品一一对应，
		 * 同个商城下不能存在两个商品同时与商品库中的一个商品对应
		 */
		if(ObjectUtils.isNotEmpty(lvPubPackage)){
			if(ObjectUtils.isNotEmpty(lvProduct.getIsSetMeal())){
				if(lvProduct.getIsSetMeal()==1){//套餐
					if(ObjectUtils.isNotEmpty(lvPubPackage.getCode())&&ObjectUtils.isNotEmpty(lvProduct.getPubProductCode())){
						if(!lvPubPackage.getCode().equals(lvProduct.getPubProductCode())){
							dto.put("pubProductCode", lvPubPackage.getCode());
							dto.put("storeId", lvProduct.getStoreId());
							Boolean isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
							if(isFlag){
								json.setMessage("该套餐已经存在，请重新选择套餐！");
								json.setStatusCode(300);
								return AJAX;
							}else{
							 //验证套餐明细是否在店铺中存在
								List<LvPubPackageDetails> packageDetailsList=lvPubPackage.getDetailsList();
								for (int i = 0; i < packageDetailsList.size(); i++) {
									LvPubPackageDetails pd=packageDetailsList.get(i);
									if(ObjectUtils.isNotEmpty(pd)){
										dto.put("pubProductCode", pd.getPubProductCode());
										dto.put("storeId", lvProduct.getStoreId());
										isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
										if(!isFlag){
											json.setMessage("该套餐明细中的商品在店铺中不存在，请先添加！");
											json.setStatusCode(300);
											return AJAX;
										}
									}
								}
							}
						}
					}	
				}else if(lvProduct.getIsSetMeal()==0){//非套餐
					if(ObjectUtils.isNotEmpty(lvProduct.getPubProductCode())&&ObjectUtils.isNotEmpty(lvProduct.getOldPubProductCode())){
						if(!lvProduct.getPubProductCode().equals(lvProduct.getOldPubProductCode())){
							dto.put("pubProductCode", lvProduct.getPubProductCode());
							dto.put("storeId", lvProduct.getStoreId());
							Boolean isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
							if(isFlag){
								json.setMessage("该产品已经存在，请重新选择商品！");
								json.setStatusCode(300);
								return AJAX;
							}
						}
					}
				}
			}
		}
		
		
		
		//保存商品信息
		Boolean isFlag=(Boolean) this.doService("LvProductService", "update", dto);
		if(!isFlag){
			json.setMessage("同一产品类型产品名称不能重复，请重新输入！");
			json.setStatusCode(300);
		}
		
		//发送消息通知前台，商品信息已经变更
		productMsgService.sendProductMsg(lvProduct.getCode());
		
		//sendHtmlToWeb();
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.edit() method end*****");
		}
		return AJAX;
	}



	/**
	 * 
	 * @Method: befAdd 
	 * @Description:  [跳转到发布产品页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午03:32:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午03:32:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befAdd(){
		//查询所有的产品类型信息
		List<LvProductType> typeList=(List<LvProductType>) this.doService("LvProductTypeService", "getAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
//		//查询产品列表信息
//		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllNoMealProduct", dto);
//		this.getRequest().setAttribute("productList", list);
		
		//查询商城产品分类
		List<LvShopProductType> shopProductTypeList=(List<LvShopProductType>) this.doService("LvShopProductTypeService", "findAll", dto);
		this.getRequest().setAttribute("shopProductTypeList", shopProductTypeList);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
	    return "befAdd";	
	}
	
	/**
	 * 
	 * @Method: add 
	 * @Description:  [发布新产品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午03:33:08]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午03:33:08]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String add(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.save() method begin*****");
		}
		dto.put("lvProduct", lvProduct);
		dto.put("resPath", this.getText("res.domain.path"));
		//产品详情主图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String imgPath=(String) this.doService("LvProductService", "upload", dto);
			lvProduct.setPimage(imgPath);
		}
		//页面主产品图(广告)
		if(imgAd!=null){
			dto.put("img", imgAd);
			dto.put("imgFileName", imgAdFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String imgPath=(String) this.doService("LvProductService", "upload", dto);
			lvProduct.setPimageAd(imgPath);
		}
		//新增产品相关属性设置
		lvProduct.setStatus(1);
		lvProduct.setCode(CodeUtils.getCode());//code设置
		lvProduct.setCreateTime(new Date());   //创建时间
		dto.put("lvProduct", lvProduct);
		dto.put("lvPubPackage", lvPubPackage);
		/**
		 * 规则定义:商品库中的商品只能与一个商城的下的商品一一对应，
		 * 同个商城下不能存在两个商品同时与商品库中的一个商品对应
		 */
		if(ObjectUtils.isNotEmpty(lvPubPackage)){
			if(ObjectUtils.isNotEmpty(lvProduct.getIsSetMeal())){
				if(lvProduct.getIsSetMeal()==1){//套餐
					dto.put("pubProductCode", lvPubPackage.getCode());
					dto.put("storeId", lvProduct.getStoreId());
					Boolean isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
					if(isFlag){
						json.setMessage("该套餐已经存在，请重新选择套餐！");
						json.setStatusCode(300);
						return AJAX;
					}else{
					 //验证套餐明细是否在店铺中存在
						List<LvPubPackageDetails> packageDetailsList=lvPubPackage.getDetailsList();
						for (int i = 0; i < packageDetailsList.size(); i++) {
							LvPubPackageDetails pd=packageDetailsList.get(i);
							if(ObjectUtils.isNotEmpty(pd)){
								dto.put("pubProductCode", pd.getPubProductCode());
								dto.put("storeId", lvProduct.getStoreId());
								isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
								if(!isFlag){
									json.setMessage("该套餐明细中的商品在店铺中不存在，请先添加！");
									json.setStatusCode(300);
									return AJAX;
								}
							}
						}
					}
				}else if(lvProduct.getIsSetMeal()==0){//非套餐
					dto.put("pubProductCode", lvProduct.getPubProductCode());
					dto.put("storeId", lvProduct.getStoreId());
					Boolean isFlag=(Boolean) this.doService("LvProductService", "isExistPubProduct", dto);
					if(isFlag){
						json.setMessage("该产品已经存在，请重新选择商品！");
						json.setStatusCode(300);
						return AJAX;
					}
				}
			}
		}
		
		//保存商品信息
		Boolean isFlag=(Boolean) this.doService("LvProductService", "save", dto);
		if(!isFlag){
			json.setMessage("该产品名称已经存在，请重新输入！");
			json.setStatusCode(300);
		}
		
		//向前台发送消息
		productMsgService.sendProductMsg(lvProduct.getCode());
		
		//执行页面静态化请求
		//sendHtmlToWeb();
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.save() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看产品详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午03:33:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午03:33:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.view() method begin*****");
		}
		//查询产品信息
		dto.put("lvProduct", lvProduct);
		lvProduct=(LvProduct) this.doService("LvProductService", "get", dto);
		this.getRequest().setAttribute("lvProduct", lvProduct);
		
		//根据产品的商家标志查商家信息
		dto.put("storeFlag", lvProduct.getStoreId());
		LvStore lvStore=(LvStore) this.doService("LvStoreService", "findStore", dto);
		this.getRequest().setAttribute("domain", lvStore.getDomainName());
		//查询所有的产品类型信息
		List<LvProductType> typeList=(List<LvProductType>) this.doService("LvProductTypeService", "getAll", dto);
		this.getRequest().setAttribute("typeList", typeList);		
		
		//查询商城产品分类
		List<LvShopProductType> shopProductTypeList=(List<LvShopProductType>) this.doService("LvShopProductTypeService", "findAll", dto);
		this.getRequest().setAttribute("shopProductTypeList", shopProductTypeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.view() method end*****");
		}
	    return "view";	
	}
	
	public String unSupportDetails(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.view() method begin*****");
		}
		//查询产品信息
		dto.put("lvProduct", lvProduct);
		lvProduct=(LvProduct) this.doService("LvProductService", "get", dto);
		this.getRequest().setAttribute("lvProduct", lvProduct);
		
		//根据产品的商家标志查商家信息
		dto.put("storeFlag", lvProduct.getStoreId());
		LvStore lvStore=(LvStore) this.doService("LvStoreService", "findStore", dto);
		this.getRequest().setAttribute("domain", lvStore.getDomainName());
		//查询所有的产品类型信息
		List<LvProductType> typeList=(List<LvProductType>) this.doService("LvProductTypeService", "getAll", dto);
		this.getRequest().setAttribute("typeList", typeList);		
		
		//查询商城产品分类
		List<LvShopProductType> shopProductTypeList=(List<LvShopProductType>) this.doService("LvShopProductTypeService", "findAll", dto);
		this.getRequest().setAttribute("shopProductTypeList", shopProductTypeList);
		
		return "unSupportDetails";
	}	
	
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除产品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午03:35:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午03:35:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.del() method begin*****");
		}
		dto.put("ids", ids);
		this.doService("LvProductService", "delete", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.del() method end*****");
		}
		json.doNavTabTodo();
		
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: showPic 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午06:25:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午06:25:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String showPic(){
		  request.setAttribute("imgSrc", imgSrc);
		  //根据登录用户判断用户对应店铺的域名信息
			BaseUsers user=	(BaseUsers) getSession().getAttribute("USER");
			String nowmain=request.getServerName();
			String domain="";
			for(Map.Entry<String, String> entry:Constants.STORE_IDS.entrySet()){   
			    if(user.getStoreFlag().equals(entry.getValue())){
			    	domain=entry.getKey();
			    	break;
			    }
			}
			this.getRequest().setAttribute("domain", domain);
		  
		  return "showPic";
	}
	

	/**
	 * 
	 * @Method: undercarriage 
	 * @Description:  [将当前产品进行下/上架操作]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-9 下午03:28:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-9 下午03:28:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String updateSupport(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.updateSupport() method begin*****");
		}
		dto.put("lvProduct", lvProduct);
		this.doService("LvProductService", "updateSupport", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.updateSupport() method begin*****");
		}
		json.doNavTabTodo();
		//发送消息通知前台更新缓存
		productMsgService.sendProductMsg(lvProduct.getCode());
		return AJAX;
	}
	
	
	/**
	 * 
	 * @Method: undercarriage 
	 * @Description:  [更新缓存]  
	 * @Author:       [老蒋]     
	 * @CreateDate:   [2014-12-11]   
	 * @UpdateUser:        
	 * @UpdateDate:    
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String updateCache(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.updateCache() method begin*****");
		}
		//发送消息通知前台更新缓存
		productMsgService.sendProductMsg(lvProduct.getCode());
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductAction.updateCache() method end*****");
		}
		json.doNavTabTodo();
		return AJAX;
	}
	
	
	/**
	 * 
	 * @Method: befChangeType 
	 * @Description:  [跳转到批量修改商城商品分类页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 下午01:46:41]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 下午01:46:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String befChangeType(){
		//查询所有的商城商品分类信息
		System.out.println(ids);
		String  [] temp=ids.split(",");
		this.getRequest().setAttribute("num",temp.length);
		List<LvShopProductType> productTypList= (List<LvShopProductType>) this.doService("LvShopProductTypeService","findAll", dto);
		this.getRequest().setAttribute("productTypList", productTypList);
		return "befChangeType";
	}
	/**
	 * 
	 * @Method: changeType 
	 * @Description:  [批量修改商城商品分类]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 下午03:44:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 下午03:44:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String changeType(){
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvProduct.setModifyUserId(users.getId());
		lvProduct.setModifyUserName(users.getUserName());
		lvProduct.setModifyTime(new Date());
		dto.put("ids",ids);
		dto.put("lvProduct", lvProduct);
		this.doService("LvProductService","updateType", dto);
		return AJAX;
	}
	/**
	 * 
	 * @Method: befUnSupport 
	 * @Description:  [跳转到产品下架页面，填写下架说明]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 下午01:46:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 下午01:46:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String befUnSupport(){
		return "befUnSupport";
	}

	/**
	 * 
	 * @Method: unSupport 
	 * @Description:  [批量商品下架]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 下午05:37:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 下午05:37:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String unSupport(){
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvProduct.setModifyUserId(users.getId());
		lvProduct.setModifyUserName(users.getUserName());
		lvProduct.setModifyTime(new Date());
		dto.put("ids",ids);
		dto.put("lvProduct", lvProduct);
		this.doService("LvProductService","updateUnSupport", dto);
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: befToNotice 
	 * @Description:  [跳转到商品异常信息商家邮件通知发送内容页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 下午05:37:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 下午05:37:48]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String befToNotice(){
		dto.put("storeFlag", lvProduct.getStoreId());
		LvStore lvStore=(LvStore) this.doService("LvStoreService","findStore", dto);
		this.getRequest().setAttribute("lvStore", lvStore);
		return "befToNotice";
	}
	/**
	 * 
	 * @Method: toNotice 
	 * @Description:  [商品异常信息商家邮件通知发送]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 下午05:38:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 下午05:38:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String toNotice(){
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(lvProduct.getStoreId()));
		dto.put("title", title);// 邮件标题
		dto.put("userEmail", userEmail);// 用户email
		this.content += "<p>" + StringUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss") + "</p>";
		dto.put("content", content);// 邮件内容
		doService("emailSendService", "sendEmailNotice", dto);
		json.setMessage("邮件发送成功！");
		json.setStatusCode(200);
		return AJAX;
	}
	
	public String getProductImage(){
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProduct", lvProduct);
		dto.put("lvProductImage", lvProductImage);
		page=(Pagination) this.doService("LvProductService", "getProductImage", dto);
		return "productImage";
	}
	public String getProductProperty(){
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProduct", lvProduct);
		dto.put("lvProductProperty", lvProductProperty);
		page=(Pagination) this.doService("LvProductService", "getProductProperty", dto);
		return "productProperty";
	}
	
	public String getProductLadder(){
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProduct", lvProduct);
		dto.put("lvProductLadder", lvProductLadder);
		page=(Pagination) this.doService("LvProductService", "getProductLadder", dto);
		return "productLadder";
	}
    /**
     * 
     * @Method: getProduct 
     * @Description:  [更加产品code获取产品数据，并且以json数据形式返回]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-9-26 上午11:59:13]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-9-26 上午11:59:13]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return 
     * @return String
     */
	public String toProduct(){
		PrintWriter out = null;
		//查询产品信息
		dto.put("lvProduct", lvProduct);
		lvProduct=(LvProduct) this.doService("LvProductService", "getProduct", dto);
		this.getRequest().setAttribute("lvProduct", lvProduct);
		
		try {
		    out=this.getResponse().getWriter();
		    String jsonTmp = JSONObject.fromObject(lvProduct).toString();
		    System.out.println(jsonTmp);
		    logger.info("json_message"+jsonTmp);
			out.print(jsonTmp );
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Method: toProductByStoreId 
	 * @Description:  [查询不包含套餐的产品列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-3-23 下午02:27:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-3-23 下午02:27:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toProductByStoreId(){
		PrintWriter out = null;
		dto.put("lvProduct", lvProduct);
		if(lvProduct!=null&&ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
			//查询套餐产品信息
			List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllNoMealProduct", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvProduct lvProduct=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("productCode", lvProduct.getCode());
					map.put("productName", lvProduct.getProductName());
					listTmp.add(map);
					
				}
			}
			mapTmp.put("listTmp", listTmp);
			//查询店铺产品类型
			List listTypeTmp=new ArrayList();
			List<LvProductType> listPay=(List<LvProductType>) this.doService("LvProductTypeService", "getAll", dto);
			if(list!=null&&listPay.size()>0){
				for (int i = 0; i < listPay.size(); i++) {
					LvProductType lvProductType=listPay.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("typeCode", lvProductType.getCode());
					map.put("typeName", lvProductType.getTypeName());
					listTypeTmp.add(map);
				}
			}
			mapTmp.put("listTypeTmp", listTypeTmp);
			//查询商城商品分类
			dto.put("storeFlag", lvProduct.getStoreId());
			LvStore lvStore=(LvStore) this.doService("LvStoreService", "getParentStore", dto);
			System.out.println(lvStore.getStoreFlag());
			LvShopProductType lvShopProductType=new LvShopProductType();
			lvShopProductType.setStoreId(lvStore.getStoreFlag());
			dto.put("lvShopProductType", lvShopProductType);
			List shopProductTypeList=new ArrayList();
			List<LvShopProductType> listShopType=(List<LvShopProductType>) this.doService("LvShopProductTypeService", "findAll", dto);
			for (LvShopProductType stype : listShopType) {
				Map<String,Object>  map=new HashMap<String,Object>();
				map.put("shopTypeCode", stype.getCode());
				map.put("shopTypeName", stype.getTypeName());
				shopProductTypeList.add(map);
			}
			mapTmp.put("shopProductTypeList", shopProductTypeList);
			
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
		
		return null;
	}
	
	/**
	 * 
	 * @Method: toAllProduct 
	 * @Description:  [查询所有的产品列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-3-23 下午02:26:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-3-23 下午02:26:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toAllProduct(){
		PrintWriter out = null;
		
		dto.put("lvProduct", lvProduct);
		if(lvProduct!=null&&ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
			List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllProduct", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvProduct lvProduct=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("productCode", lvProduct.getCode());
					map.put("productName", lvProduct.getProductName());
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

	/**
	 * 
	 * @Method: toProductType 
	 * @Description:  [根据店铺标志查询当前店铺下的商品类别信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-13 下午3:27:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-13 下午3:27:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toProductType(){
		PrintWriter out = null;
		
		dto.put("lvProduct", lvProduct);
		if(lvProduct!=null&&ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
			List<LvProductType> list=(List<LvProductType>) this.doService("LvProductTypeService", "getAll", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvProductType lvProductType=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("typeCode", lvProductType.getCode());
					map.put("typeName", lvProductType.getTypeName());
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
	private void sendHtmlToWeb() {
		// 执行单个页面静态化任务
		String htmlPath = "/products/" + lvProduct.getCode() + ".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, lvProduct.getStoreId());
	}
	
	public LvProductType getLvProductType() {
		return lvProductType;
	}

	public void setLvProductType(LvProductType lvProductType) {
		this.lvProductType = lvProductType;
	}

	public LvProduct getLvProduct() {
		return lvProduct;
	}

	public void setLvProduct(LvProduct lvProduct) {
		this.lvProduct = lvProduct;
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
    public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	public File getImgAd() {
		return imgAd;
	}

	public void setImgAd(File imgAd) {
		this.imgAd = imgAd;
	}

	public String getImgAdFileName() {
		return imgAdFileName;
	}

	public void setImgAdFileName(String imgAdFileName) {
		this.imgAdFileName = imgAdFileName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public LvProductProperty getLvProductProperty() {
		return lvProductProperty;
	}

	public void setLvProductProperty(LvProductProperty lvProductProperty) {
		this.lvProductProperty = lvProductProperty;
	}

	public LvProductImage getLvProductImage() {
		return lvProductImage;
	}

	public void setLvProductImage(LvProductImage lvProductImage) {
		this.lvProductImage = lvProductImage;
	}

	public LvProductLadder getLvProductLadder() {
		return lvProductLadder;
	}

	public void setLvProductLadder(LvProductLadder lvProductLadder) {
		this.lvProductLadder = lvProductLadder;
	}

	public LvPubPackage getLvPubPackage() {
		return lvPubPackage;
	}

	public void setLvPubPackage(LvPubPackage lvPubPackage) {
		this.lvPubPackage = lvPubPackage;
	}

	public ProductMsgService getProductMsgService() {
		return productMsgService;
	}

	public void setProductMsgService(ProductMsgService productMsgService) {
		this.productMsgService = productMsgService;
	}
	
	

}
