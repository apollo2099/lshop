<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>取消交易_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmcn/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp" %>
		<div class="buy">
	  		<div class="message" style="background:#FEFFED;">		
				<ul>
					<li class="pp"><img src="${resDomain}/bmcn/res/images/payment_fail.gif" width="118" height="106" /></li>
					<li class="wz" style="padding-top:20px;">您的订单${Ref}取消当前支付交易。<br /><br />
			  	    <a href="/web/userOrder!toConfirmPay.action?oid=${oid}&shopFlag=${shopFlag }">重新支付</a><br />
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
