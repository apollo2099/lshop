<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Submit Order-HUA YANG MALL</title>
<% request.setAttribute("navFlag","mall"); %>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- top -->
	<%@include file="/web/tvpaden/common/top.jsp" %>
	<div class="buy">	
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
				<div class="buy_lc04"></div>
			<div class="message">
				<ul class="title">The order is successfully submitted</ul>	
				<ul>
					<li class="pp"><img src="/res/images/suc.gif" /></li>
				  <li class="wz">Your order has been successfully submitted, we'll arrange a delivery as soon as possible, please keep your phone open.<br />Order No.：<a href="http://${domain }/web/userOrder!viewOrderInfo.action?oid=${lvOrder.oid }">${lvOrder.oid}</a><br />
				   After finishing payment, you can check the order status in  <a href="http://${domain }/web/userCenterManage!getAccount.action"><font class="bluewz">My Account</font></a>--<a href="http://${domain }/web/userOrder!getOrderlist.action"><font class="bluewz"> My Order</font></a>Check Order Status</li>
				</ul>	
				<ul class="btn"><a href="http://${domain }/web/shopCart!toPayOrder.action?oid=${lvOrder.oid}"><img src="/res/images/btn01_pay.gif" /></a></ul>
			</div>								
								
		</div>
</div>		
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
