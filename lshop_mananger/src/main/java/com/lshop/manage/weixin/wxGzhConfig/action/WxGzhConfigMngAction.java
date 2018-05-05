/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxGzhConfig.action;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxKeywordsReply;
import com.lshop.manage.weixin.pojo.WxMenu;
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.pojo.WxObtainNewsTpl;
import com.lshop.manage.weixin.pojo.WxPassiveReply;
import com.lshop.manage.weixin.pojo.WxTextMaterial;
import com.lshop.manage.weixin.pojo.WxUser;
import com.lshop.manage.weixin.util.HttpClientUtil;
import com.lshop.manage.weixin.wxGzhConfig.service.WxGzhConfigService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxGzhConfigMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxGzhConfigMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxGzhConfigMngAction.class);
	private WxGzhConfig wxGzhConfig = new WxGzhConfig();
	public  String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	@Resource
	private WxGzhConfigService wxGzhConfigService;

	public WxGzhConfig getWxGzhConfig() {
		return wxGzhConfig;
	}

	public void setWxGzhConfig(WxGzhConfig wxGzhConfig) {
		this.wxGzhConfig = wxGzhConfig;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		//查询所有商城信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.save() method begin*****");
		}
		wxGzhConfig.setAccessTokenTime(new Date());
		wxGzhConfig.setAccessTokenTimeString(DateUtils.formatDate(wxGzhConfig.getAccessTokenTime(), "yyyy-MM-dd HH:mm:ss"));
		wxGzhConfig.setCode(CodeUtils.getCode());
		wxGzhConfig.setCreateTime(new Date());
		wxGzhConfig.setCreateTimeString(DateUtils.formatDate(wxGzhConfig.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		wxGzhConfig.setModifyTime(new Date());
		wxGzhConfig.setModifyTimeString(DateUtils.formatDate(wxGzhConfig.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		wxGzhConfig.setModifyUserId(users.getId());
		wxGzhConfig.setModifyUserName(users.getUserName());
		if(StringUtils.isNotEmpty(wxGzhConfig.getAppId()) && StringUtils.isNotEmpty(wxGzhConfig.getAppSecret())){
			url+="&appid="+wxGzhConfig.getAppId();
			url+="&secret="+wxGzhConfig.getAppSecret();
		}
		List<WxGzhConfig> list = wxGzhConfigService.getAllWxgzh(null);
		for (WxGzhConfig item: list) {
			if (item.getAppId().equals(wxGzhConfig.getAppId())) {
				json.setStatusCode(300);
				json.setMessage(wxGzhConfig.getAppId()+"应用ID不能重复添加！");
				return AJAX;				
			}			
			if (item.getStoreId().equals(wxGzhConfig.getStoreId())) {
				json.setStatusCode(300);
				json.setMessage("该商城已经配置过公众号，不能重复配置！");
				return AJAX;				
			}
			if(item.getPreUrl().equals(wxGzhConfig.getPreUrl())){
				json.setStatusCode(300);
				json.setMessage("微信公众号 URL前缀不能重复！");
				return AJAX;		
			}
		}
		String accessToken = null;
		String expires_in = null;		
		try {
			String configInfo = getWeixinConfig();
			JSONObject configInfoJo = JSONObject.fromObject(configInfo);			
			accessToken = JSONObject.fromObject(configInfo).getString("access_token");
			expires_in = JSONObject.fromObject(configInfo).getString("expires_in");			
		} catch (Exception e) {
			e.printStackTrace();
            json.setStatusCode(300);
			json.setMessage("请确认应用Id以及应用秘钥填写正确，获取access_token失败");
			return AJAX;
		}
		if(!StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(expires_in)){
			//表示已经获取到了accessToken
			wxGzhConfig.setAccessToken(accessToken);
			wxGzhConfig.setAccessTokenExpires(Integer.parseInt(expires_in));
		}else{
			json.setStatusCode(300);
			json.setMessage("未获取到access_token");
			return AJAX;
		}
		
		
		dto.put("model", wxGzhConfig);
		
		this.doService("WxGzhConfigService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.save() method end*****");
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
			logger.info("***** WxGzhConfigMngAction.list() method begin*****");
		}
		//查询所有商城信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
		this.getRequest().setAttribute("storeList", storeList);
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", wxGzhConfig);
		
		page = (Pagination)this.doService("WxGzhConfigService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.list() method end*****");
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
			logger.info("***** WxGzhConfigMngAction.view() method begin*****");
		}
		//查询所有商城信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
		this.getRequest().setAttribute("storeList", storeList);
		dto.put("model", wxGzhConfig);
		wxGzhConfig = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.view() method end*****");
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
			logger.info("***** WxGzhConfigMngAction.befEdit() method begin*****");
		}
		//查询所有商城信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
		this.getRequest().setAttribute("storeList", storeList);
		dto.put("model", wxGzhConfig);
		wxGzhConfig = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.befEdit() method end*****");
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
			logger.info("***** WxGzhConfigMngAction.edit() method begin*****");
		}
		wxGzhConfig.setAccessTokenTime(new Date());
		wxGzhConfig.setAccessTokenTimeString(DateUtils.formatDate(wxGzhConfig.getAccessTokenTime(), "yyyy-MM-dd HH:mm:ss"));
		wxGzhConfig.setModifyTime(new Date());
		wxGzhConfig.setModifyTimeString(DateUtils.formatDate(wxGzhConfig.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		wxGzhConfig.setModifyUserId(users.getId());
		wxGzhConfig.setModifyUserName(users.getUserName());
		if(StringUtils.isNotEmpty(wxGzhConfig.getAppId()) && StringUtils.isNotEmpty(wxGzhConfig.getAppSecret())){
			url+="&appid="+wxGzhConfig.getAppId();
			url+="&secret="+wxGzhConfig.getAppSecret();
		}		
		String accessToken = null;
		String expires_in = null;		
		try {
			String configInfo = getWeixinConfig();
			JSONObject configInfoJo = JSONObject.fromObject(configInfo);			
			accessToken = JSONObject.fromObject(configInfo).getString("access_token");
			expires_in = JSONObject.fromObject(configInfo).getString("expires_in");			
		} catch (Exception e) {
			e.printStackTrace();
            json.setStatusCode(300);
			json.setMessage("请确认应用Id以及应用秘钥填写正确，获取access_token失败");
			return AJAX;
		}
		if(!StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(expires_in)){
			//表示已经获取到了accessToken
			wxGzhConfig.setAccessToken(accessToken);
			wxGzhConfig.setAccessTokenExpires(Integer.parseInt(expires_in));
		}else{
			json.setStatusCode(300);
			json.setMessage("未获取到access_token");
			return AJAX;
		}
		
		dto.put("model", wxGzhConfig);
		this.doService("WxGzhConfigService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.edit() method end*****");
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
			logger.info("***** WxGzhConfigMngAction.del() method begin*****");
		}
		
		
	
		WxMenu menu = new WxMenu();
		menu.setWxhConfigId(wxGzhConfig.getId());
		dto.put("model", menu);
		List<WxMenu> menus = (List<WxMenu>)this.doService("WxMenuService", "findAll", dto);
		if( menus != null && menus.size()>0){
			json.setStatusCode(300);
			json.setMessage("有菜单存在，删除失败");
			json.setCallbackType(null);
			return AJAX;
		}
		WxTextMaterial wxTextMaterial = new WxTextMaterial();
		wxTextMaterial.setWxhConfigId(wxGzhConfig.getId());
		dto.put("model", wxTextMaterial);
		List<WxTextMaterial> textMaterials = (List<WxTextMaterial>)this.doService("WxTextMaterialService", "findAll", dto);
		
		if( textMaterials != null && textMaterials.size()>0){
			json.setStatusCode(300);
			json.setMessage("有文本素材存在，删除失败");
			json.setCallbackType(null);
			return AJAX;
		}
		WxNewsMaterial wxNewsMaterial = new WxNewsMaterial();
		wxNewsMaterial.setWxhConfigId(wxGzhConfig.getId());
		dto.put("model", wxNewsMaterial);
		List<WxNewsMaterial> wxNewsMaterials = (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		
		if( wxNewsMaterials != null && wxNewsMaterials.size()>0){
			json.setStatusCode(300);
			json.setMessage("有图文素材存在，删除失败");
			json.setCallbackType(null);
			return AJAX;
		}
		WxUser wxUser = new WxUser();
		wxUser.setWxhConfigId(wxGzhConfig.getId());
		dto.put("model", wxUser);
		
		List<WxUser> wxUsers = (List<WxUser>)this.doService("WxUserService", "findAll", dto);
		if( wxUsers != null && wxUsers.size()>0){
			json.setStatusCode(300);
			json.setMessage("有用户存在，删除失败");
			json.setCallbackType(null);
			return AJAX;
		}
		 WxPassiveReply passiveReply = new WxPassiveReply();
		 passiveReply.setWxhConfigId(wxGzhConfig.getId());
		 dto.put("model", passiveReply);
		 List<WxPassiveReply> wxPassiveReplys = (List<WxPassiveReply>)this.doService("WxPassiveReplyService", "findAll", dto);
			if( wxPassiveReplys != null && wxPassiveReplys.size()>0){
				json.setStatusCode(300);
				json.setMessage("有被动自动回复存在，删除失败");
				json.setCallbackType(null);
				return AJAX;
			}
		 WxKeywordsReply keywordsReply = new WxKeywordsReply();
		 keywordsReply.setWxhConfigId(wxGzhConfig.getId());
		 dto.put("model", keywordsReply);
		 List<WxKeywordsReply> keywordsReplys = (List<WxKeywordsReply>)this.doService("WxKeywordsReplyService", "findAll", dto);
			if( keywordsReplys != null && keywordsReplys.size()>0){
				json.setStatusCode(300);
				json.setMessage("有关键字自动回复存在，删除失败");
				json.setCallbackType(null);
				return AJAX;
			}
			
		WxObtainNewsTpl wxObtainNewsTpl = new WxObtainNewsTpl();
		wxObtainNewsTpl.setWxhConfigId(wxGzhConfig.getId());
	    dto.put("model", wxObtainNewsTpl);
		List<WxObtainNewsTpl> wxObtainNewsTpls = (List<WxObtainNewsTpl>)this.doService("WxObtainNewsTplService", "findAll", dto);
		  if( wxObtainNewsTpls != null && wxObtainNewsTpls.size()>0){
				json.setStatusCode(300);
				json.setMessage("有领取图文消息模板存在，删除失败");
				json.setCallbackType(null);
				return AJAX;
			}
			
		
		dto.put("model", wxGzhConfig);
		//删除model
		this.doService("WxGzhConfigService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.del() method end*****");
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
			logger.info("***** WxGzhConfigMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxGzhConfig.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxGzhConfig);
				//删除model
				this.doService("WxGzhConfigService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public String getWeixinConfig()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigMngAction.getWeixinConfig() method begin*****");
		}
		String configInfo = HttpClientUtil.post(url);
		return configInfo;
		
	}

}
