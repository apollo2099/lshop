<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Sales of TVpad Products, Discount Package and Other Accessories-HUA YANG MALL</title>
		<meta name="description" content="HUA YANG E-mall sells various Apps and parts of TVpad including PLC modem, TVpad remote, charger, Apps packages,etc., which meets the needs of various users. For wonderful TVpad experience, please start from HUA YANG E-mall." />
		<meta name="keywords" content="TVpad M121S, PLC Modem, TVpad Remote, TVpad Package, TVpad App, TVpad Network TV STB, TVpad Parts" />
		<!-- 加载公共JS文件 -->
		<%@include file="/web/tvpaden/common/top.jsp" %>
		<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
		<link href="/res/css/layout.css" rel="stylesheet" type="text/css" />
		<script src="/res/js/yu.js" type="text/javascript"></script>
		<script src="/res/js/tb.js" type="text/javascript"></script>	
	</head>
	
	<body>
		<!-- 获取店铺头部文件 -->
		<% request.setAttribute("navFlag","tvpaden_index"); %>
		<%@include file="/web/tvpaden/common/header.jsp" %>
		
		<!--ad-->
		<ad:ad adkey="AD_LOCATION_APP"></ad:ad>
		
		<div class="content_main">
			<!--left_Frame-->
			<div class="left_frame">
				<!--left_店铺信息-->
				<div class="cm_n_1">
		        	<h3>Store Info</h3>
					<ul class="xinxi_ul">
					  <li class="xinxi"><p class="x1">Name：</p><p class="x2"><span><a href="http://${lvStore.domainName }/index.html">${lvStore.name }</a></span></p></li>
					  <%-- <li class="xinxi"><p class="x1">District：</p><p class="x2"><a href="${storeDomain }/web/store!toShopList.action?continentCode=${lvStore.continentCode }&countryCode=${lvStore.countryCode }">${lvStore.country }</a>&nbsp;-&nbsp;<a href="${storeDomain }/web/store!toShopList.action?continentCode=${lvStore.continentCode }&countryCode=${lvStore.countryCode }&cityCode=${lvStore.cityCode }">${lvStore.city }</a></p></li>--%>
					  <c:if test="${not empty lvStore.serviceTel }"><li class="xinxi"><p class="x1">Tel.：</p><p class="x2">${lvStore.serviceTel }</p></li></c:if>
					  <c:if test="${not empty lvStore.address }"><li class="xinxi"><p class="x1">Address：</p><p class="x2">${lvStore.address}</p></li></c:if>
					  <c:if test="${not empty lvStore.servicePromise }"><li class="xinxi"><p class="x1">Commitment：</p><p class="x2">${lvStore.servicePromise }</p></li></c:if>
					  <li class="xinx2"><img src="/res/images/chengnuo.gif" /></li>
					</ul>
		         </div>
				<!--left_app-->
				<app:app></app:app>
				<!--left_ad-->
				<ad:ad adkey="AD_LOCATION_LEFT"></ad:ad>
			</div>
			 
			 <div class="right_frame">
			 	<c:if test="${not empty pList}">
			 		<c:foreach items="${pList}" var="p" varStatus="status">
			 			<c:if test="${not empty p.list}">
			 			<!--产品列表-->
			 			<c:if test="${status.count==1}">
			 				<div class="app_list">
				 				<h1><span class="s_l">${p.title }</span></h1>
								<ul>
								<c:foreach items="${p.list}" var="firstProduct" varStatus="sta">
										<c:if test="${sta.count%4==1}">
										<ul>
											<li>
												<a href="/web/product!toProduct.action?pcode=${firstProduct.code }"><img src="${firstProduct.pimage }" border="0" width="160" height="130"/></a>
												<p><a href="/web/product!toProduct.action?pcode=${firstProduct.code }">${firstProduct.productName }</a><br />
										      		Price:<font class="redfont">USD ${firstProduct.price  }</font></p>
											</li>
										</c:if>
										<c:if test="${sta.count%4!=1}">
											<li>
												<a href="/web/product!toProduct.action?pcode=${firstProduct.code }"><img src="${firstProduct.pimage }" border="0" width="160" height="130"/></a>
												<p><a href="/web/product!toProduct.action?pcode=${firstProduct.code }">${firstProduct.productName }</a><br />
										      		Price:<font class="redfont">USD ${firstProduct.price  }</font></p>
											</li>
											<c:if test="${sta.count%4==0}"></ul></c:if>
										</c:if>
		
								</c:foreach>
								</ul>						
				 			</div>
			 			</c:if>
			 			 <!--產品頁 配件列表-->
			 			 <c:if test="${status.count!=1}">
			 			  	<div class="app_list">
							 	<h1><span class="s_l">${p.title }</span></h1>		
									<ul>
									<c:foreach items="${p.list}" var="product" varStatus="sta">
										<c:if test="${sta.count%4==1}">
										<ul>
											<li>
												<a href="/web/product!toProduct.action?pcode=${product.code }"><img src="${product.pimage }" border="0" width="160" height="130"/></a>
										  		<p><a href="/web/product!toProduct.action?pcode=${product.code }">${product.productName }</a><br />
									      		Price:<font class="redfont">USD ${product.price  }</font></p>
									      	</li>
										</c:if>
										<c:if test="${sta.count%4!=1}">
											<li>
												<a href="/web/product!toProduct.action?pcode=${product.code }"><img src="${product.pimage }" border="0" width="160" height="130"/></a>
										  		<p><a href="/web/product!toProduct.action?pcode=${product.code }">${product.productName }</a><br />
									      		Price:<font class="redfont">USD ${product.price  }</font></p>
									      	</li>
									      	<c:if test="${sta.count%4==0}"></ul></c:if>
										</c:if>
									</c:foreach>
									</ul>		
							 </div>
			 			 </c:if>
			 			 </c:if>
			 		</c:foreach>
			 	</c:if>
			 </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->
		
		<!-- footer-->
		<%@include file="/web/tvpaden/common/footer.jsp" %>
	
	</body>
</html> 