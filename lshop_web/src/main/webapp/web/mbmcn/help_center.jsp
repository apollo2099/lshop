<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana 商城</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp"%>
</head>

<body>

	<header>
		<div class="shopping">
			<div class="shoplebg1">
				<div class="shopicon1">
					<a href="/"></a>
				</div>
			</div>
		</div>
		<div class="top">
			<div class="title" id="title1">
				<h1>帮助中心</h1>
				<span>Enlish</span>
			</div>
			<div class="shopping">
				<div class="shoplebg">
					<div class="shopicon">
						<a href="/web/mall!getShopCartList.action"></a>
					</div>
				</div>
			</div>
		</div>
	</header>
<c:if test="${empty hid}">
<c:foreach items="${objList}" var="obj">
	<article>
		<div class="articbox">
			<h2>${obj[0].name }</h2>
			<div class="listyle">
				<ul>
				<c:foreach items="${obj[1]}" var="lvHelp">
					<li><a href="/help${obj[0].id}.html#M_${lvHelp.id}">${lvHelp.name }</a></li>
				</c:foreach>
				</ul>
			</div>
		</div>
	</article>
</c:foreach>
</c:if>
<c:if test="${not empty hid}">
<article>
<div class="articbox">
	<h2 style="color:#9d9d9d"><a href="/help.html" style="color:#0099ff; text-decoration:underline">帮助中心</a>-->${htitle }</h2>
</div>
<c:foreach items="${lvHelps}" var="help" varStatus="status">
	<div id="M_${help.id}" class="articbox">
	   <h2>${help.name}</h2>
	   <div class="help_content">${help.content }</div>
	 </div>
</c:foreach>
</article>
</c:if>
	<!-- footer -->
	<%@include file="/web/mbmcn/common/footer.jsp"%>
</body>
</html>