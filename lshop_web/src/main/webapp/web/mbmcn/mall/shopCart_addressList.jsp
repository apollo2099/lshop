<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>Banana商城_收货人信息</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp"%>
</head>

<body>
	<header>
		<div class="top">
			<div class="shopping">
				<div class="shoplebg1">
					<div class="shopicon1">
						<a href="/"></a>
					</div>
				</div>
			</div>
			<div class="title">
				<h1>收货人信息</h1>
			</div>
		</div>
	</header>

	<article>
		<div class="fanhui" style="height: 72px">
			<div class="fanhui_inner">
				<a href="/web/shopCart!toOrderInfo.action?shopFlag=${shopFlag}">返回</a> <a
					href="/web/shopCart!toAddAddressMobile.action?shopFlag=${shopFlag}" id="addnew">新增</a>
				<div class="clear"></div>
			</div>
		</div>
	</article>

	<article>
		<c:if test="${not empty page.list}">
			<c:foreach items="${page.list}" var="address" varStatus="status">
				<div class="massinfomaw">
					<div class="conmaseg">

						<table width="94%" border="0" align="center">
							<tr>
								<td height="12" align="right"></td>
								<td height="12"></td>
							</tr>
							<tr>
								<td width="25%" height="25" align="right" valign="top">收 货
									人：</td>
								<td width="75%" height="25" valign="top">${address.relName
									}</td>
							</tr>
							<c:if test="${not empty address.mobile}">
							<tr>
								<td height="25" align="right" valign="top">手&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
								<td height="25" valign="top">${address.mobile }</td>
							</tr>
							</c:if>
							<c:if test="${not empty address.tel}">
							<tr>
								<td height="25" align="right" valign="top">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
								<td height="25" valign="top">${address.tel }</td>
							</tr>
							</c:if>
							<tr>
								<td height="25" align="right" valign="top">收货地址：</td>
								<td height="25" valign="top">${address.adress
									}&nbsp;${address.cityName }&nbsp;${address.provinceName
									}&nbsp;${address.contryName }</td>
							</tr>
							<tr>
								<td height="25" align="right" valign="top">邮&nbsp;&nbsp;&nbsp;&nbsp;编：</td>
								<td height="25" valign="top">${address.postCode }</td>
							</tr>
							<tr>
								<td height="12" align="right"></td>
								<td height="12"></td>
							</tr>
						</table>
					</div>
					<div class="maseedit">
						<a href="/web/shopCart!toEditAddressMobile.action?flag=${flag }&addressCode=${address.code}&shopFlag=${shopFlag}"></a>
						<div class="tipedit">
							<i></i> <em></em>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</c:foreach>
		</c:if>
	</article>

	<!-- 分享 -->
	<%@include file="/web/mbmcn/common/share.jsp"%>

	<!-- footer -->
	<%@include file="/web/mbmcn/common/footer.jsp"%>

</body>
</html>
