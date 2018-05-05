package com.lshop.manage.lvProductImage.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvProductLadder.action.LvProductLadderAction;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductImage.action.LvProductImageAction.java]  
 * @ClassName:    [LvProductImageAction]   
 * @Description:  [产品效果图管理--数据库访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午04:09:53]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午04:09:53]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
@Controller("LvProductImageAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvProductImageAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvProductImageAction.class);
	@Resource
	private InitLinkDataService initLinkDataService;
	private LvProductImage lvProductImage;
	private File img;//产品图片
	private String imgFileName;//产品名称
	private String imgSrc;
	
	
	/**
     * 
     * @Method: list 
     * @Description:  [分页查询产品效果图列表]  
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
			  logger.info("***** lvProductImageAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvProductImage", lvProductImage);
		//产品效果图分页集合对象
		page=(Pagination) this.doService("LvProductImageService", "getList", dto);
		
		//查询产品列表信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		

		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到编辑产品效果图页面]  
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
			  logger.info("***** lvProductImageAction.befEdit() method begin*****");
		}
		//查询产品效果图信息
		dto.put("lvProductImage", lvProductImage);
		lvProductImage=(LvProductImage) this.doService("LvProductImageService", "get", dto);
		this.getRequest().setAttribute("lvProductImage", lvProductImage);
		
		//查询产品列表信息
		LvProduct lvProduct=new LvProduct();
		lvProduct.setStoreId(lvProductImage.getStoreId());
		dto.put("lvProduct", lvProduct);
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllProduct", dto);
		this.getRequest().setAttribute("productList", list);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.befEdit() method end*****");
		}
		return "edit";
	}
	
	public String befShopEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.befEdit() method begin*****");
		}
		//查询产品效果图信息
		dto.put("lvProductImage", lvProductImage);
		lvProductImage=(LvProductImage) this.doService("LvProductImageService", "get", dto);
		this.getRequest().setAttribute("lvProductImage", lvProductImage);
		
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.befEdit() method end*****");
		}
		return "befShopEdit";
	}
	
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 * @Method: edit 
	 * @Description:  [修改产品效果图]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:22]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit() throws FileNotFoundException, IOException{
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.edit() method begin*****");
		}
		if(img!=null){
			LvProduct lvProduct=null;
			if(ObjectUtils.isNotEmpty(lvProductImage.getStoreId())){
				lvProduct=new LvProduct();
				lvProduct.setStoreId(lvProductImage.getStoreId());
			}
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("lvProduct", lvProduct);
			dto.put("resPath", this.getText("res.domain.path"));
			String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
			if (!(type.toLowerCase().equals(".jpg")
					|| type.toLowerCase().equals(".jpeg")
					|| type.toLowerCase().equals(".png")
					|| type.toLowerCase().equals(".gif"))) {
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
			lvProductImage.setProductImage(imgPath);
		}
		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvProductImage.setModifyUserId(users.getId());
		lvProductImage.setModifyUserName(users.getUserName());
		lvProductImage.setModifyTime(new Date());
		dto.put("lvProductImage", lvProductImage);
		this.doService("LvProductImageService", "update", dto);
		
		this.sendHtmlToWeb(lvProductImage);
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.edit() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: befAdd 
	 * @Description:  [跳转到产品效果图新增页面]  
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
			  logger.info("***** lvProductImageAction.befAdd() method begin*****");
		}
		//查询产品列表信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllProduct", dto);
		this.getRequest().setAttribute("productList", list);
		
	
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.befAdd() method add*****");
		}
		return "befAdd";
	}
	
	public String befShopAdd(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.befAdd() method begin*****");
		}
		
		dto.put("lvProductImage", lvProductImage);
		this.getRequest().setAttribute("lvProductImage", lvProductImage);
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.befAdd() method add*****");
		}
		return "befShopAdd";
	}
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 * @Method: add 
	 * @Description:  [新增产品效果图]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String add() throws FileNotFoundException, IOException{
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.add() method begin*****");
		}
		LvProduct lvProduct=new LvProduct();
		lvProduct.setCode(lvProductImage.getProductCode());
		dto.put("lvProduct", lvProduct);
		lvProduct=(LvProduct) this.doService("LvProductService", "getProduct", dto);
		dto.put("lvProduct", lvProduct);
		
		
		//产品效果图上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("lvProduct", lvProduct);
			dto.put("resPath", this.getText("res.domain.path"));
			String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
			if (!(type.toLowerCase().equals(".jpg")
					|| type.toLowerCase().equals(".jpeg")
					|| type.toLowerCase().equals(".png")
					|| type.toLowerCase().equals(".gif"))) {
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
			lvProductImage.setProductImage(imgPath);
		}
		
		lvProductImage.setCode(CodeUtils.getCode());//code设置
		lvProductImage.setCreateTime(new Date());   //创建时间
		dto.put("lvProductImage", lvProductImage);
		this.doService("LvProductImageService", "save", dto);
		
		request.setAttribute("imgPath", lvProductImage.getProductImage()); //日志截取上传的图片名
		
		//执行单个文件静态化任务
		this.sendHtmlToWeb(lvProductImage);
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.add() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看产品效果图详情]  
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
			  logger.info("***** lvProductImageAction.view() method begin*****");
		}
		//查询产品效果图信息
		dto.put("lvProductImage", lvProductImage);
		lvProductImage=(LvProductImage) this.doService("LvProductImageService", "get", dto);
		this.getRequest().setAttribute("lvProductImage", lvProductImage);
		
		//查询产品列表信息
		List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);
		
		//根据产品的商家标志查商家信息
		dto.put("storeFlag", lvProductImage.getStoreId());
		LvStore lvStore=(LvStore) this.doService("LvStoreService", "findStore", dto);
		this.getRequest().setAttribute("domain", lvStore.getDomainName());
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.view() method end*****");
		}
		return "view";
	}
	
	public String shopView(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.shopView() method begin*****");
		}
		//查询产品效果图信息
		dto.put("lvProductImage", lvProductImage);
		lvProductImage=(LvProductImage) this.doService("LvProductImageService", "get", dto);
		this.getRequest().setAttribute("lvProductImage", lvProductImage);
				
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
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.shopView() method end*****");
		}
		return "shopView";
	}
	
	public String befBatch(){
		//查询产品信息
		dto.put("productCode", lvProductImage.getProductCode());
		LvProduct lvProduct=new LvProduct();
		lvProduct.setCode(lvProductImage.getProductCode());
		dto.put("lvProduct", lvProduct);
		lvProduct=(LvProduct) this.doService("LvProductService", "getProduct", dto);
		this.getRequest().setAttribute("lvProduct", lvProduct);
		return "befBatch";
	}
	
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除产品效果图]  
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
			  logger.info("***** lvProductImageAction.del() method begin*****");
		}
		
		//获取原效果图，日志截取用
		dto.put("lvProductImage", lvProductImage);
		LvProductImage oldProductImage = (LvProductImage)this.doService("LvProductImageService", "get", dto);
		request.setAttribute("productImage", oldProductImage.getProductImage());
		request.setAttribute("productCode", oldProductImage.getProductCode());
		
		//删除效果图
		dto.put("lvProductImage", lvProductImage);
		this.doService("LvProductImageService", "delete", dto);
		json.doNavTabTodo();
		
		//执行单个文件静态化任务
		this.sendHtmlToWeb(oldProductImage);
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.del() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除产品效果图]  
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
			  logger.info("***** lvProductImageAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			LvProductImage oldProductImage=null;
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvProductImage=new LvProductImage();
				lvProductImage.setId(id);
				dto.put("lvProductImage", lvProductImage);
				oldProductImage= (LvProductImage)this.doService("LvProductImageService", "get", dto);
				//删除model
				this.doService("LvProductImageService", "delete", dto);
				}
				
			}
			//执行单个文件静态化任务
			this.sendHtmlToWeb(oldProductImage);
		}
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvProductImageAction.delList() method end*****");
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
	private void sendHtmlToWeb(LvProductImage productImage) {
		// 执行单个页面静态化任务
		String htmlPath = "/products/" + productImage.getProductCode() + ".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, productImage.getStoreId());
	}
	
	/**
	 * 
	 * @Method: upload 
	 * @Description:  [图片上传]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午06:25:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午06:25:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	private String upload(){
		//String gv_cod = this.getText("lshop.gv_cod.url");
		//String picBath = this.getText("cod.upload.picture.url");
		String basepath=request.getRealPath("/res/images/pimg");//文件上传资源路径
		String imgName=System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
		FileUpload.upload(img,basepath, imgName);
		return "/res/images/pimg/"+imgName;
	}
	
	public LvProductImage getLvProductImage() {
		return lvProductImage;
	}

	public void setLvProductImage(LvProductImage lvProductImage) {
		this.lvProductImage = lvProductImage;
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
}
