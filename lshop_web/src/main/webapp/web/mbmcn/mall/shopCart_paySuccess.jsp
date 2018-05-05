<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana商城_支付成功</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp"%>
<script type="text/javascript">
if (self.frameElement && self.frameElement.tagName == "IFRAME") {
	window.parent.location.href=location.href;
}
</script>
<%
   String out_trade_no=request.getParameter("out_trade_no");
   if(out_trade_no!=null&&!"".equals(out_trade_no)){
	   request.setAttribute("oid",out_trade_no);
   }
%>
</head>

<body>
	<header>
		<div class="top">
			<div class="title1">
				<h1>支付成功</h1>
			</div>
		</div>
	</header>

	<article>
		<div class="subminsucess">
			<div class="sucesstrue"></div>
			<div class="sucwwtip">
				已支付成功，我们将尽快给您发货<br /> 订单编号:<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }"><span class="ord">${oid }</span></a>
			</div>
		</div>
		<div class="goshop">
			<a href="/" style="background-color: #0099ff">继续购物</a>
		</div>
	</article>

	<article>
		<div style="height: 190px"></div>
	</article>

	<!-- 分享 -->
	<%@include file="/web/mbmcn/common/share.jsp"%>
	<!-- footer -->
	<%@include file="/web/mbmcn/common/footer.jsp"%>

</body>
</html>
