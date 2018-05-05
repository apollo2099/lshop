<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad商城</title>
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
		
		<script type=text/javascript src="${resDomain}/www/res/js/storeNav.js"></script>
		<script type=text/javascript src="${resDomain}/www/res/js/storeIndex.js"></script>
		<script type="text/javascript">
			$(function(){
				if(${not empty continentCode and not empty countryCode and not empty cityCode}){
					clickCity('${continentCode}','${countryCode}','${cityCode}');
				}else if(${not empty continentCode and not empty countryCode}){
					clickCountry('${continentCode}','${countryCode}');
				}else if(${not empty continentCode}){
					clickContinent('${continentCode}');
				}
			});
			
		</script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>
		
		<div class="sc_main">
			<p class="welcome">請選擇您身邊的TVpad專賣店，如果您所在地區沒有店鋪可選擇  <a href="http://www.hmall.hk/" target="_blank">TVpad官方店鋪</a></p>
		 	<div class="guide_main1">
			 	<!-- 洲 -->
			    <div class="user_center_menu" id="continent">
			    	<ul>
			    		<c:foreach items="${continentList}" var="continent">
			    			<li id="continent${continent.id }"><a href="/web/store!toShopList.action?continentCode=${continent.code}">${continent.areaName }</a></li>
			    		</c:foreach>
			    	</ul>
			    </div>
				<div class="guide_m_diqu">
				 	<!-- 国家 -->
				  	<dl id="country"></dl>
				  	<!-- 城市 -->
				  	<dl class="chengshi" id="city"></dl>
				</div>
				<!-- 店铺 -->
				<div class="dianpu" id="shop">
					<c:if test="${empty storeList}">
						<div class="search_guide_none"> 
						   <img src="${resDomain}/www/res/images/biaoqing.gif" />抱歉！沒有搜索到相符合的店鋪！
						</div>
					</c:if>
					<c:if test="${not empty storeList}">
						<c:foreach items="${storeList}" var="store">
							<ul onmouseover="showUL(this);" onmouseout="hideUL(this);">
							  <li>
							   <p class="img_dianpu"><a href="javascript:saveCookieForStore('${store.code }','${store.name }','${store.domainName }');"><img src="${store.logo }" border="0" width="120px" height="40px"/></a><c:if test="${store.isHot==1}"><img src="${resDomain}/www/res/images/hot.gif"  class="hot"/></c:if> </p>
							   <p class="text">
							    <span class="name"><a href="javascript:saveCookieForStore('${store.code }','${store.name }','${store.domainName }');">${store.name }</a></span>
							    <span class="country1"><a href="/web/store!toShopList.action?continentCode=${store.continentCode}&countryCode=${store.countryCode }">${store.country }</a></span>
								<span class="city1"> - <a href="/web/store!toShopList.action?continentCode=${store.continentCode}&countryCode=${store.countryCode }&cityCode=${store.cityCode }">${store.city }</a></span>
							   </p>
							  </li>
							 </ul>
						</c:foreach>
					</c:if>
					<div class="cb"></div>	
				</div>
		
				<c:if test="${not empty mark}">
					<c:if test="${page.totalPage>1}">
					  	<u:newPage href="/web/store!toShopList.action?continentCode=${continentCode}&countryCode=${countryCode }&cityCode=${cityCode }&page.pageNum=@"></u:newPage>
					  	<script type="text/javascript">
							function toPage(){
								var pageNum = $("#pageValue").val();
							   	window.location.href="/web/store!toShopList.action?continentCode=${continentCode}&countryCode=${countryCode }&cityCode=${cityCode }&page.pageNum="+pageNum;
							
							}
					 	</script>
				 	</c:if>
				</c:if>
				<c:if test="${empty mark}">
					<c:if test="${page.totalPage>1}">
						  <u:newPage href="/web/store!searchStores.action?str=${searchContent}&countryCode=${countryCode }&page.pageNum=@"></u:newPage>
						  <script type="text/javascript">
							function toPage(){
								var pageNum = $("#pageValue").val();
							   	window.location.href="/web/store!searchStores.action?str=${searchContent}&page.pageNum="+pageNum;
							
							}
						 </script>
					 </c:if>
				</c:if>
				<div class="cb"></div>  
		  	</div>
		 	<div class="cb"></div>	
		</div>
		
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html>
