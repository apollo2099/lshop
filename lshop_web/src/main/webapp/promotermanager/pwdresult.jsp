<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad推广返现联盟</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--顶部信息-->
<div class="top">
	<ul>
		<li class="left"><img src="images/top_logo.gif" /></li>
		<!--<li class="right">您好！ 某先生  <a href="Concenter.html" class="link01">个人中心</a>  <a href="#">[退出]</a></li>-->
	</ul>
</div>
<div class="clear_p"></div>
<!--banner部份-->
<div class="banner02">
	<ul>
		<li><img src="images/banner02_01.gif" /></li>		
	</ul>
</div>

<!--主要内容-->
<div class="main_conten2">
	<div class="diffusion_title"><ul><li>找回密码</li></ul></div>	
	<div class="login" align="center">
		您的密码已成功发送到邮箱${param.email},请登录您的邮件查看并修改密码，<a href="login.jsp"><font color="red">返回</font></a>
	</div>
</div>
<!--版权-->
<%@include file="/promotermanager/common/footer.jsp" %>
</body>
</html>