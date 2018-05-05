package com.lshop.web.userCenter.bbs;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivestars.interfaces.bbs.client.Client;
import com.fivestars.interfaces.bbs.util.XMLHelper;


public class UcenterSSO {
	private static final Log logger = LogFactory.getLog(UcenterSSO.class);
//	public static String bbs_banana_domain = "localhost:8088";
	public static String bbs_banana_domain = "bbs.bananatv.com";

	/**
	 * 同步登陆
	 */
	public static String login(String name,String pwd,String email){
		Client e = new Client();
		LinkedList<String> rs = new LinkedList<String>();
		try{
			String result = e.uc_user_login(email, pwd); 
			rs = XMLHelper.uc_unserialize(result); 
		}catch (Exception ex) {
			
		}
		
		if(rs.size()>0){ 
		 int $uid = Integer.parseInt(rs.get(0)); 
		 if($uid > 0) { 
			 
			 String $ucsynlogin = e.uc_user_synlogin($uid); 
			 logger.info("========同步登录成功，登陆账号："+name); 
			 return $ucsynlogin;

		 } else if($uid == -1) { 
			 logger.info("========同步登录失败，用户不存在，将用户同步注册至ucenter，注册账号："+name); 
			 int statu = regist(name,pwd,email,"");
			 if(statu == 1){
				 return login(name,pwd,email);
			 }
			 
		 } else if($uid == -2) { 
			 logger.info("========同步登录失败，密码错误,对bbs密码进行重置，登陆账号："+name); 
			 int statu = reSetPwd(email,pwd);
			 if(statu == 1){
				 return login(name,pwd,email);
			 }
		 } else { 
			 logger.info("========同步登录失败,登陆账号："+name); 
		 } 
		}else{ 
			logger.info("========同步登录失败,登陆账号："+name); 
		}
		return "";
	}
	
	/**
	 * 同步退出
	 */
	public static String logout(){
		Client uc = new Client(); 
		// 生成同步退出的代码 
		String $ucsynlogout = uc.uc_user_synlogout(); 
		logger.info("========同步退出成功");
		return $ucsynlogout;
	}
	
	/**
	 * 同步注册
	 */
	public static int regist(String name,String pwd,String email,String ip){
		 if(isDBUsable()){
			Client uc = new Client(); 
				// 生成同步退出的代码 
				String $returns = uc.uc_user_register(name, pwd ,email ); 
				if($returns!=null&&!$returns.equals("")){
					int $uid = Integer.parseInt($returns); 
					if($uid <= 0){ 
						if($uid == -1) { 
							logger.info(name + "========同步注册失败：用户名不合法"); 
						} else if($uid == -2) { 
							logger.info(name + "========同步注册失败：包含未允许注册的词语"); 
						} else if($uid == -3) { 
							logger.info(name + "========同步注册失败：用户名已经存在"); 
						} else if($uid == -4) { 
							logger.info(name + "========同步注册失败：Email 格式有误"); 
						} else if($uid == -5) { 
							logger.info(name + "========同步注册失败：Email 不允许注册"); 
						} else if($uid == -6) { 
							logger.info(name + "========同步注册失败：该 Email 已经被注册"); 
						} else { 
							logger.info(name + "========同步注册失败：未定义"); 
						} 
					} else { 
						logger.info("========同步注册成功:"+$returns + "==注册账号：" + name); 
						SynDB.getSynDB().synDiscuzDB($uid,name, email,ip);
						return 1;
					}
				}else{
					logger.info("========同步注册失败:注册账号：" + name); 
				}
		}

		return 0;
	}
	
	/**
	 * 判断数据库是否可用
	 */
	public static boolean isDBUsable(){
		try{
			if(SynDB.getSynDB().getDiscuzDB().getDBConn() != null) return true;
		}catch(Exception e){
			logger.info("========连接同步数据库失败"); 
		}
		return false;
	}
	
	/**
	 * 同步修改信息（密码、邮箱）
	 */
	public static void edit(String username,String oldPwd,String newPwd,String email){
		Client uc = new Client();
		String result = uc.uc_user_edit(username, oldPwd, newPwd, "", 1, "", "");
		int res = Integer.valueOf(result);
		switch (res) {
		case 1:
			logger.info(username + "========同步更新成功");
			break;
		case 0:
			logger.info(username + "========同步更新失败:没有做任何修改");		
			break;
		case -1:
			logger.info(username + "========同步更新失败:旧密码不正确");		
			break;
		case 4:
			logger.info(username + "========同步更新失败:Email 格式有误");
			break;
		case 5:
			logger.info(username + "========同步更新失败:Email 不允许注册");
			break;
		case 6:
			logger.info(username + "========同步更新失败:该 Email 已经被注册");
			break;
		case 7:
			logger.info(username + "========同步更新失败:没有做任何修改");
			break;
		case 8:
			logger.info(username + "========同步更新失败:该用户受保护无权限更改");
			break;
		default:
			logger.info(username + "========同步更新失败");
			break;
		}
	}
	
	/**
	 * 不需要原密码的情况下重置密码
	 */
	public static int reSetPwd(String email,String newPwd){
		logger.info(email + "========UC重置密码");
		try{
			SynDB.getSynDB().reSetUCPwd(email, newPwd);
			return 1;
		}catch(Exception e){
			logger.info(email + "========UC重置密码失败");
		}
		return 0;
	}
	
	public static String filterName(String name){
		return name.replace("@", "").replace(".", "");
	}
	
	public static String filterDomain(String flag,String res){
		if(flag.equals("bmcn") || flag.equals("bscn") ){
			if(res.contains(bbs_banana_domain)) return res;
		}
		return "";
	} 
	
	public static String getSynUrl(String str,String regix){
		String re = "";
		if(null != str && !str.equals("")){
			String[] ss = str.split(" ");
			for(String s : ss){
				if(s.startsWith("src=\"")){
					s = s.substring(5, s.length()-1);
					if(s.contains(regix)){
						re = s;
						break;
					}
				}
			}
		}
		return re;
	}

	public static void main(String[] args) {
		String str = "<script type=\"text/javascript\" src=\"http://localhost:8088/api/uc.php?time=1409820745&code=fed3oNnpckiRTiGcYA6CTKDZ5mLZx9He0uknPgOGYL39p9Q7d6XckUqnr9Y49SGe9Q7IrBLTE%2Bq9aHt%2FXg\" reload=\"1\"></script><script type=\"text/javascript\" src=\"http://www.bananatv.com/api/uc.php?time=1409820745&code=fed3bLdRW1VYoGTss%2BhijpsCEWuT%2BudY3GfSixqObvyPXjHw3kcWL7C5RpvUGUXdCKSVcObJ%2FCtfvt8asg\" reload=\"1\"></script>";
		getSynUrl(str,bbs_banana_domain);
	
	
	}

}
