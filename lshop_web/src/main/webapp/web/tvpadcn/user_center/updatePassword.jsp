<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用戶中心_華揚商城</title>
		<%@include file="/web/tvpadcn/common/header.jsp"%>
		<!-- top -->
	<%@include file="/web/tvpadcn/common/top.jsp" %>
		<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/tvpadcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
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
				required: "<font color='red'>請輸入原密碼！</font>",
				minlength: "<font color='red'>密碼不能少于6位字符！</font>",
				maxlength: "<font color='red'>密碼不能大于16位字符！</font>"
			},
			newPwd: {
				required: "<font color='red'>請輸入新密碼！</font>",
				minlength: "<font color='red'>密碼不能少于6位！</font>",
				maxlength: "<font color='red'>密碼不能大于16位字符！</font>"
			},
			truePwd: {
				required: "<font color='red'>請再次輸入新密碼！</font>",
				minlength: "<font color='red'>密碼不能少于6位！</font>",
				maxlength: "<font color='red'>密碼不能大于16位字符！</font>",
				equalTo: "<font color='red'>兩次輸入密碼不一致！</font>"
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

		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/tvpadcn/user_center/leftFrame.jsp"%>

			<div class="right_frame">
				<h1>
					<span class="s_r"><font class="bfont"><img
								src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a
							href="/index.html">首頁</a>--><a
							href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 密碼管理</span>
				</h1>
				<div class="usercenter_box">
					<font color="red">
						<c:if test="${not empty mFlag}">
							<c:if test="${mFlag==1}">原密码不正确！</c:if>
							<c:if test="${mFlag==2}">修改成功!</c:if>
						</c:if>
					</font>
					<%
						session.removeAttribute("msg");
					%>
					<form action="/web/userCenterManage!updatePassword.action"
						method="post" id="myform">
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>原密碼：
							</li>
							<li class="wd2">
								<input name="pwd" id="pwd" class="input2" type="password" />
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>新密码：
							</li>
							<li class="wd2">
								<input name="newPwd" id="newPwd" class="input2" type="password" />
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>確認新密码：
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
									src="${resDomain}/tvpadcn/res/images/user_center_btn.gif" />
							</li>
						</ul>
					</form>
				</div>
			</div>
			<!--End right_Frame-->
		</div>
		<!--End content-->

		<!-- footer-->
		<%@include file="/web/tvpadcn/common/footer.jsp"%>

	</body>
</html>
