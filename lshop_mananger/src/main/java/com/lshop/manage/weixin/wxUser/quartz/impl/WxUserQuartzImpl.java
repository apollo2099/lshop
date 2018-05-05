package com.lshop.manage.weixin.wxUser.quartz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.ServiceException;
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxUser;
import com.lshop.manage.weixin.util.HttpClientUtil;
import com.lshop.manage.weixin.wxGzhConfig.service.WxGzhConfigService;
import com.lshop.manage.weixin.wxUser.quartz.WxUserQuartz;
import com.lshop.manage.weixin.wxUser.service.WxUserService;
@Service("WxUserQuartz")
public class WxUserQuartzImpl implements WxUserQuartz {
	private static final Log logger	= LogFactory.getLog(WxUserQuartzImpl.class);
	private String getOpenId_url_pre="https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
	@Resource 
	private WxGzhConfigService wxGzhConfigService;
	@Resource
	private WxUserService wxUserService;

	@Override
	public void init() throws ServiceException {
				getAllWxUser();
	}

	@Override
	public void getAllWxUser() {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()){
			logger.info("***** wxUserQuartzImpl.getAllWxUser() method begin*****");
		}
		Dto dto = new BaseDto();
		List<WxGzhConfig> wxGzhConfigs = wxGzhConfigService.getAllWxgzh(null);
		if(wxGzhConfigs!=null){
			for(WxGzhConfig obj : wxGzhConfigs){
				dto.put("model", obj);
				BaseUsers user = new BaseUsers();
				user.setId(0);
				user.setUserName("admin");
				dto.put("users", user);
				String access_token = (String)wxGzhConfigService.getLatestToken(dto);
				
				
				if(access_token!=null && access_token.equals("Error")){
					return;
					
				}
				String open_url_pre="";
				open_url_pre = getOpenId_url_pre;
		        open_url_pre+=access_token;
		        String openIdJson = HttpClientUtil.post(open_url_pre);
		        if(JSONObject.fromObject(openIdJson).get("errcode") == null){
		        int total = JSONObject.fromObject(openIdJson).getInt("total");
		        int count = JSONObject.fromObject(openIdJson).getInt("count");
		        String next_openId= JSONObject.fromObject(openIdJson).getString("next_openid");
		        List<WxUser> wxUsers = new ArrayList<WxUser>();
		        
		        String openids = JSONObject.fromObject(openIdJson).getJSONObject("data").getString("openid");
		        if(total>0 && count>0 && total == count){
		        	//表示一次性获取成功 关注微信用户小于等于10000个
		        	openids = StringUtils.substring(openids, 1, openids.length()-1);
		        	openids = StringUtils.replace(openids, "\"", "");
		        	dto.put("openIds", openids);
		        	dto.put("token_access", access_token);
		        	dto.put("configId", obj.getId());
		        	wxUsers = (List<WxUser>)wxUserService.constructWxUsersByOpenIds(dto);
			        }else{
			        	int other = total;
			        	while(other>count){
			        	String url = open_url_pre;
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
				        	dto.put("configId", obj.getId());
				        	wxUsers = (List<WxUser>)wxUserService.constructWxUsersByOpenIds(dto);
				        }
		        //更新数据库
			       for(WxUser uu : wxUsers){
						dto.put("openId", uu.getOpenid());
						WxUser userObj = wxUserService.getUserIdByOpenId(dto);
						if(userObj == null){
							try{
							  dto.put("model", uu);
							  wxUserService.save(dto);
							  
							}catch (Exception e) {
								System.out.println(uu.getNickname());
								e.printStackTrace();
							}
							
						}else{
							try{	
								userObj.setNickname(uu.getNickname());
								userObj.setModifyTime(new Date());
								dto.put("model", userObj);
							    wxUserService.update(dto);
							}catch (Exception e) {
								System.out.println(userObj.getNickname());
								e.printStackTrace();
							}
						}
					}
					
				}
			
			
		}
	}
		if (logger.isInfoEnabled()){
			logger.info("***** wxUserQuartzImpl.getAllWxUser() method end*****");
		}
}

	@Override
	public void run() {
		new Thread(this).start();
	}

}
