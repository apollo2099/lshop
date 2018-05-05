<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad海外高清中文直播電視—TVpad官方商城</title>
		<meta name="description" content="TVpad為全球華人提供海量高清中文電視節目，是目前最流行的电视盒子，擁有中文节目直播、點播，时移等多种形式，提供最新最熱中文影视、体育，财经，教育，宗教，港澳臺及日韓經典影視點播。" />
		<meta name="keywords" content="TVpad,看電視直播, 網絡電視機頂盒，中文電視,海外高清中文電視,海外網絡電視,中文電視直播,TVB直播，HKTV直播 TVpad官方商城， 香港电视， H.265 ，小米盒子，四核盒子，TVpad4" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/www/res/js/storeIndex.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/yu.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/tb.js"></script>
		<style type="text/css">
		/*banner*/
		.pro_zs li a:hover .product_pr img{height: 192px;width: 230px;}
		</style>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","www_index"); %>
		<%@include file="/web/www/common/header.jsp" %>
		
		<!--焦点图 -->
		<div id="mainbody">
		<div id="slides" class="banner">
			<div class="bannerImg">
				<div class="slides_container">
					<ad:ad adkey="AD_TVPAD_INDEX"></ad:ad>
				</div>
			</div>
		</div>
		</div>
		<!--焦点图 END--> 
		
		<div class="cantian">
			<div class="sc_main">
			<div class="w_sc_main_left">
			<!-- 商品信息 广告图及商品列表-->
			<c:if test="${not empty subjectList}">
				<c:foreach items="${subjectList}" var="subj" varStatus="status">
				<div class="cuxiao">
				<h2 class="bt2">
				<div class="titl_bt2">${subj.subjectName}<span class="xie_bt">/</span><span class="pro_tb2">product</span></div>
				<span class="more"><a href="/web/store!showMoreProducts.action?subjectType=${subj.code}&exhibitType=${subj.exhibitType}">更多&gt;&gt;</a></span>  
				</h2>
				<ul class="pro_zs">
				<ad:product products="${subj.products}" currency="USD" maxDisplay="4"></ad:product>
				</ul>
				</div>
				</c:foreach>
			</c:if>
			</div>
			</div>
		  	<div class="cb"></div>  
		</div>
		<div class="content_main_box">
		<div class="content_main">
			<div class="news_x">
			  <h1>
		       <div class="tit2_bt2">TVpad最新動態<span class="xie_bt">/</span><span class="pro_tb2">news</span></div>
		       <span class="more"><a href="/blog.html">更多&gt;&gt;</a></span>
		      </h1>
			  <ul>
				<c:if test="${not empty blogs}">
				<c:foreach items="${blogs}" var="blog" varStatus="status">
				<c:if test="${status.index < 3}">
				<li>
					<a href="/blog/blogInfo${blog.id}.html">
					<p class="text">
					<span class="ti_title">${blog.title }
					<c:if test="${status.index == 0}">
					<strong>NEW</strong>
					</c:if>
					</span>
					<span class="ti_detail">${blog.intor}</span>
					</p>
					<p class="date"><fmt:formatDate value="${blog.createTime}" pattern="yyyy-MM-dd"/></p>
					<div class="clear"></div>
					</a>
				</li>
				</c:if>
				</c:foreach>
				</c:if>
			  </ul>
			</div>
			<!-- 视频展播 -->
			<div class="zmt_chan">
			<ad:ad adkey="AD_TVPAD_RIGHT1"></ad:ad>
			</div>
		</div>
		</div>
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	    <script type="text/javascript" src="${resDomain}/www/res/js/html.js"></script>
	</body>
</html>
