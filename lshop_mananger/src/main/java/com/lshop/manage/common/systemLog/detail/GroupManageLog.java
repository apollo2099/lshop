package com.lshop.manage.common.systemLog.detail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOperationLogs;
import com.lshop.manage.common.systemLog.BaseSystemLog;

@Component("GroupManageLog")
public class GroupManageLog extends BaseSystemLog{

	@Resource
	private HibernateBaseDAO dao;
	@Override
	public boolean execute(String className, String methodName,
			HttpServletRequest request, LvOperationLogs lvOperationLogs)
			throws Exception {
		lvOperationLogs.setOperationModule("团购信息");
		
		if(className.equals("com.lshop.manage.lvGroupBuy.action.LvGroupBuyAction")){//团购信息操作
			if(methodName.equals("add")){
				lvOperationLogs.setOperationKey("添加团购信息");
				lvOperationLogs.setOperationDetails("添加团购，团购名称："+request.getParameter("lvGroupBuy.title"));
			}else if(methodName.equals("edit")){
				lvOperationLogs.setOperationKey("修改团购信息");
				lvOperationLogs.setOperationDetails("修改团购，团购名称："+request.getParameter("lvGroupBuy.title"));
			}else if(methodName.equals("del")){
				lvOperationLogs.setOperationKey("删除团购信息");
				lvOperationLogs.setOperationDetails("修改团购，团购ID："+request.getParameter("lvGroupBuy.id"));
			}
		}else if(className.equals("com.lshop.manage.lvGroupProperty.action.LvGroupPropertyAction")){//团购扩展属性操作
			if(methodName.equals("add")){
				lvOperationLogs.setOperationKey("添加团购扩展属性");
				lvOperationLogs.setOperationDetails("属性名称："+request.getParameter("lvGroupProperty.title")+",团购CODE："+request.getParameter("lvGroupProperty.groupCode"));
			}else if(methodName.equals("edit")){
				lvOperationLogs.setOperationKey("修改团购扩展属性");
				lvOperationLogs.setOperationDetails("属性名称："+request.getParameter("lvGroupProperty.title")+",团购CODE："+request.getParameter("lvGroupProperty.groupCode"));
			}else if(methodName.equals("del")){
				lvOperationLogs.setOperationKey("删除团购扩展属性");
				lvOperationLogs.setOperationDetails("团购扩展属性ID："+request.getParameter("lvGroupProperty.id"));
			}
		}

		return true;
	}


}
