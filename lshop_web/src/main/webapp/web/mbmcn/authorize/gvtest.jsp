<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.lshop.common.util.HttpUtil"%>
<%@ page import="java.util.Map"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>test盒子</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp"%>
</head>
<% 
String imgurl = "";
String lid = "";
String host = request.getParameter("host");host=host==null?"":host.trim();
String bizline = request.getParameter("bizline");bizline=bizline==null?"":bizline.trim();

if(!host.equals("") && !bizline.equals("")){
	String url = "http://"+host+"/api/qrlogincreate?bizline="+bizline+"&terminal=2";
	String res = HttpUtil.get(url);
	try{
		Map<String, String> map = HttpUtil.xml2Map(res);
		imgurl = map.get("return.qrcode.url");	
		lid = map.get("return.qrcode.lid");	
	}catch(Exception e){
		e.printStackTrace();
	}
}
%>
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
				<h1>模拟盒子登陆</h1>

			</div>
			
			<article>
				<div class="subminsucess" style="text-align: center;">
					<img alt="" src="<%=imgurl%>">
				</div>
				<div class="goshop" style="text-align: center;">
					<p>检测状态：<font color="red" id="checkStatu"></font></p>
					<p>状态标记：<font color="red" id="checkResultF"></font></p>
					<p>lid状态：<font color="red" id="checkResult"></font></p>
					<p>sid：<font color="red" id="sidResult"></font></p>
				</div>
			</article>
		</div>
	</header>

	<article>
		<div style="height: 150px"></div>
	</article>

<script type="text/javascript">
var host = '<%=host%>';
var bizline = '<%=bizline%>';
function checkStatu() {
	if(host==''||bizline==''){
		$("#checkStatu").html("参数缺失");
		window.clearInterval(mytimer);
		return;
	}
	$("#checkStatu").html("检测中...");
	$.ajax({
		url:"gvtestqrstatus.jsp?lid=<%=lid%>&host=<%=host%>&bizline=<%=bizline%>&terminal=2",
		type: 'post',
		dataType:"text",
		cache:false,
		async:false,
		success:function(str){
			str = str.replace(/(^\s*)|(\s*$)/g, "");
			var data = eval('(' + str + ')');
			var f = data.statu;
			var sid = data.sid;
			$("#sidResult").html(sid);
			$("#checkResultF").html(f);
			if(f=="429"){
				$("#checkResult").html("处理中");
			}
			if(f=="430"){
				$("#checkResult").html("lid超时");
				window.clearInterval(mytimer);
				$("#checkStatu").html("检测结束");
			}
			if(f=="431"){
				$("#checkResult").html("已扫描");
			}
			if(f=="432"){
				$("#checkResult").html("已取消");
			}
			if(f=="200"){
				$("#checkResult").html("登陆成功");
				window.clearInterval(mytimer);
				$("#checkStatu").html("检测结束");
			}
			return;
		}
	});
}

var time = 500;

var mytimer = window.setInterval('checkStatu()', time);
</script> 
</body>
</html>

