<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad推广返现联盟</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/FomrValidate.js" ></script>
<script type="text/javascript">
$(function (){
	   var users=lshop.getCookieToJSON('rankpromoter');
		if(users.nickname!=null&&users.nickname!=''){
		  location.href="/promotermanager/rankpromt!getPromtCodeList.action";
		}
}
);
function doLogin(){
   var uname=$.trim($('#uname').val());
   var pwd=$.trim($('#pwd').val());
   if(uname==""){
	  $('#uInfo').html("请输入的帐户！");
      $('#uInfo').show();
      return false;
   }else{
	   if(checkEmail(uname)==false){
			 $('#uInfo').html("email格式不正确！");
			  $('#uInfo').show();
		 return false;
	   }else{
		   $('#uInfo').hide();
		   $('#uInfo').html("请输入的帐户！");
	 }
}
   if(pwd==""){
      $('#pInfo').show();
      return false;
   }else{
	   $('#pInfo').hide();
   }
	return true;
}
</script>
</head>
<body>
<!--顶部信息-->
<div class="top">
	<ul>
		<li class="left"><img src="images/top_logo.gif" /></li>
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
	<div class="diffusion_title"><ul><li>登 录</li></ul></div>	
	<div class="login">
		<form action="rankpromt!login.action" method="post"  onsubmit="return doLogin()">
		<ul>
		   <li>
		    <p class="login_left">&nbsp;</p>
		    <p class="login_right" style="color:red;"> <s:if test="#request.msg!=null"><img src="images/tb02.gif" />${msg}</s:if></p>
		    </li>
			<li>
				<p class="login_left">帐户（Email）：</p>
				<p class="login_right"><input type="text" name="uname" id="uname" size="30" value="${uname}" maxlength="30" />&nbsp;&nbsp;&nbsp;<font color="red" id="uInfo" style="display:none">请输入的帐户！</font></p>
			</li>
			<li>
				<p class="login_left">密 码：</p>
				<p class="login_right"><input type="password" name="pwd" id="pwd" size="30" maxlength="30" />&nbsp;&nbsp;&nbsp;<font color="red" id="pInfo" style="display:none">请输入密码！</font> </p>
			</li>			
			<li>
				<p class="login_left">&nbsp;</p>
				<p class="login_right"><input name="" type="submit" value="登 录" class="button_01" /><a href="findpwd.html" class="link01">找回密码</a>		</p>
			</li>
		</ul>
		</form>
	</div>
</div>
<!--版权-->
<!--版权-->
<%@include file="/promotermanager/common/footer.jsp" %>
	
</body>
</html>
