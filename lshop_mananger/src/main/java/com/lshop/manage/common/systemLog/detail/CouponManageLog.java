package com.lshop.manage.common.systemLog.detail;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvOperationLogs;
import com.lshop.manage.common.systemLog.BaseSystemLog;

/**
 * AOP操作日志管理_优惠券管理
 * @author zhengxue 2014-06-26
 *
 */
@Component("CouponManageLog")
public class CouponManageLog extends BaseSystemLog {

	@Resource
	private HibernateBaseDAO dao;
	
	@Override
	public boolean execute(String className, String methodName,
			HttpServletRequest request, LvOperationLogs lvOperationLogs)
			throws Exception {
		
		lvOperationLogs.setOperationModule("优惠券管理");
		
		//添加优惠券
		if(methodName.equals("save")){
			lvOperationLogs.setOperationKey("添加优惠券");
			lvOperationLogs.setOperationDetails("添加优惠券，优惠券名称："+request.getParameter("lvCouponType.name"));
			
		//下载优惠码
		}else if(methodName.equals("couponDownload")){
			LvCouponType lvCouponType = (LvCouponType)dao.load(LvCouponType.class, Integer.parseInt(request.getParameter("lvCouponType.id")));
			lvOperationLogs.setOperationKey("下载优惠码");
			lvOperationLogs.setOperationDetails("下载优惠码，优惠券名称："+lvCouponType.getName()+"，下载数量："+request.getParameter("couponDownloadNum"));
			
		//追加数量
		}else if(methodName.equals("updateTotalNumber")){
			LvCouponType lvCouponType = (LvCouponType)dao.load(LvCouponType.class, Integer.parseInt(request.getParameter("lvCouponType.id")));
			lvOperationLogs.setOperationKey("追加数量");
			lvOperationLogs.setOperationDetails("追加数量，优惠券名称："+lvCouponType.getName()+"，追加数量："+request.getParameter("lvCouponType.addNumber"));

		//延长有效期
		}else if(methodName.equals("updateExtendDate")){
			LvCouponType lvCouponType = (LvCouponType)dao.load(LvCouponType.class, Integer.parseInt(request.getParameter("lvCouponType.id")));
			 SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
//			 Date date = sdf.parse(request.getParameter("lvCouponType.endTime"));
//			 String endTime = sdf.format(date)+" 23:59:59";
			lvOperationLogs.setOperationKey("延长有效期");
			lvOperationLogs.setOperationDetails("延长有效期，优惠券名称："+lvCouponType.getName()+"，延长到："+sdf.format(lvCouponType.getEndTime()));
			
		//启用
		}else if(methodName.equals("updateStatus")){
			LvCouponType lvCouponType = (LvCouponType)dao.load(LvCouponType.class, Integer.parseInt(request.getParameter("lvCouponType.id")));
			lvOperationLogs.setOperationKey("启用");
			lvOperationLogs.setOperationDetails("启用，优惠券名称："+lvCouponType.getName());

		//停用
		}else if(methodName.equals("updateDisable")){
			LvCouponType lvCouponType = (LvCouponType)dao.load(LvCouponType.class, Integer.parseInt(request.getParameter("lvCouponType.id")));
			lvOperationLogs.setOperationKey("停用");
			lvOperationLogs.setOperationDetails("停用，优惠券名称："+lvCouponType.getName());
			
		}
		return true;
	}
}
