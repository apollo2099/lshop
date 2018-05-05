<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad產品、優惠套餐及相關配件銷售_華揚商城</title>
		<meta name="description" content="華揚網絡商城彙集各種APP應用和TVpad配件等，電力貓、遙控器、充電器、各種app應用套餐應有盡有，適應每一個不同需求用戶的口味，體驗精彩TVpad，從華揚網絡商城開始。" />
		<meta name="keywords" content="TVpad M121S，電力貓，TVpad遙控器，TVpad套餐，TVpad APP應用，TVpad網絡電視機頂盒，TVpad配件" />
		<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
		<link href="/res/css/layout.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/tvpadcn/common/top.jsp" %>
		<script src="/res/js/yu.js" type="text/javascript"></script>
		<script src="/res/js/tb.js" type="text/javascript"></script>
	</head>
	
	<body>
		<!-- 获取店铺头部文件 -->
		<% request.setAttribute("navFlag","cn_index"); %>
		<%@include file="/web/tvpadcn/common/header.jsp" %>
		
		<!--ad-->
		<ad:ad adkey="AD_LOCATION_APP"></ad:ad>
		
		<div class="content_main">
			<!--left_Frame-->
			<div class="left_frame">
				<!--left_店铺信息-->
				<div class="cm_n_1">
		        	<h3>店鋪信息</h3>
					<ul class="xinxi_ul">
					  <li class="xinxi"><p class="x1">店 鋪 名：</p><p class="x2"><span><a href="http://${lvStore.domainName }/index.html">${lvStore.name }</a></span></p></li>
					  <%-- <li class="xinxi"><p class="x1">所屬地區：</p><p class="x2"><a href="${storeDomain }/web/store!toShopList.action?continentCode=${lvStore.continentCode }&countryCode=${lvStore.countryCode }">${lvStore.country }</a>&nbsp;-&nbsp;<a href="${storeDomain }/web/store!toShopList.action?continentCode=${lvStore.continentCode }&countryCode=${lvStore.countryCode }&cityCode=${lvStore.cityCode }">${lvStore.city }</a></p></li>--%>
					  <c:if test="${not empty lvStore.serviceTel }"><li class="xinxi"><p class="x1">服務電話：</p><p class="x2">${lvStore.serviceTel }</p></li></c:if>
					  <c:if test="${not empty lvStore.address }"><li class="xinxi"><p class="x1">店鋪地址：</p><p class="x2">${lvStore.address}</p></li></c:if>
					  <c:if test="${not empty lvStore.servicePromise }"><li class="xinxi"><p class="x1">服務承諾：</p><p class="x2">${lvStore.servicePromise }</p></li></c:if>
					  <li class="xinx2"><img src="/res/images/chengnuo.gif" /></li>
					</ul>
		         </div>
				<!--left_app-->
				<app:app></app:app>
				<!--left_ad-->
				<ad:ad adkey="AD_LOCATION_LEFT"></ad:ad>
			</div>
			 
			 <div class="right_frame">
			 	<div class="product_ad_list">
			 	</div>
			 	<c:if test="${not empty pList}">
			 		<c:foreach items="${pList}" var="p" varStatus="status">
			 			<!--产品列表-->
			 			<c:if test="${status.count==1}">
			 				<div class="pj_list">
				 				<h1><span class="s_l">${p.title }</span></h1>
								<ul>
								<c:foreach items="${p.list}" var="firstProduct" varStatus="sta">
										<c:if test="${sta.count%4==1}">
										<ul>
											<li class="pre">
												<a href="/web/product!toProduct.action?pcode=${firstProduct.code }"><img src="${firstProduct.pimage }" border="0" width="160" height="130"/></a>
												<p><a href="/web/product!toProduct.action?pcode=${firstProduct.code }">${firstProduct.productName }</a><br />
										      		價格:<font class="redfont">USD ${firstProduct.price  }</font></p>
											</li>
										</c:if>
										<c:if test="${sta.count%4!=1}">
											<li>
												<a href="/web/product!toProduct.action?pcode=${firstProduct.code }"><img src="${firstProduct.pimage }" border="0" width="160" height="130"/></a>
												<p><a href="/web/product!toProduct.action?pcode=${firstProduct.code }">${firstProduct.productName }</a><br />
										      		價格:<font class="redfont">USD ${firstProduct.price  }</font></p>
											</li>
											<c:if test="${sta.count%4==0}"></ul></c:if>
										</c:if>
		
								</c:foreach>
								</ul>						
				 			</div>
			 			</c:if>
			 			 <!--產品頁 配件列表-->
			 			 <c:if test="${status.count!=1}">
			 			  	<div class="pj_list">
							 	<h1><span class="s_l">${p.title }</span></h1>		
									<ul>
									<c:foreach items="${p.list}" var="product" varStatus="sta">
										<c:if test="${sta.count%4==1}">
										<ul>
											<li class="pre">
												<a href="/web/product!toProduct.action?pcode=${product.code }"><img src="${product.pimage }" border="0" width="160" height="130"/></a>
										  		<p><a href="/web/product!toProduct.action?pcode=${product.code }">${product.productName }</a><br />
									      		價格:<font class="redfont">USD ${product.price  }</font></p>
									      	</li>
										</c:if>
										<c:if test="${sta.count%4!=1}">
											<li>
												<a href="/web/product!toProduct.action?pcode=${product.code }"><img src="${product.pimage }" border="0" width="160" height="130"/></a>
										  		<p><a href="/web/product!toProduct.action?pcode=${product.code }">${product.productName }</a><br />
									      		價格:<font class="redfont">USD ${product.price  }</font></p>
									      	</li>
									      	<c:if test="${sta.count%4==0}"></ul></c:if>
										</c:if>
									</c:foreach>
									</ul>		
							 </div>
			 			 </c:if>
			 		</c:foreach>
			 	</c:if>
		
				<!--產品頁 应用列表-->
				<!--<c:if test="${not empty aList}">
				 	<c:foreach items="${aList}" var="a" varStatus="status">
					  <div class="app_list">
					 	<h1><span class="s_l">${a.title }</span></h1>		
							<ul>
							<c:foreach items="${a.list}" var="app">
								<li>
									<a href="/web/applist!toApp.action?appCode=${app.code }"><img src="${app.appImage }" width="100" height="100" border="0" /></a>
								  	<p><b><a href="/web/applist!toApp.action?appCode=${app.code }">${app.name }</a><font class="redfont">${app.language }</font></b><br />提供者：${app.thirdParty }</p>
								</li>
							</c:foreach>
							</ul>					
						</div>
				 	</c:foreach>
			 	</c:if>-->
			 </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->
		
		<!-- footer-->
		<%@include file="/web/tvpadcn/common/footer.jsp" %>
		
	</body>
</html> 