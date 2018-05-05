package com.lshop.manage.weixin.util;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;



public class HttpClientUtil {
	 public static String get(String url) {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			String respStr = new String(responseBody, "utf-8");
			return respStr;
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return null;
	}

	 public static String post(String url) {
			// 构造HttpClient的实例
			HttpClient httpClient = new HttpClient();
			// 创建GET方法的实例
			GetMethod getMethod = new GetMethod(url);
			// 使用系统提供的默认的恢复策略
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			try {
				// 执行getMethod
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: " + getMethod.getStatusLine());
				}
				// 读取内容
				byte[] responseBody = getMethod.getResponseBody();
				// 处理内容
				String respStr = new String(responseBody, "utf-8");
				return respStr;
			} catch (HttpException e) {
				// 发生致命的异常，可能是协议不对或者返回的内容有问题
				System.out.println("Please check your provided http address!");
				e.printStackTrace();
			} catch (IOException e) {
				// 发生网络异常
				e.printStackTrace();
			} finally {
				// 释放连接
				getMethod.releaseConnection();
			}
			return null;
		}
	 /**
	  * 根据指定的url以及json参数请求
	  * @param url
	  * @param json
	  * @return
	  */
	@SuppressWarnings("deprecation")
	public static String post(String url, String json) {
	 HttpClient client = new HttpClient();
     PostMethod post = new PostMethod(url);
     post.setRequestBody(json);
     post.getParams().setContentCharset("utf-8");
     //发送http请求
     String respStr = "";
     try {
         client.executeMethod(post);
         respStr = post.getResponseBodyAsString();
     } catch (HttpException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
     return respStr;
	}
	
	
	
	 
	 
	 
	 

	 
	
		 
}
