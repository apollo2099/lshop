package com.lshop.web.vbpay.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.StringUtil;
import com.lshop.common.action.BaseAction;
import com.lshop.common.payutil.alipay.config.AlipayConfig;
import com.lshop.common.payutil.alipay.util.AlipayNotify;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvRecharge;
import com.lshop.common.pojo.logic.LvRechargePaylog;
import com.lshop.common.util.Constants;
import com.lshop.common.util.I18nCache;
import com.lshop.common.util.StoreHelp;

@Controller("VBAliPayReturnAction")
@Scope("prototype")
public class AliPayReturnAction extends BaseAction {//支付宝内卡
	@SuppressWarnings("unchecked")
	/**
	 * 验证请求参数 
	 */
    private  boolean  verifyResult(HttpServletRequest request)throws UnsupportedEncodingException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		String order_no = request.getParameter("out_trade_no");//获取订单号
		dto.put("oid", order_no);
		LvRecharge lvRecharge=(LvRecharge)doService("VBOrderService", "getOrder", dto);
		request.setAttribute("shopFlag", lvRecharge.getStoreFlag());
		dto.clear();
		dto.put("storeFlag", lvRecharge.getStoreFlag());
	    dto.put("payValue", Constants.PAY_METHOD_ALIPAY);
		LvPaymentStyle paystyle=(LvPaymentStyle) doService("VBPayService", "getLvPaymentStyle", dto);
		JSONObject payData=null;
	    if(paystyle.getParams()!=null&&!"".equals(paystyle.getParams().trim())){
	        payData=JSONObject.fromObject(paystyle.getParams());
	    }
		AlipayConfig alipayConfig=new AlipayConfig();//构造收款参数
    	alipayConfig.partner = (String)payData.get("partner");
    	alipayConfig.seller_email=(String)payData.get("seller_email");
    	alipayConfig.key=(String)payData.get("key");
    	alipayConfig.gateway=paystyle.getUrl();
		return AlipayNotify.verify(params,alipayConfig);//验证请求参数
	}
	/**
	 * 处理订单状态
	 * @param request
	 * @param trade_status//订单状态
	 * @return
	 */
	private boolean updatePaydata(HttpServletRequest request){
		String trade_no = request.getParameter("trade_no");				//支付宝交易号
		String order_no = request.getParameter("out_trade_no");	        //获取订单号
		String trade_status = request.getParameter("trade_status");		//交易状态
		String total_fee = request.getParameter("total_fee"); // 获取总金额
		if(total_fee==null||"".equals(total_fee.trim())){
			float price=Float.parseFloat(request.getParameter("price"));
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			total_fee= (price*quantity)+"";
		}
		String subject = request.getParameter("subject");//商品名称、订单名称
		String paydata = request.getParameter("gmt_payment");//支付时间
		Date paydate = new Date();// 支付时间
		if (!StringUtil.IsNullOrEmpty(paydata)) {
			try {
				paydate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(paydata);
			} catch (Exception e) {}
		}
		String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
		LvRechargePaylog log = new LvRechargePaylog();
		log.setRNum(order_no);
		log.setAmount(Float.parseFloat(total_fee));
		log.setPaymethod(Constants.PAY_METHOD_ALIPAY);
		if (paydata != null && !"".equals(paydata)) {
			try {
				log.setPaydate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(paydata));
			} catch (Exception e) {
				log.setPaydate(new Date());
			}
		} else {
			log.setPaydate(new Date());
		}
		log.setBankorderid(trade_no);
		log.setCheckno(buyer_email);
		log.setRemark(trade_status);
		log.setCurrency("CNY");
		dto.setDefaultPo(log);
		dto.put("totalPrice", Float.parseFloat(total_fee));
		dto.put("oid", order_no);
		dto.put("thirdpartyorder", trade_no);
		dto.put("rechargeResource", StoreHelp.getRechargeResource(this.getFlag()));
		
		if (trade_status.equals("WAIT_BUYER_PAY")) {// 等待买家付款
			log.setStatus(Constants.PAY_STATUS_NO);
			doService("VBPayService", "addPayLog", dto);
		} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")||trade_status.equals("TRADE_FINISHED")){// 买家已付款，等待卖家发货
			dto.put("totalPrice", total_fee);
			dto.put("paydate", paydate);
			dto.put("rnum", order_no);
			doService("VBOrderService", "updateOrderStatus", dto);
			log.setStatus(Constants.ORDER_STATUS_1);
			dto.setDefaultPo(log);
			doService("VBPayService", "addPayLog", dto);
		} else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {// 卖家已发货，等待买家收货
			dto.put("totalPrice", total_fee);
			dto.put("paydate", paydate);
			dto.put("rnum", order_no);
			doService("VBOrderService", "updateOrderStatus", dto);
			log.setStatus(Constants.ORDER_STATUS_2);
			dto.setDefaultPo(log);
			doService("VBPayService", "addPayLog", dto);
		} else if (trade_status.equals("TRADE_FINISHED")) {// 买家已收货，交易完成
			dto.put("totalPrice", total_fee);
			dto.put("paydate", paydate);
			dto.put("rnum", order_no);
			doService("VBOrderService", "updateOrderStatus", dto);
			log.setStatus(Constants.ORDER_STATUS_4);
			dto.setDefaultPo(log);
			doService("VBPayService", "addPayLog", dto);
		} else {
			logger.info("验证成功,状态无效");
		}
		return true;
	}
	/**
	  * 支付宝同步通知
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	@SuppressWarnings("unchecked")
	public String ok() throws UnsupportedEncodingException {
		HttpServletRequest request=getRequest();
		String order_no = request.getParameter("out_trade_no");	        //获取订单号
		request.setAttribute("oid", order_no);
		
		dto.put("oid", order_no);
		LvRecharge lvRecharge = (LvRecharge) doService("VBOrderService", "getOrder", dto);
		request.setAttribute("charge", lvRecharge);
		request.setAttribute("serviceTel", I18nCache.getResourceValue("common.service.tel", this.getFlag()));
		
		//计算得出通知验证结果
		boolean verify_result = this.verifyResult(request);
		if(verify_result){//验证成功
			this.updatePaydata(request);//处理订单状态
			return "success";//支付成功，跳到成功页面
		}
	   return "fail"; 
	}
	/**
	  * 支付宝异步通知
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	@SuppressWarnings("unchecked")
	public String doAliPayNotify() throws UnsupportedEncodingException {
		HttpServletRequest request = getRequest();
		if (this.verifyResult(request)) {// 验证成功
			this.updatePaydata(request);//处理订单状态
		} else {// 验证失败
			logger.info("验证失败");
		}
		return null;
	}

}
