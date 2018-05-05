package com.lshop.web.userCenter.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.model.spring.ApplicationContextHolder;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.DES;
import com.gv.core.util.cryption.MD5;
import com.gv.epg.pojo.UcEpgUserInfo;
import com.lshop.common.action.BaseAction;
import com.lshop.common.activity.vo.ActivityMac;
import com.lshop.common.constant.AppDataConstant;
import com.lshop.common.pojo.ShoppingCartBeanForCookie;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderMac;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.common.pojo.logic.LvUserSubscribe;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.pojo.user.LvAccountInfo;
import com.lshop.common.spring.LoginEvent;
import com.lshop.common.util.AppCookieUtil;
import com.lshop.common.util.Constants;
import com.lshop.common.util.IPUtils;
import com.lshop.common.util.LanguageUtil;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.activity.service.ActivityMacService;
import com.lshop.web.activity.service.RegistService;
import com.lshop.web.mac.service.AccountMacService;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.order.service.OrderMacService;
import com.lshop.web.userCenter.UserCommon;
import com.lshop.web.userCenter.UserConstant;
import com.lshop.web.userCenter.UserStatus;
import com.lshop.web.userCenter.bbs.UcenterSSO;
import com.lshop.web.userCenter.service.UserCenterService;

/**
 * 用户中心模块
 * @author zhengxue
 * @since 2.0 2012-07-03
 */
@SuppressWarnings("serial")
@Controller("UserCenterManager")
@Scope("prototype")
public class UserCenterManager extends BaseAction {

	private LvAccount lvAccount; // 账户表
	private LvAccountInfo lvAccountInfo; //账户详情表
	private String code; //验证码
	private String jumpurl;
	private String uname; //登录用户名
	private String pwd; //密码
	private String newPwd; //新密码
	private LvOrder lvOrder; //订单表
	private String ordnickname;//原呢称
	private LvAccountAddress lvAccountAddress; //账户收货地址
	private LvUserSubscribe lvUserSubscribe; //邮件订阅
	
	private String loginstyle;
	private String authtype;//授权方式
	private String synurl;//论坛同步代码
    private Integer jumpFlag;//登录合并跳转标志(1登录Tab,2注册Tab)
    private String productCode;
    private String shopFlag;
    private String mac;
    private String phone;
    private String tel;
    private String sourceUrl;

	
	@Resource
	private RegistService registService; 
	@Resource
	private OnlineMallService onlineMallService;
	@Resource
	private ActivityMacService activityMacService;
	@Resource
	private AccountMacService accountMacService;
	@Resource
	private UserCenterService userCenterService;	
	@Resource
	private OrderMacService orderMacService;
	/**
	 * 跳转到注册页面
	 * @return
	 * @throws Exception
	 */
	public String toRegister() throws Exception{
		return "register";
	}
	
	public String toLoginRegister(){
		if(ObjectUtils.isEmpty(jumpFlag)){
			jumpFlag=1;
		}
		this.getRequest().setAttribute("jumpFlag",jumpFlag);
		this.getRequest().setAttribute("jumpurl",jumpurl);
		return "loginRegister";
	}
	
	/**
	 * 
	 * @Method: loginRegister 
	 * @Description:  [
	 *                 1.自动登录或者注册方法合并
	 *                 2.保存mac兑换情况记录信息
	 *                ]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月15日 下午4:53:24]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月15日 下午4:53:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws Exception 
	 */
	public String loginRegister(){
		try {
			
			
			int mark = 0;
			mark = validateOrderMac();
			if(mark==10){
				/*
				 * 一、 登录或者注册
				 */
				Boolean flag=false;
				if(ObjectUtils.isNotEmpty(loginstyle)){
					if(loginstyle.equals(UserStatus.LOGIN_STYLE_REG)){
						lvAccount.setPwd("111111");
						mark=commonRegist();
						if(mark==UserStatus.REG_SUC){
							flag=true;
		
							//给用户发送邮件，告知注册密码
							Map<String, String> user = getCookieValueToMap(UserConstant.USER_CENTER,true);
							dto.put("tplKey", "USER_REGISTER");
							dto.put("storeId", this.getFlag());
							LvEmailTpl lvEmailTpl = (LvEmailTpl) this.doService("lvEmailManageService","get", dto);
							if(lvEmailTpl!=null){
								if (ObjectUtils.isEmpty(user.get("nickname"))) {
									user.put("nickname", lvAccount.getEmail());
								}
								dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(shopFlag)); //获取当前店铺所属系统（华扬orBanana）
								dto.put("userEmail", lvAccount.getEmail());
								String title=lvEmailTpl.getEnTitle().replace("{relname}",user.get("nickname"));
								String body=(lvEmailTpl.getEn()+lvEmailTpl.getZh()).replace("{nickname}", user.get("nickname")).replace("{pwd}", "111111").replace("{email}", lvAccount.getEmail());
								body=body.replace("{createtime}", StringUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
								body=body.replace("{sendtime}", StringUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
								dto.put("title", title);
								dto.put("content", body);
							    doService("emailSendService", "sendEmailNotice", dto);
							}
							
						}
					}else if(loginstyle.equals(UserStatus.LOGIN_STYLE_LOGIN)){
						mark=this.loginForQuickOrder(lvAccount);
						if(mark==1){
							flag=true;
						}
					}
				}
				
				/*
				 * 二、保存mac用户行为记录 
				 */
				if(flag){
					String userCode=null;
					userCode = this.getCookieValue(UserConstant.USER_ID, true);
					String ip=IPUtils.getIpAddr(this.getRequest());
					
					LvAccount ac = userCenterService.getAccountByEmail(lvAccount.getEmail());
					if(ObjectUtils.isEmpty(userCode)){
						if(ObjectUtils.isNotEmpty(ac)){
							userCode=ac.getCode();
						}
					}else if(!userCode.equals(ac.getCode())){
						userCode=ac.getCode();
					}
					accountMacService.saveAccountMac(lvAccount.getEmail(),userCode,phone,tel,mac,ip,sourceUrl);
				}
				//返回数据参数
				userReturn(mark);
			}else{
				userReturn(mark);
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 验证mac是否已经使用
	 */
	private int validateOrderMac() {
		int mark;
		ActivityMac activityMac = activityMacService.findByProduct(productCode);
		if (ObjectUtils.isNotEmpty(activityMac)) {
			//根据mac查询兑换的总次数
			int macCount=orderMacService.countByMac(mac);
			if(macCount>=activityMac.getExchangeNum()){
				mark=12;//mac兑换次数超过
			}else{
				//根据mac查询
				LvOrderMac orderMac = orderMacService.findByMac(mac);
				if (ObjectUtils.isNotEmpty(orderMac)) {
					mark=11;//mac当天已经兑换
				} else {
					mark=10;//mac未兑换
				}
			}
		} else {
			mark=-11;//活动结束
		}
		return mark;
	}

	/**
	 * 用户注册
	 * @return
	 * @throws Exception 
	 * edit by dingh
	 */
	public String regeditAccount() throws Exception{
		//判断验证码填写是否正确
		if(StringUtil.IsNullOrEmpty(code) || !code.trim().equals(this.getSession().getAttribute("rcode"))){
			this.getRequest().setAttribute("reg_erro", "erro");
			return userReturn(UserStatus.REG_CODE_ERRO);
		}
		return userReturn(commonRegist());
	}
	
	//注册并登陆
	private int commonRegist() throws Exception{
		dto.put("storeFlag", this.getFlag());
		String myPwd=lvAccount.getPwd();
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		if(!StringUtil.IsNullOrEmpty(lvAccount.getNickname())){
			lvAccount.setNickname(lvAccount.getNickname().trim());
		}else{//註冊時去掉暱稱
			lvAccount.setNickname("");
		}
		
		//验证账号是否存在
	    lvAccount.setEmail(lvAccount.getEmail().trim());
		dto.setDefaultPo(lvAccount);
		Boolean flag=UcenterForEpg.checkUserExist(dto);
		if(flag){
			this.getRequest().setAttribute("reg_erro", "erro");
			return UserStatus.REG_ERRO;
		}
		 
		/**
		 * 同步註冊用戶中心
		 */
		UcEpgUserInfo userInfo = null;
		userInfo = UcenterForEpg.uc_synregist(lvAccount.getEmail(), 
				myPwd,LanguageUtil.getLan(this.getFlag()), this.getFlag(),dto);
		if(null == userInfo){
			this.getRequest().setAttribute("reg_erro", "erro");
			return UserStatus.REG_ERRO;
		}
		
		lvAccount.setCode(userInfo.getUserCode());
		dto.setDefaultPo(lvAccount);
		 //保存账号
		LvAccount user=(LvAccount)doService("UserCenterService", "addAccount",dto);
		if(user!=null){
			//同步论坛注册
			//只处理Banana商城论坛同步登录,modify by liaoxj 20141212
			if(this.getFlag().equals("bmcn")||this.getFlag().equals("bscn")){
				try{
					//同步注册
					UcenterSSO.regist(userInfo.getEmail(), myPwd.trim(), userInfo.getEmail(), "");
					//同步登录
					String ucsynlogin = UcenterSSO.login(user.getEmail(), myPwd.trim(), user.getEmail());
					this.setSynurl(UcenterSSO.getSynUrl(UcenterSSO.filterDomain(this.getFlag(), ucsynlogin), UcenterSSO.bbs_banana_domain));
				}catch(Exception e){
				}
			}
			
			login(user,mallSystem,myPwd);//执行登陆
			setSuitableJumpUrl();
			//商品用户注册成功活动回调
			String mallFlag = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag());
			registService.asyncRegisted(user.getCode(), mallFlag);
			return UserStatus.REG_SUC;
		}else{
			this.getRequest().setAttribute("reg_erro", "erro");
			return UserStatus.REG_ERRO;
		}
	}
	
	private String userReturn(int mark) throws IOException{
		/**callback为跨域请求返回标记*/
		String callback = this.getRequest().getParameter("callback");
		PrintWriter out=this.getResponse().getWriter();
		String json="{'mark':"+mark+",'jumpurl':'"+jumpurl+"','synurl':'"+synurl+"'}";
		if(!StringUtil.IsNullOrEmpty(callback)){
			json = callback + "(" + json + ")";
		}
		out.print(json);
		out.close();
		return null;
	}
	
	/**
	 * 判断账号是否存在
	 * @return
	 */
	public String isExistsUser() {
		//用于修改昵称的时候，判断是否和原来的昵称一致
 		String nickname="";
		try {
			Map<String, String> _userinfo = getCookieValueToMap(UserConstant.USER_CENTER,true);
			if (_userinfo != null && _userinfo.containsKey("nickname")) {
				nickname=_userinfo.get("nickname");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		dto.put("nickname", nickname);
		dto.put("storeFlag", this.getFlag());
		PrintWriter out=null;
		try {
			out=this.getResponse().getWriter();
			if(lvAccount!=null){
				 String email = lvAccount.getEmail();
				 if(email.matches(UserConstant.EMAIL_PATTERN)){
					 dto.setDefaultPo(lvAccount);
					 dto.put("email", email);
					 Boolean flag= UcenterForEpg.checkUserExist(dto);
					 if(flag){
						 out.print(1);//电子邮箱或用户名已存在
					 }else{
						 out.print(0);//电子邮箱或用户名不存在
					 }
				 }else{
					 out.print(-1);
				 }
		   }else{
			   out.print("请输入邮箱！"); //此消息不存在，不会显示出来
		   }
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 注册成功后跳转页面
	 * @return
	 * @throws Exception
	 */
	public String registerAccount() throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		if(uid==null||"".equals(uid.trim())){
			return "register";
		}
		dto.put("uid", uid);
		this.lvAccount=(LvAccount)this.doService("UserCenterService", "getAccount", dto);
		this.getRequest().setAttribute("flag", "account");
		
		return "account";
	}	
	
	/**
	 * 登陆成功后跳转页面
	 * @return
	 * @throws Exception
	 */
	public String loginAccount() throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		if(uid==null||"".equals(uid.trim())){
			return INPUT;
		}
		dto.put("uid", uid);
		this.lvAccount=(LvAccount)this.doService("UserCenterService", "getAccount", dto);
		this.getRequest().setAttribute("flag", "account");
		
		return "account";
	}	
	
	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception 
	 * edit by dingh
	 */
	public String login() throws Exception {
		if(null == this.loginstyle || this.loginstyle.equals("")){
			this.loginstyle = "1";
		}
		
		if(!this.getFlag().equals("mbmcn") && !this.getFlag().equals("mtmcn")){//手机端不用验证验证码
			if(this.loginstyle.equals("1")){//如果是頁面登陸則判断验证码填写是否正确
				code = (null==code)?"":code;
				if(!code.trim().equals(this.getSession().getAttribute("rcode"))){
					return userReturn(-5);
				}
			}
		}
		
		if(StringUtil.IsNullOrEmpty(uname.trim()) || StringUtil.IsNullOrEmpty(pwd.trim())){
			return userReturn(-1);
		}

		/**
		 * 從用戶中心登陸
		 */
		UcEpgUserInfo userInfo = null;
		Map<String,Object> map = UcenterForEpg.uc_synlogin(uname.trim(), pwd.trim(),this.getFlag(), dto);
		int statu = Integer.valueOf(map.get("statu").toString()).intValue();
		if(statu != 1){
			this.getRequest().setAttribute("erromsg", statu);
			return userReturn(statu);
		}
		userInfo = (UcEpgUserInfo)map.get("userInfo");
		dto.put("uid", userInfo.getUserCode());
		dto.put("email", userInfo.getEmail());
		//用戶中心登陸成功后直接獲取用戶信息進行登陸
		LvAccount user = (LvAccount) super.doService("UserCenterService", "getAccountByEmail", dto);
		
		//获取商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		
		if (user != null){
			user.setLastTime(userInfo.getLoginTime());
			user.setNickname(userInfo.getNickname());
			login(user, mallSystem, mallFlag);
			
			//只处理Banana商城论坛同步登录,modify by liaoxj 20141212
			if(mallFlag.equals("bmcn")||mallFlag.equals("bscn")){
				String ucsynlogin = UcenterSSO.login(user.getEmail(), pwd.trim(), user.getEmail());
				this.setSynurl(UcenterSSO.getSynUrl(UcenterSSO.filterDomain(this.getFlag(), ucsynlogin), UcenterSSO.bbs_banana_domain));
			}
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
			
			//只处理Banana商城论坛同步登录,modify by liaoxj 20141212
			if(mallFlag.equals("bmcn")||mallFlag.equals("bscn")){
				String ucsynlogin = UcenterSSO.login(u.getEmail(), pwd.trim(), u.getEmail());
				this.setSynurl(UcenterSSO.getSynUrl(UcenterSSO.filterDomain(this.getFlag(), ucsynlogin),UcenterSSO.bbs_banana_domain));
			}
		}
		
		setSuitableJumpUrl();
		return userReturn(statu);
	}
	
	/**
	 * 
	 * @Method: loginForQuickOrder 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月15日 下午5:54:05]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月15日 下午5:54:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws Exception 
	 */
	public Integer loginForQuickOrder(LvAccount lvAccount) throws Exception{
		if(StringUtil.IsNullOrEmpty(lvAccount.getEmail().trim()) || StringUtil.IsNullOrEmpty(lvAccount.getPwd().trim())){
			return -1;
		}

		/**
		 * 從用戶中心登陸
		 */
		UcEpgUserInfo userInfo = null;
		Map<String,Object> map = UcenterForEpg.uc_synlogin(lvAccount.getEmail().trim(), lvAccount.getPwd().trim(),this.getFlag(), dto);
		int statu = Integer.valueOf(map.get("statu").toString()).intValue();
		if(statu != 1){
			this.getRequest().setAttribute("erromsg", statu);
			return statu;
		}
		userInfo = (UcEpgUserInfo)map.get("userInfo");
		dto.put("uid", userInfo.getUserCode());
		dto.put("email", userInfo.getEmail());
		//用戶中心登陸成功后直接獲取用戶信息進行登陸
		LvAccount user = (LvAccount) super.doService("UserCenterService", "getAccountByEmail", dto);
		
		//获取商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		
		if (user != null){
			user.setLastTime(userInfo.getLoginTime());
			user.setNickname(userInfo.getNickname());
			login(user, mallSystem, mallFlag);
			
			//只处理Banana商城论坛同步登录,modify by liaoxj 20141212
			if(mallFlag.equals("bmcn")||mallFlag.equals("bscn")){
				String ucsynlogin = UcenterSSO.login(user.getEmail(), pwd.trim(), user.getEmail());
				this.setSynurl(UcenterSSO.getSynUrl(UcenterSSO.filterDomain(this.getFlag(), ucsynlogin), UcenterSSO.bbs_banana_domain));
			}
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
			
			//只处理Banana商城论坛同步登录,modify by liaoxj 20141212
			if(mallFlag.equals("bmcn")||mallFlag.equals("bscn")){
				String ucsynlogin = UcenterSSO.login(u.getEmail(), pwd.trim(), u.getEmail());
				this.setSynurl(UcenterSSO.getSynUrl(UcenterSSO.filterDomain(this.getFlag(), ucsynlogin),UcenterSSO.bbs_banana_domain));
			}
		}
		
		return statu;
	}
	
	
	
	private void login(LvAccount user,String mallSystem,String mallFlag) throws Exception{
		if(null == user.getNickname() || user.getNickname().equals("")){
			String email = user.getEmail();
			user.setNickname(email.substring(0, email.indexOf("@")));
		}
		this.addCookie(UserConstant.USER_ID, user.getCode().toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastTime =format.format(user.getLastTime());
		String str="uid="+user.getCode().toString()+"*id="+user.getId()+"*email="+user.getEmail()+"*nickname="+user.getNickname()+"*lastTime="+lastTime;
		this.addCookie(UserConstant.USER_CENTER,str, true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		 //保存一个加密的cookie，用于拦截比较
		String MD5Str = "uid="+MD5.convert32(user.getCode().toString())+"*id="+MD5.convert32(user.getId().toString())+"*email="+MD5.convert32(user.getEmail())+"*nickname="+MD5.convert32(user.getNickname());
		this.addCookie(UserConstant.MD5_USER_CENTER, MD5Str, true, -1, Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		 
		 //判断cookie中是否有购物车信息
		Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
		if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
			//获取购物车信息
			String shopCartList = shopCart.get("list");
			List<ShoppingCartBeanForCookie> list=AppCookieUtil.parseString(shopCartList,"@","#",":");
			if(null!=list && list.size()>0){
				onlineMallService.loginMergeShopCart(list, user.getCode());
			}
		}
		//将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
		dto.put("userCode", user.getCode());
		dto.put("storeFlag", this.getFlag());
		Integer shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
		this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		this.removeByCookie(UserConstant.SHOPCART, Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		//发布登陆成功事件
		ApplicationContextHolder.getApplicationContext().publishEvent(new LoginEvent(this, user.getCode(), mallSystem));
	}
	
	/**
	 * 用户退出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception{
		dto.put("storeFlag", this.getFlag());
		//退出华扬/banana商城
		UserCommon.commonLoginout(this.getFlag(), this.getRequest(), this.getResponse());
		//退出论坛
		String ucsynlogout = UcenterSSO.logout();
		ucsynlogout = UcenterSSO.filterDomain(this.getFlag(), ucsynlogout);
		if(this.getFlag().equals("bmcn") || this.getFlag().equals("bscn")){
			this.setSynurl(UcenterSSO.getSynUrl(UcenterSSO.filterDomain(this.getFlag(), ucsynlogout),
					UcenterSSO.bbs_banana_domain));
			return userReturn(1);
		}
		return "logout";
	}

	/**
	 * 跳转至找回密码页面
	 * @return
	 */
	public String toFindPassword(){
		return "password";
	}
	
	/**
	 * 找回密码
	 * @return
	 */
	public String password(){
		//判断验证码填写是否正确
		if(StringUtil.IsNullOrEmpty(code) || !code.trim().equals(this.getSession().getAttribute("rcode"))){
			this.getRequest().setAttribute("erromsg", "-2");
			return "findpwd_erro";
		}
				
		if(null == lvAccount || null == lvAccount.getEmail()){
			this.getRequest().setAttribute("erromsg", "-1");
			return "findpwd_erro";
		}
		
		String lang = LanguageUtil.getLan(this.getFlag());
		int stat = UcenterForEpg.findPwdCode(lvAccount.getEmail(), lang,this.getFlag(), dto);
		if(stat == 1){
			return "findpwd_suc";
		}
		if(stat == 0){
			this.getRequest().setAttribute("erromsg", "0");
		}
		if(stat == -1){
			this.getRequest().setAttribute("erromsg", "-1");
		}
		return "findpwd_erro";
	}
	
	public String reSetPwd(){
		String account = this.getRequest().getParameter("account");
		String code = this.getRequest().getParameter("code");
		if(null == account || null == code || code.equals("null") || null== newPwd 
				|| account.equals("") || code.equals("") || newPwd.equals("")){
			this.getRequest().setAttribute("erromsg", "-4");
			return "resetpwd_erro";
		}
		account = DES.decrypt(account);
		int stat = UcenterForEpg.resetPwd(account, newPwd, code, this.getFlag(),dto);
		if(stat == 1){
			UcenterSSO.reSetPwd(account, this.newPwd.trim());
			return "resetpwd_suc";
		}
		if(stat == 0){
			this.getRequest().setAttribute("erromsg", "0");
		}
		if(stat == -1){
			this.getRequest().setAttribute("erromsg", "-1");
		}
		if(stat == -2){
			this.getRequest().setAttribute("erromsg", "-2");
		}
		if(stat == -3){
			this.getRequest().setAttribute("erromsg", "-3");
		}
		return "resetpwd_erro";
	}
	
	/**
	 * 用户中心——我的账户
	 * @return
	 */
	public String getAccount() throws Exception{
		
		String em = "";
		Map<String, String> _userinfo = getCookieValueToMap(UserConstant.USER_CENTER,true);
		if (_userinfo == null || _userinfo.isEmpty()||!_userinfo.containsKey("email")) {
			return INPUT;
		}
		em = _userinfo.get("email");
		
		dto.put("request", this.getRequest());
		dto.put("em", em);
		dto.setDefaultPo(lvOrder);
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("mallSystem", mallSystem);
		this.doService("UserCenterService", "getOrderNum", dto);
		
		//查询用户订阅关联信息
		lvUserSubscribe=new LvUserSubscribe();
		lvUserSubscribe.setUid(Integer.parseInt(_userinfo.get("id")));
		dto.put("model", lvUserSubscribe);
		lvUserSubscribe=(LvUserSubscribe)this.doService("LvSubscribeService", "get", dto);
		//未找到关联关系时，查找会话中的用户信息，用于用户中心的邮件订阅
		if(lvUserSubscribe.getId()==null){
			if(_userinfo!=null&&_userinfo.size()>0){
				lvUserSubscribe.setUid(Integer.parseInt(_userinfo.get("id")));
				lvUserSubscribe.setUserName(_userinfo.get("nickname"));
				lvUserSubscribe.setEmail(_userinfo.get("email"));
				lvUserSubscribe.setCreateTime(new Date());
			}
		}
		this.getRequest().setAttribute("lvUserSubscribe", lvUserSubscribe);
		this.getRequest().setAttribute("mybalance", UcenterForEpg.getBalance(_userinfo.get("email")));
		
		this.getRequest().setAttribute("flag", "account");
		return "myAccount";
	}
	
	/**
	 * 用户中心——我的资料
	 * @return
	 */
	public String getInfoDetail()throws Exception{
		this.lvAccount = new LvAccount();
		this.lvAccountInfo = new LvAccountInfo();
		
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("usercode", uid);
		UcEpgUserInfo info = UcenterForEpg.getUserInfo(this.getFlag(),dto);
		this.lvAccount.setEmail(info.getEmail());
		this.lvAccount.setNickname(info.getNickname());
		
		this.lvAccountInfo.setSex(((info.getSex()==null)?2:info.getSex().byteValue()));
		this.lvAccountInfo.setQq(info.getQq());
		this.lvAccountInfo.setMsn(info.getMsn());
		this.lvAccountInfo.setName(info.getName());
		this.lvAccountInfo.setTel(info.getTel());
		this.lvAccountInfo.setMobile(info.getPhone());
		this.lvAccountInfo.setAddress(info.getAddress());
		this.lvAccountInfo.setContryName(info.getCountry());
		this.lvAccountInfo.setProvinceName(info.getProvince());
		this.lvAccountInfo.setCityName(info.getCity());
		this.lvAccountInfo.setPostCode(info.getPostal());
		
		//获取国家列表
		List<LvArea> contryList=(List<LvArea>)this.doService("ShopCartService", "getContryList", dto);
		this.getRequest().setAttribute("contryList", contryList);
		this.getRequest().setAttribute("flag", "info");
		
		return "myInfo";
	}
	
	/**
	 * 用户中心—— 用户资料——编辑我的资料
	 * @return
	 */
	public String editInfo() throws Exception{
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("usercode", uid);
		dto.put("nickname", lvAccount.getNickname());

		dto.put("uid", uid);
		//2014-4-14 移动端第一次可以设置呢称
//		if (StringUtils.isNotBlank(ordnickname)) {
//			dto.put("nickname", ordnickname);
//		}
		
		dto.put("sex", lvAccountInfo.getSex());
		dto.put("qq", lvAccountInfo.getQq());
		dto.put("msn", lvAccountInfo.getMsn());
		dto.put("name", lvAccountInfo.getName());
		dto.put("tel", lvAccountInfo.getTel());
		dto.put("phone", lvAccountInfo.getMobile());
		
		dto.put("address", lvAccountInfo.getAddress());
		dto.put("country", lvAccountInfo.getContryName());
		dto.put("province", lvAccountInfo.getProvinceName());
		dto.put("city", lvAccountInfo.getCityName());
		dto.put("postal", lvAccountInfo.getPostCode());
		
		int stat = UcenterForEpg.uc_syneditUserInfo(this.getFlag(),dto);
		this.getSession().setAttribute("statu", stat);
		return "edit";
	}
	
	/**
	 * 用户中心——跳转进入修改密码页面
	 * @return
	 */
	public String toUpdatePassword() throws Exception{
		this.getRequest().setAttribute("flag", "pwd");
		return "updatePassword";
	}
	/**
	 * 用户中心——修改密码
	 * @return
	 */
	public String updatePassword() throws Exception{
		dto.put("storeFlag", this.getFlag());
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		int stat = UcenterForEpg.uc_syneditPwd(uid, this.pwd.trim(), this.newPwd.trim(),this.getFlag(), dto);
		if(stat == 1){
			this.getRequest().setAttribute("mFlag", 2); //密码修改成功!
			dto.put("uid", uid);
			LvAccount account = (LvAccount)this.doService("UserCenterService", "getAccount", dto);
			UcenterSSO.reSetPwd(account.getEmail(), this.newPwd.trim());
		}
		if(stat == -1){
			this.getRequest().setAttribute("mFlag", 1); //原密码不正确！
		}
		if(stat == -2){
			this.getRequest().setAttribute("mFlag", 0); //系統錯誤！
		}
		this.getRequest().setAttribute("flag", "pwd");
		return "updatePassword";
	}
	
	/**
	 * 我的评论——我发表的评论
	 * 默认是显示“我发表的评论”列表
	 * @return
	 * @throws Exception
	 */
	public String getCommentList() throws Exception{
		
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
	      
	    dto.setDefaultPage(page);
	    page.setNumPerPage(10);
		dto.put("uid", uid);
		dto.put("request", this.getRequest());
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("mallSystem", mallSystem);
		this.page=(Pagination)this.doService("UserCenterService", "getCommentList", dto);
			
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("flag", "comment");
		this.getRequest().setAttribute("action", "mylist");
		return "myComments";
	}
	/**
	 * 我的评论分页
	 * @return
	 * @throws Exception
	 */
	public String pageCommentList() throws Exception{
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
	      
	    dto.setDefaultPage(page);
	    page.setNumPerPage(10);
		dto.put("uid", uid);
		dto.put("request", this.getRequest());
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("mallSystem", mallSystem);
		this.page=(Pagination)this.doService("UserCenterService", "getCommentList", dto);
			
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("flag", "comment");
		this.getRequest().setAttribute("action", "mylist");
		return "pageMyComments";
	}
	/**
	 * 我的评论——管理员回复的评论
	 * @return
	 * @throws Exception
	 */
	public String getReplyList() throws Exception{
		
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		
		dto.put("uid", uid);
		dto.setDefaultPage(page);
		page.setNumPerPage(10);
		dto.put("request", this.getRequest());
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("mallSystem", mallSystem);
		this.page=(Pagination)this.doService("UserCenterService", "getReplyList", dto);
		
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("flag", "comment");
	    this.getRequest().setAttribute("action", "replylist");
	    
		return "myComments";
	}
	/**
	 * 我的评论——管理员回复的评论
	 * @return
	 * @throws Exception
	 */
	public String pageReplyList() throws Exception{
		
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		
		dto.put("uid", uid);
		dto.setDefaultPage(page);
		page.setNumPerPage(10);
		dto.put("request", this.getRequest());
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("mallSystem", mallSystem);
		this.page=(Pagination)this.doService("UserCenterService", "getReplyList", dto);
		
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("flag", "comment");
	    this.getRequest().setAttribute("action", "replylist");
	    
		return "pageMyComments";
	}
	/**
	 * 用户中心——收货地址
	 * 显示该账号所有的收货地址
	 * @return
	 * @throws Exception
	 */
	public String getAddressList()  throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", uid);
		List<LvAccountAddress> addresses = (List<LvAccountAddress>) this.doService("AccountAddressService", "getUserAddress", dto);
		if (CollectionUtils.isNotEmpty(addresses)) {
			this.page = new Pagination(1, 10, addresses.size(), addresses);
		}
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("flag", "address");
		this.getRequest().setAttribute("addressCount", page.getList().size());
		
		return "addressList";
	}
	
	/**
	 * 用户中心——收货地址——设置or取消默认地址
	 * @return
	 * @throws Exception
	 */
	public String setDefaultAddress() throws Exception{
		short isDefault = Short.valueOf(this.getRequest().getParameter("isDefault"));
		String myAddress = this.getRequest().getParameter("addressCode");
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		if (1 == isDefault) {
			//设置默认收货地址
			dto.put("userCode", userCode);
			dto.put("addressCode", myAddress);
			this.doService("AccountAddressService", "setDefAddress", dto);
		} else if (0 == isDefault) {
			//取消默认收货地址
			dto.put("userCode", userCode);
			dto.put("addressCode", myAddress);
			this.doService("AccountAddressService", "cancelDefAddress", dto);
		}
		this.getRequest().setAttribute("flag", "address");
		return "getAddressList";
	}
	
	/**
	 * 用户中心——收货地址——删除某一个收货地址
	 * @return
	 * @throws Exception 
	 */
	public String deleteAddress() throws Exception{
		String code=this.getRequest().getParameter("addressCode");
		dto.put("addressCode", code);
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", userCode);
		this.doService("AccountAddressService", "delAddress", dto);
		this.getRequest().setAttribute("flag", "address");
		return "getAddressList";
	}
	
	/**
	 * 用户中心——收货地址——跳转到地址新增页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String toAddAddress() throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		//获取国家列表
		List<LvArea>contryList=(List<LvArea>)this.doService("ShopCartService", "getContryList", dto);
		this.getRequest().setAttribute("contryList", contryList);
		
		this.getRequest().setAttribute("flag", "address");
		return "addAddress";
	}
	/**
	 * 用户中心——收货地址——新增一个收货地址
	 * @return
	 * @throws Exception
	 */
	public String addAddress() throws Exception{
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lvAccountAddress.setStoreId(this.getFlag());
		dto.put("userCode", userCode);
		dto.put("addr", lvAccountAddress);
		dto.put("setDefault", false);
		this.doService("AccountAddressService", "addAddress", dto);
		return "getAddressList";
	}
	
	/**
	 * 用户中心——收货地址——编辑收货地址
	 * @return
	 * @throws Exception 
	 */
	public String toEditAddress() throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		
		//获取国家列表
		List<LvArea> contryList=(List<LvArea>)this.doService("ShopCartService", "getContryList", dto);
		this.getRequest().setAttribute("contryList", contryList);
		//收货地址
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", userCode);
		dto.put("addressCode", this.getRequest().getParameter("addressCode")); //账户地址code
		LvAccountAddress lvAccountAddress=(LvAccountAddress)this.doService("AccountAddressService", "getAddressByCode", dto);
		
		//获取州省列表
		if(null!=lvAccountAddress.getProvinceId() && !("").equals(lvAccountAddress.getProvinceId())){
			dto.put("parentid", lvAccountAddress.getContryId());
			List<LvArea> provinceList=(List<LvArea>)this.doService("UserCenterService", "getProvinces", dto);
			this.getRequest().setAttribute("provinceList", provinceList);
		}
		
		//获取县市列表
		if(null!=lvAccountAddress.getCityId() && !("").equals(lvAccountAddress.getCityId()) && null!=lvAccountAddress.getProvinceId() && !("").equals(lvAccountAddress.getProvinceId())){
			dto.put("parentid", lvAccountAddress.getProvinceId());
			List<LvArea> cityList=(List<LvArea>)this.doService("UserCenterService", "getProvinces", dto); //获取县市和州省的方法一致，所以可调用一样的方法
			this.getRequest().setAttribute("cityList", cityList);
		}
		
		this.getRequest().setAttribute("flag", "address");
		this.getRequest().setAttribute("lvAccountAddress", lvAccountAddress);
		return "editAddress";
	}
	
	/**
	 * 用户中心——收货地址——编辑收货地址
	 * @return
	 * @throws Exception 
	 */
	public String editAddress() throws Exception{
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lvAccountAddress.setStoreId(this.getFlag());
		dto.put("userCode", userCode);
		dto.put("addr", lvAccountAddress);
		this.doService("AccountAddressService", "editAddress", dto);
		this.getRequest().setAttribute("flag", "address");
		return "getAddressList";
	}
	
	/**
	 * 用户邮件订阅/邮件退订
	 * @return
	 */
	public String  subscribe(){
		try {
			PrintWriter out=this.getResponse().getWriter();
			Boolean flag=lvUserSubscribe.getIsSubscribe();
			if(ObjectUtils.isNotEmpty(lvUserSubscribe.getIsSubscribe())){
			   if(lvUserSubscribe.getIsSubscribe()){//邮件订阅
				  lvUserSubscribe.setCreateTime(new Date());
				  dto.put("model", lvUserSubscribe);
				  lvUserSubscribe=(LvUserSubscribe)this.doService("LvSubscribeService","save", dto);
				  String result="{\"id\":"+lvUserSubscribe.getId()+",\"isSubscribe\":"+flag+",\"email\":\""+lvUserSubscribe.getEmail()+"\"}";
				  out.println(result);
				  out.close(); 
			   }else{//邮件退订 
				  dto.put("model", lvUserSubscribe);
				  lvUserSubscribe=(LvUserSubscribe)this.doService("LvSubscribeService","delete", dto);
				  String result="{\"id\":"+lvUserSubscribe.getId()+",\"isSubscribe\":"+flag+",\"email\":\""+lvUserSubscribe.getEmail()+"\"}";
				  out.println(result);
				  out.close();
			   }	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 根据国家ID获取对应的洲省
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getProvinces(){
		//获取该国家所对应的洲省
		dto.put("parentid", this.getRequest().getParameter("parentid"));
		dto.put("storeId", this.getFlag());
		List<LvArea> areas=(List<LvArea>)this.doService("UserCenterService", "getProvinces", dto);
		
		//将获取的id和name封装至json中
		String json="[";
		if(null!=areas && areas.size()>0){
			for(LvArea area:areas){
				json+="{'id':"+area.getId()+",'nameen':'"+area.getNameen()+"'},";
			}
			json=json.substring(0, json.lastIndexOf(","));
		}
		json+="]";
		
		//输出数据
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
			   out.close();
			}
			
		}
		
		return null;
	}
	
	/**
	 * 用户中心——我的购物车
	 * @return
	 * @throws Exception
	 */
//	@SuppressWarnings("unchecked")
//	public String getCart() throws Exception{
//		
//		//获取用户code
//		String userCode=this.getCookieValue(UserConstant.USER_ID, true);
//		dto.put("userCode", userCode);
//		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
//		dto.put("mallSystem", mallSystem);
//		List list = (List)this.doService("OnlineMallService", "getAllShopCartInfo", dto);
//		this.getRequest().setAttribute("objList",list);
//		this.getRequest().setAttribute("mark","1"); //mark:1,登陆之后的购物车列表展示   2,未登陆之前的购物车列表展示
//		
//		//设置购物车选中标志
//		this.getRequest().setAttribute("flag", "cart");
//		
//		return "myShopCart";
//	}
	
	/**
	 * 用户中心——我的购物车——批量删除
	 * @return
	 * @throws Exception
	 */
//	public String delCartList() throws Exception{
//		
//		String str = this.getRequest().getParameter("str");
//		String codes[]=str.split(";");
//
//		String uid=this.getCookieValue(UserConstant.USER_ID, true);
//		dto.put("userCode", uid);
//		
//		if(null!=codes){
//			for(int i=0; i<codes.length; i++){
//				String code=codes[i];
//				dto.put("code", code);
//				this.doService("OnlineMallService", "delCartByCode", dto);
//			}
//		}
//		
//		//将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
//		String userCode=this.getCookieValue(UserConstant.USER_ID, true); //获取登陆用户code
//		dto.put("userCode", userCode);
//		dto.put("storeFlag", this.getFlag());
//		Integer shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
//		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
//		this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
//		
//		return "getCart";
//	}
	
	
	//删除当前商城的购物车cookie
//	public void delShopCartCookieByMall() throws Exception{
//		
//		//获取商城标识
//		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
//		mallFlag=mallFlag==null?this.getFlag():mallFlag;
//		
//		//获取已有的cookie中是否已有购物车信息
//		Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
//		if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
//			
//			//获取当前商城所对应的店铺
//			String storeFlags[] = Constants.STORE_LIST.get(mallFlag).toString().replaceAll("'", "").split(",");
//			String shopCartList = shopCart.get("list");
//			Integer shopCartNum = Integer.parseInt(shopCart.get("num"));
//			if(null!=storeFlags && storeFlags.length>0){
//				for(String storeFlag : storeFlags){
//					//判断这条产品信息是否存在(根据店铺标志)
//					while(shopCartList.indexOf("@"+storeFlag)!=-1){
//						int startNum=shopCartList.indexOf("@"+storeFlag); //数据之前索引
//						int endNum = shopCartList.indexOf("@",startNum+1); //数据之后索引
//						String delStr=""; //获取要删除的字符串
//						if(endNum!=-1){
//							delStr = shopCartList.substring(startNum,endNum); 
//						}else{
//							delStr = shopCartList.substring(startNum); 
//						}
//						shopCartList = shopCartList.replace(delStr, ""); //将此条产品信息替换掉
//						shopCartNum = shopCartNum-1;
//					}
//				}
//				String str="";
//				if(0!=shopCartNum && !("").equals(shopCartList)){
//					str="list="+shopCartList.toString()+"*num="+shopCartNum;
//				}
//				String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
//				this.addCookie(UserConstant.SHOPCART, str, true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
//			}
//		}
//	}
	
	public void setSuitableJumpUrl(){
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
		if(null != jumpurl){
			this.jumpurl = jumpurl.replace("<script>", "").replace("</script>", "").replace("<", "").replace(">", "");
		}else{
			this.jumpurl = "";
		}
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

	public LvOrder getLvOrder() {
		return lvOrder;
	}

	public void setLvOrder(LvOrder lvOrder) {
		this.lvOrder = lvOrder;
	}

	public LvAccountInfo getLvAccountInfo() {
		return lvAccountInfo;
	}

	public void setLvAccountInfo(LvAccountInfo lvAccountInfo) {
		this.lvAccountInfo = lvAccountInfo;
	}

	public String getOrdnickname() {
		return ordnickname;
	}

	public void setOrdnickname(String ordnickname) {
		this.ordnickname = ordnickname;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public LvAccountAddress getLvAccountAddress() {
		return lvAccountAddress;
	}

	public void setLvAccountAddress(LvAccountAddress lvAccountAddress) {
		this.lvAccountAddress = lvAccountAddress;
	}

	public LvUserSubscribe getLvUserSubscribe() {
		return lvUserSubscribe;
	}

	public void setLvUserSubscribe(LvUserSubscribe lvUserSubscribe) {
		this.lvUserSubscribe = lvUserSubscribe;
	}

	public String getLoginstyle() {
		return loginstyle;
	}

	public void setLoginstyle(String loginstyle) {
		this.loginstyle = loginstyle;
	}

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	public String getSynurl() {
		return synurl;
	}

	public void setSynurl(String synurl) {
		this.synurl = synurl;
	}

	public Integer getJumpFlag() {
		return jumpFlag;
	}

	public void setJumpFlag(Integer jumpFlag) {
		this.jumpFlag = jumpFlag;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getShopFlag() {
		return shopFlag;
	}

	public void setShopFlag(String shopFlag) {
		this.shopFlag = shopFlag;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}


	
	
	
}
