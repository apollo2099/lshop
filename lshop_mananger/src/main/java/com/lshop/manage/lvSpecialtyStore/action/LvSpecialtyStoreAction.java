/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvSpecialtyStore.action;

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
import com.lshop.common.pojo.logic.LvSpecialtyStore;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvSpecialtyStoreAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvSpecialtyStoreAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvSpecialtyStoreAction.class);
	private LvSpecialtyStore lvSpecialtyStore = new LvSpecialtyStore();
	private File img;
	private String imgFileName;
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvSpecialtyStore);
		
		page = (Pagination)this.doService("LvSpecialtyStoreService", "findPage", dto);

		//查询所有的专卖店信息
		List<LvSpecialtyStoreType> ptypeList=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAllPareat", dto);
		this.getRequest().setAttribute("ptypeList", ptypeList);
		
		//查询所有的专卖店信息
		List<LvSpecialtyStoreType> typeList=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreAction.list() method end*****");
		}
    	
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
    	if (logger.isInfoEnabled()){
			logger.info("***** LvShopPartnerAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		//查询所有的专卖店信息
		List<LvSpecialtyStoreType> ptypeList=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAllPareat", dto);
		this.getRequest().setAttribute("ptypeList", ptypeList);
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
			logger.info("***** LvSpecialtyStoreAction.save() method begin*****");
		}
		//专卖店图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
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
			String imgPath=(String) this.doService("LvSpecialtyStoreService", "upload", dto);
			lvSpecialtyStore.setStoreLogo(imgPath);
		}
		lvSpecialtyStore.setCode(CodeUtils.getCode());//code设置
		lvSpecialtyStore.setCreateTime(new Date());   //创建时间
		dto.put("model", lvSpecialtyStore);
		this.doService("LvSpecialtyStoreService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreAction.save() method end*****");
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
			logger.info("***** LvSpecialtyStoreAction.view() method begin*****");
		}
		dto.put("model", lvSpecialtyStore);
		lvSpecialtyStore = (LvSpecialtyStore)this.doService("LvSpecialtyStoreService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreAction.view() method end*****");
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
			logger.info("***** LvSpecialtyStoreAction.befEdit() method begin*****");
		}
		dto.put("model", lvSpecialtyStore);
		lvSpecialtyStore = (LvSpecialtyStore)this.doService("LvSpecialtyStoreService", "get", dto);
		
		//查询当前专卖店类型信息
		dto.put("storeTypeCode", lvSpecialtyStore.getStoreTypeCode());
		LvSpecialtyStoreType lvSpecialtyStoreType=(LvSpecialtyStoreType) this.doService("LvSpecialtyStoreTypeService", "getSpecialtyStoreType", dto);
		this.getRequest().setAttribute("lvSpecialtyStoreType", lvSpecialtyStoreType);
		//查询所有的专卖店信息
		List<LvSpecialtyStoreType> ptypeList=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAllPareat", dto);
		this.getRequest().setAttribute("ptypeList", ptypeList);
		//查询所有的专卖店信息
		List<LvSpecialtyStoreType> typeList=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreAction.befEdit() method end*****");
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
			logger.info("***** LvSpecialtyStoreAction.edit() method begin*****");
		}
		//专卖店图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
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
			String imgPath=(String) this.doService("LvSpecialtyStoreService", "upload", dto);
			lvSpecialtyStore.setStoreLogo(imgPath);
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvSpecialtyStore.setModifyUserId(users.getId());
		lvSpecialtyStore.setModifyUserName(users.getUserName());
		lvSpecialtyStore.setModifyTime(new Date());
		dto.put("model", lvSpecialtyStore);
		this.doService("LvSpecialtyStoreService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreAction.edit() method end*****");
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
			logger.info("***** LvSpecialtyStoreAction.del() method begin*****");
		}
		dto.put("model", lvSpecialtyStore);
		//删除model
		this.doService("LvSpecialtyStoreService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreAction.del() method end*****");
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
			logger.info("***** LvSpecialtyStoreAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvSpecialtyStore.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvSpecialtyStore);
				//删除model
				this.doService("LvSpecialtyStoreService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreAction.delList() method end*****");
		}
		return AJAX;
	}
	

	public LvSpecialtyStore getLvSpecialtyStore() {
		return lvSpecialtyStore;
	}

	public void setLvSpecialtyStore(LvSpecialtyStore lvSpecialtyStore) {
		this.lvSpecialtyStore = lvSpecialtyStore;
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
