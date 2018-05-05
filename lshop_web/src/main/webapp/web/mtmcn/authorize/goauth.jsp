<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_授权登陆</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp"%>
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
				<h1>授权登陆</h1>

			</div>
		</div>
	</header>

	<article>
		<div style="height: 150px"></div>
	</article>

<script type="text/javascript">
var pgo = 0;
function JumpUrl() {
	if (pgo == 0) {
		var goto_url = '${jumpurl }';
		if(goto_url == ''){
			goto_url = '/index.html';
		}
		location.href = goto_url;
		pgo = 1;
	}
}
var time = '${dalaytime}';
if(time == null || time == ""){
	time = 500;
	}

setTimeout('JumpUrl()', time);
</script> 
</body>
</html>

