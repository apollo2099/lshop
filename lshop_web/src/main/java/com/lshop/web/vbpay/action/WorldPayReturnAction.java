package com.lshop.web.vbpay.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.StringUtil;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvRechargePaylog;
import com.lshop.common.pojo.logic.LvRecharge;
import com.lshop.common.util.I18nCache;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.common.util.StoreHelp;

@Controller("VBWorldPayReturnAction")
@Scope("prototype")
public class WorldPayReturnAction extends BaseAction {
	private static final Log logger = LogFactory
			.getLog(WorldPayReturnAction.class);

	private String requestParamStr() {
		Map requestParams = this.getRequest().getParameterMap();
		StringBuffer buf = new StringBuffer("");
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			buf.append("&"+ name+"=" + valueStr);
		}
		return buf.toString();
	}

	private boolean verifyResult(HttpServletRequest request) {// 认证
		String callbackPW = request.getParameter("callbackPW");// 返回认证key
		String authPwd = PropertiesHelper.getProperty(
				"paymethod.worldpay.callbackpwd").trim();
		if (authPwd != null && authPwd.equals(callbackPW)) {
			return true;
		}
		return false;
	}
	
	private boolean updatePaydata(HttpServletRequest request) {
		String amount = request.getParameter("amount");// 金额
		String currency = request.getParameter("currency");// 币种
		String transId=request.getParameter("transId");//交易id
		String transStatus = request.getParameter("transStatus");// 交易状态 Y
		// 交易成功，C取消交易,重复支付
		String transTime = request.getParameter("transTime");// 交易时间
		String order_no = request.getParameter("cartId");// 订单号
		String instId = request.getParameter("instId");// 安装id
		Date paydate = new Date();// 支付时间
		if (!StringUtil.IsNullOrEmpty(transTime)) {
			try {
				paydate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(transTime);
			} catch (Exception e) {}
		}
		dto.put("oid", order_no);
		LvRecharge charge = (LvRecharge) doService("VBOrderService", "getOrder", dto);
		request.setAttribute("charge", charge);
		
		LvRechargePaylog log = new LvRechargePaylog();
		boolean flag = false;
		if (verifyResult(request)) {// 认证
			if ("Y".equals(transStatus)) {// 交易成功
				dto.put("totalPrice", amount);
				dto.put("paydate", paydate);
				dto.put("rnum", order_no);
				dto.put("rechargeResource", StoreHelp.getRechargeResource(this.getFlag()));
				doService("VBOrderService", "updateOrderStatus", dto);
				flag = true;
				log.setRemark(transStatus + "：" + this.requestParamStr());
			}
		} else {// 失败
			log.setRemark("认证失败!" + this.requestParamStr());
		}
		dto.clear();
		/****** 保存支付日志 *****/
		log.setAmount(Float.parseFloat(amount));
		log.setBankorderid(order_no);
		log.setPaydate(paydate);
		log.setPaymethod(charge.getRtype().shortValue());
		log.setRNum(order_no);
		log.setCurrency(currency);
		log.setCheckno(transId);
		log.setStatus(flag == true ? (short) 1 : (short) 0);// 1表示成功，0表示失败
		dto.put("model", log);
		doService("VBPayService", "addPayLog", dto);
		return flag;
	}

	public String doWorldPayNotify() throws UnsupportedEncodingException {
		HttpServletRequest request = this.getRequest();
		this.updatePaydata(request);// 处理支付订单测试
		return null;
	}
}
