<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center_TVpad Mall</title>
<link href="${resDomain}/en/res/css/css.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/en/res/css/buy.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/en/common/top.jsp"%>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/en/common/header.jsp"%>
<div class="buy">
  <div class="fill_orders">
    <s:if test="#request.recharge.rtype==11||#request.recharge.rtype==12||#request.recharge.rtype==13||#request.recharge.rtype==15||#request.recharge.rtype==16||#request.recharge.rtype==17">
    <h1>
      <s:if test="#request.recharge.rtype==11||#request.recharge.rtype==15">VISA</s:if>
      <s:elseif test="#request.recharge.rtype==12||#request.recharge.rtype==16">Master</s:elseif>
      <s:if test="#request.recharge.rtype==13||#request.recharge.rtype==17">JCB</s:if>
      Credit or Dedit card 
    </h1>
    <div class="pay">
      <ul class="pay_t">Order Information</ul>
      <ul class="pay_t1">
        <li class="wd1">Merchant：</li>
        <li class="wd2">
        	<s:if test="@com.lshop.common.util.Constants@STORE_TO_MALL_SYSTEM[flag]=='tvpad'">CREATE NEW TECHNOLOGY (HK) LIMITED</s:if>
			<s:else>BANANA TECHNOLOGY LIMITED</s:else>
        </li>
      </ul>
      <ul class="pay_t1">
        <li class="wd1">Merchant Category：</li>
        <li class="wd2">V coin rechargeable card</li>
      </ul>
      <ul class="pay_t1">
        <li class="wd1">Order No.：</li>
        <li class="wd2">${recharge.rnum}</li>
      </ul>
      <ul class="pay_t1">
        <li class="wd1">Amount：</li>
        <li class="wd2">${recharge.currency} ${recharge.money}</li>
      </ul>
      <div class="cb"></div>
    </div>
    </s:if>
    <div class="pay">
      <div align="center">
        <div id="fromdiv">
          <s:action name="payparam" namespace="/web/vbpay" executeResult="true" />
        </div>
      </div>
      <div class="cb"></div>
    </div>
    <div class="note">
      <font class="redfont">溫馨提示：</font></b>點擊“pay my order”按鈕，頁面跳轉后請耐心等待，請勿刷新瀏覽器頁面，如有疑問可聯繫我們的在線客服確認支付狀態，以免重複支付。 <br/>
      <font class="redfont">Tips：</font></b>After click on “pay my order”, it will take some time to complete the payment, so please be patient without refreshing the page. If you have any questions, please ask our online customer service to check your payment status to avoid duplicate payment. <br/>
      <font class="redfont">Note: </font></b>Ascertain card-issuing banks might not yet be ready for Internet transaction, please contact your card-issuing bank for any problems in using your card for transactions. </div>
    <div class="card"><img src="${resDomain}/en/res/images/card.jpg" border="0" /></div>
    <!--订单列表-->
  </div>
</div>
<%@include file="/web/en/common/footer.jsp" %>
</body>
