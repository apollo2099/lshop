package com.lshop.web.vbpay.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.business.ws.boss.online.OnlineRechargeDto;
import com.gv.business.ws.boss.online.SOAPException_Exception;
import com.gv.business.ws.boss.online.WSOnlineRechargeService;
import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvRecharge;
import com.lshop.web.vbpay.service.OrderService;


@Service("VBOrderService")
public class OrderServiceImpl implements OrderService {
	private static final Log logger	= LogFactory.getLog(OrderServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private WSOnlineRechargeService onlineWs;//在线充值接口
	/**
	 * 修改订单状态
	 */
	@Override
	public  synchronized Boolean   updateOrderStatus(Dto dto) throws ServiceException {
		float amount=Float.parseFloat(dto.getAsString("totalPrice"));//金额
		Date paydate=(Date)dto.get("paydate");
		String rnum=dto.getAsString("rnum");//订单号
	    Map params =new HashMap();
	    params.put("rnum", rnum);
	    LvRecharge charge=(LvRecharge)lvlogicReadDao.findUnique("from LvRecharge where rnum=:rnum", params);//判断是否已支付
		if(charge!=null){
			   if(charge.getPayStatus()!=1){//判断是否已支付
				params.put("payStatus", 1);//支付成功
				params.put("payDate", paydate);//支付时间
				lvlogicWriteDao.update("update LvRecharge set payStatus=:payStatus,payDate=:payDate where rnum=:rnum",params);
			   }
			   if(charge.getStatus()!=1){
				try {
					int rechargeResource = dto.getAsInteger("rechargeResource");
					this.onlineCharge(charge, paydate, rechargeResource);
				} catch (SOAPException_Exception e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}
			   }
				return true;
		}
	    return false;
	}
	
	private void onlineCharge( LvRecharge charge, Date payD, int rechargeResource) throws SOAPException_Exception{//调用boss充值接口
		            String paydate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payD);
					//if(charge.getIsForOther()==0){//本号充值
						OnlineRechargeDto resDto=onlineWs.recharge(charge.getAccounts(), charge.getRnum(), charge.getVbNum()+"", 0, null, 1,rechargeResource,paydate);
						if(resDto.getStatus()==1&&charge.getStatus()==0){//充值成功
							 Map params =new HashMap();
							 params.put("rnum", charge.getRnum());
						     lvlogicWriteDao.update("update LvRecharge set status=1  where rnum=:rnum", params);
						}
					/*}else if(charge.getIsForOther()==1){//给他人充值
						OnlineRechargeDto resDto= onlineWs.recharge(charge.getCuserEmail(), charge.getRnum(), charge.getVbNum()+"", 1, charge.getAccounts(), 1,rechargeResource,paydate);	
						if(resDto.getStatus()==1&&charge.getStatus()==0){
						 Map params =new HashMap();
						 params.put("rnum", charge.getRnum());
					     lvlogicWriteDao.update("update LvRecharge set status=1  where rnum=:rnum", params);
						}
					}*/
	}
	@Override
	public LvRecharge getOrder(Dto dto) throws ServiceException {
		String rnum=dto.getAsString("oid");//订单号
		return (LvRecharge)lvlogicReadDao.findUniqueByProperty(LvRecharge.class, "rnum", rnum);
	}
}
