package com.lshop.web.userCenter.three.facebook.contraller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import com.lshop.common.action.BaseAction;
import com.lshop.common.util.HttpUtil;
import com.lshop.web.userCenter.three.facebook.entity.FacebookUser;
import com.lshop.web.userCenter.three.facebook.utils.FacebookConfig;

/**
 * Facebook第三方授权操作类
 * @author liaoxj
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
public class FacebookClient extends BaseAction {
	private static final Log logger = LogFactory.getLog(FacebookClient.class);
	private FacebookUser user;
	private FacebookClient(){}

	private static FacebookClient client=null;
	
	public synchronized  static FacebookClient getInstance(){
		if(client==null){
			client=new FacebookClient();
		}
		return client;
	}
	
	/**
	 * 
	 * @Method: getOauthToken 
	 * @Description:  [获取Facebook登录鉴权]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-8 下午6:21:59]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-8 下午6:21:59]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return
	 * @throws IOException 
	 * @return String
	 */
	public String getOauthToken() throws IOException{
		//auth_token
		FacebookConfig config=new FacebookConfig();
		
		String oauthUrl="https://graph.facebook.com/oauth/authorize?client_id="+config.getAppId()+
				"&redirect_uri="+config.getAppUrl()+
				"&scope=user_about_me,user_activities,user_birthday,user_education_history,user_events,user_groups,user_hometown,user_interests,user_likes,user_location,user_notes,user_online_presence,user_photo_video_tags,user_photos,user_relationships,user_religion_politics,user_status,user_videos,user_website,user_work_history,read_friendlists,read_requests,publish_stream,create_event,rsvp_event,sms,offline_access,friends_about_me,friends_activities,friends_birthday,friends_education_history,friends_events,friends_groups,friends_hometown,friends_interests,friends_likes,friends_location,friends_notes,friends_online_presence,friends_photo_video_tags,friends_photos,friends_relationships,friends_religion_politics,friends_status,friends_videos,friends_website,friends_work_history,read_stream,photo_upload";
		System.out.println(oauthUrl);
		return oauthUrl;
	}
	
	/**
	 * 根据code参数获取授权Url
	 * 
	 * @param code
	 *            地址栏的参数(auth_token)
	 * @return
	 */
	public String getAccessTokenByCode(String code) {
		FacebookConfig config=new FacebookConfig();
		String url = "https://graph.facebook.com/oauth/access_token?client_id="
				+ config.getAppId() + "&redirect_uri=" + config.getAppUrl() + "&client_secret="
				+ config.getSecret() + "&code=" + code;

		//发送授权请求
		String accessToken=HttpUtil.get(url);
		
		logger.info("facebook登录获取授权URL时访问的URL：\n" + url);
		logger.info("facebook登录获取授权accessToken：\n" + accessToken);
		
		return accessToken;
	}


	/**
	 * 根据获得的访问标记accessToken访问获取用户个人信息的json数据
	 * 
	 * @param accessToken
	 *            访问标记
	 * @return
	 */
	public JSONObject getFacebookUserJsonByAccessToken(String accessToken) {
		try {			
//			String line=null;
//			InputStream input=new URL("https://graph.facebook.com/me?access_token="+ accessToken).openStream();  
//			
//	        //读取返回相应
//			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//			String lines;
//			StringBuffer data = new StringBuffer("");
//			while ((lines = reader.readLine()) != null) {
//				lines = new String(lines.getBytes(), "utf-8");
//				data.append(lines);
//			}
//			reader.close();
			String url="https://graph.facebook.com/me?access_token="+ accessToken;
			String data=HttpUtil.get(url);
	        
			JSONObject resp = JSONObject.fromObject(data);
			logger.info("根据访问标记accessToken访问获取用户个人信息的json数据:\n"+resp.toString());
			//
			return resp;
		} catch (Throwable ex) {
			throw new RuntimeException("failed login", ex);
		}
	}

	/**
	 * 根据用户个人信息的json数据，进行我们需要的本地化处理，比如：存入数据库等等
	 * @param json 用户个人信息的json数据
      {"id":"1497833350505067","first_name":"Fiona","gender":"female","last_name":"Wong",
      "link":"https://www.facebook.com/app_scoped_user_id/1497833350505067/","locale":"zh_HK",
      "name":"Fiona Wong","timezone":8,"updated_time":"2014-12-09T03:45:08+0000","verified":true}
	 * @return
	 */
	public FacebookUser localizeFacebookUser(JSONObject json) {
		user = new FacebookUser();
		try {
			String name = json.getString("name");
			if (name != null)
				user.setName(name);
			else
				user.setName("no register name");

			user.setUserName(json.getString("first_name"));
//			user.setEmail(json.getString("email"));
			user.setSnsId(json.getString("id"));
		} catch (JSONException e) {
			logger.error("解析facebook返回的json数据失败:\n", e);
		}
		return user;
	}
	

}
