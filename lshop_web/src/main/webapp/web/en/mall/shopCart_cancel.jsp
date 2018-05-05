<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Pay Cancel_TVpad Mall</title>
		<link href="${resDomain}/en/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/en/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div class="buy">	
			<div class="buy_top"><img src="${resDomain}/en/res/images/shopping01.gif" /></div>
			<div class="buy_lc05"></div>
			<div class="message">
				<ul class="title">Place Order Failed</ul>	
				<ul>
				<!-- 
					<li class="pp"><img src="${resDomain}/en/res/images/fail.gif" /></li>
					<li class="wz">Sorry, operation Cancel!<br />
						Please check carefully your order details and retry!
					</li>
					 -->
					<li class="pp"><img src="${resDomain}/en/res/images/fail.gif" /></li>
	                 <li class="wz" >Your order ${oid} to cancel the current payment transactions。<br /><br />
				  	     <a href="/web/userOrder!toConfirmPay.action?oid=${oid}&shopFlag=${shopFlag}">Order Repay</a><br />
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
