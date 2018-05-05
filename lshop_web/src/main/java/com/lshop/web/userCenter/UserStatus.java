package com.lshop.web.userCenter;

public class UserStatus {

		/**
		 * 登陆成功
		 */
		public final static int LOGIN_SUC = 1;
		/**
		 * 系统错误
		 */
		public final static int LOGIN_SYS_ERRO = 0;
		/**
		 * 登陆账号不存在
		 */
		public final static int LOGIN_ACCOUNT_NOT_EXIST = -1;
		/**
		 * 账号已停用
		 */
		public final static int LOGIN_ACCOUNT_DISABLED = -2;
		/**
		 * 账号未激活
		 */
		public final static int LOGIN_ACCOUNT_NOT_ACTIVATED = -3;
		/**
		 * 密码错误
		 */
		public final static int LOGIN_PASSWORD_ERRO = -4;
		/**
		 * 验证码错误
		 */
		public final static int LOGIN_CODE_ERRO = -5;
		/**
		 * 账号已经被绑定
		 */
		public final static int LOGIN_ISBIND = -6;
		/**
		 * 取消绑定
		 */
		public final static int BIND_CANCEL = -7;
		/**
		 * 会话超时(lid过期)
		 */
		public final static int LID_INVALID = -8;
		/**
		 * 系统错误
		 */
		public final static int SYS_ERRO = -9;
	
		/**
		 * 注册成功
		 */
		public final static int REG_SUC = 0;
		/**
		 * 注册失败
		 */
		public final static int REG_ERRO = 2;
		/**
		 * 验证码错误
		 */
		public final static int REG_CODE_ERRO = -2;
		/**
		 * 登录判断标志-注册
		 */		
		public final static String LOGIN_STYLE_REG="0";
		/**
		 * 登录判断标志-登录
		 */
		public final static String LOGIN_STYLE_LOGIN="1";

}
