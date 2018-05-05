<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad - HD Live TV Contents for Overseas Chinese- TVpad Mall</title>
		<meta name="description" content="As one of the most popular and acclaimed streaming players, TVpad which specially designed for overseas Chinese provides worldwide Chinese with tons of live, VOD, and time-shifted Chinese TV contents, covering the latest Chinese movies, sports news, financial news, education, religion, and videos on demand from HK, Taiwan and Macao." />
		<meta name="keywords" content="TVpad, live TV, network set-top box, Chinese TV contents, overseas Chinese HD TV contents, oversea Chinese TV, Chinese live TV, TVB, HKTV, TVpad official store, TVpad Mall, Hong Kong TV, H.265, MI TV box, quad-core streaming player, TVpad4 OTT box, TVpad4." />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/en/res/js/storeIndex.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","en_index"); %>
		<%@include file="/web/en/common/header.jsp" %>
		
		<!--焦点图 -->
		<div id="mainbody" class="index_bg01" style="background-repeat: repeat;">
		<div id="slides" class="banner">
			<div class="bannerImg">
				<div class="slides_container">
					<ad:ad adkey="AD_TVPAD_INDEX"></ad:ad>
				</div>
			</div>
		</div>
		</div>
		<!--焦点图 END-->
		<!-- 商品栏目 -->
		<c:if test="${not empty subjectList}">
		<c:foreach items="${subjectList}" var="subj" varStatus="ss">
		<c:if test="${subj.exhibitType == 1}"><!-- 栏目商品图片 -->
		<div class="tvp_en">
			<div class="tvp_en_inner">
				<ul>
				<c:if test="${not empty subj.products}">
				<c:foreach items="${subj.products}" var="prod" varStatus="ps">
					<c:if test="${ps.index < 3 }">
					<li><a href="${prod.url }" title=""><img src="${prod.pimageAd }" width="320" height="159"></a></li>
					</c:if>
				</c:foreach>
				</c:if>
				</ul>
			</div>
		</div>
		</c:if>
		</c:foreach>
		</c:if>
		<!-- 商品栏目 end-->
		<!-- footer -->
		<div class="bottom_en">
			<div class="bottom_en_iner">
				<div class="content_en">Copyright © 2007-2015 mtvpad.com All Rights Reserved</div>
				<div class="help_cu"><a href="http://en.mtvpad.com/help43.html#M_184">Help</a> | <a href="http://en.mtvpad.com/help15.html#M_68">About Us</a></div>
				<div class="clear"></div>
			</div>
		</div>
		<script type='text/javascript' src='http://chat.53kf.com/kf.php?arg=lulucute&style=3'></script><div style='display:none;'><a href='http://www.53kf.com'>在线客服系统</a></div>
		<!--不显示下面按钮-->
		<script>
		var kf53_in = setInterval(function(){
			if($('#hz6d_mnkh_talking').length>0){
				$('#hz6d_mnkh_talking').remove();
				$('#hz6d_mnkh_min').remove();
				window.clearInterval(kf53_in);
				kf53_in = null;
			}
		}, 300);
		</script>
	</body>
</html>
