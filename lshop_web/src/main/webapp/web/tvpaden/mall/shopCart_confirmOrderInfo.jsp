<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>

<%    
  response.setHeader("Pragma","No-cache");    
  response.setHeader("Cache-Control","no-cache");    
  response.setDateHeader("Expires",   0);    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Confirm Order-HUA YANG MALL</title>
<% request.setAttribute("navFlag","mall"); %>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function onSub(){
	$("#myForm").submit();
}
</script>
</head>
<body>
	<!-- top -->
	<%@include file="/web/tvpaden/common/top.jsp" %>
	
	<div class="buy">	
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
				<div class="buy_lc03"></div>
				
				<div class="fill_orders">
					<h1>Confirm Order</h1>
					<div class="fill_orders01">
					  <div class="receive_info">
							<ul>
								<li class="wd1"><font class="redfont">*</font>Full Name：</li>
								<li class="wd2">${address.relName }</li>
							</ul>
							
						   <ul>
								<li class="wd1">Mobile No.：</li>
								<li class="wd2">${address.mobile }</li>
						  </ul>	
						  <ul >
								<li class="wd1">Tel.：</li>
								<li class="wd2">${address.tel }</li>
						  </ul>
						  <ul >
								<li class="wd1"><font class="redfont">*</font>Shipping Address：</li>
								<li class="wd2"  style="word-break:break-all">${address.adress }${address.cityName }${address.provinceName }${address.contryName }</li>
						  </ul>
						  <ul >
								<li class="wd1"><font class="redfont">*</font>Zip Code：</li>
							    <li class="wd2">${address.postCode }</li>
						  </ul>
					  </div>
					 
					 <div class="pay">	
					 	<ul class="pay_t">								
						   <li>Order Summary</li>
						   <li class="freight">Shipping Cost：${moneyMark}${postPrice }</li>							   
						</ul>						  
					  </div>
											
					<!--订单列表-->
					<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">Items Name</p>
								<p>Price</p>
								<p>Quantity</p>
								<p>Subtotal</p>
							</li>
							<c:foreach items="${objList}" var="obj" varStatus="status">
							<li id="cart${status.count }">
								<p><img src="${obj[1].pimage }" width="70px" height="60px"/></p>
								<p class="title">${obj[1].productName }</p>
								<p><font class="redfont fontwz">${moneyMark}${obj[0].shopPrice }</font></p>
						 	  	<p>${obj[0].shopNum }</p>
						 	  	<p><font class="redfont fontwz">${moneyMark}<u:formatnumber value="${obj[0].shopPrice * obj[0].shopNum }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></p>
							</li>
							</c:foreach>
						</ul>
						<ul class="sum"><span id="showData">Total：${moneyMark}<u:formatnumber value="${allAmount }"  type="number" groupingUsed="false" maxFractionDigits="2"/> - Coupon：${moneyMark}<u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/> + Shipping Cost：${moneyMark}<u:formatnumber value="${postPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/> </span></ul>
						<ul class="sum01">Merchandise Subtotal：<font class="redfont">${moneyMark}<u:formatnumber value="${allAmount-allCouponPrice+postPrice}"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></a></ul>
						<c:if test="${not empty msg}">
							<div style="width:965px; background:#FFFFCB; height:25px; text-align:right; padding-top:8px; color:#FF0000; padding-right:30px; border-bottom:1px #e5e5e5 solid;"><img src="/res/images/pos_icon.gif" /> The price of your order has changed, please reconfirm it!</div>
						</c:if>	
						<ul class="btn1">
							<form action="/web/shopCart!saveOrder.action" id="myForm" method="post">
								<input type="hidden" name="addressCode" value="${address.code }"/>
								<input type="hidden" name="lvOrder.paymethod" value="${payValue }"/>
								<input type="hidden" name="bestDeliveryValue" value="${bestDeliveryValue }"/>
								<input type="hidden" name="lvOrder.totalPrice" value="<u:formatnumber value='${allAmount-allCouponPrice+postPrice}'  type='number' groupingUsed="false" maxFractionDigits="2"/>"/>
								<input type="hidden" name="couponCode" value="${couponCode}"/>
								<input type="hidden" name="lvOrder.orderRemark" value="${orderRemark }"/>
								<input type="hidden" name="presentPrice" value="${groupBuy.presentPrice }"/>
								<!--<input type="hidden" name="lvOrder.couponNum" value="${couponNum }"/>  -->
								<!--<input type="hidden" name="potPrice" value="${postPrice }"/>  -->
								<!--<input type="hidden" name="couponPrice" value="${couponPrice }"/>  -->
								<p class="bt"><a href="#" onclick="onSub();"> <img src="/res/images/sub01.gif" width="123" height="46" border="0" /></a></p>
								<p class="bt"><a href="/web/shopCart!backToOrderInfo.action?lvOrder.paymethod=${payValue }&couponCode=${couponCode }&addressCode=${address.code }&lvOrder.orderRemark=${orderRemark}&bestDeliveryValue=${bestDeliveryValue}"><img src="/res/images/modi.gif" width="123" height="45" border="0" /></a></p>
							 </form>
						</ul>	
					</div>	
								
		</div></div>
</div>		
		<!--End 购物车-->
	<!-- footer-->
	<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
