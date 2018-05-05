package com.lshop.common.payutil.qh.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.gv.core.util.ObjectUtils;
import com.lshop.common.payutil.qh.config.QHPayConfig;
import com.lshop.common.payutil.qh.util.HttpClientUtil;
import com.lshop.common.payutil.qh.vo.OrderInfoVo;
import com.lshop.common.payutil.qh.vo.OrderPayReturnVo;
import com.lshop.common.payutil.qh.vo.TranckInfoVo;
import com.lshop.common.util.Sha256Util;

public class QHPayUtils {
	
	
	
	/**
	 * 
	 * @Method: payOrderInfo 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年5月28日 上午11:23:15]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年5月28日 上午11:23:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param url
	 * @param params
	 * @return 
	 * @return String
	 */
	public static String payOrderInfo(String url,Map<String, String> params){
		return HttpClientUtil.doPost(url,params,QHPayConfig.charset);
	}

	
	public static Boolean doQHNotify(String responseText){
		
		
		return true;
	}
	
	

	
	/**
	 * 
	 * @Method: queryOrderStatus
	 * @Description: [查询订单状态]
	 * @CreateDate: [2015年5月27日 下午5:44:32]
	 * @param account
	 *            账户
	 * @param terminal
	 *            终端号
	 * @param secureCode
	 *            密钥
	 * @param order_number
	 *            订单号
	 * @throws IOException
	 * @return String 响应返回xmlStr
	 * @throws DocumentException 
	 */
	public static OrderInfoVo queryOrderStatus(String account,String terminal,String secureCode,String order_number) throws IOException, DocumentException{
		String httpsUrl = QHPayConfig.QUERY_CHECKOUT_URL;
		Map<String,String> createMap = new HashMap<String,String>();
		String signValue=Sha256Util.encode(account+terminal+secureCode+order_number);
		
		createMap.put("account",account);
		createMap.put("terminal",terminal);
		createMap.put("signValue",signValue);
		createMap.put("order_number",order_number);

		//发送查询请求
		String sHtmlText = HttpClientUtil.doPost(httpsUrl,createMap,QHPayConfig.charset);
		System.out.println("result:"+sHtmlText);
		
		//解析返回数据    
		OrderInfoVo orderVo=xmlParseOrderInfo(sHtmlText);
		return orderVo;
	}

	
	/**
	 * 
	 * @Method: uploadTrackNo
	 * @Description: [上传物流信息]
	 * @Author: [liaoxj]
	 * @CreateDate: [2015年5月28日 上午10:53:29]
	 * @param account
	 *            账户
	 * @param terminal
	 *            终端号
	 * @param secureCode
	 *            密钥
	 * @param payment_id
	 *            支付唯一号
	 * @param tracking_number
	 *            物流号
	 * @param tracking_site
	 *            物流网站
	 * @param tracking_handler
	 *            物流添加人
	 * @return String 
	 *            响应返回xmlStr
	 * @throws DocumentException 
	 */
	public static TranckInfoVo uploadTrackNo(String account, String terminal,
			                           String secureCode, String payment_id, String tracking_number,
			                           String tracking_site, String trancking_handler) throws DocumentException {
		
		String httpsUrl = QHPayConfig.UPLOAD_TRACHING_URL;
		Map<String,String> createMap = new HashMap<String,String>();
		//交易安全签名
		String signValue=Sha256Util.encode(account+terminal+payment_id+tracking_number+tracking_site+trancking_handler+secureCode);
		System.out.println(signValue);
		
		createMap.put("account",account);
		createMap.put("terminal",terminal);
		createMap.put("signValue",signValue);
		createMap.put("payment_id",payment_id);
		createMap.put("tracking_number",tracking_number);
		createMap.put("tracking_site",tracking_site);
		createMap.put("trancking_handler",trancking_handler);

		//上传物流信息
		String sHtmlText=HttpClientUtil.doPost(httpsUrl,createMap,QHPayConfig.charset);
		System.out.println(sHtmlText);
		TranckInfoVo tranckVo=xmlParseThanckInfo(sHtmlText);
		return tranckVo;
		
	}
	
	/**
	 * 
	 * @Method: xmlParseOrderInfo 
	 * @Description:  [解析xml返回格式数据，完成数据组装]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年5月28日 下午12:03:28]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年5月28日 下午12:03:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param sHtmlText
	 * @param orderVo
	 * @throws DocumentException 
	 * @return void
	 */
	public static OrderInfoVo xmlParseOrderInfo(String sHtmlText)throws DocumentException {
		OrderInfoVo orderVo=new OrderInfoVo();
		if (ObjectUtils.isNotEmpty(sHtmlText)) {
			Document document = DocumentHelper.parseText(sHtmlText);
			if(ObjectUtils.isNotEmpty(document)){
				Element root=document.getRootElement();
				if(ObjectUtils.isNotEmpty(root)){
					Element payInfo= root.element("paymentInfo");
					if(ObjectUtils.isNotEmpty(payInfo)){
						if(ObjectUtils.isNotEmpty(payInfo.element("account"))){
							orderVo.setAccount(payInfo.element("account").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("terminal"))){
							orderVo.setTerminal(payInfo.element("terminal").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("signValue"))){
							orderVo.setSignValue(payInfo.element("signValue").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("order_number"))){
							orderVo.setOrder_number(payInfo.element("order_number").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("order_currency"))){
							orderVo.setOrder_currency(payInfo.element("order_currency").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("order_amount"))){
							orderVo.setOrder_amount(payInfo.element("order_amount").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("order_sourceUrl"))){
							orderVo.setOrder_sourceUrl(payInfo.element("order_sourceUrl").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("payment_id"))){
							orderVo.setPayment_id(payInfo.element("payment_id").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("payment_results"))){
							orderVo.setPayment_results(payInfo.element("payment_results").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("payment_dateTime"))){
							orderVo.setPayment_dateTime(payInfo.element("payment_dateTime").getText());
						}
						if(ObjectUtils.isNotEmpty(payInfo.element("auth_status"))){
							orderVo.setAuth_status(payInfo.element("auth_status").getText());
						}
					}
				}
			}
		}
		return orderVo;
	}
	
	
	/**
	 * 
	 * @Method: xmlParseThanckInfo 
	 * @Description:  [解析物流返回数据]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年5月28日 下午1:57:42]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年5月28日 下午1:57:42]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param sHtmlText
	 * @throws DocumentException 
	 * @return TranckInfoVo
	 */
	public static TranckInfoVo xmlParseThanckInfo(String sHtmlText)throws DocumentException{
		TranckInfoVo tranckInfoVo=null;
		if (ObjectUtils.isNotEmpty(sHtmlText)) {
			Document document = DocumentHelper.parseText(sHtmlText);
			if(ObjectUtils.isNotEmpty(document)){
				tranckInfoVo=new TranckInfoVo();
				Element root=document.getRootElement();
				if(ObjectUtils.isNotEmpty(root.element("account"))){
					tranckInfoVo.setAccount(root.element("account").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("terminal"))){
					tranckInfoVo.setTerminal(root.element("terminal").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("signValue"))){
					tranckInfoVo.setSignValue(root.element("signValue").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("payment_id"))){
					tranckInfoVo.setPayment_id(root.element("payment_id").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("tracking_results"))){
					tranckInfoVo.setTracking_results(root.element("tracking_results").getText());
				}
			}
		}
		return tranckInfoVo;
	}

	
	/**
	 * 
	 * @Method: xmlParseOrderPayReturn 
	 * @Description:  [解析支付成功异步返回处理]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年6月16日 下午2:46:26]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年6月16日 下午2:46:26]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param sHtmlText
	 * @return OrderPayReturnVo
	 * @throws DocumentException 
	 */
	public static OrderPayReturnVo xmlParseOrderPayReturn(String sHtmlText) throws DocumentException{
		OrderPayReturnVo vo=null;
		if (ObjectUtils.isNotEmpty(sHtmlText)) {
			Document document = DocumentHelper.parseText(sHtmlText);
			if(ObjectUtils.isNotEmpty(document)){
				vo=new OrderPayReturnVo();
				Element root=document.getRootElement();
				if(ObjectUtils.isNotEmpty(root.element("response_type"))){
					vo.setResponse_type(root.element("response_type").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("account"))){
					vo.setAccount(root.element("account").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("terminal"))){
					vo.setTerminal(root.element("terminal").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("signValue"))){
					vo.setSignValue(root.element("signValue").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("order_number"))){
					vo.setOrder_number(root.element("order_number").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("order_currency"))){
					vo.setOrder_currency(root.element("order_currency").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("order_amount"))){
				    vo.setOrder_amount(root.element("order_amount").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("order_notes"))){
				    vo.setOrder_notes(root.element("order_notes").getText());
				}
				if(ObjectUtils.isNotEmpty(root.element("card_number"))){
					vo.setCard_number(root.element("card_number").getText());
				}
				if (ObjectUtils.isNotEmpty(root.element("payment_id"))) {
	                   vo.setPayment_id(root.element("payment_id").getText());
				}
				if (ObjectUtils.isNotEmpty(root.element("payment_authType"))) {
                   vo.setPayment_authType(root.element("payment_authType").getText());
				}
				if (ObjectUtils.isNotEmpty(root.element("payment_status"))) {
                   vo.setPayment_status(root.element("payment_status").getText());
				}
				if (ObjectUtils.isNotEmpty(root.element("payment_details"))) {
                   vo.setPayment_details(root.element("payment_details").getText());
				}
				if (ObjectUtils.isNotEmpty(root.element("payment_risk"))) {
					vo.setPayment_risk(root.element("payment_risk").getText());
				}
			}
		}
		
		return vo;
	}
}
