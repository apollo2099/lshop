package com.lshop.web.pay.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.payutil.qh.config.QHPayConfig;
import com.lshop.common.payutil.qh.service.QHPayUtils;
import com.lshop.common.payutil.qh.vo.OrderInfoVo;
import com.lshop.common.payutil.qh.vo.OrderPayReturnVo;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.lshop.common.util.Sha256Util;
import com.lshop.web.userCenter.UserConstant;

@Controller("QHPayReturnAction")
@Scope("prototype")
public class QHPayReturnAction extends BaseAction{

	private static final long serialVersionUID = -2269414914034390741L;
	private static final Log logger = LogFactory.getLog(QHPayReturnAction.class);
	private String oid;
	
	/**
	 * 
	 * @Method: updatePaydata 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年5月25日 下午2:27:29]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年5月25日 下午2:27:29]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param request
	 * @param response
	 * @return boolean
	 */
    private boolean updatePaydata(OrderPayReturnVo orderPayVo) throws Exception{
    	if (logger.isInfoEnabled()) {
			logger.info("***** QHPayReturnAction.updatePaydata() method begin*****");
			logger.info("***** QHPayReturnAction.updatePaydata() request.getParameterMap()=****"+orderPayVo);
			logger.info("***** 订单号=****"+orderPayVo.getOrder_number()+":响应类型："+orderPayVo.getResponse_type());
		}
        
       /***********************************************************************************************
        * 返回参数收集
        ***********************************************************************************************/    	
		// 响应类型
		String response_type = orderPayVo.getResponse_type();
		// 返回商户号
		String account = orderPayVo.getAccount();
		// 返回终端号
		String terminal = orderPayVo.getTerminal();
		// 返回Oceanpayment 的支付唯一号
		String payment_id = orderPayVo.getPayment_id();
		// 返回网站订单号
		String order_number = orderPayVo.getOrder_number();
		// 返回交易币种
		String order_currency = orderPayVo.getOrder_currency();
		// 返回支付金额
		String order_amount = orderPayVo.getOrder_amount();
		// 返回支付状态
		int payment_status = 100;
		if (ObjectUtils.isNotEmpty(orderPayVo.getPayment_status())) {
			payment_status = Integer.parseInt(orderPayVo.getPayment_status());
		}

		// 返回支付详情
		String payment_details = orderPayVo.getPayment_details();
		// 返回交易安全签名
		String back_signValue = orderPayVo.getSignValue();
		// 返回备注
		String order_notes = orderPayVo.getOrder_notes();
		// 未通过的风控规则
		String payment_risk = orderPayVo.getPayment_risk();
		// 返回支付信用卡卡号
		String card_number = orderPayVo.getCard_number();
		// 返回交易类型
		String payment_authType = orderPayVo.getPayment_authType();
       
       //获取密钥
       String secureCode="";
       if(ObjectUtils.isNotEmpty(order_number)){
    	   dto.put("oid", order_number);
           LvOrder order=(LvOrder) doService("OrderService", "getOrder", dto);
           dto.put("storeFlag",order.getStoreId());
           dto.put("payValue", Constants.PAY_METHOD_QH);
           LvPaymentStyle paystyle=(LvPaymentStyle) doService("PayService", "getLvPaymentStyle", dto);
           JSONObject payData=null;
           if(paystyle.getParams()!=null&&!"".equals(paystyle.getParams().trim())){
               payData=JSONObject.fromObject(paystyle.getParams());
           }
           secureCode=(String)payData.get("secureCode"); //密钥  
       }
       
       
       
       
       //返回本地交易安全签名
	   String local_signValue = Sha256Util.encode(account + terminal
				+ order_number + order_currency + order_amount + order_notes
				+ card_number + payment_id + payment_authType + payment_status
				+ payment_details + payment_risk + secureCode);
       
	   System.out.println(local_signValue);
       //加密串校验
		if (local_signValue.equals(StringUtils.swapCase(back_signValue))) {
			if (payment_authType.equals("0")) {// 消费交易情况
				if (payment_status == 1) {// 支付成功
					// 修改订单状态
					updateOrderStatus(payment_id, order_number, order_amount,Constants.PAY_STATUS_OK);
					// 新增交易日志
					this.savePayLog(order_number,
							Float.parseFloat(order_amount.toString()),
							Constants.PAY_METHOD_QH, payment_id,
							order_currency, Constants.PAY_STATUS_OK,
							"消费交易-支付成功");// 添加支付日志信息
				} else if (payment_status == -1) {// 待处理
					this.savePayLog(order_number,
							Float.parseFloat(order_amount.toString()),
							Constants.PAY_METHOD_QH, payment_id,
							order_currency,
							Constants.PAY_STATUS_OK_UNRECOGNIZED,
							"消费交易-支付失败,待处理");// 添加支付日志信息
					return false;
				} else if (payment_status == 0) {// 支付失败
					this.savePayLog(order_number,
							Float.parseFloat(order_amount.toString()),
							Constants.PAY_METHOD_QH, payment_id,
							order_currency, Constants.PAY_STATUS_NO,
							"消费交易-支付失败:" + payment_details);// 添加支付日志信息
					return false;
				}
			} else if (payment_authType.equals("1")) {// 预授权交易情况
				if (payment_status == 1 || payment_status == -1) {// 支付成功
					if (payment_status == 1) {
						updateOrderStatus(payment_id, order_number,
								order_amount, Constants.PAY_STATUS_OK);
						this.savePayLog(order_number,
								Float.parseFloat(order_amount.toString()),
								Constants.PAY_METHOD_QH, payment_id,
								order_currency, Constants.PAY_STATUS_OK,
								"预授权交易-支付成功,交易状态payment_status="+ payment_status);// 添加支付日志信息
					} else if (payment_status == -1) {
						updateOrderStatus(payment_id, order_number,
								order_amount,
								Constants.PAY_STATUS_OK_UNRECOGNIZED);
						this.savePayLog(order_number,
								Float.parseFloat(order_amount.toString()),
								Constants.PAY_METHOD_QH, payment_id,
								order_currency,
								Constants.PAY_STATUS_OK_UNRECOGNIZED,
								"预授权交易-支付成功,交易状态payment_status="+ payment_status);// 添加支付日志信息
					}
				} else if (payment_status == 0) {// 支付失败
					this.savePayLog(order_number,
							Float.parseFloat(order_amount.toString()),
							Constants.PAY_METHOD_QH, payment_id,
							order_currency, Constants.PAY_STATUS_NO,"预授权交易-支付失败:" + payment_details);// 添加支付日志信息
					return false;
				}
			}
		} else {
			// 校验失败
			this.savePayLog(order_number,
					Float.parseFloat(order_amount.toString()),
					Constants.PAY_METHOD_QH, payment_id, order_currency,
					Constants.PAY_STATUS_NO, "安全认证失败");// 添加支付日志信息
			logger.info("verifyResult Faile");
			return false;
		}
	   
		if (logger.isInfoEnabled()) {
			logger.info("***** QHPayReturnAction.updatePaydata() method end*****");
		}
		return true;
    }

	/**
	 * 
	 * @Method: updateOrderStatus
	 * @Description: [修改订单状态和相关订单支付活动回调]
	 * @Author: [liaoxj]
	 * @CreateDate: [2015年6月16日 下午5:00:07]
	 * @UpdateUser: [liaoxj]
	 * @UpdateDate: [2015年6月16日 下午5:00:07]
	 * @UpdateRemark: [说明本次修改内容]
	 * @param payment_id
	 *            支付单号
	 * @param order_number
	 *            订单号
	 * @param order_amount
	 *            订单金额
	 * @param payStatus
	 *            支付状态
	 * @return void
	 */
	private void updateOrderStatus(String payment_id, String order_number,String order_amount,Short payStatus) {
		dto.put("oid", order_number);
		dto.put("totalPrice", Float.parseFloat(order_amount));
		dto.put("thirdpartyorder", payment_id);
		dto.put("payStatus", payStatus);// 已支付
		dto.put("status", Constants.ORDER_STATUS_0);// 待发货
		boolean result = (Boolean) doService("OrderService","updateOrderStatus", dto);
		if (true == result) {
			Dto dto2 = new BaseDto();
			try {
				String uid = null;
				uid = this.getCookieValue(UserConstant.USER_ID, true);
				if (StringUtils.isNotBlank(uid)) {
					dto2.put("uid", uid);
				}
			} catch (Exception e) {
			}
			dto2.put("oid", order_number);
			doService("OrderService", "orderPayCallBack", dto2);// 支付时更改剩余量或已购买量
		}
	}
    
    /**
     * 
     * @Method: ok 
     * @Description:  [一句话描述该类的功能]  
     * @Author:       [liaoxj]     
     * @CreateDate:   [2015年5月26日 上午10:28:58]   
     * @UpdateUser:   [liaoxj]     
     * @UpdateDate:   [2015年5月26日 上午10:28:58]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws Exception 
     * @return String
     */
	public String ok() throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("***** QHPayReturnAction.ok() method begin*****");
		}
		HttpServletRequest request = getRequest();
		
		//处理交易返回信息
		OrderPayReturnVo orderPayVo=initParam(request);   
		boolean resultFlag=this.updatePaydata(orderPayVo);
		
		oid= this.getRequest().getParameter("order_number");
		this.getRequest().setAttribute("oid",oid);
		if (logger.isInfoEnabled()) {
			logger.info("***** 支付订单号:"+oid);
			logger.info("***** 支付回调结果:"+resultFlag);
			logger.info("***** QHPayReturnAction.ok() method end*****");
		}
		 
		if(resultFlag==true){
			return "success";//支付成功，跳到成功页面
		}else{
			return "fail";
		}
	}
	
	/**
	 * 
	 * @Method: initParam 
	 * @Description:  [初始化同步请求参数]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年6月16日 下午4:37:19]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年6月16日 下午4:37:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param request
	 * @return OrderPayReturnVo
	 */
	private OrderPayReturnVo initParam(HttpServletRequest request) {
		OrderPayReturnVo orderPayVo=new OrderPayReturnVo();
	       //返回商户号
	       String account = request.getParameter("account");
		   //返回终端号
	       String terminal = request.getParameter("terminal");
	       //返回Oceanpayment 的支付唯一号
	       String payment_id = request.getParameter("payment_id");
		   //返回网站订单号
	       String order_number = request.getParameter("order_number");
	       //返回交易币种
	       String order_currency = request.getParameter("order_currency");
	       //返回支付金额
	       String order_amount = request.getParameter("order_amount");
	       //返回支付状态
	       String payment_status=request.getParameter("payment_status");
	       
	       //返回支付详情
	       String payment_details = request.getParameter("payment_details");
	       //返回交易安全签名
	       String back_signValue = request.getParameter("signValue");
	       //返回备注
	       String order_notes = request.getParameter("order_notes");
	       //未通过的风控规则
	       String payment_risk = request.getParameter("payment_risk");
	       //返回支付信用卡卡号
	       String card_number = request.getParameter("card_number");
	       //返回交易类型
	       String payment_authType = request.getParameter("payment_authType");

	       orderPayVo.setAccount(account);
	       orderPayVo.setTerminal(terminal);
	       orderPayVo.setPayment_id(payment_id);
	       orderPayVo.setOrder_number(order_number);
	       orderPayVo.setOrder_currency(order_currency);
	       orderPayVo.setOrder_amount(order_amount);
	       orderPayVo.setPayment_status(payment_status);
	       orderPayVo.setPayment_details(payment_details);
	       orderPayVo.setSignValue(back_signValue);
	       orderPayVo.setOrder_notes(order_notes);
	       orderPayVo.setPayment_risk(payment_risk);
	       orderPayVo.setCard_number(card_number);
	       orderPayVo.setPayment_authType(payment_authType);
	       
	       return orderPayVo;
	}

	/**
	 * 处理钱海支付异步方式
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String doQHNotify() throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("***** QHPayReturnAction.doQHNotify() method begin*****");
			logger.info("***** 支付回调服务器响应方法*****");
		}
		HttpServletRequest request = getRequest();
		
		//获取异步返回数据
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String sHtmlText = sb.toString();
		System.out.println("异步回调响应结果："+sHtmlText);
		//解析xml数据
		OrderPayReturnVo orderPayVo=QHPayUtils.xmlParseOrderPayReturn(sHtmlText);
		//修改订单状态
		boolean resultFlag=this.updatePaydata(orderPayVo);
		if (logger.isInfoEnabled()) {
			logger.info("***** QHPayReturnAction.doQHNotify()支付订单号:"+orderPayVo.getOrder_number());
			logger.info("***** QHPayReturnAction.doQHNotify()支付回调结果:"+resultFlag);
			logger.info("***** QHPayReturnAction.doQHNotify() method end*****");
		}
		return null;
	}

	
	
	/**
	 * 
	 * @Method: queryOrderStatus 
	 * @Description:  [查询订单状态接口]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年5月27日 下午4:00:09]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年5月27日 下午4:00:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws IOException 
	 * @return String
	 */
	public String queryOrderStatus() throws IOException{
		this.getResponse().setContentType("text/html;charset=utf-8");  
	    PrintWriter out=this.getResponse().getWriter();
	    HttpServletRequest request=this.getRequest();
	    //
	    String oid=request.getParameter("oid");
		dto.put("oid", oid);
		LvOrder order = (LvOrder) doService("OrderService", "getOrder", dto);
	    String paymeothd=Short.valueOf(order.getPaymethod()).toString();//获取支付方式
	    dto.put("storeFlag", order.getStoreId());
	    dto.put("payValue", paymeothd);
	    LvPaymentStyle paystyle=(LvPaymentStyle) doService("PayService", "getLvPaymentStyle", dto);
	    String url=QHPayConfig.QUERY_CHECKOUT_URL;
	    JSONObject payData=null;
	    if(paystyle.getParams()!=null&&!"".equals(paystyle.getParams().trim())){
	        payData=JSONObject.fromObject(paystyle.getParams());
	    }
	    
    	String account=(String)payData.get("account"); //账户
    	String terminal=(String)payData.get("terminal"); //终端号
    	String secureCode=(String) payData.get("secureCode");//密钥
    	
    	try {
    		OrderInfoVo orderInfoVo=QHPayUtils.queryOrderStatus(account, terminal, secureCode, oid);
    		out.println("订单号："+oid);
    		out.println("订单状态："+orderInfoVo.getPayment_results());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//构造请求参数

	    return null;
	}
	
    /**
     * 
     * @Method: savePayLog 
     * @Description:  [保存支付日志信息]  
     * @Author:       [liaoxj]     
     * @CreateDate:   [2015年5月23日 上午11:36:14]   
     * @UpdateUser:   [liaoxj]     
     * @UpdateDate:   [2015年5月23日 上午11:36:14]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param oid
     * @param amount
     * @param paymethod
     * @param bankorderid
     * @param currency
     * @param remark 
     * @return void
     */
	private void savePayLog(String oid,Float amount,Short paymethod,String bankorderid,String currency,Short status,String remark) {
		LvPayLogs log = new LvPayLogs();
		log.setOid(oid);
		log.setAmount(amount);
		log.setPaymethod(paymethod);
		log.setPaydate(new Date());
		log.setBankorderid(bankorderid);
		log.setCheckno("");
		log.setCurrency(currency);
		log.setStatus(status);
		log.setRemark(remark);
		log.setCreateTime(new Date());
		dto.setDefaultPo(log);
		doService("PayService", "addPayLog", dto);
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	
	
}
