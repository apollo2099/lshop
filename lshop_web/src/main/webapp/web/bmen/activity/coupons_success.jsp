<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad2定制版智能系統網路智能機頂盒</title>
		<link href="${resDomain}/bmen/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmen/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
			<!-- 成功 -->
			<div class="coupons_f">
				<ul>
			     <li class="wd1">${couponView.activityTitle}</li>
			      <li class="wd3">Congratulations! You got a coupon!</li>
			      <div class="wd4">
			      	<ul>
			      	    <li><span class="w">Promo Code：</span><font >${couponView.couponCode}</font></li>
			        	<li><span class="w">Value：</span><font class="blue2 fontwz">${couponView.faceValue}</font></li>
			            <li><span class="w">Rules of use：</span>1.${couponView.type} ：${couponView.typeProduct}</li> 
			            <li><span class="w">&nbsp;</span>2.Order amount should be no less than ${couponView.limitAmount}</li> 
			            <li ><span class="w">Validity：</span><font class="blue2 fontwz">${couponView.validDate}</font></li>         
			        </ul>
			      </div>
			      
			         <li class="wd2"><input name="" type="button" class="btn05"value="Home" onclick="window.location.href='${storeDomain}/index.html'" /></li>
			    </ul>
			</div>
			<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/bmen/common/footer.jsp" %>
	</body>
</html>