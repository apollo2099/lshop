<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad2定制版智能系統網路智能機頂盒</title>
		<link href="${resDomain}/www/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/www/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
		
			<!-- 领过 -->
			<div class="coupons_f">
				<ul>
			      <li class="wd1">${couponView.activityTitle}</li>
			      <li class="wd2">您已經領取過了，下次再來吧</li>
			      <li class="wd2"><input name="" type="button" class="btn04"value="去首頁" onclick="window.location.href='${storeDomain}/index.html'"/></li>
			    </ul>
			</div>
			<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	</body>
</html>
