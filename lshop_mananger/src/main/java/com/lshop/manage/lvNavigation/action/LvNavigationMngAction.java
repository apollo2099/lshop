/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvNavigation.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonArray;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvNavigation;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvNavigation.service.LvNavigationService;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvNavigationMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvNavigationMngAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvNavigationMngAction.class);
	private LvNavigation lvNavigation = new LvNavigation();
	private File img;//产品详情产品图片
	private String imgFileName;//产品详情产品名称
	private String storeId;//商城标识
	
	@Resource LvNavigationService navigationService;
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
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
			logger.info("***** LvNavigationMngAction.save() method begin*****");
		}
		//图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("storeId", lvNavigation.getStoreId());
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
			String imgPath=(String) this.doService("LvNavigationService", "upload", dto);
			lvNavigation.setNavImage(imgPath);
		}
		
		lvNavigation.setCode(CodeUtils.getCode());
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvNavigation.setModifyUserId(users.getId());
		lvNavigation.setModifyUserName(users.getUserName());
		lvNavigation.setCreateTime(new Date());
		dto.put("model", lvNavigation);
		this.doService("LvNavigationService", "save", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.save() method end*****");
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
			logger.info("***** LvNavigationMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		lvNavigation.setNavParentId(0);
		dto.put("model", lvNavigation);
		
		page = (Pagination)this.doService("LvNavigationService", "findPage", dto);

		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
    	if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.list() method end*****");
		}
		return LIST;
	}
	
	public String childlist()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.childlist() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvNavigation);
		page = (Pagination)this.doService("LvNavigationService", "findPage", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.childlist() method end*****");
		}
		
		return "childlist";
	}

	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.view() method begin*****");
		}
		dto.put("model", lvNavigation);
		lvNavigation = (LvNavigation)this.doService("LvNavigationService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.view() method end*****");
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
			logger.info("***** LvNavigationMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvNavigation);
		lvNavigation = (LvNavigation)this.doService("LvNavigationService", "get", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		if (0 != lvNavigation.getNavParentId().intValue()) {//非项层导航菜单有父级菜单
			LvNavigation par = navigationService.getById(lvNavigation.getNavParentId());
			this.getRequest().setAttribute("parentMenu", par);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.befEdit() method end*****");
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
			logger.info("***** LvNavigationMngAction.edit() method begin*****");
		}
		//图片上传
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("storeId", lvNavigation.getStoreId());
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
			String imgPath=(String) this.doService("LvNavigationService", "upload", dto);
			lvNavigation.setNavImage(imgPath);
		}
		
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvNavigation.setModifyUserId(users.getId());
		lvNavigation.setModifyUserName(users.getUserName());
		lvNavigation.setModifyTime(new Date());
		dto.put("model", lvNavigation);
		this.doService("LvNavigationService", "update", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.edit() method end*****");
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
			logger.info("***** LvNavigationMngAction.del() method begin*****");
		}
		dto.put("model", lvNavigation);
		//删除model
		this.doService("LvNavigationService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.del() method end*****");
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
			logger.info("***** LvNavigationMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvNavigation.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvNavigation);
				//删除model
				this.doService("LvNavigationService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvNavigationMngAction.delList() method end*****");
		}
		return AJAX;
	}

	/**
	 * 返回指定商城父级导航菜单JSON
	 * @throws IOException 
	 */
	public void getMallPrimNavi() throws IOException{
		if (StringUtils.isNotBlank(storeId)) {
			List<LvNavigation> navigations = navigationService.findPrimNaviByStoreId(storeId);
			if (ObjectUtils.isNotEmpty(navigations)) {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(JSONArray.fromObject(navigations).toString());
			}
		}
	}
	public LvNavigation getLvNavigation() {
		return lvNavigation;
	}

	public void setLvNavigation(LvNavigation lvNavigation) {
		this.lvNavigation = lvNavigation;
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

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}
