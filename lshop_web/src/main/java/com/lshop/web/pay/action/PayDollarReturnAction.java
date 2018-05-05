package com.lshop.web.pay.action;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.action.BaseAction;
import com.lshop.common.payutil.payDollar.secure.PaydollarSecureUtil;
import com.lshop.common.payutil.payDollar.util.PayDollarConstants;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.lshop.web.userCenter.UserConstant;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new_branch2.0] 
 * @Package:      [com.lshop.web.pay.action.PayDollarReturnAction.java]  
 * @ClassName:    [PayDollarReturnAction]   
 * @Description:  [PAYDOLLAR返回请求处理]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2013-3-8 上午11:30:18]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2013-3-8 上午11:30:18]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
@Controller("PayDollarReturnAction")
@Scope("prototype")
public class PayDollarReturnAction extends BaseAction {

	private static final long serialVersionUID = -2269414914034390741L;
	private static final Log logger = LogFactory.getLog(PayDollarReturnAction.class);
    private boolean updatePaydata(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	PrintWriter out=response.getWriter();
    	/*
    	System.out.println("*********************************************************************************************************************");
        System.out.println("Requeset PathInfo=>" + request.getPathInfo());
        System.out.println("Requeset PathTranslated=>"+ request.getPathTranslated());
        System.out.println("Requeset RequestURI=>"+ request.getRequestURI());
        System.out.println("Requeset QueryString=>"+ request.getQueryString());
        Enumeration enumeration = request.getParameterNames();
        String[] paramValues = null;
        String paramName = null;
        StringBuffer buffer = new StringBuffer();
        int paramValuesSize = 0;

        while (enumeration != null && enumeration.hasMoreElements()) {
	           buffer.delete(0, buffer.length());
	           paramName = (String) enumeration.nextElement();
	           paramValues = request.getParameterValues(paramName);
	           paramValuesSize = 0;
	           if (paramValues != null && paramValues.length > 0)
		           paramValuesSize = paramValues.length;
	               for (int i = 0; i < paramValuesSize; i++) {
		           buffer.append(paramValues[i] + ",");
	           }
	   buffer.deleteCharAt(buffer.lastIndexOf(","));
	   System.out.println("Requeset Parameter NAME=>" + paramName+ " VALUES =>" + buffer.toString());
       }
       System.out.println("Request Charater Encoding is ==>"+ request.getCharacterEncoding());
       */
       /***********************************************************************************************
        * 返回参数收集
        ***********************************************************************************************/
       String src = request.getParameter("src");//返回银行主机码（次）
       String prc = request.getParameter("prc");//返回银行主机码（主）
       String successcode = request.getParameter("successcode");//0-成功,1-失败,Others-错误
       String ref = request.getParameter("Ref");//商家参考订单号
       String payRef = request.getParameter("PayRef");//PayDollar的支付参考号
       String amt = request.getParameter("Amt");//交易金额
       String cur = request.getParameter("Cur");//交易币种类型
       String payerAuth = request.getParameter("payerAuth");//付款人认证状态

       String ord = request.getParameter("Ord");//银行参考订单号
       String holder = request.getParameter("Holder");//支付账号的持有人姓名
       String remark = request.getParameter("remark");//备注域，用来存储商家没有现在在交易网页上的附加数据
       String authId = request.getParameter("AuthId");//核准码
       String eci = request.getParameter("eci");//ECI值(适用于启用3D的商家)
       String sourceIp = request.getParameter("sourceIp");//付款人ip
       String ipCountry = request.getParameter("ipCountry");//付款人国家

       String mpsAmt = request.getParameter("mpsAmt");//MPS交易金额
       String mpsCur = request.getParameter("mpsCur");//MPS交易币种
       String mpsForeignAmt = request.getParameter("mpsForeignAmt");//MPS对外交易金额
       String mpsForeignCur = request.getParameter("mpsForeignCur");//MPS对外交易币种
       String mpsRate = request.getParameter("mpsRate");//MPS汇率
       String cardlssuingCountry = request.getParameter("cardlssuingCountry");//发卡国家编码
       String payMethod = request.getParameter("payMethod");//付款方式

       
       //获取密钥
       dto.put("oid", ref);
       LvOrder order=(LvOrder) doService("OrderService", "getOrder", dto);
       dto.put("storeFlag",order.getStoreId());
       dto.put("payValue", Constants.PAY_METHOD_PAYDOLLAR);
       LvPaymentStyle paystyle=(LvPaymentStyle) doService("PayService", "getLvPaymentStyle", dto);
       JSONObject payData=null;
       if(paystyle.getParams()!=null&&!"".equals(paystyle.getParams().trim())){
           payData=JSONObject.fromObject(paystyle.getParams());
       }
       String secureHashSecret=(String)payData.get("key"); //密钥
       request.setAttribute("shopFlag", order.getStoreId());
	   request.setAttribute("oid", ref);
       
		//拼装支付日志信息
	    String currCode="";
		for(Map.Entry<String, String> entry:PayDollarConstants.CURRCODES.entrySet()){   
			if(cur.equals(entry.getValue())){
			   currCode = entry.getKey();
			   break;
			}
		}
		LvPayLogs log = new LvPayLogs();
		log.setOid(ref);
		log.setAmount(Float.parseFloat(amt));
		log.setPaymethod(Constants.PAY_METHOD_PAYDOLLAR);
		log.setPaydate(new Date());
		log.setBankorderid(payRef);
		log.setCheckno("");
		log.setCurrency(currCode);
       /***********************************************************************************************
        * 商户订单安全认证
        ***********************************************************************************************/
       boolean isSecureHashSetting=true;

       //if Secure Hash is used
		if (isSecureHashSetting) {
			String[] secureHash = request.getParameterValues("secureHash");
			List tempList = new ArrayList();
			if (secureHash != null) {
				for (int i = 0; i < secureHash.length; i++) {
					System.out.println(secureHash[i]);
					if (secureHash[i].indexOf(",") > 0) {
						String[] data = secureHash[i].split(",");
						for (int j = 0; data != null & j < data.length; j++) {
							tempList.add(data[j]);
						}
					} else {
						tempList.add(secureHash[i]);
					}
				}
			}

			int size = tempList.size();
			if (size > 0) {
				secureHash = new String[size];
				for (int i = 0; i < size; i++) {
					secureHash[i] = (String) tempList.get(i);
				}
			}

			boolean verifyResult = PaydollarSecureUtil.verifyPaymentDatafeed(
					src, prc, successcode, ref, payRef, cur, amt, payerAuth,secureHashSecret,
					secureHash);
			logger.info("verifyResult =" + verifyResult);
			
			if (!verifyResult) {//安全认证失败
				log.setStatus(Constants.PAY_STATUS_NO);
				log.setRemark("安全认证失败!");
				dto.setDefaultPo(log);
				doService("PayService", "addPayLog", dto);
				logger.info("verifyResult Faile");
				return false;
			}else{//安全认证通过，修改订单状态
				  //向paydollar发送
			      out.println("OK");

			       
				  /***********************************************************************************************
				   * 修改订单状态，添加支付操作日志信息
				   ***********************************************************************************************/
					if (successcode.equals("0")) {
						// 修改订单状态
						dto.put("oid", ref);
						dto.put("totalPrice", Float.parseFloat(amt));
						dto.put("thirdpartyorder", payRef);
						dto.put("payStatus", Constants.PAY_STATUS_OK);// 已支付
						dto.put("status", Constants.ORDER_STATUS_0);// 待发货
						boolean res = (Boolean) doService("OrderService","updateOrderStatus", dto);
						if (true == res) {
							Dto dto2 = new BaseDto();
							try {
								String uid = null;
								uid=this.getCookieValue(UserConstant.USER_ID, true);
								if (StringUtils.isNotBlank(uid)) {
									dto2.put("uid", uid);
								}
							} catch (Exception e) {}
							dto2.put("oid", ref);
							doService("OrderService","orderPayCallBack",dto2);//支付时更改剩余量或已购买量
						}
						// 添加支付成功日志
						log.setStatus(Constants.PAY_STATUS_OK);
						log.setRemark("支付成功");
						dto.setDefaultPo(log);
						doService("PayService", "addPayLog", dto);
					} else {
						// 添加支付失败日志
						log.setStatus(Constants.PAY_STATUS_NO);
						log.setRemark("支付失败,主要响应码(PRC):"+prc+",次要响应码(SRC):"+src);
						dto.setDefaultPo(log);
						doService("PayService", "addPayLog", dto);
						return false;
					}				
			}
		}

	 
		System.out.println("*********************************************************************************************************************");
		return true;
    }
    
    
	public String ok() throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.ok() method begin*****");
		}
		HttpServletRequest request = getRequest();
		HttpServletResponse resposne=this.getResponse();
		boolean resultFlag=this.updatePaydata(request,resposne);
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.ok() method end*****");
		}
		return resultFlag==true?"success":"fail";

	}
	
	
	/**
	 * 处理PayDollar异步数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String doPayDollarNotify() throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.doPayPalNotify() method begin*****");
		}
		HttpServletRequest request = getRequest();
		HttpServletResponse resposne=this.getResponse();
		boolean resultFlag=this.updatePaydata(request,resposne);
		if (logger.isInfoEnabled()) {
			logger.info("***** PayReturnAction.doPayPalNotify() method end*****");
		}
		return null;
	}
	
	/**
	 * 支付成功跳转
	 * @return
	 */
	public String success(){
		  String Ref= this.getRequest().getParameter("Ref");
		  this.getRequest().setAttribute("oid",Ref);
		  if(Ref!=null){
			  dto.put("oid", Ref);
			  LvOrder lvOrder=(LvOrder) doService("OrderService", "getOrder", dto);
			  if(lvOrder!=null){
				  this.getRequest().setAttribute("shopFlag",lvOrder.getStoreId()); 
				  return "success";  
			  }
		  }
		return "e404";
	}
	
	/**
	 * 支付失败跳转
	 * @return
	 */
	public String failure(){
		 String Ref= this.getRequest().getParameter("Ref");
		  this.getRequest().setAttribute("oid",Ref);
		  if(Ref!=null){
			  dto.put("oid", Ref);
			  LvOrder lvOrder=(LvOrder) doService("OrderService", "getOrder", dto);
			  if(lvOrder!=null){
				  this.getRequest().setAttribute("shopFlag",lvOrder.getStoreId()); 
				  return "fail";
			  }
		  }
		  return "e404";		
	}
	
	/**
	 * 取消交易跳转
	 * @return
	 */
	public String cancel(){
		String Ref = this.getRequest().getParameter("Ref");
		this.getRequest().setAttribute("oid", Ref);
		if (Ref != null) {
			dto.put("oid", Ref);
			LvOrder lvOrder = (LvOrder) doService("OrderService", "getOrder",
					dto);
			if (lvOrder != null) {
				this.getRequest()
						.setAttribute("shopFlag", lvOrder.getStoreId());
				return "cancel";
			}
		}
		return "e404";
	}
	
}
