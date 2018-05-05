<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>bananaTV面向全球诚招区域经销商和OTT智能互动电视系统合作伙伴</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="banana TV立足于新兴的互联网行业，技术研发实力雄厚，产品优势明显，利润回报丰厚，面向全球招募区域经销商和智能局域网电视合作运营商。" />
		<meta name="keywords" content="bananaTV招商；banan TV幼教版招商； 香蕉盒子招商；电视机顶盒；酒店智能电视互动系统； OTT智能电视系统解决方案" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
		<link href="${resDomain}/bmcn/res/css/merchants.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","bmcn_merchants"); %>
		<%@include file="/web/bmcn/common/header.jsp" %>
		<div class="merchants_banner"><img src="${resDomain}/bmcn/res/images/merchants/merchants_banner.jpg" /></div>
		<!--end banner-->

		<div class="merchants_content">
		  <ul>
		    <li>
			  <p><img src="${resDomain}/bmcn/res/images/merchants/merchants_content_img1.jpg" /> <a href="/btvfbd.html " target="_blank">点击进入</a></p>
			  <p class="img_bg"><span><a href="/btvfbd.html" target="_blank"><img src="${resDomain}/bmcn/res/images/merchants/merchants_content_img3.jpg" border="0" /></a></span></p>
			  <p><font class="merchants_font bd">没有互联网？一样收看家乡实时高清电视！</font><br />面向全球诚招智能局域网电视<font class="bd">代理商、运营商</font></p>
			</li>
			<li>
			  <p><img src="${resDomain}/bmcn/res/images/merchants/merchants_content_img1.jpg" /> <a href="/btvott.html" target="_blank">点击进入</a></p>
			  <p class="img_bg"><span><a href="/btvott.html" target="_blank"><img src="${resDomain}/bmcn/res/images/merchants/merchants_content_img4.jpg" border="0" /></a></span></p>
			  <p><font class="merchants_font bd">banana TV全球经销商招募</font><br />财富一触即发！</p>
			</li>
		  </ul>
		</div>
		
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	</body>
</html>
