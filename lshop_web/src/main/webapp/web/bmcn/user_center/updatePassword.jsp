<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/userCenter.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp"%>

			<div class="right_frame">
				<h1>
					<span class="s_r"><font class="bfont"><img
								src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a
							href="/index.html">首页</a> --><a
							href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 账户安全</span>
				</h1>
				<div class="usercenter_box">
					<font color="red" style="margin-left: 120px;">
						<c:if test="${not empty mFlag}">
							<c:if test="${mFlag==0}">系统错误！</c:if>
							<c:if test="${mFlag==1}">原密码不正确！</c:if>
							<c:if test="${mFlag==2}">修改成功！</c:if>
						</c:if>
					</font>
					<%
						session.removeAttribute("msg");
					%>
					<form action="/web/userCenterManage!updatePassword.action"
						method="post" id="passForm">
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>原密码：
							</li>
							<li class="wd2">
								<input name="pwd" id="pwd" class="input4" type="password" />
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>新密码：
							</li>
							<li class="wd2">
								<input name="newPwd" id="newPwd" class="input4" type="password" />
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>确认新密码：
							</li>
							<li class="wd2">
								<input name="truePwd" id="truePwd" class="input4"
									type="password" />
							</li>
						</ul>
						<ul class="btn">
							<li class="wd1">
								&nbsp;
							</li>
							<li class="wd2">
								<input type="submit" value="保存修改" class="user_center_bt" />
							</li>
						</ul>
					</form>
				</div>
			</div>
			<!--End right_Frame-->
		</div>
		<!--End content-->

		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>

		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp"%>

	</body>
</html>
