package com.lshop.web.vbpay.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.payutil.alipay.config.AlipayConfig;
import com.lshop.common.payutil.alipay.services.AlipayService;
import com.lshop.common.payutil.alipayout.config.AlipayOutConfig;
import com.lshop.common.payutil.payDollar.secure.PaydollarSecureUtil;
import com.lshop.common.payutil.payDollar.util.PayDollarConstants;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvRecharge;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.CommonUtil;
import com.lshop.common.util.Constants;
import com.lshop.common.util.EncoderUtil;
import com.lshop.web.userCenter.UserConstant;


/**
 * 处理支付请求参数
 * @author Administrator
 *
 */
@Controller("VBPayParamAction")
@Scope("prototype")
public class PayParamAction extends BaseAction {
	private static final Log logger = LogFactory.getLog(PayParamAction.class);
	/**
	 * 支付宝内卡操作
	 * @param lvRecharge
	 * @param lvAccountAddress
	 * @param domurl
	 * @param alipayConfig
	 * @return
	 * @throws Exception
	 */
	private String execAllpay(LvRecharge lvRecharge,String domurl,AlipayConfig alipayConfig)throws Exception{
		//必填参数//
		//请与贵网站订单系统中的唯一订单号匹配
		String out_trade_no = lvRecharge.getRnum();
		//订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
		String subject = "购买V币充值";
		//订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
		String body = "在线充值支付";
		//订单总金额，显示在支付宝收银台里的“应付总额”里
		String price = lvRecharge.getMoney()+"";
		//String price = "0.01";
		//物流费用，即运费。
		String logistics_fee = "0.00";
		//物流类型，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
		String logistics_type = "EXPRESS";
		//物流支付方式，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
		String logistics_payment = "SELLER_PAY";
		//商品数量，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品。
		String quantity = "1";
		//扩展参数//
		
		//买家收货信息（推荐作为必填）
		//该功能作用在于买家已经在商户网站的下单流程中填过一次收货信息，而不需要买家在支付宝的付款流程中再次填写收货信息。
		//若要使用该功能，请至少保证receive_name、receive_address有值
		
		// 网站商品的展示地址，不允许加?id=123这类自定义参数
		String show_url =domurl;
		String notify_url = domurl+"/web/vbpay/alipayreturn!doAliPayNotify.action";
		String return_url =domurl+ "/web/vbpay/alipayreturn.action";
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("payment_type","1");
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("body", body);
		sParaTemp.put("price", price);
		sParaTemp.put("logistics_fee", logistics_fee);
		sParaTemp.put("logistics_type", logistics_type);
		sParaTemp.put("logistics_payment", logistics_payment);
		sParaTemp.put("quantity", quantity);
		//构造函数，生成请求URL
		String sHtmlText = AlipayService.trade_create_by_buyer(sParaTemp,alipayConfig);
		return sHtmlText;
	}
	/**
	 * 支付宝外卡支付
	 */
	private String execAllpayOut(LvRecharge lvRecharge,LvAccountAddress lvAccountAddress,String domurl,AlipayOutConfig alipayOutConfig,String js_return)throws Exception{
		com.gv.core.hibernate3.HibernateBaseDAO lvlogicReadDao=(com.gv.core.hibernate3.HibernateBaseDAO)com.gv.core.proxy.ServiceConstants.beanFactory.getBean("lvlogicReadDao");
		LvArea lvCountry=(LvArea)lvlogicReadDao.load(LvArea.class,lvAccountAddress.getContryId());   
		String paymeothd=lvRecharge.getRtype()+""; 
			String notify_url = domurl+"/web/vbpay/alipayoutcardreturn!doAliPayOutNotify.action";//异步 通知
			String return_url =domurl+ "/web/vbpay/alipayoutcardreturn.action";//同步通知
			//商户订单号
			String out_trade_no = lvRecharge.getRnum();
			//订单名称
			String subject ="购买V币充值";
			//默认网银
			String default_bank = "";
		   if(Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_VISA).toString().equals(paymeothd)){
				default_bank="cybs-visa";
				
			}else if(Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_MASTER).toString().equals(paymeothd)){
				default_bank="cybs-master";
			}
			String currency = lvRecharge.getCurrency();
			//公用业务扩展参数
			String tpl=lvAccountAddress.getMobile();
			if(tpl==null||"".equals(tpl)){
			 tpl=lvAccountAddress.getTel();
			}
			if(lvRecharge.getCuserEmail()==null||"".equals(lvRecharge.getCuserEmail().trim())){
				lvRecharge.setCuserEmail(lvRecharge.getAccounts());
			}
		    String receive_name	= lvAccountAddress.getRelName();			//收货人姓名，如：张三
			StringBuffer buf=new StringBuffer();
			/**
			 * 注意
			 * 外卡农行moto相关的修改：
			        商户引入js的脚本： 
			<script type="text/javascript" src="https://{rds domain}/merchant/merchant.js"></script>
			rds domain可以选择：rds.alipay.com或irds.alipay.com
			
			商户引入js脚本后通过：window["alipay-merchant-result"]获取js_return
			这个是获取和引入方法
			
			2.paymethod，可选的值为jvm-3d，jvm-moto，具体看你们需求及签约的产品
			
			3.default_bank，可选的值为cybs-visa，cybs-master，如果需要用jcb可以使用老账号的外卡
			
			4.cookie jsReturnCookie在pay_mothod_code.jsp里设置cookie
	       */
			String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
			String userCode=this.getCookieValue(UserConstant.USER_ID, true, Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
			buf.append("PAGE_DISPLAY_MODE^Embedded");
			buf.append("|js_return^").append(js_return);//支付宝安全校验字段
			buf.append("|customer_id").append("^").append(userCode).append("|");
			buf.append("customer_first_name").append("^").append(receive_name).append("|");
			buf.append("customer_last_name").append("^").append(receive_name).append("|");
			buf.append("customer_email").append("^").append(lvRecharge.getCuserEmail()).append("|");
			buf.append("customer_phone").append("^").append(tpl).append("|");
			buf.append("BILL_TO_FIRSTNAME").append("^").append(receive_name).append("|");
			buf.append("BILL_TO_LASTNAME").append("^").append(receive_name).append("|");
			buf.append("BILL_TO_CITY").append("^").append(lvAccountAddress.getCityName()).append("|");
			buf.append("BILL_TO_STREET").append("^").append(lvAccountAddress.getAdress()).append("|");
			buf.append("BILL_TO_PHONENUMBER").append("^").append(tpl).append("|");
			buf.append("BILL_TO_POSTALCODE").append("^").append(lvAccountAddress.getPostCode()).append("|");
			buf.append("BILL_TO_EMAIL").append("^").append(lvRecharge.getCuserEmail()).append("|");
			buf.append("ship_to_firstname").append("^").append(receive_name).append("|");
			buf.append("ship_to_lastname").append("^").append(receive_name).append("|");
			buf.append("ship_to_postalcode").append("^").append(lvAccountAddress.getPostCode()).append("|");
			buf.append("ship_to_phonenumber").append("^").append(tpl).append("|");
			buf.append("ship_to_street1").append("^").append(lvAccountAddress.getAdress()).append("|");
			buf.append("ship_to_city").append("^").append(lvAccountAddress.getCityName()).append("|");
			if(lvCountry.getCode()!=null&&!"".equals(lvCountry.getCode().trim())){
				buf.append("ship_to_country").append("^").append(lvCountry.getCode()).append("|");	
				buf.append("BILL_TO_COUNTRY").append("^").append(lvCountry.getCode()).append("|");
			}else{
			buf.append("ship_to_country").append("^").append(lvAccountAddress.getContryName()).append("|");
			buf.append("BILL_TO_COUNTRY").append("^").append(lvAccountAddress.getContryName()).append("|");
			}
			if(100226==lvCountry.getId()||100020==lvCountry.getId()){//注意 id为值为美国，或加拿大的，需要特需处理
			if(lvAccountAddress.getProvinceId()!=null){
		    LvArea lvP=(LvArea)lvlogicReadDao.load(LvArea.class,lvAccountAddress.getProvinceId());//获取洲
			buf.append("ship_to_state").append("^").append(lvP.getCode()).append("|");
			buf.append("BILL_TO_STATE").append("^").append(lvP.getCode()).append("|");
			}
			}else{
			buf.append("ship_to_state").append("^").append(lvAccountAddress.getProvinceName()).append("|");
			buf.append("BILL_TO_STATE").append("^").append(lvAccountAddress.getProvinceName()).append("|");
			}
			buf.append("ship_to_shipmethod").append("^").append("3").append("|");//3表示DHL发货
			buf.append("logistics_cost").append("^").append("0.00").append("|");
			buf.append("registration_name").append("^").append(lvAccountAddress.getRelName()).append("|");
			buf.append("registration_email").append("^").append(lvRecharge.getCuserEmail()).append("|");
			buf.append("registration_phone").append("^").append(tpl).append("");
			String extend_param =buf.toString();
			//必填，用于商户的特定业务信息的传递
			//卖家支付宝账号
			String seller_logon_id =alipayOutConfig.seller_logon_id;
			//付款金额
			String total_fee = lvRecharge.getMoney()+"";
			//订单描述
			String body = "在线充值支付";
			//商品展示地址
			String show_url ="";
			//空值
			//币种
			//必填，default_bank为boc-visa或boc-master时，支持USD，为boc-jcb时，不支持currency参数，即默认支持RMB
			//把请求参数打包成数组
			String error_return_url=domurl+"/web/"+(String)this.getRequest().getAttribute("flag")+"/mall/jump_fail.jsp";
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service","alipay.trade.direct.forcard.pay");
	        sParaTemp.put("partner", alipayOutConfig.partner);
	        sParaTemp.put("_input_charset", alipayOutConfig.input_charset);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("error_return_url", error_return_url);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("paymethod", "jvm-3d");//paymethod，可选的值为jvm-3d，jvm-moto，具体看你们需求及签约的产品
			sParaTemp.put("default_bank", default_bank);
			sParaTemp.put("extend_param", extend_param);
			sParaTemp.put("seller_logon_id", seller_logon_id);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			if(!"boc-jcb".equals(default_bank)){//default_bank为boc-jcb时，不支持currency参数，即默认支持RMB
			sParaTemp.put("currency", currency);
			}
			//建立请求
			String sHtmlText = com.lshop.common.payutil.alipayout.util.AlipaySubmit.buildRequest(sParaTemp,"post","确认",alipayOutConfig);
		    logger.info(lvRecharge.getRnum()+"****js_return="+js_return);
			logger.info(lvRecharge.getRnum()+"******extend_param="+extend_param);
			return sHtmlText;	
	}

	private String execOldAllpayOut(LvRecharge lvRecharge, LvAccountAddress lvAccountAddress,String domurl,AlipayOutConfig alipayOutConfig,String js_return)throws Exception{
		com.gv.core.hibernate3.HibernateBaseDAO lvlogicReadDao=(com.gv.core.hibernate3.HibernateBaseDAO)com.gv.core.proxy.ServiceConstants.beanFactory.getBean("lvlogicReadDao");
		LvArea lvCountry=(LvArea)lvlogicReadDao.load(LvArea.class,lvAccountAddress.getContryId());
		String paymeothd=lvRecharge.getRtype()+""; 
		String notify_url = domurl+"/web/vbpay/alipayoutcardreturn!doAliPayOutNotify.action";//异步 通知
		String return_url =domurl+ "/web/vbpay/alipayoutcardreturn.action";//同步通知
		//商户订单号
		String out_trade_no = lvRecharge.getRnum();
		//订单名称
		String subject ="购买V币充值";
		//默认网银
		String default_bank = "";
		if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_JCB).toString().equals(paymeothd)){
			default_bank="boc-jcb";//默认是jcb支付方式 这种不支持外币
		}else if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA).toString().equals(paymeothd)){
			default_bank="boc-visa";
			
		}else if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER).toString().equals(paymeothd)){
			default_bank="boc-master";
		}
		if(lvRecharge.getCuserEmail()==null||"".equals(lvRecharge.getCuserEmail().trim())){
			lvRecharge.setCuserEmail(lvRecharge.getAccounts());
		}
		//必填，用于商户的特定业务信息的传递
		//卖家支付宝账号
		String seller_logon_id =alipayOutConfig.seller_logon_id;
		//付款金额
		String total_fee = lvRecharge.getMoney()+"";
		//订单描述
		String body = "在线充值支付";
		//商品展示地址
		String show_url ="";
		//空值
		//币种
		String currency = lvRecharge.getCurrency();
		//公用业务扩展参数
		String tpl=lvAccountAddress.getMobile();
		if(tpl==null||"".equals(tpl)){
		 tpl=lvAccountAddress.getTel();
		}
	    String receive_name	= lvAccountAddress.getRelName();			//收货人姓名，如：张三
		StringBuffer buf=new StringBuffer();
		buf.append("PAGE_DISPLAY_MODE^Embedded|");
		buf.append("BILL_TO_FIRSTNAME").append("^").append(receive_name).append("|");
		buf.append("BILL_TO_LASTNAME").append("^").append(receive_name).append("|");
		buf.append("BILL_TO_CITY").append("^").append(lvAccountAddress.getCityName()).append("|");
		buf.append("BILL_TO_STREET").append("^").append(lvAccountAddress.getAdress()).append("|");
		buf.append("BILL_TO_PHONENUMBER").append("^").append(tpl).append("|");
		buf.append("BILL_TO_POSTALCODE").append("^").append(lvAccountAddress.getPostCode()).append("|");
		buf.append("BILL_TO_EMAIL").append("^").append(lvRecharge.getCuserEmail()).append("|");
		buf.append("ship_to_firstname").append("^").append(receive_name).append("|");
		buf.append("ship_to_lastname").append("^").append(receive_name).append("|");
		buf.append("ship_to_postalcode").append("^").append(lvAccountAddress.getPostCode()).append("|");
		buf.append("ship_to_phonenumber").append("^").append(tpl).append("|");
		buf.append("ship_to_street1").append("^").append(lvAccountAddress.getAdress()).append("|");
		buf.append("ship_to_city").append("^").append(lvAccountAddress.getCityName()).append("|");
		if(lvCountry.getCode()!=null&&!"".equals(lvCountry.getCode().trim())){
			buf.append("ship_to_country").append("^").append(lvCountry.getCode()).append("|");	
			buf.append("BILL_TO_COUNTRY").append("^").append(lvCountry.getCode()).append("|");
		}else{
		buf.append("ship_to_country").append("^").append(lvAccountAddress.getContryName()).append("|");
		buf.append("BILL_TO_COUNTRY").append("^").append(lvAccountAddress.getContryName()).append("|");
		}
		if(100226==lvCountry.getId()||100020==lvCountry.getId()){//注意 id为值为美国，或加拿大的，需要特需处理
		if(lvAccountAddress.getProvinceId()!=null){
	    LvArea lvP=(LvArea)lvlogicReadDao.load(LvArea.class,lvAccountAddress.getProvinceId());//获取洲
		buf.append("ship_to_state").append("^").append(lvP.getCode()).append("|");
		buf.append("BILL_TO_STATE").append("^").append(lvP.getCode()).append("|");
		
		}
		}else{
		buf.append("ship_to_state").append("^").append(lvAccountAddress.getProvinceName()).append("|");
		buf.append("BILL_TO_STATE").append("^").append(lvAccountAddress.getProvinceName()).append("|");
		}
		buf.append("ship_to_shipmethod").append("^").append("3").append("|");//3表示DHL发货
		buf.append("logistics_cost").append("^").append("0.00").append("|");
		buf.append("registration_name").append("^").append(lvAccountAddress.getRelName()).append("|");
		buf.append("registration_email").append("^").append(lvRecharge.getCuserEmail()).append("|");
		buf.append("registration_phone").append("^").append("none").append("");
		String extend_param =buf.toString();
		//必填，default_bank为boc-visa或boc-master时，支持USD，为boc-jcb时，不支持currency参数，即默认支持RMB
		//把请求参数打包成数组
		String error_return_url=domurl+"/web/"+(String)this.getRequest().getAttribute("flag")+"/mall/jump_fail.jsp";
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service","alipay.trade.direct.forcard.pay");
	    sParaTemp.put("partner", alipayOutConfig.partner);
	    sParaTemp.put("_input_charset", alipayOutConfig.input_charset);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("error_return_url", error_return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("default_bank", default_bank);
		sParaTemp.put("extend_param", extend_param);
		sParaTemp.put("seller_logon_id", seller_logon_id);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		if(!"boc-jcb".equals(default_bank)){//default_bank为boc-jcb时，不支持currency参数，即默认支持RMB
		sParaTemp.put("currency", currency);
		}
		//建立请求
		String sHtmlText = com.lshop.common.payutil.alipayout.util.AlipaySubmit.buildRequest(sParaTemp,"post","确认",alipayOutConfig);
		logger.info(lvRecharge.getRnum()+"****js_return="+js_return);
		return sHtmlText;	
	}
	/**
	 * 构造提交表单HTML数据
	 * @param params 请求参数数组
	 * @param gateway 网关地址
	 * @param strMethod 提交方式。两个值可选：post、get
	 * @param strButtonName 确认按钮显示文字
	 * @return 提交表单HTML文本
	 * target  from表单提交方式
	 */
	private  String buildForm(Map<String, String> params, String gateway, String strMethod,String target) {
		StringBuffer sbHtml=new StringBuffer();
		sbHtml.append("<form   name=\"paysubmit\" method=\""+strMethod+"\" action=\""+gateway+"\" target=\""+target+"\">");
		Iterator<String> it =params.keySet().iterator();
		while(it.hasNext()){
			String name=it.next();
			String value=params.get(name);
			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}
	   return sbHtml.toString();
	}


	public String execute()throws Exception{
		this.getResponse().setContentType("text/html;charset=utf-8");  
	    PrintWriter out=this.getResponse().getWriter();
	    HttpServletRequest request=this.getRequest();
	    String domurl="http://"+request.getHeader("Host");
	    LvRecharge lvRecharge = (LvRecharge) request.getAttribute("recharge");
	    LvAccountAddress lvAccountAddress=(LvAccountAddress)request.getAttribute("lvAccountAddress");
	    if(lvRecharge!=null){
	    String paymeothd = lvRecharge.getRtype() + "";	//获取支付方式
	    dto.put("storeFlag", this.getFlag());
	    dto.put("payValue", paymeothd);
	    LvPaymentStyle paystyle=(LvPaymentStyle) doService("VBPayService", "getLvPaymentStyle", dto);
	    
	    String url=paystyle.getUrl()!=null?paystyle.getUrl().trim():paystyle.getUrl();//第三方网关地址
	    JSONObject payData=null;
	    if(paystyle.getParams()!=null&&!"".equals(paystyle.getParams().trim())){
	        payData=JSONObject.fromObject(paystyle.getParams());
	    }
	    if(Short.valueOf(Constants.PAY_METHOD_ALIPAY).toString().equals(paymeothd)){ //支付宝支付
	    	AlipayConfig alipayConfig=new AlipayConfig();//构造收款参数
	    	alipayConfig.partner = (String)payData.get("partner");
	    	alipayConfig.seller_email=(String)payData.get("seller_email");
	    	alipayConfig.key=(String)payData.get("key");
	    	alipayConfig.gateway=url;
	    	String sHtmlText=execAllpay(lvRecharge, domurl,alipayConfig);
	    	out.print(sHtmlText);
	    }else  if(Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_VISA).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_JCB).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_MASTER).toString().equals(paymeothd)){
	    	//支付宝moto通道
	    	AlipayOutConfig alipayOutConfig=new AlipayOutConfig();//构造收款参数
	    	alipayOutConfig.partner = (String)payData.get("partner");
	    	alipayOutConfig.seller_logon_id=(String)payData.get("seller_email");
	    	alipayOutConfig.key=(String)payData.get("key");
	    	alipayOutConfig.gateway=url;
	    	String js_return=this.getCookieValue("jsReturnCookie", true);
	    	if(js_return==null||"".equals(js_return.trim())){
	    		logger.info(lvRecharge.getRnum()+"***********js_return***********not value");
	    	}
	    	js_return=EncoderUtil.dencode(js_return);
	    	js_return=js_return.replace(" ", "");//去掉空格
	    	String sHtmlText=execAllpayOut(lvRecharge,lvAccountAddress, domurl,alipayOutConfig,js_return);
	    	out.print(sHtmlText);
	    }else  if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_JCB).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER).toString().equals(paymeothd)){
	    	//支付宝通道
	    	AlipayOutConfig alipayOutConfig=new AlipayOutConfig();//构造收款参数
	    	alipayOutConfig.partner = (String)payData.get("partner");
	    	alipayOutConfig.seller_logon_id=(String)payData.get("seller_email");
	    	alipayOutConfig.key=(String)payData.get("key");
	    	alipayOutConfig.gateway=url;
	    	String js_return=this.getCookieValue("jsReturnCookie", true);
	    	if(js_return==null||"".equals(js_return.trim())){
	    		logger.info(lvRecharge.getRnum()+"***********js_return***********not value");
	    	}
	    	js_return=EncoderUtil.dencode(js_return);
	    	js_return=js_return.replace(" ", "");//去掉空格
	    	String sHtmlText=execOldAllpayOut(lvRecharge,lvAccountAddress, domurl,alipayOutConfig,js_return);
	    	out.print(sHtmlText);
	    }else if(Short.valueOf(Constants.PAY_METHOD_PAYDOLLAR).toString().equals(paymeothd)){//PAYDOLLAR支付
	    	//获取数据
	    	String merchantId=(String)payData.get("merchantId"); //商户id
	    	String key=(String)payData.get("key"); //密钥
	    	String successUrl=(String) payData.get("successUrl");//支付成功页面
	    	String failUrl=(String) payData.get("failUrl");      //支付失败页面
	    	String cancelUrl=(String) payData.get("cancelUrl");  //取消交易页面
	    	String oid = CommonUtil.getStringABS(lvRecharge.getRnum());//订单编号
	    	String currency= CommonUtil.getStringABS(lvRecharge.getCurrency());        //币种
	    	String totalPrice=CommonUtil.getStringABS(lvRecharge.getMoney());//订单金额
	    	
	    	String currCode="";
	    	String lang="";
	    	if(ObjectUtils.isNotEmpty(currency)){
	    		//币种和币种转换码关系对接
	    		for(Map.Entry<String, String> entry:PayDollarConstants.CURRCODES.entrySet()){   
					if(currency.equals(entry.getKey())){
					   currCode = entry.getValue();
					   break;
					}
				}
	    		//支付语言
	    		if(currency.equals(PayDollarConstants.CURRENCY_USD)
	    		   ||currency.equals(PayDollarConstants.CURRENCY_EUR)
	    		   ||currency.equals(PayDollarConstants.CURRENCY_GBP)
	    		   ||currency.equals(PayDollarConstants.CURRENCY_SGD)
	    		   ||currency.equals(PayDollarConstants.CURRENCY_CAD)){
	    			lang=PayDollarConstants.LANG_E ;
	        	}else if(currency.equals(PayDollarConstants.CURRENCY_RMB)
	        			||currency.equals(PayDollarConstants.CURRENCY_CNY)
	        			||currency.equals(PayDollarConstants.CURRENCY_HKD)
	        			||currency.equals(PayDollarConstants.CURRENCY_AUD)){
	        		lang=PayDollarConstants.LANG_C ;
	        	}else if(currency.equals(PayDollarConstants.CURRENCY_RMB)){
	        		lang=PayDollarConstants.LANG_J;
	        	}
	    	}
	    	
	    	//secureHash安全唯一标识码
	    	String secureHash=PaydollarSecureUtil.generatePaymentSecureHash(merchantId, lvRecharge.getRnum(), currCode, String.valueOf(lvRecharge.getMoney()),PayDollarConstants.PAYTYPE_N,key);
	        
	        //拼接请求支付表单
	    	Map<String, String> params = new HashMap<String, String>();
	    	params.put("merchantId", merchantId);//商户代号
	    	params.put("orderRef", oid);         //订单号
	    	params.put("amount",totalPrice);     //代收金额
	    	params.put("currCode",currCode);     //币种
	    	params.put("successUrl",successUrl); //支付成功页面
	    	params.put("failUrl",failUrl);       //支付失败页面
	    	params.put("cancelUrl", cancelUrl);  //取消交易页面
	    	params.put("mpsMode", PayDollarConstants.MPSMode_NIL);//多币种处理服务
	    	params.put("payType",PayDollarConstants.PAYTYPE_N );  //支付类型
	    	params.put("lang",lang);        //支付语言
	    	params.put("payMethod", PayDollarConstants.PAYMETHOD_ALL);//支付方式
	    	params.put("secureHash",secureHash);
	    	String sHtml=this.buildForm(params, url, "post", "");
			out.println(sHtml);//生成提交表单
	    }
	    /**********from表自动提交表单网关*******************/
	    if(Short.valueOf(Constants.PAY_METHOD_95EPAY).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_VISA).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_JCB).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_MASTER).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_JCB).toString().equals(paymeothd)){
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
	  return null;
	}
}
