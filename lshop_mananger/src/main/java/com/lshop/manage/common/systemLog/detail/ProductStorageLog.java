package com.lshop.manage.common.systemLog.detail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOperationLogs;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.manage.common.systemLog.BaseSystemLog;

/**
 * AOP操作日志管理_商品库管理
 * @author zhengxue 2014-06-26
 *
 */
@Component("ProductStorageLog")
public class ProductStorageLog extends BaseSystemLog {
	
	@Resource
	private HibernateBaseDAO dao;
	
	@Override
	public boolean execute(String className, String methodName,
			HttpServletRequest request, LvOperationLogs lvOperationLogs)
			throws Exception {
		lvOperationLogs.setOperationModule("商品库管理");
		
		if(className.equals("com.lshop.manage.lvPubProduct.action.LvPubProductAction")){
			//新增商品库
			if(methodName.equals("save")){
				lvOperationLogs.setOperationKey("新增商品库");
				lvOperationLogs.setOperationDetails("新增商品库，商品库名称："+request.getParameter("lvPubProduct.productName")+"，商务对接code："+request.getParameter("lvPubProduct.pcode"));
				
			//修改商品库
			}else if(methodName.equals("edit")){
				lvOperationLogs.setOperationKey("修改商品库");
				lvOperationLogs.setOperationDetails("修改商品库，商品库名称："+request.getParameter("lvPubProduct.productName")+"，商务对接code："+request.getParameter("lvPubProduct.pcode"));
				
			//删除商品库
			}else if(methodName.equals("del")){
				LvPubProduct lvPubProduct = (LvPubProduct)dao.load(LvPubProduct.class, Integer.parseInt(request.getParameter("lvPubProduct.id")));
				lvOperationLogs.setOperationKey("删除商品库");
				lvOperationLogs.setOperationDetails("删除商品库，商品库名称："+lvPubProduct.getProductName());
			}
			
		}else if(className.equals("com.lshop.manage.lvPubPackage.action.LvPubPackageAction")){
			//新增套餐
			if(methodName.equals("save")){
				lvOperationLogs.setOperationKey("新增套餐");
				lvOperationLogs.setOperationDetails("新增套餐");
				
			//修改套餐
			}else if(methodName.equals("edit")){
				lvOperationLogs.setOperationKey("修改套餐");
				lvOperationLogs.setOperationDetails("修改套餐");
				
			//删除套餐
			}else if(methodName.equals("del")){
				lvOperationLogs.setOperationKey("删除套餐");
				lvOperationLogs.setOperationDetails("删除套餐");
			}
		}
			
		return true;
	}

}
