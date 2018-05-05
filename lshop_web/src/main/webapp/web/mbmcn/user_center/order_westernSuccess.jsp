<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>
<title>banana商城_用户中心</title>
</head>
<body>
	<header>
		<div class="top">
			<div class="title1">
				<h1>西联信息提交成功</h1>
			</div>
		</div>
	</header>

	<article>
		<div class="subminsucess">
			<div class="sucesstrue"></div>
			<div class="sucwwtip">
				您已成功提交西联汇款信息<br/>感谢您的订购，我们会在1-2个工作日内进行款项确认，到账后24小时之内为您发货。<br /> 
				订单编号:<br/><span class="ord"><a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a></span>
			</div>
		</div>
		<div class="goshop">
			<a href="${MallPath}/" style="background-color: #0099ff">继续购物</a>
		</div>
	</article>

	<article>
		<div style="height: 190px"></div>
	</article>

	<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
	<script type="text/javascript">
	</script>
</body>
</html>
