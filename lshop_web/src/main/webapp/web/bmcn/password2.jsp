<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>找回密码_banana商城</title>
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/storeIndex.js"></script>
		
	</head>	
<%
String account = request.getParameter("account"); 
String code = request.getParameter("code");
String lang = request.getParameter("lang");
%>	
		
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div  class="xlhk_main">
			<h3>找回密码</h3>
			<div class="password_bz3"></div>
			<ul>
			<form action="/web/userCenterManage!reSetPwd.action" method="post" onsubmit="return submitResetPwdValidate();">
			  <input type="hidden" name="account" value="<%=account%>"/>
			  <input type="hidden" name="code" value="<%=code%>"/>
			  <input type="hidden" name="lang" value="<%=lang%>"/>
			  <li class="modify">
			    <p>
				  <span class="text"><font class="redfont">*</font>新密码：</span>
				  <span><input name="lvAccount.pwd" id="rpwd"  type="password" class="input02" maxlength="16" onblur="validatePwd();"/></span>
				</p>
				<p class="prompt">
				  <span class="pt" id="pwd_tip1">6-16位字符，可使用字母、数字或符合的组合，不建议使用纯字母、纯数字、纯符号</span>
				  <!--输入框获得焦点状态提示语-->
				  <span class="er" style="display:none;" id="pwd_tip2">请输入新密码！</span>
				  <!--错误状态提示语-->
				</p>
			  </li>
	          
			  <li class="modify">
			    <p>
				  <span class="text"><font class="redfont">*</font>确认新密码：</span>
				  <span><input name="newPwd" id="truePwd"  type="password" class="input02" maxlength="16" onblur="validateTruePwd();" /></span>
				</p>
				<p class="prompt">
				  <span class="pt" id="tpwd_tip1">请再次输入密码</span>
				  <!--输入框获得焦点状态提示语-->
				  <span class="er" style="display:none;" id="tpwd_tip2">两次密码输入不一致！</span>
				  <!--错误状态提示语-->
				</p>
			  </li>
			  
			 
			  
			  <li>
			    <p class="btn_password"><input name="" type="submit" value="保存修改"  class="user_center_bt" /></p>
			  </li>
			
			<div class="cb"></div>
			</form>
			</ul>
		
		
		</div>
		
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 
