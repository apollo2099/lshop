package com.lshop.web.pay.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.action.BaseAction;
import com.lshop.common.payutil.alipay.config.AlipayConfig;
import com.lshop.common.payutil.alipay.util.AlipayNotify;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.util.Constants;
import com.lshop.web.userCenter.UserConstant;

@Controller("AliPayReturnAction")
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
		LvOrder lvOrder=(LvOrder)doService("OrderService", "getOrder", dto);
		request.setAttribute("shopFlag", lvOrder.getStoreId());
		dto.clear();
		dto.put("storeFlag", lvOrder.getStoreId());
	    dto.put("payValue", Constants.PAY_METHOD_ALIPAY);
		LvPaymentStyle paystyle=(LvPaymentStyle) doService("PayService", "getLvPaymentStyle", dto);
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
		String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
		LvPayLogs log = new LvPayLogs();
		log.setOid(order_no);
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
		log.setCurrency("RMB");
		dto.setDefaultPo(log);
		dto.put("totalPrice", Float.parseFloat(total_fee));
		dto.put("oid", order_no);
		dto.put("thirdpartyorder", trade_no);
		if (trade_status.equals("WAIT_BUYER_PAY")) {// 等待买家付款
			log.setStatus(Constants.PAY_STATUS_NO);
			doService("PayService", "addPayLog", dto);
		} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")||trade_status.equals("TRADE_FINISHED")){// 买家已付款，等待卖家发货
			dto.put("payStatus", Constants.PAY_STATUS_OK);// 已支付
			dto.put("status", Constants.ORDER_STATUS_0);//
			boolean result=(Boolean)doService("OrderService", "updateOrderStatus", dto);
			if(true==result){
				Dto dto2 = new BaseDto();
				try {
					String uid = null;
					uid=this.getCookieValue(UserConstant.USER_ID, true);
					if (StringUtils.isNotBlank(uid)) {
						dto2.put("uid", uid);
					}
				} catch (Exception e) {}
				dto2.put("oid", order_no);
				doService("OrderService","orderPayCallBack",dto2);//支付时更改剩余量或已购买量
			}
			log.setStatus(Constants.ORDER_STATUS_1);
			doService("PayService", "addPayLog", dto);
		} else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {// 卖家已发货，等待买家收货
			dto.put("payStatus", Constants.PAY_STATUS_OK);// 已支付
			dto.put("status", Constants.ORDER_STATUS_1);// 已发货
			doService("OrderService", "updateOrderStatus", dto);
			log.setStatus(Constants.ORDER_STATUS_1);
			doService("PayService", "addPayLog", dto);
		} else if (trade_status.equals("TRADE_FINISHED")) {// 买家已收货，交易完成
			dto.put("payStatus", Constants.PAY_STATUS_OK);// 已支付
			dto.put("status", Constants.ORDER_STATUS_2);// 已确认收货
			doService("OrderService", "updateOrderStatus", dto);
			log.setStatus(Constants.ORDER_STATUS_2);
			doService("PayService", "addPayLog", dto);
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
