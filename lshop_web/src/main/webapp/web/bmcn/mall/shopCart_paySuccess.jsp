<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>支付成功_banana商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${resDomain}/bmcn/res/css/buy.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<script type="text/javascript">
			if (self.frameElement && self.frameElement.tagName == "IFRAME") {
	          window.parent.location.href=location.href;
	          }
	    </script>
		<%@include file="/web/bmcn/common/top.jsp"%>
		<%
		   String out_trade_no=request.getParameter("out_trade_no");
		   if(out_trade_no!=null&&!"".equals(out_trade_no)){
			   request.setAttribute("oid",out_trade_no);
		   }
		%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		<div class="buy">
		  	<div class="message">	
		  		<ul class="title">支付成功</ul>	
				<ul>
					<li class="pp"><img src="${resDomain}/bmcn/res/images/payment_ok.gif" width="118" height="106" /></li>
				  	<li class="wz" style="padding-top:20px;">您的订单已支付成功，我们会尽快安排发货，清保持电话通畅<br />
				  	  订单编号：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a><br />
				  </li>
				</ul>	
				<ul class="btn">
                  <input  type="button" class="btn05"  onclick="javascript:location.href='/index.html'" value="继续购物" />
                </ul>
			</div>								
		</div>		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<%@include file="/web/bmcn/common/footer.jsp" %>
	</body>
</html> 
