package com.lshop.common.payutil.qh.util;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.gv.core.util.ObjectUtils;
import com.lshop.common.payutil.qh.config.QHPayConfig;
import com.lshop.common.payutil.qh.service.QHPayUtils;
import com.lshop.common.payutil.qh.vo.OrderPayReturnVo;
import com.lshop.common.payutil.qh.vo.TranckInfoVo;


//对接口进行测试
public class QHPayTestMain {
	private String url = "https://192.168.1.101/";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;
	
	public QHPayTestMain(){
		httpClientUtil = new HttpClientUtil();
	}
	
	public void test() throws DocumentException{
		String httpOrgCreateTest = QHPayConfig.QUERY_CHECKOUT_URL;
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("account","140105");
		createMap.put("terminal","14010501");
		createMap.put("signValue","1819e59723836a9468e2aeb7a1069709f8bdf1053de5bbc71997648a16c88e6a");
		createMap.put("order_number","C012015052615111872346");

		String sHtmlText = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
		System.out.println("result:"+sHtmlText);
		
		    
		if (ObjectUtils.isNotEmpty(sHtmlText)) {
			Document document = DocumentHelper.parseText(sHtmlText);
			
			Element root=document.getRootElement();
			
			System.out.println(root.getName());
			Element pay= root.element("paymentInfo");
			System.out.println(pay.element("signValue").getText());
			System.out.println(pay.getText());
			System.out.println(document.getDocument().getName());
		}
	}
	
	public void uploadTranckNo() throws DocumentException{
		System.out.println("*****************************************");
		String httpOrgCreateTest = QHPayConfig.UPLOAD_TRACHING_URL;
//		Map<String,String> createMap = new HashMap<String,String>();
//		createMap.put("account","140105");
//		createMap.put("terminal","14010501");
//		createMap.put("signValue","1819e59723836a9468e2aeb7a1069709f8bdf1053de5bbc71997648a16c88e6a");
//		createMap.put("order_number","C012015052615111872346");

		TranckInfoVo tranckInfoVo= QHPayUtils.uploadTrackNo("150240", "15024001", "6f86822d", "150615145626150240466", "8571353604", "http://www.dhl.com", "admin");
		System.out.println("result:"+tranckInfoVo.getTracking_results());
		
		    
		
	}
	
	
	public void payOrderInfo(){
		
	}
	
	public void xmlParseOrderPayReturnTest() throws DocumentException{
		String sHtmlText="<?xml version='1.0' encoding='UTF-8'?><response><response_type>1</response_type><account>140105</account>"
				+ "<terminal>14010508</terminal><signValue>4B9AAA1AD0B931D46D46842227C62776EBF270B8C73BE0FD9C65B7F22852E74A</signValue>"
				+ "<order_number>500000102</order_number><order_currency>USD</order_currency><order_amount>156.03</order_amount><order_notes></order_notes>"
				+ "<card_number>411111***1111</card_number><payment_id>150417134644140105991</payment_id>"
				+ "<payment_authType>0</payment_authType><payment_status>0</payment_status>"
				+ "<payment_details>80036:Transaction Not Allowed</payment_details>"
				+ "<payment_risk>sourceUrl=10.0(http://localhost:82);</payment_risk></response>";
		OrderPayReturnVo oprvo=QHPayUtils.xmlParseOrderPayReturn(sHtmlText);
		System.out.println(oprvo);
		System.out.println(oprvo.getCard_number());
	}
	public static void main(String[] args) {
		try {
			QHPayTestMain main = new QHPayTestMain();
			//main.test();
			//main.uploadTranckNo();
			System.out.println("+++++++++++++++++++++++++++++++++");
			main.xmlParseOrderPayReturnTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("请求异常"+e.getMessage());
		}
	}
}

