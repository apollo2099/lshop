package com.lshop.ws.server.creatent.order.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvOrder.service.LvEmailService;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.lvOrder.service.OrderToCreatentService;
import com.lshop.manage.message.service.impl.OrderMsgServiceImpl;
import com.lshop.ws.server.creatent.order.bean.Result;
import com.lshop.ws.server.creatent.order.service.WSCTOrderService;

@Service("WSCTOrderService")
@WebService(endpointInterface = "com.lshop.ws.server.creatent.order.service.WSCTOrderService")
public class WSCTOrderServiceImpl implements WSCTOrderService {
	private static final Log logger = LogFactory.getLog(OrderMsgServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private LvOrderService orderService;
	@Resource
	private OrderToCreatentService orderToCreatentService;
	@Resource
	private LvOrderService lvOrderService;
	@Resource
	private LvEmailService emailService;
	
	
	/**
	 * 
	 * @Method: synLogisticsFromCreatent 
	 * @Description:  [同步发货订单物流信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-10-28 上午11:47:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-10-28 上午11:47:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Result synLogisticsFromCreatent(
			@WebParam(name = "orderNum") String orderNum,
			@WebParam(name = "expressCompany") String expressCompany,
			@WebParam(name = "expressName") String expressName,
			@WebParam(name = "expressNum") String expressNum,
			@WebParam(name = "status") Integer status,
			@WebParam(name = "shipTime") String shipTime) {
		Result result=new Result();
		 try {
			//根据订单号查询订单信息
			 LvOrder lvOrder=orderService.getOrder(orderNum);
			 if( ObjectUtils.isNotEmpty(orderNum)
			   &&ObjectUtils.isNotEmpty(expressName)
			   &&ObjectUtils.isNotEmpty(expressNum)
			   &&ObjectUtils.isNotEmpty(status)){
				 
					if (status==Constants.ORDER_STATUS_1&& lvOrder.getStatus() == Constants.ORDER_STATUS_0) {
						LvOrder tempOrder = new LvOrder();
						tempOrder.setOid(orderNum);
						if(ObjectUtils.isNotEmpty(expressCompany)){
							tempOrder.setExpressCompany(expressCompany);
						}else{
							tempOrder.setExpressCompany(expressName);
						}
						tempOrder.setExpressName(expressName);
						tempOrder.setExpressNum(expressNum);
						tempOrder.setShipTime(DateUtils.convertToDateTime(shipTime));
						tempOrder.setStatus(Short.parseShort(status.toString()));
						BaseDto dto = new BaseDto();
						dto.remove("lvOrder");
						dto.put("lvOrder", tempOrder);
						dto.put("uName", "CreatentSystem");
						dto.put("operate", "启创同步物流发货");
						// 调用同步物流实现
						int statuscode = (Integer) orderService.doOrder(dto);
						if (statuscode == 1) {
							result.setStatus(1);
							result.setMessage("发货同步成功");
							//发送发货成功邮件
							emailService.doNoticeFa(dto);
						}else{
							result.setStatus(0);
							result.setMessage("发货同步失败");
						}
					}else{
						result.setStatus(0);
						result.setMessage("发货同步失败，该订单已经发货请勿重复处理");
					}
			 }
		} catch (NumberFormatException e) {
			logger.error("发货同步异常，更新状态失败"+e.getMessage());
			result.setStatus(-1);
			result.setMessage("发货同步异常");
		} catch (IllegalArgumentException e) {
			logger.error("发货同步异常，更新状态失败"+e.getMessage());
			result.setStatus(-1);
			result.setMessage("发货同步异常");
		} catch (Exception e) {
			logger.error("发货同步异常，更新状态失败"+e.getMessage());
			result.setStatus(-1);
			result.setMessage("发货同步异常");
		}


		return result;
	}
	
	
	
	/**
	 * 同步已经审核的订单到启创接口定义(异常订单处理)
	 * @Method: synAuditOrderToCreatent 
	 * @Description:  
	 * @param startTime  审核开始时间（查询范围起始时间，格式：yyyy-mm-dd HH:mm:ss）
	 * @param endTime    审核结束时间（查询范围结束时间，格式：yyyy-mm-dd HH:mm:ss）
	 * @return Result
	 */
	@Override
	public Result synAuditOrderToCreatent(String startTime, String endTime) {
		if(logger.isInfoEnabled()){
			  logger.info("*****WSOrderServiceImpl.synAuditOrderToCreatent() method begin*****");
		}
		Result result=new Result();		
		//根据审核时间查询已经审核订单集合
		try {
			if(ObjectUtils.isNotEmpty(startTime)&&ObjectUtils.isNotEmpty(endTime)){
				String hql=" select DISTINCT ls.ord as ord from  LvOrderLogs ls,LvOrder o " +
						" where ls.ord=o.oid " +
						" and o.isServiceAudit=1 " +
						" and o.isFinanceAudit=1 " +
						" and o.isdelete=0 " +
						" and o.isSyncSas=0 " +
						" and ls.createTime>=:startTime and ls.createTime <=:endTime ";
				Map param=new HashMap();
				param.put("startTime", DateUtils.convertToDateTime(startTime));
				param.put("endTime", DateUtils.convertToDateTime(endTime));
				List listTmp = dao.getMapListByHql(hql, param).getList();
				if(ObjectUtils.isNotEmpty(listTmp)){
					for (int i = 0; i < listTmp.size(); i++) {
						Map map=(Map) listTmp.get(i);
						String orderNum=String.valueOf(map.get("ord"));
						logger.info("***** OrderToCreatentServiceImpl.synAuditOrderToCreatent() method begin*****");
						logger.info("***** OrderToCreatentServiceImpl.synAuditOrderToCreatent() B2C销售订单号orderNum="+orderNum);
						
						//根据订单好向启创发送订单信息的服务调用
						Boolean isFlag=orderToCreatentService.sendOrderMSGToCreatent(orderNum);
						if(isFlag){
							logger.info("***** OrderToCreatentServiceImpl.synAuditOrderToCreatent() 同步启创成功,B2C销售订单orderNum="+orderNum);
							//修改订单的同步状态
							lvOrderService.updateSyncSasFlag(orderNum, (short)1);
						}else{
							logger.info("***** OrderToCreatentServiceImpl.synAuditOrderToCreatent() 同步启创失败,B2C销售订单orderNum="+orderNum);
						}
					}
				}
			}
			result.setStatus(1);
			result.setMessage("同步启创完成");
		} catch (Exception e) {
			result.setStatus(0);
			result.setMessage("同步启创异常");
			logger.error("同步启创异常"+e.getMessage());
		}
		
		if(logger.isInfoEnabled()){
			logger.info("*****WSOrderServiceImpl.synAuditOrderToCreatent() method end*****");
		}
		return result;
	}
}
