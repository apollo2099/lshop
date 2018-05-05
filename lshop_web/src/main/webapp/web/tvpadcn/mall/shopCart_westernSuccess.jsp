<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提交西聯匯款信息成功_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<!-- top -->
<% request.setAttribute("navFlag","mall"); %>
<%@include file="/web/tvpadcn/common/top.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$().ready(function() {
	var users=lshop.getCookieToJSON('user');
	$("#accountEmail").text(users.email);
});

</script>
</head>
<body>
	<div class="buy">	
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
				<div class="buy_lc04"></div>
			<div class="message">
				<ul class="title">訂單提交成功</ul>	
				<ul>
					<li class="pp"><img src="/res/images/suc.gif" /></li>
					<li class="wz">您已成功上傳西聯信息<br />
訂單編號：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a> <br />
感謝您的訂購，我們會在1-2個工作日內進行款項確認，到賬之後24小時之內為您發貨
通過郵件通知您的訂單狀態，請您及時關注您下單時提供的郵箱：<font id="accountEmail"></font>
</li>
				</ul>	
				<ul class="btn"><a href="/web/mall!toMall.action"><img src="/res/images/btn01.gif" /></a></ul>
			</div>								
								
		</div>
</div>		
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 
