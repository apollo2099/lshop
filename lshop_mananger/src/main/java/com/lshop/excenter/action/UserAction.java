package com.lshop.excenter.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvUser;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserInfo;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.excenter.ExcenterConstant;
@SuppressWarnings("unchecked")
@Controller("ExcenterUserAction")
@Scope("prototype")
/**
 * 用户中心
 */
public class UserAction extends BaseAction {
	private LvUser lvUser;
	private LvUserPromoters userp;
	private LvUserInfo lvUserInfo;
	private String uname;//登录用户名
	private String pwd;//密码
	private String code;
	private String newPwd;
	private String ordnickname;//原呢称
	private String jumpurl;
	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String login() throws Exception {
		dto.put("uname", uname.trim());
		dto.put("pwd", MD5.convert32(pwd.trim()));
		dto.put("ip", this.getRemoteAddress());
		LvUser user = (LvUser) super.doService("ExcenterUserService", "login", dto);
		 
		if (user != null){
			if(!user.getPwd().equals(MD5.convert32(pwd.trim()))){
				this.getRequest().setAttribute("msg", "用户名或密码不正确！");
				return this.INPUT;
			}
			if(user.getIsDelete()==1){
				this.getRequest().setAttribute("msg", "帐号已被管理员停用！");
				return this.INPUT;
			}
			this.addCookie(ExcenterConstant.EXUSER_ID, user.getId().toString(), true, -1,getText("domain.base.name"));
			 SimpleDateFormat  format = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm");
			 String lastTime="";
//			 if(user.getLastTime()==null)
				  lastTime=format.format(new Date());
//			 else
//			      lastTime =format.format(user.getLastTime());
			 String str="uid="+user.getId().toString()+"*email="+user.getEmail()+"*nickname="+user.getNickname()+"*lastTime="+lastTime;
//			 if(user.getEmail().contains("@"))
//			  return this.LOGIN;
//			 else 
//			 {
//			  return "detail";	
//		     }
			
			 //判断用户是否补全个人资料，若没有，刚登陆后跳转到个人资料页面
			dto.put("uid", user.getId());
			LvUserPromoters up=(LvUserPromoters) super.doService("ExcenterUserService", "getUserInfo", dto);
			
			if(up!=null){
				 str+="*userType="+up.getUserType();
			 }
			 this.addCookie(ExcenterConstant.EXUSER_CENTER,str, true, -1,getText("domain.base.name"));
			 
			if ((null!=up.getRealName())&& (!("").equals(up.getRealName())))
			{
				return this.LOGIN;
			}
			else {
				return "detail";
			}
		}else{
			LvUserCoupon uc=(LvUserCoupon)super.doService("ExcenterUserService", "getUserCoupon", dto);
			if(uc==null)
			{
				this.getRequest().setAttribute("msg", "用户名或密码不正确！");
			}
			else
			{
				this.getRequest().setAttribute("msg", "帐号已被管理员删除！");
			}
			
			return this.INPUT;
		}
	}
	
	public String account() throws Exception{
		String uid=this.getCookieValue(ExcenterConstant.EXUSER_ID, true);
		if(uid==null||"".equals(uid.trim())){
			return this.INPUT;
		}
		dto.put("uid", uid);
		this.lvUser=(LvUser)this.doService("ExcenterUserService", "getUser", dto);
		this.getRequest().setAttribute("flag", "account");
		return "account";
	}
	public String editpwd() throws Exception{
		this.getRequest().setAttribute("flag", "pwd");
		
		String uid=this.getCookieValue(ExcenterConstant.EXUSER_ID, true);
		dto.put("uid", uid);
		this.doService("PromoterManageService","befPay", dto);
		Integer userType=dto.getAsInteger("userType");
		this.getRequest().setAttribute("userType", userType);
	    if(userType!=null&&userType==1)
	    {
	    	this.getRequest().setAttribute("specialAmount", dto.getAsString("specialAmount"));
	    	LvCoupon coupon=(LvCoupon)dto.get("coupon");
	    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	    	this.getRequest().setAttribute("createTime",format.format(coupon.getCreateTime()));
	    	this.getRequest().setAttribute("validitydate",format.format(coupon.getValidityDate()));
	    }
	    
	    int presentNum=dto.getAsInteger("presentNum");
		int rateNum=dto.getAsInteger("rateNum");
		int presentRate=dto.getAsInteger("presentRate");
		int totalNum=dto.getAsInteger("totalNum");
		this.getRequest().setAttribute("presentNum", presentNum);
		this.getRequest().setAttribute("rateNum", rateNum);
		this.getRequest().setAttribute("presentRate", presentRate);
		this.getRequest().setAttribute("totalNum", totalNum);
		
		return "editpwd";
	}
	public String doEditpwd() throws Exception{
		String uid=this.getCookieValue(ExcenterConstant.EXUSER_ID, true);
		dto.put("uid", uid);
		pwd=MD5.convert32(this.pwd.trim());
		dto.put("origPwd",pwd);
		if(newPwd.length()<6){
			this.getRequest().setAttribute("msg", "密码不能小于6位！");
			return "editpwd";
		}
		dto.put("newPwd",  MD5.convert32(this.newPwd));
		this.lvUser=(LvUser)this.doService("ExcenterUserService", "getUser", dto);
		if(!pwd.equals(lvUser.getPwd())){
			this.getRequest().setAttribute("msg", "原密码不正确！");
			return "editpwd";
		}
		this.doService("ExcenterUserService", "editPwd", dto);
		this.getSession().setAttribute("msg", "密码修改成功，下次登录请用新密码！");
		return "doEditpwd";
	}
	public String basic() throws Exception{
		String uid=this.getCookieValue(ExcenterConstant.EXUSER_ID, true);
		dto.put("uid", uid);
		this.userp=(LvUserPromoters)this.doService("ExcenterUserService", "getUserInfo", dto);
		
		Integer userType=dto.getAsInteger("userType");
		this.getRequest().setAttribute("userType", userType);
	    if(userType!=null&&userType==1)
	    {
	    	this.getRequest().setAttribute("specialAmount", dto.getAsString("specialAmount"));
	    	LvCoupon coupon=(LvCoupon)dto.get("coupon");
	    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	    	this.getRequest().setAttribute("createTime",format.format(coupon.getCreateTime()));
	    	this.getRequest().setAttribute("validitydate",format.format(coupon.getValidityDate()));
	    }
	    
	    int presentNum=dto.getAsInteger("presentNum");
		int rateNum=dto.getAsInteger("rateNum");
		int presentRate=dto.getAsInteger("presentRate");
//		int totalNum=dto.getAsInteger("totalNum");
		this.getRequest().setAttribute("presentNum", presentNum);
		this.getRequest().setAttribute("rateNum", rateNum);
		this.getRequest().setAttribute("presentRate", presentRate);
//		this.getRequest().setAttribute("totalNum", totalNum);
		return "basic";
	}
	/**
	 * 修改基本资料
	 * @return
	 * @throws Exception 
	 */
	public String  edit() throws Exception{
        if(null==userp||"".equals(userp.getAccountNumber().trim())||null==userp.getAccountTypes())
        {
        	 this.getSession().setAttribute("msg", "false");
		}
        else
		{
			dto.setDefaultPo(userp);
			String uid=this.getCookieValue(ExcenterConstant.EXUSER_ID, true);
			dto.put("uid", uid);
			this.doService("ExcenterUserService", "edit", dto);
			if(dto.getAsString("message")!=null&&!dto.getAsString("message").equals(""))
			{
				this.getSession().setAttribute("msg", "false");
				this.getSession().setAttribute("message",dto.getAsString("message"));
			}
			else
			{
			 this.getSession().setAttribute("msg", "true");
			}
		}
		return "edit";
	}
   /**
    * 检查邮箱或昵称是否存在
    * @return
    */
	public String isExistsUser() {
		PrintWriter out=null;
		try {
			out=this.getResponse().getWriter();
			if(lvUser!=null){
					 dto.setDefaultPo(lvUser);
					 Boolean flag=(Boolean)doService("ExcenterUserService", "isExistsUser",dto);
					 if(flag){
						 out.print(1);//电子邮箱或用户名已存在
					 }else{
						 out.print(0);//电子邮箱或用户名不存在
					 }
		   }else{
			   out.print("请输入电子邮箱！");
		   }
		} catch (IOException e) {
			  e.getMessage(); 
		} finally{
		  if(out!=null){
			 out.close();	
		   }
	    }
		return null;
	}
	/**
	 * 用户注册
	 * @return
	 * @throws Exception 
	 */
	public String regedit() throws Exception{
		dto.setDefaultPo(userp);
		userp.setApprovalStatus((short)0);
		userp.setUserType(0);
		Integer issuccess=(Integer)doService("ExcenterUserService", "add",dto);
		if(issuccess>0){
			return this.SUCCESS;
		}
		return this.INPUT;
		
	}
	/**
	 * 用户退出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception{
		this.removeByCookie(ExcenterConstant.EXUSER_ID,getText("domain.base.name"));
		this.removeByCookie(ExcenterConstant.EXUSER_CENTER,getText("domain.base.name"));
		return "logout";
	}
	/**
	 * 找回密码
	 * @return
	 */
	public String password(){
		if(lvUser!=null){
			String email=lvUser.getEmail().trim();
			if(!"".equals(email)){
				dto.put("email", email);
				Integer newPwd=100000+new Random().nextInt(899999);
				dto.put("newPwd", MD5.convert32(newPwd.toString())); 
				LvUser user =(LvUser)this.doService("ExcenterUserService", "password", dto);
				if(user!=null){
					dto.put("tplKey", "FIND_USER_PASSWORD");
					dto.put("storeId", "tvpadcn");
					LvEmailTpl	lvEmailTpl = (LvEmailTpl) this.doService("lvEmailManageService","get", dto);
					dto.put("title", lvEmailTpl.getEnTitle());
					String body="";
					//获取配置的发件人
					dto.put("emailService",this.getText("lshop.email.send.many.smtpHost"));
					dto.put("sendEmail", this.getText("lshop.email.send.many.userName"));
					dto.put("emailPwd", this.getText("lshop.email.send.many.password"));
					dto.put("mailfromName", this.getText("lshop.email.mailfromname"));
					if(dto.getAsString("eml")!=null&&dto.getAsString("eml").length()>0)
					{
					  dto.put("userEmail",dto.getAsString("eml"));
					  body=(lvEmailTpl.getEn()+lvEmailTpl.getZh()).replace("{nickname}", user.getNickname()).replace("{pwd}", newPwd.toString()).replace("{email}",dto.getAsString("eml"));
					}
					else
					{
					  dto.put("userEmail", user.getEmail());
					  body=(lvEmailTpl.getEn()+lvEmailTpl.getZh()).replace("{nickname}", user.getNickname()).replace("{pwd}", newPwd.toString()).replace("{email}", user.getEmail());
					}
					body=body.replace("{sendtime}", StringUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
					dto.put("content", body);
				    doService("emailSendService", "sendEmailCode", dto);
				}else{
					return this.ERROR;
				}
				return "pwdresult";
			}
		}
		return this.ERROR;
	}
	public LvUserInfo getLvUserInfo() {
		return lvUserInfo;
	}

	public void setLvUserInfo(LvUserInfo lvUserInfo) {
		this.lvUserInfo = lvUserInfo;
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

	public LvUser getLvUser() {
		return lvUser;
	}

	public void setLvUser(LvUser lvUser) {
		this.lvUser = lvUser;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getOrdnickname() {
		return ordnickname;
	}

	public void setOrdnickname(String ordnickname) {
		this.ordnickname = ordnickname;
	}

	public String getJumpurl() {
		return jumpurl;
	}

	public void setJumpurl(String jumpurl) {
		this.jumpurl = jumpurl;
	}

	public LvUserPromoters getUserp() {
		return userp;
	}

	public void setUserp(LvUserPromoters userp) {
		this.userp = userp;
	}
	
}
