<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad2定制版智能系統網路智能機頂盒</title>
		<link href="${resDomain}/www/res/css/css.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
		<script type="text/javascript" language="javascript"> 
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
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","www_tvpadzt"); %>
		<%@include file="/web/www/common/header.jsp"%>
		
		<div class="project">
			<iframe id="iframepage" src="/tvpadzt/products.html"  frameborder="no"  SCROLLING="NO" width="100%"  BORDER="0" onLoad="iFrameHeight()" /> </iframe>
		
		</div>
		<!--End 專題-->
		
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	</body>
</html>
