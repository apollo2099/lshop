package com.lshop.common.spring;

import org.springframework.context.ApplicationEvent;

/**
 * 用户登陆成功事件
 * @author caicl
 *
 */
public class LoginEvent extends ApplicationEvent{
	private static final long serialVersionUID = 3345555397537214813L;
	private String userCode;
	private String mallSystem;
	public LoginEvent(Object source) {
		super(source);
	}
	public LoginEvent(Object source, String userCode, String mallSystem) {
		super(source);
		this.userCode = userCode;
		this.mallSystem = mallSystem;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getMallSystem() {
		return mallSystem;
	}
	public void setMallSystem(String mallSystem) {
		this.mallSystem = mallSystem;
	}

}
