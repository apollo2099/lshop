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
<title>確認訂單信息_華揚商城</title>
<%@include file="/web/bscn/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
<!-- top -->
<%@include file="/web/bscn/common/top.jsp" %> 
<script type="text/javascript">
function onSub(){
	$("#myForm").submit();
}
</script>
</head>
<body>
	
	<div class="buy">	
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
				<div class="buy_lc03"></div>
				
				<div class="fill_orders">
					<h1>確認訂單信息</h1>
					<div class="fill_orders01">
					  <div class="receive_info">
							<ul>
								<li class="wd1"><font class="redfont">*</font>收貨人姓名：</li>
								<li class="wd2">${address.relName }</li>
							</ul>
							
						   <ul>
								<li class="wd1">手機號碼：</li>
								<li class="wd2">${address.mobile }</li>
						  </ul>	
						  <ul >
								<li class="wd1">固定電話：</li>
								<li class="wd2">${address.tel }</li>
						  </ul>
						  <ul >
								<li class="wd1"><font class="redfont">*</font>收貨地址：</li>
								<li class="wd2">${address.adress }${address.cityName }${address.provinceName }${address.contryName }</li>
						  </ul>
						  <ul >
								<li class="wd1"><font class="redfont">*</font>郵編區號：</li>
							    <li class="wd2">${address.postCode }</li>
						  </ul>
					  </div>
					 
					 <div class="pay">	
					 	<ul class="pay_t">								
						   <li>商品清單</li>
						   <li class="freight">運費：${moneyMark} ${postPrice }</li>							   
						</ul>						  
					  </div>
											
					<!--订单列表-->
					<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">商品名称</p>
								<p>价格</p>
								<p>购买数量</p>
								<p>小计金额</p>
							</li>
							<li>
								<p><img src="${product.pimage }" width="70px" height="60px"/></p>
								<p class="title">${product.productName }</p>
								<p><font class="redfont fontwz">${moneyMark} ${groupBuy.presentPrice}</font></p>
						 	  	<p>${shopNum }</p>
						 	  	<p><font class="redfont fontwz">${moneyMark} <u:formatnumber value="${groupBuy.presentPrice *shopNum }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></p>
							</li>
						</ul>
						<ul class="sum"><span id="showData">商品总金额：${moneyMark} <u:formatnumber value="${amount }"  type="number" groupingUsed="false" maxFractionDigits="2"/>+ 运费:${moneyMark} ${postPrice }</span></ul>
						<ul class="sum01">应付金额：<font class="redfont">${moneyMark} <u:formatnumber value="${amount+postPrice}"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></a></ul>	
						
						<ul class="btn">
							<form action="/web/group!saveOrderForGroup.action" id="myForm" method="post">
							
								<input type="hidden" name="lvGroupBuy.code" value="${groupBuy.code}"/>
								<input type="hidden" name="shopNum" value="${shopNum}"/>
								<input type="hidden" name="addressCode" value="${address.code }"/>
								<input type="hidden" name="lvOrder.paymethod" value="${payValue }"/>
								<input type="hidden" name="lvOrder.orderRemark" value="${orderRemark }"/>
								<input type="hidden" name="bestDeliveryValue" value="${bestDeliveryValue }"/>
								<!--<input type="hidden" name="potPrice" value="${postPrice }"/>  -->
								<!--<input type="hidden" name="lvOrder.totalPrice" value="${groupBuy.presentPrice *shopNum+postPrice}"/> -->
								<a href="#" onclick="onSub();"> <img src="/res/images/sub01.gif" width="123" height="46" border="0" /></a>
								<a href="/web/group!backToGroupOrderInfo.action?lvGroupBuy.code=${groupBuy.code}&shopNum=${shopNum}&addressCode=${address.code }&lvOrder.paymethod=${payValue }&lvOrder.orderRemark=${orderRemark}&bestDeliveryValue=${bestDeliveryValue}"><img src="/res/images/modi.gif" width="123" height="45" border="0" /></a>
							 </form>
						</ul>	
					</div>	
								
		</div></div>
</div>		
		<!--End 购物车-->
	<!-- footer-->
	<%@include file="/web/bscn/common/footer.jsp" %>

</body>
</html> 
