package com.lshop.web.userCenter.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.gv.epg.common.constants.ApiConstants;
import com.gv.epg.model.user.service.EpgUserService;
import com.gv.epg.pojo.UcEpgUserInfo;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.ShoppingCartBeanForCookie;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.common.pojo.logic.LvUserTh;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.AppCookieUtil;
import com.lshop.common.util.Constants;
import com.lshop.common.util.LanguageUtil;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.userCenter.UserCommon;
import com.lshop.web.userCenter.UserConstant;
import com.lshop.web.userCenter.UserStatus;

/**
 * 授权
 * 授权登陆和注册时不对论坛进行操作，即不会同步注册或登陆论坛
 * @author dingh
 */
@SuppressWarnings("serial")
@Controller("Authorize")
@Scope("prototype")
public class Authorize extends BaseAction {
	
	private static final Log logger = LogFactory.getLog(Authorize.class);

	private LvAccount lvAccount; // 账户表
	private LvUserTh lvUserTh;//绑定关联表
	private String code; // 验证码
	private String jumpurl;
	private String uname; // 登录用户名
	private String pwd; // 密码
	private String newPwd; // 新密码
	private String authtype;// 授权方式
	private EpgUserService epgUserService = new EpgUserService();
	@Resource
	private OnlineMallService onlineMallService;
	/**
	 * 授权 
	 */
	public String authorize() throws Exception {
		// 会话ID，传给盒子端登陆
		String sessionId = this.getRequest().getParameter("lid");
		logger.info("=======================Authorize.authorize begin,lid="+sessionId);
		this.getRequest().setAttribute("sessionId", sessionId);
		
		String stat = qrCodeStatusUpdate(sessionId,ApiConstants.STATUS_QR_CODE_ON,UserCommon.getBizline(this.getFlag()),
				UserCommon.getTerminal(this.getFlag()),"","","");//修改状态为已扫描
		if(!(stat.equals(ApiConstants.STATUS_SUCCEED))) return stat;

		// 浏览器信息
		String userAgent = this.getRequest().getHeader("USER-AGENT")
				.toLowerCase();

		if (userAgent.contains(UserConstant.WEIXIN)) {// 判断是否是微信请求
			this.setJumpurl(UserCommon.getWXRequestUrl(this.getFlag(), sessionId, UserConstant.AUTH_THIRD_TYPE_WX));
			this.getRequest().setAttribute("dalaytime", 200);
			return "goto";
		} else {// 不是微信请求
			this.setAuthtype(UserConstant.AUTH_THIRD_TYPE_MOBILE);// 设置授权方式
			// 判断账号是否已经登陆
			if (UserCommon.compareUser(this.getRequest())) {// 已经登陆，跳到确认页面
				String username = UserCommon.getCookieValueToMap(UserConstant.USER_CENTER, true, this.getRequest()).
						get("email");
				this.getRequest().setAttribute("username", username);
				return "authcomfirm";
			} else {// 未登录，跳到登陆页面
				return "authlogin";
			}
		}
	}
	
	/**
	 * 授权回调
	 * @throws Exception 
	 */
	public String authRedirect() throws Exception{
		logger.info("========================Authorize.authRedirect begin");
		String sessionId = this.getRequest().getParameter("sessionId");
		String type = this.getRequest().getParameter("type");
		
		if(type.equals(UserConstant.AUTH_THIRD_TYPE_WX)){//判断是微信请求返回过来的，回调微信授权
			this.setAuthtype(UserConstant.AUTH_THIRD_TYPE_WX);// 设置授权方式
			return auth_wx(sessionId);
			
		}
		return "";
	}
	
	/**
	 * 微信回调地址
	 */
	protected String auth_wx(String sessionId) throws Exception{
		String token = this.getRequest().getParameter("code");
		String openId = UserCommon.getWXOpenId(token);
		if(null == openId || openId.equals("")){
			logger.info("========================微信授权失败,openId："+openId);
			openId = "";
		}
		
		dto.put("openId", openId);
		// 判断token是否已经绑定用户
		dto.put("thType", 0);
		LvUserTh lvUserTh = (LvUserTh) this.doService("AuthorizeService","getLvUserTh", dto);
		if (null == lvUserTh) {// 如果没有绑定，跳到绑定账号页面
			if(UserCommon.compareUser(this.getRequest())){
				String email = UserCommon.getCookieValueToMap(UserConstant.USER_CENTER, true, this.getRequest()).
						get("email");
				this.getRequest().setAttribute("email", email);
			}
			this.getRequest().setAttribute("token", openId);
			return "authlogin";
		} else {// 如果已经绑定，跳转到确认页面
			dto.put("usercode", lvUserTh.getUserCode());
			UcEpgUserInfo info = UcenterForEpg.getUserInfo(this.getFlag(),dto);
			this.getRequest().setAttribute("username", info.getEmail());
			this.getRequest().setAttribute("token", lvUserTh.getUserCode());
			return "authcomfirm";
		}
	}

	/**
	 * 第三方授权 登陆
	 * @throws Exception
	 */
	public String authLogin() throws Exception {
		logger.info("========================Authorize.authLogin begin");
		if (!UserCommon.validateCode(code, this.getSession())) {
			return userReturn(UserStatus.LOGIN_CODE_ERRO);
		}
		String thtype = "";
		String sessionId = this.getRequest().getParameter("sessionId");
		String token = this.getRequest().getParameter("token");
		
		// 從用戶中心登陸
		UcEpgUserInfo userInfo = null;
		Map<String, Object> map = UcenterForEpg.uc_synlogin(uname.trim(),
				pwd.trim(), this.getFlag(), dto);
		int statu = Integer.valueOf(map.get("statu").toString()).intValue();
		if (statu != 1) {// 登陆失败
			this.getRequest().setAttribute("token", token);
			this.getRequest().setAttribute("sessionId", sessionId);
			return userReturn(statu);
		}
		userInfo = (UcEpgUserInfo) map.get("userInfo");
		
		if (this.authtype.equals(UserConstant.AUTH_THIRD_TYPE_WX)) {//判断是否是微信授权
			thtype = "0";
			//判断账号是否已经被绑定
			dto.put("code", userInfo.getUserCode());
			dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));
			dto.put("thType", 0);
			Boolean f = (Boolean)this.doService("AuthorizeService", "isBind", dto);
			if(f){//如果已经被绑定，跳转到提示页面
				this.getRequest().setAttribute("token", token);
				this.getRequest().setAttribute("sessionId", sessionId);
				return userReturn(UserStatus.LOGIN_ISBIND);
			}else{
				String r = bindWX(sessionId,userInfo.getUserCode(),token);
				if(!r.equals("")) return r;
				this.setJumpurl("/web/"+this.getFlag()+"/authorize/wx/suc.jsp");
			}
		} else {
			this.setJumpurl("/index.html");
		}
		commonLogin(userInfo.getUserCode());//登陆商城
		// 登陆成功，调用底层盒子登陆
		String stat = qrCodeStatusUpdate(sessionId,ApiConstants.STATUS_SUCCEED,UserCommon.getBizline(this.getFlag()),
				UserCommon.getTerminal(this.getFlag()),userInfo.getEmail(),token,thtype);//修改状态为登陆成功
		if(!(stat.equals(ApiConstants.STATUS_SUCCEED))) {//修改状态失败
			UserCommon.commonLoginout(this.getFlag(), this.getRequest(), this.getResponse());
			if(stat.equals("430")) statu=UserStatus.LID_INVALID;
			if(stat.equals("erro")) statu=UserStatus.SYS_ERRO;
		}
		logger.info("========================Authorize.authLogin end");
		return userReturn(statu);
	}

	/**
	 * 第三方授权 注册并登陆
	 * @throws Exception
	 */
	public String authRegist() throws Exception {
		logger.info("========================Authorize.authRegist begin");
		if (!UserCommon.validateCode(code, this.getSession())) {
			return userReturn(UserStatus.REG_CODE_ERRO);
		}
		String sessionId = this.getRequest().getParameter("sessionId");
		String token = this.getRequest().getParameter("token");
		String thtype = "";
		
		int statu = commonRegist();// 注册并登陆
		
		if (statu != UserStatus.REG_SUC) {//注册失败
			return userReturn(statu);
		}
		if (this.authtype.equals(UserConstant.AUTH_THIRD_TYPE_WX)) {//如果是微信授权，则进行绑定
			thtype = "0";
			String r = bindWX(sessionId,lvAccount.getCode(),token);
			if(!r.equals("")) return r;
			this.setJumpurl("/web/"+this.getFlag()+"/authorize/"+this.authtype+"/suc.jsp");
		} else {
			this.setJumpurl("/index.html");
		}
		// 注册登陆成功，调用底层盒子登陆
		String stat = qrCodeStatusUpdate(sessionId,ApiConstants.STATUS_SUCCEED,UserCommon.getBizline(this.getFlag()),
				UserCommon.getTerminal(this.getFlag()),lvAccount.getEmail(),token,thtype);//修改状态为登陆成功
		if(!(stat.equals(ApiConstants.STATUS_SUCCEED))) {//修改状态失败
			UserCommon.commonLoginout(this.getFlag(), this.getRequest(), this.getResponse());
			if(stat.equals("430")) statu=UserStatus.LID_INVALID;
			if(stat.equals("erro")) statu=UserStatus.SYS_ERRO;
		}
		logger.info("========================Authorize.authRegist end");
		return userReturn(statu);
	}
	
	/**
	 * 绑定微信
	 */
	private synchronized String bindWX(String sessionId,String code,String token){
		this.dto.put("lid", sessionId);
		this.dto.put("bizline", UserCommon.getBizline(this.getFlag()));
		this.dto.put("terminal", UserCommon.getTerminal(this.getFlag()));
		this.dto.put("qrstatus", "");
		Dto d = epgUserService.qrCodeStatus(dto);
		String statu = d.getAsString(ApiConstants.KEY_STATUS);
		if(statu.equals("430") || statu.equals("200")){//绑定前判断lid状态
			return userReturn(UserStatus.LID_INVALID);
		}else{
			this.lvUserTh = new LvUserTh();
			this.lvUserTh.setUserCode(code);
			this.lvUserTh.setMallFlag(Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));
			this.lvUserTh.setThType(0);
			this.lvUserTh.setThCode(token);
			this.lvUserTh.setStatus(1);
			Date date = new Date();
			this.lvUserTh.setCreateTime(date);
			this.lvUserTh.setUpdateTime(date);
			this.dto.put("lvUserTh", this.lvUserTh);
			//对账号进行绑定
			this.doService("AuthorizeService", "bind", dto);
		}
		return "";
	}
	
	/**
	 * 确认后直接登陆盒子
	 * @throws Exception 
	 */
	public String agreeLogin() throws Exception {
		logger.info("========================Authorize.agreeLogin begin");
		String sessionId = this.getRequest().getParameter("sessionId");
		String token = this.getRequest().getParameter("token");
		String thtype = "";
		if (this.authtype.equals(UserConstant.AUTH_THIRD_TYPE_WX)){
			thtype = "0";
			UcEpgUserInfo userInfo = null;
			dto.put("usercode", token);
			userInfo = UcenterForEpg.getUserInfo(this.getFlag(), dto);
			commonLogin(userInfo.getUserCode());//登陆商城
		}
		String email = UserCommon.getCookieValueToMap(UserConstant.USER_CENTER, true, this.getRequest()).get("email");
		String stat = qrCodeStatusUpdate(sessionId,ApiConstants.STATUS_SUCCEED,UserCommon.getBizline(this.getFlag()),
				UserCommon.getTerminal(this.getFlag()),email,token,thtype);//修改状态为登陆成功
		if(!(stat.equals(ApiConstants.STATUS_SUCCEED))){
			if(stat.equals("430")) return userReturn(UserStatus.LID_INVALID);
			if(stat.equals("erro")) return userReturn(UserStatus.SYS_ERRO);
		} 
		this.setJumpurl("/index.html");
		return userReturn(UserStatus.LOGIN_SUC);
	}
	
	/**
	 * 用户取消授权
	 */
	public String disagreeLogin(){
		logger.info("========================Authorize.disagreeLogin begin");
		String sessionId = this.getRequest().getParameter("sessionId");
		qrCodeStatusUpdate(sessionId,ApiConstants.STATUS_QR_CODE_CANCEL,UserCommon.getBizline(this.getFlag()),
				UserCommon.getTerminal(this.getFlag()),"","","");//修改状态为登陆成功，若修改时过期或报错等，不进行提示
		return userReturn(UserStatus.BIND_CANCEL);
	}
	
	private void commonLogin(String code) throws Exception {
		dto.put("uid", code);
		dto.put("usercode", code);
		UcEpgUserInfo userInfo = UcenterForEpg.getUserInfo(this.getFlag(), dto);

		// 获取商城标识
		String mallFlag = StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag = mallFlag == null ? this.getFlag() : mallFlag;
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); // 获取当前店铺所属系统（华扬orBanana）

		// 用戶中心登陸成功后直接獲取用戶信息進行登陸
		dto.put("email", userInfo.getEmail());
		LvAccount user = (LvAccount) super.doService("UserCenterService", "getAccountByEmail", dto);
		if(user != null){
			if(null == userInfo.getLoginTime()){
				user.setLastTime(new Date());
			}else{
				user.setLastTime(userInfo.getLoginTime());
			}
			user.setNickname(userInfo.getNickname());
			login(user, mallSystem, mallFlag);
		}else{
			LvAccount lvAccount = new LvAccount();
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
		}
		
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
			login(user, mallSystem,this.getFlag());//执行登陆
			setSuitableJumpUrl();
			return UserStatus.REG_SUC;
		} else {
			this.getRequest().setAttribute("reg_erro", "erro");
			return UserStatus.REG_ERRO;
		}
	}

	private String userReturn(int mark) {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			String json = "{'mark':" + mark + ",'jumpurl':'" + jumpurl + "'}";
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
		return null;
	}

	private void login(LvAccount user, String mallSystem, String mallFlag)
			throws Exception {
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
	
	/**
	 * 修改lid状态
 * @param dto lid二维码的临时会话ID
 * status状态
 * ApiConstants.STATUS_SUCCEED 登陆成功 
 * ApiConstants.STATUS_QR_CODE_ING 处理中
 * ApiConstants.STATUS_QR_CODE_ON 已扫码
 * ApiConstants.STATUS_QR_CODE_CANCEL 取消 
 * ApiConstants.STATUS_QR_CODE_OVER 超时)
 * bizline业务线
 * terminal终端标识
 * account 账号(状态为成功需要传入)
	 */
	protected String qrCodeStatusUpdate(String sessionId,String status,String bizline,String terminal,
			String emil,String thcode,String thtype){
		logger.info("=======================进入远程修改epg lid状态  qrCodeStatusUpdate begin");
		try{
			this.dto.clear();
			this.dto.put("lid", sessionId);
			this.dto.put("qrstatus", status);
			this.dto.put("bizline", bizline);
			this.dto.put("terminal", terminal);
			
			this.dto.put("account", emil);
			this.dto.put("thcode", thcode);
			this.dto.put("thtype", thtype);
			Dto d = epgUserService.qrCodeStatusUpdate(dto);
			String statu = d.getAsString(ApiConstants.KEY_STATUS);
			if(statu.equals(ApiConstants.STATUS_SUCCEED)){//成功修改状态
				logger.info("=======================远程修改epg lid状态成功  suc");
				return ApiConstants.STATUS_SUCCEED;
			}else if(statu.equals(ApiConstants.STATUS_QR_CODE_OVER)){//lid超时
				logger.info("=======================远程修改epg lid状态失败，lid超时  invalid");
				this.setJumpurl("/web/"+this.getFlag()+"/authorize/codetimeout.jsp");
				return ApiConstants.STATUS_QR_CODE_OVER;
			}else{//系统错误
				logger.info("=======================远程修改epg lid状态失败，系统错误 sys erro");
				this.setJumpurl("/web/"+this.getFlag()+"/authorize/erro.jsp");
				return "erro";
			}
		}catch(Exception e){
			logger.info("=======================远程修改epg lid状态失败，系统错误  sys erro");
			this.setJumpurl("/web/"+this.getFlag()+"/authorize/erro.jsp");
			e.printStackTrace();
			return "erro";
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
		this.getSession().setAttribute("jumpurl", this.jumpurl);
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

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	public LvUserTh getLvUserTh() {
		return lvUserTh;
	}

	public void setLvUserTh(LvUserTh lvUserTh) {
		this.lvUserTh = lvUserTh;
	}

}
