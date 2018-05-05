<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad安卓定製版高清電視機頂盒_華揚商城</title>
<meta name="description" content="TVpad為全球華人免費提供海量高清中文電視節目，是目前最流行的海外網絡電視觀看形式，覆蓋中文直播電視、影視點播、新聞體育專題、熱門音樂等等。擁有最新最熱中文頻道及10萬中文及港澳臺，日韓經典影視點播。" />
<meta name="keywords" content="TVpad,TVpad電視直播, TVpad網絡電視機頂盒，TVpad中文電視,免費網絡中文電視,chinese tv,海外高清中文電視,海外網絡電視,中文電視直播" />
<%@include file="/web/tvpadcn/common/header.jsp" %>

<!-- top -->
<% request.setAttribute("navFlag","store_index"); %>
<%@include file="/web/tvpadcn/common/top.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/css.css" rel="stylesheet" type="text/css" />
<script>
	$(function(){
		$('#slides').slides({
			preload: true,
			preloadImage: 'img/loading.gif',
			play: 3000,
			pause: 2500,
			hoverPause: true,
			animationStart: function(){
				$('.caption').animate({
					bottom:-35
				},100);
			},
			animationComplete: function(current){
				$('.caption').animate({
					bottom:0
				},200);
				if (window.console && console.log) {
					// example return of current slide number
					console.log(current);
				};
			}
		});
	});
</script>
</head>
<body>

<!--ad-->
<ad:ad adkey="AD_LOCATION_INDEX"></ad:ad>

<div class="plist_title_bg">
	<div class="plist_title"><span><img src="${resDomain}/tvpadcn/res/images/index_title.gif" /></span><ad:ad adkey="AD_LOCATION_SUBJECT_WORD"></ad:ad></div>
</div>

<ad:ad adkey="AD_LOCATION_SUBJECT"></ad:ad>
<!--End plist-->

<!-- 底部-->
<%@include file="common/footer.jsp" %>
</body>
</html>
