/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxMenu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.manage.weixin.pojo.Button;
import com.lshop.manage.weixin.pojo.CommonButton;
import com.lshop.manage.weixin.pojo.ComplexButton;
import com.lshop.manage.weixin.pojo.Menu;
import com.lshop.manage.weixin.pojo.ViewButton;
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxMenu;
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.pojo.WxTextMaterial;
import com.lshop.manage.weixin.util.WeixinUtil;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxMenuMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxMenuMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxMenuMngAction.class);
	private WxMenu wxMenu = new WxMenu();

	public WxMenu getWxMenu() {
		return wxMenu;
	}

	public void setWxMenu(WxMenu wxMenu) {
		this.wxMenu = wxMenu;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		getMaterials();
		getWxgzh();
		dto.put("model", wxMenu);
		List<WxMenu> menus = (List<WxMenu>)this.doService("WxMenuService", "getAllParentMenu", dto);
		this.request.setAttribute("menus", menus);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.save() method begin*****");
		}
		wxMenu.setCreateTime(new Date());
		dto.put("model", wxMenu);
		this.doService("WxMenuService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.save() method end*****");
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
			logger.info("***** WxMenuMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		getMaterials();
		getWxgzh();
		dto.put("model", wxMenu);
		
		page = (Pagination)this.doService("WxMenuService", "findPage", dto);
		List<WxMenu> menus = (List<WxMenu>)this.doService("WxMenuService", "findAll", dto);
		this.request.setAttribute("menus", menus);
    	if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.list() method end*****");
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
			logger.info("***** WxMenuMngAction.view() method begin*****");
		}
		dto.put("model", wxMenu);
		wxMenu = (WxMenu)this.doService("WxMenuService", "get", dto);
		getNewMaterialsByConfigId(wxMenu.getWxhConfigId());
		
		if(wxMenu!= null && wxMenu.getMenuParent()!=null){
		WxMenu menu = new WxMenu();
		menu.setId(wxMenu.getMenuParent());
		dto.put("model", menu);
		menu = (WxMenu)this.doService("WxMenuService", "get", dto);
		this.request.setAttribute("menu", menu);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.view() method end*****");
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
			logger.info("***** WxMenuMngAction.befEdit() method begin*****");
		}
		getMaterials();
		getWxgzh();
		dto.put("model", wxMenu);
		wxMenu = (WxMenu)this.doService("WxMenuService", "get", dto);
		List<WxMenu> menus = (List<WxMenu>)this.doService("WxMenuService", "getAllParentMenu", dto);
		this.request.setAttribute("menus", menus);
		if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.edit() method begin*****");
		}
		dto.put("model", wxMenu);
		this.doService("WxMenuService", "update", dto);
		
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.edit() method end*****");
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
			logger.info("***** WxMenuMngAction.del() method begin*****");
		}
		dto.put("model", wxMenu);
		//删除model
		this.doService("WxMenuService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.del() method end*****");
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
			logger.info("***** WxMenuMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxMenu.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxMenu);
				//删除model
				this.doService("WxMenuService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxMenuMngAction.delList() method end*****");
		}
		return AJAX;
	}
	public String toDefineMenusToWeixin()throws ActionException{
		getWxgzh();
		return "define";
	}
	
	
	public String createMenusToWeixin()throws ActionException{
		dto.put("model", wxMenu);
		List<Button> btns = new ArrayList();
		List<WxMenu> parentmenus = (List<WxMenu>)this.doService("WxMenuService", "getAllParentMenu", dto);
		if(parentmenus == null || parentmenus.size()==0){
			json.setMessage("该公众号下未定义菜单");
			json.setStatusCode(300);
			return AJAX;
		}
		for(WxMenu menu : parentmenus ){
			dto.put("model", menu);
			List<WxMenu> subMenus = (List<WxMenu>)this.doService("WxMenuService", "getAllSubMenu", dto);
			if(subMenus == null || subMenus.size() == 0){
		    	Button button1 = this.constructButton(menu);//创建一个普通的按钮
		    	btns.add( button1);
		    		
		    	}else{
		    		ComplexButton com = new ComplexButton();
		    		List<Button> subs = new ArrayList();;
		    		com.setName(menu.getName());
		    		for (WxMenu sub : subMenus){
		    			Button button2 = this.constructButton(sub);//创建一个普通的按钮
		    			subs.add( button2);
		    		}
		    		com.setSub_button((Button [])subs.toArray(new Button[subs.size()]));
		    		btns.add(com);
		    }
		
		}
		Menu createdMenu = this.getMenu((Button [])btns.toArray(new Button[btns.size()]));
		
		WxGzhConfig config = new WxGzhConfig();
		config.setId(wxMenu.getWxhConfigId());
		dto.put("model", config);
		BaseUsers user = new BaseUsers();
		user.setId(0);
		user.setUserName("admin");
		dto.put("users", user);
		String access_token = (String)this.doService("WxGzhConfigService", "getLatestToken", dto);
		int result = WeixinUtil.createMenu(createdMenu, access_token);
		if(result == 0){
			json.setMessage("自定义微信菜单成功");
			json.setStatusCode(200);
			
		}else{
			json.setMessage("自定义微信菜单失败");
			json.setStatusCode(300);
		}
		return AJAX;
	}
	
	/**
	 * 查询所有的公众号配置信息
	 */
	private void getWxgzh(){
		List<WxGzhConfig> list = (List<WxGzhConfig>)this.doService("WxGzhConfigService", "getAllWxgzh", dto);
		this.request.setAttribute("wxhConfigList", list);
	}
	
	private void getMaterials(){
		List<WxTextMaterial> textMaterials = (List<WxTextMaterial>)this.doService("WxTextMaterialService", "findAll", dto);
		List<WxNewsMaterial> newsMaterials = (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		this.request.setAttribute("textMaterials", textMaterials);
		this.request.setAttribute("newsMaterials", newsMaterials);
		
	}
	
	/**
	 * 根据所选中的微信公众号配置信息 查询其下面的所有的素材 包括图文以及文本
	 * @param id
	 */
	private void getNewMaterialsByConfigId(int id){
		WxNewsMaterial wxNewsMaterial = new WxNewsMaterial();
		wxNewsMaterial.setWxhConfigId(id);
		dto.put("model", wxNewsMaterial);
		List<WxNewsMaterial>  wxNewsMaterials= (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		request.setAttribute("newsMaterials", wxNewsMaterials);
		WxTextMaterial wxTextMaterial = new WxTextMaterial();
		wxTextMaterial.setWxhConfigId(id);
		dto.put("model", wxTextMaterial);
		List<WxTextMaterial> textMaterials = (List<WxTextMaterial>)this.doService("WxTextMaterialService", "findAll", dto);
		this.request.setAttribute("textMaterials", textMaterials);
		WxGzhConfig config = new WxGzhConfig();
		config.setId(wxMenu.getWxhConfigId());
		dto.put("model", config);
		config = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		this.request.setAttribute("config", config);
		
	}
	
	
 private Button constructButton(WxMenu menu){
	 if(menu.getMenuType().equals("click")){
		 CommonButton	 btn = new CommonButton();
		 btn.setName(menu.getName());
		 btn.setKey(menu.getMenuKey());
		 btn.setType("click");
		 return btn;
	 }else if(menu.getMenuType().equals("view")){
		 ViewButton btn = new ViewButton();
		 btn.setName(menu.getName());
		 btn.setUrl(menu.getMenuUrl());
		 btn.setType("view");
		 return btn;
	 }
	 return null;
	 
 }
 
 
	
	private Menu getMenu(Button [] buttons ){ 
        Menu menu = new Menu();  
        menu.setButton(buttons); 
        return menu;
	}


}
