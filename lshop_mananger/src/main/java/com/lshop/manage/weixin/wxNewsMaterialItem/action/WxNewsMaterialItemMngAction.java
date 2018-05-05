/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxNewsMaterialItem.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.pojo.WxNewsMaterialItem;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxNewsMaterialItemMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxNewsMaterialItemMngAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(WxNewsMaterialItemMngAction.class);
	private WxNewsMaterialItem wxNewsMaterialItem = new WxNewsMaterialItem();
	private File img;
	private String imgFileName;

	public WxNewsMaterialItem getWxNewsMaterialItem() {
		return wxNewsMaterialItem;
	}

	public void setWxNewsMaterialItem(WxNewsMaterialItem wxNewsMaterialItem) {
		this.wxNewsMaterialItem = wxNewsMaterialItem;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		getWxgzhAndNewsMaterial();
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String save()throws ActionException, FileNotFoundException, IOException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.save() method begin*****");
		}

		wxNewsMaterialItem.setCreateTime(new Date());
		wxNewsMaterialItem.setModifyTime(new Date());
		WxGzhConfig wxGzhConfig = new WxGzhConfig();
		wxGzhConfig.setId(wxNewsMaterialItem.getWxhConfigId());
		dto.put("model", wxGzhConfig);
		wxGzhConfig = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		
		String storeFlag = wxGzhConfig.getStoreId();
		
		if(img!=null && StringUtils.isNotEmpty(imgFileName)){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("resPath", this.getText("res.domain.path"));
			dto.put("storeFlag", storeFlag);
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
			String imgPath=(String) this.doService("WxNewsMaterialItemService", "upload", dto);
			wxNewsMaterialItem.setPicurl(imgPath);
		}else{
			json.setMessage("文件上传失败！");
			json.setStatusCode(300);
			return AJAX;
		}
		dto.put("model", wxNewsMaterialItem);
		this.doService("WxNewsMaterialItemService", "save", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.save() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		
		getWxgzhAndNewsMaterial();
		dto.put("model", wxNewsMaterialItem);
		page = (Pagination)this.doService("WxNewsMaterialItemService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.list() method end*****");
		}
		return LIST;
	}

	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.view() method begin*****");
		}
		dto.put("model", wxNewsMaterialItem);
		wxNewsMaterialItem = (WxNewsMaterialItem)this.doService("WxNewsMaterialItemService", "get", dto);
		getWxgzhAndNewsMaterial();
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.view() method end*****");
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
			logger.info("***** WxNewsMaterialItemMngAction.befEdit() method begin*****");
		}
		dto.put("model", wxNewsMaterialItem);
		wxNewsMaterialItem = (WxNewsMaterialItem)this.doService("WxNewsMaterialItemService", "get", dto);
		getWxgzhAndNewsMaterial();
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.befEdit() method end*****");
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
			logger.info("***** WxNewsMaterialItemMngAction.edit() method begin*****");
		}
		
		wxNewsMaterialItem.setModifyTime(new Date());
		WxGzhConfig wxGzhConfig = new WxGzhConfig();
		wxGzhConfig.setId(wxNewsMaterialItem.getWxhConfigId());
		dto.put("model", wxGzhConfig);
		wxGzhConfig = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		
		String storeFlag = wxGzhConfig.getStoreId();
		if(img!=null && StringUtils.isNotEmpty(imgFileName)){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("storeFlag", storeFlag);
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
			String imgPath=(String) this.doService("WxNewsMaterialItemService", "upload", dto);
			wxNewsMaterialItem.setPicurl(imgPath);
		}
		dto.put("model", wxNewsMaterialItem);
		this.doService("WxNewsMaterialItemService", "update", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.edit() method end*****");
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
			logger.info("***** WxNewsMaterialItemMngAction.del() method begin*****");
		}
		dto.put("model", wxNewsMaterialItem);
		//删除model
		this.doService("WxNewsMaterialItemService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.del() method end*****");
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
			logger.info("***** WxNewsMaterialItemMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxNewsMaterialItem.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxNewsMaterialItem);
				//删除model
				this.doService("WxNewsMaterialItemService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemMngAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public String showPic(){
		  request.setAttribute("imgSrc", imgFileName);
		  return "showPic";
	}
	
	private void getWxgzhAndNewsMaterial(){
		WxNewsMaterial wxNewsMaterial = new WxNewsMaterial();
		wxNewsMaterial.setId(wxNewsMaterialItem.getNewsId());
		dto.put("model", wxNewsMaterial);
		wxNewsMaterial = (WxNewsMaterial)this.doService("WxNewsMaterialService", "get", dto);
		request.setAttribute("newsMaterial", wxNewsMaterial);
		List<WxGzhConfig> list = (List<WxGzhConfig>)this.doService("WxGzhConfigService", "getAllWxgzh", dto);
		this.request.setAttribute("wxhConfigList", list);
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
