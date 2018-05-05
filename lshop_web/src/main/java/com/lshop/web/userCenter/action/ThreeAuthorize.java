package com.lshop.web.userCenter.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.gv.epg.model.user.service.EpgUserService;
import com.gv.epg.pojo.UcEpgUserInfo;
import com.gv.epg.ucapi.thread.HTTPSSecureProtocolSocketFactory;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.ShoppingCartBeanForCookie;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.common.pojo.logic.LvUserTh;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.AppCookieUtil;
import com.lshop.common.util.Constants;
import com.lshop.common.util.HttpUtil;
import com.lshop.common.util.LanguageUtil;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.activity.service.RegistService;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.userCenter.UserCommon;
import com.lshop.web.userCenter.UserConstant;
import com.lshop.web.userCenter.UserStatus;
import com.lshop.web.userCenter.bbs.UcenterSSO;
import com.lshop.web.userCenter.three.facebook.contraller.FacebookClient;
import com.lshop.web.userCenter.three.facebook.entity.FacebookUser;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.qzone.UserInfoBean;

/**
 * 第三方授权登录qq and weibo 登录注册授权
 * 
 * @author tangd
 * 
 */
@SuppressWarnings("serial")
@Controller("ThreeAuthorize")
@Scope("prototype")
public class ThreeAuthorize extends BaseAction {
	private static final Log logger = LogFactory.getLog(ThreeAuthorize.class);
	private LvAccount lvAccount; // 账户表
	private LvUserTh lvUserTh = new LvUserTh();// 绑定关联表
	private String code; // 验证码
	private String jumpurl;
	private String uname; // 登录用户名
	private String pwd; // 密码
	private String newPwd; // 新密码
	private EpgUserService epgUserService = new EpgUserService();
	private String loginstyle;
	private String synurl;// 论坛同步代码
	private String token;// 第三方授权用户标识
	private String thnick;// 第三方授权用户昵称


	@Resource
	private RegistService registService;
	@Resource
	private OnlineMallService onlineMallService;
	/**
	 * 转向微博
	 * 
	 * @return
	 * @throws Exception
	 */
	public String weibo() throws Exception {
		//加载weibo配置
		String config=Constants.ConfigParam.getMallConfigParam("weibo.config");
		JSONObject  json= JSONObject.fromObject(config);
		for(Object key : json.keySet()){  
			com.lshop.web.userCenter.three.weibo4j.util.WeiboConfig.updateProperties((String)key, json.getString((String)key));
		}  
		com.lshop.web.userCenter.three.weibo4j.Oauth oauth = new com.lshop.web.userCenter.three.weibo4j.Oauth();
		this.getResponse().sendRedirect(oauth.authorize("code"));
		return null;
	}

	// 微博回调地址
	public String weiboUrl() throws Exception {
		String code = this.getRequest().getParameter("code");
		if(this.getRequest().getParameter("error_uri")!=null){
		    this.getResponse().sendRedirect("/index.html");
			return null;
		}
		com.lshop.web.userCenter.three.weibo4j.Oauth oauth = new com.lshop.web.userCenter.three.weibo4j.Oauth();
		com.lshop.web.userCenter.three.weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
		String uid = accessToken.getUid();// 获取微博的用户id
		logger.info("========================微博授权：" + uid + "|accessToken"
				+ accessToken.getAccessToken());
		token = uid;// 标识
		dto.put("openId", uid);
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));
		this.lvUserTh.setThType(2);//微博类型
		dto.put("thType", lvUserTh.getThType());
		LvUserTh userTh = (LvUserTh) this.doService("AuthorizeService","getLvUserTh", dto);
		if (userTh != null) {// 用户已绑定
			String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); // 获取当前店铺所属系统（华扬orBanana）
			dto.put("uid", userTh.getUserCode());
			LvAccount user = (LvAccount) this.doService("UserCenterService","getAccount", dto);
			this.login(user, mallSystem,Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));// 登录
			return "index";
		}
		return "authorize";
	}
	


	/**
	 * 转向qq
	 * 
	 * @return
	 * @throws Exception
	 */
	public String qq() throws Exception {
		String config=Constants.ConfigParam.getMallConfigParam("qq.config");
		JSONObject  json= JSONObject.fromObject(config);
		for(Object key : json.keySet()){  
			com.qq.connect.utils.QQConnectConfig.updateProperties((String)key, json.getString((String)key));
		}  
		com.qq.connect.oauth.Oauth oauth = new com.qq.connect.oauth.Oauth();
		this.getResponse().sendRedirect(oauth.getAuthorizeURL("1"));
		return null;
	}

	public String qqUrl() throws Exception {
		// 利用获取到的accessToken 去获取当前用的openid -------- start
		String config=Constants.ConfigParam.getMallConfigParam("qq.config");
		JSONObject  json= JSONObject.fromObject(config);
		String reuslt=HttpUtil.get("https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="+json.getString("app_ID")+"&client_secret="+json.getString("app_KEY")+"&code="+code+"&redirect_uri="+json.getString("redirect_URI"));
		if(reuslt==null||"".equals(reuslt)){
//          我们的网站被CSRF攻击了或者用户取消了授权
//          做一些数据统计工作
			logger.info("========================qq授权：accessToken为空");
			return "";
		}
		logger.info("========================qq授权"+reuslt);
		String  []res=reuslt.split("&");
		String accessToken=(res[0].split("="))[1];
		OpenID openIDObj = new OpenID(accessToken);
		String openID = openIDObj.getUserOpenID();
		logger.info("========================qq授权：" + openID + "|accessToken"+ accessToken);
		token = openID;// 标识

			
		dto.put("openId", openID);
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));
		this.lvUserTh.setThType(1);// qq类型
		dto.put("thType", lvUserTh.getThType());
		LvUserTh userTh = (LvUserTh) this.doService("AuthorizeService","getLvUserTh", dto);
		if (userTh != null) {// 用户已绑定
			String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); // 获取当前店铺所属系统（华扬orBanana）
			dto.put("uid", userTh.getUserCode());
			LvAccount user = (LvAccount) this.doService("UserCenterService","getAccount", dto);
			this.login(user, mallSystem,Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));// 登录
			return "index";
		}
		
		//获取qq昵称
		  UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
          UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
          String qqnick=userInfoBean.getNickname();
          thnick=URLEncoder.encode(qqnick,"UTF-8");
		 
  		//处理统一用户证书重置问题  
    	Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 7043);
    	Protocol.registerProtocol("https", https);
		
		return "authorize";
	}
	
	/**
	 * 
	 * @Method: facebook 
	 * @Description:  [转向facebook登录页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-8 上午11:15:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-8 上午11:15:21]   
	 * @UpdateRemark: [登录鉴权之后会回调到你给定的redirect_uri，此时会在你的url后面带上facebook给定的结果参数，返回auth_token
	 * @throws Exception 
	 * @return String
	 */
	public String facebook() throws Exception {
		//登录鉴权，返回认证auth_token
		String oauthUrl= FacebookClient.getInstance().getOauthToken();
		this.getResponse().sendRedirect(oauthUrl);
		return null;
	}
	
	/**
	 * 
	 * @Method: facebookUrl 
	 * @Description:  [facebook认证成功回调]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-8 上午11:19:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-8 上午11:19:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws Exception 
	 */
	public String facebookUrl() throws Exception{
		String authToken= this.getRequest().getParameter("code");
		//根据authToken登录授权获取accessToken访问授权
		FacebookClient client=FacebookClient.getInstance();
		/**
		 * access_token=CAAKZB569lJl8BAK1iK0a0W6iq224PC659coBeP3L7XvVisuwPuNcmItBQFx1bDQ53INXbDzyCUZCRZB49jRZBqLYZC4FZAZBcNafiEZCTMJhohLxGzT0eyEctX2oImJlZBScD79YwNhkGoWJJLUPmdpXOHq4eTqunEWWQhnkKSr2ycWRda0TBnZAQ5wsxkBiajC2r2NYPB3kFXvwj5VwFKscsZB&expires=5183978
		 */
		String accessTokenStr=client.getAccessTokenByCode(authToken);
		String[] pairs = accessTokenStr.split("&");
		String[] keyValue;
		Integer expires=0;
		String accessToken=null;
		FacebookUser facebookUser=null;
		for (String pair : pairs) {
			keyValue = pair.split("=");
			if (keyValue.length != 2) {
				logger.error("facebook访问授权URL获取keyValue对出错,原因：\n",
						new RuntimeException("Unexpected auth response"));
			} else {
				//获取accessToken
				if (keyValue[0].equals("access_token")) {
					accessToken = keyValue[1];
					if (accessToken != null) {
						//将accessToken放入session，避免重复获取
						this.getRequest().setAttribute("accessToken",accessToken);

						//查询facebook用户信息
						JSONObject jsonOjbect=client.getFacebookUserJsonByAccessToken(accessToken);
						facebookUser=client.localizeFacebookUser(jsonOjbect);
						this.getRequest().setAttribute("localisedFacebookUser", facebookUser);
					} else {
						logger.info("facebook 获取accessToken失败：\naccessToken="+ accessToken);
					}
				}
				//获取expires
				if (keyValue[0].equals("expires")) {
					expires = Integer.valueOf(keyValue[1]);
					//将expires放入session，避免重复获取
					this.getRequest().setAttribute("expires", expires);
				}
			}
		}
	
 		token=facebookUser.getSnsId();//第三方授权用户标示
 		thnick=URLEncoder.encode(facebookUser.getName(),"UTF-8");//第三方授权用户昵称

 		
 		dto.put("openId", facebookUser.getSnsId());//????
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));
		dto.put("thCode", facebookUser.getSnsId());//userid
		this.lvUserTh.setThType(3);//Facebook类型
		dto.put("thType", 3);//Facebook类型
		LvUserTh userTh = (LvUserTh) this.doService("AuthorizeService","getLvUserTh", dto);
		if (userTh != null) {// 用户已绑定
			String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); // 获取当前店铺所属系统（华扬orBanana）
			dto.put("uid", userTh.getUserCode());
			LvAccount user = (LvAccount) this.doService("UserCenterService","getAccount", dto);
			this.login(user, mallSystem,Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));// 登录
			return "index";
		}
		
		//处理统一用户证书重置问题
    	Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 7043);
    	Protocol.registerProtocol("https", https);
 		
		
		return "authorize";
	}
	
//	public String twitter() throws TwitterException, IOException, ServletException{
//		String key= HttpsUtils.encodeKeys("j9ukvbtUEw3jPqhebeRZtOBI3", "WZRiiAqNhSBLWJpLUqFkDt6WacEMikcZsTVAUv9vHE53CPHpBf");
//		String temp= HttpsUtils.requestBearerToken2("https://api.twitter.com/oauth2/token");
//        System.out.println(temp);
//		 
//         String consumerKey = "j9ukvbtUEw3jPqhebeRZtOBI3";   
//	     String consumerSecret = "WZRiiAqNhSBLWJpLUqFkDt6WacEMikcZsTVAUv9vHE53CPHpBf";   
//	     String CALLBACKURL="http://www.itvpad.com/web/threeauth!twitterUrl.action";
//
////		 Twitter twitter = new TwitterFactory().getInstance();   
////		 twitter.setOAuthConsumer(consumerKey, consumerSecret);   
//		 
//  
//
//	    //OAuth有两种Token，分别是RequestToken和AccessTonke。 
//	    //通过OAuth Consumer的key和secret就可以获取RequestTokenKey和RequestTokenSecret：
//			
//		ConfigurationBuilder cb = new ConfigurationBuilder();
//
////		cb.setDebugEnabled(true)
////				.setOAuthConsumerKey(consumerKey)
////				.setOAuthConsumerSecret(consumerSecret)
//////				.setOAuthRequestTokenURL("https://api.twitter.com/oauth/request_token")
//////				.setOAuthAuthorizationURL("https://api.twitter.com/oauth/authorize")
//////				.setOAuthAccessTokenURL("https://api.twitter.com/oauth/access_token")
////				;
////		TwitterFactory tf = new TwitterFactory(cb.build());
////		Twitter twitter = tf.getInstance();
//		
//		
//		 Twitter twitter = TwitterFactory.getSingleton();
//		    twitter.setOAuthConsumer("j9ukvbtUEw3jPqhebeRZtOBI3", "WZRiiAqNhSBLWJpLUqFkDt6WacEMikcZsTVAUv9vHE53CPHpBf");
//		   
//		
//		this.getRequest().getSession().setAttribute("twitter", twitter);
//		try {
//			StringBuffer callbackURL = this.getRequest().getRequestURL();
//			System.out.println("TwitterLoginServlet:callbackURL:" + callbackURL);
//			int index = callbackURL.lastIndexOf("/");
//			callbackURL.replace(index, callbackURL.length(), "").append("/threeauth!twitterUrl.action");
//
//			RequestToken requestToken = twitter.getOAuthRequestToken();
////			RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
//			this.getRequest().getSession().setAttribute("requestToken", requestToken);
//			String token = requestToken.getToken();
//			String tokenSecret = requestToken.getTokenSecret();
//			System.out.println("Got request token.");
//			System.out.println("Request token: " + requestToken.getToken());
//			System.out.println("Request token secret: "+ requestToken.getTokenSecret());
//			System.out.println("requestToken.getAuthenticationURL():"+ requestToken.getAuthenticationURL());
//			//跳转到Twitter OAuth验证页面 
//			this.getResponse().sendRedirect(requestToken.getAuthenticationURL());
//
//		} catch (TwitterException e) {
//			throw new ServletException(e);
//		}
// 
//	     return null;
//
//	}
//	
//	public String twitterUrl() throws TwitterException, ServletException{
////		Twitter twitter = new Twitter(); twitter.setOAuthConsumer( "[your consumer key] ",  "[your consumer secret] ");   
//		//获取之前保存的requestTokenKey和requestTokenSecret 
////		String token = Utility.getCookie( "tonke ", this.getRequest()); 
////		String tokenSecret = Utility.getCookie( "tokenSecret ", this.getRequest());   
//		//通过requestTokenKey和requestTokenSecret获得accessToken，包含了accessTokenKey和accessTokenSecret 
//
//
//		
////		AccessToken accessToken = twitter.getOAuthAccessToken(token, tokenSecret); 
//		//把accessToken给twitter，twitter这个实例就算登陆了，效果等同于twitter = new twitter( "name ", "password ") 
//		
//		//在这里就可以updateStatus了 
////		twitter.updateStatus( "OAuth登陆成功 ");   
//		
//		//把accessTokenKey和accessTokenSecret保存起来，下次就不用再验证了
////		Utility.setCookie( "accessToken ",accessToken.getToken(), true, this.getResponse());
////		Utility.setCookie( "accessTokenSecret ", accessToken.getTokenSecret(), true,this.getResponse()); 
//		
//		
//		Twitter twitter = (Twitter) this.getRequest().getSession().getAttribute("twitter");
//		RequestToken requestToken = (RequestToken) this.getRequest().getSession().getAttribute("requestToken");
//		System.out.println("TwitterCallbackServlet:requestToken:"+ requestToken);
//		String verifier = this.getRequest().getParameter("oauth_verifier");
//		try {
//			twitter.getOAuthAccessToken(requestToken, verifier);
//			this.getRequest().getSession().removeAttribute("requestToken");
//		} catch (TwitterException e) {
//			throw new ServletException(e);
//		}
//		return "authorize";
//	}
	
	/**
	 * redirect重定向到授权页面
	 * @return
	 * @throws Exception
	 */
	public String authorize() throws Exception{
		
		return "redirectauthorize";
	};
	/**
	 * 注册并绑定
	 * 
	 * @return
	 * @throws Exception
	 */
	public String registerBuild() throws Exception {
		// 判断验证码填写是否正确
		if (StringUtil.IsNullOrEmpty(code)
				|| !code.trim().equals(this.getSession().getAttribute("rcode"))) {
			this.getRequest().setAttribute("reg_erro", "erro");
			return userReturn(UserStatus.REG_CODE_ERRO);
		}
		return userReturn(commonRegist());
	}

	// 注册并登陆
	private int commonRegist() throws Exception {
		dto.put("storeFlag", this.getFlag());
		String myPwd = lvAccount.getPwd();
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); // 获取当前店铺所属系统（华扬orBanana）
		if (!StringUtil.IsNullOrEmpty(lvAccount.getNickname())) {
			lvAccount.setNickname(lvAccount.getNickname().trim());
		} else {// 註冊時去掉暱稱
			lvAccount.setNickname("");
		}

		// 验证账号是否存在
		lvAccount.setEmail(lvAccount.getEmail().trim());
		dto.setDefaultPo(lvAccount);
		Boolean flag = UcenterForEpg.checkUserExist(dto);
		if (flag) {
			this.getRequest().setAttribute("reg_erro", "erro");
			return UserStatus.REG_ERRO;
		}

		/**
		 * 同步註冊用戶中心
		 */
		UcEpgUserInfo userInfo = null;
		userInfo = UcenterForEpg.uc_synregist(lvAccount.getEmail(), myPwd,
				LanguageUtil.getLan(this.getFlag()), this.getFlag(), dto);
		if (null == userInfo) {
			this.getRequest().setAttribute("reg_erro", "erro");
			return UserStatus.REG_ERRO;
		}

		lvAccount.setCode(userInfo.getUserCode());
		dto.setDefaultPo(lvAccount);
		// 保存账号
		LvAccount user = (LvAccount) doService("UserCenterService",
				"addAccount", dto);
		if (user != null) {
			// 同步论坛注册
			try {
				UcenterSSO.regist(userInfo.getEmail(), myPwd.trim(),
						userInfo.getEmail(), "");
			} catch (Exception e) {

			}
			login(user, mallSystem, myPwd);// 执行登陆
			String ucsynlogin = UcenterSSO.login(user.getEmail(), myPwd.trim(),
					user.getEmail());
			this.setSynurl(UcenterSSO.getSynUrl(
					UcenterSSO.filterDomain(this.getFlag(), ucsynlogin),
					UcenterSSO.bbs_banana_domain));
			setSuitableJumpUrl();
			// 商品用户注册成功活动回调
			String mallFlag = Constants.STORE_TO_MALL_SYSTEM
					.get(this.getFlag());
			registService.asyncRegisted(user.getCode(), mallFlag);
			this.bind(user.getCode(), token);
			return UserStatus.REG_SUC;
		} else {
			this.getRequest().setAttribute("reg_erro", "erro");
			return UserStatus.REG_ERRO;
		}
	}

	/**
	 * 已有账号绑定
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buildAccount() throws Exception {
		if (null == this.loginstyle || this.loginstyle.equals("")) {
			this.loginstyle = "1";
		}

		if (!this.getFlag().equals("mbmcn") && !this.getFlag().equals("mtmcn")) {// 手机端不用验证验证码
			if (this.loginstyle.equals("1")) {// 如果是頁面登陸則判断验证码填写是否正确
				code = (null == code) ? "" : code;
				if (!code.trim()
						.equals(this.getSession().getAttribute("rcode"))) {
					return userReturn(-5);
				}
			}
		}

		if (StringUtil.IsNullOrEmpty(uname.trim())
				|| StringUtil.IsNullOrEmpty(pwd.trim())) {
			return userReturn(-1);
		}

		/**
		 * 從用戶中心登陸
		 */
		UcEpgUserInfo userInfo = null;
		Map<String, Object> map = UcenterForEpg.uc_synlogin(uname.trim(),
				pwd.trim(), this.getFlag(), dto);
		int statu = Integer.valueOf(map.get("statu").toString()).intValue();
		if (statu != 1) {
			this.getRequest().setAttribute("erromsg", statu);
			return userReturn(statu);
		}
		userInfo = (UcEpgUserInfo) map.get("userInfo");
		dto.put("uid", userInfo.getUserCode());
		dto.put("email", userInfo.getEmail());
		// 用戶中心登陸成功后直接獲取用戶信息進行登陸
		LvAccount user = (LvAccount) super.doService("UserCenterService",
				"getAccountByEmail", dto);

		// 获取商城标识
		String mallFlag = StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag = mallFlag == null ? this.getFlag() : mallFlag;
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); // 获取当前店铺所属系统（华扬orBanana）
		if (user != null) {
			user.setLastTime(userInfo.getLoginTime());
			user.setNickname(userInfo.getNickname());
			login(user, mallSystem, mallFlag);
			String ucsynlogin = UcenterSSO.login(user.getEmail(), pwd.trim(),
					user.getEmail());
			this.setSynurl(UcenterSSO.getSynUrl(
					UcenterSSO.filterDomain(this.getFlag(), ucsynlogin),
					UcenterSSO.bbs_banana_domain));
		}else{
			lvAccount = new LvAccount();
			if(null == userInfo.getNickname() || userInfo.getNickname().equals("")){
				lvAccount.setNickname(userInfo.getEmail());
			}else{
				lvAccount.setNickname(userInfo.getNickname());
			}
			lvAccount.setEmail(userInfo.getEmail());
			lvAccount.setPwd(userInfo.getPassword());
			lvAccount.setCode(userInfo.getUserCode());
			lvAccount.setUserType(Short.valueOf("0"));
			lvAccount.setStatus(Short.valueOf("1"));
			lvAccount.setStoreId(mallFlag);
			lvAccount.setCreateTime(userInfo.getCreateTime());
			lvAccount.setLastTime(userInfo.getLoginTime());
			dto.setDefaultPo(lvAccount);
			 //保存账号
			LvAccount u=(LvAccount)doService("UserCenterService", "addAccount",dto);
			login(u, mallSystem, mallFlag);
			String ucsynlogin = UcenterSSO.login(u.getEmail(), pwd.trim(), u.getEmail());
			this.setSynurl(UcenterSSO.getSynUrl(UcenterSSO.filterDomain(this.getFlag(), ucsynlogin), 
					UcenterSSO.bbs_banana_domain));
		}
		setSuitableJumpUrl();
		this.bind(user.getCode(), this.getRequest().getParameter("token"));// 绑定账号
		return userReturn(statu);
	}

	/**
	 * 新增绑定
	 */
	private void bind(String userCode, String token) {
		this.lvUserTh.setUserCode(userCode);
		this.lvUserTh.setMallFlag(Constants.STORE_TO_MALL_SYSTEM.get(this
				.getFlag()));
		this.lvUserTh.setThCode(token);
		this.lvUserTh.setStatus(1);
		Date date = new Date();
		this.lvUserTh.setCreateTime(date);
		this.lvUserTh.setUpdateTime(date);
		this.dto.put("lvUserTh", this.lvUserTh);
		// 对账号进行绑定
		this.doService("AuthorizeService", "bind", dto);
	}

	public String success() throws Exception {

		return "success";
	}

	private String userReturn(int mark) throws IOException {
		/** callback为跨域请求返回标记 */
		String callback = this.getRequest().getParameter("callback");
		PrintWriter out = this.getResponse().getWriter();
		String json = "{'mark':" + mark + ",'jumpurl':'" + jumpurl
				+ "','synurl':'" + synurl + "'}";
		if (!StringUtil.IsNullOrEmpty(callback)) {
			json = callback + "(" + json + ")";
		}
		out.print(json);
		out.close();
		return null;
	}

	private void login(LvAccount user, String mallSystem, String mallFlag)
			throws Exception {
		UcEpgUserInfo userInfo = null;
		dto.put("usercode", user.getCode());
		UcEpgUserInfo info = UcenterForEpg.getUserInfo(this.getFlag(),dto);
		user.setNickname(info.getNickname());
		if (null == user.getNickname() || user.getNickname().equals("")) {
			String email = user.getEmail();
			user.setNickname(email.substring(0, email.indexOf("@")));
		}
		this.addCookie(UserConstant.USER_ID, user.getCode().toString(), true,
				-1, Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastTime = format.format(user.getLastTime());
		String str = "uid=" + user.getCode().toString() + "*id=" + user.getId()
				+ "*email=" + user.getEmail() + "*nickname="
				+ user.getNickname() + "*lastTime=" + lastTime;
		this.addCookie(UserConstant.USER_CENTER, str, true, -1,
				Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		// 保存一个加密的cookie，用于拦截比较
		String MD5Str = "uid=" + MD5.convert32(user.getCode().toString())
				+ "*id=" + MD5.convert32(user.getId().toString()) + "*email="
				+ MD5.convert32(user.getEmail()) + "*nickname="
				+ MD5.convert32(user.getNickname());
		this.addCookie(UserConstant.MD5_USER_CENTER, MD5Str, true, -1,
				Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));

		// 判断cookie中是否有购物车信息
		Map<String, String> shopCart = getCookieValueToMap(
				UserConstant.SHOPCART, true);
		if (null != shopCart && shopCart.containsKey("list")
				&& shopCart.containsKey("num")) {

			// 获取购物车信息
			String shopCartList = shopCart.get("list");
			List<ShoppingCartBeanForCookie> list = AppCookieUtil.parseString(
					shopCartList, "@", "#", ":");
			if (null != list && list.size() > 0) {
				onlineMallService.loginMergeShopCart(list, user.getCode());
			}
		}
		// 将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
		dto.put("userCode", user.getCode());
		dto.put("storeFlag", this.getFlag());
		Integer shopCartNum = (Integer) this.doService("OnlineMallService",
				"getShopCartNum", dto);
		this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true,
				-1, Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		this.removeByCookie(UserConstant.SHOPCART,
				Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
	}

	private String findGet(String url1) {
		String body = "";
//		HttpClient client = new HttpClient();
//		GetMethod post = new GetMethod(url);// 登陆或注册的提交路径
//		// post.setRequestHeader("Content-Type",
//		// "application/x-www-form-urlencoded; charset=UTF-8");
//		// post.setRequestHeader("User-Agent", "gvhttp");
//		try {
//			// 获取访问状态值
//			int statusCode = client.executeMethod(post);
//			// //System.out.println(statusCode);
//			// 状态码200表示请求成功
//			if (HttpStatus.SC_OK == statusCode) {
//				body = post.getResponseBodyAsString();
//			} else if (302 == statusCode) {
//				Header header = post.getResponseHeader("location");
//				if (header != null) {
//					String newUrl = header.getValue(); // 获得跳转页面地址
//					body = newUrl;
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			return body;
//		}
		
		
		
		 String getUrl = url1;  
		 URL url=null;
		 HttpURLConnection uRLConnection=null;
	        try {  
	            url = new URL(getUrl);  
	            uRLConnection = (HttpURLConnection)url.openConnection();  
	            InputStream is = uRLConnection.getInputStream();  
	            BufferedReader br = new BufferedReader(new InputStreamReader(is));  
	            String response = "";  
	            String readLine = null;  
	            while((readLine =br.readLine()) != null){  
	                response = response + readLine;  
	            }  
	            is.close();  
	            br.close();  
	            uRLConnection.disconnect();  
	            return response;  
	        } catch (MalformedURLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return null;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return null;  
	        } 
          
		
		
		
		
		
		
	}

	public void setSuitableJumpUrl() {
		this.setJumpurl(UserCommon.setSuitableJumpUrl(this.jumpurl));
	}

	public LvAccount getLvAccount() {
		return lvAccount;
	}

	public void setLvAccount(LvAccount lvAccount) {
		this.lvAccount = lvAccount;
	}

	public LvUserTh getLvUserTh() {
		return lvUserTh;
	}

	public void setLvUserTh(LvUserTh lvUserTh) {
		this.lvUserTh = lvUserTh;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getJumpurl() {
		return jumpurl;
	}

	public void setJumpurl(String jumpurl) {
		this.jumpurl = jumpurl;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public EpgUserService getEpgUserService() {
		return epgUserService;
	}

	public void setEpgUserService(EpgUserService epgUserService) {
		this.epgUserService = epgUserService;
	}

	public String getLoginstyle() {
		return loginstyle;
	}

	public void setLoginstyle(String loginstyle) {
		this.loginstyle = loginstyle;
	}

	public String getSynurl() {
		return synurl;
	}

	public void setSynurl(String synurl) {
		this.synurl = synurl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getThnick() {
		return thnick;
	}

	public void setThnick(String thnick) {
		this.thnick = thnick;
	}
	
}
