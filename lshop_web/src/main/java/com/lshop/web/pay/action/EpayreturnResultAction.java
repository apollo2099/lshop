package com.lshop.web.pay.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

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
import com.lshop.common.payutil.epay.MD5;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.lshop.web.userCenter.UserConstant;

@Controller("EpayreturnResultAction")
@Scope("prototype")
public class EpayreturnResultAction extends BaseAction {

	private static final long serialVersionUID = -2269414914034390741L;

	private static final Log logger = LogFactory.getLog(EpayreturnResultAction.class);
   
	private boolean updatePaydata(HttpServletRequest request) throws UnsupportedEncodingException{
		String tradeNo = request.getParameter("RorderNo");// 95epay流水号，是唯一的，用来区分交易,请妥善保存
		String billNo = request.getParameter("BillNo"); // 商户网站订单号
		String currency = request.getParameter("Currency");// 交易币种
		String amount = request.getParameter("Amount"); // 以以上币种计价的金额
		String succeed = request.getParameter("Succeed");// 支付状态:该值说明见于word说明文档[商户根据该值来修改数据库中相应订单的状态]
		String result = request.getParameter("Result"); // 支付结果信息：展示给顾客看的信息
		String remark = request.getParameter("Remark");// 商户备注
		String mD5info = request.getParameter("MD5info");
		String serverName =  request.getServerName();
		dto.put("oid", billNo);
		LvOrder lvOrder=(LvOrder)doService("OrderService", "getOrder", dto);
		request.setAttribute("shopFlag", lvOrder.getStoreId());
		dto.clear();
		dto.put("storeFlag", lvOrder.getStoreId());
		dto.put("payValue", Constants.PAY_METHOD_95EPAY);
		LvPaymentStyle paystyle=(LvPaymentStyle) doService("PayService", "getLvPaymentStyle", dto);
	    JSONObject payData=null;
	    if(paystyle.getParams()!=null&&!"".equals(paystyle.getParams().trim())){
	        payData=JSONObject.fromObject(paystyle.getParams());
	    }
		String merNo = (String)payData.get("merno");
		String md5key = (String)payData.get("key");
		request.setAttribute("oid", billNo);
		String md5src = billNo + currency + amount + succeed + md5key;
		logger.info("到商户页面上的MD5SRC=");
		logger.info(md5src);
		String md5str;
		md5str = new MD5().getMD5ofStr(md5src);
		if (mD5info.equals(md5str)) {// 签名匹配
			LvPayLogs log = new LvPayLogs();
			log.setOid(billNo);
			log.setAmount(Float.parseFloat(amount));
			log.setPaymethod(Constants.PAY_METHOD_95EPAY);
			log.setPaydate(new Date());
			log.setBankorderid(tradeNo);
			log.setCheckno(merNo);
			log.setCurrency(currency);
			if (succeed.equals("88")) {// 支付已成功
				// 请修改订单状态,或者其他操作
				// 在界面显示，付款结果提示信息 Result
				dto.put("totalPrice", Float.parseFloat(amount));
				dto.put("oid", billNo);
				dto.put("thirdpartyorder", tradeNo);
				dto.put("payStatus", Constants.PAY_STATUS_OK);// 已支付
				dto.put("status", Constants.ORDER_STATUS_0);// 未发货
				boolean res=(Boolean)doService("OrderService", "updateOrderStatus", dto);
				if(true==res){
					Dto dto2 = new BaseDto();
					try {
						String uid = null;
						uid=this.getCookieValue(UserConstant.USER_ID, true);
						if (StringUtils.isNotBlank(uid)) {
							dto2.put("uid", uid);
						}
					} catch (Exception e) {}
					dto2.put("oid", billNo);
					doService("OrderService","orderPayCallBack",dto2);//支付时更改剩余量或已购买量
				}
				log.setStatus(Constants.PAY_STATUS_OK);
				log.setRemark("支付成功，状态："+succeed+"，Result："+result);
				dto.setDefaultPo(log);
				doService("PayService", "addPayLog", dto);
				Integer mark=(Integer)request.getSession().getAttribute("mark");
				request.getSession().removeAttribute("mark");
				return true;
			} else if (succeed.equals("0")) {// 支付失败
				// 请修改订单状态,或者其他操作
				// 在界面显示，付款结果提示信息 Result
				log.setStatus(Constants.PAY_STATUS_NO);
				log.setRemark("支付失败，状态："+succeed+"，Result："+result);
				dto.setDefaultPo(log);
				doService("PayService", "addPayLog", dto);
			} else if (succeed.equals("19")) {// 待处理
				// 请修改订单状态,或者其他操作
				// 在界面显示，付款结果提示信息 Result
				log.setStatus(Constants.PAY_STATUS_NO);
				log.setRemark("支付处理中，状态："+succeed+"，Result："+result);
				dto.setDefaultPo(log);
				doService("PayService", "addPayLog", dto);
			} else {// 支付失败
				log.setStatus(Constants.PAY_STATUS_NO);
				log.setRemark("支付失败，状态："+succeed+"，Result："+result);
				dto.setDefaultPo(log);
				doService("PayService", "addPayLog", dto);
			}

		}else{
			logger.info("签名匹配,支付被拒绝。");
			
		}
		return false;
		
	}
	public String ok() throws Exception {
		// 字符编码
		String CharacterEncoding = "UTF-8";
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding(CharacterEncoding);
		String billNo = request.getParameter("BillNo"); // 商户网站订单号
		boolean resultFlag=this.updatePaydata(request);//支付成功修改订单状态
		if(resultFlag==true){
			return "success";//支付成功，跳到成功页面
		}
		return "fail";
	
	}
	/**
	 * 双乾支付异步通知接口 
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String do95epayNotify() throws UnsupportedEncodingException {
		HttpServletRequest request = getRequest();
		String CharacterEncoding = "UTF-8";
	    request.setCharacterEncoding(CharacterEncoding);
	    this.updatePaydata(request);//支付成功修改订单状态
		return null;
	}


}
