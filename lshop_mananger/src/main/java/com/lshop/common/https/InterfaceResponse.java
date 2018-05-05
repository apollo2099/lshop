package com.lshop.common.https;

public class InterfaceResponse {
	public static final int STATUS_SUCCESS = 1;	// 成功状态
	public static final int STATUS_FAIL = 0;	// 失败状态
	
	private String responseBody;	// 返回数据
	private int responseStatus;		// 返回状态
	private String responseMessage;	// 返回消息
	
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	public int getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
