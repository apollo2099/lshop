package com.lshop.common.https;

import java.net.ConnectException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import com.lshop.common.util.Constants;
import com.lshop.common.xml.XmlParse;

public class InterfaceRequest {
	
	static{
    	Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);
    	Protocol.registerProtocol("https", https);
	}
	
	public InterfaceRequest(String url) {
		postMethod = new PostMethod(url);
	}
	
	private PostMethod postMethod = null;
	
	/**
	 * 添加请求参数
	 * @param paramName		参数名称
	 * @param paramValue	参数值
	 */
	public void addParameter(String paramName, String paramValue) {
		postMethod.addParameter(paramName, paramValue);
	}
	
	public void addParameter(String paramName, int paramValue) {
		addParameter(paramName, String.valueOf(paramValue));
	}

	public void addParameter(String paramName, float paramValue) {
		addParameter(paramName, String.valueOf(paramValue));
	}
	
	public void addParameter(String paramName, double paramValue) {
		addParameter(paramName, String.valueOf(paramValue));
	}
	
	/**
	 * post方式调用http请求
	 */
	public InterfaceResponse post() {
		InterfaceResponse response = new InterfaceResponse();
		HttpClient client = new HttpClient();

		postMethod.setRequestHeader("User-Agent", Constants.HTTPS_USER);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		Integer responseStatus = null;
		try {
			responseStatus = client.executeMethod(postMethod);
//			System.out.println(responseStatus);
			String body = postMethod.getResponseBodyAsString();
			response.setResponseBody(body);
			
			postMethod.releaseConnection();	// 释放连接
		} catch (ConnectException e) {
			e.printStackTrace();
			response.setResponseStatus(InterfaceResponse.STATUS_FAIL);
			response.setResponseMessage("请求接口未响应");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseStatus(InterfaceResponse.STATUS_FAIL);
			response.setResponseMessage("接口调用出错");
			return response;
		}
		
		if (responseStatus == null || responseStatus != HttpStatus.SC_OK) {
			response.setResponseStatus(InterfaceResponse.STATUS_FAIL);
			response.setResponseMessage("接口调用出错");
			return response;
		}
		response.setResponseStatus(InterfaceResponse.STATUS_SUCCESS);
		return response;
	}
	
	public static void main(String[] args) {
//		Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);
//    	Protocol.registerProtocol("https", https);
//		String url = "https://10.0.1.219:7043/api/userlist?bizline=0001";
//		String url = "http://10.0.2.85:8081/api/userlist";
		String url = "https://10.0.2.85:8443/api/userlist";
		InterfaceRequest request = new InterfaceRequest(url);
		request.addParameter("bizline", "-1");
		
		InterfaceResponse response = request.post();
		
		String body = response.getResponseBody();
		
		System.out.println(body);
		
		XmlParse parse = new XmlParse(body);
		String status = parse.getElementText("result.status");
		String message = parse.getElementText("result.message");
		
		System.out.println(status);
		System.out.println(message);
	}
	
}
