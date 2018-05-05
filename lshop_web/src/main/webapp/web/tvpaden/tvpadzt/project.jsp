<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad Products Center-HUA YANG MALL</title>
		<meta name="description" content="Smart TVpad of custom Android system makes your TV be not just a TV.It provides popular international live programs and programs from Hong Kong, Macao, Taiwan, Mainland 7*24 hours and covers more than 100,000 VOD HD movies. Also, its top Apps support global share for overseas Chinese.Come to enjoy your SMPTE HD experience! Once purchased to life long benifits!" />
		<meta name="keywords" content="TVpad, TVpad network STB，TVpad Chinese TV，TVpad M121S，network Chinese TV，TVpad Android，TVpad smart STB，TVpad live network TV" />
		<!-- top -->
		<%@include file="/web/tvpaden/common/top.jsp" %>
		<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/tvpaden/res/js/cookie.js"></script>
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
		<% request.setAttribute("navFlag","tvpaden_tvpadzt"); %>
		<%@include file="/web/tvpaden/common/header.jsp" %>
		
		 <form id="buyForm" action="" method="post">
		      	<input type="hidden" name="lvShopCart.productCode" value="14b9354e0fea44febf7ff337f4209747"/>
			  	<input type="hidden" name="cookieStr" id="cookieStr" value=""/>
			  	<input type="hidden" id="num" name="lvShopCart.shopNum" value="1"/>
			  	<input type="hidden" id="productId"  value="16"/>
		</form>
		
		<div class="project">
			<iframe src="/tvpadzt/products.html"  id="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="iFrameHeight()"   /> </iframe>
		</div>
		<!--End 專題-->
		
		<!-- 底部-->
		<%@include file="/web/tvpaden/common/footer.jsp" %>
	</body>
</html>
