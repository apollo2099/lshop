package com.lshop.web.interceptor;

import com.lshop.common.action.BaseAction;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 店铺权限认证
 * @author xusl
 *
 */
public class StoreAuthInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 4949812834762901805L; 

	public String intercept(ActionInvocation invocation)  throws Exception {
		
		//获取用户请求action、及方法
		BaseAction action = (BaseAction) invocation.getAction();
		String serverName = action.getRequest().getServerName();
		String flag = Constants.STORE_IDS.get(serverName.trim());
		if(flag!=null){
			action.setFlag(flag);
			String storeDomain=StoreHelp.getStoreDomainByFlag(StoreHelp.getParentFlagByFlag(flag));
			if(storeDomain==null){
				action.setStoreDomain("http://"+serverName);
				
			}else{
				action.setStoreDomain("http://"+storeDomain);
			}
			//添加资源管理域名配置
			String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(flag);//根据店铺标志查询商城体系
		    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
		    String resDomain="http://res"+domainPostfix;
		    action.setResDomain(resDomain);
		}else{
			action.getResponse().sendRedirect("/common/404.jsp");
		}
	
		return invocation.invoke();

	}

}


