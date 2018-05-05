package com.lshop.ws.server.bz.order.bean;

public class Result implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int STATUS_SUCCEED = 1;			// 成功
	public final static int STATUS_FAIL = 0;			// 失败
	
	private Integer status;
	private String message;
	private String syncdate;
	
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
	public String getSyncdate() {
		return syncdate;
	}
	public void setSyncdate(String syncdate) {
		this.syncdate = syncdate;
	}
}
