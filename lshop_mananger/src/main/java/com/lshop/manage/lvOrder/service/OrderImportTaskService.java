package com.lshop.manage.lvOrder.service;

import java.util.List;

import com.gv.core.exception.ServiceException;
import com.lshop.manage.lvOrder.vo.OrderImport;

public interface OrderImportTaskService {
	
	/**
	 * 
	 * @Method: saveOrderImport 
	 * @Description:  [批量导入保存订单程序入口]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-20 下午5:14:15]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-20 下午5:14:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderImport 订单数据组装
	 * @return void
	 */
	public String saveOrderImport(OrderImport orderImport)throws Exception;
	/**
	 * 
	 * @Method: buildOrderData 
	 * @Description:  [组装excel解析的订单数据]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-20 下午4:25:58]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-20 下午4:25:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param importList 表格数据
	 * @return List<OrderImport> 组装订单
	 */
	public List<OrderImport> buildOrderData(List<String []> importList)throws ServiceException;
}
