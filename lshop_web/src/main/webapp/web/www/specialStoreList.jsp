<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad商城</title>
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>
		
		<div class="sc_main">
			<div class="sc_main_left">
			    <div class="sc_product">
				 	<ad:ad adkey="AD_TVPAD_SPECIAL"></ad:ad>
				 
					<c:foreach items="${list}" var="m">
					 	<c:if test="${not empty m.storeList}">
						 	<div class="cuxiao">
						 	  	<h2 class="bt2">
						     		<p class="cx">${m.storeType.storeTypeName }</span></p>
						     	</h2>
						    	<ul class="dp_zs">
						    		<c:foreach items="${m.storeList}" var="store">
						    			<li><a href="${store.storeUrl}"><img src="${store.storeLogo}" border="0" width="120px" height="58px"/></a><br/><a href="${store.storeUrl }">${store.storeName }</a></li>
						    		</c:foreach>
								  	<div class="cb"></div>
						  		</ul>
						  	</div>
					  </c:if>
					</c:foreach>
				 
				</div>
			</div>
		  	<!--End left-->
		  
			<div class="sc_main_right">
				<!-- 华扬入口 -->
				<ad:ad adkey="AD_TVPAD_ENTER"></ad:ad>
				<!-- 右侧第一个广告 -->
				<ad:ad adkey="AD_TVPAD_RIGHT1"></ad:ad>
				<!-- 右侧第二个广告 -->
				<ad:ad adkey="AD_TVPAD_RIGHT2"></ad:ad>
			</div>
		  	<!--End right-->
		  
			<div class="cb"></div>  
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html>
