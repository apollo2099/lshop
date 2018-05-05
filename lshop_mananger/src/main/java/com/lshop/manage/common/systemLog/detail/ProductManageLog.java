package com.lshop.manage.common.systemLog.detail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOperationLogs;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.manage.common.systemLog.BaseSystemLog;

/**
 * AOP操作日志管理_商品管理
 * @author zhengxue 2014-06-26
 *
 */
@Component("ProductManageLog")
public class ProductManageLog extends BaseSystemLog {

	@Resource
	private HibernateBaseDAO dao;

	@Override
	public boolean execute(String className, String methodName,
			HttpServletRequest request, LvOperationLogs lvOperationLogs)
			throws Exception {
		
		lvOperationLogs.setOperationModule("商品管理");
		
		if(className.equals("com.lshop.manage.lvProduct.action.LvProductAction")){
			//发布商品
			if(methodName.equals("add")){
				lvOperationLogs.setOperationKey("发布商品");
				lvOperationLogs.setOperationDetails("发布商品："+request.getParameter("lvProduct.productName")+"成功");
				
			//修改商品
			}else if(methodName.equals("edit")){
				lvOperationLogs.setOperationKey("修改商品");
				lvOperationLogs.setOperationDetails("修改商品："+request.getParameter("lvProduct.productName"));
				
			//上下架
			}else if(methodName.equals("updateSupport")){
				String productCode = request.getParameter("lvProduct.code");
				LvProduct lvProduct = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", productCode);
				String isSupport_str = request.getParameter("lvProduct.isSupport");
				Integer isSupport = null;
				if(StringUtils.isNotEmpty(isSupport_str)){
					isSupport=Integer.parseInt(isSupport_str);
					if(isSupport==0){
						lvOperationLogs.setOperationKey("下架");
						lvOperationLogs.setOperationDetails("商品："+lvProduct.getProductName()+"下架");
					}else if(isSupport==1){
						lvOperationLogs.setOperationKey("上架");
						lvOperationLogs.setOperationDetails("商品："+lvProduct.getProductName()+"上架");
					}
				}
				
				
			//推荐组合
			}else if(methodName.equals("commend")){
				String productCode = request.getParameter("lvProduct.code");
				LvProduct lvProduct = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", productCode);
				lvOperationLogs.setOperationKey("推荐组合");
				lvOperationLogs.setOperationDetails("设置商品："+lvProduct.getProductName()+"的推荐组合商品");
				
			}
			
		}else if(className.equals("com.lshop.manage.lvProductImage.action.LvProductImageAction")){
			//添加效果图
			if(methodName.equals("add")){
				LvProduct lvProduct = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", request.getParameter("lvProductImage.productCode"));
				lvOperationLogs.setOperationKey("添加效果图");
				lvOperationLogs.setOperationDetails("商品："+lvProduct.getProductName()+"添加效果图："+request.getAttribute("imgPath"));
				
			//修改效果图
			}else if(methodName.equals("edit")){
				LvProduct lvProduct = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", request.getParameter("lvProductImage.productCode"));
				lvOperationLogs.setOperationKey("修改效果图");
				lvOperationLogs.setOperationDetails("商品："+lvProduct.getProductName()+"修改原效果图："+request.getParameter("lvProductImage.productImage"));
				
			//删除效果图（有问题）
			}else if(methodName.equals("del")){
				String productCode = request.getAttribute("productCode").toString();
				LvProduct lvProduct = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", productCode);
				lvOperationLogs.setOperationKey("删除效果图");
				lvOperationLogs.setOperationDetails("商品："+lvProduct.getProductName()+"删除效果图："+request.getAttribute("productImage"));
			}
			
		}else if(className.equals("com.lshop.manage.lvProductProperty.action.LvProductPropertyAction")){
			//添加扩展页
			if(methodName.equals("add")){
				LvProduct lvProduct = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", request.getParameter("lvProductProperty.productCode"));
				lvOperationLogs.setOperationKey("添加扩展页");
				lvOperationLogs.setOperationDetails("商品："+lvProduct.getProductName()+"添加扩展页："+request.getParameter("lvProductProperty.title"));
				
			//修改扩展页
			}else if(methodName.equals("edit")){
				LvProduct lvProduct = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", request.getParameter("lvProductProperty.productCode"));
				lvOperationLogs.setOperationKey("修改扩展页");
				lvOperationLogs.setOperationDetails("商品："+lvProduct.getProductName()+"修改扩展页为："+request.getParameter("lvProductProperty.title"));
				
			//删除扩展页（有问题）
			}else if(methodName.equals("del")){
				String productCode = request.getAttribute("productCode").toString();
				LvProduct lvProduct = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", productCode);
				lvOperationLogs.setOperationKey("删除扩展页");
				lvOperationLogs.setOperationDetails("商品："+lvProduct.getProductName()+"删除扩展页："+request.getAttribute("propertyTitle"));
			}
		}
			
		return true;
	}

}
