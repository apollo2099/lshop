<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Retrieve Password_banana Mall</title>
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/bmen/res/js/storeIndex.js"></script>
		
	</head>	
<%
String account = request.getParameter("account"); 
String code = request.getParameter("code");
String lang = request.getParameter("lang");
%>	
		
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div  class="xlhk_main">
			<h3>Retrieve Password</h3>
			<div class="password_bz3"></div>
			<ul>
			<form action="/web/userCenterManage!reSetPwd.action" method="post" onsubmit="return submitResetPwdValidate();">
			  <input type="hidden" name="account" value="<%=account%>"/>
			  <input type="hidden" name="code" value="<%=code%>"/>
			  <input type="hidden" name="lang" value="<%=lang%>"/>
			  <li class="modify">
			    <p>
				  <span class="text"><font class="redfont">*</font>New password：</span>
				  <span><input name="lvAccount.pwd" id="rpwd"  type="password" class="input02" maxlength="16" onblur="validatePwd();"/></span>
				</p>
				<p class="prompt">
				  <span class="pt" id="pwd_tip1">6-16 characters. Combination of letters, numbers or symbols is highly recommended</span>
				  <!--输入框获得焦点状态提示语-->
				  <span class="er" style="display:none;" id="pwd_tip2">Please enter the new password</span>
				  <!--错误状态提示语-->
				</p>
			  </li>
	          
			  <li class="modify">
			    <p>
				  <span class="text"><font class="redfont">*</font>Confirm new password：</span>
				  <span><input name="newPwd" id="truePwd"  type="password" class="input02" maxlength="16" onblur="validateTruePwd();" /></span>
				</p>
				<p class="prompt">
				  <span class="pt" id="tpwd_tip1">Please re-enter the new password</span>
				  <!--输入框获得焦点状态提示语-->
				  <span class="er" style="display:none;" id="tpwd_tip2">The two passwords that you entered do not match</span>
				  <!--错误状态提示语-->
				</p>
			  </li>
			  
			 
			  
			  <li>
			    <p class="btn_password"><input name="" type="submit" value="Save"  class="user_center_bt" /></p>
			  </li>
			
			<div class="cb"></div>
			</form>
			</ul>
		
		
		</div>
		
		<!-- footer -->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 
