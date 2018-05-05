<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana商城_商品清单</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp"%>

</head>

<body>
	<header>
		<div class="top">
			<div class="title1">
				<h1>商品清单</h1>

			</div>
		</div>
	</header>

	<article>
		<div class="fanhui">
			<div class="fanhui_inner">
				<a href="/web/shopCart!toOrderInfo.action?shopFlag=${shopFlag }">返回</a>
			</div>
		</div>
	</article>

	<article>
		<div class="mycart">
			<div class="cartlist">
				<c:foreach items="${objList}" var="obj" varStatus="status">
					<ul>
						<li>
							<div class="imgchek">
								<table width="100%" border="0">
									<tr>
										<td><a
											href="http://${lvStore.domainName }/web/product!toProduct.action?pcode=${obj[1].code}">
												<img src="${obj[1].pimage }"
												width="90%" />
										</a></td>
									</tr>
								</table>
							</div>
							<div class="totalconts">
								<a
									href="http://${lvStore.domainName }/web/product!toProduct.action?pcode=${obj[1].code}">
									<div class="imgname">
										<p>${obj[1].productName }</p>
									</div>
								</a>
								<div>
									<table width="100%" border="0">
										<tr>
											<td valign="middle" class="imgprcont">数量：X
												${obj[0].shopNum }</td>
										</tr>
									</table>

								</div>
								<div class="imgjiag">USD ${obj[0].shopPrice }</div>
							</div>
							<div class="clear"></div>
						</li>
					</ul>
				</c:foreach>
			</div>
		</div>
	</article>

	<!-- 分享 -->
	<%@include file="/web/mbmcn/common/share.jsp"%>

	<!-- footer -->
	<%@include file="/web/mbmcn/common/footer.jsp"%>

</body>
</html>
