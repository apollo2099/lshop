<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>banana商城——bananaTV官方在线销售平台，国内首家倡导家文化的电视盒子</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="banana TV——家文化倡导者，重视对中国传统家庭文化的承袭与创新，倡导快乐、温馨、分享、传承的和谐家文化。商城包括：官方信息，招商动态及bananaTV幼教版、国内标准版，海外华人版系列产品详情介绍。" />
		<meta name="keywords" content="banana TV； banana TV幼教版； 香蕉盒子；  电视盒子； 家文化倡导者； banana TV官网； banana TV商城" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/storeIndex.js"></script>
		<!--[if IE 6]>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/DD_belatedPNG_0.0.8a-min.js"></script>
		<script>DD_belatedPNG.fix('.new_product a span');</script>
		<![endif]-->
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","bmcn_index"); %>
		<%@include file="/web/bmcn/common/header.jsp" %>

		<div id="mainbody">
			<!-- 滚动图片 -->
			<div id="slides" class="banner">
				<div class="bannerImg">
					<div class="slides_container">
						<ad:ad adkey="AD_TVPAD_INDEX"></ad:ad>
					</div>
				</div>
			</div>
		</div>
		<!--end banner-->

		<div class="content">
  			<!-- 商品信息 广告图及商品列表-->
			<st:shopProduct address="false" style="2"></st:shopProduct>

			<div class="content2">
				<st:blog></st:blog>
				<!--end news-->

				<ad:ad adkey="AD_BANANA_ATTENTION"></ad:ad>
				<!--end 关注我们-->
			</div>

		</div>
		<!--end content-->
		
		<ad:ad adkey="AD_TVPAD_INDEX_BOTTOM"></ad:ad>
		
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/html.js"></script>
	</body>
</html>
