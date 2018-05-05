<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>banana TV</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="banana TV是首款针对海外华人免费提供海量中文电视的智能网络电视机顶盒，搭载Android智能系统，用户可自行下载安装丰富的第三方APP应用，轻松畅享各类精彩纷呈的直播、点播节目与百万部免费电影资源及趣味游戏，为家庭齐聚欢乐。banana TV商城提供banana TV产品及各类配件支持，欢迎选购。" />
		<meta name="keywords" content="banana TV，TV box，banana TV中文电视，华语电视，华人电视，海外华人电视，免费中文电视，banana TV网络电视机顶盒，无月租网络电视，无合约网络电视，安卓网络电视，中文电视直播" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/storeIndex.js"></script>
		<!--[if IE 6]>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/DD_belatedPNG_0.0.8a-min.js"></script>
		<script>DD_belatedPNG.fix('.new_product a span');</script>
		<![endif]-->
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","bmen_index"); %>
		<%@include file="/web/bmen/common/header.jsp" %>

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
				<st:blog language="en"></st:blog>
				<!--end news-->

				<ad:ad adkey="AD_BANANA_ATTENTION"></ad:ad>
				<!--end 关注我们-->
			</div>

		</div>
		<!--end content-->
		
		<ad:ad adkey="AD_TVPAD_INDEX_BOTTOM"></ad:ad>
		
		<!-- footer -->
		<%@include file="/web/bmen/common/footer.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/html.js"></script>
	</body>
</html>
