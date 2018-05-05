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
import com.gv.core.exception.ActionException;
import com.lshop.common.action.BaseAction;
import com.lshop.common.payutil.alipayout.config.AlipayOutConfig;
import com.lshop.common.payutil.alipayout.util.AlipayNotify;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.lshop.web.userCenter.UserConstant;
@Controller("AliPayOutCardReturnAction")
@Scope("prototype")
public class AliPayOutCardReturnAction extends BaseAction {//支付宝外卡
	@SuppressWarnings("unchecked")
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
		dto.put("payValue", lvOrder.getPaymethod());//获取当前支付方式值
		LvPaymentStyle paystyle=(LvPaymentStyle) doService("PayService", "getLvPaymentStyle", dto);
	    JSONObject payData=null;
	    if(paystyle.getParams()!=null&&!"".equals(paystyle.getParams().trim())){
	        payData=JSONObject.fromObject(paystyle.getParams());
	    }
		AlipayOutConfig alipayOutConfig=new AlipayOutConfig();//构造收款参数
    	alipayOutConfig.partner = (String)payData.get("partner");
    	alipayOutConfig.seller_logon_id=(String)payData.get("seller_email");
    	alipayOutConfig.key=(String)payData.get("key");
    	alipayOutConfig.gateway=paystyle.getUrl();//默认网关
		return AlipayNotify.verify(params,alipayOutConfig);//验证请求参数
	}
	
	private boolean updatePaydata(HttpServletRequest request){
		String trade_no = request.getParameter("trade_no");				//支付宝交易号
		String order_no = request.getParameter("out_trade_no");	        //获取订单号
		String total_fee = request.getParameter("forex_total_fee"); // 获取总金额	
		String subject = request.getParameter("subject");//商品名称、订单名称
		String useremail = request.getParameter("seller_email");
		String paydata = request.getParameter("gmt_payment");
		String currency=request.getParameter("currency");
		if(currency==null||currency.equals("")||"CNY".equals(currency)){
			   total_fee=request.getParameter("total_fee");//人民币支付
		}
		String trade_status = request.getParameter("trade_status");		//交易状态
		String body = "";
		if(request.getParameter("body") != null){
			body = request.getParameter("body");//商品描述、订单备注、描述
		}
		String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
		String receive_name = "";//收货人姓名
		if(request.getParameter("receive_name") != null){
			receive_name =request.getParameter("receive_name");
		}
		String receive_address = "";//收货人地址
		if(request.getParameter("receive_address") != null){
			receive_address =request.getParameter("receive_address");
		}
		LvPayLogs log=new LvPayLogs();
		log.setOid(order_no);
		log.setAmount(Float.parseFloat(total_fee));
		log.setPaymethod(Constants.PAY_METHOD_ALIPAY);
		if (paydata!=null&&!"".equals(paydata)) {
			try {
				log.setPaydate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(paydata));
			} catch (Exception e) {
				log.setPaydate(new Date());
			}
		}else {
			log.setPaydate(new Date());
		}
		log.setBankorderid(trade_no);
		log.setCheckno(buyer_email);
		log.setRemark(trade_status);
		log.setCurrency(currency);
		if(trade_status.equals("TRADE_SUCCESS")||trade_status.equals("TRADE_FINISHED")){
			dto.put("totalPrice", Float.parseFloat(total_fee));
			dto.put("oid", order_no);
			dto.put("thirdpartyorder", trade_no);
			dto.put("payStatus", Constants.PAY_STATUS_OK);//已支付
			dto.put("status", Constants.ORDER_STATUS_0);//未发货
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
			dto.setDefaultPo(log);
			doService("PayService", "addPayLog", dto);
	}	
		return true;
	
	}
    //支付宝外卡支付
	@SuppressWarnings("unchecked")
	public String ok() throws ActionException, UnsupportedEncodingException {
		HttpServletRequest request=getRequest();
		String currency=request.getParameter("currency");
		String trade_no = request.getParameter("trade_no");				//支付宝交易号
		String order_no = request.getParameter("out_trade_no");	        //获取订单号
		String total_fee = request.getParameter("forex_total_fee"); // 获取总金额	
		request.setAttribute("oid", order_no);
		request.setAttribute("amount", total_fee);
		request.setAttribute("currency", currency);
		//计算得出通知验证结果
		boolean verify_result = verifyResult(request);
		if(verify_result){//验证成功
			this.updatePaydata(request);
			return "success";
		}else{
			return "fail";
		}
	}
	public String doAliPayOutNotify()throws ActionException, UnsupportedEncodingException {
		HttpServletRequest request=getRequest();
		//计算得出通知验证结果
		boolean verify_result = verifyResult(request);
		if(verify_result){//验证成功
			this.updatePaydata(request);
		}
		return null;
	}
}
