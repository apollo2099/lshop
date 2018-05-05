package com.lshop.web.activity.service;

import java.util.List;

import com.lshop.common.activity.vo.RegistActivity;

/**
 * 注册活动
 * @author caicl
 *
 */
public interface RegistService {
	/**
	 * 返回系统所有正在进行中的注册活动
	 * @param mallFlag TODO
	 * @return
	 * @throws Exception 
	 */
	List<RegistActivity> getAllRegistActivitie(String mallFlag) throws Exception;
	/**
	 * 注册成功回调
	 * @param userCode
	 * @param mallFlag TODO
	 * @return
	 * @throws Exception
	 */
	boolean userRegisted(String userCode, String mallFlag) throws Exception;
	/**
	 * 异步注册活动回调
	 * @param userCode
	 * @param mallFlag TODO
	 */
	void asyncRegisted(String userCode, String mallFlag);
}
