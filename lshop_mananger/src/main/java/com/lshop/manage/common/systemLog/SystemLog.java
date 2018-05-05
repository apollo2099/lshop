package com.lshop.manage.common.systemLog;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOperationLogs;

/**
 * AOP操作日志管理
 * @author zhengxue 2014-06-26
 *
 */
public class SystemLog {

	@Resource
	private HibernateBaseDAO dao;

	public Object around(ProceedingJoinPoint pjp) throws Throwable {

		Signature signature = pjp.getSignature();
		String className = signature.getDeclaringTypeName();// 类名称
		String methodName = signature.getName();// 方法名称

		BaseSystemLog component = SystemlogMap.getName(className + "." + methodName); //获取对应的日志文件

		Object result = pjp.proceed();
		if (null != component) {
//			//判断执行过程是否有异常，没异常的情况下再保存日志
//			try {
//				 result = pjp.proceed();
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			BaseUsers user = (BaseUsers) request.getSession().getAttribute("USER");// 后台登录信息
			
			LvOperationLogs lvOperationLogs = new LvOperationLogs();
			lvOperationLogs.setOperator(user.getUserName());
			lvOperationLogs.setCreatetime(new Date());

			boolean isSuccess = component.execute(className,methodName,request,lvOperationLogs);//生成日志
			
			if (isSuccess) {
				dao.save(lvOperationLogs);
			}
			
		}
		return result;
	}
}
