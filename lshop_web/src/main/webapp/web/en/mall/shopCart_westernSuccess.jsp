<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Submit Western Union Information Success_TVpad Mall</title>
		<link href="${resDomain}/en/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/en/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript">
			$().ready(function() {
				var users=lshop.getCookieToJSON('user');
				var str = "<a href='mailto:"+users.email+"'>"+users.email+"</a>";
				$("#accountEmail").html(str);
			});
		
		</script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		
		<div class="buy">	
			<div class="buy_top"><img src="${resDomain}/en/res/images/shopping01.gif" /></div>
			<div class="buy_lc04"></div>
			<div class="message">
				<ul class="title">Submit order successfully</ul>
				<ul>
					<li class="pp"><img src="${resDomain}/en/res/images/suc.gif" /></li>
					<li class="wz">Your Western Union information has been uploaded successfully.<br />
					Order No.：<a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a> <br />
					Thank you for your order! We will confirm your payment in 1-2 working days and arrange delivery in 24 hours. <br />
					Please check your order status at：<font id="accountEmail"></font>
					</li>
				</ul>	
				<ul class="btn"><a href="/index.html"><img src="${resDomain}/en/res/images/shopping.gif" border="0" /></a></ul>
			</div>								
		</div>	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>	
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
	
	</body>
</html> 
