/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopProduct.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvShopProductAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvShopProductAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvShopProductAction.class);
	private LvShopProduct lvShopProduct = new LvShopProduct();
	private LvProduct lvProduct;
	private File img;//产品图片
	private String imgFileName;//产品图片名称
	private File imgAd;//产品广告图
    private String imgAdFileName;//产品广告图名称
	private String imgSrc;

	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvShopProduct);
		
		page = (Pagination)this.doService("LvShopProductService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvShopProduct", lvShopProduct);
		dto.put("lvProduct", lvProduct);
		page=(Pagination) this.doService("LvProductService", "getShopProductList", dto);
		
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		//查询所有的商城商品分类信息
		List<LvShopProductType> productTypList= (List<LvShopProductType>) this.doService("LvShopProductTypeService","findAll", dto);
		this.getRequest().setAttribute("productTypList", productTypList);

		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.save() method begin*****");
		}
        dto.put("ids", ids);
		dto.put("model", lvShopProduct);
		Boolean isFlag=(Boolean) this.doService("LvShopProductService", "save", dto);
		if(!isFlag){
			json.setMessage("操作失败，请先选择要添加的产品!");
			json.setStatusCode(300);
			return AJAX;
		}
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.save() method end*****");
		}
		return AJAX;
	}
	


	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.view() method begin*****");
		}
		dto.put("model", lvShopProduct);
		lvShopProduct = (LvShopProduct)this.doService("LvShopProductService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 跳入编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.befEdit() method begin*****");
		}
		dto.put("model", lvShopProduct);
		lvShopProduct = (LvShopProduct)this.doService("LvShopProductService", "get", dto);
		//查询商家产品信息
		LvProduct lvProduct=new LvProduct();
		lvProduct.setCode(lvShopProduct.getProductCode());
		dto.put("lvProduct", lvProduct);
		lvProduct=(LvProduct) this.doService("LvProductService", "getProduct", dto);
		this.getRequest().setAttribute("lvProduct", lvProduct);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String edit()throws ActionException, FileNotFoundException, IOException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.edit() method begin*****");
		}
		//根据登录用户判断用户对应店铺的域名信息
//		BaseUsers user=	(BaseUsers) getSession().getAttribute("USER");
//		String nowmain=request.getServerName();
//		String domain="";
//		for(Map.Entry<String, String> entry:Constants.STORE_IDS.entrySet()){   
//		    if(user.getStoreFlag().equals(entry.getValue())){
//		    	domain=entry.getKey();
//		    	break;
//		    }
//		}
		dto.put("storeFlag", lvShopProduct.getStoreId());
		LvStore lvStore= (LvStore) this.doService("LvStoreService", "findStore", dto);
		this.getRequest().setAttribute("domain", lvStore.getDomainName());
		LvProduct lvProduct=new LvProduct();
		lvProduct.setStoreId(lvShopProduct.getStoreId());
		dto.put("lvProduct", lvProduct);
		//商城产品详情主图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
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
			lvShopProduct.setPimage(imgPath);
		}
		//商城页面主产品图(广告)
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
			lvShopProduct.setPimageAd(imgPath);
		}
		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvShopProduct.setModifyUserId(users.getId());
		lvShopProduct.setModifyUserName(users.getUserName());
		lvShopProduct.setModifyTime(new Date());
		dto.put("model", lvShopProduct);
		this.doService("LvShopProductService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.del() method begin*****");
		}
		dto.put("model", lvShopProduct);
		//删除model
		this.doService("LvShopProductService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws ActionException
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvShopProduct.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvShopProduct);
				//删除model
				this.doService("LvShopProductService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductAction.delList() method end*****");
		}
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
		  return "showPic";
	}
	

	public LvShopProduct getLvShopProduct() {
		return lvShopProduct;
	}

	public void setLvShopProduct(LvShopProduct lvShopProduct) {
		this.lvShopProduct = lvShopProduct;
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
	
    public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

}
