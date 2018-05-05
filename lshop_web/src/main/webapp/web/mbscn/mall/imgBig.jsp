<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana商城_商品详情</title>
<!-- 加载公共JS -->
<%@include file="/web/mbscn/common/top.jsp"%>
</head>

<body>
	<header>
		<div class="shopping">
			<div class="shoplebg1">
				<div class="shopicon1">
					<a href="${storeDomain}"></a>
				</div>
			</div>
		</div>
		<div class="top">
			<div class="title" id="title1">
				<h1>商品详情</h1>
				<span>Enlish</span>
			</div>
			<div class="shopping">
				<div class="shoplebg">
					<div class="shopicon">
						<a href="javascript:toCar('${storeDomain}');"></a><span
							id="shopCartNum" style="display: none;"></span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<article>
		<div class="bigimg">
			<div class="imgconts">1/5</div>
			<div class="imgflag">
				<img src="<%=request.getParameter("pimage")%>" width="90%" />
			</div>
			<div class="imgreture">
				<a href="javascript:history.go(-1);"></a>
			</div>
		</div>
	</article>

	<!-- 分享 -->
	<%@include file="/web/mbscn/common/share.jsp"%>

	<!-- footer -->
	<%@include file="/web/mbscn/common/footer.jsp"%>

</body>
</html>
