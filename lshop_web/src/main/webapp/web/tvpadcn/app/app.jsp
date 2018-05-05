<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${app.name }_華揚商城</title>
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
	 		<h1><img src="${resDomain}/tvpadcn/res/images/icon02.gif" /><font class="bfont"><a href="/index.html">首頁</a>--> <a href="/web/applist!toApplist.action">第三方應用</a>--> </font>${app.name } </h1>
			
	 	 <div class="app_instr">
		 	<span class="app_instr_l"><img src="${app.appImage}" width="100px" height="100px"/></span>
			<span class="app_instr_r">
				<ul class="instr1">
					<li class="title">${app.name }</li>
					<li>應用語言：${app.language }</li>
					<li>提 供 者： ${app.thirdParty }</li>
					<li>適用機型：${app.applyModel }</li>
					<li>適用版本：${app.applyVersion }</li>
					<li>版    本：${app.version }</li>
					<li>文件大小：${app.appSize }</li>
					
				</ul>
				<ul class="instr2">
				<input name="download"  class="btn04" type="button" onclick="javascript:window.open('${app.download }');" style="CURSOR: pointer; "/>	
				</ul>
			</span>						 	
		 </div>
		 
		 <div class="app_details">
		  <h1><span class="s_l">应用介绍</span></h1>	
			  <p>${app.introduce }</p>		 
		 </div>
		<!--End 應用詳情-->
		
	 </div>
	 <!--End right_Frame-->
</div>
<!--End content-->

<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 