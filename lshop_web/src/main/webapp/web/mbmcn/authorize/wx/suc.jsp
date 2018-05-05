<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>Banana商城_微信绑定</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp"%>
</head>

<body>
	<header>
		<div class="top">

			<div class="shopping">
				<div class="shoplebg1">
					<div class="shopicon1">
						<a href="/index.html"></a>
					</div>
				</div>
			</div>
			<div class="title">
				<h1>绑定成功</h1>

			</div>
		</div>
	</header>



	<article>
		<div class="subminsucess">
			<div class="sucesstrue"></div>
			<div class="sucwwtip">
				绑定成功！<br /> 您下次仅需微信扫描后即可登陆Banana盒子！
			</div>
		</div>

		<div class="goshop1">
			<a href="/index.html" style="background-color: #0099ff">去购物</a> <a href="/web/userCenterManage!getAccount.action">我的账号</a>
		</div>

	</article>

	<article>
		<div style="height: 150px"></div>
	</article>


	<!-- 分享 -->
	<%@include file="/web/mbmcn/common/share.jsp"%>

	<!-- footer -->
	<%@include file="/web/mbmcn/common/footer.jsp"%>

</body>
</html>

