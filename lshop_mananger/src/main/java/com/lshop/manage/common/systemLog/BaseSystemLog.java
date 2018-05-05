package com.lshop.manage.common.systemLog;

import javax.servlet.http.HttpServletRequest;

import com.lshop.common.pojo.logic.LvOperationLogs;

/**
 * AOP操作日志管理
 * @author zhengxue 2014-06-26
 *
 */
public abstract class BaseSystemLog {
	/**
	 * 
	 * @Description:生成日志
	 * @author CYJ
	 * @date 2014-1-21 上午9:46:41
	 * @param methodName
	 * @param args
	 * @param systemLog
	 * @param retVal
	 * @return 
	 * @throws Exception
	 */
	public abstract boolean execute(String className, String methodName, HttpServletRequest request, LvOperationLogs lvOperationLogs) throws Exception;

}
