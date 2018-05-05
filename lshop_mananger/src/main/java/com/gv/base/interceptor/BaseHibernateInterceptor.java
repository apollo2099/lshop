package com.gv.base.interceptor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.gv.base.model.spring.ApplicationContextHolder;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.base.springSecurity.web.support.BaseAuthenticationSuccessHandler;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：BaseHibernateInterceptor   
 * 类描述：   Hibernate拦截器,将公共字段的赋值操作统一处理
 * 创建人：  yangzheng
 * 创建时间：2012-4-23 上午11:28:16   
 * 修改人：yangzheng   
 * 修改时间：2012-4-23 上午11:28:16   
 * 修改备注：   
 * @version
 */
public class BaseHibernateInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = -8597658125309889388L;

	/**
	 * 
	 * 功能描述：   执行插入操作前 为createdBy,createdDate 赋值
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-23 上午11:23:36   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-23 上午11:23:36   
	 * 修改备注：
	 * @param entity
	 * @param id
	 * @param state
	 * @param propertyNames
	 * @param types
	 * @return
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state,String[] propertyNames, Type[] types) {
		BaseAuthenticationSuccessHandler ss = (BaseAuthenticationSuccessHandler) ApplicationContextHolder.getBean("baseSuccessHandler");
		BaseUsers baseUser = ss.getBaseUser();
		
		for (int index = 0; index < propertyNames.length; index++) {
				/* 找到名为"createdBy"的属性 */
			if ("createdBy".equals(propertyNames[index])) { 
			  //使用拦截器将"createdBy" 属性赋上值
				state[index] = baseUser.getUserCode();
			}
			if ("createdDate".equals(propertyNames[index])) { 
				 //使用拦截器将"createdDate" 属性赋上值
					state[index] = new Timestamp(System.currentTimeMillis());
			}
		}
		return true;
	}

	/**
	 * 
	 * 功能描述：   执行更新操作时为lastUpdatedBy,lastUpdatedDate 赋值
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-23 上午11:24:40   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-23 上午11:24:40   
	 * 修改备注：
	 * @param entity
	 * @param id
	 * @param currentState
	 * @param previousState
	 * @param propertyNames
	 * @param types
	 * @return
	 */
	public boolean onFlushDirty(Object entity, Serializable id,Object[] currentState, Object[] previousState,String[] propertyNames, Type[] types) {
		
		BaseAuthenticationSuccessHandler ss = (BaseAuthenticationSuccessHandler) ApplicationContextHolder.getBean("baseSuccessHandler");
		BaseUsers baseUser = ss.getBaseUser();
		for (int index = 0; index < propertyNames.length; index++) {
			/* 找到名为"lastUpdatedBy"的属性 */
			if ("lastUpdatedBy".equals(propertyNames[index])) { 
            //使用拦截器将"lastUpdatedBy" 属性赋上值
				currentState[index] = baseUser.getUserCode();
			}
			if ("lastUpdatedDate".equals(propertyNames[index])) { 
				//使用拦截器将"lastUpdatedDate" 属性赋上值
				currentState[index] = new Timestamp(System.currentTimeMillis());
			}
		}
		
		return true;
	}

	
	
}
