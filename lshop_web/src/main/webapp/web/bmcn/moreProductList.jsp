<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>banana TV——家文化倡导者，banana TV幼教版，国内标准版，海外华人版等系列产品</title>
		<meta name="description" content="banana TV家文化倡导者，国内首家倡导家文化的电视盒子。 banana TV幼教版、国内标准版、海外华人版等系列产品详情介绍。" />
		<meta name="keywords" content="banana TV ；  banana TV幼教版；电视盒子中的育儿专家； 香蕉盒子幼教版； 电视盒子" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
	
		<div class="content">
		  	<div class="hot_accessories">
		  	 	<h1> ${subject.subjectName }</h1>
		  	 	<c:foreach items="${objList}" var="obj" varStatus="status">
		  	 		<c:if test="${status.count%4==1}"><ul></c:if>
		  	 			<li <c:if test="${status.count%4==0}">class="no_mg"</c:if>>
		  	 				<a href="${obj[0].url}"><img src="${obj[0].pimage }" border="0" /><span>${obj[0].productName }<br />
			      			<font class="blue2" style="font-size:14px;">${obj[1].currency} ${obj[0].productPrice}</font></span></a>
			      		</li>
		  	 		<c:if test="${status.count%4==0 || status.count==page.totalCount}"></ul></c:if>
		  	 	</c:foreach>
		  	 </div>
		    	
			<c:if test="${page.totalPage>1}">
			  	<u:newPage href="/web/store!showMoreProducts.action?subjectType=${subject.code}&exhibitType=${subject.exhibitType}&page.pageNum=@"></u:newPage>
			  	<script type="text/javascript">
					function toPage(){
						var pageNum = $("#pageValue").val();
					   	window.location.href="/web/store!showMoreProducts.action?subjectType=${subject.code}&exhibitType=${subject.exhibitType}&page.pageNum="+pageNum;
					}
			 	</script>
		 	</c:if>
			<!--end 分页-->
			
		   <div class="cb"></div>
		</div>
		<!--end content-->
		
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html>