<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_同步登陆确认</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/authlogin.js?time=<%=timeFlag %>"></script>
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
				<h1>同步登陆确认</h1>

			</div>
		</div>
	</header>



	<article>
		<div class="subminsucess">
			<div class="sucesstrue"></div>
			<div class="sucwwtip">
				您即将使用账号:<span class="star">${username }</span> <br /> 登陆TVpad盒子 <br />
				请确认是否本人操作
			</div>
		</div>

		<div class="goshop">
			<input type="hidden" id="_token" value="${token }">
			<input type="hidden" id="_sessionId" value="${sessionId }">
			<input type="hidden" id="_authtype" value="${authtype }">
			<a href="javascript:void(0);" onclick="agreeLogin();" id="agreeLoginBtn"
				style="background-color: #0099ff; margin-bottom: 40px">登陆TVpad盒子</a>
			<a href="javascript:void(0);" onclick="disagreeLogin();" id="cancelBtn"
				style="background-color: #d8d8d8; color: #8b8c90">取 消</a>
		</div>

	</article>

	<article>
		<div style="height: 100px"></div>
	</article>

	<!-- 分享 -->
	<%@include file="/web/mtmcn/common/share.jsp"%>

	<!-- footer -->
	<%@include file="/web/mtmcn/common/footer.jsp"%>

</body>
</html>

