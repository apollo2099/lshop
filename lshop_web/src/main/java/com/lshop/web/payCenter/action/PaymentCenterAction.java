package com.lshop.web.payCenter.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.payCenter.PayConstants;
import com.lshop.common.payCenter.PayHelper;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StringUtil;
import com.lshop.web.userCenter.UserConstant;


/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_web] 
 * @Package:      [com.lshop.web.payCenter.action.PaymentCenterAction.java]  
 * @ClassName:    [PaymentCenterAction]   
 * @Description:  [对接支付中心业务实现]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2014-3-26 上午11:55:37]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2014-3-26 上午11:55:37]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.0]
 */
@Controller("PaymentCenterAction")
@Scope("prototype")
public class PaymentCenterAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(PaymentCenterAction.class);

	/**
	 * /web/payCenter!toPayCenter.action
	 * @Method: toPayCenter 
	 * @Description:  [提交支付数据到内部支付接口]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-25 下午6:01:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-25 下午6:01:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws Exception 
	 */
	public String toPayCenter() throws Exception{
		if (logger.isInfoEnabled()){
			logger.info("***** PaymentCenterAction.toPayCenter() method begin*****");
		}
		//获取内置对象数据
	    this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = this.getResponse().getWriter();
		HttpServletRequest request = this.getRequest();
		
		String domurl = "http://" + request.getHeader("Host");
		LvOrder lvOrder = (LvOrder) request.getAttribute("lvOrder");
		LvOrderAddress lvOrderAddress = (LvOrderAddress) request.getAttribute("lvOrderAdress");
		
		//根据订单信息查询支付方式详情
		dto.put("payValue", lvOrder.getPaymethod());
		dto.put("storeFlag", lvOrder.getStoreId());
		LvPaymentStyle paystyle=(LvPaymentStyle) this.doService("PayService", "getLvPaymentStyle", dto);		
		JSONObject payData=null;
		String url=null;//请求支付系统的url
		String merCode=null;// 商户号
		String pwdKey=null;//商户密钥 
		if(ObjectUtils.isNotEmpty(paystyle.getPaymentSystemParams())){
			payData=JSONObject.fromObject(paystyle.getPaymentSystemParams());
			url=(String) payData.get("paySysUrl");
			merCode=(String) payData.get("paySysMerno");
			pwdKey=(String) payData.get("paySysKey");
		}
		 
		
		
		if (lvOrder != null) {
			Short paymethod = lvOrder.getPaymethod();// 获取支付方式

			String orderNo =StringUtil.isNotEmpty(lvOrder.getOid());// 订单号
			String amount = StringUtil.isNotEmpty(lvOrder.getTotalPrice());// 金额
			String currency =StringUtil.isNotEmpty(lvOrder.getCurrency());//币种
			String notifyUrl =domurl+"/web/payCenter!asyncReturn.action";// 异步返回地址/web/payCenter!asyncReturn.action
			String errorUrl = domurl+"/web/payCenter!errorReturn.action";// 错误地址/web/payCenter!errorReturn.action
			String returnUrl =domurl+"/web/payCenter!syncReturn.action";// 同步返回地址/web/payCenter!syncReturn.action
			String cancelUrl= "";
			if(Short.valueOf(Constants.PAY_METHOD_PAYDOLLAR).toString().equals(paymethod.toString())){
				 returnUrl =domurl+"/web/payCenter!successReturn.action";// 同步返回地址/web/payCenter!successReturn.action
				 cancelUrl = domurl+"/web/payCenter!cancelReturn.action";//取消交易返回地址/web/payCenter!cancelReturn.action
			}
			if(Short.valueOf(Constants.PAY_METHOD_ALIPAY_MOBILE).toString().equals(paymethod.toString())){
				 cancelUrl = domurl+"/index.html";//支付成功返回商品链接
			}
			
			
			String isAlipayMod = "";// 支付模式:默认普通模式，1=>嵌入模式
			// 支付方式转换
			String payPlatform = switchPaymethod(paymethod);// 支付平台
			// md5生成签名
			String str = merCode + orderNo + amount + currency + returnUrl + pwdKey;// 商户号+订单号+金额+币种+同步地址+密钥key														
			String sign = PayHelper.sign(str);// 生成签名
			
			
			
			//设置公有参数
			String extraParam="";
			if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA).toString().equals(paymethod.toString())
					||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_JCB).toString().equals(paymethod.toString())
					||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER).toString().equals(paymethod.toString())
					||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_VISA).toString().equals(paymethod.toString())
					||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_JCB).toString().equals(paymethod.toString())
					||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_MASTER).toString().equals(paymethod.toString())
					||Short.valueOf(Constants.PAY_METHOD_ALIPAY).toString().equals(paymethod.toString())){
				extraParam = initExtraParam(lvOrderAddress, lvOrder);//
			}
			
			
			//设置商品名称和描述
			String subject = "Tvpad";//商品名称
			String body = "Tvpad在线支付";// 商品描述
			String mallFlag= Constants.STORE_TO_MALL_SYSTEM.get(lvOrder.getStoreId());
			if(mallFlag.equals("banana")){
				subject = "Banana TV";
				body="Banana TV在线支付";
			}
			
			
			//设置安全控件字段
			String jsReturn="";
			if(Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_VISA).toString().equals(paymethod.toString())
					||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_JCB).toString().equals(paymethod.toString())
					||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_MASTER).toString().equals(paymethod.toString())){
				jsReturn=this.getCookieValue("jsReturnCookie", true);
				isAlipayMod="1";
			}
			
			url=url+"/pay/pay.action";
			//拼装参数
			Map<String, String> param = new HashMap<String, String>();
			param.put("merCode", merCode);
			param.put("orderNo", orderNo);
			param.put("amount", amount);
			param.put("currency", currency);
			param.put("sign", sign);
			param.put("returnUrl", returnUrl);
			param.put("notifyUrl", notifyUrl);
			param.put("errorUrl", errorUrl);
			param.put("cancelUrl", cancelUrl);
			param.put("payPlatform", payPlatform);
			param.put("extraParam", extraParam);
			param.put("subject", subject);
			param.put("body", body);
			param.put("isAlipayMod", isAlipayMod);
			param.put("jsReturn", jsReturn);
			String html = PayHelper.buildForm(param, url, "post", "iframepage");
			out.println(html);
			
			
		    /**********from表自动提交表单网关*******************/
			if(Short.valueOf(Constants.PAY_METHOD_95EPAY).toString().equals(paymethod.toString())
					   ||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_VISA).toString().equals(paymethod.toString())
					   ||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_JCB).toString().equals(paymethod.toString())
					   ||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_MASTER).toString().equals(paymethod.toString())
					   ||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA).toString().equals(paymethod.toString())
					   ||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER).toString().equals(paymethod.toString())
					   ||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_JCB).toString().equals(paymethod.toString())){
			    out.print("<iframe id=\"iframepage\" name=\"iframepage\" width=\"1000\" height=\"300\" scrolling=\"auto\" frameborder=\"0\" src=\"\"></iframe>");
			    out.println("<script type=\"text/javascript\">");
			    out.println("document.paysubmit.submit();");
			    out.println("</script>");
		    }else{
			    out.println("<script type=\"text/javascript\">");
			    out.println("document.paysubmit.target=\"_self\";");
			    out.println("document.paysubmit.submit();");
			    out.println("</script>");
			}
		}

		if (logger.isInfoEnabled()){
			logger.info("***** PaymentCenterAction.toPayCenter() method end*****");
		}
		return null;
	}
	
	/**
	 * 
	 * @Method: errorReturn 
	 * @Description:  [错误参数返回列表处理]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-25 下午3:45:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-25 下午3:45:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String errorReturn(){
		if (logger.isInfoEnabled()){
			logger.info("***** PaymentCenterAction.errorReturn() method begin*****");
		}
		HttpServletRequest request = this.getRequest();
		String orderNo=request.getParameter("orderNo"); //商户订单号
		String tradeNo=request.getParameter("tradeNo");//支付系统流水号
		String errorInfo=request.getParameter("errorInfo");//错误参数列表
		request.setAttribute("oid", orderNo);
		//解析错误信息，保存支付日志
		dto.put("oid", orderNo);
		//根据订单号查询订单信息
		LvOrder order=(LvOrder) doService("OrderService", "getOrder", dto);
		//拼接支付日志信息
		LvPayLogs log = new LvPayLogs();
		log.setOid(orderNo);
		log.setAmount(order.getTotalPrice());
		log.setPaymethod(order.getPaymethod());
		log.setPaydate(new Date());
		log.setBankorderid(tradeNo);
		log.setCheckno(order.getUserEmail());
		//遍历查找错误原因信息
		for(Map.Entry<String, String> entry:PayConstants.ERROR_MESSAGE.entrySet()){   
		    if(errorInfo.equals(entry.getKey())){
		    	log.setRemark(entry.getValue());
		    	break;
		    }
	    }
		
		//判断是否找到了错误返回原因
		if(ObjectUtils.isEmpty(log.getRemark())){
			log.setRemark("其他原因："+errorInfo);
		}
		log.setCurrency(order.getCurrency());
		log.setStatus(Constants.ORDER_STATUS_0);
		dto.setDefaultPo(log);
		doService("PayService", "addPayLog", dto);
		this.getRequest().setAttribute("oid", orderNo);
		if (logger.isInfoEnabled()){
			logger.info("***** PaymentCenterAction.errorReturn() method end*****");
		}
		return "fail";
	}
	
	
	/**
	 * 
	 * @Method: cancelReturn 
	 * @Description:  [取消交易返回地址]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-28 下午3:21:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-28 下午3:21:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String cancelReturn(){
		if (logger.isInfoEnabled()) {
			logger.info("***** PaymentCenterAction.cancelReturn() method begin*****");
		}
		HttpServletRequest request = this.getRequest();
		String orderNo=request.getParameter("orderNo"); //商户订单号
		request.setAttribute("oid", orderNo);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** PaymentCenterAction.cancelReturn() method end*****");
		}
		return "cancel";
	} 
	
	/**
	 * 
	 * @Method: successReturn 
	 * @Description:  [支付成功调用页面跳转]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-4-2 上午11:46:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-4-2 上午11:46:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String successReturn(){
		if (logger.isInfoEnabled()) {
			logger.info("***** PaymentCenterAction.successReturn() method begin*****");
		}
		String orderNo = this.getRequest().getParameter("orderNo");
		this.getRequest().setAttribute("oid", orderNo);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** PaymentCenterAction.successReturn() method begin*****");
		}
		return "success";
	}
	
	
	/**
	 * 
	 * @Method: callbackReturn 
	 * @Description:  [同步回调参数返回列表处理]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-25 下午3:45:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-25 下午3:45:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String syncReturn(){
		if (logger.isInfoEnabled()) {
			logger.info("***** PaymentCenterAction.syncReturn() method begin*****");
		}
		HttpServletRequest request = this.getRequest();
		String merCode = request.getParameter("merCode");// 商户号
		String tradeNo = request.getParameter("tradeNo");// 支付系统交易流水号
		String orderNo = request.getParameter("orderNo");// 商户订单号
		String currency = request.getParameter("currency");// 交易币种
		String amount = request.getParameter("amount");// 交易金额
		String orderStatus = request.getParameter("orderStatus");// 订单状态0=>支付失败,，1表示已支付
		String orderInfo = request.getParameter("orderInfo");// 交易结果信息
		String authkey = request.getParameter("authkey");// 比较密钥key认证,//商户号+订单号+商户密钥 生成密钥key;
		String tradeTime = request.getParameter("tradeTime");// 交易时间
		

		// 根据订单号查询订单信息
		dto.put("oid", orderNo);
		request.setAttribute("oid", orderNo);
		LvOrder order = (LvOrder) doService("OrderService", "getOrder", dto);
		
		String pwdKey=null;
		dto.put("payValue", order.getPaymethod());
		dto.put("storeFlag", order.getStoreId());
		LvPaymentStyle lvPaymentStyle=(com.lshop.common.pojo.logic.LvPaymentStyle) this.doService("PayService", "getLvPaymentStyle", dto);
		if(ObjectUtils.isNotEmpty(lvPaymentStyle.getPaymentSystemParams())){
			JSONObject payData=null;
			if(ObjectUtils.isNotEmpty(lvPaymentStyle.getPaymentSystemParams())){
				payData=JSONObject.fromObject(lvPaymentStyle.getPaymentSystemParams());
				pwdKey=(String) payData.get("paySysKey");
			}
		}
		
		String authstr = PayHelper.sign(merCode + tradeNo + orderNo + amount+ currency + pwdKey.trim());// 商户号+订单号+金额+币种+商户密钥 生成密钥key;

		// 拼接支付日志信息
		LvPayLogs log = new LvPayLogs();
		log.setOid(orderNo);
		log.setAmount(Float.parseFloat(amount));
		log.setPaymethod(Constants.PAY_METHOD_ALIPAY);
		if (tradeTime != null && !"".equals(tradeTime)) {
			try {
				log.setPaydate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tradeTime));
			} catch (Exception e) {
				log.setPaydate(new Date());
			}
		} else {
			log.setPaydate(new Date());
		}
		log.setBankorderid(tradeNo);
		log.setCheckno(order.getUserEmail());
		 
		 
		 
		  if("1".equals(orderStatus)&&authstr.equals(authkey)){//支付成功做业务逻辑处理
			    //注意  修改订单成功状态时， 先判断支付系统返回的交易金额 大于或等于商户订单金额，	  
			    if(order.getTotalPrice()<=Float.parseFloat(amount)){//金额验证
			    	dto.put("totalPrice", Float.parseFloat(amount));
					dto.put("oid", orderNo);
					dto.put("thirdpartyorder", tradeNo);
					dto.put("payStatus", Constants.PAY_STATUS_OK);//已支付
					dto.put("status", Constants.ORDER_STATUS_0);//未发货
					//修改订单状态，支付状态并且发送邮件提醒
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
						dto2.put("oid", orderNo);
						doService("OrderService","orderPayCallBack",dto2);//支付时更改剩余量或已购买量
					}

					log.setRemark("支付成功");
					log.setCurrency(currency);
					log.setStatus(Constants.ORDER_STATUS_1);
					dto.setDefaultPo(log);
					doService("PayService", "addPayLog", dto);
					return "success";	
			    }
		  }else if("-2".equals(orderStatus)){//支付失败业务逻辑处理
			log.setRemark("支付失败");
			log.setCurrency(currency);
			log.setStatus(Constants.ORDER_STATUS_0);
			dto.setDefaultPo(log);
			doService("PayService", "addPayLog", dto);
			return "fail";
		  }else if("2".equals(orderStatus)){//支付取消业务逻辑处理
			log.setRemark("交易取消");
			log.setCurrency(currency);
			log.setStatus(Constants.ORDER_STATUS_0);
			dto.setDefaultPo(log);
			doService("PayService", "addPayLog", dto);
			return "cancel";
		  }
		
		
		if (logger.isInfoEnabled()) {
			logger.info("***** PaymentCenterAction.syncReturn() method end*****");
		}
		return "fail";
	} 
	
	
	/**
	 * 
	 * @Method: asyncCallbackReturn 
	 * @Description:  [异步回调参数返回列表处理]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-25 下午3:58:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-25 下午3:58:48]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 * @throws IOException 
	 */
	public String asyncReturn() throws IOException{
		if (logger.isInfoEnabled()) {
			logger.info("***** PaymentCenterAction.asyncReturn() method begin*****");
		}		
		HttpServletRequest request = this.getRequest();
		PrintWriter out=this.getResponse().getWriter();
		String merCode = request.getParameter("merCode");// 商户号
		String tradeNo = request.getParameter("tradeNo");// 支付系统交易流水号
		String orderNo = request.getParameter("orderNo");// 商户订单号
		String currency = request.getParameter("currency");// 交易币种
		String amount = request.getParameter("amount");// 交易金额
		String orderStatus = request.getParameter("orderStatus");// 订单状态0=>支付失败,，1表示已支付
		String orderInfo = request.getParameter("orderInfo");// 交易结果信息
		String authkey = request.getParameter("authkey");// 比较密钥key认证,//商户号+订单号+商户密钥 生成密钥key;
		String tradeTime = request.getParameter("tradeTime");// 交易时间		
		// 根据订单号查询订单信息
		dto.put("oid", orderNo);
		LvOrder order = (LvOrder) doService("OrderService", "getOrder", dto);
		
		String pwdKey=null;
		dto.put("payValue", order.getPaymethod());
		dto.put("storeFlag", order.getStoreId());
		LvPaymentStyle lvPaymentStyle=(com.lshop.common.pojo.logic.LvPaymentStyle) this.doService("PayService", "getLvPaymentStyle", dto);
		if(ObjectUtils.isNotEmpty(lvPaymentStyle.getPaymentSystemParams())){
			JSONObject payData=null;
			if(ObjectUtils.isNotEmpty(lvPaymentStyle.getPaymentSystemParams())){
				payData=JSONObject.fromObject(lvPaymentStyle.getPaymentSystemParams());
				pwdKey=(String) payData.get("paySysKey");
			}
		}
		
		String authstr = PayHelper.sign(merCode + tradeNo + orderNo + amount+ currency + pwdKey.trim());// 商户号+订单号+金额+币种+商户密钥 生成密钥key;
		// 拼接支付日志信息
		LvPayLogs log = new LvPayLogs();
		log.setOid(orderNo);
		log.setAmount(Float.parseFloat(amount));
		log.setPaymethod(Constants.PAY_METHOD_ALIPAY);
		if (tradeTime != null && !"".equals(tradeTime)) {
			try {
				log.setPaydate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(tradeTime));
			} catch (Exception e) {
				log.setPaydate(new Date());
			}
		} else {
			log.setPaydate(new Date());
		}
		log.setBankorderid(tradeNo);
		log.setCheckno(order.getUserEmail());
		 
		 
		 
		if ("1".equals(orderStatus) && authstr.equals(authkey)) {// 支付成功做业务逻辑处理
			// 注意 修改订单成功状态时， 先判断支付系统返回的交易金额 大于或等于商户订单金额，
			if (order.getTotalPrice() <= Float.parseFloat(amount)) {// 金额验证
				dto.put("totalPrice", Float.parseFloat(amount));
				dto.put("oid", orderNo);
				dto.put("thirdpartyorder", tradeNo);
				dto.put("payStatus", Constants.PAY_STATUS_OK);// 已支付
				dto.put("status", Constants.ORDER_STATUS_0);// 未发货
				// 修改订单状态，支付状态并且发送邮件提醒
				boolean result = (Boolean) doService("OrderService","updateOrderStatus", dto);
				if (true == result) {
					Dto dto2 = new BaseDto();
					try {
						String uid = null;
						uid=this.getCookieValue(UserConstant.USER_ID, true);
						if (StringUtils.isNotBlank(uid)) {
							dto2.put("uid", uid);
						}
					} catch (Exception e) {}
					dto2.put("oid", orderNo);
					doService("OrderService","orderPayCallBack",dto2);//支付时更改剩余量或已购买量
				}

				log.setRemark("支付成功");
				log.setCurrency(currency);
				log.setStatus(Constants.ORDER_STATUS_1);
				dto.setDefaultPo(log);
				doService("PayService", "addPayLog", dto);

				
				//对于异步地址支付成功输出打印一个success字符串
				out.print("success");
			}
		} else if ("-2".equals(orderStatus)) {// 支付失败业务逻辑处理
			log.setRemark("支付失败");
			log.setCurrency(currency);
			log.setStatus(Constants.ORDER_STATUS_0);
			dto.setDefaultPo(log);
			doService("PayService", "addPayLog", dto);
		} else if ("2".equals(orderStatus)) {// 支付取消业务逻辑处理
			log.setRemark("交易取消");
			log.setCurrency(currency);
			log.setStatus(Constants.ORDER_STATUS_0);
			dto.setDefaultPo(log);
			doService("PayService", "addPayLog", dto);
		}
		
		
		if (logger.isInfoEnabled()) {
			logger.info("***** PaymentCenterAction.syncReturn() method end*****");
		}
		
	    return null;	
	}
	
	
	/**
	 * 
	 * @Method: switchPaymethod 
	 * @Description:  [支付方式转换]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-26 下午6:24:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-26 下午6:24:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param paymethod
	 * @return String
	 */
	private String switchPaymethod(Short paymethod){
		if(Constants.PAY_METHOD_ALIPAY==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_ALIPAY;
		}else if(Constants.PAY_METHOD_GB==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_GLOBEBILL;
		}else if(Constants.PAY_METHOD_PAYPALOUT_VISA==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_ALIPAYOUT_VISA;
		}else if(Constants.PAY_METHOD_PAYPALOUT_MASTER==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_ALIPAYOUT_MASTER;
		}else if(Constants.PAY_METHOD_PAYPALOUT_JCB==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_ALIPAYOUT_JCB;
		}else if(Constants.PAY_METHOD_PAYDOLLAR==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_PAYDOLLAR;
		}else if(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_MOTO_VISA;
		}else if(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_MOTO_MASTER;
		}else if(Constants.PAY_METHOD_ALIPAY_MOBILE==paymethod){
			return PayConstants.THIRD_PARTY_PAYMENT_ALIPAY_MOBILE;
		}
		return null;
	}
	
	
	/**
	 * 
	 * @Method: initExtraParam 
	 * @Description:  [构造额外公有参数]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-27 上午10:58:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-27 上午10:58:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderAddress
	 * @return String
	 */
	private String initExtraParam(LvOrderAddress orderAddress,LvOrder lvOrder){
		//查询国家和用户信息
	    com.gv.core.hibernate3.HibernateBaseDAO lvlogicReadDao=(com.gv.core.hibernate3.HibernateBaseDAO)com.gv.core.proxy.ServiceConstants.beanFactory.getBean("lvlogicReadDao");
		LvArea lvCountry=(LvArea)lvlogicReadDao.load(LvArea.class,orderAddress.getContryId());
		com.gv.core.hibernate3.HibernateBaseDAO lvuserReadDao=(com.gv.core.hibernate3.HibernateBaseDAO)com.gv.core.proxy.ServiceConstants.beanFactory.getBean("lvuserReadDao");
		LvAccount  lvAccount=(LvAccount)lvuserReadDao.load(LvAccount.class,lvOrder.getMemid());
		
		//获取参数
		String country=StringUtil.isNotEmpty(lvCountry.getCode());//
		String state="";
		if (orderAddress.getProvinceId() != null) {
			LvArea lvP = (LvArea) lvlogicReadDao.load(LvArea.class,orderAddress.getProvinceId());// 获取洲
			if(ObjectUtils.isNotEmpty(lvP.getCode())){
				state=StringUtil.isNotEmpty(lvP.getCode());//
			}else{
				state=StringUtil.isNotEmpty(orderAddress.getProvinceName());//
			}
		} else {
		    state=StringUtil.isNotEmpty(orderAddress.getProvinceName());//
		}
	    String city=StringUtil.isNotEmpty(orderAddress.getCityName());
	    String address=StringUtil.isNotEmpty(orderAddress.getAdress());
	    String firestname=StringUtil.isNotEmpty(orderAddress.getRelName());
	    String lastname=StringUtil.isNotEmpty(orderAddress.getRelName());
	    String phonenumber=StringUtil.isNotEmpty(orderAddress.getMobile());
	    String tel=StringUtil.isNotEmpty(orderAddress.getTel());
	    phonenumber=!"".equals(phonenumber.trim())?phonenumber:tel;
	    String postcode=StringUtil.isNotEmpty(orderAddress.getPostCode());
	    String email=StringUtil.isNotEmpty(lvAccount.getEmail());
	    
	    //拼接对象，转换成JSON
	    Map<String, String> param = new HashMap<String, String>();
		param.put("country", country);
		param.put("state", state);
		param.put("city", city);
		param.put("address", address);
		param.put("firestname", firestname);
		param.put("lastname", lastname);
		param.put("phonenumber", phonenumber);
		param.put("postcode", postcode);
		param.put("email", email);

		String jsonTemp=JSONObject.fromObject(param).toString();
		logger.info("***** PaymentCenterAction.initExtraParam()===>jsonTemp:*****"+jsonTemp);
		return jsonTemp;
	}
	
}
