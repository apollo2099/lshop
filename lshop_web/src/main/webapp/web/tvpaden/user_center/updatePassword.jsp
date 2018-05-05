<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center- HUA YANG MALL</title>
		<%@include file="/web/tvpaden/common/header.jsp"%>
		<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
$().ready(function() {
	$("#myform").validate({
		rules: {
			pwd: {
				required: true,
				minlength: 6,
				maxlength: 16
			},
	        newPwd:{
		     required: true,
		     minlength: 6,
		     maxlength: 16
            }
            ,truePwd:{
		     required: true,
		     minlength: 6,
		     maxlength: 16,
		     equalTo: "#newPwd"
            }
			
		},
		messages: {
			pwd: {
				required: "<font color='red'>Please enter the old password!</font>",
				minlength: "<font color='red'>Password shall be at least 6 characters!</font>",
				maxlength: "<font color='red'>Password shall not be longer than 16 characters!</font>"
			},
			newPwd: {
				required: "<font color='red'>Please enter the new password!</font>",
				minlength: "<font color='red'>Password shall be at least 6 characters!</font>",
				maxlength: "<font color='red'>Password shall not be longer than 16 characters!</font>"
			},
			truePwd: {
				required: "<font color='red'>Please enter the new password again!</font>",
				minlength: "<font color='red'>Password shall be at least 6 characters!</font>",
				maxlength: "<font color='red'>Password shall not be longer than 16 characters!</font>",
				equalTo: "<font color='red'>The two passwords you entered do not match!</font>"
			}
		}
	});
});

function onSub(){
	document.myform.onSubmit();
}
</script>
	</head>
	<body>
		<!--  -->
		<%@include file="/web/tvpaden/common/top.jsp"%>

		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/tvpaden/user_center/leftFrame.jsp"%>

			<div class="right_frame">
				<h1>
					<span class="s_r"><font class="bfont"><img
								src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a
							href="/index.html">Home</a>--><a
							href="/web/userCenterManage!getAccount.action">User Center</a>--></font> Change Password </span>
				</h1>
				<div class="usercenter_box">
					<font color="red">
						<c:if test="${not empty mFlag}">
							<c:if test="${mFlag==1}">The old password you entered is incorrect!</c:if>
							<c:if test="${mFlag==2}">Successfully changed!</c:if>
						</c:if>
					</font>
					<%
						session.removeAttribute("msg");
					%>
					<form action="/web/userCenterManage!updatePassword.action"
						method="post" id="myform">
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>Current Password：
							</li>
							<li class="wd2">
								<input name="pwd" id="pwd" class="input2" type="password" />
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>New password：
							</li>
							<li class="wd2">
								<input name="newPwd" id="newPwd" class="input2" type="password" />
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>Confirm New Password：
							</li>
							<li class="wd2">
								<input name="truePwd" id="truePwd" class="input2"
									type="password" />
							</li>
						</ul>
						<ul class="btn">
							<li class="wd1">
								&nbsp;
							</li>
							<li class="wd2">
								<input type="image" onclick="onSub();"
									src="${resDomain}/tvpaden/res/images/user_center_btn.gif" />
							</li>
						</ul>
					</form>
				</div>
			</div>
			<!--End right_Frame-->
		</div>
		<!--End content-->

		<!-- footer-->
		<%@include file="/web/tvpaden/common/footer.jsp"%>

	</body>
</html>
