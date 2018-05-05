<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登陸_華揚商城</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var isuccA=true;
var isuccB=true;
$().ready(function() {

	var users=lshop.getCookieToJSON('user');
	if(users.nickname!=null&&users.nickname!=''){
		location.href="/web/userCenterManage!loginAccount.action";
	}
	$("#login").validate({
		rules: {
		     uname: {
		       required: true
	           },
			pwd: {
				required: true,
				minlength: 6
			},
			code:"required"
		},
		messages: {
			uname: {
			required: "请输入登录帐户"
		    },
			pwd: {
				required: "请输入密码",
				minlength: "密码不能少于6位字符"
			},
			code:"<font color='red'>不能为空</font>"
		}
	});
});

function onSub(){
	document.myform.submit();
}
</script>
</head>
<body>
<div class="pop_up_box">
	<span class="box_l">用戶登錄<span class="box_r"><a href="#"><img src="${resDomain}/tvpadcn/res/images/close.gif" border="0" /></a></span></span>
	<form id="login"  action="/web/userCenterManage!login.action" method="post" >
		<input type="hidden" name="jumpurl" value="${param.jumpurl}"/>
		<ul>
			<li><span class="wd1">用戶名：</span><input name="uname" id="uname" class="input2" type="text"  value="${uname}"/></li>
			<li><span class="wd1">密 碼：</span><input name="pwd"  id="pwd" class="input2"  type="password" /></li>
			<li><span class="wd1">&nbsp;</span><a href="password.html">忘记密码？</a></li>
			<li><span class="wd2"><input type="image" onclick="onSub();" src="${resDomain}/tvpadcn/res/images/admin_login02.gif" width="65" height="28"  border="0" /></a> <a href="register.jsp">註 冊</a></span></li>
  		</ul>
  	</form>
</div>
</body>
</html>
