<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提交訂單成功_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- top -->
	<% request.setAttribute("navFlag","mall"); %>
	<%@include file="/web/tvpadcn/common/top.jsp" %>
	<div class="buy">	
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
				<div class="buy_lc04"></div>
			<div class="message">
				<ul class="title">訂單提交成功</ul>	
				<ul>
					<li class="pp"><img src="/res/images/suc.gif" /></li>
				  <li class="wz">您的訂單已提交成功，我們會儘快安排上門安裝，清保持電話暢通！<br />訂單編號：<a href="/web/userOrder!viewOrderInfo.action?oid=${lvOrder.oid  }">${lvOrder.oid }</a><br />
				</ul>	
				<ul class="btn"><a href="/web/mall!toMall.action"><img src="/res/images/btn01.gif" /></a></ul>
			</div>								
								
		</div>
</div>		
		<!--End 购物车-->
	<!-- footer-->
	<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 
