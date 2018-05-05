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
				  <li class="wz">Your order has been successfully submitted, we'll arrange a delivery as soon<br /> 
				    as possible, please keep your phone open<br />Order No.：<a href="/web/userOrder!viewOrderInfo.action?oid=${lvOrder.oid  }">${lvOrder.oid }</a><br />
				</ul>	
				<ul class="btn"><a href="/web/mall!toMall.action"><img src="/res/images/btn01.gif" /></a></ul>
			</div>								
								
		</div>
</div>		
		<!--End 购物车-->
	<!-- footer-->
	<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
