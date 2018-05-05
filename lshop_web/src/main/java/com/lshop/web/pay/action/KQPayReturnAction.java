package com.lshop.web.pay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.util.StringUtil;
import com.lshop.common.action.BaseAction;
import com.lshop.common.payutil.kq.VerifySignature;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.util.Constants;
import com.lshop.web.userCenter.UserConstant;

@Controller("KQPayReturnAction")
@Scope("prototype")
public class KQPayReturnAction extends BaseAction {
	private static final Log logger	= LogFactory.getLog(KQPayReturnAction.class);
	@SuppressWarnings("unchecked")
	public String ok() throws IOException {
		
		return "success";
	}
	/**
	 * 处理快钱异步据
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String doKQNotify() throws IOException {
		logger.info("********** KQPayReturnAction.doKQNOtify begin **********");
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		String domurl = "http://" + request.getHeader("Host");
		// 商户订单号（只允许使用字母、数字、- 、_,并以字母或数字开头每商户提交的订单号，必须在自身账户交易中唯一）
		String orderId = request.getParameter("orderId");
		// 快钱交易号（该交易在快钱系统中对应的交易号）
		String dealId = request.getParameter("dealId");
		// 快钱交易时间（格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2位] ，例如：20071117020101）
		String dealTime = request.getParameter("dealTime");

		// 实际支付金额（返回在使用优惠券等情况后，用户实际支付的金额 。金额乘以100。例如，美元298.30，提交时金额应为29830）
		String payAmountStr = request.getParameter("payAmount");
		String exchangeRateStr = request.getParameter("exchangeRate");	// 汇率
		float payAmount = 0;
		if (!StringUtil.IsNullOrEmpty(payAmountStr)) {
			try {
				payAmount = Float.valueOf(payAmountStr);
				float exchangeRate = Float.valueOf(exchangeRateStr);
				float usdCent = Math.round(payAmount * exchangeRate);	// 换算美分
				payAmount = usdCent / 100;								// 换算美元
			} catch (NumberFormatException e) {
				logger.error("支付金额有误，订单号" + orderId + "，金额：" + payAmountStr);
				return "fail";
			}
		}
		// 支付币别
		String payCurrency = request.getParameter("payCurrency");
		// 处理结果（10：支付成功 11：支付失败 ）
		String payResult = request.getParameter("payResult");
		VerifySignature vs = VerifySignature.initVerifySignature();
		vs.setPublickey("com/lshop/common/payutil/kq/vpos_cp4JAVA.cer");
		boolean bill99Sign = vs.verifySign(request);
		String rtnUrl = ""; // 提供给快钱将要重定向的地址
		logger.info("********** orderId：" + orderId + " **********");
		logger.info("********** dealId：" + dealId + " **********");

		// 签名字符串验证
		if (bill99Sign) {
			logger.info("********** Enter if (bill99Sign) **********");
			dto.put("oid", orderId);
			Dto d = (Dto)doService("OrderService", "getOrderInfo", dto);
			LvOrder lvOrder = (LvOrder)d.get("lvOrder");
			// 订单未支付
			if (lvOrder != null && lvOrder.getPayStatus() == 0) {
				LvPayLogs log = new LvPayLogs();
				log.setOid(orderId);
				log.setPaymethod(Constants.PAY_METHOD_KQ);
				log.setCheckno("");
				log.setAmount(payAmount);
				log.setStatus(Constants.ORDER_STATUS_0);
				log.setBankorderid(dealId);
				log.setCurrency(payCurrency);
				// 支付成功
				if ("10".equals(payResult)) {
					logger.info("********** Enter if ('10'.equals(payResult)) **********");
					log.setRemark("支付成功");
					dto.put("totalPrice", payAmount);
					dto.put("thirdpartyorder", dealId);
					dto.put("payStatus", Constants.PAY_STATUS_OK);// 已支付
					dto.put("status", Constants.ORDER_STATUS_0);// 订单已支付
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
						dto2.put("oid", orderId);
						doService("OrderService","orderPayCallBack",dto2);//支付时更改剩余量或已购买量
					}
					rtnUrl = domurl + "/web/"+request.getParameter("flag")+"/mall/shopCart_paySuccess.jsp";
				} else {
					logger.info("********** Enter if ('10'.equals(payResult)) of else **********");
					String remark = "支付失败。";
					if ("11".equals(payResult)) {

						// 错误代码（如果处理结果返回11，则有errCode 和errMessage
						// 。错误代码和错误信息详细资料见参考资料。）
						String errCode = request.getParameter("errCode");

						// 错误信息（错误代码的英文描述。 如果处理结果返回11）
						String errMessage = request.getParameter("errMessage");

						remark += ("错误代码：" + errCode + "。错误信息：" + errMessage);
					}
					log.setRemark(remark);
					rtnUrl = domurl + "/web/"+request.getParameter("flag")+"/mall/shopCart_paySuccess.jsp";
				}
				if (dealTime != null && !"".equals(dealTime)) {
					try {
						log.setPaydate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dealTime));
					} catch (Exception e) {
						log.setPaydate(new Date());
					}
				} else {
					log.setPaydate(new Date());
				}
				dto.setDefaultPo(log);
				doService("PayService", "addPayLog", dto);
			}
			// 错误
		} else {
			logger.info("********** Enter if (bill99Sign) of else **********");
			rtnUrl = domurl + "/web/"+request.getParameter("flag")+"/mall/shopCart_payfail.jsp";
		}
		// 1：报告给快钱已处理；
		// trnUrl：显示支付结果的页面。
		out.print("<result>0</result><redirecturl>" + rtnUrl + "</redirecturl>");
		out.close();
		logger.info("********** KQPayReturnAction.doKQNOtify end **********");
		return null;
	}
}
