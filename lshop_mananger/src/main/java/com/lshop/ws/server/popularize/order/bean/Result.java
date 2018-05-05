package com.lshop.ws.server.popularize.order.bean;

public class Result implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int STATUS_SUCCEED = 1;			// 成功
	public final static int STATUS_FAIL = 0;			// 失败
	public final static int STATUS_NULL=-1;             // 不存在
	
	private Integer status;
	private String message;

	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
