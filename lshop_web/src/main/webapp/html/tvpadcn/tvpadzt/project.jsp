<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TVpad產品詳情專題介紹_華揚商城</title>
	<meta name="description" content="TVpad Android系統智能機頂盒讓你的電視不只是電視，7*24小時收看港澳、台灣、大陸、國際主流直播，超過10万部高清影片即點既播，頂級應用供海外華人獨享，一次購買終身享用，真正尊享SMPTE標準HD畫質體驗。" />
	<meta name="keywords" content="TVpad,TVpad網絡電視機頂盒， TVpad中文電視, TVpad M121S,網絡中文電視,TVpad Android, TVpad 智能機頂盒，TV網絡電視直播" />
	<%@include file="/web/tvpadcn/common/top.jsp" %>
	<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${resDomain}/tvpadcn/res/js/cookie.js"></script>
	<script type="text/javascript" language="javascript"> 
		//下方的iframe显示
		function iFrameHeight() { 
			var ifm= document.getElementById("iframepage"); 
			var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument; 
			if(ifm != null && subWeb != null) { 
				ifm.height = subWeb.body.scrollHeight; 
			} 
		} 
	
	</script> 
	
	</head>
	<body>
		<!-- top -->
		<% request.setAttribute("navFlag","cn_tvpadzt"); %>
		<%@include file="/web/tvpadcn/common/header.jsp" %>
		
		<div class="project">
			<iframe src="/tvpadzt/products.html"  id="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="iFrameHeight()"   /> </iframe>
		</div>
		<!--End 專題-->
		
		<!-- 底部-->
		<%@include file="/web/tvpadcn/common/footer.jsp" %>
	</body>
</html>
