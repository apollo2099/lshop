package com.lshop.manage.common.systemLog.detail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOperationLogs;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.systemLog.BaseSystemLog;

/**
 * AOP操作日志管理_订单管理
 * @author zhengxue 2014-06-26
 *
 */
@Component("OrderManageLog")
public class OrderManageLog extends BaseSystemLog {

	@Resource
	private HibernateBaseDAO dao;
	
	@Override
	public boolean execute(String className, String methodName,
			HttpServletRequest request, LvOperationLogs lvOperationLogs)
			throws Exception {
		
		lvOperationLogs.setOperationModule("订单管理");
		
		if(className.equals("com.lshop.manage.lvOrder.action.LvOrderAction")){
			//修改
			if(methodName.equals("edit")){ 
				lvOperationLogs.setOperationKey("修改");
				lvOperationLogs.setOperationDetails("修改订单，订单号："+request.getParameter("lvOrder.oid"));
				
			//删除
			}else if(methodName.equals("del")){ 
				lvOperationLogs.setOperationKey("删除");
				lvOperationLogs.setOperationDetails("删除订单，订单号："+request.getParameter("lvOrder.oid"));
			
			//审核
			}else if(methodName.equals("audit")){ 
				String isServiceAudit_str = request.getParameter("lvOrder.isServiceAudit"); //客服审核状态
				String isFinanceAudit_str = request.getParameter("lvOrder.isFinanceAudit"); //财务审核状态
				String serviceAudit = "";
				String financeAudit = "";
				if(StringUtils.isNotEmpty(isServiceAudit_str)){
					Integer isServiceAudit = Integer.parseInt(isServiceAudit_str);
					if(isServiceAudit==-1){
						serviceAudit = "审核不通过";
					}else if(isServiceAudit==0){
						serviceAudit = "未审核";
					}else if(isServiceAudit==1){
						serviceAudit = "审核通过";
					}
				}
				if(StringUtils.isNotEmpty(isFinanceAudit_str)){
					Integer isFinanceAudit = Integer.parseInt(isFinanceAudit_str);
					if(isFinanceAudit==-1){
						financeAudit = "审核不通过";
					}else if(isFinanceAudit==0){
						financeAudit = "未审核";
					}else if(isFinanceAudit==1){
						financeAudit = "审核通过";
					}
				}
				lvOperationLogs.setOperationKey("审核");
				lvOperationLogs.setOperationDetails("客服审核状态："+serviceAudit+"，审核说明："+request.getParameter("lvOrder.serviceAuditContent")+"；财务审核状态："+financeAudit+"，审核说明："+request.getParameter("lvOrder.financeAuditContent"));
				
			//退货	
			}else if(methodName.equals("editBreakRemark")){ 
				lvOperationLogs.setOperationKey("退货");
				lvOperationLogs.setOperationDetails("订单："+request.getParameter("lvOrder.oid")+"退货，退货理由："+request.getParameter("lvOrder.breakRemark"));
			
			//确认收货（有问题）	
			}else if(methodName.equals("updateStatus")){ 
				lvOperationLogs.setOperationKey("确认收货");
				lvOperationLogs.setOperationDetails("后台确认收货成功");	

			//修改状态	
			}else if(methodName.equals("updateOrderStatus")){ 
				String orderStatus = "";
				String payStatus = "";
				
				String status_str = request.getParameter("lvOrder.status");
				if(StringUtils.isNotEmpty(status_str)){
					Integer status = Integer.parseInt(status_str);
					if(status==Constants.ORDER_STATUS_0){
						orderStatus="待发货";
					}
					if(status==Constants.ORDER_STATUS_1){
						orderStatus="已发货,未确认收货";			
					}
					if(status==Constants.ORDER_STATUS_2){
						orderStatus="已收货 ,待评价";
					}
					if(status==Constants.ORDER_STATUS_3){
						orderStatus="已退货";
					}
					if(status==Constants.ORDER_STATUS_4){
						orderStatus="已完成";
					}
				}
				
				String payStatus_str = request.getParameter("lvOrder.payStatus");
				if(StringUtils.isNotEmpty(payStatus_str)){
					Integer tmpPayStatus = Integer.parseInt(payStatus_str);
					if(tmpPayStatus==Constants.PAY_STATUS_NO){
						payStatus="未支付";
					}else if(tmpPayStatus==Constants.PAY_STATUS_OK){
						payStatus="已支付,已确认";
					}else if(tmpPayStatus==Constants.PAY_STATUS_OK_UNRECOGNIZED){
						payStatus="已支付,未确认";
					}
				}
				
				lvOperationLogs.setOperationKey("修改状态");
				lvOperationLogs.setOperationDetails("修改订单状态为："+orderStatus+"；支付状态为："+payStatus);

			//修改金额
			}else if(methodName.equals("updatePrice")){ 
				lvOperationLogs.setOperationKey("修改金额");
				lvOperationLogs.setOperationDetails("修改前订单金额为："+request.getAttribute("oldPrice")+"，修改后订单金额为："+request.getParameter("lvOrder.totalPrice"));
				
			//同步物流
			}else if(methodName.equals("synSendOrder")){ 
				lvOperationLogs.setOperationKey("同步物流");
				lvOperationLogs.setOperationDetails("同步物流成功");
			
			//发货	
			}else if(methodName.equals("sendOrder")){ 
				lvOperationLogs.setOperationKey("发货");
				lvOperationLogs.setOperationDetails("发货成功");	

			//转单	
			}else if(methodName.equals("changeOrder")){ 
				String oldStoreId = request.getParameter("lvOrder.storeId");
				String newStoreId = request.getParameter("targetFlag");
				LvStore oldStore = (LvStore)dao.findUniqueByProperty(LvStore.class, "storeFlag", oldStoreId);
				LvStore newStore = (LvStore)dao.findUniqueByProperty(LvStore.class, "storeFlag", newStoreId);
				lvOperationLogs.setOperationKey("转单");
				lvOperationLogs.setOperationDetails("店铺："+oldStore.getName()+"向店铺："+newStore.getName()+"转单");

			//商家自主下单
			}else if(methodName.equals("doShop")){ 
				lvOperationLogs.setOperationKey("商家自主下单");
				lvOperationLogs.setOperationDetails("商家自主下单");
			}
			
		}else if(className.equals("com.lshop.manage.lvProductComment.action.LvProductCommentAction")){
			
			//审核产品评论
			if(methodName.equals("audit")){ 
				lvOperationLogs.setOperationKey("审核产品评论");
				lvOperationLogs.setOperationDetails("审核产品评论");
				
			//添加系统评论
			}else if(methodName.equals("add")){ 
				String productCode = request.getParameter("lvProductComment.productCode");
				LvProduct product = (LvProduct)dao.findUniqueByProperty(LvProduct.class, "code", productCode);
				lvOperationLogs.setOperationKey("添加系统评论");
				lvOperationLogs.setOperationDetails("后台添加商品："+product.getProductName()+"的系统评论");
			}
		}
		
		
		return true;
	}

}
