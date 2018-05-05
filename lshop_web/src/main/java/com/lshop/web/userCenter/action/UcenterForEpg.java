/**   
 * @filename: UcenterForEpg.java
 * @description: TODO
 * @author：dh
 * @time：2014年3月27日 下午1:45:11
 * @version：V1.0   
 */

package com.lshop.web.userCenter.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.base.model.spring.ApplicationContextHolder;
import com.gv.business.ws.boss.userinfo.UserInfoDto;
import com.gv.business.ws.boss.userinfo.WSUserInfoService;
import com.gv.core.datastructure.Dto;
import com.gv.epg.common.constants.ApiConstants;
import com.gv.epg.model.user.service.EpgUserService;
import com.gv.epg.pojo.UcEpgUserInfo;
import com.lshop.web.userCenter.UserCommon;

/**   
 * @author：dh
 * @time：2014年3月27日 下午1:45:11
 * @version：V1.0   
 * @description: TODO
 */
public class UcenterForEpg {
	
	private static final Log logger	= LogFactory.getLog(UcenterForEpg.class);
	private static EpgUserService epgservice = new EpgUserService();
	
	public static UcEpgUserInfo uc_synregist(String email,String pwd,String lang,String flag,Dto dto){
		logger.info("========同步註冊用戶中心，賬號：" + email);
		
		UcEpgUserInfo userInfo = null;
		dto.put("account", email);
		dto.put("password", pwd);
		dto.put("lang", lang);
		dto.put("bizline", UserCommon.getBizline(flag));
		dto.put("terminal", UserCommon.getTerminal(flag));
		
		Dto d = null;
		try{
			d = epgservice.register(dto);
		}catch(Exception e){
			logger.info("========调取同步注册方法失败，賬號：" + email);
			e.printStackTrace();
			return userInfo;
		}
		
		String statu = d.getAsString(ApiConstants.KEY_STATUS);
		if(statu.equals(ApiConstants.STATUS_SUCCEED)){
			logger.info("========註冊用戶中心成功，賬號：" + email);
			userInfo = (UcEpgUserInfo)d.get("userInfo");
		}
		if(statu.equals(ApiConstants.STATUS_EMAIL_ALREADY_USE)){
			logger.info("========註冊用戶中心失敗，賬號已被注册，賬號：" + email);
		}
		if(statu.equals(ApiConstants.STATUS_USER_EXIST_NOT_ACTIVE)){
			logger.info("========註冊用戶中心失敗，賬號已被注册(未激活)，賬號：" + email);
		}
		if(statu.equals(ApiConstants.STATUS_SYSTEM_ERROR)){
			logger.info("========註冊用戶中心失敗，邮箱错误，或者未知错误，賬號：" + email);
		}
		
        return userInfo;
	}
	
	public static Map<String,Object> uc_synlogin(String email,String pwd,String flag,Dto dto){
		Map<String,Object> m = new HashMap<String,Object>();
		logger.info("========用戶中心登陸，賬號：" + email);
		UcEpgUserInfo userInfo = null;
		dto.put("account", email);
		dto.put("password", pwd);
		dto.put("bizline", UserCommon.getBizline(flag));
		dto.put("terminal", UserCommon.getTerminal(flag));
		
		Dto d = null;
		try{
			d = epgservice.login(dto);
		}catch(Exception e){
			logger.info("========调取同步登陆方法失败，賬號：" + email);
			m.put("statu", 0);
			return m;
		}
		
		String statu = d.getAsString(ApiConstants.KEY_STATUS);
		
		if(statu.equals(ApiConstants.STATUS_USER_NOT_FIND)){
			logger.info("========登陸失敗，賬號不存在，賬號：" + email);
			m.put("statu", -1);
		}
		if(statu.equals(ApiConstants.STATUS_ACCOUNT_STOP)){
			logger.info("========登陸失敗，賬號已停用，賬號：" + email);
			m.put("statu", -2);
		}
		if(statu.equals(ApiConstants.STATUS_USER_EXIST_NOT_ACTIVE)){
			logger.info("========登陸失敗，賬號未激活，賬號：" + email);
			m.put("statu", -3);
		}
		if(statu.equals(ApiConstants.STATUS_ACCOUNT_OR_PASSWORD_ERROR)){
			logger.info("========登陸失敗，密碼錯誤，賬號：" + email);
			m.put("statu", -4);
		}
		if(statu.equals(ApiConstants.STATUS_SUCCEED)){
			logger.info("========登陸成功，賬號：" + email);
			userInfo = (UcEpgUserInfo)d.get("userInfo");
			m.put("userInfo", userInfo);
			m.put("statu", 1);
		}
		return m;
	}
	
	public static int uc_syneditPwd(String usercode,String pwd,String newPwd,String flag,Dto dto){
		int stat = 0;
		logger.info("========用戶中心修改密碼，賬號code：" + usercode);
		dto.put("usercode", usercode);
		dto.put("oldpwd", pwd);
		dto.put("newpwd", newPwd);
		dto.put("bizline", UserCommon.getBizline(flag));
		dto.put("terminal", UserCommon.getTerminal(flag));
		
		Dto d = null;
		try{
			d = epgservice.pwdedit(dto);
		}catch(Exception e){
			logger.info("========调取同步修改方法失败，賬號code：" + usercode);
			stat = -2;
			return stat;
		}
		
		String statu = d.getAsString(ApiConstants.KEY_STATUS);
		if(statu.equals(ApiConstants.STATUS_SUCCEED)){
			logger.info("========用戶中心修改密碼成功，賬號code：" + usercode);
			stat = 1;
		}
		
		if(statu.equals(ApiConstants.STATUS_SYSTEM_ERROR)){
			logger.info("========用戶中心修改密碼失敗，賬號code：" + usercode);
			stat = -2;
		}
		if(statu.equals(ApiConstants.STATUS_OLD_PASSWORD_ERROR)){
			logger.info("========原密碼錯誤，用戶中心修改密碼失敗，賬號code：" + usercode);
			stat = -1;
		}
		return stat;
	}
	
	public static int findPwdCode(String email,String lang,String flag,Dto dto){
		logger.info("========用戶中心找回密碼，賬號：" + email);
		int stat = 0;
		dto.put("account", email);
		dto.put("lang", lang);
		dto.put("bizline", UserCommon.getBizline(flag));
		dto.put("terminal", UserCommon.getTerminal(flag));
		Dto d = null;
		
		try{
			d = epgservice.findPwdCode(dto);
		}catch(Exception e){
			logger.info("========调取找回密码方法失败，賬號：" + email);
			stat = -2;
			return stat;
		}
		
		String statu = d.getAsString(ApiConstants.KEY_STATUS);
		if(statu.equals(ApiConstants.STATUS_SUCCEED)){
			logger.info("========找回密码生成验证码成功，賬號：" + email);
			stat = 1;
		}
		if(statu.equals(ApiConstants.STATUS_USER_NOT_FIND)){
			logger.info("========找回密码失败，账号不存在，賬號：" + email);
			stat = -1;
		}
		if(statu.equals(ApiConstants.STATUS_USER_EXIST_NOT_ACTIVE)){
			logger.info("========找回密码失败，账号未激活，賬號：" + email);
			stat = 0;
		}
		return stat;
	}
	
	public static int resetPwd(String email,String pwd,String code,String flag,Dto dto){
		logger.info("========用戶中心设置密碼，賬號：" + email);
		int stat = 0;
		dto.put("account", email);
		dto.put("password", pwd);
		dto.put("code", code);
		dto.put("bizline", UserCommon.getBizline(flag));
		dto.put("terminal", UserCommon.getTerminal(flag));
		Dto d = null;
		
		try{
			d = epgservice.findPwd(dto);
		}catch(Exception e){
			logger.info("========调取设置密码方法失败，賬號：" + email);
			stat = 0;
			return stat;
		}
		
		String statu = d.getAsString(ApiConstants.KEY_STATUS);
		if(statu.equals(ApiConstants.STATUS_SUCCEED)){
			logger.info("========设置密码成功，賬號：" + email);
			stat = 1;
		}
		if(statu.equals(ApiConstants.STATUS_USER_NOT_FIND)){
			logger.info("========找回密码失败，账号不存在，賬號：" + email);
			stat = -1;
		}
		if(statu.equals(ApiConstants.STATUS_PASSWORD_MODIFY_OVERDUE)){
			logger.info("========找回密码失败，激活码已过期，賬號：" + email);
			stat = -2;
		}
		if(statu.equals(ApiConstants.STATUS_ACCOUNT_INVALIDATION)){
			logger.info("========找回密码失败，激活码不匹配，賬號：" + email);
			stat = -3;
		}
		if(statu.equals(ApiConstants.STATUS_SYSTEM_ERROR)){
			logger.info("========找回密码失败，系统错误，賬號：" + email);
			stat = 0;
		}
		return stat;
	}
	
	public static int uc_syneditUserInfo(String flag,Dto dto){
		logger.info("========用戶中心修改用户信息");
		int stat = 0;
		dto.put("bizline", UserCommon.getBizline(flag));
		dto.put("terminal", UserCommon.getTerminal(flag));
		Dto d = null;
		try{
			d = epgservice.update(dto);
		}catch(Exception e){
			logger.info("========调取修改用户信息方法失败");
			stat = 0;
			return stat;
		}
		
		String statu = d.getAsString(ApiConstants.KEY_STATUS);
		if(statu.equals(ApiConstants.STATUS_SUCCEED)){
			logger.info("========修改用户信息成功");
			stat = 1;
		}else if(statu.equals(ApiConstants.STATUS_USER_NICK_EXIST)){
			logger.info("========修改用户昵称已存在");
			stat = -1;
		}else{
			logger.info("========修改用户信息失败");
			stat = 0;
		}
		return stat;
	}
	
	public static UcEpgUserInfo getUserInfo(String flag,Dto dto){
		dto.put("bizline", UserCommon.getBizline(flag));
		dto.put("terminal", UserCommon.getTerminal(flag));
		Dto d = null;
		try{
			d = epgservice.userinfo(dto);
		}catch(Exception e){
			logger.info("========调取获取用户信息方法失败");
			return null;
		}
		return (UcEpgUserInfo)d.get("userInfo");
	}
	
	public static UcEpgUserInfo getUserInfo(Dto dto){
		Dto d = null;
		try{
			d = epgservice.userinfo(dto);
		}catch(Exception e){
			logger.info("========调取获取用户信息方法失败");
			return null;
		}
		return (UcEpgUserInfo)d.get("userInfo");
	}
	
	public static Integer getBalance(String account){
		Integer balance = 0;
		try{
			WSUserInfoService userinfoWs=(WSUserInfoService) ApplicationContextHolder.getBean("userinfoWs");
			UserInfoDto uDto=userinfoWs.queryUserInfo(account,"");
			balance = uDto.getBalance();
		}catch(Exception e){
			logger.info("========获取余额失败，账号：" + account);
		}
		return balance;
	}
	
	public static boolean checkUserExist(Dto dto){
		boolean f = false;
		try{
			Dto d = epgservice.checkUser(dto);
			f = (Boolean)d.get("isexist");
		}catch(Exception e){
			logger.info("========远程调用方法失败");
		}
		return f;
	}
	
	public static void main(String[] args) {
//		Dto dto = new BaseDto();
	}
	
	
	
}
