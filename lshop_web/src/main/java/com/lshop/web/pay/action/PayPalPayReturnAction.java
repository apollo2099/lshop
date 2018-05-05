package com.lshop.web.pay.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.lshop.web.userCenter.UserConstant;

@Controller("PayPalPayReturnAction")
@Scope("prototype")
public class PayPalPayReturnAction extends BaseAction {

	private static final long serialVersionUID = -2269414914034390741L;

	private static final Log logger = LogFactory.getLog(PayPalPayReturnAction.class);

    private boolean updatePaydata(HttpServletRequest request) throws Exception{
    	boolean resultFlag = false;
    	String custom=request.getParameter("custom").trim();
    	String oid = custom.substring(0, custom.indexOf("|"));
    	dto.put("oid", oid);
    	LvOrder lvOrder=(LvOrder)doService("OrderService", "getOrder", dto);
    	request.setAttribute("shopFlag", lvOrder.getStoreId());
		dto.clear();
		dto.put("storeFlag", lvOrder.getStoreId());
        dto.put("payValue", Constants.PAY_METHOD_PAYPAL);
 		LvPaymentStyle paystyle=(LvPaymentStyle) doService("PayService", "getLvPaymentStyle", dto);
 		JSONObject payData=null;
 	    if(paystyle.getParams()!=null&&!"".equals(paystyle.getParams().trim())){
 	        payData=JSONObject.fromObject(paystyle.getParams());
 	    }
		String authToken =(String)payData.get("key");// 认证码
		String databusiness =(String)payData.get("business");//paypal帐号
		// 获取 PayPal 交易流水号
		String txToken = request.getParameter("tx");
		if(txToken!=null&&!"".equals(txToken)){
			String str = "cmd=_notify-synch&tx=" + txToken + "&at=" + authToken;
			URL u = new URL(paystyle.getUrl());
			URLConnection uc = u.openConnection();
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			PrintWriter pw = new PrintWriter(uc.getOutputStream());
			pw.println(str);
			pw.close();
			// 接受 PayPal 对 IPN 回发的回复信息
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String res = in.readLine();
			if (res != null && "SUCCESS".equals(res.trim())) {
				Map<String, String> param = new HashMap<String, String>();
				while (in.ready()) {
					String temp = in.readLine();
					int index = temp.indexOf("=");
					if (index != -1) {
						param.put(temp.substring(0, index).trim(), URLDecoder.decode(temp.substring(index + 1),"UTF-8"));
					}
				}
				resultFlag = this.doFormSuccess(param, request, databusiness);
			}
			in.close();
		}else {
			Map<String, String> param = new HashMap<String, String>();
			try {
				param.put("payment_status", request.getParameter("payment_status").trim());
				param.put("custom", request.getParameter("custom").trim());
				param.put("mc_currency", request.getParameter("custom").trim());
				param.put("txn_id", request.getParameter("txn_id").trim());
				param.put("mc_gross", request.getParameter("mc_gross").trim());
				param.put("payment_date", request.getParameter("payment_date"));
				param.put("payer_email", request.getParameter("payer_email"));
				param.put("business", request.getParameter("business"));
				resultFlag = this.doFormSuccess(param, request,databusiness);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
		}
    	return resultFlag;
    }
	@SuppressWarnings("unchecked")
	public boolean doFormSuccess(Map<String, String> param, HttpServletRequest request,String databusiness) {
		String paymentStatus = param.get("payment_status").trim();
		String custom = param.get("custom").trim();
		String oid = custom.substring(0, custom.indexOf("|"));
		String useremail = custom.substring(custom.indexOf("|") + 1);
		String currency = param.get("mc_currency").trim();
		String txnId = param.get("txn_id").trim();
		String amount = param.get("mc_gross").trim();
		String paydata = param.get("payment_date");
		String payer_email = param.get("payer_email");
		String business = param.get("business");
		// 设置返回的参数
		request.setAttribute("oid", oid);
		request.setAttribute("amount", amount);
		request.setAttribute("useremail", useremail);
		request.setAttribute("currency", "USD");
		if (databusiness.equals(business)) {
			if (paymentStatus.equals("Completed") || paymentStatus.equals("Processed")) {
				dto.put("oid", oid);
				dto.put("totalPrice", Float.parseFloat(amount));
				dto.put("thirdpartyorder", txnId);
				dto.put("payStatus", Constants.PAY_STATUS_OK);// 已支付
				dto.put("status", Constants.ORDER_STATUS_0);// 待发货
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
					dto2.put("oid", oid);
					doService("OrderService","orderPayCallBack",dto2);//支付时更改剩余量或已购买量
				}
				LvPayLogs log = new LvPayLogs();
				log.setOid(oid);
				log.setAmount(Float.parseFloat(amount));
				log.setPaymethod(Constants.PAY_METHOD_PAYPAL);
				if (paydata != null && !"".equals(paydata)) {
					try {
						log.setPaydate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(paydata));
					} catch (Exception e) {
						log.setPaydate(new Date());
					}
				} else {
					log.setPaydate(new Date());
				}
				log.setBankorderid(txnId);
				log.setCheckno(payer_email);
				log.setRemark(paymentStatus);
				log.setCurrency("USD");
				dto.setDefaultPo(log);
				log.setStatus(Constants.ORDER_STATUS_1);
				doService("PayService", "addPayLog", dto);
				return true;
			}
		}
		return false;
	}

	public String ok() throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.ok() method begin*****");
		}
		HttpServletRequest request = getRequest();
		boolean resultFlag=this.updatePaydata(request);
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.ok() method end*****");
		}
		return resultFlag==true?"success":"fail";

	}
	/**
	 * 处理PayPal异步数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String doPayPalNotify() throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.doPayPalNotify() method begin*****");
		}
		HttpServletRequest request = getRequest();
		boolean resultFlag=this.updatePaydata(request);
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.doPayPalNotify() method end*****");
		}
		return null;
	}
	public String cancal() throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.cancal() method begin*****");
		}
	return "fail";
	}
}
