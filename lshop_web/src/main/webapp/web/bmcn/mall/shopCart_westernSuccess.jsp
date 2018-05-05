<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>提交西联汇款信息成功_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		
		<script type="text/javascript">
		$().ready(function() {
			var users=lshop.getCookieToJSON('user');
			$("#accountEmail").text(users.email);
		});
		
		</script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div class="buy">	
			<div class="buy_top"><img src="${resDomain}/bmcn/res/images/shopping01.gif" /></div>
			<div class="buy_lc04"></div>
			<div class="message">
				<ul class="title">订单提交成功</ul>	
				<ul>
					<li class="pp"><img src="${resDomain}/bmcn/res/images/suc.gif" /></li>
					<li class="wz">您已成功上传西联信息<br />
					订单编号：<a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a> <br />
					感谢您的订购，我们会在1-2个工作日内进行款项确认，到账之后24小时之内为您发货，<br />
					通过邮件通知您的订单状态，请您及时关注您下单时提供的邮箱：<font id="accountEmail"></font>
					</li>
				</ul>	
				<ul class="btn"><input type="button" class="btn05"  onclick="javascript:location.href='/index.html';" value="继续购物" /></ul>
			</div>								
		</div>	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>	
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 
