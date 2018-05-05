<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Third-Party Apps for TVpad - HUA YANG MALL</title>
<meta name="description" content="TVpad provides massive third-party Apps on popular VOD movies & TV palys, mini-games for TV, etc., which meets the needs of various users." />
<meta name="keywords" content="Third-party Apps for TVpad, TVpad Apps for TV end users, TVpad Apps, sports online App, popular VOD movies & TV plays, Android intelligent App." />
<% request.setAttribute("navFlag","app"); %>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/layout.css" rel="stylesheet" type="text/css" />
<script src="${resDomain}/tvpaden/res/js/yu.js" type="text/javascript"></script>
<script src="${resDomain}/tvpaden/res/js/tb.js" type="text/javascript"></script>	
</head>
<body>

<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
<!--ad-->
<ad:ad adkey="AD_LOCATION_APP"></ad:ad>

<div class="content_main">
	<!--left_Frame-->
	<div class="left_frame">
		<!--left_app-->
		<app:app></app:app>
		<!--left_ad-->
		<ad:ad adkey="AD_LOCATION_LEFT"></ad:ad>
	</div>

	 <div class="right_frame">
	 	 <div class="product_ad_list">
		 	<h1><span class="s_l">How to install</span><span class="s_r"><font class="bfont"><a href="/index.html">Home</a>--></font> <a href="/web/applist!toApplist.action">Apps</a></span></h1>
			<ul>
				<li><a href="/help5.html#M_65"><img src="${resDomain}/tvpaden/res/images/app_install.gif" width="760" height="220" border="0" /></a></li>

			</ul>						
		 </div>
		 <!--产品广告图列表-->
		 		 
		<!--產品頁 应用列表-->
		<c:if test="${not empty aList}">
		 	<c:foreach items="${aList}" var="a" varStatus="status">
			  <div class="app_list">
			 	<h1><span class="s_l">${a.title }</span></h1>		
					<ul>
					<c:foreach items="${a.list}" var="app">
						<li>
							<a href="/web/applist!toApp.action?appCode=${app.code }"><img src="${app.appImage }" width="100" height="100" border="0" /></a>
						  	<p><b><a href="/web/applist!toApp.action?appCode=${app.code }">${app.name }</a><font class="redfont">${app.language }</font></b><br />Provider：${app.thirdParty }</p>
						</li>
					</c:foreach>
					</ul>					
				</div>
		 	</c:foreach>
	 	</c:if>

	 </div>
	 <!--End right_Frame-->
</div>
<!--End content-->

<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 