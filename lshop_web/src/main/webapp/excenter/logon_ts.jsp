<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		<li class="left"><a href="index.html"><img src="images/top_logo.gif" /></a></li>
		<!--<li class="right">您好！ 某先生  <a href="Concenter.html" class="link01">个人中心</a>  <a href="#">[退出]</a></li>-->
	</ul>
</div>
<div class="clear_p"></div>
<!--banner部份-->
<div class="banner02">
	<ul>
		<li><img src="images/banner02_01.gif" /><a href="diffusionPB.html"><img src="images/banner02_02.gif" /></a></li>		
	</ul>
</div>

<!--主要内容-->
<div class="main_conten2">
	<div class="diffusion_title"><ul><li>申请帐户</li></ul></div>	
	<form action="/excenter/index.html" >
	<div class="logon_ts">
	
		<p>您的申请已经提交，如果您通过了我们的审核，</p>
		<p>我们会通过您留下的Email地址<span class="f_orange">${param.email}</span></p>
		<p>通知您审批的结果，谢谢您的参与。</p>
		<p class="logon_right"><input name="返回" type="submit" class="button_01" value="返回" /></p>		
	</div>
	</form>
</div>
<!--版权-->
<%@include file="/excenter/common/footer.jsp" %>
	
</body>
</html>
