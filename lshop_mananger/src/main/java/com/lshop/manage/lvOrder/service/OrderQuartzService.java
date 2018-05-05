package com.lshop.manage.lvOrder.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.OrderQuartzService.java]  
 * @ClassName:    [LvOrderQuartzService]   
 * @Description:  [订单状态需要增加两个时间戳——已发货，未确认收货状态订单如果没有进行任何操作，第15个自然日订单状态自动变为已完成；
 *                 已收货，待评价状态订单如果没有进行任何操作，48小时后订单变为已完成(默认好评)]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2013-2-26 下午02:27:10]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2013-2-26 下午02:27:10]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.0]
 */
public interface OrderQuartzService extends Runnable{
	public void init()throws ServiceException;
	public void start(Dto dto)throws ServiceException;
	public void updateOrderIsReceipt();
	public void updateOrderShipped();
	public void synOrderLogisticsByBZ();
}
