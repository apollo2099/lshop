<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>支付成功_TVpad商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${resDomain}/www/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/www/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<script type="text/javascript">
			if (self.frameElement && self.frameElement.tagName == "IFRAME") {
	          window.parent.location.href=location.href;
	          }
	    </script>
		<%@include file="/web/www/common/top.jsp"%>
		<%
		   String out_trade_no=request.getParameter("out_trade_no");
		   if(out_trade_no!=null&&!"".equals(out_trade_no)){
			   request.setAttribute("oid",out_trade_no);
		   }
		%>
		
		
		<!-- 支付成功google广告统计代码 -->
		<ad:ad adkey="AD_TVPAD_STAT_PAY"></ad:ad>
		
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
		<div class="buy">
		  	<div class="message" style="background:#FEFFED;">	
		  	<ul class="title">支付成功</ul>	
				<ul>
					<li class="pp"><img src="${resDomain}/www/res/images/payment_ok.gif" width="118" height="106" /></li>
				  	<li class="wz" style="padding-top:20px;">您的訂單已支付成功，我們會儘快安排發貨，请保持電話通暢<br />訂單編號：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a><br />
				  </li>
				</ul>	
				<ul class="btn"  style="padding-right:100px;"><img src="${resDomain}/www/res/images/btn01.gif" onclick="javascript:location.href='/index.html'"/></ul>
			</div>								
		</div>		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<%@include file="/web/www/common/footer.jsp" %>
		
		
		
		<!-- Google Code for &#20184;&#27454;&#25104;&#21151; Conversion Page -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 978626603;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "4FN9CN_4r1cQq9DS0gM";
var google_remarketing_only = false;
/* ]]> */
</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="//www.googleadservices.com/pagead/conversion/978626603/?label=4FN9CN_4r1cQq9DS0gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
		
	</body>
</html> 
