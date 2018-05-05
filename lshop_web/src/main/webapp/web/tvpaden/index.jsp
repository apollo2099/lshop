<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HD TVpad of Custom Android System-HUA YANG MALL</title>
<meta name="description" content="TVpad, the most popular watching model overseas, provides free massive HD Chinese TV programs for global Chinese including Chinese live TV programs, VOD movies, sports news special, hot music, etc. It covers the newest and hottest Chinese programs and more than 100,000 classic VOD movies from mainland, Hong Kong, Macao, Japan and Korea." />
<meta name="keywords" content="TVpad, TVpad live TV, TVpad network STB, TVpad Chinese programs, free network Chinese programs, Chinese TV, overseas HD Chinese TV, overseas network TV, Chinese live TV" />
<% request.setAttribute("navFlag","toIndex"); %>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
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

<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>


<!--ad-->
<ad:ad adkey="AD_LOCATION_INDEX"></ad:ad>

<div class="plist_title_bg">
	<div class="plist_title"><span><img src="${resDomain}/tvpaden/res/images/index_title.gif" /></span><ad:ad adkey="AD_LOCATION_SUBJECT_WORD"></ad:ad></div>
</div>

<ad:ad adkey="AD_LOCATION_SUBJECT"></ad:ad>
<!--End plist-->

<!-- 底部-->
<%@include file="common/footer.jsp" %>
</body>
</html>
