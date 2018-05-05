<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad第三方安卓應用_華揚商城</title>
<meta name="description" content="TVpad 擁有豐富的第三方應用，熱門影視點播、體育直播應用、電視端小遊戲等等一系列豐富多彩的應用，滿足不同用戶的不同需求。" />
<meta name="keywords" content="TVpad第三方應用，TVpad 電視端應用，TVpad APP應用，體育直播應用，熱門影視點播，Android 智能應用" />
<%@include file="/web/tvpadcn/common/header.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/layout.css" rel="stylesheet" type="text/css" />
<script src="${resDomain}/tvpadcn/res/js/yu.js" type="text/javascript"></script>
<script src="${resDomain}/tvpadcn/res/js/tb.js" type="text/javascript"></script>	
</head>
<body>

<!-- top -->
<% request.setAttribute("navFlag","app"); %>
<%@include file="/web/tvpadcn/common/top.jsp" %>
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
		 	<h1><span class="s_l">如何安裝</span><span class="s_r"><font class="bfont"><a href="/index.html">首頁</a>--></font> <a href="/web/applist!toApplist.action">第三方應用</a></span></h1>
			<ul>
				<li><a href="/help5.html#M_65"><img src="${resDomain}/tvpadcn/res/images/app_install.gif" width="760" height="220" border="0" /></a></li>

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
						  	<p><b><a href="/web/applist!toApp.action?appCode=${app.code }">${app.name }</a><font class="redfont">${app.language }</font></b><br />提供者：${app.thirdParty }</p>
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
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 