<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Retrieve Password_TVpad MALL</title>
<!-- 加载公共JS -->
<%@include file="/web/en/common/top.jsp"%>
<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/en/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/en/res/js/page/password2.js"></script>
</head>
<%
String account = request.getParameter("account"); 
String code = request.getParameter("code");
String lang = request.getParameter("lang");
%>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/en/common/header.jsp"%>
<div  class="xlhk_main">
  <h3>Retrieve Password</h3>
  <div class="password_bz3"></div>
  <ul>
    <form action="/web/userCenterManage!reSetPwd.action" method="post" onsubmit="return subForm();">
      <input type="hidden" name="account" value="<%=account%>"/>
	  <input type="hidden" name="code" value="<%=code%>"/>
	  <input type="hidden" name="lang" value="<%=lang%>"/>
      <li class="modify">
        <p>
	        <span class="text"><font class="redfont">*</font>New password：</span>
	        <span><input name="lvAccount.pwd" id="rpwd"  type="password" class="input02" maxlength="16" onblur="checkPassword();"/></span>
        </p>
        <p id="remind_rpwd" class="prompt"></p>
      </li>
      <li class="modify">
        <p>
	        <span class="text"><font class="redfont">*</font>Confirm new password：</span>
	        <span><input name="newPwd" id="truePwd"  type="password" class="input02" maxlength="16" onblur="checkTruePassword();" /></span>
        </p>
        <p id="remind_truePwd" class="prompt"></p>
      </li>
      <li>
        <p class="btn_password">
          <input type="submit" value="Save"  class="user_center_bt" />
        </p>
      </li>
      <div class="cb"></div>
    </form>
  </ul>
</div>
<!-- footer -->
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html>
