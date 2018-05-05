package com.lshop.web.pay.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.lshop.common.payutil.epay.MD5;
import com.lshop.common.payutil.kq.ParamConn;
import com.lshop.common.payutil.kq.UseKeyToSignMsg;
import com.lshop.common.payutil.payDollar.secure.PaydollarSecureUtil;
import com.lshop.common.payutil.payDollar.util.PayDollarConstants;
import com.lshop.common.payutil.qh.service.QHPayUtils;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.CommonUtil;
import com.lshop.common.util.Constants;
import com.lshop.common.util.EncoderUtil;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.common.util.Sha256Util;
import com.lshop.common.util.StringUtil;
import com.lshop.web.order.service.OrderDetetailsService;


/**
 * 处理支付请求参数
 * @author Administrator
 *
 */
@Controller("PayParamAction")
@Scope("prototype")
public class PayParamAction extends BaseAction{
private static final Log logger = LogFactory.getLog(PayParamAction.class);
@Resource
private OrderDetetailsService orderDetetailsService;
/**
 * 支付宝内卡操作
 * @param lvOrder
 * @param lvOrderAdress
 * @param domurl
 * @param alipayConfig
 * @return
 * @throws Exception
 */
private String execAllpay(LvOrder lvOrder,LvOrderAddress lvOrderAdress,String domurl,AlipayConfig alipayConfig)throws Exception{
	//必填参数//
	//请与贵网站订单系统中的唯一订单号匹配
	String out_trade_no = lvOrder.getOid();
	//订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
	String mallFlag= Constants.STORE_TO_MALL_SYSTEM.get(lvOrder.getStoreId());
	String subject = "TVpad";
	//订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
	String body = "TVpad在线支付";
	if(mallFlag.equals("banana")){
		subject = "Banana TV";
		body="Banana TV在线支付";
	}
	//订单总金额，显示在支付宝收银台里的“应付总额”里
	String price = lvOrder.getTotalPrice()+"";
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
	String receive_name	= lvOrderAdress.getRelName();			//收货人姓名，如：张三
	String receive_address = lvOrderAdress.getContryName()+lvOrderAdress.getProvinceName()+lvOrderAdress.getCityName()+lvOrderAdress.getAdress();		//收货人地址
	// 网站商品的展示地址，不允许加?id=123这类自定义参数
	String show_url =domurl;
	String notify_url = domurl+"/web/alipayreturn!doAliPayNotify.action";
	String return_url =domurl+ "/web/alipayreturn.action";
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
	sParaTemp.put("receive_name", receive_name);
	sParaTemp.put("receive_address", receive_address);
	//构造函数，生成请求URL
	String sHtmlText = AlipayService.trade_create_by_buyer(sParaTemp,alipayConfig);
	return sHtmlText;
}
/**
 * 支付宝外卡支付
 */
private String execAllpayOut(LvOrder lvOrder,LvOrderAddress lvOrderAdress,String domurl,AlipayOutConfig alipayOutConfig,String js_return)throws Exception{
	    String paymeothd=lvOrder.getPaymethod()+""; 
	    com.gv.core.hibernate3.HibernateBaseDAO lvlogicReadDao=(com.gv.core.hibernate3.HibernateBaseDAO)com.gv.core.proxy.ServiceConstants.beanFactory.getBean("lvlogicReadDao");
		LvArea lvCountry=(LvArea)lvlogicReadDao.load(LvArea.class,lvOrderAdress.getContryId());
		com.gv.core.hibernate3.HibernateBaseDAO lvuserReadDao=(com.gv.core.hibernate3.HibernateBaseDAO)com.gv.core.proxy.ServiceConstants.beanFactory.getBean("lvuserReadDao");
	//	LvAccount  lvAccount=(LvAccount)lvuserReadDao.load(LvAccount.class,lvOrder.getMemid());
		String notify_url = domurl+"/web/alipayoutcardreturn!doAliPayOutNotify.action";//异步 通知
		String return_url =domurl+ "/web/alipayoutcardreturn.action";//同步通知
		//商户订单号
		String out_trade_no = lvOrder.getOid();
		//订单名称
		String mallFlag= Constants.STORE_TO_MALL_SYSTEM.get(lvOrder.getStoreId());
		String subject = "TVpad";
		//订单描述
		String body = "TVpad在线支付";
		if(mallFlag.equals("banana")){
			subject = "Banana TV";
			body="Banana TV在线支付";
		}
		//默认网银
		String default_bank = "";
	   if(Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_VISA).toString().equals(paymeothd)){
			default_bank="cybs-visa";
			
		}else if(Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_MASTER).toString().equals(paymeothd)){
			default_bank="cybs-master";
		}
		//公用业务扩展参数
		String tpl=lvOrderAdress.getMobile();
		if(tpl==null||"".equals(tpl)){
		 tpl=lvOrderAdress.getTel();
		}
        String receive_name	= lvOrderAdress.getRelName();			//收货人姓名，如：张三
		StringBuffer buf=new StringBuffer();
		buf.append("PAGE_DISPLAY_MODE^Embedded|");
		buf.append("customer_id").append("^").append(lvOrder.getMemid()).append("|");
		buf.append("customer_first_name").append("^").append(receive_name).append("|");
		buf.append("customer_last_name").append("^").append(receive_name).append("|");
		buf.append("customer_email").append("^").append(lvOrder.getUserEmail()).append("|");
		buf.append("customer_phone").append("^").append(tpl).append("|");

		buf.append("BILL_TO_FIRSTNAME").append("^").append(receive_name).append("|");
		buf.append("BILL_TO_LASTNAME").append("^").append(receive_name).append("|");
		buf.append("BILL_TO_CITY").append("^").append(lvOrderAdress.getCityName()).append("|");
		buf.append("BILL_TO_STREET").append("^").append(lvOrderAdress.getAdress()).append("|");
		buf.append("BILL_TO_PHONENUMBER").append("^").append(tpl).append("|");
		buf.append("BILL_TO_POSTALCODE").append("^").append(lvOrderAdress.getPostCode()).append("|");
		buf.append("BILL_TO_EMAIL").append("^").append(lvOrder.getUserEmail()).append("|");
		buf.append("ship_to_firstname").append("^").append(receive_name).append("|");
		buf.append("ship_to_lastname").append("^").append(receive_name).append("|");
		buf.append("ship_to_postalcode").append("^").append(lvOrderAdress.getPostCode()).append("|");
		buf.append("ship_to_phonenumber").append("^").append(tpl).append("|");
		buf.append("ship_to_street1").append("^").append(lvOrderAdress.getAdress()).append("|");
		buf.append("ship_to_city").append("^").append(lvOrderAdress.getCityName()).append("|");
		if(lvCountry.getCode()!=null&&!"".equals(lvCountry.getCode().trim())){
			buf.append("ship_to_country").append("^").append(lvCountry.getCode()).append("|");	
			buf.append("BILL_TO_COUNTRY").append("^").append(lvCountry.getCode()).append("|");
		}else{
		buf.append("ship_to_country").append("^").append(lvOrderAdress.getContryName()).append("|");
		buf.append("BILL_TO_COUNTRY").append("^").append(lvOrderAdress.getContryName()).append("|");
		}
		if(100226==lvCountry.getId()||100020==lvCountry.getId()){//注意 id为值为美国，或加拿大的，需要特需处理
		if(lvOrderAdress.getProvinceId()!=null){
	    LvArea lvP=(LvArea)lvlogicReadDao.load(LvArea.class,lvOrderAdress.getProvinceId());//获取洲
		buf.append("ship_to_state").append("^").append(lvP.getCode()).append("|");
		buf.append("BILL_TO_STATE").append("^").append(lvP.getCode()).append("|");
		
		}
		}else{
		buf.append("ship_to_state").append("^").append(lvOrderAdress.getProvinceName()).append("|");
		buf.append("BILL_TO_STATE").append("^").append(lvOrderAdress.getProvinceName()).append("|");
		}
		buf.append("ship_to_shipmethod").append("^").append("3").append("|");//3表示DHL发货
		buf.append("logistics_cost").append("^").append("0.00").append("|");
		buf.append("registration_name").append("^").append(receive_name).append("|");
		buf.append("registration_email").append("^").append(lvOrder.getUserEmail()).append("|");
		buf.append("registration_phone").append("^").append(tpl).append("");
		
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
		
		buf.append("|js_return^").append(js_return);//支付宝安全校验字段
		String extend_param =buf.toString();
		//必填，用于商户的特定业务信息的传递
		//卖家支付宝账号
		String seller_logon_id =alipayOutConfig.seller_logon_id;
		//付款金额
		String total_fee = lvOrder.getTotalPrice()+"";
		
		//商品展示地址
		String show_url ="";
		//空值
		//币种
		String currency = lvOrder.getCurrency();
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
		sParaTemp.put("paymethod", "jvm-moto");//paymethod，可选的值为jvm-3d，jvm-moto，具体看你们需求及签约的产品
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
	    logger.info(lvOrder.getOid()+"****js_return="+js_return);
		logger.info(lvOrder.getOid()+"******extend_param="+extend_param);
		return sHtmlText;	
}

private String execOldAllpayOut(LvOrder lvOrder,LvOrderAddress lvOrderAdress,String domurl,AlipayOutConfig alipayOutConfig)throws Exception{
    String paymeothd=lvOrder.getPaymethod()+""; 
    com.gv.core.hibernate3.HibernateBaseDAO lvlogicReadDao=(com.gv.core.hibernate3.HibernateBaseDAO)com.gv.core.proxy.ServiceConstants.beanFactory.getBean("lvlogicReadDao");
	LvArea lvCountry=(LvArea)lvlogicReadDao.load(LvArea.class,lvOrderAdress.getContryId());
	com.gv.core.hibernate3.HibernateBaseDAO lvuserReadDao=(com.gv.core.hibernate3.HibernateBaseDAO)com.gv.core.proxy.ServiceConstants.beanFactory.getBean("lvuserReadDao");
//	LvAccount  lvAccount=(LvAccount)lvuserReadDao.load(LvAccount.class,lvOrder.getMemid());
	String notify_url = domurl+"/web/alipayoutcardreturn!doAliPayOutNotify.action";//异步 通知
	String return_url =domurl+ "/web/alipayoutcardreturn.action";//同步通知
	//商户订单号
	String out_trade_no = lvOrder.getOid();
	//订单名称
	String mallFlag= Constants.STORE_TO_MALL_SYSTEM.get(lvOrder.getStoreId());
	String subject = "TVpad";
	String body = "TVpad在线支付";
	if(mallFlag.equals("banana")){
		subject = "Banana TV";
		body="Banana TV在线支付";
	}
	//默认网银
	String default_bank = "";
	if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_JCB).toString().equals(paymeothd)){
		default_bank="boc-jcb";//默认是jcb支付方式 这种不支持外币
	}else if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA).toString().equals(paymeothd)){
		default_bank="boc-visa";
		
	}else if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER).toString().equals(paymeothd)){
		default_bank="boc-master";
	}
	//公用业务扩展参数
	String tpl=lvOrderAdress.getMobile();
	if(tpl==null||"".equals(tpl)){
	 tpl=lvOrderAdress.getTel();
	}
    String receive_name	= lvOrderAdress.getRelName();			//收货人姓名，如：张三
	StringBuffer buf=new StringBuffer();
	buf.append("PAGE_DISPLAY_MODE^Embedded|");
	buf.append("BILL_TO_FIRSTNAME").append("^").append(receive_name).append("|");
	buf.append("BILL_TO_LASTNAME").append("^").append(receive_name).append("|");
	buf.append("BILL_TO_CITY").append("^").append(lvOrderAdress.getCityName()).append("|");
	buf.append("BILL_TO_STREET").append("^").append(lvOrderAdress.getAdress()).append("|");
	buf.append("BILL_TO_PHONENUMBER").append("^").append(tpl).append("|");
	buf.append("BILL_TO_POSTALCODE").append("^").append(lvOrderAdress.getPostCode()).append("|");
	buf.append("BILL_TO_EMAIL").append("^").append(lvOrder.getUserEmail()).append("|");
	buf.append("ship_to_firstname").append("^").append(receive_name).append("|");
	buf.append("ship_to_lastname").append("^").append(receive_name).append("|");
	buf.append("ship_to_postalcode").append("^").append(lvOrderAdress.getPostCode()).append("|");
	buf.append("ship_to_phonenumber").append("^").append(tpl).append("|");
	buf.append("ship_to_street1").append("^").append(lvOrderAdress.getAdress()).append("|");
	buf.append("ship_to_city").append("^").append(lvOrderAdress.getCityName()).append("|");
	if(lvCountry.getCode()!=null&&!"".equals(lvCountry.getCode().trim())){
		buf.append("ship_to_country").append("^").append(lvCountry.getCode()).append("|");	
		buf.append("BILL_TO_COUNTRY").append("^").append(lvCountry.getCode()).append("|");
	}else{
	buf.append("ship_to_country").append("^").append(lvOrderAdress.getContryName()).append("|");
	buf.append("BILL_TO_COUNTRY").append("^").append(lvOrderAdress.getContryName()).append("|");
	}
	if(100226==lvCountry.getId()||100020==lvCountry.getId()){//注意 id为值为美国，或加拿大的，需要特需处理
	if(lvOrderAdress.getProvinceId()!=null){
    LvArea lvP=(LvArea)lvlogicReadDao.load(LvArea.class,lvOrderAdress.getProvinceId());//获取洲
	buf.append("ship_to_state").append("^").append(lvP.getCode()).append("|");
	buf.append("BILL_TO_STATE").append("^").append(lvP.getCode()).append("|");
	
	}
	}else{
	buf.append("ship_to_state").append("^").append(lvOrderAdress.getProvinceName()).append("|");
	buf.append("BILL_TO_STATE").append("^").append(lvOrderAdress.getProvinceName()).append("|");
	}
	buf.append("ship_to_shipmethod").append("^").append("3").append("|");//3表示DHL发货
	buf.append("logistics_cost").append("^").append("0.00").append("|");
	buf.append("registration_name").append("^").append(receive_name).append("|");
	buf.append("registration_email").append("^").append(lvOrder.getUserEmail()).append("|");
	buf.append("registration_phone").append("^").append("none").append("");
	String extend_param =buf.toString();
	//必填，用于商户的特定业务信息的传递
	//卖家支付宝账号
	String seller_logon_id =alipayOutConfig.seller_logon_id;
	//付款金额
	String total_fee = lvOrder.getTotalPrice()+"";
	
	//商品展示地址
	String show_url ="";
	//空值
	//币种
	String currency = lvOrder.getCurrency();
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
	String sHtmlText = com.lshop.common.payutil.alipayout.util.AlipaySubmit.buildRequest(sParaTemp,"get","确认",alipayOutConfig);
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
    LvOrder lvOrder=(LvOrder)request.getAttribute("lvOrder");
    if(lvOrder!=null){
    LvOrderAddress lvOrderAdress=(LvOrderAddress)request.getAttribute("lvOrderAdress");
    String paymeothd=Short.valueOf(lvOrder.getPaymethod()).toString();//获取支付方式
    dto.put("storeFlag", lvOrder.getStoreId());
    dto.put("payValue", paymeothd);
    LvPaymentStyle paystyle=(LvPaymentStyle) doService("PayService", "getLvPaymentStyle", dto);
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
    	String sHtmlText=execAllpay(lvOrder, lvOrderAdress, domurl,alipayConfig);
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
    		logger.info(lvOrder.getOid()+"***********js_return***********not value");
    	}
    	js_return=EncoderUtil.dencode(js_return);
    	js_return=js_return.replace(" ", "");//去掉空格
    	String sHtmlText=execAllpayOut(lvOrder, lvOrderAdress, domurl,alipayOutConfig,js_return);
    	out.print(sHtmlText);
    }else  if(Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_JCB).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER).toString().equals(paymeothd)){
    	//支付宝通道
    	AlipayOutConfig alipayOutConfig=new AlipayOutConfig();//构造收款参数
    	alipayOutConfig.partner = (String)payData.get("partner");
    	alipayOutConfig.seller_logon_id=(String)payData.get("seller_email");
    	alipayOutConfig.key=(String)payData.get("key");
    	alipayOutConfig.gateway=url;
    	String sHtmlText=execOldAllpayOut(lvOrder, lvOrderAdress, domurl,alipayOutConfig);
    	out.print(sHtmlText);
    } else if(Short.valueOf(Constants.PAY_METHOD_95EPAY).toString().equals(paymeothd)){//95双乾支付
	   String merNo=(String)payData.get("merno");//商户号
	   String md5key=(String)payData.get("key");//密key
	   String returnURL=domurl+"/web/95epayreturn.action";
	   String noticeUrl=domurl+"/web/95epayreturn!do95epayNotify.action";
	   int currency=0;
	   if("USD".equals(lvOrder.getCurrency())){
		   currency=1;
	   }
		String md5info = merNo + lvOrder.getOid() + currency + lvOrder.getTotalPrice() + "en" + returnURL + md5key;//加密字符串
		md5info=new MD5().getMD5ofStr(md5info);//加密生成签名 
		Map<String, String> params = new HashMap<String, String>();
		params.put("FirstName",lvOrderAdress.getRelName());
		params.put("LastName",lvOrderAdress.getRelName());
		params.put("Email",lvOrder.getUserEmail());
		String tel=lvOrderAdress.getMobile();
		if(tel==null||"".equals(tel.trim())){
			tel=lvOrderAdress.getTel();
		}
		 //账单人信息
		params.put("Phone",tel);
		params.put("ZipCode",lvOrderAdress.getPostCode());
		params.put("Address",lvOrderAdress.getAdress() );
		params.put("City", lvOrderAdress.getCityName());
		params.put("State", lvOrderAdress.getProvinceName());
		params.put("Country",lvOrderAdress.getContryName());
		  //收货人信息
		params.put("DeliveryFirstName", lvOrderAdress.getRelName());
		params.put("DeliveryLastName",lvOrderAdress.getRelName() );
		params.put("DeliveryEmail",lvOrder.getUserEmail() );
		params.put("DeliveryPhone", lvOrderAdress.getTel());
		params.put("DeliveryZipCode", lvOrderAdress.getPostCode());
		params.put("DeliveryAddress",lvOrderAdress.getAdress());
		params.put("DeliveryCity",lvOrderAdress.getCityName() );
		params.put("DeliveryState",lvOrderAdress.getProvinceName());
		params.put("DeliveryCountry",lvOrderAdress.getProvinceName() );
		params.put("MerNo",merNo );
		params.put("Currency",currency+"" );
		params.put("BillNo",lvOrder.getOid() );
		params.put("Amount",lvOrder.getTotalPrice()+"");
		params.put("ReturnURL",returnURL);
		params.put("Noticeurl", noticeUrl);
		params.put("Language", "en");
		params.put("MD5info",md5info);
		params.put("Remark", lvOrder.getOrderRemark());
		params.put("Products", "TVpad");
		String sHtml=this.buildForm(params, url, "post", "iframepage");
		out.println(sHtml);//生成提交表单
    }else if(Short.valueOf(Constants.PAY_METHOD_PAYPAL).toString().equals(paymeothd)||Short.valueOf(Constants.PAY_METHOD_ICC).toString().equals(paymeothd)){//paypal支付
		String business=(String)payData.get("business");//商户 账户
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("cmd", "_xclick");
    	params.put("txn_type", "subscr_signup");
    	params.put("business",business);//支付账户
    	params.put("charset", "UTF-8");//编码
    	params.put("item_name", "TVpad在线支付");//说明性文字
    	params.put("amount",lvOrder.getTotalPrice()+"");//支付总金额
    	params.put("currency_code","USD" );//货币种类
    	params.put("return", domurl+"/web/"+dto.getAsString("flag")+"/index.jsp");//取消返回地址
    	params.put("notify_url", domurl+"/web/notify!doPayPalNotify.action");//异步回调地址
    	params.put("no_note","1" );
    	params.put("no_shipping","1" );
    	params.put("invoice", lvOrder.getOid());
    	params.put("custom",lvOrder.getOid()+"|"+lvOrder.getUserEmail());
    	String sHtml=this.buildForm(params, url, "post", "");
		out.println(sHtml);//生成提交表单
    }else if(Short.valueOf(Constants.PAY_METHOD_KQ).toString().equals(paymeothd)){//快钱支付外卡帐户
    	String merchantAcctId=(String)payData.get("business");//外卡账户
    	String termId=(String)payData.get("termId");//终端编号
    	String pemPassword=(String)payData.get("key");//密key
		String pemPath = "com/lshop/common/payutil/kq/81244005732004090.pem";//外卡证书
    	String inputCharset = "1";					// 字符集
    	String pageUrl = "";						// 接受支付结果的页面地址服务器接受支
    	String bgUrl = domurl + "/web/kqreturn!doKQNotify.action";		// 付结果的后台地址
    	if(logger.isInfoEnabled()){
			  logger.info("pageUrl = " + pageUrl);
			  logger.info("bgUrl = " + bgUrl);
		}
    	String version = "v1.0";					// 网关版本
    	String language = "1";						// 网关页面语言
    	String channelType = "1,3,7"; 			// 种类渠道类型
    	String signType = "1";						// 签名类型
  
    	String storeName = "TVpad";					/// 商户名称
    	String billingCountry = "US";				// 国际ISO标准的2位字符国家名称(CN=中国)
    	String orderId = lvOrder.getOid();			// 商户订单号
    	String pricingCurrency = "USD";				// 订单报价币别(CNY=人民币)	
    	String pricingAmount = Math.round(lvOrder.getTotalPrice() * 100) + "";		// 订单报价金额(单位为分)
    	if(logger.isInfoEnabled()){
			  logger.info("pricingAmount = "+pricingAmount);
		}
    	String exchangeRateFlag = "0";				// 汇率提供标志
    	String orderTime = new SimpleDateFormat("yyyyMMddHHmmss").format(lvOrder.getCreateTime());	// 商户订单提交时间
    	String payType = "20";						// 支付方式
    	String rcvMethord = "other";				// 收货方式
    	String signMsg = "";						// 签名字符串
    	
    	
    	String relname = CommonUtil.getStringABS(lvOrderAdress.getRelName());
    	String mobile = CommonUtil.getStringABS(lvOrderAdress.getMobile());
    	String tel = CommonUtil.getStringABS(lvOrderAdress.getTel());
    	String postcode = CommonUtil.getStringABS(lvOrderAdress.getPostCode());
    	String contryname = CommonUtil.getStringABS(lvOrderAdress.getContryName());
    	String provincename = CommonUtil.getStringABS(lvOrderAdress.getProvinceName());
    	String cityname = CommonUtil.getStringABS(lvOrderAdress.getCityName());
    	String adress = CommonUtil.getStringABS(lvOrderAdress.getAdress());
    	String createtime = CommonUtil.getStringABS(lvOrderAdress.getCreateTime());
    	// 风控信息
    	StringBuffer riskInfo = new StringBuffer();
    	riskInfo.append("<RiskControlInfomation>");
        riskInfo.append("<prdInfo id='0'>");
        riskInfo.append("<prdName>TVpad</prdName>");
        riskInfo.append("<prdType></prdType>");
        riskInfo.append("<prdDetail></prdDetail>");
        riskInfo.append("</prdInfo>");
        riskInfo.append("<rcvName>"+ relname +"</rcvName>");
        riskInfo.append("<rcvFirstName></rcvFirstName>");
        riskInfo.append("<rcvLastName></rcvLastName>");
        riskInfo.append("<rcvPhoneCountry></rcvPhoneCountry>");
        riskInfo.append("<rcvPhone>"+ mobile +"</rcvPhone>");
        riskInfo.append("<rcvTelCountry></rcvTelCountry>");
        riskInfo.append("<rcvTelRegion></rcvTelRegion>");
        riskInfo.append("<rcvTel>"+ tel +"</rcvTel>");
        riskInfo.append("<rcvZip>"+ postcode +"</rcvZip>");
        riskInfo.append("<rcvCountry>"+ contryname +"</rcvCountry>");
        riskInfo.append("<rcvProvince>"+ provincename +"</rcvProvince>");
        riskInfo.append("<rcvCity>"+ cityname +"</rcvCity>");
        riskInfo.append("<rcvAddress>"+ adress +"</rcvAddress>");
        riskInfo.append("<byrName>"+ relname +"</byrName>");
        riskInfo.append("<byrFirstName></byrFirstName>");
        riskInfo.append("<byrLastName></byrLastName>");
        riskInfo.append("<byrPhoneCountry></byrPhoneCountry>");
        riskInfo.append("<byrPhone>"+ mobile +"</byrPhone>");
        riskInfo.append("<byrTelCountry></byrTelCountry>");
        riskInfo.append("<byrTelRegion></byrTelRegion>");
        riskInfo.append("<byrTel>"+ tel +"</byrTel>");
        riskInfo.append("<byrIdType></byrIdType>");
        riskInfo.append("<byrIdNum></byrIdNum>");
        riskInfo.append("<byrIsMchtMember></byrIsMchtMember>");
        riskInfo.append("<byrRegAddress>"+ adress +"</byrRegAddress>");
        riskInfo.append("<byrRegTime>"+ createtime +"</byrRegTime>");
        riskInfo.append("</RiskControlInfomation>");
        String riskInfoList = riskInfo.toString();
          
    	// 签名字符串
    	StringBuffer signMsgVal=null;	//将非空的参数组成字符串
        signMsgVal = ParamConn.appendParam(signMsgVal, "inputCharset", inputCharset);
        signMsgVal = ParamConn.appendParam(signMsgVal, "pageUrl", pageUrl);
        signMsgVal = ParamConn.appendParam(signMsgVal, "bgUrl", bgUrl);
        signMsgVal = ParamConn.appendParam(signMsgVal, "version", version);
        signMsgVal = ParamConn.appendParam(signMsgVal, "language", language);
        signMsgVal = ParamConn.appendParam(signMsgVal, "signType", signType);
        signMsgVal = ParamConn.appendParam(signMsgVal, "merchantAcctId", merchantAcctId);
        signMsgVal = ParamConn.appendParam(signMsgVal, "termId", termId);
        signMsgVal = ParamConn.appendParam(signMsgVal, "orderId", orderId);
        signMsgVal = ParamConn.appendParam(signMsgVal, "pricingCurrency", pricingCurrency);
        signMsgVal = ParamConn.appendParam(signMsgVal, "pricingAmount", pricingAmount);
        signMsgVal = ParamConn.appendParam(signMsgVal, "exchangeRateFlag", exchangeRateFlag);
        signMsgVal = ParamConn.appendParam(signMsgVal, "orderTime", orderTime);
        signMsgVal = ParamConn.appendParam(signMsgVal, "payType", payType);
        
        try {
    		UseKeyToSignMsg useKey=UseKeyToSignMsg.initPemKeySign();
    		useKey.setPathAndPwd(pemPath, pemPassword);
    		signMsg=useKey.dataToSignMsg(signMsgVal.toString().getBytes("utf-8"));
    	 }catch (Exception e) {}
    		Map<String, String> params = new HashMap<String, String>();
    		params.put("inputCharset", inputCharset);
    		params.put("pageUrl", pageUrl);
    		params.put("bgUrl", bgUrl);
    		params.put("version", version);
    		params.put("language", language);
    		params.put("channelType", channelType);
    		params.put("signType", signType);
    		params.put("merchantAcctId", merchantAcctId);
    		params.put("termId", termId);
    		params.put("storeName", storeName);
    		params.put("billingCountry",billingCountry );
    		params.put("orderId", orderId);
    		params.put("pricingCurrency",pricingCurrency );
    		params.put("pricingAmount", pricingAmount);
    		params.put("exchangeRateFlag", exchangeRateFlag);
    		params.put("orderTime", orderTime);
    		params.put("payType",payType );
    		params.put("rcvMethord", rcvMethord);
    		params.put("riskInfoList", riskInfoList);
    		params.put("signMsg",signMsg );    		
    		String sHtml=this.buildForm(params, url, "post", "");
    		out.println(sHtml);//生成提交表单
    }else if(Short.valueOf(Constants.PAY_METHOD_PAYDOLLAR).toString().equals(paymeothd)){//PAYDOLLAR支付
    	//获取数据
    	String merchantId=(String)payData.get("merchantId"); //商户id
    	String key=(String)payData.get("key"); //密钥
    	String successUrl=(String) payData.get("successUrl");//支付成功页面
    	String failUrl=(String) payData.get("failUrl");      //支付失败页面
    	String cancelUrl=(String) payData.get("cancelUrl");  //取消交易页面
    	String oid = CommonUtil.getStringABS(lvOrder.getOid());//订单编号
    	String currency= CommonUtil.getStringABS(lvOrder.getCurrency());        //币种
    	String totalPrice=CommonUtil.getStringABS(lvOrder.getTotalPrice());//订单金额
    	String remark=CommonUtil.getStringABS(lvOrder.getOrderRemark());        //订单备注
    	
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
    	String secureHash=PaydollarSecureUtil.generatePaymentSecureHash(merchantId, lvOrder.getOid(), currCode, String.valueOf(lvOrder.getTotalPrice()),PayDollarConstants.PAYTYPE_N,key);
        
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
    }else if(Short.valueOf(Constants.PAY_METHOD_ALIPAY_INITIAL).toString().equals(paymeothd)){//支付宝国际银行卡
    	
    }else if(Short.valueOf(Constants.PAY_METHOD_QH).toString().equals(paymeothd)){//钱海支付
    	//交易基础数据
    	String account=(String)payData.get("account"); //账户
    	String terminal=(String)payData.get("terminal"); //终端号
    	String secureCode=(String) payData.get("secureCode");//密钥
    	String backUrl=domurl+"/web/qhPayReturn";//浏览器回调地址
    	String noticeUrl=domurl+"/web/qhPayReturn!doQHNotify.action";//服务器回调地址
    	
    	//数据获取
    	String order_number=StringUtil.isNotEmpty(lvOrder.getOid());
    	String order_currency=StringUtil.isNotEmpty(lvOrder.getCurrency());
    	String order_amount=StringUtil.isNotEmpty(lvOrder.getTotalPrice());
    	String billing_firstName=StringUtil.isNotEmpty(lvOrderAdress.getRelName());
    	String billing_lastName=StringUtil.isNotEmpty(lvOrderAdress.getRelName());
    	String billing_email=StringUtil.isNotEmpty(lvOrder.getUserEmail());
    	//处理电话号码为空的情况
    	String billing_phone=StringUtil.isNotEmpty(lvOrderAdress.getMobile());
    	if(ObjectUtils.isEmpty(billing_phone)){
    		billing_phone=StringUtil.isNotEmpty(lvOrderAdress.getTel());
    	}
    	if(ObjectUtils.isEmpty(billing_phone)){
    		billing_phone="88888888";
    	}
    	
    	String billing_country=StringUtil.isNotEmpty(lvOrderAdress.getContryName());
    	String billing_state=StringUtil.isNotEmpty(lvOrderAdress.getProvinceName());
    	String billing_city=StringUtil.isNotEmpty(lvOrderAdress.getCityName());
    	String billing_address=StringUtil.isNotEmpty(lvOrderAdress.getAdress());
    	String billing_zip=StringUtil.isNotEmpty(lvOrderAdress.getPostCode());
    	
    	//交易签名
    	String signValue=Sha256Util.encode(account+terminal+backUrl+order_number+order_currency+order_amount+billing_firstName.trim()+billing_lastName.trim()+billing_email.trim()+secureCode);
    	
    	//查询订单详情信息
    	String productSku="";
    	String productName="";
    	String productNum="";
    	List list=orderDetetailsService.findAllByOrderId(order_number);
    	if(ObjectUtils.isNotEmpty(list)){
    		for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map= (Map<String, Object>) list.get(i);
				String productSkuTemp=StringUtil.isNotEmpty(map.get("productCode"));
		    	String productNameTemp=StringUtil.isNotEmpty(map.get("productName"));
		    	String productNumTemp=StringUtil.isNotEmpty(map.get("pnum"));
				if(i==list.size()-1){
					productSku+=productSkuTemp.trim();
					productName+=productNameTemp.trim();
					productNum+=productNumTemp.trim();
				}else{
					productSku+=productSkuTemp.trim()+":";
					productName+=productNameTemp.trim()+":";
					productNum+=productNumTemp.trim()+":";
				}
			}
    	}
    	
    	//拼接请求支付表单
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("account", account);     //账户
    	params.put("terminal", terminal);   //终端号
    	params.put("signValue", signValue); //交易安全签名
    	params.put("backUrl",backUrl);      //同步回调地址
    	params.put("noticeUrl",noticeUrl);  //异步回调地址
    	
    	params.put("order_number", order_number);         //订单号
    	params.put("order_currency",order_currency);      //订单币种
    	params.put("order_amount",order_amount);          //订单金额
    	params.put("billing_firstName",billing_firstName.trim());//持卡人名
    	params.put("billing_lastName",billing_lastName.trim());  //持卡人姓
    	params.put("billing_email",billing_email.trim());        //持卡人邮箱
    	params.put("billing_phone",billing_phone.trim());        //持卡人电话
    	params.put("billing_country",billing_country);    //持卡人国家
    	params.put("billing_state", billing_state);       //持卡人州
    	params.put("billing_city",billing_city);          //持卡人城市
    	params.put("billing_address",billing_address);    //持卡人详细地址
    	params.put("billing_zip",billing_zip);            //持卡人邮编
    	params.put("order_notes","");                     //订单备注
    	params.put("productSku",productSku);              //产品SKU
    	params.put("productName",productName);            //产品名称
    	params.put("productNum",productNum);              //产品数量
    	params.put("methods","Credit Card");              //
    	params.put("cart_info","magento");                //购物车程序
    	params.put("cart_api","V1.6.0");                  //api版本
    	String sHtml=this.buildForm(params, url, "post", "iframepage");
		out.println(sHtml);//生成提交表单
    }
     
    /**********from表自动提交表单网关*******************/
	if (Short.valueOf(Constants.PAY_METHOD_95EPAY).toString().equals(paymeothd)
	    || Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_VISA).toString().equals(paymeothd)
		|| Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_JCB).toString().equals(paymeothd)
		|| Short.valueOf(Constants.PAY_METHOD_PAYPALOUT_MASTER).toString().equals(paymeothd)
		|| Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_VISA).toString().equals(paymeothd)
		|| Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER).toString().equals(paymeothd)
		|| Short.valueOf(Constants.PAY_METHOD_OLD_PAYPALOUT_JCB).toString().equals(paymeothd)
		|| Short.valueOf(Constants.PAY_METHOD_QH).toString().equals(paymeothd)) {
		   out.print("<iframe id=\"iframepage\" name=\"iframepage\" width=\"1000\" height=\"300\" scrolling=\"auto\" frameborder=\"0\" src=\"\" target=\"_parent\"></iframe>");
		   out.println("<script type=\"text/javascript\">");
		   out.println("document.paysubmit.submit();");
		   out.println("</script>");
	} else {
		   out.println("<script type=\"text/javascript\">");
		   out.println("document.paysubmit.target=\"_self\";");
		   out.println("document.paysubmit.submit();");
		   out.println("</script>");
	}
   }
  return null;
}

}
