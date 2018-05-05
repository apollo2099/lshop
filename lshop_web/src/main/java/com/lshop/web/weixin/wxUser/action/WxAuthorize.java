package com.lshop.web.weixin.wxUser.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.gv.epg.pojo.UcEpgUserInfo;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.wxGzhConfig.service.WxGzhConfigService;
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
import com.lshop.web.userCenter.UserCommon;
import com.lshop.web.userCenter.UserConstant;
import com.lshop.web.userCenter.UserStatus;
import com.lshop.web.userCenter.action.UcenterForEpg;

/**
 * 授权
 * 授权登陆和注册时不对论坛进行操作，即不会同步注册或登陆论坛
 * @author JinJian 2015-01-15
 */
@SuppressWarnings("serial")
@Controller("WxAuthorize")
@Scope("prototype")
public class WxAuthorize extends BaseAction {
	
	private static final Log logger = LogFactory.getLog(WxAuthorize.class);

	private LvAccount lvAccount; // 账户表
	private LvUserTh lvUserTh;//绑定关联表
	private String code; // 验证码
	private String jumpurl;
	private String uname; // 登录用户名
	private String pwd; // 密码
	private String newPwd; // 新密码
	private Long timestamp;
	@Resource
	private WxGzhConfigService wxGzhConfigService;

	/**
	 * 授权 
	 */
	public String authorize() throws Exception {
		logger.info("========================WxAuthorize.authorize");
		// 浏览器信息
		String userAgent = this.getRequest().getHeader("USER-AGENT")
				.toLowerCase();
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		if (wxGzhConfig == null) {
			logger.error(this.getFlag() + "域名为找到相对应的公众号，wxUserService.getByStoreId 返回为空");
		}
		this.getResponse().sendRedirect(UserCommon.getWXRequestUrl(this.getFlag(), wxGzhConfig.getAppId()));
		return null;
	}
	
	/**
	 * 授权回调
	 * @throws Exception 
	 */
	public String authRedirect() throws Exception{
		String token = this.getRequest().getParameter("code");
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		if (wxGzhConfig == null) {
			logger.error(this.getFlag() + "域名为找到相对应的公众号，wxUserService.getByStoreId 返回为空");
		}		
		String openId = UserCommon.getWXOpenId(token, wxGzhConfig.getAppId(), wxGzhConfig.getAppSecret());
		if(null == openId || openId.equals("")){
			logger.error("========================微信授权失败,openId："+openId);
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
			
			commonLogin(lvUserTh.getUserCode());//登陆商城
			Calendar cal = Calendar.getInstance();
			this.setTimestamp(cal.getTimeInMillis());
			return "webIndex";
		}
	}

	/**
	 * 第三方授权 登陆
	 * @throws Exception
	 */
	public String authLogin() throws Exception {
		logger.info("========================WxAuthorize.authLogin begin");
		if (!UserCommon.validateCode(code, this.getSession())) {
			return userReturn(UserStatus.LOGIN_CODE_ERRO);
		}
		String token = this.getRequest().getParameter("token");
		
		// 從用戶中心登陸
		UcEpgUserInfo userInfo = null;
		Map<String, Object> map = UcenterForEpg.uc_synlogin(uname.trim(),
				pwd.trim(), this.getFlag(), dto);
		int statu = Integer.valueOf(map.get("statu").toString()).intValue();
		if (statu != 1) {// 登陆失败
			this.getRequest().setAttribute("token", token);
			return userReturn(statu);
		}
		userInfo = (UcEpgUserInfo) map.get("userInfo");
		
		//判断账号是否已经被绑定
		dto.put("code", userInfo.getUserCode());
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));
		dto.put("thType", 0);
		Boolean f = (Boolean)this.doService("AuthorizeService", "isBind", dto);
		dto.put("openId", token);
		LvUserTh lvUserTh = (LvUserTh) this.doService("AuthorizeService","getLvUserTh", dto);
		if(f || (lvUserTh != null)){//如果已经被绑定，跳转到提示页面
			this.getRequest().setAttribute("token", token);
			return userReturn(UserStatus.LOGIN_ISBIND);
		}else{
			bindWX(userInfo.getUserCode(),token);
			this.setJumpurl("/web/"+this.getFlag()+"/weixin/authorize/suc.jsp");
		}
		commonLogin(userInfo.getUserCode());//登陆商城
		logger.info("========================WxAuthorize.authLogin end");
		return userReturn(statu);
	}

	/**
	 * 第三方授权 注册并登陆
	 * @throws Exception
	 */
	public String authRegist() throws Exception {
		logger.info("========================WxAuthorize.authRegist begin");
		if (!UserCommon.validateCode(code, this.getSession())) {
			return userReturn(UserStatus.REG_CODE_ERRO);
		}
		String token = this.getRequest().getParameter("token");
		int statu = commonRegist();// 注册并登陆
		if (statu != UserStatus.REG_SUC) {//注册失败
			return userReturn(statu);
		}
		bindWX(lvAccount.getCode(),token);	
		this.setJumpurl("/web/"+this.getFlag()+"/weixin/authorize/suc.jsp");
		logger.info("========================WxAuthorize.authRegist end");
		return userReturn(statu);
	}
	
	/**
	 * 检查指定openid是否已经绑定
	 * @return
	 * @throws Exception
	 */
	public String checkIsBind() throws Exception {
		String token = this.getRequest().getParameter("token");
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));
		dto.put("thType", 0);
		dto.put("openId", token);
		LvUserTh lvUserTh = (LvUserTh) this.doService("AuthorizeService","getLvUserTh", dto);
		if (lvUserTh ==  null) {
			return userReturn(-100);
		} else {
			return userReturn(UserStatus.LOGIN_ISBIND);
		}
	}	
	
	/**
	 * 绑定微信
	 */
	private synchronized void bindWX(String userCode,String token){
		this.lvUserTh = new LvUserTh();
		this.lvUserTh.setUserCode(userCode);
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
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()));
		dto.put("thType", 0);		
		String token = this.getRequest().getParameter("token");
		dto.put("openId", token);
		LvUserTh lvUserTh = (LvUserTh) this.doService("AuthorizeService","getLvUserTh", dto);		
		if (flag || (lvUserTh != null)) {
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
		str = str + "*loginWx=1";
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
				for (ShoppingCartBeanForCookie car : list) {
					// 将当前商城所对应的购物车cookie信息存入购物车中
					// if(storeFlags.indexOf(car.getStoreId())!=-1){
					dto.put("storeFlag", car.getStoreId());
					dto.put("pcode", car.getCode());
					LvProduct product = (LvProduct) this.doService(
							"ProductService", "getProductByCode", dto);

					// 产品不存在
					if (null == product) {
						continue;
					}
					// 产品删除或停用
					if (null != product.getStatus() && product.getStatus() != 1) {
						continue;
					}
					// 产品下架
					if (null != product.getIsSupport()
							&& product.getIsSupport() != 1) {
						continue;
					}
					// 获取当前价格（是否参加活动或阶梯价 参数：pcode,storeFlag）
					float price = 0f;
					dto.put("pnum", car.getShopNum());
					price = Float.parseFloat(this.doService("ProductService",
							"getProductPrice", dto).toString());

					// 保存购物车信息
					LvShopCart myCart = new LvShopCart();
					myCart.setUserCode(user.getCode());
					myCart.setProductCode(car.getCode());
					myCart.setShopNum(car.getShopNum());
					myCart.setShopPrice(price);
					myCart.setMallFlag(mallFlag);
					dto.setDefaultPo(myCart);
					dto.put("mallFlag", mallFlag);
					dto.put("product", product);
					this.doService("OnlineMallService", "cacheShopCart", dto);
					// }
				}
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

	public LvUserTh getLvUserTh() {
		return lvUserTh;
	}

	public void setLvUserTh(LvUserTh lvUserTh) {
		this.lvUserTh = lvUserTh;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
