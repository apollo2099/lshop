<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad Streaming Player, Accessories and apps - TVpad Mall</title>
		<meta name="description" content="TVpad Mall provides quality guaranteed TVpad streaming player, TVpad remote, power adapter, promotion package and apps for your purchase. Just start from TVpad Mall and enjoy excellent Chinese TV contents." />
		         <meta name="keywords" content="TVpad remote, package, App, TVpad accessories, HDMI cable, discount, coupon, promotion" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","en_moreProductList"); %>
		<%@include file="/web/en/common/header.jsp" %>
		<div class="sc_main">
		  <div class="sc_main_left">
		    <div class="sc_product">
			  <h2 class="bt3">
			    <p class="home"><img src="${resDomain}/en/res/images/icon02.gif" /></p><p><a href="/index.html">Home</a> > ${subject.subjectName }<span>（${page.totalCount}）</span> </p>
		      </h2>
			  	<c:if test="${exhibitType==2}">
				  	<ul class="pro_zs">
					  	<c:foreach items="${objList}" var="obj">
					  		<li>
					  			<%--<a href="${obj[0].url}"><img src="${obj[0].pimage }" width="158px" height="122px"/><p><span class="mingcheng">${obj[0].productName }</span><span class="price">Price：<span class="price1">USD ${obj[0].productPrice}</span></span><span class="country">${obj[1].country }</span><span class="city"> - ${obj[1].city }</span></p></a> --%>
					  			<a href="${obj[0].url}"><img src="${obj[0].pimage }" width="158px" height="122px"/>
					  			<p><span class="mingcheng">${obj[0].productName }</span>
					  			<span class="price">Price：<span class="price1">USD ${obj[0].productPrice}</span>
					  			<c:if test="${obj[0].isGift==1 }">
					  			<img src='http://res.mtvpad.com/en/res/images/zp_icon.png' width='40' height='22' />
					  			</c:if>
					  			</span></p></a>
				        	</li>
					  	</c:foreach>
					  	<div class="cb"></div>
				  	</ul>
			  	</c:if>
			  	<c:if test="${exhibitType==1}">
				  	<c:foreach items="${objList}" var="obj">
				  		<p class="zq_image"><a href="${obj[0].url}"><img src="${obj[0].pimageAd}" border="0" width="706px" height="220px"/></a></p>
				  	</c:foreach>
			  	</c:if>
		
		    </div>
				<c:if test="${page.totalPage>1}">
				  <u:newPage href="/web/store!showMoreProducts.action?subjectType=${subject.code}&exhibitType=${subject.exhibitType}&page.pageNum=@" language="en"></u:newPage>
				  <script type="text/javascript">
					function toPage(){
						var pageNum = $("#pageValue").val();
					   	window.location.href="/web/store!showMoreProducts.action?subjectType=${subject.code}&exhibitType=${subject.exhibitType}&page.pageNum="+pageNum;
					
					}
				 </script>
			 </c:if>
		</div>
		<!--End left-->
		  
		  <div class="sc_main_right">
		    <!-- 右侧广告 -->
			<ad:ad adkey="AD_TVPAD_MORE_PROD_RIGHT"></ad:ad>
		  </div>
		  <!--End right-->
		  
		<div class="cb"></div>  
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/en/common/footer.jsp" %>

	</body>
</html>