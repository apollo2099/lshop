<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>支付失败_banana商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${resDomain}/bmcn/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmcn/res/css/buy.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		<%
		   String out_trade_no=request.getParameter("out_trade_no");
		   if(out_trade_no!=null&&!"".equals(out_trade_no)){
			   request.setAttribute("oid",out_trade_no);
		   }
		%>
		<div class="buy">
	  		<div class="message">	
	  		<ul class="title">支付失败</ul>	
				<ul>
					<li class="pp"><img src="${resDomain}/bmcn/res/images/payment_fail.gif" width="118" height="106" /></li>
			  	    <li class="wz" style="padding-top:20px;">您的订单${oid}支付失败，请重新下单。<br />
			  	                失败原因：${param.error_code}<br />
				      <a href="/web/shopCart!toPayOrder.action?oid=${oid}" target="_blank">重新支付</a><br />
				  </li>
				</ul>	
				<ul class="btn">
                  <input  type="button" class="btn05"  onclick="javascript:location.href='/index.html'" value="继续购物" />
                </ul>
			</div>								
		</div>	
	
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>		
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 
