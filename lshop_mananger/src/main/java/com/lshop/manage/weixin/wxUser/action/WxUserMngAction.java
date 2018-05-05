/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxUser.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.DwzJsonResponse;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.exception.ServiceException;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.pojo.WxUser;
import com.lshop.manage.weixin.util.DateUtil;
import com.lshop.manage.weixin.util.HttpClientUtil;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxUserMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxUserMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxUserMngAction.class);
	
	private WxUser wxUser = new WxUser();
	private String getOpenId_url_pre="https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
	private String getWxUserInfo_url_pre="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
	public  String token_url_pre ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxUserMngAction.save() method begin*****");
		}
		wxUser.setModifyTime(new Date());
		dto.put("model", wxUser);
		this.doService("WxUserService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxUserMngAction.save() method end*****");
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
			logger.info("***** WxUserMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", wxUser);
		
		page = (Pagination)this.doService("WxUserService", "findPage", dto);
		getWxgzhAndNewsMaterial();
    	if (logger.isInfoEnabled()){
			logger.info("***** WxUserMngAction.list() method end*****");
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
			logger.info("***** WxUserMngAction.view() method begin*****");
		}
		getWxgzhAndNewsMaterial();
		dto.put("model", wxUser);
		wxUser = (WxUser)this.doService("WxUserService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxUserMngAction.view() method end*****");
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
			logger.info("***** WxUserMngAction.befEdit() method begin*****");
		}
		getWxgzhAndNewsMaterial();
		dto.put("model", wxUser);
		wxUser = (WxUser)this.doService("WxUserService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxUserMngAction.befEdit() method end*****");
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
			logger.info("***** WxUserMngAction.edit() method begin*****");
		}
		dto.put("model", wxUser);
		this.doService("WxUserService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxUserMngAction.edit() method end*****");
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
			logger.info("***** WxUserMngAction.del() method begin*****");
		}
		dto.put("model", wxUser);
		//删除model
		this.doService("WxUserService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxUserMngAction.del() method end*****");
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
			logger.info("***** WxUserMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxUser.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxUser);
				//删除model
				this.doService("WxUserService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxUserMngAction.delList() method end*****");
		}
		return AJAX;
	}
	
	
	public String getWeixinUsersById()throws ActionException{
		WxGzhConfig wxGzhConfig = new WxGzhConfig();
		wxGzhConfig.setId(wxUser.getWxhConfigId());
		dto.put("model", wxGzhConfig);
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		dto.put("users", users);
		String access_token = (String)this.doService("WxGzhConfigService", "getLatestToken", dto);
		
		
		if(access_token!=null && access_token.equals("Error")){
			json.setStatusCode(300);
			json.setMessage("获取微信用户信息失败");
			return AJAX;
			
		}
        getOpenId_url_pre+=access_token;
        String openIdJson = HttpClientUtil.post(getOpenId_url_pre);
        if(JSONObject.fromObject(openIdJson).get("errcode") == null){
        int total = JSONObject.fromObject(openIdJson).getInt("total");
        int count = JSONObject.fromObject(openIdJson).getInt("count");
        String next_openId= JSONObject.fromObject(openIdJson).getString("next_openid");
        List<WxUser> wxUsers = new ArrayList<WxUser>();
        if(total == 0 || count ==0){
        	//表示微信公众号没有用户
        	 json.setStatusCode(200);
             json.setCallbackType(null);
             json.setMessage("获取微信用户列表成功,该公众号暂时没有用户关注");
     		 return AJAX;
        }
        String openids = JSONObject.fromObject(openIdJson).getJSONObject("data").getString("openid");
        if(total>0 && count>0 && total == count){
        	//表示一次性获取成功 关注微信用户小于等于10000个
        	openids = StringUtils.substring(openids, 1, openids.length()-1);
        	openids = StringUtils.replace(openids, "\"", "");
        	dto.put("openIds", openids);
        	dto.put("token_access", access_token);
        	dto.put("configId", wxUser.getWxhConfigId());
        	wxUsers = (List<WxUser>)this.doService("WxUserService", "constructWxUsersByOpenIds", dto);
	        }else{
	        	int other = total;
	        	while(other>count){
	        	String url = getOpenId_url_pre;
	        	url+="&next_opendid="+next_openId;
	        	openIdJson = HttpClientUtil.post(url);
	            next_openId= JSONObject.fromObject(openIdJson).getString("next_openid");
	              
	            String ids = JSONObject.fromObject(openIdJson).getJSONObject("data").getString("openid");
	            if(StringUtils.isEmpty(next_openId)&& total>0 && count>0 && total == count){
	            	ids = StringUtils.substring(ids, 1, ids.length()-1);
	            	ids = StringUtils.replace(ids, "\"", "");
	            	openids+=","+ids;
	            }
	            other = total-count;
	        	}
	        	dto.put("openids", openids);
	        	dto.put("token_access", access_token);
	        	dto.put("configId", wxUser.getWxhConfigId());
	        	wxUsers = (List<WxUser>)this.doService("WxUserService", "constructWxUsersByOpenIds", dto);
	        }
        
        this.saveOrUpdateAll(wxUsers);
        }else{
        	json.setStatusCode(300);
			json.setMessage("获取微信用户信息失败");
			return AJAX;
        }
        json.setStatusCode(200);
        json.setCallbackType(null);
        json.setMessage("获取微信用户列表成功");
		return AJAX;
	}
	
	private void getWxgzhAndNewsMaterial(){
		List<WxGzhConfig> list = (List<WxGzhConfig>)this.doService("WxGzhConfigService", "getAllWxgzh", dto);
		this.request.setAttribute("wxhConfigList", list);
	}
	
	private void saveOrUpdateAll(List<WxUser> users) throws ActionException{
		for(WxUser user : users){
			dto.put("openId", user.getOpenid());
			WxUser obj = (WxUser)this.doService("WxUserService", "getUserIdByOpenId", dto);
			if(obj == null){
				try{
				  dto.put("model", user);
				  this.doService("WxUserService", "save", dto);
				  
				}catch (Exception e) {
					System.out.println(user.getNickname());
					e.printStackTrace();
				}
				
			}else{
				try{	
				obj.setNickname(user.getNickname());
				obj.setModifyTime(new Date());
				obj.setWxhConfigId(user.getWxhConfigId());
				dto.put("model", obj);
				  this.doService("WxUserService", "update", dto);
				}catch (Exception e) {
					System.out.println(obj.getNickname());
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}
	

	

}
