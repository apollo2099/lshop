package com.lshop.manage.common.systemLog.detail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvOperationLogs;
import com.lshop.manage.common.systemLog.BaseSystemLog;

/**
 * AOP操作日志管理_活动管理
 * @author zhengxue 2014-06-26
 *
 */
@Component("ActivityManageLog")
public class ActivityManageLog extends BaseSystemLog {
	
	@Resource
	private HibernateBaseDAO dao;
	
	@Override
	public boolean execute(String className, String methodName,
			HttpServletRequest request, LvOperationLogs lvOperationLogs)
			throws Exception {
		
		lvOperationLogs.setOperationModule("活动管理");
		
		//新增
		if(methodName.equals("save")){
			lvOperationLogs.setOperationKey("新增");
			lvOperationLogs.setOperationDetails("新增活动："+request.getParameter("lvActivity.activityTitle"));
			
		//修改（程序有问题待测试）
		}else if(methodName.equals("edit")){
			lvOperationLogs.setOperationKey("修改");
			lvOperationLogs.setOperationDetails("修改活动："+request.getParameter("lvActivity.activityTitle"));
			
		//启/停用
		}else if(methodName.equals("updateStatus")){
			LvActivity lvActivity = (LvActivity)dao.load(LvActivity.class, Integer.parseInt(request.getParameter("lvActivity.id")));
			Integer status = Integer.parseInt(request.getParameter("lvActivity.status"));
			if(status==1){
				lvOperationLogs.setOperationKey("启用");
				lvOperationLogs.setOperationDetails("启用活动："+lvActivity.getActivityTitle());
			}else if(status==0){
				lvOperationLogs.setOperationKey("停用");
				lvOperationLogs.setOperationDetails("停用活动："+lvActivity.getActivityTitle());
			}
		}
		
		return true;
	}

}
