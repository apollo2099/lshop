package com.lshop.manage.lvOrder.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvOrder.vo.OrderImport;
import com.lshop.ws.web.bz.order.ExpressResponse;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.LvOrderService.java]  
 * @ClassName:    [LvOrderService]   
 * @Description:  [订单信息管理--接口定义(贯穿整个订单流程)]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午05:37:05]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午05:37:05]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvOrderService {
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询订单信息列表定义]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:38:02]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:38:02]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Pagination
	 */
	public Pagination getList(Dto dto) throws ServiceException;
	
	
	/**
	 * 
	 * @Method: findUnfilledOrder 
	 * @Description:  [查询所有未发货订单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-2-3 上午10:29:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-2-3 上午10:29:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ServiceException 
	 * @return List<LvOrder>
	 */
	public List<LvOrder> findUnfilledOrder()throws ServiceException;
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询订单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:37:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:37:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvOrder
	 */
	public LvOrder get(Dto dto) throws ServiceException ;
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改订单状态和订单总金额]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:39:09]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:39:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void update(Dto dto)throws ServiceException ;
	/**
	 * 
	 * @Method: save 
	 * @Description:  [商家自主下单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:39:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:39:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void save(Dto dto)throws ServiceException, ParseException ;
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [软删除订单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:40:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:40:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void delete(Dto dto) throws ServiceException,Exception;
	/**
	 * 
	 * @Method: exportOrder 
	 * @Description:  [导出选择 的订单信息导成excel文件]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:40:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:40:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return List
	 */
	public List exportOrder(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: updateAudit 
	 * @Description:  [订单审核(客户审核/财务审核)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:40:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:40:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws Exception 
	 * @return void
	 */
	public Integer updateAudit(Dto dto)throws Exception;
	public Boolean sendOrderMSGToWMS(Dto dto) throws Exception;
	/**
	 * 
	 * @Method: updateSendRemark 
	 * @Description:  [发货备注]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:41:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:41:48]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Integer updateSendRemark(Dto dto)throws Exception;
	/**
	 * 
	 * @Method: updateBreakRemark 
	 * @Description:  [退货备注]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:42:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:42:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Integer updateBreakRemark(Dto dto)throws Exception;
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改订单状态]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:42:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:42:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Integer updateStatus(Dto dto)throws Exception;
	/**
	 * 
	 * @Method: updatePrice 
	 * @Description:  [修改订单金额]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-15 上午11:00:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-15 上午11:00:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Integer updatePrice(Dto dto)throws Exception;
	public Integer updateSendOrder(Dto dto)throws Exception;
	/**
	 * 
	 * @Method: updateSyncSasFlag 
	 * @Description:  [修改订单同步启创标志状态]  
	 * @param orderId 订单号
	 * @param isSyncFlag 启创同步标志（1是，0否）
	 * @return void
	 */
	public void updateSyncSasFlag(String orderId,Short isSyncFlag);
	public void updateSyncSasFlag(Dto dto);
	/**
	 * 
	 * @Method: receiving 
	 * @Description:  [恢复软删除的订单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:42:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:42:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public Boolean receiving(Dto dto)throws Exception;
	public Integer doOrder(Dto dto) throws Exception;
	
	
	/**
	 * 
	 * @Method: saveOrderLogs 
	 * @Description:  [保存订单日志公共方法]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-4-21 上午11:31:13]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-4-21 上午11:31:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param userName 操作者
	 * @param orderId 订单号
	 * @param operate 操作内容
	 * @param storeId 店铺标志
	 * @return void
	 */	
	public void saveOrderLogs(String userName,String orderId,String operate,String storeId);
	/**
	 * 
	 * @Method: saveSysComment 
	 * @Description:  [添加系统自动评论]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:43:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:43:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws Exception 
	 * @return void
	 */
	public void saveSysComment(Dto dto) throws Exception;
	/**
	 * 
	 * @Method: getOrder 
	 * @Description:  [根据订单号查询订单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午05:44:13]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午05:44:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws Exception 
	 * @return LvOrder
	 */
	public LvOrder getOrder(Dto dto) throws Exception;
	public LvOrder getOrder(String oid)throws Exception;
	public LvOrder getOrderNoDelete(Dto dto) throws Exception;
	public LvOrder getOrderByThridNum(String thirdOrderNum) throws ServiceException;
	
	/*************************************************************
	 * 订单数据报表统计(总下单数量，已付款订单数，未付款订单数，已完成订单数，已删除订单数，已退货订单数，销售额，导出excel文件)
	 *                 
	 *************************************************************/
	public Integer countAll(Dto dto) throws Exception;
	public Integer countPay(Dto dto) throws Exception;
	public Integer countUnPay(Dto dto) throws Exception;
	public Integer countFinish(Dto dto) throws Exception;
	public Integer countDelete(Dto dto) throws Exception;
	public Integer countBack(Dto dto) throws Exception;
	public Float totalPrice(Dto dto)throws Exception;
	public Float totalPriceRmb(Dto dto)throws Exception;
	public Float totalPriceUsd(Dto dto)throws Exception;
	public List exportStateOrder(Dto dto)throws Exception;
	
	/*************************************************************
	 * 产品数据报表统计(所在店铺，销售量，销售额，均价)
	 * 
	 *************************************************************/
	public Pagination stateProduct(Dto dto)throws Exception;
	public List exportStateProduct(Dto dto)throws Exception;
	
	/*************************************************************
	 * 用户订单统计(用户email，昵称，未支付订单，已经支付订单，重复购买次数，订单总台数，总订单数，销售额，创建时间)
	 *************************************************************/
	public Pagination stateUser(Dto dto)throws Exception ;
	public List exportUserOrder(Dto dto)throws Exception ;
	
	/*************************************************************
	 * 产品数据报表统计(所在店铺，销售量，销售额，均价 
	 *************************************************************/
	public Pagination shopTypeState(Dto dto)throws Exception ;
	public List exportShopTypeState(Dto dto)throws Exception ;
	//优惠码验证
	public Dto vaildCoupon(Dto dto) throws ParseException ;
	public void doShopNotice(Dto dto)throws ServiceException;
	//订单转单
	public void changeOrder(Dto dto)throws ServiceException;
    //发货订单物流信息同步
	public ExpressResponse synSendOrder(Dto dto)throws Exception;
    /**
     * 
     * @Method: orderToCreatentTask 
     * @Description:  [支付成功發送訂單信息到启创代理商后台(线程入口程序)]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2014-12-25 下午6:13:57]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2014-12-25 下午6:13:57]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param orderNum 订单号
     * @throws ServiceException 
     * @return void
     */
	public void orderToCreatentTask(String orderNum) throws ServiceException;
	
	/**
	 * 
	 * @Method: createOrderNum 
	 * @Description:  [创建订单号规则]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-24 上午10:11:22]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-24 上午10:11:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param storeId 店铺标志
	 * @param markFlag 订单标志
	 * @return String 组装订单
	 */
	public String createOrderNum(String storeId,String markFlag)throws Exception  ;
}
