<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Submit Western Union Information Success_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		
		<script type="text/javascript">
		$().ready(function() {
			var users=lshop.getCookieToJSON('user');
			$("#accountEmail").text(users.email);
		});
		
		</script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div class="buy">	
			<div class="buy_top"><img src="${resDomain}/bmen/res/images/shopping01.gif" /></div>
			<div class="buy_lc04"></div>
			<div class="message">
				<ul class="title">Submit order successfully</ul>
				<ul>
					<li class="pp"><img src="${resDomain}/bmen/res/images/suc.gif" /></li>
					<li class="wz">Your Western Union information has been uploaded successfully.<br />
					Order No.：<a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a> <br />
					Thank you for your order! We will confirm your payment in 1-2 working days and arrange delivery in 24 hours. <br />
					Please check your order status at：<font id="accountEmail"></font>
					</li>
				</ul>	
				<ul class="btn"><input type="button" class="btn05"  onclick="javascript:location.href='/index.html';" value="Go Shopping" /></ul>
			</div>								
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>	
		<!-- footer-->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 
