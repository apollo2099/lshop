<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Pay Success_TVpad Mall</title>
		<link href="${resDomain}/en/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/en/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<script type="text/javascript">
			if (self.frameElement && self.frameElement.tagName == "IFRAME") {
	          window.parent.location.href=location.href;
	          }
	    </script>
		<%
		   String out_trade_no=request.getParameter("out_trade_no");
		   if(out_trade_no!=null&&!"".equals(out_trade_no)){
			   request.setAttribute("oid",out_trade_no);
		   }
		%>
		
		<!-- 支付成功google广告统计代码 -->
		<ad:ad adkey="AD_TVPAD_STAT_PAY"></ad:ad>
		
		<%@include file="/web/en/common/top.jsp" %>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div class="buy">	
			<div class="buy_top"><img src="${resDomain}/en/res/images/shopping01.gif" /></div>
			<div class="buy_lc04"></div>
			<div class="message">
				<ul class="title">Submit order successfully</ul>	
				<ul>
					<li class="pp"><img src="${resDomain}/en/res/images/suc.gif" /></li>
					<li class="wz">Your order has been successfully submitted, we'll arrange a delivery as soon as possible, please keep your phone available.<br />Order No.：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a><br />
				</ul>	
				<ul class="btn"><a href="/index.html"><img src="${resDomain}/en/res/images/shopping.gif" border="0" /></a></ul>
			</div>															
		</div>
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<%@include file="/web/en/common/footer.jsp" %>
		
		
		
		
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
