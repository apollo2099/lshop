<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${app.name } - HUA YANG MALL</title>
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
	 		<h1><img src="${resDomain}/tvpaden/res/images/icon02.gif" /><font class="bfont"><a href="/index.html">Home</a>--> <a href="/web/applist!toApplist.action">Apps</a>--> </font>${app.name } </h1>
			
	 	 <div class="app_instr">
		 	<span class="app_instr_l"><img src="${app.appImage}" width="100px" height="100px"/></span>
			<span class="app_instr_r">
				<ul class="instr1">
					<li class="title">${app.name }</li>
					<li>Language：${app.language }</li>
					<li>Provider： ${app.thirdParty }</li>
					<li>Applicable Models：${app.applyModel }</li>
					<li>Applicable version：${app.applyVersion }</li>
					<li>Version：${app.version }</li>
					<li>Size：${app.appSize }</li>
					
				</ul>
				<ul class="instr2">
				<input name="download"  class="btn04" type="button" onclick="javascript:window.open('${app.download }');" style="CURSOR: pointer; "/>	
				</ul>
			</span>						 	
		 </div>
		 
		 <div class="app_details">
		  <h1><span class="s_l">App Info</span></h1>	
			  <p>${app.introduce }</p>		 
		 </div>
		<!--End 應用詳情-->
		
	 </div>
	 <!--End right_Frame-->
</div>
<!--End content-->

<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 