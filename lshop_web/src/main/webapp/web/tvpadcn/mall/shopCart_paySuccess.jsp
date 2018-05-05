<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>支付成功_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- top -->
	<% request.setAttribute("navFlag","mall"); %>
	<%@include file="/web/tvpadcn/common/top.jsp" %>
<!--End banner-->
	<div class="buy">
	  <div class="message" style="background:#FEFFED;">
					
				<ul>
					<li class="pp"><img src="/res/images/payment_ok.gif" width="118" height="106" /></li>
				  	<li class="wz" style="padding-top:20px;">您的訂單已支付成功，我們會儘快安排發貨，清保持電話通暢<br />訂單編號：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a><br />
				  </li>
				</ul>	
				<ul class="btn"  style="padding-right:100px;"><img src="/res/images/btn01.gif" onclick="javascript:location.href='/web/mall!toMall.action'"/></ul>
			</div>								
								
		</div>
</div>		
<%@include file="/web/tvpadcn/common/footer.jsp" %>
</body>
</html> 
