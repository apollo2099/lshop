<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>訂單支付</title>
<link href="${resDomain}/bmen/res/css/css.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/bmen/res/css/buy.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<jsp:include page="/web/${flag}/common/top.jsp"/>
</head>
<body>
<jsp:include page="/web/${flag}/common/header.jsp"/>
<div class="buy">
  <div class="fill_orders">
                    <s:if test="#request.lvOrder.paymethod==11||#request.lvOrder.paymethod==12||#request.lvOrder.paymethod==13||#request.lvOrder.paymethod==15||#request.lvOrder.paymethod==16||#request.lvOrder.paymethod==17">
                    <h1><s:if test="#request.lvOrder.paymethod==11||#request.lvOrder.paymethod==15">VISA</s:if><s:elseif test="#request.lvOrder.paymethod==12||#request.lvOrder.paymethod==16">Master</s:elseif><s:if test="#request.lvOrder.paymethod==13||#request.lvOrder.paymethod==17">JCB</s:if> Credit or Dedit card </h1>
					 <div class="pay">
						<ul class="pay_t">Order Information</ul>
						<s:if test="@com.lshop.common.util.Constants@STORE_TO_MALL_SYSTEM[flag]=='tvpad'">
						<ul class="pay_t1">
							 <li class="wd1">Merchant：</li>
							 <li class="wd2">CREATE NEW TECHNOLOGY (HK) LIMITED</li>
						  </ul>
						  <ul class="pay_t1">
						     <li class="wd1">Merchant Category：</li>
							 <li class="wd2">TVpad Set Top Box</li>
						  </ul>
						</s:if>
						<s:else>
					      <ul class="pay_t1">
							 <li class="wd1">Merchant：</li>
							 <li class="wd2">BANANA JOINT STOCK LIMITED</li>
						  </ul>
						  <ul class="pay_t1">
						     <li class="wd1">Merchant Category：</li>
							 <li class="wd2">bananaTV Set Top Box</li>
						  </ul>
						 </s:else>
						  <ul class="pay_t1">
							 <li class="wd1">Order No.：</li>
							 <li class="wd2">${lvOrder.oid}</li>
						  </ul>
						  <ul class="pay_t1">
							 <li class="wd1">Amount：</li>
							 <li class="wd2">${lvOrder.currency} ${lvOrder.totalPrice}</li>
						  </ul>
						<div class="cb"></div>	
					  </div>
					  </s:if>
					  <div class="pay">
						<div align="center">
						<div id="fromdiv">
					        <s:if test="#request.lvPaymentStyle.payChannel==1">
						    <s:action name="payCenter"  namespace="/web" executeResult="true">
							<s:param name="js_return"  value=""/>
							</s:action>
							</s:if>
							<s:else>
							<s:action name="payparam"  namespace="/web" executeResult="true">
							<s:param name="js_return"  value=""/>
							</s:action>
							</s:else>
						</div>
						</div>
						<div class="cb"></div>	
					  </div>
					  <div class="note">
					   <font class="redfont">溫馨提示：</font></b>點擊“pay my order”按鈕，頁面跳轉后請耐心等待，請勿刷新瀏覽器頁面，如有疑問可聯繫我們的在線客服確認支付狀態，以免重複支付。
						<br/>	
						<font class="redfont">Tips：</font></b>After click on “pay my order”, it will take some time to complete the payment, so please be patient without refreshing the page. If you have any questions, please ask our online customer service to check your payment status to avoid duplicate payment. 
						<br/>					  
					    <font class="redfont">Note: </font></b>Ascertain card-issuing banks might not yet be ready for Internet transaction, please contact your card-issuing bank for any problems in using your card for transactions. 
					  </div>
					  <div class="card"><img src="${resDomain}/bmen/res/images/card.jpg" border="0" /></div>
				      <!--订单列表-->
	</div>
</div>		
<jsp:include page="/web/${flag}/common/footer.jsp"/>
</body>
</html>